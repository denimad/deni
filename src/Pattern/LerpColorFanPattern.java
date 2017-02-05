/*
 * deni 2017
 */
package Pattern;

import Canvas.Layer.PGraphics.AbstractPGraphics;
import Moving.MovementDescriber;
import Moving.TargetMovementDescriber;
import Util.ColorHelper;
import Util.MathHelper;
import java.util.HashMap;
import java.util.Map;
import processing.core.PVector;

/**
 *
 * @author daudirac
 */
public class LerpColorFanPattern extends FanPattern
{

	public LerpColorFanPattern()
	{
		super();
	}

	protected FanDrawingObj createFanDrawingObj(
		MovementDescriber mdes, 
		float circleSize,
		float circleSizeInc)
	{
		return new FanDrawingObj(mdes, circleSize, circleSizeInc);
	}

	float[] circleSizeRange = new float[2];

	protected class FanDrawingObj extends FanPattern.FanDrawingObj
	{
		TargetMovementDescriber targetmd;
		protected Map<PVector,Integer> lerpColorMap;

		public FanDrawingObj(MovementDescriber movementDescriber, 
			float circleSize,
			float circleSizeInc) 
		{
			super(movementDescriber, circleSize, circleSizeInc);
			targetmd = (TargetMovementDescriber) movementDescriber;
			lerpColorMap = new  HashMap<>();
		}

		@Override
		public void draw(AbstractPGraphics canvasLayer) 
		{

			if (!targetmd.nearTarget(2))
		{
				PVector point = movementDescriber.returnLocation().copy();
				stroke.addStrokePoint(point);

				this.calculateStrokeInfoMaps(point, canvasLayer);
			}
			else
			{
				if (!painted)
				{
					canvasLayer.beginDraw();
						this.circleSize = this.originalCircleSize;
						canvasLayer.getPG().noStroke();
						canvasLayer.getPG().fill(colorDeAletas,colorDeAletasalpha);
						for (PVector point : stroke.strokePoints)
						{
							this.calculateCircleSize(point);
							canvasLayer.getPG().ellipse(point.x,
								point.y,
								circleSize+2f,circleSize+2f);  
						}

						this.circleSize = this.originalCircleSize;
						for (PVector point : stroke.strokePoints)
						{
							canvasLayer.getPG().fill(lerpColorMap.get(point),startColoralpha);
							this.calculateCircleSize(point);
							canvasLayer.getPG().ellipse(point.x,
								point.y,
								circleSize,circleSize);  
						}
					canvasLayer.endDraw();

					//set painted variable to true
					painted = true;
			}
		}
		}

		/**
		 * This method returns the calculated lerp color. 
		 * it supports 2 levels of color lerp. 
		 * (only 1 and 2 values are valid)
		 * @param levels 
		 */
		private int getLerpColor(AbstractPGraphics canvasLayer, float totalDist, float curDist,int level)
		{
			int lerpColor=0;
			if (level != 1 || level != 2)
			{
				float amt = MathHelper.directRuleOfThree(
							totalDist,
							curDist, 
							1);

				if (level == 1)
				{
					lerpColor = canvasLayer.getPG().lerpColor(
						startColor, 
						endColor, 
						amt);
				}
				else
				{
					if (amt<0.5)
					{
						lerpColor = canvasLayer.getPG().lerpColor(
						startColor, 
						middleColor, 
						amt*2f);
					}
					else
					{
						lerpColor = canvasLayer.getPG().lerpColor(
						middleColor, 
						endColor, 
						(amt-0.5f)*2f);
					}
				}
			}
			else
			{
				System.err.println("not valid level used in lerpcolor calculation." + 
					this.getClass());
			}

			return lerpColor;
		}


		protected void calculateStrokeInfoMaps(PVector point,
				AbstractPGraphics canvasLayer)
		{
			lerpColorMap.put(
					point,
					this.getLerpColor(canvasLayer, 
							targetmd.getInitialDistance(),
							targetmd.getDistanceToTarget(),
							level));
		}

		protected void calculateCircleSize(PVector point)
		{
			circleSize += circleSizeInc;
		}
	}
	
	
	public int startColor = ColorHelper.BROWN2;
	public int startColoralpha = 255;
	public int endColor = ColorHelper.AQUAMARINE;
	public int endColoralpha = 255;
	public int middleColor = ColorHelper.GOLDENROD;
	public int middleColoralpha = 255;
	public int level=2;

}