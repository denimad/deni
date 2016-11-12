package Pencil;

import Object.Drawing.DrawingObjectImpl;
import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;
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
        pointsDistance = _pointsDistance;
    }
    
    
    public void addStrokePoint(PVector point)
    {
        if (lastAddedPoint.dist(point) > pointsDistance)
        {
            strokePoints.add(point);
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
    
    @Override
    public void setDrawingProperties()
    {
        canvas.noFill();
    }

    @Override
    public void draw() 
    {
        if(drawStroke)
        {
            canvas.beginShape();
            strokePoints.stream().forEach((point) -> {
                canvas.vertex(point.x, point.y);
            });
            canvas.endShape();
        }
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

    public PVector getNearestPoint(PVector point)
    {
        PVector nearestPoint = null;
        
        float minDist= 100000;
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
        return nearestPoint;
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
    
    private List<PVector> strokePoints;
    private PVector lastAddedPoint;
    private final boolean drawStroke = true;
    
    private int pointsDistance;
    
    
    private static int DEFAULT_POINTS_DISTANCE = 5;

    
}
