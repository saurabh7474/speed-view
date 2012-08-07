package com.codesector.speedview.pro;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.location.Location;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.codesector.speedview.pro.R;

class CompassMode extends View {
	private static float KM_PER_METERS = 0.001F;
	private static float KM_PER_MILES;
	private static float KM_PER_YARDS;
	private static float METERS_PER_KM = 1000.0F;
	private static float MILES_PER_KM;
	private static float YARDS_PER_KM;
	private static float[] mEnglishDisplayUnitsPerKm;
	private static double[] mEnglishScaleChoices;
	private static String[] mEnglishScaleFormats;
	private static float[] mMetricDisplayUnitsPerKm;
	private static double[] mMetricScaleChoices;
	private static String[] mMetricScaleFormats;
	private static String[] mSides;
	private static String[] mSides2;
	private double mBearing;
	private Paint mBigPaint;
	private Paint mBigPaint2;
	private Paint mBlackPaint;
	private Bitmap mBlip;
	private long mBlipTime;
	private double mDistance;
	private float mDistanceRatio;
	private String[] mDistanceScale = new String[4];
	private Paint mErasePaint;
	private Paint mGridPaint;
	private boolean mHasBearing;
	private boolean mHaveLocation = false;
	private int mLastScale = -1;
	private float mLocationBearing;
	private float mOrientation;
	private Path mRadarPath = new Path();
	private float mScreenRatio = SpeedView.mScreenRatio;
	private boolean mSweepBefore;
	private Paint mSweepPaint0;
	private Paint mSweepPaint1;
	private Paint mSweepPaint2;
	private long mSweepTime;
	private Location mTargetLocation = null;
	private Rect mTextBounds = new Rect();
	private Paint mTextPaint;
	private boolean mUseMetric;
	private Paint mWhitePaint = new Paint();

