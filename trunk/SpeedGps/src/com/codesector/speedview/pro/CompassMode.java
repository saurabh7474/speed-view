/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.codesector.speedview.pro;

import android.content.Context;
import android.graphics.*;
import android.location.Location;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

// Referenced classes of package com.codesector.speedview.pro:
//            SpeedView

class CompassMode extends View {

	public CompassMode(Context context) {
		this(context, null);
	}

	public CompassMode(Context context, AttributeSet attributeset) {
		this(context, attributeset, 0);
	}

	public CompassMode(Context context, AttributeSet attributeset, int i) {
		super(context, attributeset, i);
		mTargetLocation = null;
		mLastScale = -1;
		mDistanceScale = new String[4];
		mHaveLocation = false;
		mTextBounds = new Rect();
		mScreenRatio = SpeedView.mScreenRatio;
		mRadarPath = new Path();
		mWhitePaint = new Paint();
		mWhitePaint.setColor(-1);
		mWhitePaint.setAntiAlias(true);
		mWhitePaint.setStyle(android.graphics.Paint.Style.FILL);
		mWhitePaint.setStrokeWidth(1.0F);
		mWhitePaint.setTextSize(14F);
		mWhitePaint.setTextAlign(android.graphics.Paint.Align.CENTER);
		mTextPaint = new Paint();
		mTextPaint.setColor(-1608507360);
		mTextPaint.setAntiAlias(true);
		mTextPaint.setStyle(android.graphics.Paint.Style.FILL);
		mTextPaint.setStrokeWidth(1.0F);
		mTextPaint.setTextSize(12F * mScreenRatio);
		mTextPaint.setTextAlign(android.graphics.Paint.Align.LEFT);
		mBigPaint = new Paint();
		mBigPaint.setColor(-1);
		mBigPaint.setAntiAlias(true);
		mBigPaint.setTextSize(32F * mScreenRatio);
		mBigPaint.setTypeface(Typeface.DEFAULT_BOLD);
		mBigPaint.setTextAlign(android.graphics.Paint.Align.CENTER);
		mBigPaint2 = new Paint();
		mBigPaint2.setColor(-3355444);
		mBigPaint2.setAntiAlias(true);
		mBigPaint2.setTextSize(20F * mScreenRatio);
		mBigPaint2.setTypeface(Typeface.DEFAULT_BOLD);
		mBigPaint2.setTextAlign(android.graphics.Paint.Align.CENTER);
		mBlackPaint = new Paint();
		mBlackPaint.setColor(-11184811);
		mBlackPaint.setAntiAlias(true);
		mBlackPaint.setStyle(android.graphics.Paint.Style.STROKE);
		mBlackPaint.setStrokeWidth(20F * mScreenRatio);
		mBlackPaint.setTextSize(10F * mScreenRatio);
		mBlackPaint.setTextAlign(android.graphics.Paint.Align.CENTER);
		mGridPaint = new Paint();
		mGridPaint.setColor(1442840575);
		mGridPaint.setAntiAlias(true);
		mGridPaint.setStyle(android.graphics.Paint.Style.STROKE);
		mGridPaint.setStrokeWidth(1.0F);
		mGridPaint.setTextSize(10F);
		mGridPaint.setTextAlign(android.graphics.Paint.Align.CENTER);
		mErasePaint = new Paint();
		mErasePaint.setColor(-15132391);
		mErasePaint.setStyle(android.graphics.Paint.Style.FILL);
		mSweepPaint0 = new Paint();
		mSweepPaint0.setColor(1345532723);
		mSweepPaint0.setAntiAlias(true);
		mSweepPaint0.setStyle(android.graphics.Paint.Style.STROKE);
		mSweepPaint0.setStrokeWidth(2.0F);
		mSweepPaint1 = new Paint();
		mSweepPaint1.setColor(808661811);
		mSweepPaint1.setAntiAlias(true);
		mSweepPaint1.setStyle(android.graphics.Paint.Style.STROKE);
		mSweepPaint1.setStrokeWidth(2.0F);
		mSweepPaint2 = new Paint();
		mSweepPaint2.setColor(271790899);
		mSweepPaint2.setAntiAlias(true);
		mSweepPaint2.setStyle(android.graphics.Paint.Style.STROKE);
		mSweepPaint2.setStrokeWidth(2.0F);
	}

