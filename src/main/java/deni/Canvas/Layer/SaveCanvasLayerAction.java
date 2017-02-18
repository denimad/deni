/*
 * deni 2017
 */
package main.java.deni.Canvas.Layer;

import main.java.deni.Canvas.Layer.PGraphics.AbstractPGraphics;

/**
 *
 * @author daudirac
 */
public class SaveCanvasLayerAction 
{
	public SaveCanvasLayerAction(
		CanvasLayer _canvasLayer,
		String _fileName,
		String _outputPath)
	{
		this.canvasLayer = _canvasLayer;
		this.fileName = _fileName;
		this.outputPath = _outputPath;
		
		outputPath +=
				outputPath.endsWith("/") ? "": "/";
		
		pGraphic = 
			CanvasLayersManager.getInstance().getLayer(canvasLayer);
		
	}
	
	public void save()
	{
		pGraphic.beginDraw();
			pGraphic.getPG().save(outputPath + fileName + imageCount + ".png");
		pGraphic.endDraw();
		imageCount++;
	}
	
	int imageCount;
	String fileName;
	String outputPath;
	CanvasLayer canvasLayer;
	AbstractPGraphics pGraphic;
}
