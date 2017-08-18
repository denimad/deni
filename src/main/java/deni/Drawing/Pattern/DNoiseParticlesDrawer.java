/*
 * deni 2017
 */
package main.java.deni.Drawing.Pattern;

import java.util.ArrayList;
import java.util.List;
import main.java.deni.Canvas.Layer.PGraphics.DAbstractPGraphics;
import main.java.deni.Color.DColor;
import main.java.deni.Color.DColorPool;
import main.java.deni.Drawing.DDrawingObjectImpl;
import main.java.deni.Pencil.DStroke;
import main.java.deni.Util.DMathHelper;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * NoiseParticlesDrawingObj
 * @author daudirac
 */
public class DNoiseParticlesDrawer extends DDrawingObjectImpl
{
	
	List<PVector> loc;
	List<PVector> dir;
	List<Float> speed;
	List<DColor> color;
	List<DColor> borderColor;
	
	DStroke stroke1;
	DStroke stroke2;
	State currentState;
	
	int totalParticles = 10;
	
	public float circleSize = 2;
	public float varCircleSize = 2;
	public float circleInc = 0;
	
	public float noiseScale = 80;
	public float noiseStrength = 1;
	
	enum State
	{
		stroke1,
		stroke2,
		particles
	}
	
	public DColorPool colorPool  = new DColorPool();
	public DColorPool borderColorPool = new DColorPool();
	
	public DNoiseParticlesDrawer()
	{
		stroke1 = new DStroke();
		currentState = State.stroke1;
		
		loc = new ArrayList<>();
		dir = new ArrayList<>();
		speed = new ArrayList<>();
		color = new ArrayList<>();
		borderColor = new ArrayList<>();
		
	}
	
	@Override
    public void onMousePressed(int mouseX, int mouseY) 
    {
		loc.clear();
		dir.clear();
		speed.clear();
		color.clear();
		borderColor.clear();
		varCircleSize = circleSize;
	}
	
	
	@Override
	public void onMouseDragged(int mouseX, int mouseY) 
    {
		if (currentState == State.stroke1)
		{
			stroke1.onMouseDragged(mouseX, mouseY);
			float angle = DMathHelper.random(0, PApplet.TWO_PI);
			
			
			loc.add(new PVector(mouseX, mouseY));
			dir.add(new PVector (
				PApplet.cos (angle) * 1, 
				PApplet.sin (angle) * 1));
			speed.add(DMathHelper.random(
				1, 
				3));
			color.add(colorPool.getDColor());
			borderColor.add(borderColorPool.getDColor());
		}
	}
	@Override
    public void onMouseReleased(int mouseX, int mouseY) 
    {
		this.stroke1.clear();
	}
	
	
	@Override
	public void update() 
	{
		
	}

	@Override
	public void draw(DAbstractPGraphics canvasLayer) 
	{
		stroke1.draw(canvas.getToolDrawingLayer());
		float angle;
		PVector velocity;
		
		for (int i = 0 ; i < loc.size() ; i++)
		{
			angle = 
				canvas.noise (
					loc.get(i).x / noiseScale, 
					loc.get(i).y / noiseScale, 
					(float) canvas.frameCount / noiseScale) * PApplet.TWO_PI * noiseStrength;
			
			dir.get(i).x = PApplet.cos (angle);
			dir.get(i).y = PApplet.sin (angle);

			velocity = dir.get(i); // kopiert direction
			velocity.mult (speed.get(i)); 
			loc.get(i).add (velocity);
			
			canvasLayer.beginDraw();
				canvasLayer.getPG().stroke(
						borderColor.get(i).getColor(),
						borderColor.get(i).getAlpha());
				canvasLayer.getPG().fill(
					color.get(i).getColor(),
					color.get(i).getAlpha());
				canvasLayer.getPG().
					ellipse (loc.get(i).x, loc.get(i).y, varCircleSize, varCircleSize);
			canvasLayer.endDraw();
		}
		
		if (!loc.isEmpty() && varCircleSize > 0 )
		{
			varCircleSize+=circleInc;
		}else
		{
			varCircleSize = 0;
		}
	}

	
	@Override
	public void setDrawingProperties() 
	{
		
	}
	
}
