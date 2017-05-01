/*
 * deni 2017
 */
package main.java.deni.Canvas.Layer.PGraphics;

/**
 *
 * @author daudirac
 */
public enum DPGraphicType 
{
	Simple,
	Undoable;
	
	DAbstractPGraphics pg;
	
	public DAbstractPGraphics createNewPGraphics(
		int width, int height)
	{
		DAbstractPGraphics newPG ;
		switch(this)
		{
			case Simple: newPG = new DSimplePGraphics(width, height);
				break;
				
			case Undoable : newPG = new DUndoablePGraphics(width, height, 50);
				break;
				
			default:  newPG = new DSimplePGraphics(width, height);
		}
		
		return newPG;
	}
}
