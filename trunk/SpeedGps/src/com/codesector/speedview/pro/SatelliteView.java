/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.codesector.speedview.pro;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.util.AttributeSet;
import android.widget.Button;
import java.util.Iterator;

// Referenced classes of package com.codesector.speedview.pro:
//            SpeedView

class SatelliteView extends Button {

	public SatelliteView(Context context) {
		this(context, null);
	}

	public SatelliteView(Context context, AttributeSet attributeset) {
		this(context, attributeset, 0);
	}

	public SatelliteView(Context context, AttributeSet attributeset, int i) {
		super(context, attributeset, i);
		Point apoint[] = new Point[33];
		apoint[0] = new Point(0, 0);
		apoint[1] = new Point(7, 9);
		apoint[2] = new Point(12, 9);
		apoint[3] = new Point(7, 14);
		apoint[4] = new Point(12, 14);
		apoint[5] = new Point(17, 14);
		apoint[6] = new Point(22, 14);
		apoint[7] = new Point(12, 19);
		apoint[8] = new Point(17, 19);
		apoint[9] = new Point(22, 19);
		apoint[10] = new Point(27, 19);
		apoint[11] = new Point(32, 19);
		apoint[12] = new Point(12, 24);
		apoint[13] = new Point(17, 24);
		apoint[14] = new Point(22, 24);
		apoint[15] = new Point(27, 24);
		apoint[16] = new Point(32, 24);
		apoint[17] = new Point(17, 29);
		apoint[18] = new Point(22, 29);
		apoint[19] = new Point(27, 29);
		apoint[20] = new Point(32, 29);
		apoint[21] = new Point(37, 29);
		apoint[22] = new Point(17, 34);
		apoint[23] = new Point(22, 34);
		apoint[24] = new Point(27, 34);
		apoint[25] = new Point(32, 34);
		apoint[26] = new Point(37, 34);
		apoint[27] = new Point(27, 39);
		apoint[28] = new Point(32, 39);
		apoint[29] = new Point(37, 39);
		apoint[30] = new Point(42, 39);
		apoint[31] = new Point(37, 44);
		apoint[32] = new Point(42, 44);
		mMatrix10 = apoint;
		Point apoint1[] = new Point[33];
		apoint1[0] = new Point(0, 0);
		apoint1[1] = new Point(8, 10);
		apoint1[2] = new Point(16, 10);
		apoint1[3] = new Point(8, 18);
		apoint1[4] = new Point(16, 18);
		apoint1[5] = new Point(24, 18);
		apoint1[6] = new Point(32, 18);
		apoint1[7] = new Point(16, 26);
		apoint1[8] = new Point(24, 26);
		apoint1[9] = new Point(32, 26);
		apoint1[10] = new Point(40, 26);
		apoint1[11] = new Point(48, 26);
		apoint1[12] = new Point(16, 34);
		apoint1[13] = new Point(24, 34);
		apoint1[14] = new Point(32, 34);
		apoint1[15] = new Point(40, 34);
		apoint1[16] = new Point(48, 34);
		apoint1[17] = new Point(24, 42);
		apoint1[18] = new Point(32, 42);
		apoint1[19] = new Point(40, 42);
		apoint1[20] = new Point(48, 42);
		apoint1[21] = new Point(56, 42);
		apoint1[22] = new Point(24, 50);
		apoint1[23] = new Point(32, 50);
		apoint1[24] = new Point(40, 50);
		apoint1[25] = new Point(48, 50);
		apoint1[26] = new Point(56, 50);
		apoint1[27] = new Point(40, 58);
		apoint1[28] = new Point(48, 58);
		apoint1[29] = new Point(56, 58);
		apoint1[30] = new Point(64, 58);
		apoint1[31] = new Point(56, 66);
		apoint1[32] = new Point(64, 66);
		mMatrix15 = apoint1;
		mScreenRatio = SpeedView.mScreenRatio;
		mSignalLevel = new int[50];
		mPaint = new Paint();
		mPaint.setARGB(255, 168, 168, 168);
		mPaintSat = new Paint();
		mBackground = ((BitmapDrawable) getResources().getDrawable(2130837533))
				.getBitmap();
		mSatellite = ((BitmapDrawable) getResources().getDrawable(2130837532))
				.getBitmap();
		setHeight(mBackground.getHeight());
		setWidth(mBackground.getWidth());
		if (mScreenRatio >= 1.5F)
			mMatrix = mMatrix15;
		else
			mMatrix = mMatrix10;
	}

	void clearSatellites() {
		int i = 1;
		do {
			if (i >= 33) {
				invalidate();
				return;
			}
			mSignalLevel[i] = 0;
			i++;
		} while (true);
	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(mBackground, 0.0F, 0.0F, mPaint);
		int i = 1;
		do {
			if (i >= 33)
				return;
			if (mSignalLevel[i] > 0) {
				int j = mSignalLevel[i];
				if (j > 230)
					j = 255;
				mPaintSat.setAlpha(j);
				Point point = mMatrix[i];
				canvas.drawBitmap(mSatellite, point.x, point.y, mPaintSat);
			}
			i++;
		} while (true);
	}

	void setSatellites(GpsStatus gpsstatus) {
		Iterator localIterator = gpsstatus.getSatellites().iterator();
		int i = 1;
		if (i >= 48)
			;
		while (true) {
			if (!localIterator.hasNext()) {
				invalidate();
				this.mSignalLevel[i] = 0;
				i++;
				break;
			}
			GpsSatellite localGpsSatellite = (GpsSatellite) localIterator
					.next();
			if ((localGpsSatellite.getPrn() < 0)
					|| (localGpsSatellite.getPrn() >= 49))
				continue;
			this.mSignalLevel[localGpsSatellite.getPrn()] = (int) Math.min(
					255.0F, 10.0F * localGpsSatellite.getSnr());
		}
	}

	private Bitmap mBackground;
	private final Point mMatrix[];
	private final Point mMatrix10[];
	private final Point mMatrix15[];
	private Paint mPaint;
	private Paint mPaintSat;
	private Bitmap mSatellite;
	private float mScreenRatio;
	private int mSignalLevel[];
}

/*
 * DECOMPILATION REPORT
 *
 * Decompiled from: H:\AndroidProject\SpeedGps\libs\GPS娴嬮�.jar Total time: 18 ms
 * Jad reported messages/errors: Couldn't fully decompile method setSatellites
 * Exit status: 0 Caught exceptions:
 */