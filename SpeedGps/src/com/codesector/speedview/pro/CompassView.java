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

class CompassView extends Button
{

    public CompassView(Context context)
    {
        this(context, null);
    }

    public CompassView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public CompassView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mBearing = 0.0F;
        mScreenRatio = SpeedView.mScreenRatio;
        mBackground = ((BitmapDrawable)getResources().getDrawable(2130837514)).getBitmap();
        setHeight(mBackground.getHeight());
        mPaint = new Paint();
        mPaint.setARGB(255, 235, 235, 235);
        mPointerPath = new Path();
        mPointerPath.moveTo(142F * mScreenRatio, 39F * mScreenRatio);
        mPointerPath.lineTo(152F * mScreenRatio, 39F * mScreenRatio);
        mPointerPath.lineTo(147F * mScreenRatio, 27F * mScreenRatio);
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawBitmap(mBackground, (float)(-(1.78D * (double)mBearing * (double)mScreenRatio)), 0.0F, mPaint);
        canvas.drawPath(mPointerPath, mPaint);
    }

    void onSpeedChanged(float f)
    {
        mBearing = f;
        invalidate();
    }

    private Bitmap mBackground;
    private float mBearing;
    private Paint mPaint;
    private Path mPointerPath;
    private float mScreenRatio;
}


/*
	DECOMPILATION REPORT

	Decompiled from: H:\AndroidProject\SpeedGps\libs\GPS测速.jar
	Total time: 16 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/