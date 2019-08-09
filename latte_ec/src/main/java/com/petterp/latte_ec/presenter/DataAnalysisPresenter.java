package com.petterp.latte_ec.presenter;

import android.util.Log;
import android.widget.Toast;

import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.mvp.presenter.BasePresenter;
import com.petterp.latte_ec.model.analysis.AnalyMessages;
import com.petterp.latte_ec.model.analysis.IAnalysisImpl;
import com.petterp.latte_ec.model.analysis.IAnalysisModel;
import com.petterp.latte_ec.model.home.IHomeRvFields;
import com.petterp.latte_ec.model.home.IHomeStateType;
import com.petterp.latte_ec.model.home.MessageItems;
import com.petterp.latte_ec.view.analysis.IDataAnalysisView;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author by petterp
 * @date 2019-08-08
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
            view.updateData();
        }
    }

    public String getTitleRes() {
        return model.getTitleRes();
    }
}
