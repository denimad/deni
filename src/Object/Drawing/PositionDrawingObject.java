/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object.Drawing;

import processing.core.PVector;

/**
 *
 * @author daudirac
 */
public abstract class PositionDrawingObject extends DrawingObjectImpl
{
    public PositionDrawingObject(float px, float py)
    {
        initialPosition = new PVector(px, py);
        position = new PVector(px,py);
    }
    
    public void move(int px, int py)
    {
        position.set(px, py);
    }
    
    
    public void addPosition(PVector p)
    {
        position.add(p);
    }
    
    protected PVector initialPosition;
    protected PVector position;
}
