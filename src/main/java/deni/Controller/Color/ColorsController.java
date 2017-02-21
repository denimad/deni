/* 
 * deni 2017
 */
package main.java.deni.Controller.Color;

import main.java.deni.Color.ColorHelper;
import controlP5.Bang;
import controlP5.CallbackEvent;
import controlP5.CallbackListener;
import controlP5.ColorWheel;
import controlP5.ControlEvent;
import controlP5.ControlP5;
import java.util.HashMap;
import java.util.Map;

/**
 * This class defines a colors controllers,
 * it implements a controller that can handle multiple colors.
 * it displays them in a matrix and uses a color wheel or color picker
 * to modify the colors.
 */
public class ColorsController 
{
	/**
	 * Map of color variable names and colors handled by this object
	 */
	Map<String, Integer> colors;
	Map<String, Float> alphas;
	Map<String, Bang> colorBangs;
	Map<Bang,String> BangToVarNameMap;
	Map<Bang,Object> BangToVarObjectMap;
	/**
	 * cp5 object that invokes the controllers.
	 */
	ControlP5 cp5;
	
	/**
	 * variable object associated.
	 */
	Object variableObject;
	
	/**
	 * name of the group name where all the 
	 * color controls of this class will be added.
	 */
	String groupName;
	
	boolean inColorEdition;
	
	int[] bangPosition;
	
	Bang currentModifiedBang;
	
	private static int DEFAULT_BANG_WIDTH= 20;
	private static int DEFAULT_BANG_HEIGHT= 20;
	
	private static int DEFAULT_BANG_INIT_POSX= 20;
	private static int DEFAULT_BANG_INIT_POSY= 30;
	
	PickerType type;
	
	enum PickerType
	{
		colorWheel,
		colorPicker;
	}
	
	public ColorsController(ControlP5 cp5, 
		Object variableObject, 
		String groupName,
		PickerType type)
	{
		this.colors = new HashMap<>();
		this.alphas = new HashMap<>();
		this.colorBangs = new HashMap<>();
		this.BangToVarNameMap = new HashMap<>();
		this.BangToVarObjectMap = new HashMap<>();
		
		this.cp5 = cp5;
		this.variableObject = variableObject;
		this.groupName = groupName;
		
		this.type = type;
		
		bangPosition= new int[]{
			ColorsController.DEFAULT_BANG_INIT_POSX,
			ColorsController.DEFAULT_BANG_INIT_POSY};
		
	}
	
	public ColorsController(ControlP5 cp5, 
		Object variableObject, 
		String groupName)
	{
		// by default the color picker GUI is going to be the
		// colorWheel 
		this(cp5, variableObject, groupName, PickerType.colorWheel);
	}
	
	public void addColor(String varName, 
		Integer color, 
		Float alpha,
		Object variableObject)
	{
		this.colors.put(varName, color);
		this.alphas.put(varName,alpha);
		
		//each color variable has a bang
		Bang newBang = this.cp5.addBang(varName + "bang")
			.setSize(ColorsController.DEFAULT_BANG_WIDTH, 
					ColorsController.DEFAULT_BANG_HEIGHT)
			.setPosition(bangPosition[0], bangPosition[1])
			.setId(0)
			.setLabel(varName)
			.setColorForeground(color)
			.setColorActive(100)
			.plugTo(this).moveTo(this.groupName);
		
		this.cp5.addSlider(varName+"alpha")
				.plugTo(variableObject,varName+"alpha")
				.setRange(0, 255)
				.setValue(alpha)
				.setPosition(bangPosition[0]+40, bangPosition[1])
				.setLabel("alpha")
				.moveTo(groupName)
				.addCallback(
					new CallbackListener()
					{
						public void controlEvent(CallbackEvent event) 
						{
							
							String bangName =
								event.getController().getName()
									.substring(0,event.getController().getName().length() - 5);
							int bangColor = ColorsController.this.colors.get(bangName);
							float alfa = event.getController().getValue();
							
							ColorsController.this.setBangColors(
								ColorsController.this.colorBangs.get(bangName), 
								ColorHelper.getInstance().getColorWithAlpha(bangColor, 
									alfa));
						}
					});
		
		this.setBangColors(newBang, color);
		
		this.colorBangs.put(varName,newBang);
		this.BangToVarNameMap.put(newBang, varName);
		this.BangToVarObjectMap.put(newBang, variableObject);
		
		//post actions
		this.calculateNextBangPosition();
	}
	
	private void setBangColors(Bang bang, 
		int color)
	{
		float alfa = ColorHelper.getInstance().getAlpha(color)-50;
		int activeColor = ColorHelper.getInstance().getColorWithAlpha(color, alfa);
		
		bang.setColorForeground(color);
		bang.setColorActive(activeColor);
		
		this.colors.put(bang.getLabel(), color);
	}
	
	private void calculateNextBangPosition()
	{
		bangPosition[1]+=40; 
	}
	
	/*public void bangClick() 
	{
		System.out.println("im here");
	}*/
	
	public void controlEvent(ControlEvent theEvent) 
	{
	
		//show the color wheel or color picker.
		if (this.type.equals(PickerType.colorWheel))
		{
			this.currentModifiedBang = (Bang) theEvent.getController();
			
			String bangVarName = this.BangToVarNameMap.get(this.currentModifiedBang);
			Object variableObject = this.BangToVarObjectMap.get(this.currentModifiedBang);
			int color = this.colors.get(bangVarName);
			inColorEdition = true;
			
			
			this.cp5.addColorWheel(bangVarName)
				.plugTo(variableObject)
				.setPosition(40,40)
				.setRGB(color)
				.moveTo(groupName)
				.addCallback(new CallbackListener()
				{
					public void controlEvent(CallbackEvent event) 
					{
						int newColor = ((ColorWheel) event.getController()).getRGB();
						if (event.getAction() == ControlP5.ACTION_RELEASE) 
						{
							ColorsController.this.currentModifiedBang.setColorForeground(newColor);
							ColorsController.this.setBangColors(currentModifiedBang, newColor);
							ColorsController.this.cp5.remove(event.getController().getName());
							
							inColorEdition = false;
						}
					}
				});
		} 
	}
	
	
	public void resetController()
	{
		bangPosition= new int[]{
			ColorsController.DEFAULT_BANG_INIT_POSX,
			ColorsController.DEFAULT_BANG_INIT_POSY};
	}
}
