package com.petterp.latte_ec.main.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.lwkandroid.imagepicker.ImagePicker;
import com.lwkandroid.imagepicker.data.ImageBean;
import com.lwkandroid.imagepicker.data.ImagePickType;
import com.lwkandroid.imagepicker.utils.GlideImagePickerDisplayer;
import com.petterp.latte_core.mvp.factory.CreatePresenter;
import com.petterp.latte_core.mvp.base.BaseFragment;
import com.petterp.latte_core.util.file.FileUtil;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.main.login.iview.IUserView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.petterp.latte_ec.main.login.CreateUserDelegate.REQUEST_OPTIONS;

/**
 * @author by petterp
 * @date 2019-08-06
 */
@CreatePresenter(LoginUserPresenter.class)
public class UserDelegate extends BaseFragment<LoginUserPresenter> implements IUserView {
    private static final int REQUEST_SELECT_IMAGES_CODE = 101;
    private boolean mode = false;
    @BindView(R2.id.bar_user_info)
    Toolbar toolbar = null;
    @BindView(R2.id.img_login_user_icon)
    CircleImageView circleImageView = null;
    @BindView(R2.id.tv_login_user_name)
    AppCompatTextView userName = null;
    @BindView(R2.id.tv_login_user_sex)
    AppCompatTextView userSex = null;
    @BindView(R2.id.tv_login_user_account)
    AppCompatTextView userAccount = null;

    @OnClick(R2.id.rl_login_user_icon)
    void updateIcon() {
        new ImagePicker()
                .pickType(ImagePickType.SINGLE) //设置选取类型(拍照ONLY_CAMERA、单选SINGLE、多选MUTIL)
                .needCamera(true) //是否需要在界面中显示相机入口(类似微信那样)
                .cachePath(FileUtil.IMG_DIR) //自定义缓存路径(拍照和裁剪都需要用到缓存)
                .doCrop(1, 1, 300, 300) //裁剪功能需要调用这个方法，多选模式下无效，参数：aspectX,aspectY,outputX,outputY
                .displayer(new GlideImagePickerDisplayer()) //自定义图片加载器，默认是Glide实现的,可自定义图片加载器
                .start(this, REQUEST_SELECT_IMAGES_CODE); //自定义RequestCode
    }

    @OnClick(R2.id.rl_login_user_name)
    void updateName() {
        getPresenter().updateName(getFragmentManager());
    }

    @OnClick(R2.id.rl_login_user_sex)
    void updateSex() {
        getPresenter().updateSex(getFragmentManager());
    }

    @OnClick(R2.id.tv_user_save)
    void save() {
        if (mode) {
            getPresenter().save();
            mode = false;
        }
    }

    @OnClick(R2.id.btn_login_user_account)
    void user_account() {
        getPresenter().userAccountQuit();
        fragmentUP();
    }

    @OnClick(R2.id.ic_dia_radio_back)
    void back() {
        fragmentUP();
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_login_user;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        HashMap<Object, String> map = getPresenter().getData();
        Glide.with(this).load(map.get(MuiltFileds.USER_ICON_URL)).into(circleImageView);
        userName.setText(map.get(MuiltFileds.USER_NAME));
        userSex.setText(map.get(MuiltFileds.USER_SEX) + "");
        //0代表手机登录，1代表qq
        if (map.get(MuiltFileds.USER_ACCOUNT_MODE).equals("0")) {
            userAccount.setText(R.string.user_login_phone);
        } else {
            userAccount.setText(R.string.user_login_qq);
        }
    }

    @Override
    public View setToolbar() {
        return toolbar;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SELECT_IMAGES_CODE && resultCode == Activity.RESULT_OK && data != null) {
            //获取选择的图片数据
            List<ImageBean> resultList = data.getParcelableArrayListExtra(ImagePicker.INTENT_RESULT_DATA);
            if (resultList.size() > 0) {
                mode = true;
                String imgUrl = resultList.get(0).getImagePath();
                Glide.with(this).load(imgUrl).apply(REQUEST_OPTIONS).into(circleImageView);
                getPresenter().updateData(MuiltFileds.USER_ICON_URL, imgUrl);
            }
        }
    }

    @Override
    public void updateName(String name) {
        mode = true;
        userName.setText(name);
    }

    @Override
    public void updateSex(String sex) {
        mode = true;
        userSex.setText(sex);
    }

    @SuppressLint("WrongConstant")
    @Override
    public boolean setBackPress(int keycode) {
        if (mode) {
            getPresenter().stateSaveData(getFragmentManager());
            return true;
        }
        return false;
    }


}