	private void addText(Canvas canvas, String s, int i, int j) {
		mGridPaint.getTextBounds(s, 0, s.length(), mTextBounds);
		canvas.drawText(s, i - mTextBounds.width(), j - 4, mTextPaint);
	}

	private void updateDistance(double d)
    {
        double mMetricScaleChoices[];
        float mMetricDisplayUnitsPerKm[];
        String as[];
        int i = mMetricScaleChoices.length;
        int j = 0;
        if(mUseMetric)
        {
            as = mMetricScaleFormats;
        } else
        {
            as = mEnglishScaleFormats;
        }

_L5:
        if(j < i) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(d >= mMetricScaleChoices[j] && j != i - 1)
            break; /* Loop/switch isn't completed */
        if(mLastScale != j)
        {
            mLastScale = j;
            String s = as[j];
            float f = (float)(mMetricScaleChoices[j] * (double)mMetricDisplayUnitsPerKm[j]);
            String as1[] = mDistanceScale;
            Object aobj[] = new Object[1];
            aobj[0] = Float.valueOf(f / 4F);
            as1[0] = String.format(s, aobj);
            String as2[] = mDistanceScale;
            Object aobj1[] = new Object[1];
            aobj1[0] = Float.valueOf(f / 2.0F);
            as2[1] = String.format(s, aobj1);
            String as3[] = mDistanceScale;
            Object aobj2[] = new Object[1];
            aobj2[0] = Float.valueOf((3F * f) / 4F);
            as3[2] = String.format(s, aobj2);
            String as4[] = mDistanceScale;
            Object aobj3[] = new Object[1];
            aobj3[0] = Float.valueOf(f);
            as4[3] = String.format(s, aobj3);
        }
        mDistanceRatio = (float)(mDistance / mMetricScaleChoices[mLastScale]);
        if(true) goto _L1; else goto _L3
_L3:
        j++;
        if(true) goto _L5; else goto _L4
_L4:
    }

	public void onAccuracyChanged(int i, int j) {
	}

