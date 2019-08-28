package com.petterp.latte_ec.main.add.topViewVp.rvItems;


import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.petterp.latte_core.mvp.presenter.BasePresenter;
import com.petterp.latte_core.util.Context.ToastUtils;
import com.petterp.latte_ec.main.add.AddItemFileds;
import com.petterp.latte_ec.main.add.AddRvDataMessage;
import com.petterp.latte_ec.main.add.AddRvViewMessage;
import com.petterp.latte_ec.main.home.HomeItemFieds;
import com.petterp.latte_ec.main.home.HomeMessage;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * AddTop Rv -> P
 *
 * @author by petterp
 * @date 2019-08-24
 */
public class AddTopRvItemPresenter extends BasePresenter<IAddTopRvItemView> {
    private IAddTopRvItemView mView;
    private IAddTopRvItemModel mModel;
    //是否刷新view
    private boolean updateViewMode = false;
    private AddItemFileds mode = AddItemFileds.ADD_ITEM_QUERY;
    private boolean addMode;
    private String kind;
    private String kindNew;
    private String category;
    private int position;

    @Override
    public void getView(IAddTopRvItemView view) {
        this.mView = view;
        mModel = new IAddTopRvItemImpl();
        EventBus.getDefault().register(this);
    }

    List<MultipleItemEntity> consumeList() {
        return mModel.consumeList();
    }

    List<MultipleItemEntity> incomeList() {
        return mModel.incomeList();
    }

    @Override
    public boolean startRxMode() {
        return true;
    }

    @Override
    public void rxPostData() {
        switch (mode) {
            case ADD_ITEM_QUERY:
                mModel.queryInfo();
                break;
            case ADD_ITEM_ADD:
                addMode = mModel.add(kindNew, category);
                EventBus.getDefault().post(new AddRvDataMessage(mode, kindNew, category));
                break;
            case ADD_ITEM_UPDATE:
                mModel.update(kindNew, kind, category);
                EventBus.getDefault().post(new AddRvDataMessage(mode, kind, kindNew, category));
                EventBus.getDefault().post(new HomeMessage(kind, kindNew, category, HomeItemFieds.HOME_DATA_UPDATE));
                break;
            case ADD_ITEM_DELEGATE:
                mModel.delegate(kind,category);
                EventBus.getDefault().post(new AddRvDataMessage(mode, kind, category));
                EventBus.getDefault().post(new HomeMessage(kind, category, HomeItemFieds.HOME_DATA_DELEGATE));
                break;
            default:
                break;
        }
    }

    @Override
    public void rxGetData() {
        if (mView != null) {
            switch (mode) {
                case ADD_ITEM_QUERY:
                    mView.showView();
                    break;
                case ADD_ITEM_ADD:
                    if (addMode) {
                        mView.addView();
                    } else {
                        ToastUtils.showCenterText("已存在此类别");
                    }
                    EventBus.getDefault().post(new AddRvViewMessage(mode));
                    break;
                case ADD_ITEM_UPDATE:
                    EventBus.getDefault().post(new AddRvViewMessage(mode, position));
                    EventBus.getDefault().post(new HomeMessage(HomeItemFieds.HOME_VIEW_UPDATE));
                    break;
                case ADD_ITEM_DELEGATE:
                    EventBus.getDefault().post(new AddRvViewMessage(mode, position));
                    EventBus.getDefault().post(new HomeMessage(HomeItemFieds.HOME_VIEW_UPDATE));
                    break;
                default:
                    break;
            }
        }
        super.rxGetData();
    }

    /**
     * 分发EvenBus
     *
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modeState(AddMessage message) {
        this.mode = message.getMode();
        kind = message.getKind();
        kindNew = message.getKindNew();
        category = message.getCategory();
        position = message.getPosition();
        updateViewMode = true;
        startRxData();
    }


    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        super.onResume(owner);
        //通知清除ViewPager
        EventBus.getDefault().post(new AddRvViewMessage(AddItemFileds.ADD_ITEM_REMOVE_VP));
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        super.onDestroy(owner);
        EventBus.getDefault().unregister(this);
    }

    public void setTitleMode(String mode) {
        mModel.setTitleMode(mode);
    }
}
