/* 
 * deni 2017
 */
package main.java.deni.Timer;

import processing.core.PApplet;

class DTimer
{
    int lastEnterTime;
    int lapseTime;

	int shortLapseTime;
	int longLapseTime;

	boolean isInShortLapseMode;
	
    PApplet canvas;

    DTimer(PApplet _canvas)
	{
        canvas = _canvas;
		
		this.shortLapseTime = DTimer.DEFAULT_SHORT_LAPSE_TIME;
		this.longLapseTime = DTimer.DEFAULT_LONG_LAPSE_TIME;
    }

    boolean isItTickTime()
	{
        if(canvas.millis() - lastEnterTime >lapseTime){
            lastEnterTime = canvas.millis();
            setLapseTime();
            return true;
        }
        return false;
    }

    void setLapseTime()
    {
		this.lapseTime = this.isInShortLapseMode ? 
			this.shortLapseTime : this.longLapseTime;
    }
	
	void setShortLapseTime(int lapseTime)
	{
		this.shortLapseTime = lapseTime;
	}
	
	void setLongLapseTime(int lapseTime)
	{
		this.longLapseTime = lapseTime;
	}

    protected final static  int DEFAULT_LONG_LAPSE_TIME = 2000;
    protected final static int DEFAULT_SHORT_LAPSE_TIME = 500;
}
