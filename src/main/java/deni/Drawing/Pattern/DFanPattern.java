/* 
 * deni 2017
 */
package main.java.deni.Drawing.Pattern;

import main.java.deni.Canvas.Layer.PGraphics.DAbstractPGraphics;
import main.java.deni.Moving.DMovingDrawingObj;
import main.java.deni.Moving.DSeekMovementDescriber;
import main.java.deni.Drawing.DDrawingObjectImpl;
import main.java.deni.Pencil.DStroke;
import main.java.deni.Color.DColorHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import main.java.deni.Color.DColor;
import main.java.deni.Color.DSimpleColor;
import main.java.deni.Util.DMathHelper;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;
import main.java.deni.Moving.DMovementDescriber;
import main.java.deni.Moving.DTargetMovementDescriber;

/**
 *
 * @author daudirac
 */
public class DFanPattern extends DDrawingObjectImpl{

    PVector point;
    PVector dir;
    DStroke stroke;
    drawingState currentState;
	
	
	public static List<String> MODES= Arrays.asList(
			"direction",
			"direct",
			"slight random",
			"direction slight random",
			"random");

    ArrayList<DMovingDrawingObj> movingObjects;
	
	public int strokePointsDistance = DStroke.DEFAULT_POINTS_DISTANCE;
    public int totalMovingObjects;
	
	public float movingObjectsSpeed = 2;
	public float movingObjectsInerciaStrengthMult = 1;
	public float movingObjectsAttractionStrength = 8;
	public float fanCircleSize = 10;
	public float fanCircleSizeInc = 0.2f;
	public float randomStrength=1;
    
    private static final int DEFAULT_NUMBER_MOVING_OBJS = 10;

	public DSimpleColor colorDeAletas = new DSimpleColor();
	
	
	public String mode="direction";
	
	public static final int DEFAULT_COLOR = 
		DColorHelper.WHITE;
    
    enum drawingState
    {
        strokeDrawing,
        pointDrawing;
    }
    
    public DFanPattern()
    {
        super();
        stroke = new DStroke();
        currentState = drawingState.strokeDrawing;
        totalMovingObjects = DFanPattern.DEFAULT_NUMBER_MOVING_OBJS;
    }
    
    @Override
    public void onMousePressed(int mouseX, int mouseY) 
    {
        if (currentState == drawingState.pointDrawing)
        {
            point = new PVector(mouseX, mouseY);
        }
		// on each start of a stroke drawing set the current 
		// points distance value.
		if (currentState == drawingState.strokeDrawing)
        {
			stroke = new DStroke();
            stroke.setPointsDistance(strokePointsDistance);
        }
    }
    
    @Override
    public void onMouseClicked(int mouseX, int mouseY) 
    {
        
    }

    @Override
    public void onMouseDragged(int mouseX, int mouseY) {
        if (currentState == drawingState.strokeDrawing)
        {
            stroke.onMouseDragged(mouseX, mouseY);
        }
    }

    @Override
    public void onMouseReleased(int mouseX, int mouseY) {
        if (currentState == drawingState.pointDrawing)
        {
            dir = PVector.sub(new PVector(mouseX, mouseY), point);    
            this.createMovingObjects(point, stroke.strokePoints);
        }
        
        if (currentState == drawingState.strokeDrawing)
        {
            
        }
        this.toggleState();
    }
    
    
    
    private void toggleState()
    {
        if (currentState == drawingState.strokeDrawing)
        {
            currentState = drawingState.pointDrawing;
        }
        else
        {
            currentState = drawingState.strokeDrawing;
        }
    
    }
    
    
    private void createMovingObjects(PVector point, List<PVector> targetPoints)
    {
        movingObjects = new ArrayList<>(targetPoints.size());
        DMovementDescriber mdes= null;
        for (PVector targetPoint : targetPoints)
        {
            switch(mode)
			{
				case "direction": mdes = new DSeekMovementDescriber(point, dir,targetPoint);
					break;
				case "direct": mdes = new DSeekMovementDescriber(point, PVector.sub(targetPoint, point),targetPoint);
					break;
				case "slight random": mdes = new DSeekMovementDescriber(point, 
					
					PVector.add(new PVector(
							PApplet.cos(canvas.random(0,PApplet.TWO_PI)),
							PApplet.sin(canvas.random(0,PApplet.TWO_PI))),
							PVector.sub(targetPoint, point).normalize().mult(randomStrength*1.5f)),targetPoint);
					break;
				case "direction slight random": mdes = new DSeekMovementDescriber(point, 
					
					PVector.add(new PVector(
							PApplet.cos(DMathHelper.randomInt(0, PApplet.TWO_PI)),
							PApplet.sin(DMathHelper.randomInt(0,PApplet.TWO_PI))),
							dir.normalize().mult(randomStrength*1.5f)).normalize(),targetPoint);
					
					break;
				
				case "random": mdes = new DSeekMovementDescriber(point, 
					new PVector(
							PApplet.cos(DMathHelper.randomInt(0,PApplet.TWO_PI)),
							PApplet.sin(DMathHelper.randomInt(0,PApplet.TWO_PI))),
					targetPoint);
				break;
			}
            
			if (mdes != null)
			{
				mdes.setSpeed(this.movingObjectsSpeed);
				((DSeekMovementDescriber) mdes).inerciaStrength =
						PVector.dist(point,targetPoint)*this.movingObjectsInerciaStrengthMult;
				((DSeekMovementDescriber) mdes).attractionStrength = 
					this.movingObjectsAttractionStrength;

				 this.movingObjects.add(
					this.createFanDrawingObj(mdes, this.fanCircleSize, this.fanCircleSizeInc));
			}
        }
    }
    
    
    
