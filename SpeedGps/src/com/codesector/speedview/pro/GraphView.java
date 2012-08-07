package com.codesector.speedview.pro;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.Button;
import com.codesector.speedview.pro.R;

class GraphView extends Button {
	public int mArrayPointer;
	private Bitmap mBackground = ((BitmapDrawable) getResources().getDrawable(
			2130837519)).getBitmap();
	private float mGraphScale = 1.0F;
	private Paint mPaint;
	private Paint mPaintLine;
	private Paint mPaintText;
	private float mScreenRatio;
	private int[] mSpeedArray;
	private int mSpeedArrayX;

	public GraphView(Context paramContext) {
		this(paramContext, null);
	}

	public GraphView(Context paramContext, AttributeSet paramAttributeSet) {
		this(paramContext, paramAttributeSet, 0);
	}

	public GraphView(Context paramContext, AttributeSet paramAttributeSet,
			int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		setHeight(this.mBackground.getHeight());
		this.mScreenRatio = SpeedView.mScreenRatio;
		this.mSpeedArrayX = (int) (294.0F * this.mScreenRatio);
		this.mPaint = new Paint();
		this.mPaint.setARGB(255, 50, 50, 50);
		this.mPaintText = new Paint();
		this.mPaintText.setARGB(200, 255, 255, 255);
		this.mPaintText.setTextAlign(Paint.Align.RIGHT);
		this.mPaintText.setTextSize(12.0F * this.mScreenRatio);
		this.mPaintText.setTypeface(Typeface.SANS_SERIF);
		this.mPaintText.setAntiAlias(true);
		this.mPaintLine = new Paint();
		this.mPaintLine.setARGB(155, 255, 255, 0);
		this.mPaintLine.setStrokeWidth(2.0F * this.mScreenRatio);
		this.mPaintLine.setStyle(Paint.Style.STROKE);
		this.mPaintLine.setAntiAlias(true);
		this.mSpeedArray = new int[1 + this.mSpeedArrayX];
		for (int i = 0;; i++) {
			if (i >= this.mSpeedArray.length)
				return;
			this.mSpeedArray[i] = 0;
		}
	}

	private void computeGraphScale() {
		int i = 0;
		int j = mSpeedArrayX;
		do {
			int k;
			if (j < -100 + mSpeedArrayX) {
				if (i >= 400)
					mGraphScale = 0.125F;
				else if (i >= 200)
					mGraphScale = 0.25F;
				else if (i >= 100)
					mGraphScale = 0.5F;
				else if (i >= 50)
					mGraphScale = 1.0F;
				else
					mGraphScale = 2.0F;
				return;
			}
			k = j + mArrayPointer;
			if (k > mSpeedArrayX)
				k -= 1 + mSpeedArrayX;
			if (mSpeedArray[k] > i)
				i = mSpeedArray[k];
			j--;
		} while (true);
	}

	String getHexArray() {
		StringBuilder localStringBuilder = new StringBuilder(
				2 * this.mSpeedArrayX);
		for (int i = 0;; i++) {
			if (i >= this.mSpeedArrayX)
				return localStringBuilder.toString();
			String str = Integer
					.toHexString(Math.min(250, this.mSpeedArray[i]));
			if (this.mSpeedArray[i] < 16)
				str = "0" + str;
			localStringBuilder.append(str);
		}
	}

	boolean isHexArrayEmpty() {
		boolean flag = true;
		int i = 0;
		do {
			if (i >= mSpeedArray.length)
				return flag;
			if (mSpeedArray[i] != 0)
				flag = false;
			i++;
		} while (true);
	}

	protected void onDraw(Canvas paramCanvas) {
		super.onDraw(paramCanvas);
		int i = -2 + getHeight();
		paramCanvas.drawBitmap(this.mBackground, 0.0F, 0.0F, this.mPaint);
		paramCanvas.drawText((new StringBuilder())
				.append((int) (100F / mGraphScale)).toString(),
				290F * mScreenRatio, 15F * mScreenRatio, mPaintText);
		int m = i;
		for (int n = 0;; n++) {
			if (n > this.mSpeedArrayX)
				return;
			int i1 = n + this.mArrayPointer;
			if (i1 > this.mSpeedArrayX)
				i1 -= 1 + this.mSpeedArrayX;
			if (n == 0)
				m = this.mSpeedArray[i1];
			float f1 = i - this.mSpeedArray[i1] * this.mGraphScale
					* this.mScreenRatio;
			float f2 = i - m * this.mGraphScale * this.mScreenRatio;
			if (f1 < 2.0F)
				f1 = 2.0F;
			if (f2 < 2.0F)
				f2 = 2.0F;
			paramCanvas.drawLine(n + 1, f1, 1, f2, this.mPaintLine);
			m = this.mSpeedArray[i1];
			int k = n + 1;
		}
	}

	void onSpeedChanged(int paramInt) {
		synchronized (this.mSpeedArray) {
			this.mSpeedArray[this.mArrayPointer] = paramInt;
			this.mArrayPointer = (1 + this.mArrayPointer);
			if (this.mArrayPointer > this.mSpeedArrayX)
				this.mArrayPointer = 0;
			computeGraphScale();
			invalidate();
		}
	}

	void resetHexArray() {
		for (int i = 0; i < mSpeedArray.length; i++) {
			this.mSpeedArray[i] = 0;
		}
		invalidate();
	}

	void setHexArray(String paramString) {
		// TODO
	}
}