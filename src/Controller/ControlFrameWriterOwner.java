/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 * This interface describes a class that owns a control frame writer.
 * Generally this classes will be tool drawing objects in the canvas
 * that use a graphic controller to control their values.
 * 
 * How to use:
 * The control frame will generate its control frame writer and
 * return it to the drawing object.
 */
public interface ControlFrameWriterOwner 
{
    public void setControlFrameWriter(ControlFrameWriter cw);
    public ControlFrameWriter getControlFrameWriter();
    public void setControls();
}
