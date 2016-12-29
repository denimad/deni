package Controller;


import controlP5.ControlP5;
import java.util.List;
import processing.core.PApplet;


/**
 *
 * @author daudirac
 */
public class ControlFrame extends PApplet implements ControlOwner{

  int w, h;
  private final PApplet parent;
  private ControlP5 cp5;
  private int locationX;
  private int locationY;
  
  public static final int DEFAULT_WINDOW_LOCATION_X = 10;
  public static final int DEFAULT_WINDOW_LOCATION_Y = 10;
  public ControlFrame(PApplet _parent, int _w, int _h, String _name) 
  {
    this(_parent,
			_w, 
			_h , 
		ControlFrame.DEFAULT_WINDOW_LOCATION_X,
		ControlFrame.DEFAULT_WINDOW_LOCATION_Y,
		_name);
  }
  
  public ControlFrame(PApplet _parent, int _w, int _h, int locX, int locY, String _name) {
	super();   
    parent = _parent;
    w=_w;
    h=_h;
	locationX = locX;
	locationY = locY;
	
    PApplet.runSketch(new String[]{this.getClass().getName()}, this);
  }

  @Override
  public void settings() {
    size(w,h);
  }

  @Override
  public void setup() {
    surface.setLocation(locationX,locationY); 
	//cp5 = new ControlP5(this);
  }
  
  public void init()
  {
  }

  @Override
  public void draw() {
    background(40);
  }

  public int getWidth()
  {
      return w;
  }
  
  public int getHeight()
  {
      return h;
  }
  
  /**
   * this method sets a color in color mode RGB.
   * if the given values are not valid, it returns
   * a default white color.
   * @param rgb
   * @return 
   */
  public int getColor(int[] rgb)
  {
      int color;
      colorMode(PApplet.RGB);
      switch(rgb.length)
      {
          case 2 : color = color(rgb[0],rgb[1]); break;
          case 3 : color = color(rgb[0],rgb[1],rgb[2]); break;
          case 4 : color = color(rgb[0],rgb[1],rgb[2],rgb[3]); break;
          default: color = color(255,255,255);
          
      }
      return color;
  }
  
  /**
   * This factory method will create a new frame writer of this 
   * control frame for the given control frame owner.
     * @param owner The owner class that will contain the new
     * controller frame writer.
   * @return new control frame owner.
   */
  public ControlFrameWriter createNewControlFrameWriter(ControlFrameWriterOwner owner)
  {
      ControlFrameWriter cfw = new ControlFrameWriter(this, owner);
      return cfw;
  }
  
  public void removeAllControls()
  {
     List controllerList =  this.cp5.getList();
     controllerList.clear();
  }
  

    @Override
    public ControlP5 getControlP5() {
        if (cp5 == null)
        {
            cp5 = new ControlP5(this);
			cp5.hide();
        }
        
        return cp5;
    }

	public void hideControllers()
	{
		this.cp5.hide();
	}
	
	public void showControllers()
	{
		this.cp5.show();
	}
	
    @Override
    public void setControllers() {
        
    }
}