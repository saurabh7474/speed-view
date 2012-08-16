/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.codesector.speedview.pro;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.Button;

// Referenced classes of package com.codesector.speedview.pro:
//            SpeedView

class SpeedometerView extends Button
{

    public SpeedometerView(Context context)
    {
        this(context, null);
    }

    public SpeedometerView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public SpeedometerView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mStart = 162F;
        mSpeedPointX = -130D;
        mSpeed = -1;
        mDataString = "";
        mSpeedoLimit = 160D;
        mScreenRatio = SpeedView.mScreenRatio;
        mAnalogSpeedoBg = ((BitmapDrawable)getResources().getDrawable(2130837507)).getBitmap();
        mOval = new RectF(63F * mScreenRatio, 47F * mScreenRatio, 252F * mScreenRatio, 238F * mScreenRatio);
        mOvalTop = new RectF(54F * mScreenRatio, 38F * mScreenRatio, 260F * mScreenRatio, 247F * mScreenRatio);
        mPaint = new Paint();
        mPaint.setARGB(255, 50, 50, 50);
        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setTextAlign(android.graphics.Paint.Align.CENTER);
        mPaintArc = new Paint();
        mPaintArc.setAntiAlias(true);
        mPaintArc.setStyle(android.graphics.Paint.Style.STROKE);
        mPaintArc.setStrokeWidth(6F * mScreenRatio);
        mPaintArcTop = new Paint();
        mPaintArcTop.setAntiAlias(true);
        mPaintArcTop.setARGB(50, 255, 255, 255);
        mPaintArcTop.setStyle(android.graphics.Paint.Style.STROKE);
        mPaintArcTop.setStrokeWidth(12F * mScreenRatio);
        mPathOvalTop = new RectF(0.0F * mScreenRatio, 20F * mScreenRatio, 640F * mScreenRatio, 440F * mScreenRatio);
        mPathOvalBtm = new RectF(0.0F * mScreenRatio, 80F * mScreenRatio, 560F * mScreenRatio, 400F * mScreenRatio);
        mPath = new Path();
        mPathSpeed = new Path();
        mPath.moveTo(35F * mScreenRatio, 162F * mScreenRatio);
        mPath.lineTo(31F * mScreenRatio, 162F * mScreenRatio);
        mPath.lineTo(22F * mScreenRatio, 155F * mScreenRatio);
        mPath.lineTo(22F * mScreenRatio, 151F * mScreenRatio);
        mPath.arcTo(mPathOvalTop, 208F, 48.5F);
        mPath.lineTo(300F * mScreenRatio, 80F * mScreenRatio);
        mPath.arcTo(mPathOvalBtm, 270F, -61F);
        mPathPaint = new Paint();
        mPathPaint.setAntiAlias(true);
        mPathPaint.setColor(-3355444);
        mPathPaint.setStyle(android.graphics.Paint.Style.STROKE);
        mPathPaint.setStrokeWidth(1.0F);
        mPathPaint.setStrokeJoin(android.graphics.Paint.Join.ROUND);
        mPathPaint.setStrokeCap(android.graphics.Paint.Cap.ROUND);
        mPathBgPaint = new Paint();
        mPathBgPaint.setAntiAlias(true);
        mPathBgPaint.setStyle(android.graphics.Paint.Style.FILL);
        mPathBgPaint.setColor(281857228);
        mPathFillPaint = new Paint();
        mPathFillPaint.setAntiAlias(true);
        mPathFillPaint.setStyle(android.graphics.Paint.Style.FILL);
        mPaintTextSpeed = new Paint();
        mPaintTextSpeed.setAntiAlias(true);
        mPaintTextSpeed.setTextSize(60F * mScreenRatio);
        mPaintTextSpeed.setTextAlign(android.graphics.Paint.Align.RIGHT);
        mPaintTextUnits = new Paint();
        mPaintTextUnits.setAntiAlias(true);
        mPaintTextUnits.setColor(-3355444);
        mPaintTextUnits.setTextSize(20F * mScreenRatio);
        mPaintTextUnits.setTextAlign(android.graphics.Paint.Align.RIGHT);
        mPaintTextData = new Paint();
        mPaintTextData.setAntiAlias(true);
        mPaintTextData.setColor(-3355444);
        mPaintTextData.setTextSize(20F * mScreenRatio);
        mPaintTextData.setTextAlign(android.graphics.Paint.Align.LEFT);
        setHeight(mAnalogSpeedoBg.getHeight());
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if(!SpeedView.mDigitSpeedoChecked) goto _L2; else goto _L1
_L1:
        canvas.drawPath(mPath, mPathBgPaint);
        Paint paint3 = mPathFillPaint;
        int k;
        Paint paint5;
        float f7;
        String s1;
        float f8;
        if(SpeedView.mCustomColorsChecked)
            k = SpeedView.mSpeedBarColor;
        else
            k = -16776961;
        paint3.setColor(k);
        if(SpeedView.mWarningChecked && mSpeed > mLimit)
        {
            mPaintTextSpeed.setColor(-65536);
        } else
        {
            Paint paint4 = mPaintTextSpeed;
            int l;
            if(SpeedView.mCustomColorsChecked)
                l = SpeedView.mPrimaryTextColor;
            else
                l = -1;
            paint4.setColor(l);
        }
        paint5 = mPaintTextSpeed;
        if(mSpeed >= 100)
            f7 = 54F * mScreenRatio;
        else
            f7 = 60F * mScreenRatio;
        paint5.setTextSize(f7);
        if(mSpeed != -1)
            s1 = (new StringBuilder()).append(mSpeed).toString();
        else
            s1 = "---";
        if(SpeedView.mDisplayUnits == 0)
            f8 = 240F * mScreenRatio;
        else
            f8 = 234F * mScreenRatio;
        canvas.drawText(s1, f8, 170F * mScreenRatio, mPaintTextSpeed);
        canvas.drawText((new StringBuilder()).append(SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits]).toString(), 294F * mScreenRatio, 170F * mScreenRatio, mPaintTextUnits);
        if(!mDataString.equals(""))
            canvas.drawText(mDataString, 27F * mScreenRatio, 35F * mScreenRatio, mPaintTextData);
        if(SpeedView.mMaxSpeedoChecked)
            mSpeedoLimit = SpeedView.mMaxSpeedoLimit;
        if(mSpeed != -1)
        {
            mSpeedPointX = -123D + 353D * ((double)mSpeed / mSpeedoLimit);
            canvas.save();
            mPathSpeed.reset();
            mPathSpeed.setLastPoint(0.0F, 200F * mScreenRatio);
            mPathSpeed.lineTo(0.0F, 10F * mScreenRatio);
            mPathSpeed.lineTo((float)(int)mSpeedPointX * mScreenRatio, 10F * mScreenRatio);
            mPathSpeed.lineTo((int)((200D + mSpeedPointX) * (double)mScreenRatio), 210F * mScreenRatio);
            mPathSpeed.lineTo(0.0F, 200F * mScreenRatio);
            try
            {
                canvas.clipPath(mPathSpeed);
            }
            catch(UnsupportedOperationException unsupportedoperationexception)
            {
                unsupportedoperationexception.printStackTrace();
            }
            canvas.drawPath(mPath, mPathFillPaint);
            canvas.restore();
        }
        canvas.drawPath(mPath, mPathPaint);