	protected void onDraw(Canvas canvas)
    {
        int i;
        int j;
        Paint paint;
        Paint paint2;
        int k;
        super.onDraw(canvas);
        i = getHeight() / 2;
        j = (int)((float)i - 30F * mScreenRatio);
        paint = mGridPaint;
        canvas.drawCircle(i, i, -2 + (j * 2) / 3, paint);
        canvas.drawCircle(i, i, -1 + (j * 1) / 3, paint);
        canvas.drawLine(i, i - j, i, i + j, paint);
        canvas.drawLine(i - j, i, i + j, i, paint);
        canvas.drawCircle(i, i, 2.0F, paint);
        Paint paint1 = mBlackPaint;
        canvas.drawCircle(i, i, j, paint1);
        paint2 = mWhitePaint;
        k = 0;
_L4:
        int l;
        int i1;
        double d4;
        float f12;
        float f13;
        long l2;
        int j1;
        double d5;
        float f14;
        float f15;
        double d6;
        float f16;
        float f17;
        double d7;
        float f18;
        float f19;
        if(k >= 360)
        {
            l = 0;
            break MISSING_BLOCK_LABEL_154;
        }
        double d = Math.toRadians(-mOrientation + (float)k) - 1.5707963267948966D;
        f = (float)Math.cos(d);
        f1 = (float)Math.sin(d);
        f2 = (float)i + f * (float)j;
        f3 = (float)i + f1 * (float)j;
        k;
        JVM INSTR lookupswitch 4: default 776
    //                   0: 800
    //                   90: 977
    //                   180: 977
    //                   270: 977;
           goto _L1 _L2 _L3 _L3 _L3
_L3:
        break MISSING_BLOCK_LABEL_977;
_L1:
        canvas.drawCircle(f2, f3, 3F * mScreenRatio, paint2);
_L5:
        k += 30;
          goto _L4
_L2:
        mRadarPath.reset();
        mRadarPath.moveTo((float)i + f * (float)(j + 7), (float)i + f1 * (float)(j + 7));
        double d1 = Math.toRadians(-mOrientation - 5F) - 1.5707963267948966D;
        float f4 = (float)Math.cos(d1);
        float f5 = (float)Math.sin(d1);
        mRadarPath.lineTo((float)i + f4 * (float)(j - 6), (float)i + f5 * (float)(j - 6));
        double d2 = Math.toRadians(5F + -mOrientation) - 1.5707963267948966D;
        float f6 = (float)Math.cos(d2);
        float f7 = (float)Math.sin(d2);
        mRadarPath.lineTo((float)i + f6 * (float)(j - 6), (float)i + f7 * (float)(j - 6));
        canvas.drawPath(mRadarPath, paint2);
          goto _L5
        canvas.drawCircle(f2, f3, 5F * mScreenRatio, paint2);
          goto _L5
_L15:
        if(l >= 360)
        {
            if(mHasBearing)
            {
                d5 = Math.toRadians(-mOrientation + mLocationBearing) - 1.5707963267948966D;
                f14 = (float)Math.cos(d5);
                f15 = (float)Math.sin(d5);
                mRadarPath.reset();
                mRadarPath.moveTo((float)i + f14 * (float)(j / 3), (float)i + f15 * (float)(j / 3));
                d6 = Math.toRadians((-mOrientation + mLocationBearing) - 150F) - 1.5707963267948966D;
                f16 = (float)Math.cos(d6);
                f17 = (float)Math.sin(d6);
                mRadarPath.lineTo((float)i + f16 * (float)(j / 3), (float)i + f17 * (float)(j / 3));
                d7 = Math.toRadians(150F + (-mOrientation + mLocationBearing)) - 1.5707963267948966D;
                f18 = (float)Math.cos(d7);
                f19 = (float)Math.sin(d7);
                mRadarPath.lineTo((float)i + f18 * (float)(j / 3), (float)i + f19 * (float)(j / 3));
                canvas.drawPath(mRadarPath, mTextPaint);
            }
            i1 = (int)(mDistanceRatio * (float)(j - 14));
            long l1 = SystemClock.uptimeMillis();
            if(mSweepTime > 0L && mHaveLocation)
            {
                l2 = l1 - mSweepTime;
                if(l2 < 512L)
                {
                    j1 = (int)(l2 * (long)(j + 6) >> 9);
                    canvas.drawCircle(i, i, j1, mSweepPaint0);
                    canvas.drawCircle(i, i, j1 - 2, mSweepPaint1);
                    canvas.drawCircle(i, i, j1 - 4, mSweepPaint2);
                    float f;
                    float f1;
                    float f2;
                    float f3;
                    double d3;
                    float f8;
                    float f9;
                    float f10;
                    float f11;
                    boolean flag;
                    if(j1 < i1)
                        flag = true;
                    else
                        flag = false;
                    if(!flag && mSweepBefore)
                    {
                        mSweepBefore = false;
                        mBlipTime = l1;
                    }
                } else
                {
                    mSweepTime = 1000L + l1;
                    mSweepBefore = true;
                }
                postInvalidate();
            }
            if(mHaveLocation && mTargetLocation != null)
            {
                d4 = Math.toRadians(mBearing - (double)mOrientation) - 1.5707963267948966D;
                f12 = (float)Math.cos(d4);
                f13 = (float)Math.sin(d4);
                addText(canvas, mDistanceScale[3], -18 + (i + j), i);
                if(mTargetLocation != null)
                {
                    paint.setAlpha(255 - (int)(128L * (l1 - mBlipTime) >> 10));
                    canvas.drawBitmap(mBlip, ((float)i + f12 * (float)i1) - 8F, ((float)i + f13 * (float)i1) - 8F, paint);
                    paint.setAlpha(255);
                }
            }
            return;
        }
        d3 = Math.toRadians(-mOrientation + (float)l) - 1.5707963267948966D;
        f8 = (float)Math.cos(d3);
        f9 = (float)Math.sin(d3);
        f10 = (float)i + f8 * (float)((j * 2) / 3);
        f11 = (float)i + f9 * (float)((j * 2) / 3);
        canvas.save();
        canvas.rotate(-mOrientation + (float)l, f10, f11);
        l;
        JVM INSTR lookupswitch 8: default 1160
    //                   0: 1170
    //                   45: 1262
    //                   90: 1193
    //                   135: 1282
    //                   180: 1216
    //                   225: 1302
    //                   270: 1239
    //                   315: 1322;
           goto _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14
_L14:
        break MISSING_BLOCK_LABEL_1322;
_L16:
        canvas.restore();
        l += 45;
          goto _L15
_L7:
        canvas.drawText(mSides[0], f10, 14F + f11, mBigPaint);
          break;
          _L9:
        canvas.drawText(mSides[1], f10, 14F + f11, mBigPaint);
          break;
_L11:
        canvas.drawText(mSides[2], f10, 14F + f11, mBigPaint);
         break;
_L13:
        canvas.drawText(mSides[3], f10, 14F + f11, mBigPaint);
         break;
_L8:
        canvas.drawText(mSides2[0], f10, f11, mBigPaint2);
         break;
_L10:
        canvas.drawText(mSides2[1], f10, f11, mBigPaint2);
         break;
_L12:
        canvas.drawText(mSides2[2], f10, f11, mBigPaint2);
         break;
        canvas.drawText(mSides2[3], f10, f11, mBigPaint2);
         break;
    }

