package com.petterp.latte_ec.main.analysis;



import com.petterp.latte_core.mvp.presenter.BasePresenter;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

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
        boolean mode = model.getDataMode();
        view.setDataMode(mode);
        view.updateData(getTitleRes());
        if (mode) {
            view.setInConsume(model.getInConsume());
            view.setDayInConsume(model.getDaysInConsume(), model.getTimes());
            view.setClassifyBill(model.classifyPieChart());
            view.setDayBill();
        }
        super.rxGetData();
    }

    public String classifyPieMoney(String kind) {
        return model.classifyPieKindMoney(kind);
    }

    public List<MultipleItemEntity> classifyRvList() {
        return model.classifyRvList();
    }

    public List<MultipleItemEntity> classifyRvItemList(String kind) {
        return model.classifyRvItemList(kind);
    }

    public List<MultipleItemEntity> billRvList() {
        return model.billRvList();
    }

    public List<MultipleItemEntity> billRvItemList(String date) {
        return model.billRvItemList(date);
    }

    public float getBillScaleMoney() {
        return model.billScaleMoney();
    }
}
