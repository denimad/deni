/* 
 * deni 2017
 */
package Canvas.Listener;

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
    public void onKeyPressed(char key);
}
