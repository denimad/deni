/*
 * deni 2017
 */
package Canvas.Layer.PGraphics;

import Canvas.CanvasManager;
import Canvas.DeniCanvas;
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
	}


	public void endDraw()
	{
		this.pgraphics.endDraw();
		this.postEndDraw();
	}
	
	public PGraphics getPG()
	{
		return this.pgraphics;
	}


	public abstract void preBeginDraw();
	public abstract void postEndDraw();

	PGraphics pgraphics;
	DeniCanvas parentCanvas;
}
