/* 
 * deni 2017
 */
package main.java.deni.Drawing;

import main.java.deni.Canvas.DCanvasManager;
import main.java.deni.Canvas.DeniCanvas;
import processing.core.PApplet;
import main.java.deni.Canvas.Listener.CanvasInputAwareObject;
import processing.event.MouseEvent;
import main.java.deni.Drawing.DDrawingObject;

/**
 *
 * @author daudirac
 */
public abstract class DDrawingObjectImpl implements CanvasInputAwareObject, DDrawingObject 
{   
    public DDrawingObjectImpl()
    {
        super();
        canvas = DCanvasManager.getInstance().getCanvas();
    }
    
    public void drawSequence()
    {
        update();
        setDrawingProperties();
        draw(canvas.getCurrenDrawingLayer());
    }
    
    public DeniCanvas canvas;

    @Override
    public  void onMousePressed(int mouseX, int mouseY){}

    @Override
    public  void onMouseDragged(int mouseX, int mouseY){}

    @Override
    public  void onMouseReleased(int mouseX, int mouseY){}

    @Override
    public void onMouseClicked(int mouseX, int mouseY){}
    
    @Override
    public  void onKeyPressed(char key){}
	
	@Override
	public  void onKeyReleased(){}
	
	@Override
	public void onMouseWheel(MouseEvent e){}

    
}


