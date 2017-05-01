/* 
 * deni 2017
 */
package main.java.deni.Moving;

import main.java.deni.Drawing.DDrawingObjectImpl;

/**
 *
 * @author daudirac
 */
public abstract class DMovingDrawingObj extends DDrawingObjectImpl{

    protected DMovementDescriber movementDescriber;
    
    public DMovingDrawingObj(DMovementDescriber movementDescriber)
    {
        this.movementDescriber = movementDescriber;
    }
    
    @Override
    public void update() {
        this.movementDescriber.update();
        this.movementDescriber.move();
    }
    
}
