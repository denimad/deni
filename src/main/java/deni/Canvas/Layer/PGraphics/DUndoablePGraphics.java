/*
 * deni 2017
 */
package main.java.deni.Canvas.Layer.PGraphics;

import java.util.LinkedList;
import java.util.Queue;
import processing.core.PApplet;
import processing.core.PGraphics;

/**
 *
 * @author daudirac
 */
public class DUndoablePGraphics extends DAbstractPGraphics
{
	public DUndoablePGraphics(int width, int height, int _maxUndos)
	{
		super(width, height);
		maxUndos = _maxUndos;
		pGraphicsQueue = new LinkedList<>();
	}

	public void startUndoStep()
	{
		
	}
	
	public void endUndoStep()
	{
		if (this.pGraphicsQueue.size()>maxUndos)
		{
			this.pGraphicsQueue.removeLast();
		}
		
		// create a new PGraphics that will store the current
		// state of the pGraphic.
		PGraphics newPGraphics = 
			parentCanvas.createGraphics(
				parentCanvas.width, 
				parentCanvas.height);

		newPGraphics.beginDraw();
		
			newPGraphics.loadPixels();
		PApplet.arrayCopy(this.pgraphics.pixels, newPGraphics.pixels);
		newPGraphics.updatePixels();
		
		newPGraphics.endDraw();
		
		
		/*newPGraphics.beginDraw();
		this.pgraphics.beginDraw();
			newPGraphics.image(
				this.pgraphics.get(),0,0);
		this.pgraphics.endDraw();
		newPGraphics.endDraw();*/
		
		this.pGraphicsQueue.addFirst(newPGraphics);
		this.pgraphics = this.pGraphicsQueue.peek();
	}
	
	public void undo()
	{
		if (this.pGraphicsQueue.size() > 1)
		{
			this.pGraphicsQueue.removeFirst();
			this.pgraphics = this.pGraphicsQueue.peek();
		}
		else
		{
			System.err.println("no more undos");
		}
	}
	
	
	private int maxUndos;
	LinkedList<PGraphics> pGraphicsQueue;

	PGraphics currentDrawingGraphics;
}
