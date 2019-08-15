package com.petterp.latte_core.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.petterp.latte_core.app.Latte;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象Activity基类，包含Fragment返回键处理
 *
 * @author by petterp
 * @date 2019-08-07
 */
public abstract class BaseActivity extends AppCompatActivity {
    //要申请的权限
    private List<String> permissions = new ArrayList<>();
    //权限申请码
    private final int mRequestCode = 100;


    private BackPressFragment iBack;

    public interface BackPressFragment {
        /**
         * 相应逻辑方法
         *
         * @return
         */
        boolean setBackPress();
    }

    public void setiBack(BackPressFragment back) {
        this.iBack = back;
    }


    public abstract int getLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //添加Activity
        Latte.getConfigurator().withBaseActivity(this);
        if (setJurisdication()) {
            setPremission();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //执行相应方法，成功拦截，否则默认执行
        if (iBack.setBackPress()) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    public boolean setJurisdication() {
        return false;
    }

    public void addPermission(String permission) {
        permissions.add(permission);
    }

    public void setPremission() {
        //用来判断哪些权限没有申请
        List<String> mPermissionList = new ArrayList<>();
        //需要申请的权限长度
        int size = permissions.size();
        if (size > 0) {
            //逐个判断你要的权限是否已经通过
            for (int i = 0; i < size; i++) {
                if (ActivityCompat.checkSelfPermission(this, permissions.get(i)) != PackageManager.PERMISSION_GRANTED) {
                    mPermissionList.add(permissions.get(i));//添加还未授予的权限
                }
            }
        }
        if (mPermissionList.size() > 0) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                new AlertDialog.Builder(this).setTitle("我有一个想法")
                        .setMessage("我需要选取头像及读取相册的权限!")
                        .setPositiveButton("取消", (dialog, which) ->
                                Toast.makeText(this, "用户拒绝权限", Toast.LENGTH_SHORT).show()
                        )
                        .setNegativeButton("确定", (dialog, which) -> {
                            String[] permission = mPermissionList.toArray(new String[mPermissionList.size()]);
                            ActivityCompat.requestPermissions(this, permission, mRequestCode);
                        })
                        .setCancelable(false)
                        .show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //有权限没有通过
        boolean hasPermissionDismiss = false;
        if (mRequestCode == requestCode) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == -1) {
                    hasPermissionDismiss = true;
                }
            }
            //有权限没有被允许
            if (hasPermissionDismiss) {
                finish();
            } else {
                Log.e("demo", "权限通过");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //优化内存
        System.gc();
        System.runFinalization();

    }
}
