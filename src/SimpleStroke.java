
import Canvas.DeniCanvas;
import Pencil.Stroke;
import Pencil.StrokeFollower2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author daudirac
 */
public class SimpleStroke extends DeniCanvas
{

    @Override
    public void settings(){
        super.settings();
    }

    @Override
    public void setup() 
    {
        super.setup();
        
        strokeElement = new Stroke();
        
    }
    
    @Override
    public void draw()
    {
        strokeElement.drawSequence();
   
    }
    
    
    Stroke strokeElement;
   
}
