/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
        if(key == 'T' || key == 't')
        {
            CanvasLayer.Tool.toggleVisible();
        }
        if(key == 'R' || key == 'r')
        {
            CanvasLayer.Test.toggleVisible();
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
