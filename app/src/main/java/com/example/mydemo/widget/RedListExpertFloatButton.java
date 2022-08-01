package com.example.mydemo.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.OverScroller;
import android.widget.TextView;

import com.example.mydemo.R;

public class RedListExpertFloatButton extends ViewGroup {
    private OverScroller scroller;
    private int offset = 0;
    private int showWidth = 70;


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

        ImageView ivBg = new ImageView(getContext());
        ivBg.setAdjustViewBounds(true);
        ivBg.setImageResource(R.drawable.redlist_expert_float_button_bg);
        ivBg.setTag("ivBg");
        ivBg.setOnClickListener(new OnClickListener() {
            private boolean open;

            @Override
            public void onClick(View view) {
                if (open) {
                    open = false;
//                    ValueAnimator animator = ValueAnimator.ofInt(initDx + (width / 2), initDx);
//                    animator.setInterpolator(new LinearInterpolator());
//                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                        @Override
//                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                            scrollTo((Integer) valueAnimator.getAnimatedValue(), scroller.getCurrY());
//                        }
//                    });
//                    animator.start();


                    if (!scroller.isFinished())
                        scroller.abortAnimation();
                    scroller.startScroll(scroller.getFinalX(), scroller.getFinalY(), -offset, 0, 1000);
                } else {
                    open = true;
//                    ValueAnimator animator = ValueAnimator.ofInt(initDx, initDx + (width / 2));
//                    animator.setInterpolator(new LinearInterpolator());
//                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                        @Override
//                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                            scrollTo((Integer) valueAnimator.getAnimatedValue(), scroller.getCurrY());
//                        }
//                    });
//                    animator.start();


                    if (!scroller.isFinished())
                        scroller.abortAnimation();
                    scroller.startScroll(0, 0, offset, 0, 1000);
                }
                invalidate();
            }
        });

        TextView tvContent = new TextView(getContext());
        tvContent.setText("发布方案");
        tvContent.setTextColor(Color.WHITE);
        tvContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, 36);
        tvContent.setTag("tvContent");

        ImageView ivClose = new ImageView(getContext());
        ivClose.setImageResource(R.drawable.icon_float_close);
        ivClose.setTag("ivClose");

        addView(ivBg, new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//        addView(tvContent, new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//        addView(ivClose, new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
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
}
