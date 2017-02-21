/*
 * deni 2017
 */
package main.java.deni.Controller.Color;

import controlP5.Bang;
import controlP5.ControlP5;
import main.java.deni.Color.DColor;

/**
 *
 * @author daudirac
 */
public interface DColorEditor<T> 
{
	public void doEditColor(DColor color, Bang bang);
	public void drawMe();
	public DColorEditor setPosition(int x, int y);
	public DColorEditor setGroupName(String gn);
}