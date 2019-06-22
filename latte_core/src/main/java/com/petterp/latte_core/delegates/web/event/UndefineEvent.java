package com.petterp.latte_core.delegates.web.event;

import com.petterp.latte_core.util.log.LatteLogger;

/**
 * @author Petterp on 2019/4/28
 * Summary:
 * email：1509492795@qq.com
 */
public class UndefineEvent extends Event{
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefineEvent",params);
        return null;
    }
}
