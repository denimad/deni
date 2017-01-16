/* 
 * deni 2017
 */
package Canvas;

import java.util.HashMap;
import java.util.Map;
import processing.core.PApplet;
import processing.core.PGraphics;

/**
 *
 * @author daudirac
 */
public class CanvasLayersManager 
{
    public CanvasLayersManager()
    {
        this.parentCanvas = CanvasManager.getInstance().getCanvas();
        this.initLayers();
    }
    
    public void drawLayers(PApplet canvas)
    {
		// pre drawing
		this.preDrawingLayer();
		
		// do draw
        CanvasLayer[] layers = CanvasLayer.values();
        
        for(CanvasLayer layer : layers)
        {
            if (layer.isVisible())
            {	
                canvas.image(this.layersMap.get(layer), 0, 0);
			}
        }
    }
	
    
    public PGraphics getCurrentDrawingLayer()
    {
        return this.layersMap.get(this.currentDrawingLayer);
    }
    
    
    /**
     * This method initializes the layers of a Deni Canvas.
     */
    private void initLayers()
    {
        layersMap = new HashMap<>();
        
        CanvasLayer[] layers = CanvasLayer.values();
        
        for(CanvasLayer layer : layers)
        {
            this.layersMap.put(layer, 
                parentCanvas.createGraphics(parentCanvas.width, parentCanvas.height));
			
        }
        
        this.currentDrawingLayer = CanvasLayer.Main;
    }
    
	public void preDrawingLayer()
	{
		this.clearLayer(CanvasLayer.Guides);
		this.drawLayerFrames();
		this.drawDrawingLayerMark();
	}
	
	
	public void drawLayerFrames()
	{
		CanvasLayer[] layers = CanvasLayer.values();
        PGraphics guidesCanvas = this.layersMap.get(CanvasLayer.Guides);
		guidesCanvas.beginDraw();
		
		guidesCanvas.noFill();
		guidesCanvas.strokeWeight(4);
        
		for(CanvasLayer canvasLayer : layers)
        {
			if (canvasLayer.isVisible() && canvasLayer.hasFrame())
			{
				guidesCanvas.stroke(canvasLayer.getFrameColor());
				guidesCanvas.rect(
					canvasLayer.getFramePosition()* 4,
					canvasLayer.getFramePosition() * 4,
					guidesCanvas.width - (canvasLayer.getFramePosition() * 8),
					guidesCanvas.height - (canvasLayer.getFramePosition() * 8));
				
			}
		}
		guidesCanvas.endDraw();
	}
	
	
	public void drawDrawingLayerMark()
	{
		PGraphics guidesCanvas = this.layersMap.get(CanvasLayer.Guides);
		
		guidesCanvas.beginDraw();
			guidesCanvas.noFill();
			guidesCanvas.stroke(0);
			guidesCanvas.strokeWeight(1);
			guidesCanvas.rect(
					this.currentDrawingLayer.getFramePosition()* 6,
					this.currentDrawingLayer.getFramePosition() * 6,
					guidesCanvas.width - (this.currentDrawingLayer.getFramePosition() * 12),
					guidesCanvas.height - (this.currentDrawingLayer.getFramePosition() * 12));
		guidesCanvas.endDraw();
	}
	
    public void clearLayer(CanvasLayer layer)
    {
        PGraphics clearLayer = this.layersMap.get(layer);
        
        clearLayer.beginDraw();
        clearLayer.clear();
        clearLayer.endDraw();
    }
    
    public PGraphics getToolLayer()
    {
        return this.getLayer(CanvasLayer.Tool);
    }
    
    public PGraphics getLayer(CanvasLayer layer)
    {
        return this.layersMap.get(layer);
    }
    
    void keyPressed(char key)
    {
        if(key == 'E' || key == 'e')
        {
            CanvasLayer.Tool.toggleVisible();
        }
		else if (key == 'W' || key == 'w')
        {
            CanvasLayer.Test.toggleVisible();
        }
		else if(key == 'D' || key == 'd')
        {
            CanvasLayer.Draft.toggleVisible();
        }
		else if(key == 'Q' || key == 'q')
        {
            CanvasLayer.Main.toggleVisible();
        }
		else if(key == 'R' || key == 'r')
        {
            currentDrawingLayer = currentDrawingLayer == CanvasLayer.Main ?
				CanvasLayer.Test : CanvasLayer.Main;
			currentDrawingLayer.setVisible(true);
        }
		else if(key == ' ')
		{
			this.clearLayer(CanvasLayer.Tool);
			this.clearLayer(currentDrawingLayer);
		}
    }
	
    PApplet parentCanvas;
    
    // this canvas has different layers
    // the main is the final drawing. the tool layer is used to show
    // visual guides for tools. And Test layer is to do tests before
    // drawing on the main layer.
    public Map<CanvasLayer, PGraphics> layersMap;
    private CanvasLayer currentDrawingLayer;

   
    
    
}
