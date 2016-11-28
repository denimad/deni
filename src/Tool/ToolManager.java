/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tool;

import Canvas.DeniCanvas;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author daudirac
 */
public class ToolManager 
{
    ToolManager(DeniCanvas parent)
    {
        canvas = parent;
        tools = new HashMap<>();
    }
    
    public void addTool(ToolObject tool)
    {
        tools.put(tool.getName(), tool);
        
        // if this is the first tool to be added
        // set this as the active tool.
        if (tools.size() == 1)
        {
            this.activeTool = tool;
        }
    }
    
    public void removeTool(ToolObject tool)
    {
        tools.remove(tool.getName(), tool);
    }
    
    public Set<String> getToolNames()
    {
        return tools.keySet();
    }
    
    
    
    
    DeniCanvas canvas;
    ToolObject activeTool;
    Map<String, ToolObject> tools;
}
