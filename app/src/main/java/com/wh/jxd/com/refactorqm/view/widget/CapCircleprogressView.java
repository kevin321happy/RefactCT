package com.wh.jxd.com.refactorqm.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.utils.DisplayUtil;


/**
 * Created by kevin321vip on 2017/8/1.
 * 圆弧进度的控件
 */

public class CapCircleprogressView extends View {
    private int mMaxProgress = 100;//最大的进度值
    private int mCurrentProgress;//当前的进度值
    private int mOutWidth;
    private int mOutHeight;
    private int mWidth;
    private int mHeight;
    private Paint mCirclepaint;
    private Paint mProgresspaint;

    private int mRingWidth = 10;//圆环的宽度
    private int mTextSize = 24;//中间文字的大小
    private int mTextCOlor = Color.BLACK;//中间文字的颜色
    private int mProgressColor;//进度的颜色

    private int INVALIDATE = 1;
    private Point mCenterPoint = new Point();//中心点
    private int padding;
    /**
     * 进度透明度
     */
    private int mProgressAlpha = 255; // 255


    private Paint mTextpaint;
    private String mText;
    private RectF mProgressrect;
    private RectF mOutrectF;
    private RectF mInerrectF;
    private Context context;
    private Paint mTimeTextpaint;
    private RectF mOutrectF1;
    private String timeType;
    private Paint mProgressBgpaint;
    private boolean FINISH_DRAW_TEXT = false;//是否完成了文字的绘制
    private Canvas mCanvas;
    int[] mColors = new int[]{
            Color.parseColor("#EEAF55"), Color.parseColor("#FF95C700"), Color.parseColor("#FF8FC700"), Color.parseColor("#FF00C717"),
            Color.parseColor("#FF00C75D"), Color.parseColor("#00C799")};


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
        mRingWidth = typedArray.getDimensionPixelOffset(R.styleable.CapCircleprogressView_ring_Width, 20);
        mTextSize = typedArray.getDimensionPixelOffset(R.styleable.CapCircleprogressView_ceter_TextSize, 18);
        mTextCOlor = typedArray.getColor(R.styleable.CapCircleprogressView_ceter_Textcolor, Color.BLACK);
        mMaxProgress = typedArray.getInteger(R.styleable.CapCircleprogressView_max_Progress, 100);
        mProgressColor = typedArray.getColor(R.styleable.CapCircleprogressView_progress_Color, 0xFFDDDDDD);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mOutWidth = MeasureSpec.getSize(widthMeasureSpec);
        mOutHeight = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = mOutWidth - padding;
        mHeight = mOutHeight - padding;
        mWidth = Math.min(mWidth, mHeight);
        mCenterPoint.x = mOutWidth / 2;
        mCenterPoint.y = mOutHeight / 2;
        mProgressrect = new RectF(padding / 2 + mRingWidth / 2, padding / 2 + mRingWidth / 2, mOutWidth - padding / 2 - mRingWidth / 2, mOutHeight - padding / 2 - mRingWidth / 2);
        mOutrectF = new RectF(0, 0, mOutWidth, mOutHeight);
        mOutrectF1 = new RectF(0 + padding / 2, 0 + padding / 2, mOutWidth - padding / 2, mOutHeight - padding / 2);
        mInerrectF = new RectF(padding / 2 + mRingWidth, padding / 2 + mRingWidth, mOutWidth - padding / 2 - mRingWidth, mOutHeight - padding / 2 - mRingWidth);
    }

    /**
     * 初始化方法
     */
    private void init(Context context) {
        this.context = context;
        padding = DisplayUtil.dp2px(context, 10);
        //外圈圆
        mCirclepaint = new Paint();
        mCirclepaint.setAntiAlias(true);
        mCirclepaint.setColor(Color.GRAY);
        mCirclepaint.setStyle(Paint.Style.STROKE);
        //绘制圆弧进度的画笔
        mProgresspaint = new Paint();
        //设置颜色渐变的效果
        Shader shader = new LinearGradient(100, 100, 100, 300, mColors, null, Shader.TileMode.CLAMP);
        mProgresspaint.setShader(shader);
        mProgresspaint.setAlpha(mProgressAlpha);
        mProgresspaint.setAntiAlias(true);
        mProgresspaint.setStrokeWidth(mRingWidth);
        mProgresspaint.setStyle(Paint.Style.STROKE);
        //绘制进度的背景的画笔
        mProgressBgpaint = new Paint();
        mProgressBgpaint.setAntiAlias(true);
        mProgressBgpaint.setColor(Color.parseColor("#f5f5f5"));
        mProgressBgpaint.setStrokeWidth(mRingWidth);
        mProgressBgpaint.setStyle(Paint.Style.STROKE);
        //绘制文字的画笔
        mTextpaint = new Paint();
        mTextpaint.setAntiAlias(true);
        mTextpaint.setColor(mTextCOlor);
        mTextpaint.setTextSize(DisplayUtil.dp2px(context, mTextSize));
        mTextpaint.setStyle(Paint.Style.FILL);
        //绘制时间文字的画笔
        mTimeTextpaint = new Paint();
        mTimeTextpaint.setAntiAlias(true);
        mTimeTextpaint.setColor(mTextCOlor);
        mTimeTextpaint.setTextSize(DisplayUtil.dp2px(context, mTextSize / 2));
        mTimeTextpaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCenterText(canvas);
        drawOutCicle(canvas);
        drawProgress(canvas);
    }

    /**
     * 绘制中间的文字
     *
     * @param canvas
     */
    private void drawCenterText(Canvas canvas) {
        Rect text_rect = new Rect();
        Rect time_text_rect = new Rect();
        if (mText != null) {
            mTextpaint.getTextBounds(mText, 0, mText.length(), text_rect);
            mTimeTextpaint.getTextBounds(timeType, 0, timeType.length(), time_text_rect);
            canvas.drawText(mText, mCenterPoint.x - text_rect.width() / 2 - time_text_rect.width() / 2, mCenterPoint.y + text_rect.height() / 2, mTextpaint);
            canvas.drawText(timeType, mCenterPoint.x + text_rect.width() / 2 - time_text_rect.width() / 3, mCenterPoint.y + text_rect.height() / 2, mTimeTextpaint);
        }
    }

    /**
     * 绘制外圈园
     *
     * @param canvas
     */
    private void drawOutCicle(Canvas canvas) {
        canvas.drawArc(mOutrectF, 120, 300, false, mCirclepaint);
        canvas.drawArc(mOutrectF1, 120, 300, false, mCirclepaint);
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
        canvas.drawArc(mProgressrect, 120, 300, false, mProgressBgpaint);
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
     *
     * @param currentProgress
     */
    public void setCurrentProgress(int currentProgress) {
        mCurrentProgress = currentProgress;
        invalidate();

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

    public void setText(String text, String type) {
        this.timeType = type;
        mText = text;
        invalidate();
    }
}
