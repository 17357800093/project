package com.example.dwkyanglao.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;


import androidx.annotation.IntDef;

import com.example.dwkyanglao.AppApplication;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressLint("AppCompatCustomView")
public class MyTextView extends TextView {

    /**
     * 文本的颜色
     */
    private int mTextColor;

    private Rect mBound;
    private Paint mPaint;
    public static class TypefaceValue{
        public static final int Bold = 0;
        public static final int BoldItalic = 1;
        public static final int Heavy = 2;
    }

    @IntDef({TypefaceValue.Bold, TypefaceValue.BoldItalic, TypefaceValue.Heavy})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TypeFace {
    }

    public MyTextView(Context context) {
        this(context,null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(AppApplication.getInstance().getTypeface(), Typeface.BOLD);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);

    }


}
