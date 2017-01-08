
import Canvas.DeniCanvas;
import Object.Drawing.DrawingObjectImpl;
import processing.core.PGraphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author daudirac
 */
public class Example1  extends DeniCanvas
{
	@Override
	public void settings()
	{
		canvasWidth = 680;
        canvasHeight = 412;
        super.settings();
        
    }
	
	private class customDrawingObj extends DrawingObjectImpl
	{
		@Override
		public void update() {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public void draw(PGraphics canvasLayer) {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public void setDrawingProperties() {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}
	}
}
