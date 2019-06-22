package com.petterp.example;

import android.app.Application;
import android.os.Handler;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.petterp.latte_core.app.Latte;
import com.petterp.latte_ec.icon.FontEcModule;


/**
 * 配置信息层
 */
public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Handler handler=new Handler();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withLoaderDelayed(1000)
                .withApiHost("http://101.132.64.249:80")
                .withHandler(handler)
                .withJavascriptInterface("latte")
                .withLaucherMode(false)
                .configure();
//        LitePal.initialize(getApplicationContext());
//        initStetho();
//        DatabaseManager.getInstance().init(this);
//        //开启极光推送
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
//        CallbackManager.getInstance()
//                .addCallback(CallbackType.TAG_OPEN_PUSH, args -> {
//                    if (JPushInterface.isPushStopped(Latte.getApplication())) {
//                        //开启极光推送
//                        JPushInterface.setDebugMode(true);
//                        JPushInterface.init(Latte.getApplication());
//                    }
//                })
//                .addCallback(CallbackType.TAG_STOP_PUSH, args -> {
//                    if (!JPushInterface.isPushStopped(Latte.getApplication())) {
//                        //停止极光推送
//                        JPushInterface.stopPush(Latte.getApplication());
//                    }
//                });
    }

}

