package com.codesector.speedview.pro;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.util.AttributeSet;
import android.widget.Button;
import com.codesector.speedview.pro.R;
import java.util.Iterator;

class SatelliteView extends Button {
	private Bitmap mBackground;
	private final Point[] mMatrix;
	private final Point[] mMatrix10;
	private final Point[] mMatrix15;
	private Paint mPaint;
	private Paint mPaintSat;
	private Bitmap mSatellite;
	private float mScreenRatio;
	private int[] mSignalLevel;

	public SatelliteView(Context paramContext) {
		this(paramContext, null);
	}

	public SatelliteView(Context paramContext, AttributeSet paramAttributeSet) {
		this(paramContext, paramAttributeSet, 0);
	}

	public SatelliteView(Context paramContext, AttributeSet paramAttributeSet,
			int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		mMatrix10 = new Point[33];
		mMatrix10[0] = new Point(0, 0);
		mMatrix10[1] = new Point(7, 9);
		mMatrix10[2] = new Point(12, 9);
		mMatrix10[3] = new Point(7, 14);
		mMatrix10[4] = new Point(12, 14);
		mMatrix10[5] = new Point(17, 14);
		mMatrix10[6] = new Point(22, 14);
		mMatrix10[7] = new Point(12, 19);
		mMatrix10[8] = new Point(17, 19);
		mMatrix10[9] = new Point(22, 19);
		mMatrix10[10] = new Point(27, 19);
		mMatrix10[11] = new Point(32, 19);
		mMatrix10[12] = new Point(12, 24);
		mMatrix10[13] = new Point(17, 24);
		mMatrix10[14] = new Point(22, 24);
		mMatrix10[15] = new Point(27, 24);
		mMatrix10[16] = new Point(32, 24);
		mMatrix10[17] = new Point(17, 29);
		mMatrix10[18] = new Point(22, 29);
		mMatrix10[19] = new Point(27, 29);
		mMatrix10[20] = new Point(32, 29);
		mMatrix10[21] = new Point(37, 29);
		mMatrix10[22] = new Point(17, 34);
		mMatrix10[23] = new Point(22, 34);
		mMatrix10[24] = new Point(27, 34);
		mMatrix10[25] = new Point(32, 34);
		mMatrix10[26] = new Point(37, 34);
		mMatrix10[27] = new Point(27, 39);
		mMatrix10[28] = new Point(32, 39);
		mMatrix10[29] = new Point(37, 39);
		mMatrix10[30] = new Point(42, 39);
		mMatrix10[31] = new Point(37, 44);
		mMatrix10[32] = new Point(42, 44);
		mMatrix15 = new Point[33];
		mMatrix15[0] = new Point(0, 0);
		mMatrix15[1] = new Point(8, 10);
		mMatrix15[2] = new Point(16, 10);
		mMatrix15[3] = new Point(8, 18);
		mMatrix15[4] = new Point(16, 18);
		mMatrix15[5] = new Point(24, 18);
		mMatrix15[6] = new Point(32, 18);
		mMatrix15[7] = new Point(16, 26);
		mMatrix15[8] = new Point(24, 26);
		mMatrix15[9] = new Point(32, 26);
		mMatrix15[10] = new Point(40, 26);
		mMatrix15[11] = new Point(48, 26);
		mMatrix15[12] = new Point(16, 34);
		mMatrix15[13] = new Point(24, 34);
		mMatrix15[14] = new Point(32, 34);
		mMatrix15[15] = new Point(40, 34);
		mMatrix15[16] = new Point(48, 34);
		mMatrix15[17] = new Point(24, 42);
		mMatrix15[18] = new Point(32, 42);
		mMatrix15[19] = new Point(40, 42);
		mMatrix15[20] = new Point(48, 42);
		mMatrix15[21] = new Point(56, 42);
		mMatrix15[22] = new Point(24, 50);
		mMatrix15[23] = new Point(32, 50);
		mMatrix15[24] = new Point(40, 50);
		mMatrix15[25] = new Point(48, 50);
		mMatrix15[26] = new Point(56, 50);
		mMatrix15[27] = new Point(40, 58);
		mMatrix15[28] = new Point(48, 58);
		mMatrix15[29] = new Point(56, 58);
		mMatrix15[30] = new Point(64, 58);
		mMatrix15[31] = new Point(56, 66);
		mMatrix15[32] = new Point(64, 66);
		this.mScreenRatio = SpeedView.mScreenRatio;
		this.mSignalLevel = new int[50];
		this.mPaint = new Paint();
		this.mPaint.setARGB(255, 168, 168, 168);
		this.mPaintSat = new Paint();
		this.mBackground = ((BitmapDrawable) getResources().getDrawable(
				R.drawable.satellite_view)).getBitmap();
		this.mSatellite = ((BitmapDrawable) getResources().getDrawable(
				R.drawable.satellite_point)).getBitmap();
		setHeight(this.mBackground.getHeight());
		setWidth(this.mBackground.getWidth());
		if (mScreenRatio >= 1.5F) {
			mMatrix = mMatrix15;
		} else {
			mMatrix = mMatrix10;
		}
	}

	void clearSatellites() {
		for (int i = 1; i < 33; i++) {
			this.mSignalLevel[i] = 0;
		}
		invalidate();
	}

	protected void onDraw(Canvas paramCanvas) {
		//TODO
	}

	void setSatellites(GpsStatus paramGpsStatus) {
		//TODO
	}
}