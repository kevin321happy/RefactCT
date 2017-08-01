package com.vip.kevin321.gapcircleprogress.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.vip.kevin321.gapcircleprogress.R;

/**
 * Created by kevin321vip on 2017/8/1.
 * 圆弧进度的控件
 */

public class CapCircleprogressView extends View {

    private int mMaxProgress = 100;//最大的进度值
    private int mCurrentProgress;//当前的进度值
    private int mWidth;
    private int mHeight;

    private Paint mCirclepaint;
    private Paint mProgresspaint;

    private int mRingWidth = 20;//圆环的宽度
    private int mTextSize = 18;//中间文字的大小
    private int mTextCOlor = Color.BLACK;//中间文字的颜色
    private int mProgressColor;//进度的颜色

    private int INVALIDATE = 1;
    private Point mCenterPoint = new Point();//中心点


    private Paint mTextpaint;
    private String mText;
    private RectF mProgressrect;
    private RectF mOutrectF;
    private RectF mInerrectF;


    public CapCircleprogressView(Context context) {
        this(context, null);
        invalidate();
    }

    public CapCircleprogressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CapCircleprogressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CapCircleprogressView, defStyleAttr, 0);
        mRingWidth = typedArray.getDimensionPixelOffset(R.styleable.CapCircleprogressView_ringWidth, 20);
        mTextSize = typedArray.getDimensionPixelOffset(R.styleable.CapCircleprogressView_ceterTextSize, 18);
        mTextCOlor = typedArray.getColor(R.styleable.CapCircleprogressView_ceterTextcolor, Color.BLACK);
        mMaxProgress = typedArray.getInteger(R.styleable.CapCircleprogressView_maxProgress, 100);
        mProgressColor = typedArray.getColor(R.styleable.CapCircleprogressView_progressColor, 0xFFDDDDDD);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = Math.min(mWidth, mHeight);
        mCenterPoint.x = mWidth / 2;
        mCenterPoint.y = mWidth / 2;
        mProgressrect = new RectF(0 + mRingWidth / 2, mRingWidth / 2, mWidth - mRingWidth / 2, mHeight - mRingWidth / 2);
        mOutrectF = new RectF(0, 0, mWidth, mHeight);
        mInerrectF = new RectF(0 + mRingWidth, 0 + mRingWidth, mWidth - mRingWidth, mHeight - mRingWidth);
    }

    /**
     * 初始化方法
     */
    private void init(Context context) {
        //外圈圆
        mCirclepaint = new Paint();
        mCirclepaint.setAntiAlias(true);
        mCirclepaint.setColor(Color.GRAY);
        mCirclepaint.setStyle(Paint.Style.STROKE);
        //绘制圆弧进度的画笔
        mProgresspaint = new Paint();
        mProgresspaint.setAntiAlias(true);
        mProgresspaint.setColor(mProgressColor);
        mProgresspaint.setStrokeWidth(mRingWidth);
        mProgresspaint.setStyle(Paint.Style.STROKE);
        //绘制文字的画笔
        mTextpaint = new Paint();
        mTextpaint.setAntiAlias(true);
        mTextpaint.setColor(mTextCOlor);
        mTextpaint.setTextSize(WidgetUtil.Dp2Px(context, mTextSize));
        mTextpaint.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawOutCicle(canvas);
        drawInCircle(canvas);
        drawProgress(canvas);
        drawCenterText(canvas);
    }


    /**
     * 绘制中间的文字
     *
     * @param canvas
     */
    private void drawCenterText(Canvas canvas) {
        Rect text_rect = new Rect();
        if (mCurrentProgress != 0) {
            mText = String.valueOf(mCurrentProgress) + "名";
            mTextpaint.getTextBounds(mText, 0, mText.length(), text_rect);
            canvas.drawText(mText, mCenterPoint.x - text_rect.width() / 2, mCenterPoint.y + text_rect.height() / 2, mTextpaint);
        }
    }

    /**
     * 绘制外圈园
     *
     * @param canvas
     */
    private void drawOutCicle(Canvas canvas) {
        canvas.drawArc(mOutrectF, 120, 300, false, mCirclepaint);
    }

    /**
     * 绘制内圈圆
     *
     * @param canvas
     */
    private void drawInCircle(Canvas canvas) {
        canvas.drawArc(mInerrectF, 120, 300, false, mCirclepaint);
    }

    /**
     * 绘制圆弧的进度
     *
     * @param canvas
     */
    private void drawProgress(Canvas canvas) {
        float sweepAngle = (float) (mCurrentProgress * 300.0 / 100);
        canvas.drawArc(mProgressrect, 120, sweepAngle, false, mProgresspaint);
    }


    public int getMaxProgress() {
        return mMaxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        mMaxProgress = maxProgress;
    }

    public int getCurrentProgress() {
        return mCurrentProgress;
    }

    /**
     * 约定绘制结束状态设置-1，避免一直绘制
     * @param currentProgress
     */
    public void setCurrentProgress(int currentProgress) {
        mCurrentProgress = currentProgress;
        if (currentProgress != -1) {
            invalidate();
        }
    }

    public int getRingWidth() {
        return mRingWidth;
    }

    public void setRingWidth(int ringWidth) {
        this.mRingWidth = ringWidth;
        invalidate();
    }

    public void setTextSize(int textSize) {
        mTextSize = textSize;
        invalidate();
    }

    public void setTextCOlor(int textCOlor) {
        mTextCOlor = textCOlor;
        invalidate();
    }

    public void setProgressColor(int progressColor) {
        mProgressColor = progressColor;
        invalidate();
    }
}
