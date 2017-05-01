/* 
 * deni 2017
 */
package main.java.deni.Tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author daudirac
 */
public class DToolManager 
{
    public DToolManager()
    {
        tools = new HashMap<>();
		toolNames = new ArrayList<>();
    }
    
    public void addTool(DToolInterface tool)
    {
        tools.put(tool.getName(), tool);
        toolNames.add(tool.getName());
    }
    
	public DToolInterface getTool(String toolName)
	{
		return this.tools.get(toolName);
	}
	
    public void removeTool(DToolInterface tool)
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
	
    
    Map<String, DToolInterface> tools;
	List<String> toolNames;
}
