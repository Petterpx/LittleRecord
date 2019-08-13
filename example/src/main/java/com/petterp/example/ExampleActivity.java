package com.petterp.example;


import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.mvp.view.BaseActivity;
import com.petterp.latte_core.mvp.view.BaseFragment;
import com.petterp.latte_core.util.callback.CallbackManager;
import com.petterp.latte_core.util.callback.IGlobalCallback;


/**
 * 主Activity,处理回调，全局view入口
 */
public class ExampleActivity extends BaseActivity {


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }


}
