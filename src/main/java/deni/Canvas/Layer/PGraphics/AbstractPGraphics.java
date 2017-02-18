/*
 * deni 2017
 */
package main.java.deni.Canvas.Layer.PGraphics;

import main.java.deni.Canvas.CanvasManager;
import main.java.deni.Canvas.DeniCanvas;
import main.java.deni.Canvas.Layer.CanvasLayersManager;
import processing.core.PGraphics;

/**
 *
 * @author daudirac
 */
public abstract class AbstractPGraphics 
{
	public AbstractPGraphics(int width, int height)
	{
		parentCanvas = CanvasManager.getInstance().getCanvas();
		pgraphics = parentCanvas.createGraphics(width, height);
	}

	public void beginDraw()
	{
		this.preBeginDraw();
		this.pgraphics.beginDraw();
		this.postBeginDraw();
	}


	public void endDraw()
	{
		this.preEndDraw();
		this.pgraphics.endDraw();
		this.postEndDraw();
	}
	
	public PGraphics getPG()
	{
		return this.pgraphics;
	}


	public void postBeginDraw()
	{
	}
	
	public void preEndDraw()
	{
	}
	
	public void preBeginDraw()
	{}
	public void postEndDraw()
	{}

	PGraphics pgraphics;
	DeniCanvas parentCanvas;
}
