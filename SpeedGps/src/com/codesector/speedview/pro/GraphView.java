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

class GraphView extends Button
{

    public GraphView(Context context)
    {
        this(context, null);
    }

    public GraphView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public GraphView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mGraphScale = 1.0F;
        mBackground = ((BitmapDrawable)getResources().getDrawable(2130837519)).getBitmap();
        setHeight(mBackground.getHeight());
        mScreenRatio = SpeedView.mScreenRatio;
        mSpeedArrayX = (int)(294F * mScreenRatio);
        mPaint = new Paint();
        mPaint.setARGB(255, 50, 50, 50);
        mPaintText = new Paint();
        mPaintText.setARGB(200, 255, 255, 255);
        mPaintText.setTextAlign(android.graphics.Paint.Align.RIGHT);
        mPaintText.setTextSize(12F * mScreenRatio);
        mPaintText.setTypeface(Typeface.SANS_SERIF);
        mPaintText.setAntiAlias(true);
        mPaintLine = new Paint();
        mPaintLine.setARGB(155, 255, 255, 0);
        mPaintLine.setStrokeWidth(2.0F * mScreenRatio);
        mPaintLine.setStyle(android.graphics.Paint.Style.STROKE);
        mPaintLine.setAntiAlias(true);
        mSpeedArray = new int[1 + mSpeedArrayX];
        int j = 0;
        do
        {
            if(j >= mSpeedArray.length)
                return;
            mSpeedArray[j] = 0;
            j++;
        } while(true);
    }

    private void computeGraphScale()
    {
        int i = 0;
        int j = mSpeedArrayX;
        do
        {
            if(j < -100 + mSpeedArrayX)
            {
                int k;
                if(i >= 400)
                    mGraphScale = 0.125F;
                else
                if(i >= 200)
                    mGraphScale = 0.25F;
                else
                if(i >= 100)
                    mGraphScale = 0.5F;
                else
                if(i >= 50)
                    mGraphScale = 1.0F;
                else
                    mGraphScale = 2.0F;
                return;
            }
            k = j + mArrayPointer;
            if(k > mSpeedArrayX)
                k -= 1 + mSpeedArrayX;
            if(mSpeedArray[k] > i)
                i = mSpeedArray[k];
            j--;
        } while(true);
    }

    String getHexArray()
    {
        StringBuilder stringbuilder = new StringBuilder(2 * mSpeedArrayX);
        int i = 0;
        do
        {
            if(i >= mSpeedArrayX)
                return stringbuilder.toString();
            String s = Integer.toHexString(Math.min(250, mSpeedArray[i]));
            if(mSpeedArray[i] < 16)
                s = (new StringBuilder("0")).append(s).toString();
            stringbuilder.append(s);
            i++;
        } while(true);
    }

    boolean isHexArrayEmpty()
    {
        boolean flag = true;
        int i = 0;
        do
        {
            if(i >= mSpeedArray.length)
                return flag;
            if(mSpeedArray[i] != 0)
                flag = false;
            i++;
        } while(true);
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        int i = -2 + getHeight();
        canvas.drawBitmap(mBackground, 0.0F, 0.0F, mPaint);
        canvas.drawText((new StringBuilder()).append((int)(100F / mGraphScale)).toString(), 290F * mScreenRatio, 15F * mScreenRatio, mPaintText);
        int j = 1;
        int k = i;
        int l = 0;
        do
        {
            if(l > mSpeedArrayX)
                return;
            int i1 = l + mArrayPointer;
            if(i1 > mSpeedArrayX)
                i1 -= 1 + mSpeedArrayX;
            if(l == 0)
                k = mSpeedArray[i1];
            float f = (float)i - (float)mSpeedArray[i1] * mGraphScale * mScreenRatio;
            float f1 = (float)i - (float)k * mGraphScale * mScreenRatio;
            if(f < 2.0F)
                f = 2.0F;
            if(f1 < 2.0F)
                f1 = 2.0F;
            canvas.drawLine(l + 1, f, j, f1, mPaintLine);
            k = mSpeedArray[i1];
            j = l + 1;
            l++;
        } while(true);
    }

    void onSpeedChanged(int i)
    {
        synchronized(mSpeedArray)
        {
            mSpeedArray[mArrayPointer] = i;
        }
        mArrayPointer = 1 + mArrayPointer;
        if(mArrayPointer > mSpeedArrayX)
            mArrayPointer = 0;
        computeGraphScale();
        invalidate();
        return;
        exception;
        ai;
        JVM INSTR monitorexit ;
        throw exception;
    }

    void resetHexArray()
    {
        int i = 0;
        do
        {
            if(i >= mSpeedArray.length)
            {
                invalidate();
                return;
            }
            mSpeedArray[i] = 0;
            i++;
        } while(true);
    }

    void setHexArray(String s)
    {
        int ai[] = mSpeedArray;
        ai;
        JVM INSTR monitorenter ;
        int i = 0;
_L3:
        int j = mSpeedArrayX;
        if(i < j) goto _L2; else goto _L1
_L1:
        ai;
        JVM INSTR monitorexit ;
        computeGraphScale();
        invalidate();
        return;
_L2:
        mSpeedArray[i] = Integer.parseInt(s.substring(i * 2, 2 + i * 2), 16);
        i++;
          goto _L3
        Exception exception1;
        exception1;
        ai;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
          goto _L1
    }

    public int mArrayPointer;
    private Bitmap mBackground;
    private float mGraphScale;
    private Paint mPaint;
    private Paint mPaintLine;
    private Paint mPaintText;
    private float mScreenRatio;
    private int mSpeedArray[];
    private int mSpeedArrayX;
}


/*
	DECOMPILATION REPORT

	Decompiled from: H:\AndroidProject\SpeedGps\libs\GPS测速.jar
	Total time: 20 ms
	Jad reported messages/errors:
Couldn't fully decompile method onSpeedChanged
Couldn't resolve all exception handlers in method onSpeedChanged
Couldn't fully decompile method setHexArray
Couldn't resolve all exception handlers in method setHexArray
	Exit status: 0
	Caught exceptions:
*/