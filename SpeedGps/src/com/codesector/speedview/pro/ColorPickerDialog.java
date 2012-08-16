/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.codesector.speedview.pro;

import android.app.Dialog;
import android.content.Context;
import android.graphics.*;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class ColorPickerDialog extends Dialog
{
    private static class ColorPickerView extends View
    {

        private int ave(int i, int j, float f)
        {
            return i + Math.round(f * (float)(j - i));
        }

        private int interpColor(int ai[], float f)
        {
            int l;
            if(f <= 0.0F)
                l = ai[0];
            else
            if(f >= 1.0F)
            {
                l = ai[-1 + ai.length];
            } else
            {
                float f1 = f * (float)(-1 + ai.length);
                int i = (int)f1;
                float f2 = f1 - (float)i;
                int j = ai[i];
                int k = ai[i + 1];
                l = Color.argb(ave(Color.alpha(j), Color.alpha(k), f2), ave(Color.red(j), Color.red(k), f2), ave(Color.green(j), Color.green(k), f2), ave(Color.blue(j), Color.blue(k), f2));
            }
            return l;
        }

        protected void onDraw(Canvas canvas)
        {
            float f = 100F - 0.5F * mPaint.getStrokeWidth();
            canvas.translate(100F, 100F);
            canvas.drawOval(new RectF(-f, -f, f, f), mPaint);
            canvas.drawCircle(0.0F, 0.0F, 32F, mCenterPaint);
            if(mTrackingCenter)
            {
                int i = mCenterPaint.getColor();
                mCenterPaint.setStyle(android.graphics.Paint.Style.STROKE);
                if(mHighlightCenter)
                    mCenterPaint.setAlpha(255);
                else
                    mCenterPaint.setAlpha(128);
                canvas.drawCircle(0.0F, 0.0F, 32F + mCenterPaint.getStrokeWidth(), mCenterPaint);
                mCenterPaint.setStyle(android.graphics.Paint.Style.FILL);
                mCenterPaint.setColor(i);
            }
        }

        protected void onMeasure(int i, int j)
        {
            setMeasuredDimension(200, 200);
        }

        public boolean onTouchEvent(MotionEvent motionevent)
        {
            float f;
            float f1;
            boolean flag;
            f = motionevent.getX() - 100F;
            f1 = motionevent.getY() - 100F;
            if(Math.sqrt(f * f + f1 * f1) <= 32D)
                flag = true;
            else
                flag = false;
            motionevent.getAction();
            JVM INSTR tableswitch 0 2: default 68
        //                       0 76
        //                       1 178
        //                       2 99;
               goto _L1 _L2 _L3 _L4
_L1:
            return true;
_L2:
            mTrackingCenter = flag;
            if(flag)
            {
                mHighlightCenter = true;
                invalidate();
                continue; /* Loop/switch isn't completed */
            }
_L4:
            if(mTrackingCenter)
            {
                if(mHighlightCenter != flag)
                {
                    mHighlightCenter = flag;
                    invalidate();
                }
            } else
            {
                float f2 = (float)Math.atan2(f1, f) / 6.283185F;
                if(f2 < 0.0F)
                    f2++;
                mCenterPaint.setColor(interpColor(mColors, f2));
                invalidate();
            }
            continue; /* Loop/switch isn't completed */
_L3:
            if(mTrackingCenter)
            {
                if(flag)
                    mListener.colorChanged(mCenterPaint.getColor());
                mTrackingCenter = false;
                invalidate();
            }
            if(true) goto _L1; else goto _L5
_L5:
        }

        private static final int CENTER_RADIUS = 32;
        private static final int CENTER_X = 100;
        private static final int CENTER_Y = 100;
        private static final float PI = 3.141593F;
        private final Paint mCenterPaint = new Paint(1);
        private final int mColors[];
        private boolean mHighlightCenter;
        private final OnColorChangedListener mListener;
        private final Paint mPaint = new Paint(1);
        private boolean mTrackingCenter;

        ColorPickerView(Context context, OnColorChangedListener oncolorchangedlistener, int i)
        {
            super(context);
            mListener = oncolorchangedlistener;
            int ai[] = new int[7];
            ai[0] = -65536;
            ai[1] = -65281;
            ai[2] = -16776961;
            ai[3] = -16711681;
            ai[4] = -16711936;
            ai[5] = -256;
            ai[6] = -65536;
            mColors = ai;
            SweepGradient sweepgradient = new SweepGradient(0.0F, 0.0F, mColors, null);
            mPaint.setShader(sweepgradient);
            mPaint.setStyle(android.graphics.Paint.Style.STROKE);
            mPaint.setStrokeWidth(32F);
            mCenterPaint.setColor(i);
            mCenterPaint.setStrokeWidth(5F);
        }
    }

    public static interface OnColorChangedListener
    {

        public abstract void colorChanged(int i);
    }


    public ColorPickerDialog(Context context, OnColorChangedListener oncolorchangedlistener, int i)
    {
        super(context);
        mListener = oncolorchangedlistener;
        mInitialColor = i;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        OnColorChangedListener oncolorchangedlistener = new OnColorChangedListener() {

            public void colorChanged(int i)
            {
                mListener.colorChanged(i);
                dismiss();
            }

            final ColorPickerDialog this$0;

            
            {
                this$0 = ColorPickerDialog.this;
                super();
            }
        }
;
        LinearLayout linearlayout = new LinearLayout(getContext());
        linearlayout.setOrientation(1);
        linearlayout.setGravity(17);
        linearlayout.setPadding(10, 10, 10, 10);
        linearlayout.addView(new ColorPickerView(getContext(), oncolorchangedlistener, mInitialColor), new android.widget.LinearLayout.LayoutParams(-2, -2));
        setContentView(linearlayout);
        setTitle(2131099868);
    }

    private final int mInitialColor;
    private final OnColorChangedListener mListener;

}


/*
	DECOMPILATION REPORT

	Decompiled from: H:\AndroidProject\SpeedGps\libs\GPS测速.jar
	Total time: 20 ms
	Jad reported messages/errors:
Couldn't fully decompile method onTouchEvent
	Exit status: 0
	Caught exceptions:
*/