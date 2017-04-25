/*
 * deni 2017
 */
package main.java.deni.Drawing.Pattern;

import main.java.deni.Canvas.Layer.PGraphics.AbstractPGraphics;
import main.java.deni.Color.DColorPool;
import main.java.deni.Color.DSimpleColor;
import main.java.deni.Moving.MovementDescriber;
import main.java.deni.Util.MathHelper;
import processing.core.PApplet;

/**
 *
 * @author daudirac
 */
public class LerpColorPoolFanPattern extends LerpColorFanPattern
{
	public LerpColorPoolFanPattern()
	{
		super();
	}
	
	protected FanDrawingObj createFanDrawingObj(
		MovementDescriber mdes, 
		float circleSize,
		float circleSizeInc)
	{
		return new LerpColorPoolFanDrawingObj(mdes, circleSize, circleSizeInc);
	}
	
	protected class LerpColorPoolFanDrawingObj extends LerpColorFanPattern.FanDrawingObj
	{
	
		public LerpColorPoolFanDrawingObj(MovementDescriber movementDescriber, float circleSize, float circleSizeInc) {
			super(movementDescriber, circleSize, circleSizeInc);

			dstartcolor = (DSimpleColor) startColorPool.getDColor();
			dmiddlecolor = (DSimpleColor) middleColorPool.getDColor();
			dendcolor = (DSimpleColor) endColorPool.getDColor();

			this.startColor = dstartcolor.getColor();
			this.startColoralpha = (int) dstartcolor.getAlpha();
			this.middleColor = middleColorPool.getDColor().getColor();
			this.middleColoralpha = (int) dmiddlecolor.getAlpha();
			this.endColor = endColorPool.getDColor().getColor();
			this.endColoralpha = (int) dendcolor.getAlpha();
		}
		
		
		protected DSimpleColor getLerpColor(AbstractPGraphics canvasLayer, float totalDist, float curDist,int level)
		{
			int lerpColor=0;
			float alpha = 0;

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
					alpha = PApplet.map(amt,
						0,100,startColoralpha,endColoralpha);
				}
				else
				{
					if (amt<0.5)
					{
						lerpColor = canvasLayer.getPG().lerpColor(
							startColor, 
							middleColor, 
							amt*2f);
						alpha = PApplet.map(amt*2f,
							0,100,startColoralpha,middleColoralpha);
					}
					else
					{
						lerpColor = canvasLayer.getPG().lerpColor(
							middleColor, 
							endColor, 
							(amt-0.5f)*2f);
						alpha = PApplet.map((amt-0.5f)*2f,
							0,100,startColoralpha,middleColoralpha);
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
		
		public int startColor;
		public int startColoralpha = 255;
		public int endColor;
		public int endColoralpha = 255;
		public int middleColor;
		public int middleColoralpha = 255;
		public int level=2;
	}

	public DColorPool startColorPool = new DColorPool();
	public DColorPool middleColorPool = new DColorPool();
	public DColorPool endColorPool = new DColorPool();
	
	public DSimpleColor dstartcolor = new DSimpleColor();
	public DSimpleColor dmiddlecolor = new DSimpleColor();
	public DSimpleColor dendcolor = new DSimpleColor();
}
