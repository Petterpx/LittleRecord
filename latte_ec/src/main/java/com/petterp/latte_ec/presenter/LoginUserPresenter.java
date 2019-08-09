package com.petterp.latte_ec.presenter;

import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.mvp.presenter.BasePresenter;
import com.petterp.latte_core.util.storage.LatterPreference;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.model.home.MessageItems;
import com.petterp.latte_ec.model.login.IUserImpl;
import com.petterp.latte_ec.model.login.MuiltFileds;
import com.petterp.latte_ec.model.login.imodel.IUserModel;
import com.petterp.latte_ec.view.login.iview.IUserView;
import com.petterp.latte_ui.dialog.BaseDialogFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

/**
 * @author by petterp
 * @date 2019-08-06
 */
public class LoginUserPresenter extends BasePresenter<IUserView> {
    private IUserModel model;
    private IUserView mView;

    @Override
    public void getView(IUserView view) {
        this.mView = view;
        model = new IUserImpl();
    }

    public HashMap<Object, String> getData() {
        return model.queryData();
    }


    public void userAccountQuit() {
        LatterPreference.setLoginMode(false);
        EventBus.getDefault().post(new MessageItems(1));
    }

    public void updateName(FragmentManager fragmentManager) {
        BaseDialogFragment.Builder()
                .setLayout(R.layout.dialog_edit)
                .setWindowsView((view, viewHolder) -> {
                    viewHolder.setText(R.id.tv_dia_edit_title, "修改昵称", false);
                    viewHolder.setText(R.id.tv_dia_edit_ensure, "确定", true);
                    viewHolder.setText(R.id.tv_dia_edit_back, "取消", true);
                })
                .setOnClickListener((view, viewHolder) -> {
                    if (view.getId() == R.id.tv_dia_edit_ensure) {
                        String name = viewHolder.getText(R.id.edit_dia);
                        model.updateData(MuiltFileds.USER_NAME, name);
                        if (mView != null) {
                            mView.updateName(name);
                        }
                    }
                    viewHolder.dismiss();
                })
                .show(fragmentManager);
    }

    public void updateSex(FragmentManager fragmentManager) {
        BaseDialogFragment.Builder()
                .setLayout(R.layout.dialog_radiogroup)
                .setWindowsView((view, viewHolder) -> {
                    viewHolder.setText(R.id.tv_dia_radio_title, "修改性别", false);
                    viewHolder.setText(R.id.rad_dia_radio_left, "男", true);
                    viewHolder.setText(R.id.rad_dia_radio_right, "女", true);
                    viewHolder.setText(R.id.tv_dia_radio_ensure, "保存", true);
                    viewHolder.setText(R.id.tv_dia_radio_back, "取消", true);
                })
                .setOnClickListener((view, viewHolder) -> {
                    int id = view.getId();
                    if (id == R.id.rad_dia_radio_left) {
                        model.updateData(MuiltFileds.USER_SEX, "男");
                        mView.updateSex("男");
                    } else if (id == R.id.rad_dia_radio_right) {
                        model.updateData(MuiltFileds.USER_SEX, "女");
                        mView.updateSex("女");
                    } else {
                        viewHolder.dismiss();
                    }
                })
                .show(fragmentManager);
    }

    public void save() {
        model.saveData();
        mView.saveUp();
    }

    public void updateData(Object key, String value) {
        model.updateData(key, value);
    }

    /**
     * 是否保存
     */
    public void stateSaveData(FragmentManager fragmentManager) {
        BaseDialogFragment.Builder()
                .setLayout(R.layout.dialog_message)
                .setWindowsView((view, viewHolder) -> {
                    viewHolder.setText(R.id.tv_dia_message_title, "保存数据", false);
                    viewHolder.setText(R.id.tv_dia_message_message, "您修改了数据,是否保存吗？", false);
                    viewHolder.setText(R.id.tv_dia_message_ensure, "确定", true);
                    viewHolder.setText(R.id.tv_dia_message_back, "取消", false);
                })
                .setOnClickListener((view, viewHolder) -> {
                    if (view.getId() == R.id.tv_dia_message_ensure) {
                        save();
                    }
                    viewHolder.dismiss();
                })
                .show(fragmentManager);
    }

}
