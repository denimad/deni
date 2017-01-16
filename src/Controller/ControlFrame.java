package Controller;


import controlP5.ControlP5;
import java.util.HashMap;
import java.util.Map;
import processing.core.PApplet;


/**
 * ControlFrames are simple PApplets Ideally used to write controllers.
 * ControlFrames with be referenced by multiple ControlP5 objects.
 * Objects that want to be write on this frame must get the ControlP5 references
 * to this frame with the method .getNewControlP5().
 */
public class ControlFrame extends PApplet{

  int w, h;
  private final PApplet parent;
  
  private int locationX;
  private int locationY;
  
  Map<Object,ControlP5> p5Controllers;
  
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
	
	p5Controllers = new HashMap<>();
	
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
  
   
  /**
   * This method returns a controlP5 that writes on this frame.
   * @param cfwo The Object that will own de CP5.
   * @return The CP5 Object that writes on this frame.
   */
    public ControlP5 getNewControlP5(ControlFrameWriterOwner cfwo) {
        
        ControlP5 cp5= new ControlP5(this);
		//register the controllers with the corresponding cfwo object
		p5Controllers.put(cfwo, cp5);
		
		return cp5;
    }
}