_L4:
        return;
_L2:
        canvas.drawBitmap(mAnalogSpeedoBg, 0.0F, 0.0F, mPaint);
        Paint paint = mPaintArc;
        int i;
        Paint paint2;
        float f;
        String s;
        RectF rectf4;
        RectF rectf5;
        if(SpeedView.mCustomColorsChecked)
            i = SpeedView.mSpeedBarColor;
        else
            i = -16776961;
        paint.setColor(i);
        if(SpeedView.mWarningChecked && mSpeed > mLimit)
        {
            mPaintText.setColor(-65536);
        } else
        {
            Paint paint1 = mPaintText;
            int j;
            if(SpeedView.mCustomColorsChecked)
                j = SpeedView.mPrimaryTextColor;
            else
                j = -1;
            paint1.setColor(j);
        }
        paint2 = mPaintText;
        if(mSpeed >= 100)
            f = 90F * mScreenRatio;
        else
            f = 100F * mScreenRatio;
        paint2.setTextSize(f);
        if(mSpeed != -1)
            s = (new StringBuilder()).append(mSpeed).toString();
        else
            s = "---";
        canvas.drawText(s, 160F * mScreenRatio, 170F * mScreenRatio, mPaintText);
        if(mSpeed == -1) goto _L4; else goto _L3
