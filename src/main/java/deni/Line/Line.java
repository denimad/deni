/* 
 * deni 2017
 */
package main.java.deni.Line;


import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Line
{

    public Line(PApplet _canvas)
    {
        canvas = _canvas;
        //do other initializations
        this.initFlagsAndVariables();
    }


    public void initializeWithTwoPoints(float _x, float _y, float _x2, float _y2)
    {
        this.initXY(_x,_y);

        //we most define the direction of drawing
        dirX = _x < _x2 ? 1 : -1;

        if((_x - _x2) == 0)
        {
            isVerticalLine = true;
            dirY = _y < _y2 ? 1 : -1;
        }
        else
        {
            //calculate m and b
            m = (_y - _y2) / (_x - _x2);
            b = y - (m*x);
        }

        x2 = _x2;
        y2 = _y2;

        this.setIncrementals(_x - _x2, _y - _y2);

        this.twoPointsDefined = true;
    }

    public void initializeWithIncrementals(float _x, float _y, float _ax, float _ay)
    {
        this.initXY(_x,_y);

        // set increments
        this.setIncrementals(_ax, _ay);

        if(incrementalX == 0)
        {
            isVerticalLine = true;
        }
        else
        {
            //calculate m and b
            m = _ay/_ax;
            b = y - (m*x);
        }

        incrementalsDefined = true;
    }

    public void setIncrementals(float x, float y)
    {
        normalizedIncrementals = new PVector(x,y);
        normalizedIncrementals.normalize();

        incrementalX = normalizedIncrementals.x;
        incrementalY = normalizedIncrementals.y;
    }

    public void setVerticalLine()
    {
        isVerticalLine = true;

    }



    private void initXY(float _x, float _y)
    {
        x = _x;
        y = _y;

        // save original positions
        ox = x;
        oy = y;
    }


    void initFlagsAndVariables()
    {
        removeMe = false;
        outOfBorder = false;
        collided = false;
        stopDrawing = false;

        acc = 1;
        incrementalsDefined = false;
        twoPointsDefined = false;

        calculateCollisions = true;
        calculateOutOfBorder = true;
        calculateEndOfDrawing = true;

        //default start with a with color
        color = canvas.color(255);
    }

    /**
     * this method runs both update and drawing
     * in most cases that is the desired sequence of actions.
     */
    public void updateDraw()
    {
        this.update();
        this.draw();
    }

    public void update()
    {

        if(this.stopDrawing){
            return;
        }

        this.nextFrameCalculation();

        this.eventManagement();

        this.updateCanvasRendering();
    }

    /**
     * in this method the canvas rendering options
     * are set to draw this line.
     */
    private void updateCanvasRendering()
    {
        //canvas.stroke(255,10);
    }


    public void draw()
    {
        if(this.stopDrawing){
            return;
        }

        canvas.line(lastX, lastY, x, y);
    }


    /**
     * In this method we calculate the variables
     * used in the drawing function for the next frame.
     */
    protected void nextFrameCalculation()
    {
        if(isVerticalLine)
        {
            this.verticalLineFrameCalculation();
        }else
        {
            //save the last x y
            lastX = x;
            lastY = y;

            // the calculation of the next
            // x and y defers
            // from the data we have.
            if(incrementalsDefined)
            {
                x+=(incrementalX * acc);
                y+=(incrementalY * acc);
            }
            else
            {
                x+= (dirX * acc);
                y = (m * x) + b;
            }
        }
    }

    private void verticalLineFrameCalculation()
    {
        //save the last x y
        lastX = x;
        lastY = y;

        if(incrementalsDefined)
        {
            y+= (incrementalY * acc);
        }
        else
        {
            y += (dirY * acc);
        }
    }

    /**
     * in this method we calculate if any event
     * has happened in this frame
     * and run the respective actions.
     */
    private void eventManagement()
    {
        // events to evaluate:
        // collision
        // out of border
        // end drawing

        collided = this.isCollided();
        outOfBorder = this.isOutOfBorder();
        stopDrawing = this.defineStopDrawing();

        if(collided) actionOnCollision();
        if(outOfBorder) actionOnOutOfBorder();
        if(stopDrawing) actionOnStopDrawing();
    }


    public void setLastCollidedPoint(float[] point)
    {
        this.lastCollidedPoint = point;
    }

    //===========================================
    // Drawing area Methods.
    //===========================================

    /**
     * this method determines the drawing area.
     * we use the drawing area to know if other object collides
     * with this line when it isn't drawn from border to border.
     * @param x x value to determine the drawing area.
     * @param y y value to determine the drawing area.
     */
    public void determineDrawArea(float x, float y)
    {
        if(drawingArea == null)
        {
            drawingArea = new float[2];
            if(ox < x){
                drawingArea[0] = ox;
                drawingArea[1] = x;
            }
            else {
                drawingArea[0] = x;
                drawingArea[1] = ox;
            }

        }
    }

    public boolean isDrawingAreaCalculated(){
        return drawingArea !=null;
    }


    public boolean pointInsideDrawingArea(float[] point)
    {
        boolean resul = true;

        if(drawingArea != null){
            if(!(point[0]>=drawingArea[0] && point[0]<=drawingArea[1]))
                resul = false;
        }
        return resul;
    }

    //===========================================
    // Ending Drawing area Methods.
    //===========================================


    //===========================================
    // override methods.
    // sub classes can override this methods
    // to change general behaviour of the line
    //===========================================
    public void actionOnCollision()
    {
        // we set here the x and y point in sake of aesthetics
        // we like that in a collision the
        // line finishes drawing at its collision point.
        x = lastCollidedPoint[0];
        y = lastCollidedPoint[1];
    }

    public void actionOnOutOfBorder()
    {

    }

    public void actionOnStopDrawing()
    {
        x = lastCollidedPoint[0];
        y = lastCollidedPoint[1];

        // when this line finishes drawing we
        // can know what is its draw area
        this.determineDrawArea(x,y);

        removeMe = true;
    }

    public boolean defineStopDrawing()
    {
        boolean resul = false;
        if(this.calculateEndOfDrawing)
        {
            //generally stop drawing this line if it has collided
            //or is out of border canvas.
            resul = outOfBorder || collided;

            // if this is a two point defined line, we also have to evaluate if
            // we are near the last point.
            if(twoPointsDefined)
            {
                if(x>x2-5 && x<x2+5 && y>y2-5 && y<y2+5)
                {
                    resul = true;
                    this.setLastCollidedPoint(new float[]{x2,y2});
                }
            }
        }
        return resul;
    }


    boolean isCollided(){
        boolean result = false;

        if(calculateCollisions && collisionPoints!=null)
        {
            for(float[] point: collisionPoints)
            {
                if(x>point[0]-5 && x<point[0]+5 && y > point[1] -5 && y < point[1]+5)
                {
                    this.setLastCollidedPoint(point);
                    result =  true;
                }
            }
        }
        return result;
    }


    public boolean isOutOfBorder()
    {
        if(calculateOutOfBorder)
        {
            float[] point = {x,y};
            if(!this.pointInCanvas(point))
            {
                this.setLastCollidedPoint(point);
                return true;
            }
        }
        return false;
    }
    //=====================================
    // Ending override methods
    //=====================================


    //=====================================
    // Collision Calculation methods
    //=====================================
    public void calculateCollisionPoints(ArrayList<Line> lines, boolean needToCalculateDrawingArea)
    {
         collisionPoints = new ArrayList<float[]>();
        float[] point;
        for(Line line: lines)
        {
            point = getCollisionPointWithLine(line);

            if(this.pointInCanvas(point))
            {
                // directly add if it doesn't need to calculate drawing area
                if(!needToCalculateDrawingArea){
                    collisionPoints.add(point);
                }
                // otherwise do the calculation
                else if(needToCalculateDrawingArea && line.pointInsideDrawingArea(point)){
                    collisionPoints.add(point);
                }
            }
        }
    }

    public float[] getCollisionPointWithLine(Line line)
    {
        float cx,cy;
        if(line.isVerticalLine)
        {
            // if it is a vertical, then the line
            // will collide when it reaches the constant x.
            cx = line.x;
            cy = (m * line.x) + b;
        }
        else
        {
             cx = (b - line.b)/(line.m-m) ;
             cy = (m*cx) + b;
        }

        float[] result = new float[2];
        result[0] = cx;
        result[1] = cy;

        return result;
    }

    public boolean pointInCanvas(float[] point)
    {
        return point[0]>0 && point[0]<canvas.width && point[1] > 0 && point[1] < canvas.height;
    }

    //=====================================
    // Ending Collision Calculation methods
    //=====================================

    protected PApplet canvas;
    protected float x, y, ox, oy, x2, y2;
    protected float lastX, lastY;
    public float incrementalX, incrementalY;

    protected float acc, dirX, dirY;

    //flags
    public boolean removeMe,
        outOfBorder,
        collided,
        stopDrawing;

    protected boolean calculateCollisions,
            calculateOutOfBorder,
            calculateEndOfDrawing;

    // geometric variables
    public float m, b;
    protected float[] lastCollidedPoint;
    protected float[] drawingArea;

    protected ArrayList<float[]> collisionPoints;

    // color of the line
    protected float color;

    protected boolean isVerticalLine;
    protected boolean incrementalsDefined, twoPointsDefined;

    PVector normalizedIncrementals;
}

