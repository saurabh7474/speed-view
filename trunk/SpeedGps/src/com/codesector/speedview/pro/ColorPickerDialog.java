package com.codesector.speedview.pro;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.codesector.speedview.pro.R;

public class ColorPickerDialog extends Dialog {
	private final int mInitialColor;
	private OnColorChangedListener mListener;

	public ColorPickerDialog(Context paramContext,
			OnColorChangedListener paramOnColorChangedListener, int paramInt) {
		super(paramContext);
		this.mListener = paramOnColorChangedListener;
		this.mInitialColor = paramInt;
	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		mListener = new OnColorChangedListener() {
			public void colorChanged(int paramInt) {
				ColorPickerDialog.this.mListener.colorChanged(paramInt);
				ColorPickerDialog.this.dismiss();
			}
		};
		LinearLayout localLinearLayout = new LinearLayout(getContext());
		localLinearLayout.setOrientation(1);
		localLinearLayout.setGravity(17);
		localLinearLayout.setPadding(10, 10, 10, 10);
		localLinearLayout.addView(new ColorPickerView(getContext(), mListener,
				this.mInitialColor), new LinearLayout.LayoutParams(-2, -2));
		setContentView(localLinearLayout);
		setTitle(2131099868);
	}

	private static class ColorPickerView extends View {


		private static final int CENTER_RADIUS = 32;
		private static final int CENTER_X = 100;
		private static final int CENTER_Y = 100;
		private static final float PI = 3.141593F;
		private final Paint mCenterPaint;
		private final int[] mColors = null;
		private boolean mHighlightCenter;
		private final ColorPickerDialog.OnColorChangedListener mListener;
		private final Paint mPaint;
		private boolean mTrackingCenter;

		ColorPickerView(
				Context paramContext,
				ColorPickerDialog.OnColorChangedListener paramOnColorChangedListener,
				int paramInt) {
			super(paramContext);
			this.mListener = paramOnColorChangedListener;
			mColors[0] = -65536;
			mColors[1] = -65281;
			mColors[2] = -16776961;
			mColors[3] = -16711681;
			mColors[4] = -16711936;
			mColors[5] = -256;
			mColors[6] = -65536;
			SweepGradient localSweepGradient = new SweepGradient(0.0F, 0.0F,
					this.mColors, null);
			this.mPaint = new Paint(1);
			this.mPaint.setShader(localSweepGradient);
			this.mPaint.setStyle(Paint.Style.STROKE);
			this.mPaint.setStrokeWidth(32.0F);
			this.mCenterPaint = new Paint(1);
			this.mCenterPaint.setColor(paramInt);
			this.mCenterPaint.setStrokeWidth(5.0F);
		}

		private int ave(int paramInt1, int paramInt2, float paramFloat) {
			return paramInt1 + Math.round(paramFloat * (paramInt2 - paramInt1));
		}

		private int interpColor(int ai[], float f) {
			int l;
			if (f <= 0.0F)
				l = ai[0];
			else if (f >= 1.0F) {
				l = ai[-1 + ai.length];
			} else {
				float f1 = f * (float) (-1 + ai.length);
				int i = (int) f1;
				float f2 = f1 - (float) i;
				int j = ai[i];
				int k = ai[i + 1];
				l = Color.argb(ave(Color.alpha(j), Color.alpha(k), f2),
						ave(Color.red(j), Color.red(k), f2),
						ave(Color.green(j), Color.green(k), f2),
						ave(Color.blue(j), Color.blue(k), f2));
			}
			return l;
		}

		protected void onDraw(Canvas canvas) {
			float f = 100F - 0.5F * mPaint.getStrokeWidth();
			canvas.translate(100F, 100F);
			canvas.drawOval(new RectF(-f, -f, f, f), mPaint);
			canvas.drawCircle(0.0F, 0.0F, 32F, mCenterPaint);
			if (mTrackingCenter) {
				mCenterPaint.setStyle(android.graphics.Paint.Style.STROKE);
				if (mHighlightCenter)
					mCenterPaint.setAlpha(255);
				else
					mCenterPaint.setAlpha(128);
				canvas.drawCircle(0.0F, 0.0F,
						32F + mCenterPaint.getStrokeWidth(), mCenterPaint);
				mCenterPaint.setStyle(android.graphics.Paint.Style.FILL);
				mCenterPaint.setColor(mCenterPaint.getColor());
			}
		}

		protected void onMeasure(int paramInt1, int paramInt2) {
			setMeasuredDimension(200, 200);
		}

		public boolean onTouchEvent(MotionEvent paramMotionEvent) {
			//TODO
			return false;
		}
	}

	public static abstract interface OnColorChangedListener {
		public abstract void colorChanged(int paramInt);
	}
}