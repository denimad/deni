/* 
 * deni 2017
 */
package main.java.deni.Canvas.Layer;

import main.java.deni.Canvas.Layer.action.DSaveCanvasLayerAction;
import main.java.deni.Canvas.Listener.CanvasInputAwareObject;
import main.java.deni.Canvas.DCanvasManager;
import main.java.deni.Canvas.Layer.DCanvasLayer;
import main.java.deni.Canvas.Layer.PGraphics.DAbstractPGraphics;
import main.java.deni.Canvas.Layer.PGraphics.DUndoablePGraphics;
import java.util.HashMap;
import java.util.Map;
import main.java.deni.Canvas.Layer.action.DUpdateCanvasLayerAction;
import processing.core.PApplet;
import processing.event.MouseEvent;

/**
 *
 * @author daudirac
 */
public class DCanvasLayersManager implements CanvasInputAwareObject
{
    public DCanvasLayersManager()
    {
        this.parentCanvas = DCanvasManager.getInstance().getCanvas();
        this.initLayers();
		this.setDefaultZoomTranslate();
    }

	public static DCanvasLayersManager getInstance()
	{
		return INSTANCE;
	}

    public void drawLayers(PApplet canvas)
    {
		// pre drawing
		this.preDrawingLayer();
		
		// do draw
        DCanvasLayer[] layers = DCanvasLayer.values();
        canvas.clear();
        for(DCanvasLayer layer : layers)
        {
            if (layer.isVisible())
            {	
				if (layer != DCanvasLayer.Guides)
				{
					canvas.pushMatrix();
						canvas.translate(translateX,translateY);
						canvas.scale(scaleFactor);
						//canvas.tint(255,layer.getOpacity());
						canvas.image(this.layersMap.get(layer).getPG(), 0, 0);
					canvas.popMatrix();
				}
				else
				{
					canvas.image(this.layersMap.get(layer).getPG(), 0, 0);
				}
			}
        }
    }
	
    
    public DAbstractPGraphics getCurrentDrawingLayer()
    {
        return this.layersMap.get(this.currentDrawingLayer);
    }
    
    private void setDefaultZoomTranslate()
	{
		scaleFactor = 1;
		translateX = 0;
		translateY = 0;
	}
	
	
    /**
     * This method initializes the layers of a Deni Canvas.
     */
    private void initLayers()
    {
        layersMap = new HashMap<>();
        
        DCanvasLayer[] layers = DCanvasLayer.values();
        
        for(DCanvasLayer layer : layers)
        {
            this.layersMap.put(layer, 
				layer.getPGraphicType().createNewPGraphics(parentCanvas.width, parentCanvas.height));	
			
        }
        
        this.currentDrawingLayer = DCanvasLayer.Main;
		// convinient variable assignment
		this.mainPGraphic = (DUndoablePGraphics) 
			this.layersMap.get(DCanvasLayer.Main);
    }
	
	
	public void preDrawingLayer()
	{
		this.clearLayer(DCanvasLayer.Guides);
		this.drawLayerFrames();
	//	this.drawDrawingLayerMark();
	}
	
	
	public void drawLayerFrames()
	{
		DCanvasLayer[] layers = DCanvasLayer.values();
        DAbstractPGraphics guidesCanvas = this.layersMap.get(DCanvasLayer.Guides);
		guidesCanvas.beginDraw();
		
		guidesCanvas.getPG().noFill();
		guidesCanvas.getPG().strokeWeight(4);
        
		for(DCanvasLayer canvasLayer : layers)
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
		DAbstractPGraphics guidesCanvas = this.layersMap.get(DCanvasLayer.Guides);
		
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
	
    public void clearLayer(DCanvasLayer layer)
    {
        DAbstractPGraphics clearLayer = this.layersMap.get(layer);
        
        clearLayer.beginDraw();
        clearLayer.getPG().clear();
        clearLayer.endDraw();
    }
    
    public DAbstractPGraphics getToolLayer()
    {
        return this.getLayer(DCanvasLayer.Tool);
    }
    
    public DAbstractPGraphics getLayer(DCanvasLayer layer)
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

		this.clearLayer(DCanvasLayer.Tool);
	}

	@Override
	public void onMouseClicked(int mouseX, int mouseY) 
	{
		
	}
	
	@Override
	public void onMouseWheel(MouseEvent e) 
	{
		translateX = translateX-e.getAmount()*(e.getX())/100;
		translateY = translateY-e.getAmount()*(e.getY())/100;
		scaleFactor += e.getAmount() / 100;
	}
	
	
	public void setSavingInfo(String fileName, String path)
	{
		this.imageSaver = new DSaveCanvasLayerAction(
			DCanvasLayer.Main,
			fileName,
			path);
		this.imageUpdater = new DUpdateCanvasLayerAction(
			DCanvasLayer.Draft,
			fileName,
			path);
	}
	
	public void doSaveImage()
	{
		if (this.imageSaver != null ||
			this.imageUpdater != null)
		{
			this.imageSaver.doAction();
			this.imageUpdater.setUpdateImageName(
				this.imageSaver.getLastSavedImageName());
		}
		else
		{
			String errorMessage = (this.imageSaver == null ? 
				"\nNo saving info specified!":""	) + 
				(this.imageUpdater == null ? 
				"\n No updating info specified!":""	);
			
			System.err.println(errorMessage);
		}
	}
	
	public void doUpdateImage()
	{
		if (this.imageUpdater != null)
		{
			this.imageUpdater.doAction();
		}
		else
		{
			System.err.println("No updating info specified!");
		}
	}

	@Override
	public void onKeyPressed(char key) 
	{
		switch (key) {
			
			case 'Q':
			case 'q':
				 DCanvasLayer.Main.toggleVisible();
				break;
			case 'W':
			case 'w':
				 DCanvasLayer.Background.toggleVisible();
				break;
			case 'E':
			case 'e':
				DCanvasLayer.Draft.toggleVisible();
				break;
			case 'R':
			case 'r':
				DCanvasLayer.Tool.toggleVisible();
				break;
			case 'Z':
			case 'z':
				this.undoMainCanvas();
				break;
				
			case 'S':
			case 's':
				this.doSaveImage();
				break;
			case 'U':
			case 'u':
				this.doUpdateImage();
				break;
				
			case 'C':
			case 'c':
				this.clearLayer(DCanvasLayer.Tool);
				this.clearLayer(currentDrawingLayer);
				break;
			case ' ':
				this.setDefaultZoomTranslate();
			default:
				break;
		}
	}


	@Override
	public void onKeyReleased() 
	{
	}


	private void undoMainCanvas()
	{
		this.mainPGraphic.undo();
	}
	
    PApplet parentCanvas;
    
	// convinient variable assignment to prevent bunch of 
	// castings.
	private DUndoablePGraphics mainPGraphic;
	
	public DSaveCanvasLayerAction imageSaver;
	public DUpdateCanvasLayerAction imageUpdater;
	
    // this canvas has different layers
    // the main is the final drawing. the tool layer is used to show
    // visual guides for tools. And Test layer is to do tests before
    // drawing on the main layer.
    public Map<DCanvasLayer, DAbstractPGraphics> layersMap;
    private DCanvasLayer currentDrawingLayer;
	private static final DCanvasLayersManager INSTANCE = new DCanvasLayersManager();
	
	// This variables control the zoom and pan actions on the canvases.
	public float scaleFactor;
	public float translateX;
	public float translateY;
}
