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
		//TODO
        double mMetricScaleChoices[] = null;
        float mMetricDisplayUnitsPerKm[] = null;
        String as[];
        if(mUseMetric)
        {
            as = mMetricScaleFormats;
        } else
        {
            as = mEnglishScaleFormats;
        }

		for (int j = 0; j < mMetricScaleChoices.length; j++){
            if(mLastScale != j)
            {
                String s = as[mLastScale];
                float f = (float)(mMetricScaleChoices[j] * (double)mMetricDisplayUnitsPerKm[j]);
                mDistanceScale[0] = String.format(s, Float.valueOf(f / 4F));
                mDistanceScale[1] = String.format(s, Float.valueOf(f / 2.0F));
                mDistanceScale[2] = String.format(s, Float.valueOf((3F * f) / 4F));
                mDistanceScale[3] = String.format(s, Float.valueOf(f));
            }
            mDistanceRatio = (float)(mDistance / mMetricScaleChoices[mLastScale]);
        }
    }

	public void onAccuracyChanged(int i, int j) {
	}

	protected void onDraw(Canvas canvas)
    {
        //TODO
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