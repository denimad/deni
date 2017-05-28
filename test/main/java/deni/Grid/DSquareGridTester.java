/*
 * deni 2017
 */
package main.java.deni.Grid;

import processing.core.PApplet;

/**
 *
 * @author daudirac
 */
public class DSquareGridTester extends PApplet
{
	DSquareGrid squareGrid;
	int[] position;
	
	public static void main(String args[]) {
		PApplet.main(new String[] { DSquareGridTester.class.getName() });
	}
	
	public void settings()
	{
	 	size(300,300);
		
	}
	@Override
	public void setup()
	{
		squareGrid = new DSquareGrid(300,300,10,10,
			DSquareGrid.DSquareGridFillingMode.HORIZONTAL_FILLING, 
			DSquareGrid.DCorner.TOP_RIGHT_CORNER);
	}
	
	public void draw()
	{
		position = squareGrid.getNextPosition();
		
		ellipse(
				position[0],
				position[1],
				3,3
		);
	}
}
