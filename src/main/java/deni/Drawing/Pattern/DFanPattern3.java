/*
 * deni 2017
 */
package main.java.deni.Drawing.Pattern;

import main.java.deni.Pencil.DStroke;
import java.util.ArrayList;
import java.util.List;
import processing.core.PVector;

/**
 *
 * @author daudirac
 */
public class DFanPattern3 extends DFanPattern
{
	DStroke stroke2;
	
	
	enum drawingState3
    {
        strokeDrawing,
		stroke2Drawing,
        pointDrawing;
    }
	
	drawingState3 currentState3;
	
	public DFanPattern3()
	{
		super();
		stroke2 = new DStroke();
	}
	
	@Override
    public void onMouseReleased(int mouseX, int mouseY) {
		switch(currentState3)
		{
			case strokeDrawing: currentState3 = drawingState3.stroke2Drawing; break;
			
			case stroke2Drawing: currentState3 = drawingState3.pointDrawing; break;
			
			case pointDrawing: currentState3 = drawingState3.strokeDrawing; break;
		}
    }
	
	 @Override
    public void onMouseDragged(int mouseX, int mouseY) 
	{
		switch(currentState3)
		{
			case strokeDrawing: stroke.onMouseDragged(mouseX, mouseY); break;
			
			case stroke2Drawing: stroke2.onMouseDragged(mouseX, mouseY); break;
			
			case pointDrawing: currentState3 = drawingState3.strokeDrawing; break;
		}
    }
	
	private void createMovingObjects(List<PVector> originPoints, List<PVector> targetPoints)
    {
		movingObjects = new ArrayList<>(targetPoints.size());
		
	}
}
