package com.petterp.latte_ec.view.analysis;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author by petterp
 * @date 2019-08-13
 */
public class TestView extends View {
    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
