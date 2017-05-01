/* 
 * deni 2017
 */
package main.java.deni.Moving;

import processing.core.PVector;

/**
 *
 * @author daudirac
 */
public interface DMovementDescriber 
{
    public PVector returnLocation();
	public void resetOriginalValues();
    public float getLocationX();
    public float getLocationY();
    public void setSpeed(float speed);
    public void move();
    public void update();
}
