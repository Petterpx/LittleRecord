package com.petterp.latte_ec.presenter;


import android.util.Log;

import com.example.rxretifoit.ui.LatteLoader;
import com.petterp.latte_core.mvp.presenter.BasePresenter;
import com.petterp.latte_ec.model.analysis.AnalyMessages;
import com.petterp.latte_ec.model.analysis.IAnalysisImpl;
import com.petterp.latte_ec.model.analysis.IAnalysisModel;
import com.petterp.latte_ec.view.analysis.IDataAnalysisView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author by petterp
 */
public class DataAnalysisPresenter extends BasePresenter<IDataAnalysisView> {
    private IDataAnalysisView view;
    private IAnalysisModel model;

    @Override
    public void getView(IDataAnalysisView view) {
        this.view = view;
        model = new IAnalysisImpl();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modeState(AnalyMessages messageItems) {
        model.setMonth(messageItems.getMonth());
        model.setYear(messageItems.getYear());
        if (view != null) {
            view.showLoader();
        }
        startRxData();
    }

    public String getTitleRes() {
        return model.getTitleRes();
    }


    public int getYear() {
        return model.getYear();
    }

    public int getMonth() {
        return model.getMonth();
    }

    public void showInConume() {

    }

    @Override
    public boolean startRxMode() {
        return true;
    }

    @Override
    public void rxPostData() {
        model.queryInfo();
    }

    @Override
    public void rxGetData() {
        super.rxGetData();
        Log.e("demo","再次致谢");
        view.updateData(getTitleRes());
        view.setInConsume(model.getInConsume());
        view.setDayInConsume(model.getDaysInConsume(),model.getTimes());
        view.setClassifyBill(model.classifyPieChart(),model.classifyRvList());
        view.setDayBill();
    }

    public String classifyPieMoney(String kind){
        return model.classifyPieKindMoney(kind);
    }
}
