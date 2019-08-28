package com.petterp.latte_ec.main.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lwkandroid.imagepicker.ImagePicker;
import com.lwkandroid.imagepicker.data.ImageBean;
import com.lwkandroid.imagepicker.data.ImagePickType;
import com.lwkandroid.imagepicker.utils.GlideImagePickerDisplayer;
import com.petterp.latte_core.mvp.factory.CreatePresenter;
import com.petterp.latte_core.mvp.base.BaseFragment;
import com.petterp.latte_core.util.file.FileUtil;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.main.home.MessageItems;
import com.petterp.latte_ec.main.login.iview.ICreateUserView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * 注册详细界面
 *
 * @author by Petterp
 * @date 2019-08-01
 */
@CreatePresenter(LoginCreatePresenter.class)
public class CreateUserDelegate extends BaseFragment<LoginCreatePresenter> implements ICreateUserView {

    public static final RequestOptions REQUEST_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    private static final int REQUEST_SELECT_IMAGES_CODE = 10;
    private String imgUrl;
    private String mSex = "男";
    @BindView(R2.id.bar_user_create)
    Toolbar toolbar = null;
    @BindView(R2.id.edit_user_create_name)
    AppCompatEditText editName = null;
    @BindView(R2.id.edit_user_create_pswd)
    AppCompatEditText editPswd = null;
    @BindView(R2.id.img_user_create_avatar)
    CircleImageView circleImageView = null;

    @OnClick(R2.id.rad_user_create_man)
    void getSexMan() {
        mSex = "男";
    }

    @OnClick(R2.id.rad_user_create_woman)
    void getSexWoman() {
        mSex = "女";
    }

    @OnClick(R2.id.rl_img_settings)
    void setIcon() {
        new ImagePicker()
                .pickType(ImagePickType.SINGLE) //设置选取类型(拍照ONLY_CAMERA、单选SINGLE、多选MUTIL)
                .needCamera(true) //是否需要在界面中显示相机入口(类似微信那样)
                .cachePath(FileUtil.IMG_DIR) //自定义缓存路径(拍照和裁剪都需要用到缓存)
                .doCrop(1, 1, 300, 300) //裁剪功能需要调用这个方法，多选模式下无效，参数：aspectX,aspectY,outputX,outputY
                .displayer(new GlideImagePickerDisplayer()) //自定义图片加载器，默认是Glide实现的,可自定义图片加载器
                .start(this, REQUEST_SELECT_IMAGES_CODE); //自定义RequestCode
    }

    @OnClick(R2.id.btn_login_login)
    void createUser() {
        String name = editName.getText().toString().trim();
        String pswd = editPswd.getText().toString().trim();
        if (name.equals("")) {
            editName.setError("用户名不能为空");
            return;
        }
        if (pswd.length() < 6 || pswd.length() > 18) {
            editPswd.setError("密码长度不合规范");
            return;
        }

        HashMap<Object, String> map = new HashMap<>();
        map.put(MuiltFileds.USER_ACCOUNT, CreateUserDelegateArgs.fromBundle(getArguments()).getPhone());
        map.put(MuiltFileds.USER_PSWD, pswd);
        map.put(MuiltFileds.USER_NAME, name);
        map.put(MuiltFileds.USER_ICON_URL, imgUrl);
        map.put(MuiltFileds.USER_SEX, mSex);
        map.put(MuiltFileds.USER_ACCOUNT_MODE, "0");
        getPresenter().setSave(map);
        Toast.makeText(getContext(), "注册成功", Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new MessageItems(1));
        fragmentStartToA(R.id.homeDelegate);
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_login_user_create;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }


    @Override
    public View setToolbar() {
        return toolbar;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_SELECT_IMAGES_CODE && resultCode == Activity.RESULT_OK && data != null) {
            //获取选择的图片数据
            List<ImageBean> resultList = data.getParcelableArrayListExtra(ImagePicker.INTENT_RESULT_DATA);
            if (resultList.size() > 0) {
                imgUrl = resultList.get(0).getImagePath();
                Glide.with(this).load(imgUrl).apply(REQUEST_OPTIONS).into(circleImageView);
            }
        }
    }
}
