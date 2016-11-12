/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Timer;
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