	static {
		mMetricScaleChoices[0] = (25.0F * KM_PER_METERS);
		mMetricScaleChoices[1] = (100.0F * KM_PER_METERS);
		mMetricScaleChoices[2] = (200.0F * KM_PER_METERS);
		mMetricScaleChoices[3] = (400.0F * KM_PER_METERS);
		mMetricScaleChoices[4] = 1.0D;
		mMetricScaleChoices[5] = 2.0D;
		mMetricScaleChoices[6] = 4.0D;
		mMetricScaleChoices[7] = 8.0D;
		mMetricScaleChoices[8] = 20.0D;
		mMetricScaleChoices[9] = 40.0D;
		mMetricScaleChoices[10] = 100.0D;
		mMetricScaleChoices[11] = 200.0D;
		mMetricScaleChoices[12] = 400.0D;
		mMetricScaleChoices[13] = 1000.0D;
		mMetricScaleChoices[14] = 2000.0D;
		mMetricScaleChoices[15] = 4000.0D;
		mMetricScaleChoices[16] = 10000.0D;
		mMetricScaleChoices[17] = 20000.0D;
		mMetricScaleChoices[18] = 40000.0D;
		mMetricScaleChoices[19] = 80000.0D;
		mMetricDisplayUnitsPerKm[0] = METERS_PER_KM;
		mMetricDisplayUnitsPerKm[1] = METERS_PER_KM;
		mMetricDisplayUnitsPerKm[2] = METERS_PER_KM;
		mMetricDisplayUnitsPerKm[3] = METERS_PER_KM;
		mMetricDisplayUnitsPerKm[4] = METERS_PER_KM;
		mMetricDisplayUnitsPerKm[5] = METERS_PER_KM;
		mMetricDisplayUnitsPerKm[6] = 1.0F;
		mMetricDisplayUnitsPerKm[7] = 1.0F;
		mMetricDisplayUnitsPerKm[8] = 1.0F;
		mMetricDisplayUnitsPerKm[9] = 1.0F;
		mMetricDisplayUnitsPerKm[10] = 1.0F;
		mMetricDisplayUnitsPerKm[11] = 1.0F;
		mMetricDisplayUnitsPerKm[12] = 1.0F;
		mMetricDisplayUnitsPerKm[13] = 1.0F;
		mMetricDisplayUnitsPerKm[14] = 1.0F;
		mMetricDisplayUnitsPerKm[15] = 1.0F;
		mMetricDisplayUnitsPerKm[16] = 1.0F;
		mMetricDisplayUnitsPerKm[17] = 1.0F;
		mMetricDisplayUnitsPerKm[18] = 1.0F;
		mMetricDisplayUnitsPerKm[19] = 1.0F;
		mSides[0] = "N";
		mSides[1] = "E";
		mSides[2] = "S";
		mSides[3] = "W";
		mSides2[0] = "NE";
		mSides2[1] = "SE";
		mSides2[2] = "SW";
		mSides2[3] = "NW";
		mMetricScaleFormats[0] = "%.0fm";
		mMetricScaleFormats[1] = "%.0fm";
		mMetricScaleFormats[2] = "%.0fm";
		mMetricScaleFormats[3] = "%.0fm";
		mMetricScaleFormats[4] = "%.0fm";
		mMetricScaleFormats[5] = "%.0fm";
		mMetricScaleFormats[6] = "%.0fkm";
		mMetricScaleFormats[7] = "%.0fkm";
		mMetricScaleFormats[8] = "%.0fkm";
		mMetricScaleFormats[9] = "%.0fkm";
		mMetricScaleFormats[10] = "%.0fkm";
		mMetricScaleFormats[11] = "%.0fkm";
		mMetricScaleFormats[12] = "%.0fkm";
		mMetricScaleFormats[13] = "%.0fkm";
		mMetricScaleFormats[14] = "%.0fkm";
		mMetricScaleFormats[15] = "%.0fkm";
		mMetricScaleFormats[16] = "%.0fkm";
		mMetricScaleFormats[17] = "%.0fkm";
		mMetricScaleFormats[18] = "%.0fkm";
		mMetricScaleFormats[19] = "%.0fkm";
		mMetricScaleFormats[20] = "%.0fkm";
		KM_PER_YARDS = 0.0009144F;
		KM_PER_MILES = 1.609344F;
		YARDS_PER_KM = 1093.6133F;
		MILES_PER_KM = 0.6213712F;
		mEnglishScaleChoices[0] = (25.0F * KM_PER_YARDS);
		mEnglishScaleChoices[1] = (100.0F * KM_PER_YARDS);
		mEnglishScaleChoices[2] = (200.0F * KM_PER_YARDS);
		mEnglishScaleChoices[3] = (400.0F * KM_PER_YARDS);
		mEnglishScaleChoices[4] = (1000.0F * KM_PER_YARDS);
		mEnglishScaleChoices[5] = (1.0F * KM_PER_MILES);
		mEnglishScaleChoices[6] = (2.0F * KM_PER_MILES);
		mEnglishScaleChoices[7] = (4.0F * KM_PER_MILES);
		mEnglishScaleChoices[8] = (8.0F * KM_PER_MILES);
		mEnglishScaleChoices[9] = (20.0F * KM_PER_MILES);
		mEnglishScaleChoices[10] = (40.0F * KM_PER_MILES);
		mEnglishScaleChoices[11] = (100.0F * KM_PER_MILES);
		mEnglishScaleChoices[12] = (200.0F * KM_PER_MILES);
		mEnglishScaleChoices[13] = (400.0F * KM_PER_MILES);
		mEnglishScaleChoices[14] = (1000.0F * KM_PER_MILES);
		mEnglishScaleChoices[15] = (2000.0F * KM_PER_MILES);
		mEnglishScaleChoices[16] = (4000.0F * KM_PER_MILES);
		mEnglishScaleChoices[17] = (10000.0F * KM_PER_MILES);
		mEnglishScaleChoices[18] = (20000.0F * KM_PER_MILES);
		mEnglishScaleChoices[19] = (40000.0F * KM_PER_MILES);
		mEnglishScaleChoices[20] = (80000.0F * KM_PER_MILES);
		mEnglishDisplayUnitsPerKm[0] = YARDS_PER_KM;
		mEnglishDisplayUnitsPerKm[1] = YARDS_PER_KM;
		mEnglishDisplayUnitsPerKm[2] = YARDS_PER_KM;
		mEnglishDisplayUnitsPerKm[3] = YARDS_PER_KM;
		mEnglishDisplayUnitsPerKm[4] = YARDS_PER_KM;
		mEnglishDisplayUnitsPerKm[5] = MILES_PER_KM;
		mEnglishDisplayUnitsPerKm[6] = MILES_PER_KM;
		mEnglishDisplayUnitsPerKm[7] = MILES_PER_KM;
		mEnglishDisplayUnitsPerKm[8] = MILES_PER_KM;
		mEnglishDisplayUnitsPerKm[9] = MILES_PER_KM;
		mEnglishDisplayUnitsPerKm[10] = MILES_PER_KM;
		mEnglishDisplayUnitsPerKm[11] = MILES_PER_KM;
		mEnglishDisplayUnitsPerKm[12] = MILES_PER_KM;
		mEnglishDisplayUnitsPerKm[13] = MILES_PER_KM;
		mEnglishDisplayUnitsPerKm[14] = MILES_PER_KM;
		mEnglishDisplayUnitsPerKm[15] = MILES_PER_KM;
		mEnglishDisplayUnitsPerKm[16] = MILES_PER_KM;
		mEnglishDisplayUnitsPerKm[17] = MILES_PER_KM;
		mEnglishDisplayUnitsPerKm[18] = MILES_PER_KM;
		mEnglishDisplayUnitsPerKm[19] = MILES_PER_KM;
		mEnglishDisplayUnitsPerKm[20] = MILES_PER_KM;
		mEnglishScaleFormats[0] = "%.0fyd";
		mEnglishScaleFormats[1] = "%.0fyd";
		mEnglishScaleFormats[2] = "%.0fyd";
		mEnglishScaleFormats[3] = "%.0fyd";
		mEnglishScaleFormats[4] = "%.0fyd";
		mEnglishScaleFormats[5] = "%.2fmi";
		mEnglishScaleFormats[6] = "%.1fmi";
		mEnglishScaleFormats[7] = "%.0fmi";
		mEnglishScaleFormats[8] = "%.0fmi";
		mEnglishScaleFormats[9] = "%.0fmi";
		mEnglishScaleFormats[10] = "%.0fmi";
		mEnglishScaleFormats[11] = "%.0fmi";
		mEnglishScaleFormats[12] = "%.0fmi";
		mEnglishScaleFormats[13] = "%.0fmi";
		mEnglishScaleFormats[14] = "%.0fmi";
		mEnglishScaleFormats[15] = "%.0fmi";
		mEnglishScaleFormats[16] = "%.0fmi";
		mEnglishScaleFormats[17] = "%.0fmi";
		mEnglishScaleFormats[18] = "%.0fmi";
		mEnglishScaleFormats[19] = "%.0fmi";
		mEnglishScaleFormats[20] = "%.0fmi";
	}

