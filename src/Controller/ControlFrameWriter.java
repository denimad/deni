/* 
 * deni 2017
 */
package Controller;

import Controller.Color.ColorsController;
import controlP5.ControlP5;
import controlP5.Controller;
import controlP5.ControllerGroup;
import controlP5.ControllerInterface;
import controlP5.Tab;
import java.util.List;

/**
 * ControlFrameWriter class writes on a Control Frame 
 * interactive tools to modify the variables of a 
 * ControlFrameWriterOwner class.
 * For better organization, all elements must be contained
 * in a controlP5.ControllerGroup Object.
 * @author daudirac
 */
public class ControlFrameWriter implements ControlOwner{

    ControlFrameWriter(ControlFrame _controlFrame,
            ControlFrameWriterOwner _controlOwner) {
        controlFrame = _controlFrame;
        controlOwner = _controlOwner;
        this.cp5 = controlFrame.getNewControlP5(controlOwner);
    }
    

	public void resetController()
	{
		this.getColorsController().resetController();
	}
	
    /**
     * add a slider to the current active group.
     * @param varName
     * @param posX
     * @param posY
     * @param minVal
     * @param maxVal
     * @return 
     */
    public Controller addSlider(String varName, 
        float posX, 
        float posY, 
        float minVal, 
        float maxVal,
		float value) 
    {
       return this.addSlider(varName, 
            posX, 
            posY, 
            minVal, 
            maxVal,
			value,
            this.getCurrentControllerGroup().getName());
        
    }
    
    /**
     * add slider to the given group name.
     * @param varName
     * @param posX
     * @param posY
     * @param minVal
     * @param maxVal
     * @param groupName
     * @return 
     */
    public Controller addSlider(String varName, 
        float posX, 
        float posY, 
        float minVal, 
        float maxVal,
		float value,
        String groupName) 
    {
         return cp5.addSlider(varName).setPosition(posX, posY)
                .setRange(minVal, maxVal)
				.setValue(value)
                .plugTo(this.getControlledObject(), varName)
                .moveTo(this.getTab(groupName).getName());
    }
	public Controller addToogle(
			String label,
        float posX, 
        float posY,
		int w,
		int h,
		String groupName)
	{
		return cp5.addToggle(label)
				.setLabel(label)
				.setPosition(posX, posY)
				.setSize(w, h)
				.plugTo(this.getControlledObject())
				.moveTo(this.getTab(groupName).getName());
	}
	
	
	public Controller addScrollableList(String theName,
		String label,
        float posX, 
        float posY,
		List items,
        String groupName) 
	{
		return cp5.addScrollableList(theName)
				.setPosition(posX, posY)
				.setLabel(label)
				.addItems(items)
				.setBarHeight(20)
				.setItemHeight(20)
				.plugTo(this.getControlledObject())
				.moveTo(this.getTab(groupName).getName());
	}
    
	public Controller addRange(String theName, String label,float posX, float posY, 
		float minVal, float maxVal, String groupName)
	{
		return cp5.addRange(theName)
				.setBroadcast(false) 
				.setLabel(label)
				.setPosition(posX,posY)
				.setRange(minVal, maxVal)
				.setBroadcast(true)
				.plugTo(this.getControlledObject())
				.moveTo(this.getTab(groupName).getName());
	}
	
	public void addColorController(String varName,
		int color,
		float alpha)
	{
		this.getColorsController().addColor(varName, 
			color,
			alpha,
			this.getControlledObject());
	}

    public void newGroup(String label, int width, int height) {
        currentControllerGroup = this.cp5.addGroup("myGroup" + groupCounter++)
                .setBackgroundColor(controlFrame.getColor(ControlWriterConstants.DEFAULT_GROUP_COLOR))
                .setWidth(width)
                .setBackgroundHeight(height).setLabel(label);
    }
    
    public Tab newTab(String label) {
        Tab tab = this.cp5.addTab("myTab" + groupCounter++)
                .setLabel(label)
                .setId(groupCounter)
                .setColorBackground(controlFrame.getColor(ControlWriterConstants.DEFAULT_GROUP_COLOR))
                .setColorActive(controlFrame.getColor(ControlWriterConstants.DEFAULT_TAB_ACTIVE_COLOR));
		currentControllerGroup = tab;
		return tab;
    }
    

	/**
	 * return the requested tab. if it doesn't exists it loosely creates it. 
	 */
	public Tab getTab(String name)
	{
		Tab tab = this.cp5.getTab(name);
		if (tab == null)
		{
			tab = this.newTab(name);
		}
		
		return tab;
	}
	
	public Controller getController(String name)
	{
		
		return this.cp5.getController(name);
	}
	
	public <C extends Object> C getController(Class<C> c, String name)
	{
		return this.cp5.get(c, name);
	}
	
    private ControllerGroup getCurrentControllerGroup()
    {
        if (currentControllerGroup == null)
        {
            currentControllerGroup = this.cp5.getTab("default");
        }
        
        return currentControllerGroup;
    }
	
	
	
	private Object getControlledObject()
	{
		return (this.controlledObject == null ? 
				this.controlOwner : this.controlledObject);
	}
	
	public void setControlledObject(Object _variableObject)
	{
		this.controlledObject = _variableObject;
	}
	
	/**
	 * method creation of the color controller.
	 * @return 
	 */
	private ColorsController getColorsController()
	{
		if (this.colorsController == null)
		{
			this.colorsController = new ColorsController(this.cp5, 
				this.getControlledObject(),
				this.getTab(ControlWriterConstants.DEFAULT_COLOR_CONTROLLER_GROUP_NAME)
					.getName());
		}
		return this.colorsController;
	}	
	
	public void hideControlls()
	{
		this.cp5.hide();
	}
	
	public void showControlls()
	{
		this.cp5.show();
	}
	
	public void toggleControllsVisibility()
	{
		if (cp5.isVisible())
		{
		  this.hideControlls();
		}
		else
		{
		  this.showControlls();
		}
	}
	
	public void removeAllControlls()
	{
		List<ControllerInterface<?>> controllerList =  this.cp5.getList();
		for (ControllerInterface<?> control: controllerList)
		{
			this.cp5.remove(control.getName());
		}
	}
	
	@Override
	public ControlP5 getControlP5(Object obj) 
	{
		return this.cp5;
	}

    ControllerGroup currentControllerGroup;

    int groupCounter = 0;
    
    /**
     * The control frame to write on.
     */
    private final ControlFrame controlFrame;

    /**
     * The class this controller writer serves.
     */
    private final ControlFrameWriterOwner controlOwner;
    
	/**
	 * color variables are handled by a Colors Controller.
	 * ColorsController writes the controls for colors in
	 * the "color" tab.
	 */
	private ColorsController colorsController;
	
	/**
	 * This object whose variables are modified.
	 * serves when the controlOwner is delegates a drawing object.
	 */
	private Object controlledObject;
	
	
    /**
     * This variables stores the controlP5 instance to write controllers. This
     * cP5 is always the one own by the controlFrame of this object.
     */
    private final ControlP5 cp5;

	
}
