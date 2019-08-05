package com.petterp.example;

import android.app.Application;
import android.os.Handler;
import android.util.Log;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.mob.MobSDK;
import com.petterp.latte_core.app.Latte;
import com.petterp.latte_ec.icon.FontEcModule;

import org.greenrobot.eventbus.EventBus;
import org.litepal.LitePal;


/**
 * 配置信息层
 */
public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("demo","application");
        Handler handler=new Handler();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
//                .withLoaderDelayed(1000)
                .withApiHost("http://101.132.64.249:80")
                .withHandler(handler)
                .withJavascriptInterface("latte")
                .withLaucherMode(false)
                .configure();
        //初始化LitePal
        LitePal.initialize(getApplicationContext());
        //初始化Mobsdk
        MobSDK.init(this);
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

