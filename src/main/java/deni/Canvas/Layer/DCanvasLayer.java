/* 
 * deni 2017
 */
package main.java.deni.Canvas.Layer;

import main.java.deni.Canvas.Layer.PGraphics.DPGraphicType;
import main.java.deni.Color.DColorHelper;

/**
 *
 * @author daudirac
 */
public enum DCanvasLayer
{
	Draft("draft",true),
	Background("background",true, DColorHelper.DARKOLIVEGREEN,1),
	Saved("saved",true),
	Main("main",true, DColorHelper.BROWN2,0, DPGraphicType.Undoable),
	Tool("tool",true, DColorHelper.AQUAMARINE,1),
	Guides("guides",true);

	private final String name;
	private final int framePosition;
	private boolean visible;
	private final int frameColor;
	boolean hasFrame;
	int opacity;


	DPGraphicType pGraphicType;

	DCanvasLayer(String _name, boolean _visible)
	{
		this(_name,
			_visible,
			0,
			0);
		hasFrame = false;
	}

	DCanvasLayer(String _name, 
		boolean _visible, 
		int _frameColor, 
		int _framePosition)
	{
		this(_name, 
			_visible, 
			_frameColor, 
			_framePosition, 
			DPGraphicType.Simple);

	}

	DCanvasLayer(String _name, 
		boolean _visible, 
		int _frameColor, 
		int _framePosition,
		DPGraphicType pGtype)
	{
		name = _name;
		visible = _visible;
		frameColor = _frameColor;
		framePosition = _framePosition;
		hasFrame = true;
		opacity = 255;
		pGraphicType = pGtype;
	}
	public String getName()
	{
		return name;
	}

	public DPGraphicType getPGraphicType()
	{
		return pGraphicType;
	}

	public boolean isVisible()
	{
		return visible;
	}

	public void toggleVisible()
	{
		this.visible = !this.visible;
	}

	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}

	public int getOpacity()
	{
		return opacity;
	}

	public void setOpacity(int _opacity)
	{
		opacity = _opacity;
	}
	public int getFrameColor()
	{
		return frameColor;
	}

	public int getFramePosition()
	{
		return framePosition;
	}

	public boolean hasFrame()
	{
		return hasFrame;
	}
} 