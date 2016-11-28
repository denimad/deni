/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import controlP5.ControlP5;
import controlP5.Controller;
import controlP5.ControllerGroup;

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
       /*return this.addSlider(varName, 
            posX, 
            posY, 
            minVal, 
            maxVal, 
            this.getCurrentControllerGroup().getName());*/
        return cp5.addSlider(varName).setPosition(posX, posY)
                .setRange(minVal, maxVal)
                .plugTo(this.controlOwner, varName);
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
                .plugTo(this.controlOwner, varName)
                .setGroup(groupName);
    }
    

    public void newGroup(String label, int width, int height) {
        currentControllerGroup = this.cp5.addGroup("myGroup" + groupCounter++)
                .setBackgroundColor(controlFrame.getColor(ControlWriterConstants.DEFAULT_GROUP_COLOR))
                .setWidth(width)
                .setBackgroundHeight(height).setLabel(label);
    }
    
    public void newTab(String label, int width, int height) {
        currentControllerGroup = this.cp5.addTab("myTab" + groupCounter++)
                .setLabel(label)
                .setId(groupCounter)
                .setColorBackground(controlFrame.getColor(ControlWriterConstants.DEFAULT_GROUP_COLOR))
                .setColorActive(controlFrame.getColor(ControlWriterConstants.DEFAULT_TAB_ACTIVE_COLOR));
    }
    
    private ControllerGroup getCurrentControllerGroup()
    {
        if (currentControllerGroup == null)
        {
            this.newTab("newTab", controlFrame.w, controlFrame.h);
        }
        
        return currentControllerGroup;
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
     * This variables stores the controlP5 instance to write controllers. This
     * cP5 is always the one own by the controlFrame of this object.
     */
    private final ControlP5 cp5;
}
