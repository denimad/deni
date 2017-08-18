/* 
 * deni 2017
 */
package main.java.deni.Timer;
import processing.core.PApplet;

class DToogleTimer extends DTimer
{
    boolean toogleVariable;

    DToogleTimer(PApplet _canvas){
        super(_canvas);
        toogleVariable = true;
    }

    void setLapseTime(){
        if(toogleVariable){
            lapseTime = DTimer.DEFAULT_LONG_LAPSE_TIME;
        }else{
            lapseTime = DTimer.DEFAULT_SHORT_LAPSE_TIME;
        }
        toogleVariable = !toogleVariable;
    }
}
