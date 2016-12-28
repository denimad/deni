/*
 * deni 2016
 */


package ToolBox;

import Canvas.CanvasObject;
import Object.Drawing.DrawingObject;
import Object.Drawing.DrawingObjectImpl;
import processing.core.PGraphics;

/**
 * Forwarding drawing object class.
 */
public class ForwardingDrawingObject implements
	CanvasObject, DrawingObject {	

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
	public void onKeyPressed(char key) {
		this.drawingObj.onKeyPressed(key);
	}

	@Override
	public void update() {
		this.drawingObj.update();
	}

	@Override
	public void draw(PGraphics canvasLayer) {
		this.drawingObj.draw(canvasLayer);
	}

	@Override
	public void setDrawingProperties() {
		this.drawingObj.setDrawingProperties();
	}
}
