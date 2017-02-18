/* 
 * deni 2017
 */
package main.java.deni.Moving;

import main.java.deni.Canvas.CanvasManager;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * This class describes the movement of a particle.
 */
public abstract class MovementDescriberImpl implements MovementDescriber 
{
    protected PVector direction;
    protected PVector location;

    protected float speed;
    protected float SPEED;

    protected float forceStrength;
    
    protected PApplet canvas;
	
    MovementDescriberImpl()
    {
        canvas = CanvasManager.getInstance().getCanvas();
        init();
    }
    
    private void init()
    {
        initValues();
    }
    
     void initValues ()
    {
        setLocation(new PVector(getCanvas().random(getCanvas().width), getCanvas().random(getCanvas().height)));

        float angle = getCanvas().random (PApplet.TWO_PI);
        setDirection(new PVector (PApplet.cos (angle), PApplet.sin (angle)));
        setSpeed(0.1f);
        setSPEED(0.1f);
        setForceStrength(0.1f);
    }   

    void setRandomValues ()
    {
        setLocation(new PVector(getCanvas().random(getCanvas().width), getCanvas().random(getCanvas().height)));

      float angle = getCanvas().random (PApplet.TWO_PI);
        setDirection(new PVector (PApplet.cos (angle), PApplet.sin (angle)));

        setSpeed(getCanvas().random(4, 7));
        setSPEED(getSpeed());
        setForceStrength(getCanvas().random(1, 2) / 10);
    }   

    /**
     * @return the direction
     */
    public PVector getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(PVector direction) {
        this.direction = direction;
    }

    /**
     * @return the location
     */
    public PVector getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(PVector location) {
        this.location = location;
    }

    /**
     * @return the speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * @return the SPEED
     */
    public float getSPEED() {
        return SPEED;
    }

    /**
     * @param SPEED the SPEED to set
     */
    public void setSPEED(float SPEED) {
        this.SPEED = SPEED;
    }

    /**
     * @return the forceStrength
     */
    public float getForceStrength() {
        return forceStrength;
    }

    /**
     * @param forceStrength the forceStrength to set
     */
    public void setForceStrength(float forceStrength) {
        this.forceStrength = forceStrength;
    }

    /**
     * @return the canvas
     */
    public PApplet getCanvas() {
        return canvas;
    }

    /**
     * @param canvas the canvas to set
     */
    public void setCanvas(PApplet canvas) {
        this.canvas = canvas;
    }
    
    
    @Override
    public void move() {
		PVector mov = direction.copy();
		mov.mult (speed);
		location.add (mov);
    }
    
    @Override
    public PVector returnLocation() 
    {
        return this.getLocation();
    }
    
    @Override
    public float getLocationX()
    {
        return this.getLocation().x;
    }
    
    @Override
    public float getLocationY()
    {
        return this.getLocation().y;
    }
}
