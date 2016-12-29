/*
 * deni 2016
 */
package Controller;

import Controller.Color.ColorControllerCreator;
import Controller.Color.ColorsController;
import controlP5.ControlP5;
import controlP5.Controller;
import controlP5.ControllerGroup;
import controlP5.Tab;
import java.util.List;
import java.util.Set;

/**
 * ControlFrameWriter class writes on a Control Frame 
 * interactive tools to modify the variables of a 
 * ControlFrameWriterOwner class.
 * For better organization, all elements must be contained
 * in a controlP5.ControllerGroup Object.
 * @author daudirac
 */
public class ControlFrameWriter {

    ControlFrameWriter(ControlFrame _controlFrame,
            ControlFrameWriterOwner _controlOwner) {
        controlFrame = _controlFrame;
        controlOwner = _controlOwner;
        this.cp5 = controlFrame.getControlP5();
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
        float maxVal) 
    {
       return this.addSlider(varName, 
            posX, 
            posY, 
            minVal, 
            maxVal, 
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
        String groupName) 
    {
         return cp5.addSlider(varName).setPosition(posX, posY)
                .setRange(minVal, maxVal)
                .plugTo(this.getVariableObject(), varName)
                .moveTo(this.getTab(groupName).getName());
    }
	
	public Controller addScrollableList(String methodName, 
        float posX, 
        float posY,
		List items,
        String groupName) 
	{
		return cp5.addScrollableList(methodName)
				.setPosition(posX, posY)
				.addItems(items)
				.setBarHeight(20)
				.setItemHeight(20)
				.plugTo(this.getVariableObject(), methodName)
				.moveTo(this.getTab(groupName).getName())	;
	}
    
	public void addColorController(String varName,
		int color)
	{
		this.getColorsController().addColor(varName, color,this.variableObject);
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
	
	
	
	private Object getVariableObject()
	{
		return (this.variableObject == null ? 
				this.controlOwner : this.variableObject);
	}
	
	public void setVariableObject(Object _variableObject)
	{
		this.variableObject = _variableObject;
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
				this.getVariableObject(),
				this.getTab(ControlWriterConstants.DEFAULT_COLOR_CONTROLLER_GROUP_NAME)
					.getName());
		}
		return this.colorsController;
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
	private Object variableObject;
	
	
    /**
     * This variables stores the controlP5 instance to write controllers. This
     * cP5 is always the one own by the controlFrame of this object.
     */
    private final ControlP5 cp5;
}
