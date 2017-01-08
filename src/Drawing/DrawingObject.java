/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drawing;

import processing.core.PGraphics;

/**
 *
 * @author daudirac
 */
public interface DrawingObject 
{
    public void update();    
    public void draw(PGraphics canvasLayer);
    public void setDrawingProperties();
}
