package com.petterp.latte_core.mvp.factory;


import com.petterp.latte_core.mvp.presenter.BasePresenter;
import com.petterp.latte_core.mvp.view.IBaseView;

/**
 * 注解工厂
 * @author by Petterp
 * @date 2019-08-03
 */
public class PresenterFactoryImpl<V extends IBaseView, P extends BasePresenter<V>> implements PresenterFactory<V, P> {

    /**
     * 需要创建的Presenter的类型
     */
    private final Class<P> mPresenterClass;


    /**
     * 根据注解创建Presenter的工厂实现类
     * @param viewClazz 需要创建Presenter的V层实现类
     * @param <V> 当前View实现的接口类型
     * @param <P> 当前要创建的Presenter类型
     * @return 工厂类
     */
    public static <V extends IBaseView, P extends BasePresenter<V>> PresenterFactoryImpl<V,P> createFactory(Class<?> viewClazz){
        CreatePresenter annotation = viewClazz.getAnnotation(CreatePresenter.class);
        Class<P> aClass = null;
        if(annotation != null){
            aClass = (Class<P>) annotation.value();
        }
        return aClass == null ? null : new PresenterFactoryImpl<>(aClass);
    }


    private PresenterFactoryImpl(Class<P> presenterClass) {
        this.mPresenterClass = presenterClass;
    }


    @Override
    public P createPresenter() {
        try {
            //获取对象，类加载方式
            return mPresenterClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Presenter创建失败!，检查是否声明了@CreatePresenter(xx.class)注解");
        }
    }
}