	public void onLocationChanged(Location location, int i, int j) {
		if (!mHaveLocation)
			mHaveLocation = true;
		if (SpeedView.mWarningChecked && i > j)
			mBlackPaint.setColor(-3407872);
		else
			mBlackPaint.setColor(-11184811);
		mOrientation = location.getBearing();
		invalidate();
	}

	public void onSensorChanged(float f) {
		mOrientation = f;
		mBlackPaint.setColor(-11184811);
		postInvalidate();
	}

	public void setDistanceView(TextView textview) {
	}

	public void setUseMetric(boolean flag) {
		mUseMetric = flag;
		mLastScale = -1;
		if (mHaveLocation)
			updateDistance(mDistance);
		invalidate();
	}

	public void startSweep() {
		mSweepTime = SystemClock.uptimeMillis();
		mSweepBefore = true;
	}

	public void stopSweep() {
		mSweepTime = 0L;
	}

	private static float KM_PER_METERS = 0.001F;;
	private static float KM_PER_MILES = 1.609344F;
	private static float KM_PER_YARDS = 0.0009144F;;
	private static float METERS_PER_KM = 1000F;
	private static float MILES_PER_KM = 0.6213712F;
	private static float YARDS_PER_KM = 1093.613F;
	private static float mEnglishDisplayUnitsPerKm[] = { YARDS_PER_KM,
			YARDS_PER_KM, YARDS_PER_KM, YARDS_PER_KM, YARDS_PER_KM,
			MILES_PER_KM, MILES_PER_KM, MILES_PER_KM, MILES_PER_KM,
			MILES_PER_KM, MILES_PER_KM, MILES_PER_KM, MILES_PER_KM,
			MILES_PER_KM, MILES_PER_KM, MILES_PER_KM, MILES_PER_KM,
			MILES_PER_KM, MILES_PER_KM, MILES_PER_KM, MILES_PER_KM };
	private static double mEnglishScaleChoices[] = { 25F * KM_PER_YARDS,
			100F * KM_PER_YARDS, 200F * KM_PER_YARDS, 400F * KM_PER_YARDS,
			1000F * KM_PER_YARDS, 1.0F * KM_PER_MILES, 2.0F * KM_PER_MILES,
			4F * KM_PER_MILES, 8F * KM_PER_MILES, 20F * KM_PER_MILES,
			40F * KM_PER_MILES, 100F * KM_PER_MILES, 200F * KM_PER_MILES,
			400F * KM_PER_MILES, 1000F * KM_PER_MILES, 2000F * KM_PER_MILES,
			4000F * KM_PER_MILES, 10000F * KM_PER_MILES, 20000F * KM_PER_MILES,
			40000F * KM_PER_MILES, 80000F * KM_PER_MILES };
	private static String mEnglishScaleFormats[] = { "%.0fyd", "%.0fyd",
			"%.0fyd", "%.0fyd", "%.0fyd", "%.2fmi", "%.1fmi", "%.0fmi",
			"%.0fmi", "%.0fmi", "%.0fmi", "%.0fmi", "%.0fmi", "%.0fmi",
			"%.0fmi", "%.0fmi", "%.0fmi", "%.0fmi", "%.0fmi", "%.0fmi",
			"%.0fmi" };
	private static float mMetricDisplayUnitsPerKm[] = { METERS_PER_KM,
			METERS_PER_KM, METERS_PER_KM, METERS_PER_KM, METERS_PER_KM,
			METERS_PER_KM, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F,
			1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F };
	private static double mMetricScaleChoices[] = { 25F * KM_PER_METERS,
			100F * KM_PER_METERS, 200F * KM_PER_METERS, 400F * KM_PER_METERS,
			1.0D, 2D, 4D, 8D, 20D, 40D, 100D, 200D, 400D, 1000D, 2000D, 4000D,
			10000D, 20000D, 40000D, 80000D };
	private static String mMetricScaleFormats[] = { "%.0fm", "%.0fm", "%.0fm",
			"%.0fm", "%.0fm", "%.0fm", "%.0fkm", "%.0fkm", "%.0fkm", "%.0fkm",
			"%.0fkm", "%.0fkm", "%.0fkm", "%.0fkm", "%.0fkm", "%.0fkm",
			"%.0fkm", "%.0fkm", "%.0fkm", "%.0fkm", "%.0fkm" };
	private static String mSides[] = { "N", "E", "S", "W" };
	private static String mSides2[] = { "NE", "SE", "SW", "NW" };
	private double mBearing;
	private Paint mBigPaint;
	private Paint mBigPaint2;
	private Paint mBlackPaint;
	private Bitmap mBlip;
	private long mBlipTime;
	private double mDistance;
	private float mDistanceRatio;
	private String mDistanceScale[];
	private Paint mErasePaint;
	private Paint mGridPaint;
	private boolean mHasBearing;
	private boolean mHaveLocation;
	private int mLastScale;
	private float mLocationBearing;
	private float mOrientation;
	private Path mRadarPath;
	private float mScreenRatio;
	private boolean mSweepBefore;
	private Paint mSweepPaint0;
	private Paint mSweepPaint1;
	private Paint mSweepPaint2;
	private long mSweepTime;
	private Location mTargetLocation;
	private Rect mTextBounds;
	private Paint mTextPaint;
	private boolean mUseMetric;
	private Paint mWhitePaint;
}

/*
 * DECOMPILATION REPORT
 *
 * Decompiled from: H:\AndroidProject\SpeedGps\libs\GPS娴嬮�.jar Total time: 52 ms
 * Jad reported messages/errors: Couldn't fully decompile method updateDistance
 * Couldn't fully decompile method onDraw Exit status: 0 Caught exceptions:
 */