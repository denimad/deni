/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moving;

import processing.core.PApplet;
import processing.core.PVector;

/**
 *
 * @author daudirac
 */
public class SteerMovementDescriber extends MovementDescriberImpl
{
    private PVector targetLocation; 
    private float strength;
    
    private static final float DEFAULT_STRENGTH = 1;
    
    public SteerMovementDescriber()
    {
        super();
        this.targetLocation = new PVector(0,0);
        this.strength = SteerMovementDescriber.DEFAULT_STRENGTH;
    }
    
    public SteerMovementDescriber(PVector location,
            PVector direction,
            PVector targetLocation)
    {
         this();
         this.location = location;
         this.direction = direction;
         this.targetLocation = targetLocation;
    }
    
    public SteerMovementDescriber(PVector location,
            PVector targetLocation)
    {
         this();
         this.location = location;
         this.targetLocation = targetLocation;
    }
    
    public void setTargetLocation(PVector targetLocation)
    {
         this.targetLocation = targetLocation;
    }
    
    public PVector getTargetLocation()
    {
         return this.targetLocation;
    }

    @Override
    public void update() 
    {
        steer(targetLocation.x, targetLocation.y);
    }
    
    void steer (float x, float y)
    {
      steer (x, y, strength);
    }
    
    void steer (float x, float y, float strength)
    {

      float angle = PApplet.atan2 (y-getLocation().y, x -getLocation().x);

      PVector force = new PVector (PApplet.cos (angle), PApplet.sin (angle));
      //force.mult (getForceStrength() * strength);
        force.normalize();
        
      getDirection().add (force);
      getDirection().normalize();

      float currentDistance = PApplet.dist (x, y, getLocation().x, getLocation().y);

      if (currentDistance < 70)
      {
        setSpeed(PApplet.map(currentDistance, 0, 70, 0, getSPEED()));
      }
      else setSpeed(getSPEED());
    }
    
}
