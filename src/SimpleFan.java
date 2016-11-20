
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
        super.settings();
        fan = new FanPattern();
        this.addMouseListenerObject(fan);
    }

    @Override
    public void setup() 
    {
        
    }
    
    @Override
    public void draw()
    {
        fan.draw();
    }
    
    FanPattern fan;
    
}
