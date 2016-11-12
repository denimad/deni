/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Timer;

import processing.core.PApplet;

class Timer
{
    int lastEnterTime;
    int lapseTime;
    PApplet canvas;

    Timer(PApplet _canvas){
        canvas = _canvas;
    }

    boolean isItTickTime(){

        if(canvas.millis() - lastEnterTime >lapseTime){
            lastEnterTime = canvas.millis();
            setLapseTime();
            return true;
        }
        return false;
    }

    void setLapseTime()
    {
        lapseTime = SHORT_LAPSE_TIME;
    }

    protected final static  int LONG_LAPSE_TIME = 2000;
    protected final static int SHORT_LAPSE_TIME = 500;
}
