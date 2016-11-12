/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pencil;

import Object.Drawing.DrawingObjectImpl;
import Object.Drawing.PositionDrawingObject;
import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;
import processing.core.PVector;

/**
 *
 * @author daudirac
 */
public class StrokeFollower1 extends DrawingObjectImpl
{
    public StrokeFollower1()
    {
        this(StrokeFollower1.DEFAULT_NUM_FOLLOWERS,
            StrokeFollower1.DEFAULT_MIN_STROKE_DIST);
    }
    
    public StrokeFollower1(int numFollowers, int minDist)
    {
        super();
        stroke = new Stroke();
        totalNumFollowers = numFollowers;
        strokeMinDistance = minDist;
        
        followers = new ArrayList<>();
        
    }
    
    @Override
    public void update() {
       
    }

    @Override
    public void draw() 
    {
        canvas.stroke(0);
        canvas.strokeWeight(2);
        //stroke.draw();
        
        drawFollowers();
    }

    @Override
    public void setDrawingProperties() {}

    @Override
    public void onMousePressed(int mouseX, int mouseY) {

    }

    @Override
    public void onMouseDragged(int mouseX, int mouseY) 
    {
        stroke.onMouseDragged(mouseX, mouseY);
    }
    
    @Override
    public void onMouseReleased(int mouseX, int mouseY) 
    {
        // start followers
        
        // create followers
        createFollowers();
        
        
    }
    
    public void drawFollowers()
    {
        for (Follower follower: followers)
        {
            follower.draw();
        }
    }
    
    private void createFollowers()
    {
        int x, y;
        int count = 0;
        PVector auxVector;
        
        while (count < totalNumFollowers)
        {
            x = PApplet.floor(canvas.random(0, canvas.width));
            y = PApplet.floor(canvas.random(0, canvas.height));
            
            auxVector = new PVector(x,y);
            if (stroke.isNear(auxVector, strokeMinDistance))
            {
                followers.add(new Follower(auxVector.x,auxVector.y));
                count++;
            }
        }
    }

    @Override
    public void onKeyPressed(char key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    Stroke stroke;
    List<Follower> followers;
    
    int totalNumFollowers;
    int strokeMinDistance;
    
    private static int DEFAULT_NUM_FOLLOWERS = 10;
    private static int DEFAULT_MIN_STROKE_DIST = 10;
    
    public class Follower extends PositionDrawingObject
    {
        PVector strokePoint;
        
        public Follower(float px, float py)
        {
            super(px, py);
        }
        
        public void setStrokePoint(PVector point)
        {
            strokePoint = point;
        }
        
        @Override
        public void update() 
        {
             
        }

        @Override
        public void draw() 
        {
            canvas.ellipse(position.x, position.y,3,3);
        }

        @Override
        public void setDrawingProperties() {}
        
        
        @Override
        public void onMousePressed(int mouseX, int mouseY) {}

        @Override
        public void onMouseDragged(int mouseX, int mouseY) {}

        @Override
        public void onMouseReleased(int mouseX, int mouseY) {}

        @Override
        public void onKeyPressed(char key) {}
    
    }
}
