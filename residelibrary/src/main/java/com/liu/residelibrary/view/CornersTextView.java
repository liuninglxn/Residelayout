package com.liu.residelibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;

import androidx.appcompat.widget.AppCompatTextView;

import com.liu.residelibrary.R;

public class CornersTextView extends AppCompatTextView {

    private Path mPath;

    private Paint mPaint;

    // corners array
    private float[] mCorners;

    public CornersTextView(Context context) {
        this(context, null);
    }

    public CornersTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CornersTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mPath = new Path();
        // set odd mode
        mPath.setFillType(Path.FillType.INVERSE_EVEN_ODD);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CornersGifView);
        int mCornerSize = (int) ta.getDimension(R.styleable.CornersGifView_cornerSize, 0);
        int mLeftBottomCorner = (int) ta.getDimension(R.styleable.CornersGifView_leftBottomCorner, 0);
        int mLeftTopCorner = (int) ta.getDimension(R.styleable.CornersGifView_leftTopCorner, 0);
        int mRightBottomCorner = (int) ta.getDimension(R.styleable.CornersGifView_rightBottomCorner, 0);
        int mRightTopCorner = (int) ta.getDimension(R.styleable.CornersGifView_rightTopCorner, 0);
        ta.recycle();

        if (mCornerSize == 0) {
            mCorners = new float[]{
                    mLeftTopCorner, mLeftTopCorner,
                    mRightTopCorner, mRightTopCorner,
                    mRightBottomCorner, mRightBottomCorner,
                    mLeftBottomCorner, mLeftBottomCorner};
        } else {
            mCorners = new float[]{
                    mCornerSize, mCornerSize,
                    mCornerSize, mCornerSize,
                    mCornerSize, mCornerSize,
                    mCornerSize, mCornerSize};
        }
    }

    /**
     * @param vp parent view
     * @return paint color
     */
    private int getPaintColor(ViewParent vp) {
        if (null == vp) {
            return Color.TRANSPARENT;
        }

        if (vp instanceof View) {
            View parentView = (View) vp;
            getPaintColor(parentView.getParent());
        }
        return Color.TRANSPARENT;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        initPaintColor();
        addRoundRectPath(w, h);
    }

    private void initPaintColor() {
        int paintColor = getPaintColor(getParent());
        if (Color.TRANSPARENT == paintColor) {
            // get theme background color
            TypedArray array = getContext().getTheme().obtainStyledAttributes(new int[]{
                    android.R.attr.colorBackground
            });
            paintColor = array.getColor(0, Color.TRANSPARENT);
            array.recycle();
        }

        mPaint.setColor(paintColor);
    }

    private void addRoundRectPath(int w, int h) {
        mPath.reset();

        mPath.addRoundRect(new RectF(0, 0, w, h), mCorners, Path.Direction.CCW);

    }

    public void setCornerSize(int leftTop, int leftBottom, int rightTop, int rightBottom) {
        mCorners = new float[]{
                leftTop, leftTop,
                rightTop, rightTop,
                rightBottom, rightBottom,
                leftBottom, leftBottom};
        addRoundRectPath(getWidth(), getHeight());
        invalidate();
    }
}
