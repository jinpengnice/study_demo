package com.example.mydemo.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.mydemo.R;

public class BgView extends View {
    private Bitmap bitmap;
    private Paint paint;
    private final RectF dstRectF = new RectF();
    private int width, height;

    public BgView(Context context) {
        this(context, null);
    }

    public BgView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BgView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.redlist_expert_float_button_bg);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        if (bitmap != null && !bitmap.isRecycled())
            canvas.drawBitmap(bitmap, null, dstRectF, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);

        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);

        if (wMode != MeasureSpec.EXACTLY) {
            wSize = 100;
        }

        if (hMode != MeasureSpec.EXACTLY) {
            hSize = 100;
        }

        this.width = wSize;
        this.height = hSize;

        dstRectF.set(20, 20, wSize, hSize);

//        super.onMeasure(wSize, hSize);
        setMeasuredDimension(wSize, hSize);

    }
}
