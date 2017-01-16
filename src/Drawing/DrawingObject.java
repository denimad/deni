/* 
 * deni 2017
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
