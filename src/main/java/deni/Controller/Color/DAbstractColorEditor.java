/*
 * deni 2017
 */
package main.java.deni.Controller.Color;

import controlP5.Bang;
import controlP5.Button;
import controlP5.CallbackEvent;
import controlP5.CallbackListener;
import controlP5.ColorWheel;
import controlP5.ControlP5;
import controlP5.Slider;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.deni.Color.DColor;
import main.java.deni.Color.DColorHelper;

/**
 * @author daudirac
 */
public abstract class DAbstractColorEditor implements DColorEditor{

	public DAbstractColorEditor(ControlP5 _cp5, String _colorVarName)
	{
		position = new int[2];
		cp5 = _cp5;
		colorVarName = _colorVarName;
		
		inColorEdition = false;
	}
	
	public DAbstractColorEditor setPosition(int x, int y)
	{
		position[0] = x;
		position[1] = y;
		return this;
	}
	
	public DAbstractColorEditor setGroupName(String groupName)
	{
		this.parentGroup = groupName;
		return this;
	}
	
	public String getGroupName()
	{
		return this.parentGroup;
	}
	
	
	public int colorEditorPosX()
	{
		return position[0];
	}
	
	public int colorEditorPosY()
	{
		return position[1];
	}
	
	
	/**
	 * Runs the edition of the given color 
	 * that is related to the given bang.
	 * @param color
	 * @param bang 
	 */
	@Override
	public void doEditColor(DColor color, Bang bang) 
	{
		// ensure to remove previous created editors
		this.removeAllEditorComponents();
		
		inColorEdition = true;
		
		colorWheelEditor = cp5.addColorWheel(
				DAbstractColorEditor.COLOR_WHEEL_COMPONENT_NAME)
			.setPosition(40,40)
			.setRGB(color.getColor())
			.plugTo(color)
			.moveTo(this.parentGroup)
			.addCallback(this.getHideEditionListener(bang));
		
		cp5.addSlider(
				DAbstractColorEditor.ALPHA_SLIDER_COMPONENT_NAME)
			.setSize(20,200)
			.setRange(0, 255)
			.setValue(color.getAlpha())
			.setPosition(240, 40)
			.setLabel("Alpha")
			.plugTo(color)
			.moveTo(this.parentGroup)
			.addCallback(this.getHideEditionListener(bang));
		
		cp5.addButton(
				DAbstractColorEditor.COPY_BUTTON_COMPONENT_NAME)
			.setPosition(280, 40)
			.plugTo(this)
			.moveTo(this.parentGroup)
				.addCallback(this.copyButtonListener(bang));
	}


	public CallbackListener getHideEditionListener(Bang editedBang)
	{
		
			hideEdition =  new CallbackListener()
			{
				public void controlEvent(CallbackEvent event) 
				{
					if ( inColorEdition &&
						event.getAction() == ControlP5.ACTION_RELEASE) 
					{
						DAbstractColorEditor.this.hideAllEditorComponents(editedBang);
						inColorEdition = false;
					}
				}
			};
		
		return hideEdition;
	}


	public CallbackListener copyButtonListener(Bang editedBang)
	{
		return new CallbackListener()
			{
				public void controlEvent(CallbackEvent event) 
				{
					if (event.getController() instanceof Button && (inColorEdition &&
							event.getAction() == ControlP5.ACTION_RELEASE))
					{
						try 
						{
							String hexString = (String) Toolkit.getDefaultToolkit() 
									.getSystemClipboard().getData(DataFlavor.stringFlavor);
							int color = DColorHelper.unHexColor(hexString);
							colorWheelEditor.setRGB(color);
							DAbstractColorEditor.this.hideAllEditorComponents(editedBang);
							
						} catch (UnsupportedFlavorException | IOException ex) {
							Logger.getLogger(DColorPoolEditor.class.getName()).log(Level.SEVERE,
									"Error on getting clipboard", ex);
						} catch (NumberFormatException ex)
						{
							Logger.getLogger(DColorPoolEditor.class.getName()).log(Level.SEVERE,
									"the copied text cannot be converted to color",
									ex);
						}
					}
					
					
				}
			};
	}
	
	
	protected void hideAllEditorComponents(Bang editedBang)
	{
		int newColor = 
			DAbstractColorEditor.this.colorWheelEditor.getRGB();
		editedBang.setColorForeground(newColor);

		cp5.get(DAbstractColorEditor.COLOR_WHEEL_COMPONENT_NAME).hide();
		cp5.get(DAbstractColorEditor.ALPHA_SLIDER_COMPONENT_NAME).hide();
		cp5.get(DAbstractColorEditor.COPY_BUTTON_COMPONENT_NAME).hide();
	}

	protected void removeAllEditorComponents()
	{
		cp5.remove(DAbstractColorEditor.COLOR_WHEEL_COMPONENT_NAME);
		cp5.remove(DAbstractColorEditor.ALPHA_SLIDER_COMPONENT_NAME);
		cp5.remove(DAbstractColorEditor.COPY_BUTTON_COMPONENT_NAME);
	}
	
	/**
	 * ControlP5 object owner of this editor.
	 */
	ControlP5 cp5;

	CallbackListener hideEdition;
	private final int[] position;
	String colorVarName;
	String parentGroup;
	
	ColorWheel colorWheelEditor;
	Slider alphaEditor;
	
	boolean inColorEdition;
	
	public static int DEFAULT_BANG_WIDTH= 20;
	public static int DEFAULT_BANG_HEIGHT= 20;
	
	public final static String COLOR_WHEEL_COMPONENT_NAME = "color";
	public final static String ALPHA_SLIDER_COMPONENT_NAME = "alpha";
	public final static String COPY_BUTTON_COMPONENT_NAME = "copy";

}
