package com.petterp.latte_ec.main.login;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.petterp.latte_core.mvp.presenter.BasePresenter;
import com.petterp.latte_ec.main.login.iview.IRegisterView;

import java.util.concurrent.TimeUnit;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * 注册-> 控制层
 *
 * @author by Petterp
 * @date 2019-07-31
 */
public class LoginRegisterPresenter extends BasePresenter<IRegisterView> {
    private IRegisterView iView;
    private IRegisterImpl iModel;
    private EventHandler eventHandler;
    private Disposable disposable;


    public void setCreateUser(String res) {
        closeClick();
        showAvLoader();
        iModel.createUser(res);
    }

    public void setCreateCode(String code) {
        showAvLoader();
        iModel.createCode(code);
    }

    private void showAvLoader() {
        if (iView != null) {
            iView.showLoader();
        }
    }

    private void hideAvLoader() {
        if (iView != null) {
            iView.stopLoader();
        }
    }

    private void startTimer() {
        int count = 30;
        disposable = Observable.interval(1000, TimeUnit.MILLISECONDS)
                .take(count + 1)
                .map(aLong -> String.valueOf(count - aLong))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::setTvCode)
                .doOnComplete(this::openClick).subscribe();
    }

    private void setTvCode(String res) {
        if (iView != null) {
            iView.setTvCode(res);
        }
    }

    private void openClick() {
        if (iView != null) {
            iView.setTvCode("获取验证码");
            iView.openClick();
        }
    }

    private void closeClick() {
        if (iView != null) {
            iView.closeClick();
        }
    }

    public void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            disposable = null;
        }
    }

    private void onClickUserInfo(){
        if (iView != null) {
            iView.onclickUserInfo();
        }
    }

    private void codeError(){
        if (iView != null) {
            iView.codeError();
        }
    }

    public String getPhone(){
        return iModel.getPhone();
    }

    @Override
    public void getView(IRegisterView view) {
        this.iView = view;
        iModel = new IRegisterImpl();
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                // afterEvent会在子线程被调用，因此如果后续有UI相关操作，需要将数据发送到UI线程
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                new Handler(Looper.getMainLooper(), msg1 -> {
                    int event1 = msg1.arg1;
                    int result1 = msg1.arg2;
                    Object data1 = msg1.obj;
                    if (event1 == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        if (result1 == SMSSDK.RESULT_COMPLETE) {
                            //成功了开启倒计时
                            startTimer();
                            // TODO 处理成功得到验证码的结果
                        } else {
                            // TODO 处理错误的结果
                            ((Throwable) data1).printStackTrace();
                            Log.e("demo",((Throwable) data1).getMessage());
                            //否则打开重复点击开关
                            openClick();
                        }
                    } else if (event1 == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        if (result1 == SMSSDK.RESULT_COMPLETE) {
                            //调用倒计时功能
                            onClickUserInfo();
                        } else {
                            //弹出错误信息
                            codeError();
                            // TODO 处理错误的结果
                            ((Throwable) data1).printStackTrace();
                        }
                    }
                    //关闭Loader
                    hideAvLoader();
                    // TODO 其他接口的返回结果也类似，根据event判断当前数据属于哪个接口
                    return false;
                }).sendMessage(msg);
            }
        };
        // 注册一个事件回调，用于处理SMSSDK接口请求的结果
        SMSSDK.registerEventHandler(eventHandler);
    }
}