    private void drawMovingObjects(DAbstractPGraphics canvasLayer)
    {
        if (this.movingObjects != null)
        {
            for (DMovingDrawingObj movObj: this.movingObjects)
            {
                movObj.update();
                movObj.draw(canvasLayer);
            }
        }
        
    }

    @Override
    public void onKeyPressed(char key) 
	{
		switch(key)
		{
			case ' ': 
					if (this.movingObjects != null)
					{
						this.createMovingObjects(point, stroke.strokePoints);
					}
					break;
		}
    }

    @Override
    public void update() 
    {
        
    }
	
	

    @Override
    public void draw(DAbstractPGraphics canvasLayer) {
        stroke.draw(canvas.getToolDrawingLayer());
        drawMovingObjects(canvasLayer);
    }

    @Override
    public void setDrawingProperties() {
        
    }
    
    
    protected FanDrawingObj createFanDrawingObj(DMovementDescriber mdes, float circleSize, float circleSizeInc)
	{
		return null;
	}
	
	
    public class FanDrawingObj extends DMovingDrawingObj
    {
		protected float originalCircleSize;
        protected float circleSize;
        protected float circleSizeInc;
        protected DStroke stroke;
        protected boolean painted = false;
        
		
		public FanDrawingObj(DMovementDescriber movementDescriber) {
            this(movementDescriber, 10,  0.2f);
        }
		
		public FanDrawingObj(DMovementDescriber movementDescriber, float circleSize,  float circleSizeInc) {
            super(movementDescriber);
			this.originalCircleSize = circleSize;
            this.circleSize = circleSize;
            this.circleSizeInc = circleSizeInc;
            stroke = new DStroke(1);
        }

		/**
		 *
		 * @param canvasLayer
		 */
		@Override
        public void draw(DAbstractPGraphics canvasLayer) 
        {
             
            if (this.movementDescriber instanceof DTargetMovementDescriber &&
                !((DTargetMovementDescriber) movementDescriber).nearTarget(2))
            {
                stroke.addStrokePoint(movementDescriber.returnLocation().copy());
				if (circleSize>0)
				{
					canvas.getToolDrawingLayer().beginDraw();
					circleSize += circleSizeInc;
					canvas.getToolDrawingLayer().getPG().ellipse(movementDescriber.returnLocation().x, 
							movementDescriber.returnLocation().y, circleSize, circleSize);
					canvas.getToolDrawingLayer().endDraw();
				}
            }
            else
            {
				
                if (!painted)
                {
					
                    canvasLayer.beginDraw();
                    circleSize = 5;
                    painted = true;
                    canvasLayer.getPG().fill(0);
                    canvasLayer.getPG().noStroke();
                    for (PVector point : stroke.strokePoints)
                    {
                        if (circleSize>0)
						{
							circleSize += circleSizeInc;
						
							canvasLayer.getPG().ellipse(point.x,
								point.y,
								circleSize,circleSize);  
						}
                    }
                
                    circleSize = 5;
                    canvasLayer.getPG().fill(DFanPattern.this.colorDeAletas.getColor(), DFanPattern.this.colorDeAletas.getAlpha());
                    for (PVector point : stroke.strokePoints)
                    {
                        if (circleSize>0)
						{
							circleSize += circleSizeInc;
							canvasLayer.getPG().ellipse(point.x,
								point.y,
								 circleSize,circleSize);  
							System.out.println(circleSize);
						}

                    }
                    
                    canvasLayer.endDraw();
                }   
            }

        }

		@Override
		public  void onKeyPressed(char key)
		{
			switch(key)
			{
				case ' ': this.movementDescriber.resetOriginalValues();
				this.painted = false; 
				this.circleSize = this.originalCircleSize;
				break;
			}
		}
		
        @Override
        public void setDrawingProperties() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
		
    }
}
