/* 
 * deni 2017
 */
package main.java.deni.Timer;
import processing.core.PApplet;

class ToogleTimer extends Timer
{
    boolean toogleVariable;

    ToogleTimer(PApplet _canvas){
        super(_canvas);
        toogleVariable = true;
    }

    void setLapseTime(){
        if(toogleVariable){
            lapseTime = LONG_LAPSE_TIME;
        }else{
            lapseTime = SHORT_LAPSE_TIME;
        }
        toogleVariable = !toogleVariable;
    }
}
