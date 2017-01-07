/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Canvas;

import Util.ColorHelper;

/**
 *
 * @author daudirac
 */
public enum CanvasLayer
    {
        Draft("draft", 0,true, ColorHelper.GOLDENROD),
        Main("main", 1,true, ColorHelper.BROWN2),
        Tool("tool",2,true, ColorHelper.AQUAMARINE),
        Test("test", 3,true, ColorHelper.DARKOLIVEGREEN);
        
        private final String name;
		private final int numberID;
        private boolean visible;
		private final int frameColor;
        
		
        CanvasLayer(String _name, int _numberID, boolean _visible, int _frameColor)
        {
            name = _name;
			numberID = _numberID;
            visible = _visible;
			frameColor = _frameColor;
			
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
		
		public int numberID()
		{
			return numberID;
		}
    } 