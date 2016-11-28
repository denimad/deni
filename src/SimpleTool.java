
import Canvas.DeniCanvas;
import Controller.ControlFrameWriter;
import Tool.ToolDrawingObject;
import processing.core.PGraphics;

/*
 * This is a simple example to show how to create a simple tool
 * and set a interactive controller in the control frame 
 * by overriding the setControls() method.
 */

/**
 *
 * @author daudirac
 */
public class SimpleTool extends DeniCanvas
{
    public void settings(){
        canvasWidth = 540;
        canvasHeight = 540;
        super.settings();
        
    }

    @Override
    public void setup() 
    {
      super.setup();
      theSimpleTool = new SimpleTooll();
      
      ControlFrameWriter cwf= this.getNewControlFrameWriter(theSimpleTool);
      theSimpleTool.setControlFrameWriter(cwf);
    }
    
    @Override
    public void draw()
    {
        super.draw();
        theSimpleTool.draw(this.getCurrenDrawingLayer());   
    }
    
    SimpleTooll theSimpleTool;
   
    class SimpleTooll extends ToolDrawingObject
    {
        public SimpleTooll() {
            
        }
        
        float slider = 50;
        
        @Override
        public void setControls() 
        {
           this.controlFrameWriter.addSlider("slider", 20, 20, 0, 100);
        }
        
        @Override
        public void draw(PGraphics canvasLayer)
        {
            canvasLayer.beginDraw();
            canvasLayer.ellipse(
                canvasLayer.width/2,
                canvasLayer.height/2, 
                slider, 
                slider);
            canvasLayer.endDraw();
        }
    }
    
}
