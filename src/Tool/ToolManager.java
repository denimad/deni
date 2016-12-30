/*
 * deni 2016
 */
package Tool;

import java.util.ArrayList;
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
    public ToolManager()
    {
        tools = new HashMap<>();
		toolNames = new ArrayList<>();
    }
    
    public void addTool(ToolInterface tool)
    {
        tools.put(tool.getName(), tool);
        toolNames.add(tool.getName());
		
        // if this is the first tool to be added
        // set this as the active tool.
        if (tools.size() == 1)
        {
            this.activeTool = tool;
        }
    }
    
	public ToolInterface getTool(String toolName)
	{
		return this.tools.get(toolName);
	}
	
    public void removeTool(ToolInterface tool)
    {
        tools.remove(tool.getName(), tool);
    }
    
    public List<String> getToolNames()
    {
        return toolNames;
    }
    public void setActiveTool(String toolName)
	{
		if (this.tools.containsKey(toolName))
		{
			this.tools.get(toolName).setActive(true);
		}
		else
		{
			System.err.println(
				"tool with name " + toolName + " doesn't exist ");
		}
	}
	
	public void unSetActiveTool(String toolName)
	{
		if (this.tools.containsKey(toolName))
		{
			this.tools.get(toolName).setActive(false);
		}
		else
		{
			System.err.println(
				"tool with name " + toolName + " doesn't exist");
		}
	}
	
    
    ToolInterface activeTool;
    Map<String, ToolInterface> tools;
	List<String> toolNames;
}
