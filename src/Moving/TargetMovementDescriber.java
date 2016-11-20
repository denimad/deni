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
public interface TargetMovementDescriber 
{
    public PVector getTargetLocation();
    public boolean reachedTarget();
}
