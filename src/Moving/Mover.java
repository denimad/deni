/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moving;

import Object.Drawing.DrawingObjectImpl;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

/**
 * This class implements a Mover. a mover is a moving object
 * with different moving settings:
 * steer, seek, noise and radial.
 */
public class Mover extends DrawingObjectImpl
{
    public PVector direction;
    public PVector location;

    public float speed;
    public float SPEED;

    public float noiseScale;
    public float noiseStrength;
    public float forceStrength;

    public float ellipseSize;

    public int color;
    
    public Mover () // Konstruktor = setup der Mover Klasse
    {
      init();
    }
    
    public Mover(float x, float y)
    {
        init();
       location = new PVector(x,y);
    }
    
    private void init()
    {
           setRandomValues();
    }
    
  void setRandomValues ()
  {
    location = new PVector (canvas.random (canvas.width), canvas.random (canvas.height));
    ellipseSize = canvas.random (4, 15);

    float angle = canvas.random (PApplet.TWO_PI);
    direction = new PVector (PApplet.cos (angle), PApplet.sin (angle));

    speed = canvas.random(4, 7);
    SPEED = speed;
    noiseScale = 80;
    noiseStrength = 1;
    forceStrength = canvas.random(1, 2) / 10;

    setRandomColor();
  }
    
    
    void setRandomColor ()
  {
    color = canvas.color(255);
  }
    
    @Override
  public void update ()
  {
    update (0);
  }

  public void update (int mode)
  {
    if (mode == 0) // bouncing ball
    {
      speed = SPEED * 0.7f;
      move();
    }
    else if (mode == 1) // noise
    {
      speed = SPEED * 0.7f;
      addNoise ();
      move();
    }
    else if (mode == 2) // steer
    {
      steer (canvas.mouseX, canvas.mouseY);
      move();
    }
    else if (mode == 3) // seek
    {
      speed = SPEED * 0.7f;
      seek (canvas.mouseX, canvas.mouseY);
      move();
    }
    else // radial
    {
      speed = SPEED * 0.7f;
      addRadial ();
      move();
    }
  }
  
  // HOW TO MOVE ----------------------------

  void steer (float x, float y)
  {
    steer (x, y, 1);
  }

  void steer (float x, float y, float strength)
  {

    float angle = PApplet.atan2 (y-location.y, x -location.x);

    PVector force = new PVector (PApplet.cos (angle), PApplet.sin (angle));
    force.mult (forceStrength * strength);

    direction.add (force);
    direction.normalize();

    float currentDistance = canvas.dist (x, y, location.x, location.y);

    if (currentDistance < 70)
    {
      speed = canvas.map (currentDistance, 0, 70, 0, SPEED);
    }
    else speed = SPEED;
  }

  void seek (float x, float y)
  {
    seek (x, y, 1);
  }

  void seek (float x, float y, float strength)
  {

    float angle = canvas.atan2 (y-location.y, x -location.x);

    PVector force = new PVector (PApplet.cos (angle), PApplet.sin (angle));
    force.mult (forceStrength * strength);

    direction.add (force);
    direction.normalize();
  }

  void addRadial ()
  {

    float m = canvas.noise (canvas.frameCount / (2*noiseScale));
    m = PApplet.map(m, 0f, 1f, - 1.2f, 1.2f);

    float maxDistance = m * PApplet.dist(0, 0, canvas.width/2, canvas.height/2);
    float distance = PApplet.dist (location.x, location.y, canvas.width/2, canvas.height/2);

    float angle = PApplet.map (distance, 0, maxDistance, 0, PApplet.TWO_PI);

    PVector force = new PVector (PApplet.cos(angle), PApplet.sin(angle));
    force.mult (forceStrength);

    direction.add (force);
    direction.normalize();
  }

  void addNoise ()
  {

    float noiseValue = canvas.noise (location.x /noiseScale, location.y / noiseScale, canvas.frameCount / noiseScale);
    noiseValue*= PApplet.TWO_PI * noiseStrength;

    PVector force = new PVector (PApplet.cos (noiseValue), PApplet.sin (noiseValue));
    //Processing 2.0:
    //PVector force = PVector.fromAngle (noiseValue);
    force.mult (forceStrength);
    direction.add (force);
    direction.normalize();
  }

  // MOVE -----------------------------------------

  protected void move ()
  {

    PVector velocity = direction.get();
    velocity.mult (speed);
    location.add (velocity);
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
            canvasLayer.fill (color);
            canvasLayer.ellipse (location.x, location.y, ellipseSize, ellipseSize);
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
