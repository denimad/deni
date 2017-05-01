/* 
 * deni 2017
 */
package main.java.deni.Moving;

import processing.core.PVector;

/**
 *
 * @author daudirac
 */
public interface DTargetMovementDescriber 
	extends DMovementDescriber
{
    public PVector getTargetLocation();
    public boolean reachedTarget();
	public boolean nearTarget(int distance);
	public float getDistanceToTarget();
	public float getInitialDistance();
}
