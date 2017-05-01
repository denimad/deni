/*
 * deni 2017
 */
package main.java.deni.Canvas.Layer.action;

import main.java.deni.Canvas.DCanvasManager;
import main.java.deni.Canvas.Layer.DCanvasLayer;
import main.java.deni.Canvas.Layer.DCanvasLayersManager;
import main.java.deni.Canvas.Layer.PGraphics.DAbstractPGraphics;
import processing.core.PImage;

/**
 *
 * @author daudirac
 */
public class DUpdateCanvasLayerAction implements DAction
{

	public DUpdateCanvasLayerAction(
		DCanvasLayer _canvasLayer,
		String _fileName,
		String _resourcePath)
	{
		this.canvasLayer = _canvasLayer;
		this.fileName = _fileName;
		this.resourcePath = _resourcePath;
		
		resourcePath +=
				resourcePath.endsWith("/") ? "": "/";
		
		pGraphic = 
			DCanvasLayersManager.getInstance().getLayer(canvasLayer);
		
	}
	
	public void setUpdateImageName(String _fileName)
	{
		this.fileName = _fileName;
	}
	
	public void updateImage()
	{
		PImage updatedImage = DCanvasManager.getInstance().
			getCanvas().
			loadImage(this.resourcePath + this.fileName);
		
		pGraphic.beginDraw();
			pGraphic.getPG().image(updatedImage, 0, 0);
		pGraphic.endDraw();
	}
	
	@Override
	public void doAction() 
	{
		this.updateImage();
	}

	@Override
	public String getName() {
		return "Insert Image to Canvas Layer";
	}

	@Override
	public String getDefintion() 
	{
		return "This method inserts";
	}
	
	String fileName;
	String resourcePath;
	DCanvasLayer canvasLayer;
	DAbstractPGraphics pGraphic;
}
