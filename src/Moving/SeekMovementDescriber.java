/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moving;

import processing.core.PVector;

/**
 *
 * @author daudirac
 */
public class SeekMovementDescriber extends MovementDescriberImpl implements TargetMovementDescriber
{
    private PVector targetLocation; 
    public float inerciaStrength;
    public float attractionStrength;
    
    
    public SeekMovementDescriber()
    {
        super();
        this.targetLocation = new PVector(0,0);
        this.inerciaStrength = 0;
        this.attractionStrength = 0;
    }
    
    public SeekMovementDescriber(PVector location,
            PVector direction,
            PVector targetLocation)
    {
         this();
         this.location = location.copy();
         this.direction = direction.copy();
         this.targetLocation = targetLocation.copy();
    }
    
    public SeekMovementDescriber(PVector location,
            PVector targetLocation)
    {
         this();
         this.location = location.copy();
         this.targetLocation = targetLocation.copy();
    }
    
    public void setTargetLocation(PVector targetLocation)
    {
         this.targetLocation = targetLocation;
    }
    

    @Override
    public void update() 
    {
        seek(targetLocation);
    }
    
    

    void seek (PVector targetLoc)
    {
        PVector attraction = PVector.sub(targetLoc, location);
        attraction.normalize();
        attraction.mult(this.attractionStrength);
        
        direction.mult(this.inerciaStrength);
        direction.add(attraction);
        direction.normalize();
        
    }
    
    @Override
  public void move()
  {
      if ( PVector.dist(targetLocation, location)<5)
      {
          location = targetLocation.copy();
      }
      else
      {
          super.move();
      }
      
  }  

    @Override
    public PVector getTargetLocation() {
        return this.targetLocation;
    }

    @Override
    public boolean reachedTarget() {
       return this.location.equals(this.targetLocation);
    }
	
	public boolean nearTarget(int distance)
	{
		return PVector.dist(targetLocation, location)< distance;
	}
}
