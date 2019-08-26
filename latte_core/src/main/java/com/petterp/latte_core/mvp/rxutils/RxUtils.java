package com.petterp.latte_core.mvp.rxutils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * RxUtils工具类
 * @author by petterp
 * @date 2019-08-26
 */
public class RxUtils {
    private Disposable disposable;
    private RxUtils(){

    }

    public static RxUtils Builder(){
        return Client.rxUtils;
    }

    private static class Client{
        private static RxUtils rxUtils=new RxUtils();
    }


    public void startRx(IRxConsuming rxconsuming){
        disposable = Observable
                .create(emitter -> {
                    rxconsuming.rxStart();
                    emitter.onComplete();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> {
                    rxconsuming.rxOver();
                    onDestoryRx();
                })
                .subscribe();
        rxconsuming.rxDisposable(disposable);
    }

    public void onDestoryRx(){
        //取消Rx订阅
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

}
