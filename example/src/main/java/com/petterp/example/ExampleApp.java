package com.petterp.example;

import android.app.Application;
import android.os.Handler;
import android.util.Log;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.mob.MobSDK;
import com.petterp.latte_core.app.AccouttManager;
import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.util.litepal.ClassifyConsume;
import com.petterp.latte_core.util.litepal.ClassifyIncome;
import com.petterp.latte_ec.icon.FontEcModule;

import org.greenrobot.eventbus.EventBus;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 配置信息层
 */
public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Handler handler = new Handler();
        LitePal.initialize(getApplicationContext());
        new Thread(() -> {
            Latte.init(getApplicationContext())
                    .withIcon(new FontAwesomeModule())
                    .withIcon(new FontEcModule())
//                .withLoaderDelayed(1000)
                    .withApiHost("http://101.132.64.249:80")
                    .withHandler(handler)
                    .withJavascriptInterface("latte")
                    .withLaucherMode(false)
                    .configure();
            //初始化Mobsdk
            MobSDK.init(getBaseContext());

            if (!AccouttManager.isSignIn()){
                String[] Consumenames=getResources().getStringArray(R.array.add_consume_kind_names);
                String[] Consumeicons=getResources().getStringArray(R.array.add_consume_kind_icons);
                String[] Incomenames=getResources().getStringArray(R.array.add_income_kind_names);
                String[] Incomeicons=getResources().getStringArray(R.array.add_income_kind_icons);
                int cosume=Consumeicons.length;
                int income=Incomeicons.length;
                Collection<ClassifyConsume> classifyConsumes=new ArrayList<>();
                for (int i=0;i<cosume;i++){
                    classifyConsumes.add(new ClassifyConsume(Consumeicons[i],Consumenames[i],"支出"));
                }
                Collection<ClassifyIncome> classifyIncomes=new ArrayList<>();
                for (int i=0;i<income;i++){
                    classifyIncomes.add(new ClassifyIncome(Incomeicons[i],Incomenames[i],"收入"));
                }
                LitePal.saveAll(classifyConsumes);
                LitePal.saveAll(classifyIncomes);
                AccouttManager.setSignState(true);
            }
        }).start();
        //初始化LitePal

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

