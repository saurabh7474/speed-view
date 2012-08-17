/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.codesector.speedview.pro;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.*;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import java.util.List;

// Referenced classes of package com.codesector.speedview.pro:
//            SpeedView

public class FeaturedActivity extends Activity {

	private boolean checkIfLookoutInst()
    {
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

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		getWindow().setFlags(128, 128);
		setContentView(2130903044);
		mFeaturedScreen = (LinearLayout) findViewById(2131296273);
		if (SpeedView.mDsblRotationChecked){
			switch (SpeedView.mStoredOrientation) {
			case 0:
				setRequestedOrientation(1);
				break;
			case 1:
				setRequestedOrientation(0);
				break;
			case 2:
				setRequestedOrientation(8);
				break;
			case 3:
				setRequestedOrientation(4);
				break;
			default:
				break;
			}
			if (!SpeedView.mFullScreenChecked) {
				if (SpeedView.mDsblRotationChecked
						&& (SpeedView.mStoredOrientation == 1 || SpeedView.mStoredOrientation == 3)
						|| !SpeedView.mDsblRotationChecked
						&& getResources().getConfiguration().orientation == 2)
					setFullScreenMode(true);
				else
					setFullScreenMode(false);
			} else {
				setFullScreenMode(true);
			}
			if (SpeedView.mBackgroundChecked)
				mFeaturedScreen.setBackgroundResource(2130837508);
			else
				mFeaturedScreen.setBackgroundColor(-16777216);
			mMaverickDownload = (Button) findViewById(2131296274);
			mMaverickMore = (Button) findViewById(2131296275);
			mLookoutDownload = (Button) findViewById(2131296276);
			mLookoutMore = (Button) findViewById(2131296277);
			if (SpeedView.mMaverickInst)
				mMaverickDownload.setText(getString(2131099910));
			mLookoutInst = checkIfLookoutInst();
			if (mLookoutInst)
				mLookoutDownload.setText(getString(2131099910));
			mMaverickDownload
					.setOnClickListener(new android.view.View.OnClickListener() {

						public void onClick(View view) {
							if (SpeedView.mMaverickInst) {
								Intent intent = new Intent();
								if (SpeedView.mMaverickVersion == SpeedView.VERSIONS[0])
									intent.setClassName(
											"com.codesector.maverick.full",
											"com.codesector.maverick.full.Main");
								else
									intent.setClassName(
											"com.codesector.maverick.lite",
											"com.codesector.maverick.lite.Main");
								startActivity(intent);
							} else {
								startActivity(new Intent(
										"android.intent.action.VIEW",
										Uri.parse("market://details?id=com.codesector.maverick.lite")));
							}
						}
					});
			mMaverickMore
					.setOnClickListener(new android.view.View.OnClickListener() {

						public void onClick(View view) {
							Uri uri = Uri
									.parse("http://www.codesector.com/maverick.php");
							startActivity(new Intent("android.intent.action.VIEW",
									uri));
						}
					});
			mLookoutDownload
					.setOnClickListener(new android.view.View.OnClickListener() {

						public void onClick(View view) {
							if (mLookoutInst) {
								new Intent("android.intent.action.MAIN");
								Intent intent = getPackageManager()
										.getLaunchIntentForPackage(mLookoutPackName);
								intent.addCategory("android.intent.category.LAUNCHER");
								startActivity(intent);
							} else {
								startActivity(new Intent(
										"android.intent.action.VIEW",
										Uri.parse("market://details?id=com.lookout")));
							}
						}
					});
			mLookoutMore
					.setOnClickListener(new android.view.View.OnClickListener() {

						public void onClick(View view) {
							Uri uri = Uri.parse("https://www.mylookout.com/");
							startActivity(new Intent("android.intent.action.VIEW",
									uri));
						}
					});
		}
	}

	private LinearLayout mFeaturedScreen;
	private Button mLookoutDownload;
	private boolean mLookoutInst;
	private Button mLookoutMore;
	private String mLookoutPackName;
	private Button mMaverickDownload;
	private Button mMaverickMore;

}

/*
 * DECOMPILATION REPORT
 *
 * Decompiled from: H:\AndroidProject\SpeedGps\libs\GPS娴嬮�.jar Total time: 30 ms
 * Jad reported messages/errors: Couldn't fully decompile method
 * checkIfLookoutInst Couldn't fully decompile method onCreate Exit status: 0
 * Caught exceptions:
 */