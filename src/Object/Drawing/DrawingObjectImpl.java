/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object.Drawing;

import Canvas.CanvasManager;
import Canvas.CanvasObject;
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
        draw();
    }
    
    public PApplet canvas;

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


