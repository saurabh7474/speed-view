package com.codesector.speedview.pro;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.codesector.speedview.pro.R;
import java.lang.reflect.Array;

public class SettingsActivity extends Activity implements
		ColorPickerDialog.OnColorChangedListener {
	private static final int PRIMARY = 1;
	private static final int SECONDARY = 2;
	private static final int SPEED_BAR = 0;
	private static final String[] mColors = { "speed bar", "primary",
			"secondary" };
	private final int CUSTOM_SPEED_LIMIT = 14;
	private final int REQUEST_ALARM_SOUND = 1;
	private Spinner mAccuracySpinner;
	private String[] mAddlDataValues;
	private CheckBox mAdvancedHudCheckBox;
	private boolean mAdvancedHudChecked;
	private TextView mAdvancedHudInfo;
	private TableRow mAdvancedHudRow;
	private CheckBox mAdvancedZoomCheckBox;
	private boolean mAdvancedZoomChecked;
	private TextView mAdvancedZoomInfo;
	private TableRow mAdvancedZoomRow;
	private Uri mAlertSoundUri = null;
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
	private int mTempSpeedoLimit = -1;
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

	private int getArrayIndex(String[] paramArrayOfString, String paramString) {
		// TODO
		return 0;
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
		if (flag) {
			layoutparams.flags = layoutparams.flags | 1024;
		} else {
			layoutparams.flags = layoutparams.flags & -1025;
		}
		getWindow().setAttributes(layoutparams);
	}

	public void colorChanged(int i) {
		if (mFromButton.equals(mColors[0])) {
			mSpeedBarColorButton.setText(2131099869);
			mSpeedBarColorButton.setTextColor(i);
			mSpeedBarColor = i;
			return;
		}
		if (mFromButton.equals(mColors[1])) {
			mPrimaryColorButton.setText(2131099869);
			mPrimaryColorButton.setTextColor(i);
			mPrimaryTextColor = i;
			return;
		} else {
			mSecondaryColorButton.setText(2131099869);
			mSecondaryColorButton.setTextColor(i);
			mSecondaryTextColor = i;
			return;
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
			StringBuilder stringbuilder = (new StringBuilder(
					String.valueOf(getString(2131099839)))).append(" ");
			Ringtone ringtone = RingtoneManager.getRingtone(getBaseContext(),
					mAlertSoundUri);
			stringbuilder.append(ringtone.getTitle(getBaseContext()))
					.toString();
			mSoundAlertStatus.setText(stringbuilder.toString());
			return;
		}
	}

	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		// TODO
	}

	public boolean onCreateOptionsMenu(Menu paramMenu) {
		getMenuInflater().inflate(2131230721, paramMenu);
		return super.onCreateOptionsMenu(paramMenu);
	}

	public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
		return false;
		// TODO
	}

	public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
		// TODO
		return false;
	}

	public boolean onPrepareOptionsMenu(Menu menu) {
		if (mSpeedBarColor != -16776961 || mPrimaryTextColor != -1
				|| mSecondaryTextColor != -3355444)
			menu.findItem(2131296694).setVisible(true);
		else
			menu.findItem(2131296694).setVisible(false);
		return super.onPrepareOptionsMenu(menu);
	}

	public void reload() {
		Intent localIntent = getIntent();
		overridePendingTransition(0, 0);
		localIntent.addFlags(65536);
		finish();
		overridePendingTransition(0, 0);
		startActivity(localIntent);
	}
}