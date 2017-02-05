/* 
 * deni 2017
 */
package Canvas.Layer;

import Canvas.Listener.CanvasInputAwareObject;
import Canvas.CanvasManager;
import Canvas.Layer.CanvasLayer;
import Canvas.Layer.PGraphics.AbstractPGraphics;
import Canvas.Layer.PGraphics.UndoablePGraphics;
import java.util.HashMap;
import java.util.Map;
import processing.core.PApplet;

/**
 *
 * @author daudirac
 */
public class CanvasLayersManager implements CanvasInputAwareObject
{
    public CanvasLayersManager()
    {
        this.parentCanvas = CanvasManager.getInstance().getCanvas();
        this.initLayers();
    }

	public static CanvasLayersManager getInstance()
	{
		return INSTANCE;
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
				canvas.tint(255,layer.getOpacity());
                canvas.image(this.layersMap.get(layer).getPG(), 0, 0);
			}
        }
    }
	
    
    public AbstractPGraphics getCurrentDrawingLayer()
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
				layer.getPGraphicType().createNewPGraphics(parentCanvas.width, parentCanvas.height));	
			
        }
        
        this.currentDrawingLayer = CanvasLayer.Main;
		// convinient variable assignment
		this.mainPGraphic = (UndoablePGraphics) 
			this.layersMap.get(CanvasLayer.Main);
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
        AbstractPGraphics guidesCanvas = this.layersMap.get(CanvasLayer.Guides);
		guidesCanvas.beginDraw();
		
		guidesCanvas.getPG().noFill();
		guidesCanvas.getPG().strokeWeight(4);
        
		for(CanvasLayer canvasLayer : layers)
        {
			if (canvasLayer.isVisible() && canvasLayer.hasFrame())
			{
				guidesCanvas.getPG().stroke(canvasLayer.getFrameColor());
				guidesCanvas.getPG().rect(
					canvasLayer.getFramePosition()* 4,
					canvasLayer.getFramePosition() * 4,
					guidesCanvas.getPG().width - (canvasLayer.getFramePosition() * 8),
					guidesCanvas.getPG().height - (canvasLayer.getFramePosition() * 8));
				
			}
		}
		guidesCanvas.endDraw();
	}
	
	
	public void drawDrawingLayerMark()
	{
		AbstractPGraphics guidesCanvas = this.layersMap.get(CanvasLayer.Guides);
		
		guidesCanvas.beginDraw();
			guidesCanvas.getPG().noFill();
			guidesCanvas.getPG().stroke(0);
			guidesCanvas.getPG().strokeWeight(1);
			guidesCanvas.getPG().rect(
					this.currentDrawingLayer.getFramePosition()* 6,
					this.currentDrawingLayer.getFramePosition() * 6,
					guidesCanvas.getPG().width - (this.currentDrawingLayer.getFramePosition() * 12),
					guidesCanvas.getPG().height - (this.currentDrawingLayer.getFramePosition() * 12));
		guidesCanvas.endDraw();
	}
	
    public void clearLayer(CanvasLayer layer)
    {
        AbstractPGraphics clearLayer = this.layersMap.get(layer);
        
        clearLayer.beginDraw();
        clearLayer.getPG().clear();
        clearLayer.endDraw();
    }
    
    public AbstractPGraphics getToolLayer()
    {
        return this.getLayer(CanvasLayer.Tool);
    }
    
    public AbstractPGraphics getLayer(CanvasLayer layer)
    {
        return this.layersMap.get(layer);
    }
    
	@Override
	public void onMousePressed(int mouseX, int mouseY) 
	{
		//Start a new undoable layer to main canvas.
		this.mainPGraphic.startUndoStep();
	}

	@Override
	public void onMouseDragged(int mouseX, int mouseY) {
		
	}

	@Override
	public void onMouseReleased(int mouseX, int mouseY) 
	{
		//Start a new undoable layer to main canvas.
		this.mainPGraphic.endUndoStep();

		this.clearLayer(CanvasLayer.Tool);
	}

	@Override
	public void onMouseClicked(int mouseX, int mouseY) 
	{
		
	}
	
	public void setSavingInfo(String fileName, String path)
	{
		this.imageSaver = new SaveCanvasLayerAction(
			CanvasLayer.Main,
			fileName,
			path);
	}
	
	public void doSaveImage()
	{
		if (this.imageSaver != null)
		{
			this.imageSaver.save();
		}
		else
		{
			System.err.println("No saving info specified!");
		}
	}

	@Override
	public void onKeyPressed(char key) 
	{
		switch (key) {
			case 'E':
			case 'e':
				CanvasLayer.Tool.toggleVisible();
				break;
			case 'W':
			case 'w':
				CanvasLayer.Test.toggleVisible();
				break;
			case 'R':
			case 'r':
				CanvasLayer.Draft.toggleVisible();
				break;
			case 'Q':
			case 'q':
				CanvasLayer.Main.toggleVisible();
				break;
			case 'T':
			case 't':
				currentDrawingLayer = currentDrawingLayer == CanvasLayer.Main ?
						CanvasLayer.Test : CanvasLayer.Main;
				currentDrawingLayer.setVisible(true);
				break;
			case 'Z':
			case 'z':
				this.undoMainCanvas();
				break;
				
			case 'S':
			case 's':
				this.doSaveImage();
				break;
			case ' ':
				this.clearLayer(CanvasLayer.Tool);
				this.clearLayer(currentDrawingLayer);
				break;
			default:
				break;
		}
	}
	
	private void undoMainCanvas()
	{
		this.mainPGraphic.undo();
	}
	
    PApplet parentCanvas;
    
	// convinient variable assignment to prevent bunch of 
	// castings.
	private UndoablePGraphics mainPGraphic;
	
	public SaveCanvasLayerAction imageSaver;
	
    // this canvas has different layers
    // the main is the final drawing. the tool layer is used to show
    // visual guides for tools. And Test layer is to do tests before
    // drawing on the main layer.
    public Map<CanvasLayer, AbstractPGraphics> layersMap;
    private CanvasLayer currentDrawingLayer;
	private static final CanvasLayersManager INSTANCE = new CanvasLayersManager();

}
