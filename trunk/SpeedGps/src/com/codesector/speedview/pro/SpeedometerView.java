package com.codesector.speedview.pro;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.Button;
import com.codesector.speedview.pro.R;

class SpeedometerView extends Button {
	private Bitmap mAnalogSpeedoBg = ((BitmapDrawable) getResources()
			.getDrawable(2130837507)).getBitmap();
	private String mDataString = "";
	private int mLimit;
	private float mMaxSpeed;
	private RectF mOval = new RectF(63.0F * this.mScreenRatio,
			47.0F * this.mScreenRatio, 252.0F * this.mScreenRatio,
			238.0F * this.mScreenRatio);
	private RectF mOvalTop = new RectF(54.0F * this.mScreenRatio,
			38.0F * this.mScreenRatio, 260.0F * this.mScreenRatio,
			247.0F * this.mScreenRatio);
	private Paint mPaint = new Paint();
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
	private float mScreenRatio = SpeedView.mScreenRatio;
	private int mSpeed = -1;
	private double mSpeedPointX = -130.0D;
	private double mSpeedoLimit = 160.0D;
	private final float mStart = 162.0F;

	public SpeedometerView(Context paramContext) {
		this(paramContext, null);
	}

	public SpeedometerView(Context paramContext, AttributeSet paramAttributeSet) {
		this(paramContext, paramAttributeSet, 0);
	}

