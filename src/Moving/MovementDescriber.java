/* 
 * deni 2017
 */
package Moving;

import processing.core.PVector;

/**
 *
 * @author daudirac
 */
public interface MovementDescriber 
{
    public PVector returnLocation();
    public float getLocationX();
    public float getLocationY();
    public void setSpeed(float speed);
    public void move();
    public void update();
}
