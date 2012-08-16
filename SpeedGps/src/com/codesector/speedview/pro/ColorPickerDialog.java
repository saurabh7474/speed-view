/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.codesector.speedview.pro;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.*;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

@SuppressLint("ParserError")
public class ColorPickerDialog extends Dialog {
	private static class ColorPickerView extends View {

		private int ave(int i, int j, float f) {
			return i + Math.round(f * (float) (j - i));
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
				int i = mCenterPaint.getColor();
				mCenterPaint.setStyle(android.graphics.Paint.Style.STROKE);
				if (mHighlightCenter)
					mCenterPaint.setAlpha(255);
				else
					mCenterPaint.setAlpha(128);
				canvas.drawCircle(0.0F, 0.0F,
						32F + mCenterPaint.getStrokeWidth(), mCenterPaint);
				mCenterPaint.setStyle(android.graphics.Paint.Style.FILL);
				mCenterPaint.setColor(i);
			}
		}

		protected void onMeasure(int i, int j) {
			setMeasuredDimension(200, 200);
		}

		public boolean onTouchEvent(MotionEvent motionevent) {
			float f = motionevent.getX() - 100F;
			float f1 = motionevent.getY() - 100F;
			boolean inCenter = java.lang.Math.sqrt(f * f + f1 * f1) <= CENTER_RADIUS;

			switch (motionevent.getAction()) {
			case MotionEvent.ACTION_DOWN:
				// 0
				mTrackingCenter = inCenter;
				if (inCenter) {
					mHighlightCenter = true;
					invalidate();
				}
				break;
			case MotionEvent.ACTION_MOVE:
				// 1
				if (mTrackingCenter) {
					if (mHighlightCenter != inCenter) {
						mHighlightCenter = inCenter;
						invalidate();
					}
				} else {
					float angle = (float) java.lang.Math.atan2(f1, f);
					// need to turn angle [-PI ... PI] into unit [0....1]
					float unit = angle / (2 * PI);
					if (unit < 0) {
						unit += 1;
					}
					mCenterPaint.setColor(interpColor(mColors, unit));
					invalidate();
				}
				break;
			case MotionEvent.ACTION_UP:
				// 2
				if (mTrackingCenter) {
					if (inCenter)
						mListener.colorChanged(mCenterPaint.getColor());
					mTrackingCenter = false;
					invalidate();
				}
				break;
			}
			return true;
		}

		private static final int CENTER_RADIUS = 32;
		private static final int CENTER_X = 100;
		private static final int CENTER_Y = 100;
		private static final float PI = 3.141593F;
		private final Paint mCenterPaint = new Paint(1);
		private final int mColors[] = { -65536, -65281, -16776961, -16711681,
				-16711936, -256, -65536 };
		private boolean mHighlightCenter;
		private final OnColorChangedListener mListener;
		private final Paint mPaint = new Paint(1);
		private boolean mTrackingCenter;

		ColorPickerView(Context context,
				OnColorChangedListener oncolorchangedlistener, int i) {
			super(context);
			mListener = oncolorchangedlistener;
			SweepGradient sweepgradient = new SweepGradient(0.0F, 0.0F,
					mColors, null);
			mPaint.setShader(sweepgradient);
			mPaint.setStyle(android.graphics.Paint.Style.STROKE);
			mPaint.setStrokeWidth(32F);
			mCenterPaint.setColor(i);
			mCenterPaint.setStrokeWidth(5F);
		}
	}

	public static interface OnColorChangedListener {
		public abstract void colorChanged(int i);
	}

	public ColorPickerDialog(Context context,
			OnColorChangedListener oncolorchangedlistener, int i) {
		super(context);
		mListener = oncolorchangedlistener;
		mInitialColor = i;
	}

	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		OnColorChangedListener oncolorchangedlistener = new OnColorChangedListener() {

			public void colorChanged(int i) {
				mListener.colorChanged(i);
				dismiss();
			}
		};
		LinearLayout linearlayout = new LinearLayout(getContext());
		linearlayout.setOrientation(1);
		linearlayout.setGravity(17);
		linearlayout.setPadding(10, 10, 10, 10);
		linearlayout.addView(new ColorPickerView(getContext(),
				oncolorchangedlistener, mInitialColor),
				new android.widget.LinearLayout.LayoutParams(-2, -2));
		setContentView(linearlayout);
		setTitle(2131099868);
	}

	private final int mInitialColor;
	private final OnColorChangedListener mListener;

}

/*
 * DECOMPILATION REPORT
 * 
 * Decompiled from: H:\AndroidProject\SpeedGps\libs\GPS测速.jar Total time: 20 ms
 * Jad reported messages/errors: Couldn't fully decompile method onTouchEvent
 * Exit status: 0 Caught exceptions:
 */