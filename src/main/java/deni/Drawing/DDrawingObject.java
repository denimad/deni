/* 
 * deni 2017
 */
package main.java.deni.Drawing;

import main.java.deni.Canvas.Layer.PGraphics.DAbstractPGraphics;
import processing.core.PGraphics;

/**
 *
 * @author daudirac
 */
public interface DDrawingObject 
{
    public void update();    
    public void draw(DAbstractPGraphics canvasLayer);
    public void setDrawingProperties();
}
