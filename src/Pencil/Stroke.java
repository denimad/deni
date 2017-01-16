/* 
 * deni 2017
 */
package Pencil;

import Drawing.DrawingObjectImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

/**
 *
 * @author daudirac
 */
public class Stroke extends DrawingObjectImpl
{
    public Stroke()
    {
        this(Stroke.DEFAULT_POINTS_DISTANCE);
    }
    
    
    public Stroke(int _pointsDistance)
    {
        super();
        newStrokePoints();
        strokeMovement = new HashMap<>();
        pointsDistance = _pointsDistance;
    }
    
    
    public void addStrokePoint(PVector point)
    {
        if (lastAddedPoint.dist(point) > pointsDistance)
        {
            strokePoints.add(point);
            strokeMovement.put(lastAddedPoint, PVector.sub(point, lastAddedPoint));
            lastAddedPoint = point;
        }
    }
   
    
    private void newStrokePoints()
    {
        strokePoints = new ArrayList();
        lastAddedPoint = new PVector(0,0);
    }
    
    @Override
    public void update() {

    }
	
	public void setPointsDistance(int distance)
	{
		this.pointsDistance = distance;
	}
    
    @Override
    public void setDrawingProperties()
    {
       
    }

    @Override
    public void draw(PGraphics canvasLayer) 
    {
        canvasLayer.beginDraw();
        if(drawStroke)
        {
            
            canvasLayer.beginShape();
            for (PVector point: strokePoints)
            {
                canvasLayer.stroke(0);
                canvasLayer.vertex(point.x, point.y);
                canvasLayer.ellipse(point.x, point.y,3,3);
                if(drawStrokeMovement && point != lastAddedPoint)
                {
                    PVector movement = strokeMovement.get(point);
                    drawArrow(canvasLayer, point.x,point.y,pointsDistance,pointsDistance/2,
                        PApplet.atan2(movement.y, movement.x));
                }
            }
            canvasLayer.endShape();
        }
        canvasLayer.endDraw();
    }
    
    void drawArrow (PGraphics canvasLayer, PVector loc, PVector dir, float r, float ar)
    {
      
      dir.normalize();
      dir.mult(r);
      float endX = loc.x+ dir.x;   
      float endY = loc.y+ dir.y;
      canvasLayer.line(loc.x, loc.y, endX,endY);
      canvasLayer.ellipse(endX,endY,2,2);
    }
    
    void drawArrow (PGraphics canvasLayer, float x, float y, float r, float ar, float angle)
    {
      float endX = x + PApplet.cos (angle)*r;
      float endY = y + PApplet.sin (angle)*r;
      canvasLayer.line (x, y, endX, endY );
      canvasLayer.line (endX, endY, endX + PApplet.cos (angle-PApplet.PI * 0.88f)
            * ar, endY + PApplet.sin (angle-PApplet.PI*0.88f) * ar);
      canvasLayer.line (endX, endY, endX + PApplet.cos (angle-PApplet.PI * 1.12f) 
            * ar, endY + PApplet.sin (angle-PApplet.PI*1.12f) * ar);
    }
    
    public boolean isNear(PVector point, int minDist)
    {
        boolean result = false;
        for (PVector strokePoint : strokePoints)
        {
            if (strokePoint.dist(point)<minDist)
            {
                result = true;
                break;
            }
        }
        return result;
    }

    public PVector getNearestPoint(PVector point, int minDistance)
    {
        PVector nearestPoint = null;
        
        float minDist= 10000;
        float dist;
        for (int i = 0; i < strokePoints.size() ; i++)
        {
            dist = strokePoints.get(i).dist(point);
            if (dist < minDist)
            {
                minDist = dist;
                nearestPoint = strokePoints.get(i);
            }
        }
       
        return minDist < minDistance ? nearestPoint : null;
    }
    
    @Override
    public void onMousePressed(int mouseX, int mouseY) 
    {
        newStrokePoints();
    }

    @Override
    public void onMouseDragged(int mouseX, int mouseY) 
    {
        this.addStrokePoint(
            new PVector(mouseX, mouseY));
    }
    
    @Override
    public void onMouseReleased(int mouseX, int mouseY) 
    {
        // do nothing on mouse release
    }

    @Override
    public void onKeyPressed(char key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public List<PVector> strokePoints;
    public HashMap<PVector, PVector> strokeMovement;
    
    private PVector lastAddedPoint;
    public  boolean drawStroke = true;
    public boolean drawStrokeMovement = true;
    
    private int pointsDistance;
    
    
    public static final int DEFAULT_POINTS_DISTANCE = 20;

    @Override
    public void onMouseClicked(int mouseX, int mouseY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
