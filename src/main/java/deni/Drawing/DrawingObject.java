/* 
 * deni 2017
 */
package main.java.deni.Drawing;

import main.java.deni.Canvas.Layer.PGraphics.AbstractPGraphics;
import processing.core.PGraphics;

/**
 *
 * @author daudirac
 */
public interface DrawingObject 
{
    public void update();    
    public void draw(AbstractPGraphics canvasLayer);
    public void setDrawingProperties();
}