_L3:
        switch(SpeedView.mDisplayUnits)
        {
        case 0: // '\0'
            rectf4 = mOval;
            float f5;
            float f6;
            if(mSpeed < 160)
                f5 = (float)(1.3500000000000001D * (double)mSpeed);
            else
                f5 = 217F;
            canvas.drawArc(rectf4, 162F, f5, false, mPaintArc);
            rectf5 = mOvalTop;
            if((int)(2.2400000000000002D * (double)mMaxSpeed) < 160)
                f6 = (float)(1.3500000000000001D * (double)(int)(2.2400000000000002D * (double)mMaxSpeed));
            else
                f6 = 217F;
            canvas.drawArc(rectf5, 162F, f6, false, mPaintArcTop);
            break;

        case 1: // '\001'
            RectF rectf2 = mOval;
            float f3;
            RectF rectf3;
            float f4;
            if(mSpeed < 240)
                f3 = (float)(0.90000000000000002D * (double)mSpeed);
            else
                f3 = 217F;
            canvas.drawArc(rectf2, 162F, f3, false, mPaintArc);
            rectf3 = mOvalTop;
            if((int)(3.6000000000000001D * (double)mMaxSpeed) < 240)
                f4 = (float)(0.90000000000000002D * (double)(int)(3.6000000000000001D * (double)mMaxSpeed));
            else
                f4 = 217F;
            canvas.drawArc(rectf3, 162F, f4, false, mPaintArcTop);
            break;

        case 2: // '\002'
            RectF rectf = mOval;
            float f1;
            RectF rectf1;
            float f2;
            if(mSpeed < 60)
                f1 = (float)(3.6000000000000001D * (double)mSpeed);
            else
                f1 = 217F;
            canvas.drawArc(rectf, 162F, f1, false, mPaintArc);
            rectf1 = mOvalTop;
            if((int)(1.9438445D * (double)mMaxSpeed) < 60)
                f2 = (float)(3.6000000000000001D * (double)(int)(1.9438445D * (double)mMaxSpeed));
            else
                f2 = 217F;
            canvas.drawArc(rectf1, 162F, f2, false, mPaintArcTop);
            break;
        }
        if(true) goto _L4; else goto _L5
_L5:
    }

    void onSpeedChanged(int i)
    {
        mSpeed = i;
        invalidate();
    }

    void onSpeedChanged(int i, int j, float f)
    {
        mSpeed = i;
        mLimit = j;
        mMaxSpeed = f;
        invalidate();
    }

    void onSpeedChanged(int i, int j, float f, String s)
    {
        mSpeed = i;
        mLimit = j;
        mMaxSpeed = f;
        mDataString = s;
        invalidate();
    }

    void refreshView()
    {
        if(!SpeedView.mDigitAddlDataToggled) goto _L2; else goto _L1
_L1:
        SpeedView.mDigitDataSelected;
        JVM INSTR tableswitch 0 4: default 44
    //                   0 49
    //                   1 66
    //                   2 83
    //                   3 100
    //                   4 117;
           goto _L3 _L4 _L5 _L6 _L7 _L8
_L3:
        invalidate();
        return;
_L4:
        mDataString = getResources().getString(2131099853);
        continue; /* Loop/switch isn't completed */
_L5:
        mDataString = getResources().getString(2131099742);
        continue; /* Loop/switch isn't completed */
_L6:
        mDataString = getResources().getString(2131099745);
        continue; /* Loop/switch isn't completed */
_L7:
        mDataString = getResources().getString(2131099855);
        continue; /* Loop/switch isn't completed */
_L8:
        mDataString = getResources().getString(2131099856);
        continue; /* Loop/switch isn't completed */
_L2:
        mDataString = "";
        if(true) goto _L3; else goto _L9
_L9:
    }

    void setDisplayUnits(int i)
    {
        i;
        JVM INSTR tableswitch 0 2: default 28
    //                   0 33
    //                   1 62
    //                   2 92;
           goto _L1 _L2 _L3 _L4
_L1:
        invalidate();
        return;
_L2:
        mAnalogSpeedoBg = ((BitmapDrawable)getResources().getDrawable(2130837507)).getBitmap();
        mSpeedoLimit = 160D;
        continue; /* Loop/switch isn't completed */
_L3:
        mAnalogSpeedoBg = ((BitmapDrawable)getResources().getDrawable(2130837505)).getBitmap();
        mSpeedoLimit = 240D;
        continue; /* Loop/switch isn't completed */
_L4:
        mAnalogSpeedoBg = ((BitmapDrawable)getResources().getDrawable(2130837506)).getBitmap();
        mSpeedoLimit = 60D;
        if(true) goto _L1; else goto _L5
_L5:
    }

    private Bitmap mAnalogSpeedoBg;
    private String mDataString;
    private int mLimit;
    private float mMaxSpeed;
    private RectF mOval;
    private RectF mOvalTop;
    private Paint mPaint;
    private Paint mPaintArc;
    private Paint mPaintArcTop;
    private Paint mPaintText;
    private Paint mPaintTextData;
    private Paint mPaintTextSpeed;
    private Paint mPaintTextUnits;
    private Path mPath;
    private Paint mPathBgPaint;
    private Paint mPathFillPaint;
    private RectF mPathOvalBtm;
    private RectF mPathOvalTop;
    private Paint mPathPaint;
    private Path mPathSpeed;
    private float mScreenRatio;
    private int mSpeed;
    private double mSpeedPointX;
    private double mSpeedoLimit;
    private final float mStart;
}


/*
	DECOMPILATION REPORT

	Decompiled from: H:\AndroidProject\SpeedGps\libs\GPS测速.jar
	Total time: 24 ms
	Jad reported messages/errors:
Couldn't fully decompile method onDraw
Couldn't fully decompile method refreshView
Couldn't fully decompile method setDisplayUnits
	Exit status: 0
	Caught exceptions:
*/