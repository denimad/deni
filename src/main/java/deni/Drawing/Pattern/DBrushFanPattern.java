/*
 * deni 2017
 */
package main.java.deni.Drawing.Pattern;

import main.java.deni.Canvas.Layer.PGraphics.DAbstractPGraphics;
import main.java.deni.Util.DMathHelper;
import java.util.HashMap;
import java.util.Map;
import processing.core.PApplet;
import processing.core.PVector;
import main.java.deni.Moving.DMovementDescriber;

/**
 *
 * @author daudirac
 */
public class DBrushFanPattern extends DLerpColorFanPattern
{
	public float r1= 0,r2= 1;
	
	public DBrushFanPattern()
	{
		super();

	}

	@Override
	protected FanDrawingObj createFanDrawingObj(
		DMovementDescriber mdes, 
		float circleSize,
		float circleSizeInc)
	{
		return new BrushFanDrawingObj(mdes, circleSize, circleSizeInc);
	}

	private class BrushFanDrawingObj extends DLerpColorFanPattern.FanDrawingObj
	{
		float range;
		protected Map<PVector,Float> sizeStrokeMap;

		public BrushFanDrawingObj(DMovementDescriber movementDescriber, float circleSize, float circleSizeInc) 
		{
			super(movementDescriber, circleSize, circleSizeInc);
			sizeStrokeMap = new HashMap<>();
			range = DMathHelper.random(r1,r2);	
		}

		@Override
		protected void calculateStrokeInfoMaps(PVector point,
				DAbstractPGraphics canvasLayer)
		{
			super.calculateStrokeInfoMaps(point, canvasLayer);

			this.sizeStrokeMap.put(point, DMathHelper.noNegative(
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
