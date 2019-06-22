package com.petterp.latte_core.delegates.web.event;


import androidx.annotation.NonNull;

import java.util.HashMap;

/**
 * @author Petterp on 2019/4/28
 * Summary:处理事件
 * email：1509492795@qq.com
 */
public class EventManager {
    private static final HashMap<String, Event> EVENTS = new HashMap<>();

    private EventManager() {

    }

    private static class Holder {
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance() {
        return Holder.INSTANCE;
    }

    public EventManager addEvent(@NonNull String name, @NonNull Event event) {
        EVENTS.put(name, event);
        return this;
    }

    //创建事件
    public Event createEvent(@NonNull String action) {
        final Event event = EVENTS.get(action);
        if (event == null) {
            return new UndefineEvent();
        }
        return event;
    }
}
