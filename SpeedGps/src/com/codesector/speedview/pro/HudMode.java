/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.codesector.speedview.pro;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

// Referenced classes of package com.codesector.speedview.pro:
//            SpeedView

class HudMode extends View
{

    public HudMode(Context context)
    {
        this(context, null);
    }

    public HudMode(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public HudMode(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mSpeed = -1;
        mDistance = getResources().getString(2131099744);
        mHeading = getResources().getString(2131099749);
        mElevation = getResources().getString(2131099745);
        mScreenRatio = SpeedView.mScreenRatio;
        mPaint = new Paint();
        mPaint.setARGB(255, 50, 50, 50);
        mPaintText = new Paint();
        mPaintText.setTextAlign(android.graphics.Paint.Align.CENTER);
        mPaintText.setAntiAlias(true);
        mPaintTextOdom = new Paint();
        mPaintTextOdom.setTextAlign(android.graphics.Paint.Align.CENTER);
        mPaintTextOdom.setTextSize(48F * mScreenRatio);
        mPaintTextOdom.setAntiAlias(true);
        mPaintTextMisc = new Paint();
        mPaintTextMisc.setTextAlign(android.graphics.Paint.Align.CENTER);
        mPaintTextMisc.setTextSize(36F * mScreenRatio);
        mPaintTextMisc.setAntiAlias(true);
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Paint paint1;
        int j;
        Paint paint2;
        int k;
        int l;
        if(mSpeed >= 100)
        {
            if(SpeedView.mStoredOrientation == 0)
                mPaintText.setTextSize(180F * mScreenRatio);
            else
                mPaintText.setTextSize(160F * mScreenRatio);
        } else
        {
            mPaintText.setTextSize(200F * mScreenRatio);
        }
        if(SpeedView.mWarningChecked && mSpeed > mLimit)
        {
            mPaintText.setColor(-65536);
        } else
        {
            Paint paint = mPaintText;
            int i;
            if(SpeedView.mCustomColorsChecked)
                i = SpeedView.mPrimaryTextColor;
            else
                i = -1;
            paint.setColor(i);
        }
        paint1 = mPaintTextOdom;
        if(SpeedView.mCustomColorsChecked)
            j = SpeedView.mSecondaryTextColor;
        else
            j = -3355444;
        paint1.setColor(j);
        paint2 = mPaintTextMisc;
        if(SpeedView.mCustomColorsChecked)
            k = SpeedView.mSecondaryTextColor;
        else
            k = -3355444;
        paint2.setColor(k);
        l = (int)(-mPaintText.ascent() - mPaintText.descent());
        if(!SpeedView.mIsTablet)
        {
            if(SpeedView.mUseHudChecked)
            {
                canvas.rotate(180F, canvas.getWidth() / 2, canvas.getHeight() / 2);
                canvas.scale(-1F, 1.0F);
                if(SpeedView.mAdvancedHudChecked)
                {
                    if(SpeedView.mStoredOrientation == 0)
                    {
                        String s32;
                        float f13;
                        float f14;
                        float f15;
                        float f16;
                        String s33;
                        String s34;
                        String s35;
                        if(mSpeed != -1)
                            s32 = (new StringBuilder()).append(mSpeed).toString();
                        else
                            s32 = "---";
                        f13 = -(canvas.getWidth() / 2);
                        f14 = canvas.getHeight();
                        f15 = l + canvas.getHeight();
                        if(SpeedView.mFullScreenChecked)
                            f16 = 1.85F;
                        else
                            f16 = 1.95F;
                        canvas.drawText(s32, f13, f14 - f15 / f16, mPaintText);
                        if(mSpeed != -1)
                            s33 = mDistance;
                        else
                            s33 = getResources().getString(2131099744);
                        canvas.drawText(s33, -(canvas.getWidth() / 2), 0.55F * (float)canvas.getHeight(), mPaintTextOdom);
                        if(mSpeed != -1)
                            s34 = mHeading;
                        else
                            s34 = getResources().getString(2131099749);
                        canvas.drawText(s34, -(canvas.getWidth() / 2), 0.75F * (float)canvas.getHeight(), mPaintTextMisc);
                        if(mSpeed != -1)
                            s35 = mElevation;
                        else
                            s35 = getResources().getString(2131099745);
                        canvas.drawText(s35, -(canvas.getWidth() / 2), 0.95F * (float)canvas.getHeight(), mPaintTextMisc);
                    } else
                    {
                        String s28;
                        String s29;
                        String s30;
                        String s31;
                        if(mSpeed != -1)
                            s28 = (new StringBuilder()).append(mSpeed).toString();
                        else
                            s28 = "---";
                        canvas.drawText(s28, -((float)canvas.getWidth() - (float)canvas.getWidth() / 3.7F), canvas.getHeight() - (canvas.getHeight() - l) / 2, mPaintText);
                        if(mSpeed != -1)
                            s29 = mDistance;
                        else
                            s29 = getResources().getString(2131099744);
                        canvas.drawText(s29, -((float)canvas.getWidth() / 3.9F), 0.25F * (float)canvas.getHeight(), mPaintTextOdom);
                        if(mSpeed != -1)
                            s30 = mHeading;
                        else
                            s30 = getResources().getString(2131099749);
                        canvas.drawText(s30, -((float)canvas.getWidth() / 3.9F), 0.55F * (float)canvas.getHeight(), mPaintTextMisc);
                        if(mSpeed != -1)
                            s31 = mElevation;
                        else
                            s31 = getResources().getString(2131099745);
                        canvas.drawText(s31, -((float)canvas.getWidth() / 3.9F), 0.85F * (float)canvas.getHeight(), mPaintTextMisc);
                    }
                } else
                {
                    String s27;
                    if(mSpeed != -1)
                        s27 = (new StringBuilder()).append(mSpeed).toString();
                    else
                        s27 = "---";
                    canvas.drawText(s27, -(canvas.getWidth() / 2), canvas.getHeight() - (canvas.getHeight() - l) / 2, mPaintText);
                }
            } else
            if(SpeedView.mAdvancedZoomChecked)
            {
                if(SpeedView.mStoredOrientation == 0)
                {
                    String s23;
                    float f;
                    float f1;
                    float f2;
                    float f3;
                    String s24;
                    float f4;
                    float f5;
                    float f6;
                    String s25;
                    float f7;
                    float f8;
                    float f9;
                    String s26;
                    float f10;
                    float f11;
                    float f12;
                    if(mSpeed != -1)
                        s23 = (new StringBuilder()).append(mSpeed).toString();
                    else
                        s23 = "---";
                    f = canvas.getWidth() / 2;
                    f1 = canvas.getHeight();
                    f2 = l + canvas.getHeight();
                    if(SpeedView.mFullScreenChecked)
                        f3 = 1.9F;
                    else
                        f3 = 1.85F;
                    canvas.drawText(s23, f, f1 - f2 / f3, mPaintText);
                    if(mSpeed != -1)
                        s24 = mDistance;
                    else
                        s24 = getResources().getString(2131099744);
                    f4 = canvas.getWidth() / 2;
                    f5 = canvas.getHeight();
                    if(SpeedView.mFullScreenChecked)
                        f6 = 0.55F;
                    else
                        f6 = 0.5F;
                    canvas.drawText(s24, f4, f6 * f5, mPaintTextOdom);
                    if(mSpeed != -1)
                        s25 = mHeading;
                    else
                        s25 = getResources().getString(2131099749);
                    f7 = canvas.getWidth() / 2;
                    f8 = canvas.getHeight();
                    if(SpeedView.mFullScreenChecked)
                        f9 = 0.75F;
                    else
                        f9 = 0.7F;
                    canvas.drawText(s25, f7, f9 * f8, mPaintTextMisc);
                    if(mSpeed != -1)
                        s26 = mElevation;
                    else
                        s26 = getResources().getString(2131099745);
                    f10 = canvas.getWidth() / 2;
                    f11 = canvas.getHeight();
                    if(SpeedView.mFullScreenChecked)
                        f12 = 0.95F;
                    else
                        f12 = 0.9F;
                    canvas.drawText(s26, f10, f12 * f11, mPaintTextMisc);
                } else
                {
                    String s19;
                    String s20;
                    String s21;
                    String s22;
                    if(mSpeed != -1)
                        s19 = (new StringBuilder()).append(mSpeed).toString();
                    else
                        s19 = "---";
                    canvas.drawText(s19, (float)canvas.getWidth() - (float)canvas.getWidth() / 3.5F, canvas.getHeight() - (canvas.getHeight() - l) / 2, mPaintText);
                    if(mSpeed != -1)
                        s20 = mDistance;
                    else
                        s20 = getResources().getString(2131099744);
                    canvas.drawText(s20, (float)canvas.getWidth() / 4F, 0.25F * (float)canvas.getHeight(), mPaintTextOdom);
                    if(mSpeed != -1)
                        s21 = mHeading;
                    else
                        s21 = getResources().getString(2131099749);
                    canvas.drawText(s21, (float)canvas.getWidth() / 4F, 0.55F * (float)canvas.getHeight(), mPaintTextMisc);
                    if(mSpeed != -1)
                        s22 = mElevation;
                    else
                        s22 = getResources().getString(2131099745);
                    canvas.drawText(s22, (float)canvas.getWidth() / 4F, 0.85F * (float)canvas.getHeight(), mPaintTextMisc);
                }
            } else
            {
                String s18;
                if(mSpeed != -1)
                    s18 = (new StringBuilder()).append(mSpeed).toString();
                else
                    s18 = "---";
                canvas.drawText(s18, canvas.getWidth() / 2, canvas.getHeight() - (canvas.getHeight() - l) / 2, mPaintText);
            }
        } else
        if(SpeedView.mUseHudChecked)
        {
            canvas.rotate(180F, canvas.getWidth() / 2, canvas.getHeight() / 2);
            canvas.scale(-1F, 1.0F);
            if(SpeedView.mAdvancedHudChecked)
            {
                if(SpeedView.mStoredOrientation == 0 || SpeedView.mStoredOrientation == 2)
                {
                    String s10;
                    String s11;
                    String s12;
                    String s13;
                    if(mSpeed != -1)
                        s10 = (new StringBuilder()).append(mSpeed).toString();
                    else
                        s10 = "---";
                    canvas.drawText(s10, -((float)canvas.getWidth() - (float)canvas.getWidth() / 3.5F), canvas.getHeight() - (canvas.getHeight() - l) / 2, mPaintText);
                    if(mSpeed != -1)
                        s11 = mDistance;
                    else
                        s11 = getResources().getString(2131099744);
                    canvas.drawText(s11, -((float)canvas.getWidth() / 3.8F), 0.25F * (float)canvas.getHeight(), mPaintTextOdom);
                    if(mSpeed != -1)
                        s12 = mHeading;
                    else
                        s12 = getResources().getString(2131099749);
                    canvas.drawText(s12, -((float)canvas.getWidth() / 3.8F), 0.55F * (float)canvas.getHeight(), mPaintTextMisc);
                    if(mSpeed != -1)
                        s13 = mElevation;
                    else
                        s13 = getResources().getString(2131099745);
                    canvas.drawText(s13, -((float)canvas.getWidth() / 3.8F), 0.85F * (float)canvas.getHeight(), mPaintTextMisc);
                } else
                {
                    String s14;
                    String s15;
                    String s16;
                    String s17;
                    if(mSpeed != -1)
                        s14 = (new StringBuilder()).append(mSpeed).toString();
                    else
                        s14 = "---";
                    canvas.drawText(s14, -(canvas.getWidth() / 2), (float)canvas.getHeight() - (float)(l + canvas.getHeight()) / 1.6F, mPaintText);
                    if(mSpeed != -1)
                        s15 = mDistance;
                    else
                        s15 = getResources().getString(2131099744);
                    canvas.drawText(s15, -(canvas.getWidth() / 2), 0.5F * (float)canvas.getHeight(), mPaintTextOdom);
                    if(mSpeed != -1)
                        s16 = mHeading;
                    else
                        s16 = getResources().getString(2131099749);
                    canvas.drawText(s16, -(canvas.getWidth() / 2), 0.7F * (float)canvas.getHeight(), mPaintTextMisc);
                    if(mSpeed != -1)
                        s17 = mElevation;
                    else
                        s17 = getResources().getString(2131099745);
                    canvas.drawText(s17, -(canvas.getWidth() / 2), 0.9F * (float)canvas.getHeight(), mPaintTextMisc);
                }
            } else
            {
                String s9;
                if(mSpeed != -1)
                    s9 = (new StringBuilder()).append(mSpeed).toString();
                else
                    s9 = "---";
                canvas.drawText(s9, -(canvas.getWidth() / 2), canvas.getHeight() - (canvas.getHeight() - l) / 2, mPaintText);
            }
        } else
        if(SpeedView.mAdvancedZoomChecked)
        {
            if(SpeedView.mStoredOrientation == 0 || SpeedView.mStoredOrientation == 2)
            {
                String s1;
                String s2;
                String s3;
                String s4;
                if(mSpeed != -1)
                    s1 = (new StringBuilder()).append(mSpeed).toString();
                else
                    s1 = "---";
                canvas.drawText(s1, (float)canvas.getWidth() - (float)canvas.getWidth() / 3.4F, canvas.getHeight() - (canvas.getHeight() - l) / 2, mPaintText);
                if(mSpeed != -1)
                    s2 = mDistance;
                else
                    s2 = getResources().getString(2131099744);
                canvas.drawText(s2, (float)canvas.getWidth() / 3.8F, 0.2F * (float)canvas.getHeight(), mPaintTextOdom);
                if(mSpeed != -1)
                    s3 = mHeading;
                else
                    s3 = getResources().getString(2131099749);
                canvas.drawText(s3, (float)canvas.getWidth() / 3.8F, 0.5F * (float)canvas.getHeight(), mPaintTextMisc);
                if(mSpeed != -1)
                    s4 = mElevation;
                else
                    s4 = getResources().getString(2131099745);
                canvas.drawText(s4, (float)canvas.getWidth() / 3.8F, 0.8F * (float)canvas.getHeight(), mPaintTextMisc);
            } else
            {
                String s5;
                String s6;
                String s7;
                String s8;
                if(mSpeed != -1)
                    s5 = (new StringBuilder()).append(mSpeed).toString();
                else
                    s5 = "---";
                canvas.drawText(s5, canvas.getWidth() / 2, (float)canvas.getHeight() - (float)(l + canvas.getHeight()) / 1.5F, mPaintText);
                if(mSpeed != -1)
                    s6 = mDistance;
                else
                    s6 = getResources().getString(2131099744);
                canvas.drawText(s6, canvas.getWidth() / 2, 0.45F * (float)canvas.getHeight(), mPaintTextOdom);
                if(mSpeed != -1)
                    s7 = mHeading;
                else
                    s7 = getResources().getString(2131099749);
                canvas.drawText(s7, canvas.getWidth() / 2, 0.65F * (float)canvas.getHeight(), mPaintTextMisc);
                if(mSpeed != -1)
                    s8 = mElevation;
                else
                    s8 = getResources().getString(2131099745);
                canvas.drawText(s8, canvas.getWidth() / 2, 0.85F * (float)canvas.getHeight(), mPaintTextMisc);
            }
        } else
        {
            String s;
            if(mSpeed != -1)
                s = (new StringBuilder()).append(mSpeed).toString();
            else
                s = "---";
            canvas.drawText(s, canvas.getWidth() / 2, canvas.getHeight() - (canvas.getHeight() - l) / 2, mPaintText);
        }
    }

    void onSpeedChanged(int i, int j)
    {
        mSpeed = i;
        mLimit = j;
        invalidate();
    }

    void onSpeedChanged(int i, int j, String s, String s1, String s2)
    {
        mSpeed = i;
        mLimit = j;
        mDistance = s;
        mHeading = s1;
        mElevation = s2;
        invalidate();
    }

    private String mDistance;
    private String mElevation;
    private String mHeading;
    private int mLimit;
    private Paint mPaint;
    private Paint mPaintText;
    private Paint mPaintTextMisc;
    private Paint mPaintTextOdom;
    private float mScreenRatio;
    private int mSpeed;
}


/*
	DECOMPILATION REPORT

	Decompiled from: H:\AndroidProject\SpeedGps\libs\GPS测速.jar
	Total time: 25 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/