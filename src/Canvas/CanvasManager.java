/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Canvas;

import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;

/**
 *
 * @author daudirac
 */
public final class CanvasManager 
{
    public CanvasManager()
    {
        
        this.initListenerLists();
    }
    
    public PApplet getCanvas()
    {
        return canvas;
    }
    
    public void setCanvas(PApplet _canvas)
    {
        canvas = _canvas;
    }
    
    public void initListenerLists()
    {
        this.onMousePressedListeners = new ArrayList();
        this.onMouseDraggedListeners = new ArrayList();
        this.onMouseReleasedListeners = new ArrayList();
        this.onKeyPressedListeners = new ArrayList();
    }
    
    
    public void onMousePressed(int mouseX, int mouseY)
    {
        for (CanvasObject object : this.onMousePressedListeners )
        {
            object.onMousePressed(mouseX, mouseY);
        }
    }


    public void onMouseDragged(int mouseX, int mouseY)
    {
        for (CanvasObject object : this.onMouseDraggedListeners )
        {
            object.onMouseDragged(mouseX, mouseY);
        }
    }
    
    public void onMouseReleased(int mouseX, int mouseY)
    {
        for (CanvasObject object : this.onMouseReleasedListeners )
        {
            object.onMouseReleased(mouseX, mouseY);
        }
    }
    
    
    
    public void addOnMousePressedListener(CanvasObject canvasObject)
    {
        this.onMousePressedListeners.add(canvasObject);
    }
    
    public void addOnMouseDraggedListener(CanvasObject canvasObject)
    {
        this.onMouseDraggedListeners.add(canvasObject);
    }
    
    public void addOnMouseReleasedListener(CanvasObject canvasObject)
    {
        this.onMouseReleasedListeners.add(canvasObject);
    }
    
    
    public void addMouseListeners(CanvasObject canvasObject)
    {
        addOnMousePressedListener(canvasObject);
        addOnMouseDraggedListener(canvasObject);
        addOnMouseReleasedListener(canvasObject);
    }
    
    public static CanvasManager getInstance() {
      return instance;
   }
    
    private static CanvasManager instance = new CanvasManager();
    
    public PApplet canvas;
    public List<CanvasObject> onMousePressedListeners;
    public List<CanvasObject> onMouseDraggedListeners;
    public List<CanvasObject> onMouseReleasedListeners;
    
    public List<CanvasObject> onKeyPressedListeners;
}
