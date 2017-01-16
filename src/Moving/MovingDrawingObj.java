/* 
 * deni 2017
 */
package Moving;

import Drawing.DrawingObjectImpl;

/**
 *
 * @author daudirac
 */
public abstract class MovingDrawingObj extends DrawingObjectImpl{

    protected MovementDescriber movementDescriber;
    
    public MovingDrawingObj(MovementDescriber movementDescriber)
    {
        this.movementDescriber = movementDescriber;
    }
    
    @Override
    public void update() {
        this.movementDescriber.update();
        this.movementDescriber.move();
    }
    
}
