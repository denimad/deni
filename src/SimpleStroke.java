
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
        strokeElement = new Stroke();
        this.addMouseListenerObject(strokeElement);
    }

    @Override
    public void setup() 
    {
        
    }
    
    @Override
    public void draw()
    {
        strokeElement.drawSequence();
   
    }
    
    
    Stroke strokeElement;
   
}
