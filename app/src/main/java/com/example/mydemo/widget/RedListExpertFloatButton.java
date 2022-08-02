package com.example.mydemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.OverScroller;

/**
 * 悬浮吸附按钮，支持自定义内容
 */
public class RedListExpertFloatButton extends ViewGroup {
    private OverScroller scroller;
    private int offset = 0;         // 计算的需要偏移量
    private int showWidth = 70;     // 初始显示的大小
    private int duration = 1000;    // 动画需要的时间
    private boolean isOpen = false;

    public RedListExpertFloatButton(Context context) {
        this(context, null);
    }

    public RedListExpertFloatButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RedListExpertFloatButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.scroller = new OverScroller(getContext());
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wModel = MeasureSpec.getMode(widthMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hModel = MeasureSpec.getMode(heightMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int width = 0;
        int height = 0;

        int cCount = getChildCount();
        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;

        // 测量子 View, 取最大宽高
        for (int i = 0; i < cCount; i++) {
            View childView = getChildAt(i);
            cParams = (MarginLayoutParams) childView.getLayoutParams();

            cWidth = childView.getMeasuredWidth() + cParams.leftMargin + cParams.rightMargin;
            cHeight = childView.getMeasuredHeight() + cParams.topMargin + cParams.bottomMargin;

            width = Math.max(width, cWidth);
            height = Math.max(height, cHeight);
        }

        offset = width - showWidth;
        setMeasuredDimension((wModel == MeasureSpec.EXACTLY) ? wSize : width,
                (hModel == MeasureSpec.EXACTLY) ? hSize : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int cCount = getChildCount();
        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;

        int cl = 0, ct = 0, cr = 0, cb = 0;

        for (int i = 0; i < cCount; i++) {
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            cParams = (MarginLayoutParams) childView.getLayoutParams();

            cl = getWidth() - cWidth - cParams.leftMargin - cParams.rightMargin + offset;
            ct = getHeight() - cHeight - cParams.topMargin - cParams.bottomMargin;
            cr = cl + cWidth;
            cb = ct + cHeight;

            // 所有子 view 以右下排列摆放, 可根据需要扩展
            childView.layout(cl, ct, cr, cb);
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }

    /**
     * 打开
     */
    public void open() {
        if (scroller.isFinished()) {
            isOpen = true;
            scroller.startScroll(0, 0, offset, 0, duration);
            invalidate();
        }
    }

    /**
     * 关闭
     */
    public void close() {
        if (scroller.isFinished()) {
            isOpen = false;
            scroller.startScroll(scroller.getFinalX(), scroller.getFinalY(), -offset, 0, duration);
            invalidate();
        }
    }

    /**
     * 是否打开中
     *
     * @return ture: 展开中， false: 隐藏中
     */
    public boolean isOpening() {
        return isOpen;
    }

    /**
     * 是否运行中
     *
     * @return bool
     */
    public boolean isRunning() {
        return !scroller.isFinished();
    }

    /**
     * 设置位移动画需要的时间
     *
     * @param duration duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * 设置初始显示的大小
     *
     * @param px px
     */
    public void setShowWidth(int px) {
        this.showWidth = px;
    }
}
