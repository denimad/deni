/*
 * deni 2017
 */
package main.java.deni.Controller.Color;

import controlP5.Bang;
import controlP5.CallbackEvent;
import controlP5.CallbackListener;
import controlP5.ColorWheel;
import controlP5.ControlEvent;
import controlP5.ControlP5;
import main.java.deni.Color.DColor;

/**
 *
 * @author daudirac
 */
public class DSimpleColorEditor extends DAbstractColorEditor
{

	DColor color;
	Bang bang;
	
	public DSimpleColorEditor(
			ControlP5 _cp5,
			String _varName,
			DColor _color) 
	{
		super(_cp5, _varName);
		color = _color;
	}

	public void drawMe()
	{
		bang = cp5.addBang(colorVarName)
			.setSize(
				DAbstractColorEditor.DEFAULT_BANG_WIDTH, 
				DAbstractColorEditor.DEFAULT_BANG_HEIGHT)
			.setPosition(this.colorEditorPosX(), this.colorEditorPosY())
			.setColorForeground(color.getColor())
			.setColorActive(100)
			.plugTo(this)
			.moveTo(this.parentGroup);
	}
	
	public void controlEvent(ControlEvent theEvent) 
	{
		if (theEvent.isController())
		{
			if (theEvent.getController().equals(bang))
			{
				this.doEditColor(color, bang);
			}
		}
	}
}
