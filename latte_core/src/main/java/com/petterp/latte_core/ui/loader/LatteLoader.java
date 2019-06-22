package com.petterp.latte_core.ui.loader;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDialog;

import com.petterp.latte_core.R;
import com.petterp.latte_core.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * @author Petterp on 2019/4/18
 * Summary:
 * email：1509492795@qq.com
 */
public class LatteLoader {

    //设置缩放比
    private static final int LOADER_SIZE_SCALE=8;
    //设置偏移量
    private static  final int LOADER_OFFSET_SCALE=10;

    private static final ArrayList<AppCompatDialog> LOADERS=new ArrayList<>();

    private static  final String DEFAULT_LOADER=LoaderStyle.PacmanIndicator.name();


    /**
     *  //便于传入枚举类型,转圈
     * @param context
     * @param type
     */
    public static void showLoading(Context context,Enum<LoaderStyle> type){
        showLoading(context,type.name());
    }


    /**
     * 显示转圈
     * @param context
     * @param type
     */
    public static void showLoading(Context context,String type){
            //尽量使用 V7包，为了兼容性
            final AppCompatDialog dialog=new AppCompatDialog(context, R.style.dialog);

            final AVLoadingIndicatorView avLoadingIndicatorView=LoaderCreator.create(type,context);
            //设置为根视图
            dialog.setContentView(avLoadingIndicatorView);

            int devocewidth=DimenUtil.getScreenWidth();
            int devoceHeight=DimenUtil.getScreenHeight();

            final Window dialogWindow=dialog.getWindow();
            if (dialogWindow!=null){
                WindowManager.LayoutParams lp=dialogWindow.getAttributes();
                lp.width=devocewidth/LOADER_SIZE_SCALE;
                lp.height=devoceHeight/LOADER_SIZE_SCALE;
                lp.height=lp.height+devoceHeight/LOADER_OFFSET_SCALE;
                lp.gravity=Gravity.CENTER;
            }
            LOADERS.add(dialog);
            dialog.show();
    }

    /**
     * 如果传入的是ActivityContext，在WebView 或者其他View上面会报错,最好传入当前Context
     * @param context
     */
    public static void showLoading(Context context){
        showLoading(context,DEFAULT_LOADER);
    }
    public static void stopLoading(){
        for (AppCompatDialog dialog:LOADERS){
            if (dialog != null) {
                if (dialog.isShowing()){
                    //会执行回调等
                    dialog.cancel();
                    //关闭显示
//                    dialog.dismiss();
                }
            }
        }
    }
}
