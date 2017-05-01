/*
 * deni 2017
 */
package main.java.deni.Controller.Color;

import controlP5.Bang;
import controlP5.CallbackEvent;
import controlP5.CallbackListener;
import controlP5.ControlEvent;
import controlP5.ControlP5;
import java.util.HashMap;
import java.util.Map;
import main.java.deni.Color.DColor;
import main.java.deni.Color.DColorPool;
import main.java.deni.Color.DSimpleColor;

/**
 *
 * @author daudirac
 */
public class DColorPoolEditor extends DAbstractColorEditor
{
	DColorPool colorPool;
	Map<Bang, DColor> colorBangMap;
	
	Bang newColorBang;
	public DColorPoolEditor(ControlP5 _cp5,
		String _colorVarName,
		DColorPool _colorPool) 
	{
		super(_cp5, _colorVarName);
		colorPool = _colorPool;
		colorBangMap = new HashMap<>();
	}

	@Override
	public void drawMe() 
	{
		int index = 0;
		int posX = this.colorEditorPosX();
		int posY = this.colorEditorPosY();
		
		// remove all previous bangs
		for (Bang bang : this.colorBangMap.keySet())
		{
			cp5.removeCallback(bang);
			cp5.remove(bang.getName());
		}
		if (newColorBang != null)
		{
			cp5.remove(newColorBang.getName());
		}
		
		this.colorBangMap.clear();
		
		// for each edition we regenerate all color bangs.
		for (DColor color : colorPool.getAllColors())
		{
				Bang bang = cp5.addBang(colorVarName + index)
					.setSize(DColorPoolEditor.COLORPOOL_BANG_WIDTH, 
					DColorPoolEditor.COLORPOOL_BANG_HEIGHT)
					.setPosition(posX,posY)
					.setColorForeground(color.getColor())
					.setColorActive(100)
					.setLabelVisible(false)
					.plugTo(this)
					.moveTo(this.parentGroup);
				this.colorBangMap.put(bang, color);
			
			posX += (DColorPoolEditor.COLORPOOL_BANG_WIDTH) + 1;
			index++;
		}
		
		//add the final new Color Bang
		newColorBang = cp5.addBang(colorVarName + "NewColorBang")
				.setSize(DColorPoolEditor.COLORPOOL_BANG_WIDTH, 
					DColorPoolEditor.COLORPOOL_BANG_HEIGHT)
				.setPosition(posX, posY)
				.plugTo(this)
				.moveTo(this.parentGroup);;
	}
	
	
	
	public void controlEvent(ControlEvent theEvent) 
	{
		if (theEvent.isController() && theEvent.getController() instanceof Bang)
		{
			if (theEvent.getController().equals(newColorBang))
			{
				this.newColor();
			}
			else if (this.colorBangMap.containsKey(
				(Bang) theEvent.getController()))
			{
				Bang b = (Bang) theEvent.getController(); 
				this.doEditColor(
					this.colorBangMap.get(b),
					b);
			}
		}
	}
	
	@Override
	public void doEditColor(DColor color, Bang bang) 
	{
		super.doEditColor(color, bang);
		
		cp5.addButton("delete")
			.setPosition(40,260)
			.setSize(200,19)
			.plugTo(this)
			.moveTo(this.parentGroup)	
			.addCallback(this.deleteColorListener(bang));
	}
	
	public CallbackListener deleteColorListener(Bang editedBang)
	{
		return new CallbackListener()
			{
				public void controlEvent(CallbackEvent event) 
				{
					if ( inColorEdition &&
						event.getAction() == ControlP5.ACTION_RELEASE) 
					{
						DColorPoolEditor.this.hideAllEditorComponents(editedBang);
						DColorPoolEditor.this.removeColor(
							DColorPoolEditor.this.colorBangMap.get(editedBang));
					}
				}
			};
	}
	
	protected void hideAllEditorComponents(Bang editedBang)
	{
		super.hideAllEditorComponents(editedBang);
		cp5.get("delete").hide();
	}
	
	protected void removeAllEditorComponents()
	{
		super.removeAllEditorComponents();
		cp5.remove("delete");
	}
	
	private void newColor()
	{
		colorPool.add(new DSimpleColor(100,255));
		this.drawMe();
	}

	private void removeColor(DColor color)
	{
		colorPool.remove(color);
		this.drawMe();
	}
	
	public static int COLORPOOL_BANG_WIDTH= 10;
	public static int COLORPOOL_BANG_HEIGHT = 20;
}
