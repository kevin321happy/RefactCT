package com.wh.jxd.com.refactorqm.view.widget.waterwaveprogress;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.wh.jxd.com.refactorqm.utils.DisplayUtil;

import java.lang.ref.WeakReference;

/**
 * @author Administrator
 */
public class WaterWaveProgress extends View {
    // 水的画笔 // 画圆环的画笔// 进度百分比的画笔
    private Paint mPaintWater = null, mRingPaint = null, mTextPaint = null;

    // 圆环颜色 // 圆环背景颜色 // 当前进度 //水波颜色 // 水波背景色 //进度条和水波之间的距离 //进度百分比字体大小
    // //进度百分比字体颜色
    private int mRingColor, mRingBgColor, mWaterColor, mWaterBgColor,
            mFontSize, mTextColor;
    // 进度 //浪峰个数
    float crestCount = 1.5f;

    int mProgress = 0, mMaxProgress = 100;

    // 画布中心点
    private Point mCenterPoint;
    // 圆环宽度
    private float mRingWidth, mProgress2WaterWidth;
    // 是否显示进度条 //是否显示进度百分比
    private boolean mShowProgress = false, mShowNumerical = true;
//    Color.parseColor("#EEAF55"), Color.parseColor("#FF95C700"), Color.parseColor("#FF8FC700"),
    int[] mColors = new int[]{
             Color.parseColor("#FF00C717"), Color.parseColor("#FF00C75D"), Color.parseColor("#00C799")};
    /**
     * 产生波浪效果的因子
     */
    private long mWaveFactor = 0L;
    /**
     * 正在执行波浪动画
     */
    private boolean isWaving = false;
    /**
     * 振幅
     */
    private float mAmplitude = 30.0F; // 20F
    /**
     * 波浪的速度
     */
    private float mWaveSpeed = 0.050F; // 0.020F
    /**
     * 水的透明度
     */
    private int mWaterAlpha = 255; // 255
    WaterWaveAttrInit attrInit;

    private MyHandler mHandler = null;
    private Context context;
    private int mOutwidth;
    private int mOutheight;
    private Paint mText_paint1;

    private static class MyHandler extends Handler {
        private WeakReference<WaterWaveProgress> mWeakRef = null;

        private int refreshPeriod = 100;

