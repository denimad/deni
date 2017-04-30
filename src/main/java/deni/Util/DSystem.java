/*
 * deni 2017
 */
package main.java.deni.Util;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class defines the static utility methods that are used
 * internally by the software.
 */
public class DSystem 
{
	/**
	 * 
	 * @return 
	 */
	public static String getSourceCodeLocation()
	{
		String path = "";
		try
		{
			path = DSystem.class.getProtectionDomain().
			getCodeSource().getLocation().toURI().getPath();
		}
		catch (URISyntaxException e)
		{
			e.printStackTrace();
		}
		
		return path;
	}
	
	private static String getRunningJarLocation()
	{
		String path = "";
		
		try
		{
			Path jarPath = Paths.get(
				DSystem.class.getProtectionDomain().
					getCodeSource().getLocation().toURI());

			Path parentPath = Paths.get(
				jarPath.getParent().toUri());
			
			path = parentPath.toString();
		}
		catch (URISyntaxException e)
		{
			e.printStackTrace();
		}
		
		return path;
	}
	
	
	
	public static String getAppResourcesPath()
	{
		return DSystem.getRunningJarLocation() +  "/resources";
	}
	
	public static String getAppSavedImagesPath()
	{
		return DSystem.getRunningJarLocation() + "/savedImages";
	}
	
	/**
	 * This method returns a resource located in the /resource directory.
	 * @param filename the filename of the resource to return.
	 * @return the requested resource.
	 */
	public static File getAppResource(String filename)
	{
		File resourceFile = null;

		resourceFile = new File(DSystem.getAppResourcesPath() + 
			"/" + 
			filename);

		return resourceFile;
	}
}
