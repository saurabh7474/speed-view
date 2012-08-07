package com.codesector.speedview.pro;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import com.codesector.speedview.pro.R;
import java.util.List;

public class FeaturedActivity extends Activity {
	private LinearLayout mFeaturedScreen;
	private Button mLookoutDownload;
	private boolean mLookoutInst;
	private Button mLookoutMore;
	private String mLookoutPackName;
	private Button mMaverickDownload;
	private Button mMaverickMore;

	private boolean checkIfLookoutInst() {
		//TODO
		return false;
	}

	private void setFullScreenMode(boolean flag) {
		android.view.WindowManager.LayoutParams layoutparams = getWindow()
				.getAttributes();
		if (flag)
			layoutparams.flags = 1024 | layoutparams.flags;
		else
			layoutparams.flags = -1025 & layoutparams.flags;
		getWindow().setAttributes(layoutparams);
	}

	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		//TODO
	}
}