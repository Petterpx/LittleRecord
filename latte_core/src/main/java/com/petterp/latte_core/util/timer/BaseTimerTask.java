package com.petterp.latte_core.util.timer;

import java.util.TimerTask;

/**
 * @author Petterp on 2019/4/20
 * Summary:TimerTask定时器相关
 * email：1509492795@qq.com
 */
public class BaseTimerTask  extends TimerTask {
    private ITimerListener mTimerListener=null;

    public BaseTimerTask(ITimerListener mTimerListener) {
        this.mTimerListener = mTimerListener;
    }

    @Override
    public void run() {
        if (mTimerListener!=null){
            mTimerListener.onTimer();
        }
    }
}
