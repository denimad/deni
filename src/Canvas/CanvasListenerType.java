/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Canvas;

import Canvas.Tool.ToolCanvasListenerManager;

/**
 *
 * @author daudirac
 */
public enum CanvasListenerType
{
	ToolListener("toolListener", 
		new ToolCanvasListenerManager(
			CanvasManager.getInstance().getCanvas()));
	
	
	CanvasListenerType(String _name,
		CanvasListenerManager _canvasListener)
	{
		name = _name;
		canvasListener = _canvasListener;
	}
	
	public String getName()
	{
		return name;
	}
	public CanvasListenerManager getCanvasListener()
	{
		return this.canvasListener;
	}
	
	private final String name;
	private final CanvasListenerManager canvasListener;
}
