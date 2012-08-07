package com.codesector.speedview.pro;

import android.app.Activity;
import android.view.Display;
import android.view.WindowManager;
import com.codesector.speedview.pro.R;

public class DisplayHelper extends Activity {
	public static int getRotation(SpeedView paramSpeedView) {
		return ((WindowManager) paramSpeedView.getSystemService("window"))
				.getDefaultDisplay().getRotation();
	}
}