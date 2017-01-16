/* 
 * deni 2017
 */
package Drawing;

import Drawing.DrawingObject;
import Canvas.CanvasManager;
import Canvas.CanvasObject;
import Canvas.DeniCanvas;
import processing.core.PApplet;

/**
 *
 * @author daudirac
 */
public abstract class DrawingObjectImpl implements CanvasObject, DrawingObject 
{   
    public DrawingObjectImpl()
    {
        super();
        canvas = CanvasManager.getInstance().getCanvas();
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

    
}


