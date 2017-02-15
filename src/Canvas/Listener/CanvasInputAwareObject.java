/* 
 * deni 2017
 */
package Canvas.Listener;

import processing.event.MouseEvent;

/**
 *
 * @author daudirac
 */
public interface CanvasInputAwareObject 
{
    public void onMousePressed(int mouseX, int mouseY);
    public void onMouseDragged(int mouseX, int mouseY);
    public void onMouseReleased(int mouseX, int mouseY);
    public void onMouseClicked(int mouseX, int mouseY);
	public void onMouseWheel(MouseEvent e);
    public void onKeyPressed(char key);
	public void onKeyReleased();
}
