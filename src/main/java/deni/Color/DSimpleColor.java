/*
 * deni 2017
 */
package main.java.deni.Color;

/**
 *
 * @author daudirac
 */
public class DSimpleColor implements DColor
{
	private int color;
	private float alpha;
	
	public DSimpleColor(int _color, float _alpha)
	{
		color = _color;
		alpha = _alpha;
	}

	public DSimpleColor() 
	{
		color = 100;
		alpha = 255;
	}

	/**
	 * @return the color
	 */
	@Override
	public int getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(int color) {
		this.color = color;
	}

	/**
	 * @return the alpha
	 */
	public float getAlpha() {
		return alpha;
	}

	/**
	 * @param alpha the alpha to set
	 */
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	
}