        public MyHandler(WaterWaveProgress host) {
            mWeakRef = new WeakReference<WaterWaveProgress>(host);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mWeakRef.get() != null) {
                mWeakRef.get().invalidate();
                sendEmptyMessageDelayed(0, refreshPeriod);
            }
        }
    }

    public WaterWaveProgress(Context paramContext) {
        super(paramContext);
    }

    public WaterWaveProgress(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WaterWaveProgress(Context context, AttributeSet attrs,
                             int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        attrInit = new WaterWaveAttrInit(context, attrs, defStyleAttr);
        init(context);
    }

    @SuppressLint("NewApi")
    private void init(Context context) {
        this.context = context;
        mCenterPoint = new Point();
        mRingColor = attrInit.getProgressColor();
        mRingBgColor = attrInit.getProgressBgColor();
        mWaterColor = attrInit.getWaterWaveColor();
        mWaterBgColor = attrInit.getWaterWaveBgColor();
        mRingWidth = attrInit.getProgressWidth();
        mProgress2WaterWidth = attrInit.getProgress2WaterWidth();
        mShowProgress = attrInit.isShowProgress();
        mShowNumerical = attrInit.isShowNumerical();
        mFontSize = attrInit.getFontSize();
        mTextColor = attrInit.getTextColor();
        mProgress = attrInit.getProgress();
        mMaxProgress = attrInit.getMaxProgress();

        // 如果手机版本在4.0以上,则开启硬件加速
        if (VERSION.SDK_INT >= VERSION_CODES.ICE_CREAM_SANDWICH) {
            setLayerType(View.LAYER_TYPE_HARDWARE, null);
            // setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        mRingPaint = new Paint();
        mRingPaint.setAntiAlias(true);
        mRingPaint.setColor(mRingColor); // 圆环颜色
        mRingPaint.setStyle(Paint.Style.STROKE);

        mPaintWater = new Paint();
        mPaintWater.setStrokeWidth(1.0F);
        //设置颜色渐变的效果
        Shader shader = new LinearGradient(100, 100, 100, 300, mColors, null, Shader.TileMode.CLAMP);
        mPaintWater.setShader(shader);
        mPaintWater.setAlpha(mWaterAlpha);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextSize(DisplayUtil.dp2px(context,24));


        mText_paint1 = new Paint();
        mText_paint1.setAntiAlias(true);
        mText_paint1.setColor(mTextColor);
        mText_paint1.setStyle(Paint.Style.FILL);
        mText_paint1.setTextSize(DisplayUtil.dp2px(context,12));

        mHandler = new MyHandler(this);

    }

    public void animateWave() {
        if (!isWaving) {
            mWaveFactor = 0L;
            isWaving = true;
            mHandler.sendEmptyMessage(0);
        }
    }

    @SuppressLint({"DrawAllocation", "NewApi"})
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 获取整个View（容器）的宽、高
        int width = getWidth();
        int height = getHeight();
        width = height = (width < height) ? width : height;
        mAmplitude = width / 20f;//振幅

        mCenterPoint.x = width / 2;
        mCenterPoint.y = height / 2;
        { // 重新设置进度条的宽度和水波与进度条的距离,,至于为什么写在这,我脑袋抽了可以不
            mRingWidth = mRingWidth == 0 ? width / 20 : mRingWidth;
            //水波和进度条之间的距离
            mProgress2WaterWidth = mProgress2WaterWidth == 0 ? mRingWidth * 0.6f
                    : mProgress2WaterWidth;
            mRingPaint.setStrokeWidth(mRingWidth);
            mTextPaint.setTextSize(mFontSize == 0 ? width / 5 : mFontSize);
            if (VERSION.SDK_INT == VERSION_CODES.JELLY_BEAN) {
                setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            } else {
                setLayerType(View.LAYER_TYPE_HARDWARE, null);
            }
        }

        RectF oval = new RectF();
        oval.left = mRingWidth / 2;
        oval.top = mRingWidth / 2;
        oval.right = width - mRingWidth / 2;
        oval.bottom = width - mRingWidth / 2;
        //处于可视化编辑状态(防止窗口泄露)
        if (isInEditMode()) {
            mRingPaint.setColor(mRingBgColor);
            canvas.drawArc(oval, -90, 360, false, mRingPaint);
            mRingPaint.setColor(mRingColor);
            canvas.drawArc(oval, -90, 90, false, mRingPaint);
            canvas.drawCircle(mCenterPoint.x, mCenterPoint.y, mCenterPoint.x
                    - mRingWidth - mProgress2WaterWidth, mPaintWater);
            return;
        }
        // 如果没有执行波浪动画，或者也没有指定容器宽高，就画个简单的矩形
        if ((width == 0) || (height == 0) || isInEditMode()) {
            canvas.drawCircle(mCenterPoint.x, mCenterPoint.y, width / 2
                    - mProgress2WaterWidth - mRingWidth, mPaintWater);
            return;
        }
        float waterPadding = DisplayUtil.dp2px(context, 5);
        // 水最高处
        int waterHeightCount = mShowProgress ? (int) (height - waterPadding * 2)
                : height;
        // 重新生成波浪的形状(产生波浪的因子)
        mWaveFactor++;
        if (mWaveFactor >= Integer.MAX_VALUE) {
            mWaveFactor = 0L;
        }
        // 画进度条背景
        mRingPaint.setColor(mRingColor);
        mRingPaint.setStrokeWidth(1);
        // canvas.drawArc(oval, -90, 360, false, mRingPaint);
        // 和下面效果一样,只不过这个是画个360度的弧,下面是画圆环
        canvas.drawCircle(width / 2, width / 2, waterHeightCount / 2
                + waterPadding, mRingPaint);
//        canvas.drawCircle(width / 2, width / 2, waterHeightCount / 2
//                + waterPadding + DisplayUtil.dp2px(context, 10), mRingPaint);
        // 计算出水的高度
        float waterHeight = waterHeightCount * (1 - (mProgress * 1f) / mMaxProgress)
                + waterPadding;
        int staticHeight = (int) (waterHeight + mAmplitude);//加上振幅
        Path mPath = new Path();
        mPath.reset();
        if (mShowProgress) {
            //    画一个封闭的原型路径，我们使用Path类的addCircle方法
            //    cwj.addCircle(10,10,50,Direction.CW); //参数一为x轴水平位置，参数二为y轴垂直位置，第三个参数为圆形的半径，最后是绘制的方向，CW为顺时针方向，而CCW是逆时针方向。
            mPath.addCircle(mCenterPoint.x, mCenterPoint.y, waterHeightCount / 2,
                    Direction.CCW);
        } else {
            mPath.addCircle(mCenterPoint.x, mCenterPoint.y, waterHeightCount / 2,
                    Direction.CCW);
        }
        // canvas添加限制,让接下来的绘制都在园内
        canvas.clipPath(mPath, Region.Op.INTERSECT);
//        canvas.clipPath(mPath, Region.Op.REPLACE);
        Paint bgPaint = new Paint();
        bgPaint.setColor(mWaterBgColor);
        // 绘制背景
        canvas.drawRect(waterPadding, waterPadding, waterHeightCount
                + waterPadding, waterHeightCount + waterPadding, bgPaint);
        // 绘制静止的水(waterPadding水最高处的高度)
        canvas.drawRect(waterPadding, staticHeight, waterHeightCount
                + waterPadding, waterHeightCount + waterPadding, mPaintWater);
        // 待绘制的波浪线的x坐标
        int xToBeDrawed = (int) waterPadding;
        int waveHeight = (int) (waterHeight - mAmplitude
                * Math.sin(Math.PI
                * (2.0F * (xToBeDrawed + (mWaveFactor * width)
                * mWaveSpeed)) / width));
        // 波浪线新的高度
        int newWaveHeight = waveHeight;
        while (true) {
            if (xToBeDrawed >= waterHeightCount + waterPadding) {
                break;
            }
            // 根据当前x值计算波浪线新的高度
            newWaveHeight = (int) (waterHeight - mAmplitude
                    * Math.sin(Math.PI
                    * (crestCount * (xToBeDrawed + (mWaveFactor * waterHeightCount)
                    * mWaveSpeed)) / waterHeightCount));

            // 先画出梯形的顶边
            canvas.drawLine(xToBeDrawed, waveHeight, xToBeDrawed + 1,
                    newWaveHeight, mPaintWater);

            // 画出动态变化的柱子部分
            canvas.drawLine(xToBeDrawed, newWaveHeight, xToBeDrawed + 1,
                    staticHeight, mPaintWater);
            xToBeDrawed++;
            waveHeight = newWaveHeight;
        }
        if (mShowNumerical) {
            String progressTxt = String.format("%.0f", (mProgress * 1f) / mMaxProgress
                    * 100f)+"";
            Rect rect = new Rect();
            mTextPaint.getTextBounds(progressTxt, 0, progressTxt.length(), rect);
            float mTextWidth = rect.width();
            float mTextHeight = rect.height();
            float pre_textwidth = mText_paint1.measureText("%", 0, 1);
            canvas.drawText(progressTxt, mCenterPoint.x-mTextWidth/2-pre_textwidth/2,
                    mCenterPoint.y+mTextHeight/2, mTextPaint);
            canvas.drawText("%",mCenterPoint.x+mTextWidth/2,mCenterPoint.y+mTextHeight/2,mText_paint1);
            canvas.drawText("得分率",mCenterPoint.x-pre_textwidth*3/2,mCenterPoint.y+mTextHeight*3/2,mText_paint1);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = widthMeasureSpec;
        int height = heightMeasureSpec;
        width = height = (width < height) ? width : height;
        setMeasuredDimension(width, height);
    }

    /**
     * 设置波浪的振幅
     */
    public void setAmplitude(float amplitude) {
        mAmplitude = amplitude;
    }

    /**
     * 设置水的透明度
     *
     * @param alpha 透明的百分比，值为0到1之间的小数，越接近0越透明
     */
    public void setWaterAlpha(float alpha) {
        mWaterAlpha = (int) (255.0F * alpha);
        mPaintWater.setAlpha(mWaterAlpha);
    }

    /**
     * 设置水的颜色
     */
    public void setWaterColor(int color) {
        mWaterColor = color;
    }

    /**
     * 设置当前进度
     */
    public void setProgress(int progress) {
        progress = progress > 100 ? 100 : progress < 0 ? 0 : progress;
        mProgress = progress;
        invalidate();
    }

    /**
     * 获取进度 动画时会用到
     */
    public int getProgress() {
        return mProgress;
    }

    /**
     * 设置波浪速度
     */
    public void setWaveSpeed(float speed) {
        mWaveSpeed = speed;
    }

    /**
     * 是否显示进度条
     *
     * @param b
     */
    public void setShowProgress(boolean b) {
        mShowProgress = b;
    }

    /**
     * 是否显示进度值
     *
     * @param b
     */
    public void setShowNumerical(boolean b) {
        mShowNumerical = b;
    }

    /**
     * 设置进度条前景色
     *
     * @param mRingColor
     */
    public void setmRingColor(int mRingColor) {
        this.mRingColor = mRingColor;
    }

    /**
     * 设置进度条背景色
     *
     * @param mRingBgColor
     */
    public void setmRingBgColor(int mRingBgColor) {
        this.mRingBgColor = mRingBgColor;
    }

    /**
     * 设置水波颜色
     *
     * @param mWaterColor
     */
    public void setmWaterColor(int mWaterColor) {
        this.mWaterColor = mWaterColor;
    }

    /**
     * 设置水波背景色
     *
     * @param mWaterBgColor
     */
    public void setWaterBgColor(int mWaterBgColor) {
        this.mWaterBgColor = mWaterBgColor;
    }

    /**
     * 设置进度值显示字体大小
     *
     * @param mFontSize
     */
    public void setFontSize(int mFontSize) {
        this.mFontSize = mFontSize;
    }

    /**
     * 设置进度值显示字体颜色
     *
     * @param mTextColor
     */
    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
    }

    /**
     * 设置进度条最大值
     *
     * @param mMaxProgress
     */
    public void setMaxProgress(int mMaxProgress) {
        this.mMaxProgress = mMaxProgress;
    }

    /**
     * 设置浪峰个数
     *
     * @param crestCount
     */
    public void setCrestCount(float crestCount) {
        this.crestCount = crestCount;
    }

    /**
     * 设置进度条宽度
     *
     * @param mRingWidth
     */
    public void setRingWidth(float mRingWidth) {
        this.mRingWidth = mRingWidth;
    }

    /**
     * 设置水波到进度条之间的距离
     *
     * @param mProgress2WaterWidth
     */
    public void setProgress2WaterWidth(float mProgress2WaterWidth) {
        this.mProgress2WaterWidth = mProgress2WaterWidth;
    }

}