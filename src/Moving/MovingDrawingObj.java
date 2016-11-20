/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moving;

import Object.Drawing.DrawingObjectImpl;

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