	public SpeedometerView(Context paramContext,
			AttributeSet paramAttributeSet, int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		this.mPaint.setARGB(255, 50, 50, 50);
		this.mPaintText = new Paint();
		this.mPaintText.setAntiAlias(true);
		this.mPaintText.setTextAlign(Paint.Align.CENTER);
		this.mPaintArc = new Paint();
		this.mPaintArc.setAntiAlias(true);
		this.mPaintArc.setStyle(Paint.Style.STROKE);
		this.mPaintArc.setStrokeWidth(6.0F * this.mScreenRatio);
		this.mPaintArcTop = new Paint();
		this.mPaintArcTop.setAntiAlias(true);
		this.mPaintArcTop.setARGB(50, 255, 255, 255);
		this.mPaintArcTop.setStyle(Paint.Style.STROKE);
		this.mPaintArcTop.setStrokeWidth(12.0F * this.mScreenRatio);
		this.mPathOvalTop = new RectF(0.0F * this.mScreenRatio,
				20.0F * this.mScreenRatio, 640.0F * this.mScreenRatio,
				440.0F * this.mScreenRatio);
		this.mPathOvalBtm = new RectF(0.0F * this.mScreenRatio,
				80.0F * this.mScreenRatio, 560.0F * this.mScreenRatio,
				400.0F * this.mScreenRatio);
		this.mPath = new Path();
		this.mPathSpeed = new Path();
		this.mPath
				.moveTo(35.0F * this.mScreenRatio, 162.0F * this.mScreenRatio);
		this.mPath
				.lineTo(31.0F * this.mScreenRatio, 162.0F * this.mScreenRatio);
		this.mPath
				.lineTo(22.0F * this.mScreenRatio, 155.0F * this.mScreenRatio);
		this.mPath
				.lineTo(22.0F * this.mScreenRatio, 151.0F * this.mScreenRatio);
		this.mPath.arcTo(this.mPathOvalTop, 208.0F, 48.5F);
		this.mPath
				.lineTo(300.0F * this.mScreenRatio, 80.0F * this.mScreenRatio);
		this.mPath.arcTo(this.mPathOvalBtm, 270.0F, -61.0F);
		this.mPathPaint = new Paint();
		this.mPathPaint.setAntiAlias(true);
		this.mPathPaint.setColor(-3355444);
		this.mPathPaint.setStyle(Paint.Style.STROKE);
		this.mPathPaint.setStrokeWidth(1.0F);
		this.mPathPaint.setStrokeJoin(Paint.Join.ROUND);
		this.mPathPaint.setStrokeCap(Paint.Cap.ROUND);
		this.mPathBgPaint = new Paint();
		this.mPathBgPaint.setAntiAlias(true);
		this.mPathBgPaint.setStyle(Paint.Style.FILL);
		this.mPathBgPaint.setColor(281857228);
		this.mPathFillPaint = new Paint();
		this.mPathFillPaint.setAntiAlias(true);
		this.mPathFillPaint.setStyle(Paint.Style.FILL);
		this.mPaintTextSpeed = new Paint();
		this.mPaintTextSpeed.setAntiAlias(true);
		this.mPaintTextSpeed.setTextSize(60.0F * this.mScreenRatio);
		this.mPaintTextSpeed.setTextAlign(Paint.Align.RIGHT);
		this.mPaintTextUnits = new Paint();
		this.mPaintTextUnits.setAntiAlias(true);
		this.mPaintTextUnits.setColor(-3355444);
		this.mPaintTextUnits.setTextSize(20.0F * this.mScreenRatio);
		this.mPaintTextUnits.setTextAlign(Paint.Align.RIGHT);
		this.mPaintTextData = new Paint();
		this.mPaintTextData.setAntiAlias(true);
		this.mPaintTextData.setColor(-3355444);
		this.mPaintTextData.setTextSize(20.0F * this.mScreenRatio);
		this.mPaintTextData.setTextAlign(Paint.Align.LEFT);
		setHeight(this.mAnalogSpeedoBg.getHeight());
	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (!SpeedView.mDigitSpeedoChecked) {
			canvas.drawBitmap(mAnalogSpeedoBg, 0.0F, 0.0F, mPaint);
			int i;
			float f;
			String s;
			if (SpeedView.mCustomColorsChecked)
				i = SpeedView.mSpeedBarColor;
			else
				i = -16776961;
			mPaintArc.setColor(i);
			if (SpeedView.mWarningChecked && mSpeed > mLimit) {
				mPaintText.setColor(-65536);
			} else {
				Paint paint1 = mPaintText;
				int j;
				if (SpeedView.mCustomColorsChecked)
					j = SpeedView.mPrimaryTextColor;
				else
					j = -1;
				paint1.setColor(j);
			}
			if (mSpeed >= 100)
				f = 90F * mScreenRatio;
			else
				f = 100F * mScreenRatio;
			mPaintText.setTextSize(f);
			if (mSpeed != -1)
				s = (new StringBuilder()).append(mSpeed).toString();
			else
				s = "---";
			canvas.drawText(s, 160F * mScreenRatio, 170F * mScreenRatio,
					mPaintText);
			if (mSpeed == -1) {
				return;
			} else {
				switch (SpeedView.mDisplayUnits) {
				case 0: // '\0'
					float f5;
					float f6;
					if (mSpeed < 160)
						f5 = (float) (1.3500000000000001D * (double) mSpeed);
					else
						f5 = 217F;
					canvas.drawArc(mOval, 162F, f5, false, mPaintArc);
					if ((int) (2.2400000000000002D * (double) mMaxSpeed) < 160)
						f6 = (float) (1.3500000000000001D * (double) (int) (2.2400000000000002D * (double) mMaxSpeed));
					else
						f6 = 217F;
					canvas.drawArc(mOvalTop, 162F, f6, false, mPaintArcTop);
					break;

				case 1: // '\001'
					RectF rectf2 = mOval;
					float f3;
					RectF rectf3;
					float f4;
					if (mSpeed < 240)
						f3 = (float) (0.90000000000000002D * (double) mSpeed);
					else
						f3 = 217F;
					canvas.drawArc(rectf2, 162F, f3, false, mPaintArc);
					rectf3 = mOvalTop;
					if ((int) (3.6000000000000001D * (double) mMaxSpeed) < 240)
						f4 = (float) (0.90000000000000002D * (double) (int) (3.6000000000000001D * (double) mMaxSpeed));
					else
						f4 = 217F;
					canvas.drawArc(rectf3, 162F, f4, false, mPaintArcTop);
					break;

				case 2: // '\002'
					RectF rectf = mOval;
					float f1;
					RectF rectf1;
					float f2;
					if (mSpeed < 60)
						f1 = (float) (3.6000000000000001D * (double) mSpeed);
					else
						f1 = 217F;
					canvas.drawArc(rectf, 162F, f1, false, mPaintArc);
					rectf1 = mOvalTop;
					if ((int) (1.9438445D * (double) mMaxSpeed) < 60)
						f2 = (float) (3.6000000000000001D * (double) (int) (1.9438445D * (double) mMaxSpeed));
					else
						f2 = 217F;
					canvas.drawArc(rectf1, 162F, f2, false, mPaintArcTop);
					break;
				}
			}
		} else {
			canvas.drawPath(mPath, mPathBgPaint);
			Paint paint3 = mPathFillPaint;
			int k;
			Paint paint5;
			float f7;
			String s1;
			float f8;
			if (SpeedView.mCustomColorsChecked)
				k = SpeedView.mSpeedBarColor;
			else
				k = -16776961;
			paint3.setColor(k);
			if (SpeedView.mWarningChecked && mSpeed > mLimit) {
				mPaintTextSpeed.setColor(-65536);
			} else {
				Paint paint4 = mPaintTextSpeed;
				int l;
				if (SpeedView.mCustomColorsChecked)
					l = SpeedView.mPrimaryTextColor;
				else
					l = -1;
				paint4.setColor(l);
			}
			paint5 = mPaintTextSpeed;
			if (mSpeed >= 100)
				f7 = 54F * mScreenRatio;
			else
				f7 = 60F * mScreenRatio;
			paint5.setTextSize(f7);
			if (mSpeed != -1)
				s1 = (new StringBuilder()).append(mSpeed).toString();
			else
				s1 = "---";
			if (SpeedView.mDisplayUnits == 0)
				f8 = 240F * mScreenRatio;
			else
				f8 = 234F * mScreenRatio;
			canvas.drawText(s1, f8, 170F * mScreenRatio, mPaintTextSpeed);
			canvas.drawText(
					(new StringBuilder()).append(
							SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits])
							.toString(), 294F * mScreenRatio,
					170F * mScreenRatio, mPaintTextUnits);
			if (!mDataString.equals(""))
				canvas.drawText(mDataString, 27F * mScreenRatio,
						35F * mScreenRatio, mPaintTextData);
			if (SpeedView.mMaxSpeedoChecked)
				mSpeedoLimit = SpeedView.mMaxSpeedoLimit;
			if (mSpeed != -1) {
				mSpeedPointX = -123D + 353D * ((double) mSpeed / mSpeedoLimit);
				canvas.save();
				mPathSpeed.reset();
				mPathSpeed.setLastPoint(0.0F, 200F * mScreenRatio);
				mPathSpeed.lineTo(0.0F, 10F * mScreenRatio);
				mPathSpeed.lineTo((float) (int) mSpeedPointX * mScreenRatio,
						10F * mScreenRatio);
				mPathSpeed.lineTo(
						(int) ((200D + mSpeedPointX) * (double) mScreenRatio),
						210F * mScreenRatio);
				mPathSpeed.lineTo(0.0F, 200F * mScreenRatio);
				try {
					canvas.clipPath(mPathSpeed);
				} catch (UnsupportedOperationException unsupportedoperationexception) {
					unsupportedoperationexception.printStackTrace();
				}
				canvas.drawPath(mPath, mPathFillPaint);
				canvas.restore();
			}
			canvas.drawPath(mPath, mPathPaint);
		}
	}

	void onSpeedChanged(int paramInt) {
		this.mSpeed = paramInt;
		invalidate();
	}

	void onSpeedChanged(int paramInt1, int paramInt2, float paramFloat) {
		this.mSpeed = paramInt1;
		this.mLimit = paramInt2;
		this.mMaxSpeed = paramFloat;
		invalidate();
	}

	void onSpeedChanged(int paramInt1, int paramInt2, float paramFloat,
			String paramString) {
		this.mSpeed = paramInt1;
		this.mLimit = paramInt2;
		this.mMaxSpeed = paramFloat;
		this.mDataString = paramString;
		invalidate();
	}

	void refreshView() {
		if (SpeedView.mDigitAddlDataToggled)
			switch (SpeedView.mDigitDataSelected) {
			default:
				this.mDataString = "";
				invalidate();
			case 0:
				this.mDataString = getResources().getString(2131099853);
			case 1:
				this.mDataString = getResources().getString(2131099742);
			case 2:
				this.mDataString = getResources().getString(2131099745);
			case 3:
				this.mDataString = getResources().getString(2131099855);
			case 4:
				this.mDataString = getResources().getString(2131099856);
			}
	}

	void setDisplayUnits(int paramInt) {
		switch (paramInt) {
		default:
			invalidate();
		case 0:
			this.mAnalogSpeedoBg = ((BitmapDrawable) getResources()
					.getDrawable(2130837507)).getBitmap();
			this.mSpeedoLimit = 160.0D;
		case 1:
			this.mAnalogSpeedoBg = ((BitmapDrawable) getResources()
					.getDrawable(2130837505)).getBitmap();
			this.mSpeedoLimit = 240.0D;
		case 2:
			this.mAnalogSpeedoBg = ((BitmapDrawable) getResources()
					.getDrawable(2130837506)).getBitmap();
			this.mSpeedoLimit = 60.0D;
		}
	}
}