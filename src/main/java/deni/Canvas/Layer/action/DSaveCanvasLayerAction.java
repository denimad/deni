/*
 * deni 2017
 */
package main.java.deni.Canvas.Layer.action;

import main.java.deni.Canvas.Layer.DCanvasLayer;
import main.java.deni.Canvas.Layer.DCanvasLayersManager;
import main.java.deni.Canvas.Layer.PGraphics.DAbstractPGraphics;

/**
 *
 * @author daudirac
 */
public class DSaveCanvasLayerAction implements DAction
{
	public DSaveCanvasLayerAction(
		DCanvasLayer _canvasLayer,
		String _fileName,
		String _outputPath)
	{
		this.canvasLayer = _canvasLayer;
		this.fileName = _fileName;
		this.outputPath = _outputPath;
		
		outputPath +=
				outputPath.endsWith("/") ? "": "/";
		
		pGraphic = 
			DCanvasLayersManager.getInstance().getLayer(canvasLayer);
		
		this.internalVariablesInit();
	}
	
	private void internalVariablesInit()
	{
		// nothing internal to init right now.
	}

	@Override
	public void doAction() {
		this.save();
	}

	@Override
	public String getName() 
	{
		return "save canvas layer";
	}

	@Override
	public String getDefintion() 
	{
		return "this actions saves the canvas layer current drawing";
	}
	
	public void save()
	{
		this.lastSavedFileName = 
			fileName + imageCount + ".png";
		
		pGraphic.beginDraw();
			pGraphic.getPG().save(
				outputPath + this.lastSavedFileName);
		pGraphic.endDraw();
		imageCount++;
	}
	
	public String getLastSavedImageName()
	{
		return this.lastSavedFileName;
	}
	
	int imageCount;
	String fileName;
	String outputPath;
	String lastSavedFileName;
	DCanvasLayer canvasLayer;
	DAbstractPGraphics pGraphic;

}
