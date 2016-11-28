/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
