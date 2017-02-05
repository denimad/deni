/* 
 * deni 2017
 */
package Canvas.Layer;

import Canvas.Layer.PGraphics.AbstractPGraphics;
import Canvas.Layer.PGraphics.PGraphicType;
import Canvas.Layer.PGraphics.SimplePGraphics;
import Canvas.Layer.PGraphics.UndoablePGraphics;
import Util.ColorHelper;

/**
 *
 * @author daudirac
 */
public enum CanvasLayer
    {
        Draft("draft",true),
		Tool("tool",true, ColorHelper.AQUAMARINE,2),
        Main("main",true, ColorHelper.BROWN2,0, PGraphicType.Undoable),
        Test("test",true, ColorHelper.DARKOLIVEGREEN,1),
        Guides("guides",true);

        private final String name;
		private final int framePosition;
        private boolean visible;
		private final int frameColor;
        boolean hasFrame;
		PGraphicType pGraphicType;
		
        CanvasLayer(String _name, boolean _visible)
        {
			this(_name,
				_visible,
				0,
				0);
			hasFrame = false;
        }
		
        CanvasLayer(String _name, 
			boolean _visible, 
			int _frameColor, 
			int _framePosition)
        {
            this(_name, 
				_visible, 
				_frameColor, 
				_framePosition, 
				PGraphicType.Simple);
			
        }
	
		CanvasLayer(String _name, 
			boolean _visible, 
			int _frameColor, 
			int _framePosition,
			PGraphicType pGtype)
        {
            name = _name;
            visible = _visible;
			frameColor = _frameColor;
			framePosition = _framePosition;
			hasFrame = true;
			pGraphicType = pGtype;
        }
        public String getName()
        {
            return name;
        }
		
		public PGraphicType getPGraphicType()
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