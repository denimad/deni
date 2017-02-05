/*
 * deni 2017
 */
package Pattern;

import Canvas.Layer.PGraphics.AbstractPGraphics;
import Moving.MovementDescriber;
import Util.MathHelper;
import java.util.HashMap;
import java.util.Map;
import processing.core.PApplet;
import processing.core.PVector;

/**
 *
 * @author daudirac
 */
public class BrushFanPattern extends LerpColorFanPattern
{
	public float r1= 0,r2= 1;
	
	public BrushFanPattern()
	{
		super();

	}

	@Override
	protected FanDrawingObj createFanDrawingObj(
		MovementDescriber mdes, 
		float circleSize,
		float circleSizeInc)
	{
		return new BrushFanDrawingObj(mdes, circleSize, circleSizeInc);
	}

	private class BrushFanDrawingObj extends LerpColorFanPattern.FanDrawingObj
	{
		float range;
		protected Map<PVector,Float> sizeStrokeMap;

		public BrushFanDrawingObj(MovementDescriber movementDescriber, float circleSize, float circleSizeInc) 
		{
			super(movementDescriber, circleSize, circleSizeInc);
			sizeStrokeMap = new HashMap<>();
			range = MathHelper.random(r1,r2);	
		}

		@Override
		protected void calculateStrokeInfoMaps(PVector point,
				AbstractPGraphics canvasLayer)
		{
			super.calculateStrokeInfoMaps(point, canvasLayer);

			this.sizeStrokeMap.put(point, MathHelper.noNegative(
					PApplet.map(
						targetmd.getInitialDistance() - targetmd.getDistanceToTarget(),
						0,
						targetmd.getInitialDistance() * range,
						originalCircleSize,
						0)));
		}
		@Override
		protected void calculateCircleSize(PVector point)
		{
			circleSize =  this.sizeStrokeMap.get(point);
		}
	}
}
