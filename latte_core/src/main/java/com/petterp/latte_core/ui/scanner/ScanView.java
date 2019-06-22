package com.petterp.latte_core.ui.scanner;

import android.content.Context;
import android.util.AttributeSet;

import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * @author Petterp on 2019/6/18
 * Summary:扫描二维码view
 * email：1509492795@qq.com
 */
public class ScanView extends ZBarScannerView {
    public ScanView(Context context) {
        super(context,null);
    }

    public ScanView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /**
     * 打开二维码之后有的一个扫描框
     * @param context
     * @return
     */
    @Override
    protected IViewFinder createViewFinderView(Context context) {
        return new LatteViewFindView(context);
    }
}
