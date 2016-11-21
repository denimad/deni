/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Canvas;

/**
 *
 * @author daudirac
 */
public enum CanvasLayer
    {
        Draft("draft"),
        Main("main"),
        Tool("tool"),
        Test("test");
        
        private final String name;
        private boolean visible;
        
        CanvasLayer(String _name)
        {
            name = _name;
            visible = true;
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
    } 