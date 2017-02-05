/*
 * deni 2017
 */
package Canvas.Layer.PGraphics;

/**
 *
 * @author daudirac
 */
public enum PGraphicType 
{
	Simple,
	Undoable;
	
	AbstractPGraphics pg;
	
	public AbstractPGraphics createNewPGraphics(
		int width, int height)
	{
		AbstractPGraphics newPG ;
		switch(this)
		{
			case Simple: newPG = new SimplePGraphics(width, height);
				break;
				
			case Undoable : newPG = new UndoablePGraphics(width, height, 50);
				break;
				
			default:  newPG = new SimplePGraphics(width, height);
		}
		
		return newPG;
	}
}
