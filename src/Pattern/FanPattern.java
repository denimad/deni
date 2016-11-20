/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pattern;

import Moving.MovementDescriber;
import Moving.Mover;
import Moving.MovingDrawingObj;
import Moving.SeekMovementDescriber;
import Moving.SteerMovementDescriber;
import Moving.TargetMovementDescriber;
import Object.Drawing.DrawingObjectImpl;
import Pencil.Stroke;
import java.util.ArrayList;
import java.util.List;
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
    int totalMovingObjects;
    
    private static final int DEFAULT_NUMBER_MOVING_OBJS = 10;
    
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
            
            mdes.setSpeed(2);
            ((SeekMovementDescriber) mdes).inerciaStrength =
                    PVector.dist(point,targetPoint);
            ((SeekMovementDescriber) mdes).attractionStrength = 8;
            
             this.movingObjects.add(
                new FanDrawingObj(mdes));
        }
    }
    
    
    
    private void drawMovingObjects()
    {
        if (this.movingObjects != null)
        {
            for (MovingDrawingObj movObj: this.movingObjects)
            {
                movObj.update();
                movObj.draw();
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
    public void draw() {
        //stroke.draw();
        drawMovingObjects();
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
            super(movementDescriber);
            circleSize = 0;
            circleSizeInc = 0.2f;
            stroke = new Stroke(1);
        }

        @Override
        public void draw() 
        {
            if (this.movementDescriber instanceof TargetMovementDescriber &&
                !((TargetMovementDescriber) movementDescriber).reachedTarget())
            {
                stroke.addStrokePoint(movementDescriber.returnLocation().copy());
            }
            else
            {
                if (!painted)
                {
                    painted = true;
                    canvas.fill(0);
                    canvas.noStroke();
                    for (PVector point : stroke.strokePoints)
                    {
                        circleSize += circleSizeInc;

                        canvas.ellipse(point.x,
                            point.y,
                            circleSize,circleSize);  
                    }
                
                    circleSize = 0;
                    canvas.fill(255);
                    for (PVector point : stroke.strokePoints)
                    {
                        circleSize += circleSizeInc;

                        canvas.ellipse(point.x,
                            point.y,
                             circleSize-5,circleSize-5);  
                    }
                }
                    
            }
            
        }

        @Override
        public void setDrawingProperties() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    
    }
}