	public CompassMode(Context paramContext) {
		this(paramContext, null);
	}

	public CompassMode(Context paramContext, AttributeSet paramAttributeSet) {
		this(paramContext, paramAttributeSet, 0);
	}

	public CompassMode(Context paramContext, AttributeSet paramAttributeSet,
			int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		this.mWhitePaint.setColor(Color.WHITE);
		this.mWhitePaint.setAntiAlias(true);
		this.mWhitePaint.setStyle(Paint.Style.FILL);
		this.mWhitePaint.setStrokeWidth(1.0F);
		this.mWhitePaint.setTextSize(14.0F);
		this.mWhitePaint.setTextAlign(Paint.Align.CENTER);
		this.mTextPaint = new Paint();
		this.mTextPaint.setColor(-1608507360);
		this.mTextPaint.setAntiAlias(true);
		this.mTextPaint.setStyle(Paint.Style.FILL);
		this.mTextPaint.setStrokeWidth(1.0F);
		this.mTextPaint.setTextSize(12.0F * this.mScreenRatio);
		this.mTextPaint.setTextAlign(Paint.Align.LEFT);
		this.mBigPaint = new Paint();
		this.mBigPaint.setColor(Color.WHITE);
		this.mBigPaint.setAntiAlias(true);
		this.mBigPaint.setTextSize(32.0F * this.mScreenRatio);
		this.mBigPaint.setTypeface(Typeface.DEFAULT_BOLD);
		this.mBigPaint.setTextAlign(Paint.Align.CENTER);
		this.mBigPaint2 = new Paint();
		this.mBigPaint2.setColor(-3355444);
		this.mBigPaint2.setAntiAlias(true);
		this.mBigPaint2.setTextSize(20.0F * this.mScreenRatio);
		this.mBigPaint2.setTypeface(Typeface.DEFAULT_BOLD);
		this.mBigPaint2.setTextAlign(Paint.Align.CENTER);
		this.mBlackPaint = new Paint();
		this.mBlackPaint.setColor(-11184811);
		this.mBlackPaint.setAntiAlias(true);
		this.mBlackPaint.setStyle(Paint.Style.STROKE);
		this.mBlackPaint.setStrokeWidth(20.0F * this.mScreenRatio);
		this.mBlackPaint.setTextSize(10.0F * this.mScreenRatio);
		this.mBlackPaint.setTextAlign(Paint.Align.CENTER);
		this.mGridPaint = new Paint();
		this.mGridPaint.setColor(1442840575);
		this.mGridPaint.setAntiAlias(true);
		this.mGridPaint.setStyle(Paint.Style.STROKE);
		this.mGridPaint.setStrokeWidth(1.0F);
		this.mGridPaint.setTextSize(10.0F);
		this.mGridPaint.setTextAlign(Paint.Align.CENTER);
		this.mErasePaint = new Paint();
		this.mErasePaint.setColor(-15132391);
		this.mErasePaint.setStyle(Paint.Style.FILL);
		this.mSweepPaint0 = new Paint();
		this.mSweepPaint0.setColor(1345532723);
		this.mSweepPaint0.setAntiAlias(true);
		this.mSweepPaint0.setStyle(Paint.Style.STROKE);
		this.mSweepPaint0.setStrokeWidth(2.0F);
		this.mSweepPaint1 = new Paint();
		this.mSweepPaint1.setColor(808661811);
		this.mSweepPaint1.setAntiAlias(true);
		this.mSweepPaint1.setStyle(Paint.Style.STROKE);
		this.mSweepPaint1.setStrokeWidth(2.0F);
		this.mSweepPaint2 = new Paint();
		this.mSweepPaint2.setColor(271790899);
		this.mSweepPaint2.setAntiAlias(true);
		this.mSweepPaint2.setStyle(Paint.Style.STROKE);
		this.mSweepPaint2.setStrokeWidth(2.0F);
	}

