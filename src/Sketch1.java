
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
public class Sketch1 extends DeniCanvas
{

    public void settings(){
        super.settings();
        strokeElement = new Stroke();
        strokeFollower = new StrokeFollower2(100,90);
        //this.addMouseListenerObject(strokeElement);
        this.addMouseListenerObject(strokeFollower);
    }

    @Override
    public void setup() 
    {
        
    }
    
    @Override
    public void draw()
    {
        //strokeElement.drawSequence();
        strokeFollower.draw();
    }
    
    
    Stroke strokeElement;
    StrokeFollower2 strokeFollower;
}
