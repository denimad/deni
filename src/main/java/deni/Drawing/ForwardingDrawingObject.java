/* 
 * deni 2017
 */
package main.java.deni.Drawing;

import main.java.deni.Canvas.Layer.PGraphics.AbstractPGraphics;
import main.java.deni.Drawing.DrawingObject;
import main.java.deni.Drawing.DrawingObjectImpl;
import processing.core.PGraphics;
import main.java.deni.Canvas.Listener.CanvasInputAwareObject;
import processing.event.MouseEvent;

/**
 * Forwarding drawing object class.
 */
public class ForwardingDrawingObject implements
	CanvasInputAwareObject, DrawingObject {	

	protected final DrawingObjectImpl drawingObj;
	
	public ForwardingDrawingObject(DrawingObjectImpl drawingObj)
	{
		this.drawingObj = drawingObj;
	}
	
	@Override
	public void onMousePressed(int mouseX, int mouseY) 
	{
		this.drawingObj.onMousePressed(mouseX, mouseY);
	}

	@Override
	public void onMouseDragged(int mouseX, int mouseY) {
		this.drawingObj.onMouseDragged(mouseX, mouseY);
	}

	@Override
	public void onMouseReleased(int mouseX, int mouseY) {
		this.drawingObj.onMouseReleased(mouseX, mouseY);
	}

	@Override
	public void onMouseClicked(int mouseX, int mouseY) {
		this.drawingObj.onMouseClicked(mouseX, mouseY);
	}

	@Override
	public void onMouseWheel(MouseEvent e){}
	
	
	@Override
	public void onKeyPressed(char key) {
		this.drawingObj.onKeyPressed(key);
	}
	
	
	@Override
	public void onKeyReleased() {
		this.drawingObj.onKeyReleased();
	}

	@Override
	public void update() {
		this.drawingObj.update();
	}

	@Override
	public void draw(AbstractPGraphics canvasLayer) {
		this.drawingObj.draw(canvasLayer);
	}

	@Override
	public void setDrawingProperties() {
		this.drawingObj.setDrawingProperties();
	}
}
