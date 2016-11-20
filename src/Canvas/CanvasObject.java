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
public interface CanvasObject 
{
    public void onMousePressed(int mouseX, int mouseY);
    public void onMouseDragged(int mouseX, int mouseY);
    public void onMouseReleased(int mouseX, int mouseY);
    public void onMouseClicked(int mouseX, int mouseY);
    public void onKeyPressed(char key);
}
