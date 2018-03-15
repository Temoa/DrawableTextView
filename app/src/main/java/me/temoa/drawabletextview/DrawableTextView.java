package me.temoa.drawabletextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by lai
 * on 2018/3/15.
 */

public class DrawableTextView extends AppCompatTextView {

    private int mStartWidth;
    private int mStartHeight;

    private int mEndWidth;
    private int mEndHeight;

    private int mTopWidth;
    private int mTopHeight;

    private int mBottomWidth;
    private int mBottomHeight;

    public DrawableTextView(Context context) {
        super(context);
    }

    public DrawableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DrawableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray ta = context.obtainStyledAttributes(attributeSet, R.styleable.DrawableTextView);
        mStartWidth = ta.getDimensionPixelOffset(R.styleable.DrawableTextView_dtv_start_width, 0);
        mStartHeight = ta.getDimensionPixelOffset(R.styleable.DrawableTextView_dtv_start_height, 0);

        mEndWidth = ta.getDimensionPixelOffset(R.styleable.DrawableTextView_dtv_end_width, 0);
        mEndHeight = ta.getDimensionPixelOffset(R.styleable.DrawableTextView_dtv_end_height, 0);

        mTopWidth = ta.getDimensionPixelOffset(R.styleable.DrawableTextView_dtv_top_width, 0);
        mTopHeight = ta.getDimensionPixelOffset(R.styleable.DrawableTextView_dtv_top_height, 0);

        mBottomWidth = ta.getDimensionPixelOffset(R.styleable.DrawableTextView_dtv_bottom_width, 0);
        mBottomHeight = ta.getDimensionPixelOffset(R.styleable.DrawableTextView_dtv_bottom_height, 0);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setDrawableSize();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void setDrawableSize() {
        Drawable[] drawables = getCompoundDrawables();

        for (int i = 0; i < drawables.length; i++) {
            Drawable drawable = drawables[i];
            if (drawable == null) continue;
            int drawableWidth = drawable.getIntrinsicWidth();
            int drawableHeight = drawable.getIntrinsicHeight();
            switch (i) {
                case 0:
                    drawable.setBounds(0, 0,
                            setExistValue(mStartWidth, drawableWidth),
                            setExistValue(mStartHeight, drawableHeight));
                    break;
                case 1:
                    drawable.setBounds(0, 0,
                            setExistValue(mTopWidth, drawableWidth),
                            setExistValue(mTopHeight, drawableHeight));
                    break;
                case 2:
                    drawable.setBounds(0, 0,
                            setExistValue(mEndWidth, drawableWidth),
                            setExistValue(mEndHeight, drawableHeight));
                    break;
                case 3:
                    drawable.setBounds(0, 0,
                            setExistValue(mBottomWidth, drawableWidth),
                            setExistValue(mBottomHeight, drawableHeight));
                    break;
            }
        }
        this.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    private int setExistValue(int arg1, int arg2) {
        if (arg1 != 0) {
            return arg1;
        } else {
            return arg2;
        }
    }
}
