/*
 * deni 2017
 */
package main.java.deni.Canvas.Listener;

/**
 *
 * @author daudirac
 */
public interface DCanvasKeyInputAwareObject 
{
	public void onKeyPressed(char key);
	public void onKeyPressed(char key, int keyCode);
	public void onKeyReleased();
}
