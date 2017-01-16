/* 
 * deni 2017
 */
package Tool;

import controlP5.ControlP5;

/**
 *
 * @author daudirac
 */
public interface ToolObject 
{
    public String getName();
    public void setControllers(ControlP5 cp5);
    public void removeControllers(ControlP5 cp5);
}
