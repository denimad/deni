/* 
 * deni 2017
 */
package main.java.deni.Drawing;

import main.java.deni.Canvas.Layer.PGraphics.DAbstractPGraphics;
import main.java.deni.Drawing.DDrawingObjectImpl;
import processing.core.PGraphics;
import main.java.deni.Canvas.Listener.CanvasInputAwareObject;
import processing.event.MouseEvent;
import main.java.deni.Drawing.DDrawingObject;

/**
 * Forwarding drawing object class.
 */
public class DForwardingDrawingObject implements
	CanvasInputAwareObject, DDrawingObject {	

	protected final DDrawingObjectImpl drawingObj;
	
	public DForwardingDrawingObject(DDrawingObjectImpl drawingObj)
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
	public void draw(DAbstractPGraphics canvasLayer) {
		this.drawingObj.draw(canvasLayer);
	}

	@Override
	public void setDrawingProperties() {
		this.drawingObj.setDrawingProperties();
	}
}
