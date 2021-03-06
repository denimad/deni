/* 
 * deni 2017
 */
package main.java.deni.Controller;

import main.java.deni.Canvas.DCanvasManager;
import main.java.deni.Controller.Color.DColorsController;
import controlP5.ControlFont;
import controlP5.ControlP5;
import controlP5.Controller;
import controlP5.ControllerGroup;
import controlP5.ControllerInterface;
import controlP5.Tab;
import java.util.Arrays;
import java.util.List;
import main.java.deni.Color.DColor;
import main.java.deni.Color.DColorPool;
import main.java.deni.Controller.Color.DColorsController2;
import main.java.deni.Grid.DSquareGrid;

/**
 * ControlFrameWriter class writes on a Control Frame 
 * interactive tools to modify the variables of a 
 * ControlFrameWriterOwner class.
 * For better organization, all elements must be contained
 * in a controlP5.ControllerGroup Object.
 * @author daudirac
 */
public class DControlFrameWriter implements DControlOwner{

    DControlFrameWriter(DControlFrame _controlFrame,
            DControlFrameWriterOwner _controlOwner) {
        controlFrame = _controlFrame;
        controlOwner = _controlOwner;
        this.cp5 = controlFrame.getNewControlP5(controlOwner);
		

		
		//init tab names with the defaul tab names
		this.tabNames = Arrays.asList(
				DControlWriterConstants.DEFAULT_CONTROLLER_GROUP_NAME,
				DControlWriterConstants.DEFAULT_COLOR_CONTROLLER_GROUP_NAME);
		
		this.initLayoutVariables();
    }
    
	private void initLayoutVariables()
	{
		automaticLayout = false;
		
		ownerFrameWidth = controlFrame.w;
		ownerFrameHeight = controlFrame.h;
		
		buttonsPositionsquareGrid = new DSquareGrid(ownerFrameWidth, 
			ownerFrameHeight,
			ownerFrameWidth/4,
			ownerFrameHeight/5,
			DSquareGrid.DSquareGridFillingMode.VERTICAL_FILLING);
		buttonsPositionsquareGrid.setBorder(15);
		colorsPositionsquareGrid = new DSquareGrid(ownerFrameWidth, 
			ownerFrameHeight,
			ownerFrameWidth/4,
			ownerFrameHeight/5,
			DSquareGrid.DSquareGridFillingMode.VERTICAL_FILLING);
		colorsPositionsquareGrid.setBorder(15);
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
			String id,
			String label,
        float posX, 
        float posY,
		int w,
		int h,
		String groupName)
	{
		return cp5.addToggle(id)
				.setLabel(label)
				.setPosition(posX, posY)
				.setSize(w, h)
				.plugTo(this.getControlledObject())
				.moveTo(this.getTab(groupName).getName());
	}
	
	/**
	 * add slider to the given group name. 
	 * it positions the toggle automatically.
	 * @param label
	 * @param w
	 * @param h
	 * @param groupName
	 * @return 
	 */
	public Controller addToogle(
		String id,
		String label,
		int w,
		int h,
		String groupName)
	{
		int pos[] =  this.buttonsPositionsquareGrid.getNextPosition();
		
		return this.addToogle(id,label, 
			pos[0], pos[1],
			w, h,
			groupName);
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
	
	public Controller addTextLabel(String theName,
			String text, 
			float posX, float posY,
			int color,
			String groupName)
	{
		return	cp5.addTextlabel(theName)
                    .setText(text)
                    .setPosition(posX,posY)
                    .setColorValue(color)
					.moveTo(groupName);
	}
	
	public void addColorController(String varName,
		int color,
		float alpha)
	{
		/*this.getColorsController().addColor(varName, 
			color,
			alpha,
			this.getControlledObject());*/
	}
	
	public void addSimpleColorController(String varName,
		DColor color)
	{
		this.getColorsController().addSimpleColorEditor(
			varName, 
			color,
			this.colorsPositionsquareGrid.getNextPosition());
	}
	
	
	public void addColorPoolController(String varName,
			DColorPool colorPool)
	{
		this.getColorsController().addColorPoolEditor(
			varName, 
			colorPool,
			this.colorsPositionsquareGrid.getNextPosition());
	}

    public void newGroup(String label, int width, int height) {
        currentControllerGroup = this.cp5.addGroup("myGroup" + groupCounter++)
                .setBackgroundColor(controlFrame.getColor(DControlWriterConstants.DEFAULT_GROUP_COLOR))
                .setWidth(width)
                .setBackgroundHeight(height).setLabel(label);
    }
    
    public Tab newTab(String label) {
        Tab tab = this.cp5.addTab("myTab" + groupCounter++)
                .setLabel(label)
                .setId(groupCounter)
                .setColorBackground(controlFrame.getColor(DControlWriterConstants.DEFAULT_GROUP_COLOR))
                .setColorActive(controlFrame.getColor(DControlWriterConstants.DEFAULT_TAB_ACTIVE_COLOR));
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
			tabNames.add(name);
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
	private DColorsController2 getColorsController()
	{
		if (this.colorsController == null)
		{
			this.colorsController = new DColorsController2(this.cp5, 
				this.getControlledObject(),
				this.getTab(DControlWriterConstants.DEFAULT_COLOR_CONTROLLER_GROUP_NAME)
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
	
	public List<String> getTabNames()
	{
		return this.tabNames;
	}

    ControllerGroup currentControllerGroup;

    int groupCounter = 0;
    
    /**
     * The control frame to write on.
     */
    private final DControlFrame controlFrame;

    /**
     * The class this controller writer serves.
     */
    private final DControlFrameWriterOwner controlOwner;
    
	
	/**
	 * color variables are handled by a Colors Controller.
	 * ColorsController writes the controls for colors in
	 * the "color" tab.
	 */
	private DColorsController2 colorsController;
	
	/**
	 * This object whose variables are modified.
	 * serves when the controlOwner is delegates a drawing object.
	 */
	private Object controlledObject;
	
	/**
	 * This list stores the names of all the tabs of this control frame writer.
	 */
	private List<String> tabNames;
    
	
	/**
	 * 
	 * = = = = = = = =  = = =  Dimension related variables= = = = = = = = = = = = = = =
	 * 
	 * This fields manage the layout positioning of objects in the control frame.
	 * 
	 * = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
	 */
	/**
	 * This fields store the dimensions of the owner Control Frame.
	 */
	
	private boolean automaticLayout;
	
	private int ownerFrameWidth;
	private int ownerFrameHeight;
	
	/**
	 * This method is to add button in a square grid layout.
	 */
	private DSquareGrid buttonsPositionsquareGrid;
	
	/**
	 * This method is to add colors button in a square grid layout.
	 */
	private DSquareGrid colorsPositionsquareGrid;
	
	
	/**
     * This variables stores the controlP5 instance to write controllers. This
     * cP5 is always the one own by the controlFrame of this object.
     */
    private final ControlP5 cp5;

	
}
