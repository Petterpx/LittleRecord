package com.petterp.latte_core.mvp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.gyf.immersionbar.ImmersionBar;
import com.petterp.latte_core.mvp.factory.PresenterFactoryImpl;
import com.petterp.latte_core.mvp.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author by Petterp
 * @date 2019-08-03
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView {
    private P presenter = null;
    private Unbinder unbinder = null;
    private View rootView = null;

    public abstract boolean backMode();

    /**
     * 设置view
     *
     * @return view
     */
    public abstract Object setLayout();


    /**
     * 创建视图
     *
     * @param savedInstanceState
     * @param rootView
     */
    public abstract void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Navgation 内部执行replace方法，会导致onCreateView重新执行，这里选择保存View状态(即保存数据)
        if (rootView == null) {
            if (setLayout() instanceof Integer) {
                rootView = inflater.inflate((Integer) setLayout(), container, false);
            } else if (setLayout() instanceof View) {
                rootView = (View) setLayout();
            } else {
                throw new ClassCastException("setLayout() must be int or View Error！");
            }

            if (presenter == null) {
                PresenterFactoryImpl<IBaseView, BasePresenter<IBaseView>> factory = PresenterFactoryImpl.createFactory(getClass());
                if (factory != null) {
                    presenter = (P) factory.createPresenter();
                }
            }

            if (presenter != null) {
                presenter.onAttchView(this);
                getLifecycle().addObserver(presenter);
            }

            //绑定ButterKnife
            unbinder = ButterKnife.bind(this, rootView);
            //Fragment回收保留数据
            setRetainInstance(true);
            //添加生命周期
            onBindView(savedInstanceState, rootView);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitleToolbar();
    }

    /**
     * 沉浸式状态栏
     */
    private void setTitleToolbar() {
        ImmersionBar.with(this)
                .titleBar(setToolbar())
                .autoDarkModeEnable(true)
                .init();
    }

    /**
     * 设置Toolbar
     *
     * @return
     */
    public View setToolbar() {
        return null;
    }

    /**
     * 返回P
     *
     * @return Presenter
     */
    protected P getPresenter() {
        return presenter;
    }

    /**
     * 返回子类View
     *
     * @return view
     */
    protected View getRootView() {
        return rootView;
    }

    /**
     * 销毁
     */
    @Override
    public void onDetachView() {
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        presenter = null;
        rootView = null;
    }

    public void setBackPressed(){

    }

}
