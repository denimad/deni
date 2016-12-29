/*
 * deni 2016
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
    ToolManager()
    {
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
    
    
    ToolObject activeTool;
    Map<String, ToolObject> tools;
}
