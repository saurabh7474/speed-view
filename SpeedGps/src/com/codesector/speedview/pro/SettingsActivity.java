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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

import java.lang.reflect.Array;

// Referenced classes of package com.codesector.speedview.pro:
//            SpeedView, ColorPickerDialog

public class SettingsActivity extends Activity implements
		ColorPickerDialog.OnColorChangedListener {

	private Context context = SettingsActivity.this;

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

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		getWindow().setFlags(128, 128);
		setContentView(2130903047);
		mSettingsScreen = (RelativeLayout) findViewById(2131296479);
		Bundle bundle1 = getIntent().getExtras();
		mDisplayUnits = bundle1.getInt("display_units");
		mWarningChecked = bundle1.getBoolean("warning_checked");
		mCurrentSpeedLimit = bundle1.getInt("current_speed_limit");
		mTownSpeedLimit = bundle1.getInt("town_speed_limit");
		mHighwaySpeedLimit = bundle1.getInt("highway_speed_limit");
		mFreewaySpeedLimit = bundle1.getInt("freeway_speed_limit");
		mSoundAlertToggled = bundle1.getBoolean("sound_alert_toggled");
		mAlertSoundUri = (Uri) bundle1.getParcelable("alert_sound_uri");
		mVibrationChecked = bundle1.getBoolean("vibration_checked");
		mDigitSpeedoChecked = bundle1.getBoolean("digit_speedo_checked");
		mDigitAddlDataToggled = bundle1.getBoolean("digit_addl_data_toggled");
		mDigitDataSelected = bundle1.getInt("digit_data_selected");
		mMaxSpeedoChecked = bundle1.getBoolean("max_speedo_checked");
		mMaxSpeedoLimit = bundle1.getInt("max_speedo_limit");
		mUseHudChecked = bundle1.getBoolean("use_hud_checked");
		mAdvancedHudChecked = bundle1.getBoolean("advanced_hud_checked");
		mAdvancedZoomChecked = bundle1.getBoolean("advanced_zoom_checked");
		mCustomColorsChecked = bundle1.getBoolean("custom_colors_checked");
		mSpeedBarColor = bundle1.getInt("speed_bar_color");
		mPrimaryTextColor = bundle1.getInt("primary_text_color");
		mSecondaryTextColor = bundle1.getInt("secondary_text_color");
		mRunInBGChecked = bundle1.getBoolean("run_in_bg_checked");
		mTrackLoggingChecked = bundle1.getBoolean("track_logging_checked");
		mMinTimeBetweenPts = bundle1.getInt("min_time_between_pts");
		mMinDistBetweenPts = bundle1.getInt("min_dist_between_pts");
		mNarrowingChecked = bundle1.getBoolean("narrowing_checked");
		mMinimumAccuracy = bundle1.getInt("minimum_accuracy");
		mStreetAddrChecked = bundle1.getBoolean("street_addr_checked");
		mDsblRotationChecked = bundle1.getBoolean("dsbl_rotation_checked");
		mFullScreenChecked = bundle1.getBoolean("full_screen_checked");
		mBackgroundChecked = bundle1.getBoolean("background_checked");
		if (!mDsblRotationChecked) {
			// setRequestedOrientation(4);
		} else {
		}
		switch (SpeedView.mStoredOrientation) {
		case 0:
			setRequestedOrientation(1);
			break;
		case 1:
			setRequestedOrientation(0);
			break;
		case 2:
			break;
		case 3:
			setRequestedOrientation(8);
			break;
		}
		if (!mFullScreenChecked) {
			if (mDsblRotationChecked
					&& (SpeedView.mStoredOrientation == 1 || SpeedView.mStoredOrientation == 3)
					|| !mDsblRotationChecked
					&& getResources().getConfiguration().orientation == 2)
				setFullScreenMode(true);
			else
				setFullScreenMode(false);
		} else {
			setFullScreenMode(true);
		}
		if (mBackgroundChecked)
			mSettingsScreen.setBackgroundResource(2130837508);
		else
			mSettingsScreen.setBackgroundColor(-16777216);
		mAddlDataValues = getBaseContext().getResources().getStringArray(
				2131034115);
		mUnitsSpinner = (Spinner) findViewById(2131296488);
		mWarningCheckBox = (CheckBox) findViewById(2131296492);
		mWarningTableLayout = (TableLayout) findViewById(2131296494);
		mCurrentLimit = (TextView) findViewById(2131296497);
		mUrbanAreaButton = (Button) findViewById(2131296500);
		mHighwayButton = (Button) findViewById(2131296501);
		mFreewayButton = (Button) findViewById(2131296502);
		mTownLimitLayout = (TableLayout) findViewById(2131296503);
		mHighwayLimitLayout = (TableLayout) findViewById(2131296511);
		mFreewayLimitLayout = (TableLayout) findViewById(2131296519);
		mTownLimitSpinner = (Spinner) findViewById(2131296506);
		mTownLimitSeekBar = (SeekBar) findViewById(2131296509);
		mTownLimitValue = (TextView) findViewById(2131296510);
		mHighwayLimitSpinner = (Spinner) findViewById(2131296514);
		mHighwayLimitSeekBar = (SeekBar) findViewById(2131296517);
		mHighwayLimitValue = (TextView) findViewById(2131296518);
		mFreewayLimitSpinner = (Spinner) findViewById(2131296522);
		mFreewayLimitSeekBar = (SeekBar) findViewById(2131296525);
		mFreewayLimitValue = (TextView) findViewById(2131296526);
		mSoundAlertButton = (ToggleButton) findViewById(2131296529);
		mSoundAlertStatus = (TextView) findViewById(2131296530);
		mVibrationCheckBox = (CheckBox) findViewById(2131296533);
		mDigitSpeedoCheckBox = (CheckBox) findViewById(2131296541);
		mDigitalTableLayout = (TableLayout) findViewById(2131296543);
		mDigitAddlDataButton = (ToggleButton) findViewById(2131296546);
		mDigitAddlDataStatus = (TextView) findViewById(2131296547);
		mMaxSpeedoCheckBox = (CheckBox) findViewById(2131296550);
		mSpeedoSeekbarRow = (TableRow) findViewById(2131296552);
		mSpeedoLimitSeekBar = (SeekBar) findViewById(2131296553);
		mSpeedoLimitValue = (TextView) findViewById(2131296554);
		mUseHudCheckBox = (CheckBox) findViewById(2131296557);
		mAdvancedHudRow = (TableRow) findViewById(2131296559);
		mAdvancedHudCheckBox = (CheckBox) findViewById(2131296561);
		mAdvancedHudInfo = (TextView) findViewById(2131296562);
		mAdvancedZoomRow = (TableRow) findViewById(2131296563);
		mAdvancedZoomCheckBox = (CheckBox) findViewById(2131296565);
		mAdvancedZoomInfo = (TextView) findViewById(2131296566);
		mColorsTableLayout = (TableLayout) findViewById(2131296571);
		mCustomColorsCheckBox = (CheckBox) findViewById(2131296569);
		mSpeedBarColorButton = (Button) findViewById(2131296574);
		mPrimaryColorButton = (Button) findViewById(2131296578);
		mSecondaryColorButton = (Button) findViewById(2131296582);
		mRunInBGCheckBox = (CheckBox) findViewById(2131296590);
		mTrackLoggingCheckBox = (CheckBox) findViewById(2131296594);
		mFrequencyLayout = (LinearLayout) findViewById(2131296596);
		mMinTimeSpinner = (Spinner) findViewById(2131296600);
		mMinDistanceSpinner = (Spinner) findViewById(2131296604);
		mNarrowingCheckBox = (CheckBox) findViewById(2131296611);
		mAccuracySpinner = (Spinner) findViewById(2131296615);
		mStreetAddrLayout = (LinearLayout) findViewById(2131296621);
		mStreetAddrCheckBox = (CheckBox) findViewById(2131296624);
		mDsblRotationRow = (TableRow) findViewById(2131296626);
		mDsblRotationInfo = (TextView) findViewById(2131296629);
		mDsblRotationCheckBox = (CheckBox) findViewById(2131296628);
		mFullScreenRow = (TableRow) findViewById(2131296630);
		mFullScreenInfo = (TextView) findViewById(2131296633);
		mFullScreenCheckBox = (CheckBox) findViewById(2131296632);
		mBackgroundCheckBox = (CheckBox) findViewById(2131296636);
		mUnitsSpinner.setSelection(mDisplayUnits);
		mWarningCheckBox.setChecked(mWarningChecked);
		if (mWarningChecked)
			mWarningTableLayout.setVisibility(0);
		switch (mCurrentSpeedLimit) {
		case 0:
			mCurrentLimit.setText(2131099673);
			break;
		case 1:
			mCurrentLimit.setText(2131099674);
			break;
		case 2:
			mCurrentLimit.setText(2131099675);
			break;
		}
		mUrbanAreaButton.setEnabled(false);
		mSoundAlertButton.setChecked(mSoundAlertToggled);
		if (mSoundAlertToggled) {
			if (RingtoneManager.getRingtone(getBaseContext(), mAlertSoundUri) != null) {
				StringBuilder stringbuilder = (new StringBuilder(
						String.valueOf(getString(2131099839)))).append(" ");
				Ringtone ringtone = RingtoneManager.getRingtone(
						getBaseContext(), mAlertSoundUri);
				stringbuilder.append(ringtone.getTitle(getBaseContext()))
						.toString();
				mSoundAlertStatus.setText(stringbuilder.toString());
			} else {
				String s9 = String.valueOf(getString(2131099839));
				String s10 = (new StringBuilder(s9)).append(" n/a").toString();
				mSoundAlertStatus.setText(s10);
			}
		} else {
			mSoundAlertStatus.setText(2131099838);
		}
		mVibrationCheckBox.setChecked(mVibrationChecked);
		mDigitSpeedoCheckBox.setChecked(mDigitSpeedoChecked);
		if (mDigitSpeedoChecked)
			mDigitalTableLayout.setVisibility(0);
		mDigitAddlDataButton.setChecked(mDigitAddlDataToggled);
		if (mDigitAddlDataToggled) {
			StringBuilder stringbuilder1 = (new StringBuilder(
					String.valueOf(getString(2131099850)))).append(" ");
			stringbuilder1.append(mAddlDataValues[mDigitDataSelected])
					.toString();
			mDigitAddlDataStatus.setText(stringbuilder1.toString());
		}
		if (mMaxSpeedoChecked)
			mSpeedoSeekbarRow.setVisibility(0);
		mMaxSpeedoCheckBox.setChecked(mMaxSpeedoChecked);
		mSpeedoLimitSeekBar.setProgress(mMaxSpeedoLimit);
		StringBuilder stringbuilder2 = (new StringBuilder(
				String.valueOf(mMaxSpeedoLimit))).append(" ");
		stringbuilder2.append(SpeedView.UNITS_ARRAY[mDisplayUnits]).toString();
		mSpeedoLimitValue.setText(stringbuilder2.toString());
		mUseHudCheckBox.setChecked(mUseHudChecked);
		if (mUseHudChecked) {
			mAdvancedZoomRow.setVisibility(8);
			mAdvancedZoomInfo.setVisibility(8);
			mAdvancedHudRow.setVisibility(0);
			mAdvancedHudInfo.setVisibility(0);
		} else {
			mAdvancedHudRow.setVisibility(8);
			mAdvancedHudInfo.setVisibility(8);
			mAdvancedZoomRow.setVisibility(0);
			mAdvancedZoomInfo.setVisibility(0);
		}
		mAdvancedHudCheckBox.setChecked(mAdvancedHudChecked);
		mAdvancedZoomCheckBox.setChecked(mAdvancedZoomChecked);
		mCustomColorsCheckBox.setChecked(mCustomColorsChecked);
		if (mCustomColorsChecked)
			mColorsTableLayout.setVisibility(0);
		if (mSpeedBarColor != -16776961) {
			mSpeedBarColorButton.setText(2131099869);
			mSpeedBarColorButton.setTextColor(mSpeedBarColor);
		} else {
			mSpeedBarColorButton.setText(2131099868);
		}
		if (mPrimaryTextColor != -1) {
			mPrimaryColorButton.setText(2131099869);
			mPrimaryColorButton.setTextColor(mPrimaryTextColor);
		} else {
			mPrimaryColorButton.setText(2131099868);
		}
		if (mSecondaryTextColor != -3355444) {
			mSecondaryColorButton.setText(2131099869);
			mSecondaryColorButton.setTextColor(mSecondaryTextColor);
		} else {
			mSecondaryColorButton.setText(2131099868);
		}
		mRunInBGCheckBox.setChecked(mRunInBGChecked);
		mTrackLoggingCheckBox.setChecked(mTrackLoggingChecked);
		mMinTimeSpinner.setSelection(mMinTimeBetweenPts);
		mMinDistanceSpinner.setSelection(mMinDistBetweenPts);
		if (mTrackLoggingChecked)
			mFrequencyLayout.setVisibility(0);
		else
			mFrequencyLayout.setVisibility(8);
		mNarrowingCheckBox.setChecked(mNarrowingChecked);
		mAccuracySpinner.setSelection(mMinimumAccuracy);
		mStreetAddrLayout.setVisibility(0);
		mStreetAddrCheckBox.setChecked(mStreetAddrChecked);
		if (!isNetworkAvailable())
			mStreetAddrCheckBox.setEnabled(false);
		mDsblRotationCheckBox.setChecked(mDsblRotationChecked);
		mFullScreenCheckBox.setChecked(mFullScreenChecked);
		mFullScreenCheckBox.setChecked(mFullScreenChecked);
		mBackgroundCheckBox.setChecked(mBackgroundChecked);
		mUnitsSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> adapterview, View view,
					int i, long l) {
				Resources resources = getBaseContext().getResources();
				int j;
				int i1;
				int j1;
				int k1;
				int i2;
				String s12;
				StringBuilder stringbuilder3;
				int j2;
				String s13;
				String s14;
				if (mUnitsSpinner.getSelectedItemPosition() == 1)
					j = 2131034114;
				else
					j = 2131034113;
				String[] as = resources.getStringArray(j);
				ArrayAdapter arrayadapter = new ArrayAdapter(getBaseContext(), 17367048, as);
				arrayadapter.setDropDownViewResource(17367049);
				mTownLimitSpinner.setAdapter(arrayadapter);
				mHighwayLimitSpinner.setAdapter(arrayadapter);
				mFreewayLimitSpinner.setAdapter(arrayadapter);
				arrayadapter.notifyDataSetChanged();
				String s = Integer.toString(mTownSpeedLimit);
				int k = getArrayIndex(as, s);
				if (k != -1)
					i1 = k;
				else
					i1 = 14;
				mTownLimitSpinner.setSelection(i1);
				if (k == -1) {
					String s1 = String.valueOf(mTownLimitSeekBar.getProgress());
					StringBuilder stringbuilder = (new StringBuilder(s1))
							.append(" ");
					String s2 = mUnitsSpinner.getSelectedItem().toString();
					String s3 = stringbuilder.append(s2).toString();
					mTownLimitValue.setText(s3);
				}
				String s4 = Integer.toString(mHighwaySpeedLimit);
				j1 = getArrayIndex(as, s4);
				if (j1 != -1)
					k1 = j1;
				else
					k1 = 14;
				mHighwayLimitSpinner.setSelection(k1);
				if (j1 == -1) {
					String s5 = String.valueOf(mHighwayLimitSeekBar
							.getProgress());
					StringBuilder stringbuilder1 = (new StringBuilder(s5))
							.append(" ");
					String s6 = mUnitsSpinner.getSelectedItem().toString();
					String s7 = stringbuilder1.append(s6).toString();
					mHighwayLimitValue.setText(s7);
				}
				String s8 = Integer.toString(mFreewaySpeedLimit);
				int l1 = getArrayIndex(as, s8);
				if (l1 != -1)
					i2 = l1;
				else
					i2 = 14;
				mFreewayLimitSpinner.setSelection(i2);
				if (l1 == -1) {
					String s9 = String.valueOf(mFreewayLimitSeekBar
							.getProgress());
					StringBuilder stringbuilder2 = (new StringBuilder(s9))
							.append(" ");
					String s10 = mUnitsSpinner.getSelectedItem().toString();
					String s11 = stringbuilder2.append(s10).toString();
					mFreewayLimitValue.setText(s11);
				}
				if (mUnitsSpinner.getSelectedItemPosition() == 1) {
					mTownLimitSeekBar.setMax(240);
					mHighwayLimitSeekBar.setMax(240);
					mFreewayLimitSeekBar.setMax(240);
				} else {
					mTownLimitSeekBar.setMax(160);
					mHighwayLimitSeekBar.setMax(160);
					mFreewayLimitSeekBar.setMax(160);
				}
				mTownLimitSeekBar.setProgress(0);
				mHighwayLimitSeekBar.setProgress(0);
				mFreewayLimitSeekBar.setProgress(0);
				switch (mUnitsSpinner.getSelectedItemPosition()) {
				case 0:
					mSpeedoLimitSeekBar.setMax(600);
					mSpeedoLimitSeekBar.setProgress(0);
					if (mMaxSpeedoLimit > 600)
						mMaxSpeedoLimit = 600;
					if (mMaxSpeedoLimit == 240 || mMaxSpeedoLimit == 60)
						mMaxSpeedoLimit = 160;
					mSpeedoLimitSeekBar.setProgress(mMaxSpeedoLimit);
					break;
				case 1:
					mSpeedoLimitSeekBar.setMax(1000);
					mSpeedoLimitSeekBar.setProgress(0);
					if (mMaxSpeedoLimit == 160 || mMaxSpeedoLimit == 60)
						mMaxSpeedoLimit = 240;
					mSpeedoLimitSeekBar.setProgress(mMaxSpeedoLimit);
					break;
				case 2:
					mSpeedoLimitSeekBar.setMax(500);
					mSpeedoLimitSeekBar.setProgress(0);
					if (mMaxSpeedoLimit > 500)
						mMaxSpeedoLimit = 500;
					if (mMaxSpeedoLimit == 160 || mMaxSpeedoLimit == 240)
						mMaxSpeedoLimit = 60;
					mSpeedoLimitSeekBar.setProgress(mMaxSpeedoLimit);
					break;
				}
				s12 = String.valueOf(mMaxSpeedoLimit);
				stringbuilder3 = (new StringBuilder(s12)).append(" ");
				j2 = mUnitsSpinner.getSelectedItemPosition();
				s13 = SpeedView.UNITS_ARRAY[j2];
				s14 = stringbuilder3.append(s13).toString();
				mSpeedoLimitValue.setText(s14);
				mDisplayUnits = mUnitsSpinner.getSelectedItemPosition();
			}

			public void onNothingSelected(AdapterView<?> adapterview) {

			}
		});
		mWarningCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton compoundbutton,
							boolean flag) {
						int i;
						if (flag)
							i = 0;
						else
							i = 8;
						mWarningTableLayout.setVisibility(i);
						mWarningChecked = flag;
					}
				});
		mUrbanAreaButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				mUrbanAreaButton.setEnabled(false);
				mHighwayButton.setEnabled(true);
				mFreewayButton.setEnabled(true);
				mTownLimitLayout.setVisibility(0);
				mHighwayLimitLayout.setVisibility(8);
				mFreewayLimitLayout.setVisibility(8);
			}
		});
		mHighwayButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				mHighwayButton.setEnabled(false);
				mUrbanAreaButton.setEnabled(true);
				mFreewayButton.setEnabled(true);
				mHighwayLimitLayout.setVisibility(0);
				mTownLimitLayout.setVisibility(8);
				mFreewayLimitLayout.setVisibility(8);
			}
		});
		mFreewayButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				mFreewayButton.setEnabled(false);
				mUrbanAreaButton.setEnabled(true);
				mHighwayButton.setEnabled(true);
				mFreewayLimitLayout.setVisibility(0);
				mHighwayLimitLayout.setVisibility(8);
				mTownLimitLayout.setVisibility(8);
			}
		});
		mTownLimitSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> adapterview,
							View view, int i, long l) {
						if (i != 14) {
							mTownSpeedLimit = Integer.parseInt(mTownLimitSpinner
									.getSelectedItem().toString());
							String s = String.valueOf(mTownLimitSpinner
									.getSelectedItem().toString());
							StringBuilder stringbuilder = (new StringBuilder(s))
									.append(" ");
							String s1 = mUnitsSpinner.getSelectedItem()
									.toString();
							String s2 = stringbuilder.append(s1).toString();
							mTownLimitValue.setText(s2);
						}
						mTownLimitSeekBar.setProgress(mTownSpeedLimit);
					}

					public void onNothingSelected(AdapterView<?> adapterview) {

					}
				});
		mTownLimitSeekBar
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

					public void onStopTrackingTouch(SeekBar seekbar) {
						String as[];
						if (mDisplayUnits == 1)
							as = getBaseContext().getResources()
									.getStringArray(2131034114);
						else
							as = getBaseContext().getResources()
									.getStringArray(2131034113);
						String s = Integer.toString(seekbar.getProgress());
						int i = getArrayIndex(as, s);
						if (i == -1)
							i = 14;
						mTownLimitSpinner.setSelection(i);
						mTownSpeedLimit = seekbar.getProgress();
					}

					public void onStartTrackingTouch(SeekBar seekbar) {

					}

					public void onProgressChanged(SeekBar seekbar, int i,
							boolean flag) {
						if (flag)
							mTownLimitSpinner.setSelection(14);
						String s = String.valueOf(i);
						StringBuilder stringbuilder = (new StringBuilder(s))
								.append(" ");
						String s1 = mUnitsSpinner.getSelectedItem().toString();
						String s2 = stringbuilder.append(s1).toString();
						mTownLimitValue.setText(s2);
					}
				});
		mHighwayLimitSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> adapterview,
							View view, int i, long l) {
						if (i != 14) {
							mHighwaySpeedLimit = Integer.parseInt(mHighwayLimitSpinner
									.getSelectedItem().toString());
							String s = String.valueOf(mHighwayLimitSpinner
									.getSelectedItem().toString());
							StringBuilder stringbuilder = (new StringBuilder(s))
									.append(" ");
							String s1 = mUnitsSpinner.getSelectedItem()
									.toString();
							String s2 = stringbuilder.append(s1).toString();
							mHighwayLimitValue.setText(s2);
						}
						mHighwayLimitSeekBar.setProgress(mHighwaySpeedLimit);
					}

					public void onNothingSelected(AdapterView<?> adapterview) {

					}
				});
		mHighwayLimitSeekBar
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

					public void onStopTrackingTouch(SeekBar seekbar) {
						String as[];
						if (mDisplayUnits == 1)
							as = getBaseContext().getResources()
									.getStringArray(2131034114);
						else
							as = getBaseContext().getResources()
									.getStringArray(2131034113);
						String s = Integer.toString(seekbar.getProgress());
						int i = getArrayIndex(as, s);
						if (i == -1)
							i = 14;
						mHighwayLimitSpinner.setSelection(i);
						mHighwaySpeedLimit = seekbar.getProgress();
					}

					public void onStartTrackingTouch(SeekBar seekbar) {

					}

					public void onProgressChanged(SeekBar seekbar, int i,
							boolean flag) {
						if (flag)
							mHighwayLimitSpinner.setSelection(14);
						String s = String.valueOf(i);
						StringBuilder stringbuilder = (new StringBuilder(s))
								.append(" ");
						String s1 = mUnitsSpinner.getSelectedItem().toString();
						String s2 = stringbuilder.append(s1).toString();
						mHighwayLimitValue.setText(s2);
					}
				});
		mFreewayLimitSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> adapterview,
							View view, int i, long l) {
						if (i != 14) {
							mFreewaySpeedLimit = Integer.parseInt(mFreewayLimitSpinner
									.getSelectedItem().toString());
							String s = String.valueOf(mFreewayLimitSpinner
									.getSelectedItem().toString());
							StringBuilder stringbuilder = (new StringBuilder(s))
									.append(" ");
							String s1 = mUnitsSpinner.getSelectedItem()
									.toString();
							String s2 = stringbuilder.append(s1).toString();
							mFreewayLimitValue.setText(s2);
						}
						mFreewayLimitSeekBar.setProgress(mFreewaySpeedLimit);
					}

					public void onNothingSelected(AdapterView<?> adapterview) {

					}
				});
		mFreewayLimitSeekBar
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

					public void onStopTrackingTouch(SeekBar seekbar) {
						String as[];
						if (mDisplayUnits == 1)
							as = getBaseContext().getResources()
									.getStringArray(2131034114);
						else
							as = getBaseContext().getResources()
									.getStringArray(2131034113);
						String s = Integer.toString(seekbar.getProgress());
						int i = getArrayIndex(as, s);
						if (i == -1)
							i = 14;
						mFreewayLimitSpinner.setSelection(i);
						mFreewaySpeedLimit = seekbar.getProgress();
					}

					public void onStartTrackingTouch(SeekBar seekbar) {

					}

					public void onProgressChanged(SeekBar seekbar, int i,
							boolean flag) {
						if (flag)
							mFreewayLimitSpinner.setSelection(14);
						TextView textview = mFreewayLimitValue;
						String s = String.valueOf(i);
						StringBuilder stringbuilder = (new StringBuilder(s))
								.append(" ");
						String s1 = mUnitsSpinner.getSelectedItem().toString();
						String s2 = stringbuilder.append(s1).toString();
						textview.setText(s2);
					}
				});
		mSoundAlertButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				if (mSoundAlertButton.isChecked()) {
					mSoundAlertToggled = true;
					Intent intent = new Intent(
							"android.intent.action.RINGTONE_PICKER");
					intent.putExtra(
							"android.intent.extra.ringtone.EXISTING_URI",
							mAlertSoundUri);
					intent.putExtra(
							"android.intent.extra.ringtone.SHOW_DEFAULT", false);
					intent.putExtra(
							"android.intent.extra.ringtone.SHOW_SILENT", false);
					intent.putExtra("android.intent.extra.ringtone.TYPE", 2);
					Uri uri1 = RingtoneManager.getActualDefaultRingtoneUri(
							SettingsActivity.this, 2);
					if (uri1 == null)
						uri1 = RingtoneManager.getDefaultUri(2);
					intent.putExtra(
							"android.intent.extra.ringtone.DEFAULT_URI", uri1);
					startActivityForResult(intent, 1);
				} else {
					mSoundAlertStatus.setText(2131099838);
					mSoundAlertToggled = false;
				}
			}
		});
		mVibrationCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton compoundbutton,
							boolean flag) {
						mVibrationChecked = flag;
					}
				});
		mDigitSpeedoCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton compoundbutton,
							boolean flag) {
						if (flag)
							mDigitalTableLayout.setVisibility(0);
						else
							mDigitalTableLayout.setVisibility(8);
						mDigitSpeedoChecked = flag;
					}
				});
		mDigitAddlDataButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				if (mDigitAddlDataButton.isChecked()) {
					SettingsActivity settingsactivity = SettingsActivity.this;
					AlertDialog.Builder builder = (new android.app.AlertDialog.Builder(
							settingsactivity)).setTitle(2131099848);
					builder.setSingleChoiceItems(mAddlDataValues,
							mDigitDataSelected,
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									mTempSpeedoLimit = which;
								}
							});

					builder.setPositiveButton(2131099857,
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									if (mTempSpeedoLimit != -1) {
										String s = String
												.valueOf(getString(2131099850));
										StringBuilder stringbuilder = (new StringBuilder(
												s)).append(" ");
										String s1 = mAddlDataValues[mTempSpeedoLimit];
										String s2 = stringbuilder.append(s1)
												.toString();
										mDigitAddlDataStatus.setText(s2);
										mDigitDataSelected = mTempSpeedoLimit;
										mDigitAddlDataToggled = true;
										return;
									}
									if (mDigitDataSelected != -1) {
										String s3 = String
												.valueOf(getString(2131099850));
										StringBuilder stringbuilder1 = (new StringBuilder(
												s3)).append(" ");
										String s4 = mAddlDataValues[mDigitDataSelected];
										String s5 = stringbuilder1.append(s4)
												.toString();
										mDigitAddlDataStatus.setText(s5);
										mDigitAddlDataToggled = true;
									} else {
										mDigitAddlDataButton.setChecked(false);
									}
								}
							});

					builder.setNegativeButton(2131099858,
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									mTempSpeedoLimit = -1;
									mDigitAddlDataButton.setChecked(false);
									mDigitAddlDataToggled = false;
								}
							}).show();
				} else {
					mDigitAddlDataStatus.setText(2131099849);
					mDigitAddlDataToggled = false;
				}
			}
		});
		mMaxSpeedoCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton compoundbutton,
							boolean flag) {
						if (flag)
							mSpeedoSeekbarRow.setVisibility(0);
						else
							mSpeedoSeekbarRow.setVisibility(8);
						mMaxSpeedoChecked = flag;
					}
				});
		mSpeedoLimitSeekBar
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

					public void onStopTrackingTouch(SeekBar seekBar) {
						mMaxSpeedoLimit = seekBar.getProgress();
					}

					public void onStartTrackingTouch(SeekBar seekBar) {

					}

					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						StringBuilder stringbuilder = (new StringBuilder(String
								.valueOf(progress))).append(" ");
						String s1 = mUnitsSpinner.getSelectedItem().toString();
						String s2 = stringbuilder.append(s1).toString();
						mSpeedoLimitValue.setText(s2);
					}
				});
		mUseHudCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							mAdvancedZoomRow.setVisibility(8);
							mAdvancedZoomInfo.setVisibility(8);
							mAdvancedHudRow.setVisibility(0);
							mAdvancedHudInfo.setVisibility(0);
						} else {
							mAdvancedHudRow.setVisibility(8);
							mAdvancedHudInfo.setVisibility(8);
							mAdvancedZoomRow.setVisibility(0);
							mAdvancedZoomInfo.setVisibility(0);
						}
						mUseHudChecked = isChecked;
					}
				});
		mAdvancedHudCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						mAdvancedHudChecked = isChecked;
					}
				});
		mAdvancedZoomCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						mAdvancedZoomChecked = isChecked;
					}
				});
		mCustomColorsCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked)
							mColorsTableLayout.setVisibility(0);
						else
							mColorsTableLayout.setVisibility(8);
						mCustomColorsChecked = isChecked;
					}
				});
		mSpeedBarColorButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				mFromButton = SettingsActivity.mColors[0];
				(new ColorPickerDialog(context, SettingsActivity.this, mSpeedBarColor)).show();
			}
		});
		mPrimaryColorButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				mFromButton = SettingsActivity.mColors[1];
				(new ColorPickerDialog(context, SettingsActivity.this,
						mPrimaryTextColor)).show();
			}
		});
		mSecondaryColorButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				mFromButton = SettingsActivity.mColors[2];
				(new ColorPickerDialog(context, SettingsActivity.this,
						mSecondaryTextColor)).show();
			}
		});
		mRunInBGCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton compoundbutton,
							boolean flag) {
						mRunInBGChecked = flag;
					}
				});
		mTrackLoggingCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton compoundbutton,
							boolean flag) {
						if (flag)
							mFrequencyLayout.setVisibility(0);
						else
							mFrequencyLayout.setVisibility(8);
						mTrackLoggingChecked = flag;
					}
				});
		mMinTimeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				mMinTimeBetweenPts = mMinTimeSpinner.getSelectedItemPosition();
			}

			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		mMinDistanceSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						mMinDistBetweenPts = mMinDistanceSpinner
								.getSelectedItemPosition();
					}

					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});
		mNarrowingCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton compoundbutton,
							boolean flag) {
						mNarrowingChecked = flag;
					}
				});
		mAccuracySpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						mMinimumAccuracy = mAccuracySpinner
								.getSelectedItemPosition();
					}

					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});
		mStreetAddrCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton compoundbutton,
							boolean flag) {
						mStreetAddrChecked = flag;
					}
				});
		mDsblRotationCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton compoundbutton,
							boolean flag) {
						if (!flag) {
							setRequestedOrientation(4);
						} else {
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
								mDsblRotationChecked = flag;
								break;
							}
						}
					}
				});
		mFullScreenCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton compoundbutton,
							boolean flag) {
						if (!flag) {
							if (mDsblRotationChecked
									&& SpeedView.mStoredOrientation == 1
									|| !mDsblRotationChecked
									&& getResources().getConfiguration().orientation == 2)
								setFullScreenMode(true);
							else
								setFullScreenMode(false);
						} else {
							setFullScreenMode(true);
						}
						mFullScreenChecked = flag;
					}
				});
		mBackgroundCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton compoundbutton,
							boolean flag) {
						if (flag)
							mSettingsScreen.setBackgroundResource(2130837508);
						else
							mSettingsScreen.setBackgroundColor(-16777216);
						mBackgroundChecked = flag;
					}
				});
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

	@SuppressLint("NewApi")
	public void reload() {
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