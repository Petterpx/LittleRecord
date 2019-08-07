package com.petterp.latte_core.mvp.presenter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;


import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.mvp.view.BaseFragment;
import com.petterp.latte_core.mvp.view.IBaseView;
import com.petterp.latte_core.util.callback.CallbackManager;
import com.petterp.latte_core.util.edittext.SoftHideBoardUtils;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;


/**
 * @author by Petterp
 * @date 2019-08-03
 */
public abstract class BasePresenter<V extends IBaseView> implements DefaultLifecycleObserver {

    private Reference<V> mview;

    public void onAttchView(V mView) {
        this.mview = new SoftReference<>(mView);
        getView(mview.get());
    }

    public abstract void getView(V view);

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        SoftHideBoardUtils.hidekey(Latte.getBaseActivity());
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        if (mview != null) {
            mview.get().onDetachView();
            mview.clear();
            mview = null;
        }
        owner.getLifecycle().removeObserver(this);
    }

}
