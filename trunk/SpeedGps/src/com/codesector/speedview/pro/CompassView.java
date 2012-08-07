package com.codesector.speedview.pro;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.Button;
import com.codesector.speedview.pro.R;

class CompassView extends Button {
	private Bitmap mBackground = ((BitmapDrawable) getResources().getDrawable(
			R.drawable.compass_view)).getBitmap();
	private float mBearing = 0.0F;
	private Paint mPaint;
	private Path mPointerPath;
	private float mScreenRatio = SpeedView.mScreenRatio;

	public CompassView(Context paramContext) {
		this(paramContext, null);
	}

	public CompassView(Context paramContext, AttributeSet paramAttributeSet) {
		this(paramContext, paramAttributeSet, 0);
	}

	public CompassView(Context paramContext, AttributeSet paramAttributeSet,
			int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		setHeight(this.mBackground.getHeight());
		this.mPaint = new Paint();
		this.mPaint.setARGB(255, 235, 235, 235);
		this.mPointerPath = new Path();
		this.mPointerPath.moveTo(142.0F * this.mScreenRatio,
				39.0F * this.mScreenRatio);
		this.mPointerPath.lineTo(152.0F * this.mScreenRatio,
				39.0F * this.mScreenRatio);
		this.mPointerPath.lineTo(147.0F * this.mScreenRatio,
				27.0F * this.mScreenRatio);
	}

	protected void onDraw(Canvas paramCanvas) {
		super.onDraw(paramCanvas);
		paramCanvas.drawBitmap(this.mBackground,
				(float) (-(1.78D * this.mBearing * this.mScreenRatio)), 0.0F,
				this.mPaint);
		paramCanvas.drawPath(this.mPointerPath, this.mPaint);
	}

	void onSpeedChanged(float paramFloat) {
		this.mBearing = paramFloat;
		invalidate();
	}
}