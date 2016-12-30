/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pattern;

import Moving.MovementDescriber;

import Moving.MovingDrawingObj;
import Moving.SeekMovementDescriber;
import Moving.TargetMovementDescriber;
import Object.Drawing.DrawingObjectImpl;
import Pencil.Stroke;
import Util.ColorHelper;
import java.util.ArrayList;
import java.util.List;
import processing.core.PGraphics;
import processing.core.PVector;

/**
 *
 * @author daudirac
 */
public class FanPattern extends DrawingObjectImpl{

    PVector point;
    PVector dir;
    Stroke stroke;
    drawingState currentState;

    ArrayList<MovingDrawingObj> movingObjects;
	
	public int strokePointsDistance = Stroke.DEFAULT_POINTS_DISTANCE;
    public int totalMovingObjects;
	
	public float movingObjectsSpeed = 2;
	public float movingObjectsInerciaStrengthMult = 1;
	public float movingObjectsAttractionStrength = 8;
	public float fanCircleSizeInc = 0.2f;
    
    private static final int DEFAULT_NUMBER_MOVING_OBJS = 10;

	public int colorDeAletas = FanPattern.DEFAULT_COLOR;
	
	public static final int DEFAULT_COLOR = 
		ColorHelper.getInstance().getColor(255, 0, 0);
    
    enum drawingState
    {
        strokeDrawing,
        pointDrawing;
    }
    
    public FanPattern()
    {
        super();
        stroke = new Stroke();
        currentState = drawingState.strokeDrawing;
        totalMovingObjects = FanPattern.DEFAULT_NUMBER_MOVING_OBJS;
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
            stroke = new Stroke();
            currentState = drawingState.strokeDrawing;
        }
    
    }
    
    
    private void createMovingObjects(PVector point, List<PVector> targetPoints)
    {
        movingObjects = new ArrayList<>(targetPoints.size());
        MovementDescriber mdes;
        for (PVector targetPoint : targetPoints)
        {
            
            mdes = new SeekMovementDescriber(point, dir.normalize(),targetPoint);
            
            mdes.setSpeed(this.movingObjectsSpeed);
            ((SeekMovementDescriber) mdes).inerciaStrength =
                    PVector.dist(point,targetPoint)*this.movingObjectsInerciaStrengthMult;
            ((SeekMovementDescriber) mdes).attractionStrength = 
				this.movingObjectsAttractionStrength;
            
             this.movingObjects.add(
                new FanDrawingObj(mdes, this.fanCircleSizeInc));
        }
    }
    
    
    
    private void drawMovingObjects(PGraphics canvasLayer)
    {
        if (this.movingObjects != null)
        {
            for (MovingDrawingObj movObj: this.movingObjects)
            {
                movObj.update();
                movObj.draw(canvasLayer);
            }
        }
        
    }

    @Override
    public void onKeyPressed(char key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() 
    {
        
    }

    @Override
    public void draw(PGraphics canvasLayer) {
        stroke.draw(canvas.getToolDrawingLayer());
        drawMovingObjects(canvasLayer);
    }

    @Override
    public void setDrawingProperties() {
        
    }
    
    
    class FanDrawingObj extends MovingDrawingObj
    {
        float circleSize;
        float circleSizeInc;
        Stroke stroke;
        boolean painted = false;
        
		
		public FanDrawingObj(MovementDescriber movementDescriber) {
            this(movementDescriber, 0.2f);
        }
		
		public FanDrawingObj(MovementDescriber movementDescriber, float circleSizeInc) {
            super(movementDescriber);
            circleSize = 10;
            this.circleSizeInc = circleSizeInc;
            stroke = new Stroke(1);
        }

        @Override
        public void draw(PGraphics canvasLayer) 
        {
             
            if (this.movementDescriber instanceof TargetMovementDescriber &&
                !((TargetMovementDescriber) movementDescriber).nearTarget(2))
            {
                stroke.addStrokePoint(movementDescriber.returnLocation().copy());
				canvas.getToolDrawingLayer().beginDraw();
				if (circleSize>0){circleSize -= circleSizeInc;}
				
				canvas.getToolDrawingLayer().ellipse(movementDescriber.returnLocation().x, 
							movementDescriber.returnLocation().y, circleSize, circleSize);
				canvas.getToolDrawingLayer().endDraw();
            }
            else
            {
				circleSize =10;
                if (!painted)
                {
					
                    canvasLayer.beginDraw();
                    
                    painted = true;
                    canvasLayer.fill(0);
                    canvasLayer.noStroke();
                    for (PVector point : stroke.strokePoints)
                    {
                        if (circleSize>0){circleSize -= circleSizeInc;}

                        canvasLayer.ellipse(point.x,
                            point.y,
                            circleSize,circleSize);  
                    }
                
                    circleSize = 10;
                    canvasLayer.fill(FanPattern.this.colorDeAletas);
                    for (PVector point : stroke.strokePoints)
                    {
                        if (circleSize>0){circleSize -= circleSizeInc;}

                        canvasLayer.ellipse(point.x,
                            point.y,
                             circleSize-5,circleSize-5);  
                    }
                    
                    canvasLayer.endDraw();
                }   
            }

        }

        @Override
        public void setDrawingProperties() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    
    }
}
