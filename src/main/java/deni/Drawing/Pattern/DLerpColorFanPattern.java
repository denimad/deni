/*
 * deni 2017
 */
package main.java.deni.Drawing.Pattern;

import main.java.deni.Canvas.Layer.PGraphics.DAbstractPGraphics;
import main.java.deni.Color.DColorHelper;
import main.java.deni.Util.DMathHelper;
import java.util.HashMap;
import java.util.Map;
import main.java.deni.Color.DColor;
import main.java.deni.Color.DSimpleColor;
import processing.core.PApplet;
import processing.core.PVector;
import main.java.deni.Moving.DMovementDescriber;
import main.java.deni.Moving.DTargetMovementDescriber;

/**
 *
 * @author daudirac
 */
public class DLerpColorFanPattern extends DFanPattern
{

	public DLerpColorFanPattern()
	{
		super();
	}

	protected FanDrawingObj createFanDrawingObj(
		DMovementDescriber mdes, 
		float circleSize,
		float circleSizeInc)
	{
		return new FanDrawingObj(mdes, circleSize, circleSizeInc);
	}

	float[] circleSizeRange = new float[2];

	protected class FanDrawingObj extends DFanPattern.FanDrawingObj
	{
		DTargetMovementDescriber targetmd;
		protected Map<PVector,DSimpleColor> lerpColorMap;

		public FanDrawingObj(DMovementDescriber movementDescriber, 
			float circleSize,
			float circleSizeInc) 
		{
			super(movementDescriber, circleSize, circleSizeInc);
			targetmd = (DTargetMovementDescriber) movementDescriber;
			lerpColorMap = new  HashMap<>();
		}

		@Override
		public void draw(DAbstractPGraphics canvasLayer) 
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
						canvasLayer.getPG().fill(colorDeAletas.getColor(),colorDeAletas.getAlpha());
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
							canvasLayer.getPG().fill(
								lerpColorMap.get(point).getColor(),
								lerpColorMap.get(point).getAlpha());
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
		protected DSimpleColor getLerpColor(DAbstractPGraphics canvasLayer, float totalDist, float curDist,int level)
		{
			int lerpColor=0;
			float alpha = 0;

			if (level != 1 || level != 2)
			{
				float amt = DMathHelper.directRuleOfThree(
							totalDist,
							curDist, 
							1);

				if (level == 1)
				{
					lerpColor = canvasLayer.getPG().lerpColor(
						startColor.getColor(), 
						endColor.getColor(), 
						amt);
					alpha = PApplet.map(amt,
						0,100,
						DMathHelper.round(startColor.getAlpha()), 
						DMathHelper.round(endColor.getAlpha()));
				}
				else
				{
					if (amt<0.5)
					{
						lerpColor = canvasLayer.getPG().lerpColor(
							startColor.getColor(), 
							middleColor.getColor(), 
							amt*2f);
						alpha = PApplet.map(amt*2f,
							0,100,
							DMathHelper.round(startColor.getAlpha()), 
							DMathHelper.round(middleColor.getAlpha()));
					}
					else
					{
						lerpColor = canvasLayer.getPG().lerpColor(
							middleColor.getColor(), 
							endColor.getColor(), 
							(amt-0.5f)*2f);
						alpha = PApplet.map((amt-0.5f)*2f,
							0,100,
							DMathHelper.round(middleColor.getAlpha()), 
							DMathHelper.round(endColor.getAlpha()));
					}
				}
			}
			else
			{
				System.err.println("not valid level used in lerpcolor calculation." + 
					this.getClass());
			}

			return new DSimpleColor(lerpColor, alpha);
			
		}


		protected void calculateStrokeInfoMaps(PVector point,
				DAbstractPGraphics canvasLayer)
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
	
	
	public DColor startColor = new DSimpleColor(DColorHelper.BROWN2);
	public DColor endColor = new DSimpleColor(DColorHelper.AQUAMARINE, 255);	
	public DColor middleColor = new DSimpleColor(DColorHelper.GOLDENROD);
	
	public int level=2;

}