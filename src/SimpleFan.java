
import Canvas.DeniCanvas;
import Pattern.FanPattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author daudirac
 */
public class SimpleFan extends DeniCanvas
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
      fan = new FanPattern();
      this.addMouseListenerObject(fan);
    }
    
    @Override
    public void draw()
    {
        super.draw();
        fan.draw(this.getCurrenDrawingLayer());
    }
   
    FanPattern fan;
    
}
