package com.petterp.latte_core.delegates.bottom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.LinearLayoutCompat;

/**
 * @author by Petterp
 * @date 2019-07-18
 */
public class LinearLayoutView extends LinearLayoutCompat {
    private int width;
    private int heigth;
    private Paint paint;
    public LinearLayoutView(Context context) {
        super(context);
        initPaint();
    }

    public LinearLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        paint=new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
        paint.setDither(true);
        setWillNotDraw(false);
    }

    @Override
    @SuppressLint("DrawAllocation")
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(width-300, 0, width, heigth*2);
        canvas.drawArc(rectF, 0, -90, true, paint);
        RectF rectF2 = new RectF(0, 0, 300, heigth*2);
        canvas.drawArc(rectF2, -180, 90, true, paint);
        RectF rectF3 = new RectF(150, 0, width-150, heigth);
        canvas.drawRect(rectF3,paint);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        heigth = h;

    }
}
