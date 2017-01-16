/* 
 * deni 2017
 */
package Canvas;

import Util.ColorHelper;

/**
 *
 * @author daudirac
 */
public enum CanvasLayer
    {
        Draft("draft",true),
		Tool("tool",true, ColorHelper.AQUAMARINE,2),
        Main("main",true, ColorHelper.BROWN2,0),
        Test("test",true, ColorHelper.DARKOLIVEGREEN,1),
        Guides("guides",true);
		
        private final String name;
		private final int framePosition;
        private boolean visible;
		private final int frameColor;
        boolean hasFrame;
		
        CanvasLayer(String _name, boolean _visible)
        {
            name = _name;
            visible = _visible;
			framePosition = 0;
			frameColor = 0;
			hasFrame = false;
        }
		
        CanvasLayer(String _name, boolean _visible, int _frameColor, int _framePosition)
        {
            name = _name;
            visible = _visible;
			frameColor = _frameColor;
			framePosition = _framePosition;
			hasFrame = true;
			
        }
        
        public String getName()
        {
            return name;
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