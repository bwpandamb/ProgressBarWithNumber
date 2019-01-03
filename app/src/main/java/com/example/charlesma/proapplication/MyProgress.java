package com.example.charlesma.proapplication;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

public class MyProgress extends ProgressBar {

    private int mRealWidth;
    private Paint mTextPaint = new Paint();
    Rect textRect = new Rect();

    public MyProgress(Context context) {
        this(context, null);
    }

    public MyProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setProgressDrawable(ContextCompat.getDrawable(context, R.drawable.pro_bg_blue)); //设置这种主题的Progressbar
        init();
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
        mRealWidth = getMeasuredWidth() - getPaddingRight() - getPaddingLeft();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    private void init() {
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(30);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float radio = getProgress() * 1.0f / getMax();
        float progressPosX = (int) (mRealWidth * radio); //当前的进度

        String ProgressText = String.valueOf(getProgress()) + "%";
        mTextPaint.getTextBounds(ProgressText, 0, ProgressText.length(), textRect);
        int width = textRect.width();//文本的宽度
        int height = textRect.height();//文本的高度

        if ((progressPosX + width * 1.5f + getPaddingLeft() + getPaddingRight()) < getWidth()) {
            canvas.drawText(ProgressText, progressPosX + width * 0.5f + getPaddingLeft(), (getHeight() + height) / 2, mTextPaint);
        } else {
            canvas.drawText(ProgressText, progressPosX - width * 1.5f + getPaddingLeft(), (getHeight() + height) / 2, mTextPaint);
        }

        Log.e("charles", "progressPosX: " + progressPosX);
        Log.e("charles", "getPaddingRight(): " + getPaddingRight());
        Log.e("charles", "getPaddingLeft(): " + getPaddingLeft());
        Log.e("charles", "getWidth(): " + getWidth());
        Log.e("charles", "all(): " + ((progressPosX + width * 1.5f + getPaddingLeft() + getPaddingRight())));
    }


}