	private void addText(Canvas paramCanvas, String paramString, int paramInt1,
			int paramInt2) {
		this.mGridPaint.getTextBounds(paramString, 0, paramString.length(),
				this.mTextBounds);
		paramCanvas.drawText(paramString, paramInt1 - this.mTextBounds.width(),
				paramInt2 - 4, this.mTextPaint);
	}

	private void updateDistance(double paramDouble) {
		// TODO
	}

	public void onAccuracyChanged(int paramInt1, int paramInt2) {
	}

	protected void onDraw(Canvas paramCanvas) {
		super.onDraw(paramCanvas);
		//TODO
	}

    public void onLocationChanged(Location location, int i, int j)
    {
        if(!mHaveLocation)
            mHaveLocation = true;
        if(SpeedView.mWarningChecked && i > j)
            mBlackPaint.setColor(-3407872);
        else
            mBlackPaint.setColor(-11184811);
        mOrientation = location.getBearing();
        invalidate();
    }

	public void onSensorChanged(float paramFloat) {
		this.mOrientation = paramFloat;
		this.mBlackPaint.setColor(-11184811);
		postInvalidate();
	}

	public void setDistanceView(TextView paramTextView) {
	}

	public void setUseMetric(boolean paramBoolean) {
		this.mUseMetric = paramBoolean;
		this.mLastScale = -1;
		if (this.mHaveLocation)
			updateDistance(this.mDistance);
		invalidate();
	}

	public void startSweep() {
		this.mSweepTime = SystemClock.uptimeMillis();
		this.mSweepBefore = true;
	}

	public void stopSweep() {
		this.mSweepTime = 0L;
	}
}