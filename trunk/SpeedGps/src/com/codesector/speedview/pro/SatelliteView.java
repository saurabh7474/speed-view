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
	private final Point mMatrix10[] = { new Point(0, 0), new Point(7, 9),
			new Point(12, 9), new Point(7, 14), new Point(12, 14),
			new Point(17, 14), new Point(22, 14), new Point(12, 19),
			new Point(17, 19), new Point(22, 19), new Point(27, 19),
			new Point(32, 19), new Point(12, 24), new Point(17, 24),
			new Point(22, 24), new Point(27, 24), new Point(32, 24),
			new Point(17, 29), new Point(22, 29), new Point(27, 29),
			new Point(32, 29), new Point(37, 29), new Point(17, 34),
			new Point(22, 34), new Point(27, 34), new Point(32, 34),
			new Point(37, 34), new Point(27, 39), new Point(32, 39),
			new Point(37, 39), new Point(42, 39), new Point(37, 44),
			new Point(42, 44) };
	private final Point mMatrix15[] = { new Point(0, 0), new Point(8, 10),
			new Point(16, 10), new Point(8, 18), new Point(16, 18),
			new Point(24, 18), new Point(32, 18), new Point(16, 26),
			new Point(24, 26), new Point(32, 26), new Point(40, 26),
			new Point(48, 26), new Point(16, 34), new Point(24, 34),
			new Point(32, 34), new Point(40, 34), new Point(48, 34),
			new Point(24, 42), new Point(32, 42), new Point(40, 42),
			new Point(48, 42), new Point(56, 42), new Point(24, 50),
			new Point(32, 50), new Point(40, 50), new Point(48, 50),
			new Point(56, 50), new Point(40, 58), new Point(48, 58),
			new Point(56, 58), new Point(64, 58), new Point(56, 66),
			new Point(64, 66) };
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