/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.codesector.speedview.pro;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import java.lang.reflect.Array;

// Referenced classes of package com.codesector.speedview.pro:
//            SpeedView, ColorPickerDialog

public class SettingsActivity extends Activity implements
		ColorPickerDialog.OnColorChangedListener {

	public SettingsActivity() {
		mTempSpeedoLimit = -1;
		mAlertSoundUri = null;
	}

	private int getArrayIndex(String as[], String s) {
		int i = Array.getLength(as);
		for (int j = 0;; j++) {
			if (j >= i)
				j = -1;
			do
				return j;
			while (as[j].equals(s));
		}

	}

	private boolean isNetworkAvailable() {
		boolean flag;
		if (((ConnectivityManager) getSystemService("connectivity"))
				.getActiveNetworkInfo() != null)
			flag = true;
		else
			flag = false;
		return flag;
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

	public void colorChanged(int i) {
		if (mFromButton.equals(mColors[0])) {
			mSpeedBarColorButton.setText(2131099869);
			mSpeedBarColorButton.setTextColor(i);
			mSpeedBarColor = i;
		} else if (mFromButton.equals(mColors[1])) {
			mPrimaryColorButton.setText(2131099869);
			mPrimaryColorButton.setTextColor(i);
			mPrimaryTextColor = i;
		} else {
			mSecondaryColorButton.setText(2131099869);
			mSecondaryColorButton.setTextColor(i);
			mSecondaryTextColor = i;
		}
	}

	protected void onActivityResult(int i, int j, Intent intent) {
		if (i != 1)
			return;
		if (j == 0) {
			mSoundAlertStatus.setText(2131099838);
			mSoundAlertButton.setChecked(false);
			mSoundAlertToggled = false;
			return;
		}
		if (intent == null) {
			return;
		} else {
			mAlertSoundUri = (Uri) intent
					.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
			String s = String.valueOf(getString(2131099839));
			StringBuilder stringbuilder = (new StringBuilder(s)).append(" ");
			Context context = getBaseContext();
			Ringtone ringtone = RingtoneManager.getRingtone(context,
					mAlertSoundUri);
			String s1 = ringtone.getTitle(getBaseContext());
			String s2 = stringbuilder.append(s1).toString();
			mSoundAlertStatus.setText(s2);
			return;
		}
	}

	public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        getWindow().setFlags(128, 128);
        setContentView(2130903047);
        //TODO
    }

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(2131230721, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onKeyDown(int i, KeyEvent keyevent) {
		boolean flag;
		if (i == 4) {
			android.content.SharedPreferences.Editor editor = getSharedPreferences(
					"PrefsFile", 0).edit();
			editor.putInt("displayUnits", mDisplayUnits);
			editor.putBoolean("warningChecked", mWarningChecked);
			editor.putInt("townSpeedLimit", mTownSpeedLimit);
			editor.putInt("highwaySpeedLimit", mHighwaySpeedLimit);
			editor.putInt("freewaySpeedLimit", mFreewaySpeedLimit);
			editor.putBoolean("soundAlertToggled", mSoundAlertToggled);
			String s;
			if (mAlertSoundUri == null)
				s = "";
			else
				s = mAlertSoundUri.toString();
			editor.putString("alertSoundUri", s);
			editor.putBoolean("vibrationChecked", mVibrationChecked);
			editor.putBoolean("digitSpeedoChecked", mDigitSpeedoChecked);
			editor.putBoolean("digitAddlDataToggled", mDigitAddlDataToggled);
			editor.putInt("digitDataSelected", mDigitDataSelected);
			editor.putBoolean("maxSpeedoChecked", mMaxSpeedoChecked);
			editor.putInt("maxSpeedoLimit", mMaxSpeedoLimit);
			editor.putBoolean("useHudChecked", mUseHudChecked);
			editor.putBoolean("advancedHudChecked", mAdvancedHudChecked);
			editor.putBoolean("advancedZoomChecked", mAdvancedZoomChecked);
			editor.putBoolean("customColorsChecked", mCustomColorsChecked);
			editor.putInt("speedBarColor", mSpeedBarColor);
			editor.putInt("primaryTextColor", mPrimaryTextColor);
			editor.putInt("secondaryTextColor", mSecondaryTextColor);
			editor.putBoolean("runInBGChecked", mRunInBGChecked);
			editor.putBoolean("trackLoggingChecked", mTrackLoggingChecked);
			editor.putInt("minTimeBetweenPts", mMinTimeBetweenPts);
			editor.putInt("minDistBetweenPts", mMinDistBetweenPts);
			editor.putBoolean("narrowingChecked", mNarrowingChecked);
			editor.putInt("minimumAccuracy", mMinimumAccuracy);
			editor.putBoolean("streetAddrChecked", mStreetAddrChecked);
			editor.putBoolean("dsblRotationChecked", mDsblRotationChecked);
			editor.putBoolean("fullScreenChecked", mFullScreenChecked);
			editor.putBoolean("backgroundChecked", mBackgroundChecked);
			editor.commit();
			finish();
			flag = true;
		} else {
			flag = super.onKeyDown(i, keyevent);
		}
		return flag;
	}

	public boolean onOptionsItemSelected(MenuItem menuitem) {
		switch (menuitem.getItemId()) {
		case 2131296694:
			(new AlertDialog.Builder(this))
					.setTitle(2131099897)
					.setMessage(2131099899)
					.setPositiveButton(
							2131099724,
							new android.content.DialogInterface.OnClickListener() {

								public void onClick(
										DialogInterface dialoginterface, int i) {
									mSpeedBarColorButton.setText(2131099868);
									mSpeedBarColorButton
											.setTextColor(-16777216);
									mSpeedBarColor = -16776961;
									mPrimaryColorButton.setText(2131099868);
									mPrimaryColorButton.setTextColor(-16777216);
									mPrimaryTextColor = -1;
									mSecondaryColorButton.setText(2131099868);
									mSecondaryColorButton
											.setTextColor(-16777216);
									mSecondaryTextColor = -3355444;
									Toast.makeText(getBaseContext(),
											2131099901, 1).show();
								}
							})
					.setNegativeButton(
							2131099725,
							new android.content.DialogInterface.OnClickListener() {

								public void onClick(
										DialogInterface dialoginterface, int i) {
								}
							}).show();
			break;
		case 2131296695:
			(new android.app.AlertDialog.Builder(this))
					.setTitle(2131099898)
					.setMessage(2131099900)
					.setPositiveButton(
							2131099724,
							new android.content.DialogInterface.OnClickListener() {

								public void onClick(
										DialogInterface dialoginterface, int i) {
									boolean flag = false;
									Intent intent = getIntent();
									intent.putExtra("display_units", 0);
									intent.putExtra("warning_checked", false);
									intent.putExtra("current_speed_limit", 0);
									intent.putExtra("town_speed_limit", 30);
									intent.putExtra("highway_speed_limit", 55);
									intent.putExtra("freeway_speed_limit", 65);
									intent.putExtra("sound_alert_toggled",
											false);
									mAlertSoundUri = null;
									intent.putExtra("alert_sound_uri",
											mAlertSoundUri);
									intent.putExtra("vibration_checked", false);
									intent.putExtra("digit_speedo_checked",
											false);
									intent.putExtra("digit_addl_data_toggled",
											false);
									intent.putExtra("digit_data_selected", -1);
									intent.putExtra("max_speedo_checked", false);
									intent.putExtra("max_speedo_limit", 160);
									intent.putExtra("use_hud_checked", false);
									intent.putExtra("advanced_hud_checked",
											false);
									intent.putExtra("advanced_zoom_checked",
											false);
									intent.putExtra("custom_colors_checked",
											false);
									intent.putExtra("speed_bar_color",
											-16776961);
									intent.putExtra("primary_text_color", -1);
									intent.putExtra("secondary_text_color",
											-3355444);
									intent.putExtra("run_in_bg_checked", false);
									intent.putExtra("track_logging_checked",
											false);
									intent.putExtra("min_time_between_pts", 0);
									intent.putExtra("min_dist_between_pts", 4);
									intent.putExtra("narrowing_checked", true);
									intent.putExtra("minimum_accuracy", 4);
									intent.putExtra("street_addr_checked", true);
									intent.putExtra("dsbl_rotation_checked",
											flag);
									intent.putExtra("full_screen_checked", true);
									intent.putExtra("background_checked", true);
									finish();
									startActivity(intent);
									Toast.makeText(getBaseContext(),
											2131099902, 1).show();
								}
							})
					.setNegativeButton(
							2131099725,
							new android.content.DialogInterface.OnClickListener() {

								public void onClick(
										DialogInterface dialoginterface, int i) {
								}
							}).show();
			break;
		}
		return super.onOptionsItemSelected(menuitem);
	}

	public boolean onPrepareOptionsMenu(Menu menu) {
		if (mSpeedBarColor != -16776961 || mPrimaryTextColor != -1
				|| mSecondaryTextColor != -3355444)
			menu.findItem(2131296694).setVisible(true);
		else
			menu.findItem(2131296694).setVisible(false);
		return super.onPrepareOptionsMenu(menu);
	}

	@SuppressLint("NewApi") public void reload() {
		Intent intent = getIntent();
		overridePendingTransition(0, 0);
		intent.addFlags(65536);
		finish();
		overridePendingTransition(0, 0);
		startActivity(intent);
	}

	private static final int PRIMARY = 1;
	private static final int SECONDARY = 2;
	private static final int SPEED_BAR = 0;
	private static final String mColors[] = { "speed bar", "primary",
			"secondary" };
	private final int CUSTOM_SPEED_LIMIT = 14;
	private final int REQUEST_ALARM_SOUND = 1;
	private Spinner mAccuracySpinner;
	private String mAddlDataValues[];
	private CheckBox mAdvancedHudCheckBox;
	private boolean mAdvancedHudChecked;
	private TextView mAdvancedHudInfo;
	private TableRow mAdvancedHudRow;
	private CheckBox mAdvancedZoomCheckBox;
	private boolean mAdvancedZoomChecked;
	private TextView mAdvancedZoomInfo;
	private TableRow mAdvancedZoomRow;
	private Uri mAlertSoundUri;
	private CheckBox mBackgroundCheckBox;
	private boolean mBackgroundChecked;
	private TableLayout mColorsTableLayout;
	private TextView mCurrentLimit;
	private int mCurrentSpeedLimit;
	private CheckBox mCustomColorsCheckBox;
	private boolean mCustomColorsChecked;
	private ToggleButton mDigitAddlDataButton;
	private TextView mDigitAddlDataStatus;
	private boolean mDigitAddlDataToggled;
	private int mDigitDataSelected;
	private CheckBox mDigitSpeedoCheckBox;
	private boolean mDigitSpeedoChecked;
	private TableLayout mDigitalTableLayout;
	private int mDisplayUnits;
	private CheckBox mDsblRotationCheckBox;
	private boolean mDsblRotationChecked;
	private TextView mDsblRotationInfo;
	private TableRow mDsblRotationRow;
	private Button mFreewayButton;
	private TableLayout mFreewayLimitLayout;
	private SeekBar mFreewayLimitSeekBar;
	private Spinner mFreewayLimitSpinner;
	private TextView mFreewayLimitValue;
	private int mFreewaySpeedLimit;
	private LinearLayout mFrequencyLayout;
	private String mFromButton;
	private CheckBox mFullScreenCheckBox;
	private boolean mFullScreenChecked;
	private TextView mFullScreenInfo;
	private TableRow mFullScreenRow;
	private Button mHighwayButton;
	private TableLayout mHighwayLimitLayout;
	private SeekBar mHighwayLimitSeekBar;
	private Spinner mHighwayLimitSpinner;
	private TextView mHighwayLimitValue;
	private int mHighwaySpeedLimit;
	private ImageView mLogo;
	private CheckBox mMaxSpeedoCheckBox;
	private boolean mMaxSpeedoChecked;
	private int mMaxSpeedoLimit;
	private int mMinDistBetweenPts;
	private Spinner mMinDistanceSpinner;
	private int mMinTimeBetweenPts;
	private Spinner mMinTimeSpinner;
	private int mMinimumAccuracy;
	private CheckBox mNarrowingCheckBox;
	private boolean mNarrowingChecked;
	private Button mPrimaryColorButton;
	private int mPrimaryTextColor;
	private CheckBox mRunInBGCheckBox;
	private boolean mRunInBGChecked;
	private Button mSecondaryColorButton;
	private int mSecondaryTextColor;
	private RelativeLayout mSettingsScreen;
	private ToggleButton mSoundAlertButton;
	private TextView mSoundAlertStatus;
	private boolean mSoundAlertToggled;
	private int mSpeedBarColor;
	private Button mSpeedBarColorButton;
	private SeekBar mSpeedoLimitSeekBar;
	private TextView mSpeedoLimitValue;
	private TableRow mSpeedoSeekbarRow;
	private CheckBox mStreetAddrCheckBox;
	private boolean mStreetAddrChecked;
	private LinearLayout mStreetAddrLayout;
	private int mTempSpeedoLimit;
	private TableLayout mTownLimitLayout;
	private SeekBar mTownLimitSeekBar;
	private Spinner mTownLimitSpinner;
	private TextView mTownLimitValue;
	private int mTownSpeedLimit;
	private CheckBox mTrackLoggingCheckBox;
	private boolean mTrackLoggingChecked;
	private Spinner mUnitsSpinner;
	private Button mUrbanAreaButton;
	private CheckBox mUseHudCheckBox;
	private boolean mUseHudChecked;
	private CheckBox mVibrationCheckBox;
	private boolean mVibrationChecked;
	private CheckBox mWarningCheckBox;
	private boolean mWarningChecked;
	private TableLayout mWarningTableLayout;
}

/*
 * DECOMPILATION REPORT
 *
 * Decompiled from: H:\AndroidProject\SpeedGps\libs\GPS娴嬮�.jar Total time: 124
 * ms Jad reported messages/errors: Couldn't fully decompile method
 * getArrayIndex Couldn't fully decompile method onActivityResult Couldn't fully
 * decompile method onCreate Couldn't fully decompile method onItemSelected
 * Couldn't fully decompile method onCheckedChanged Couldn't fully decompile
 * method onKeyDown Couldn't fully decompile method onOptionsItemSelected Exit
 * status: 0 Caught exceptions:
 */