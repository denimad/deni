/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pencil;

import Moving.Mover;
import Object.Drawing.DrawingObjectImpl;
import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

/**
 *
 * @author daudirac
 */
public class StrokeFollower2 extends DrawingObjectImpl{
    public StrokeFollower2()
    {
        this(StrokeFollower2.DEFAULT_NUM_FOLLOWERS,
            StrokeFollower2.DEFAULT_MIN_STROKE_DIST);
    }
    
    public StrokeFollower2(int numFollowers, int minDist)
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
    public void draw(PGraphics canvasLayer) 
    {
        stroke.draw(canvas.getToolDrawingLayer());
        drawFollowers(canvasLayer);
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
        // get stroke movement
       
        
        
        // create followers
        createFollowers();
    }
    
    public void drawFollowers(PGraphics canvasLayer)
    { 
        for (Follower follower: followers)
        {
            PVector np = stroke.getNearestPoint(follower.loc, strokeMinDistance);
            if ( np != null)
            {
                PVector movement = stroke.strokeMovement.get(np);
                if ( movement != null)
                {
                    follower.move(movement, 0.1f);
                }
                
            }
            follower.update();
            follower.draw(canvasLayer);
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
                followers.add(new Follower(auxVector));
                count++;
            }
        }
    }
    
  

    @Override
    public void onKeyPressed(char key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }

    @Override
    public void onMouseClicked(int mouseX, int mouseY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
    class Follower extends DrawingObjectImpl
    {
        PVector loc;
        PVector dir;
        PVector velocity;
        float strength;
        
        Follower(PVector loc)
        {
            super();
            this.loc = loc;
            dir = new PVector(0,0);
            strength = 0;
        }
        
        void move(PVector direction, float strength)
        {
            this.strength= strength;
            dir = direction.copy();
            dir.normalize();
            dir.mult(strength);
        }
        
        @Override
        public void update()
        {
            strength-=0.001;
            dir.mult(0.91f);
            loc.add(dir);
        }
        

        @Override
        public void onMousePressed(int mouseX, int mouseY) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void onMouseDragged(int mouseX, int mouseY) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void onMouseReleased(int mouseX, int mouseY) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void onKeyPressed(char key) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void draw(PGraphics canvasLayer) {
            canvasLayer.beginDraw();
                canvasLayer.fill(255);
                canvasLayer.ellipse(loc.x, loc.y,10,10);
            canvasLayer.endDraw();
        }

        @Override
        public void setDrawingProperties() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void onMouseClicked(int mouseX, int mouseY) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }


       
    }
    
    Stroke stroke;
    List<Follower> followers;
    List<PVector> strokeMovement;
    
    int totalNumFollowers;
    int strokeMinDistance;
    
    private static int DEFAULT_NUM_FOLLOWERS = 10;
    private static int DEFAULT_MIN_STROKE_DIST = 10;
}
