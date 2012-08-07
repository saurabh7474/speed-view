package com.codesector.speedview.pro;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Ringtone;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FilenameFilter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class SpeedView extends Activity {

	private static final int ABOUT_DIALOG_ID = 1;
	static final int ACCURACY = 3;
	static final float ACCURACY_VALUES[] = { 1092616192, 1101004800,
			1112014848, 1120403456, 1128792064, 1140457472, 1148846080,
			1157234688, 1167867904 };
	private static final int ADMOB_INTERVAL = 30;
	static final int ADVANCED = 4;
	static final int AVERAGE_SPEED = 1;
	private static final int CHANGELOG_DIALOG_ID = 2;
	static final int COMPASS = 2;
	private static final String COMPASS_DIRECTIONS[] = { "N", "NE", "E", "SE",
			"S", "SW", "W", "NW", "N" };
	static final int DEF_MIN_ACCURACY = 4;
	static final int ELEVATION = 2;
	private static final int EXPORT_FAILURE = 2;
	private static final int EXPORT_SUCCESS = 1;
	static final int FREEWAY = 2;
	private static final int HELP_DIALOG_ID = 0;
	static final int HIGHWAY = 1;
	static final int HUD_VIEW = 3;
	static final int KNOTS = 2;
	static final int KPH = 1;
	static final int LANDSCAPE = 1;
	static final int LITE = 1;
	static final int MAIN = 1;
	static final int MIN_DISTANCE_VALUES[] = { 1, 2, 3, 4, 5, 10, 100 };
	static final int MIN_TIME_VALUES[] = { 1, 2, 3, 4, 5, 10, 20, 30, 60, 120,
			300, 600, 900, 1800 };
	static final int MPH = 0;
	private static final int NO_DATA_TO_EXPORT = 0;
	static final int PORTRAIT = 0;
	static final String PREFS_NAME = "PrefsFile";
	static final int PRO = 0;
	static final boolean PRO_VERSION = true;
	static final int REVERSE_LANDSCAPE = 3;
	static final int REVERSE_PORTRAIT = 2;
	static final int STARTUP = 0;
	static final int TIME_OF_DAY = 4;
	static final int TRIP_TIME = 0;
	static final String UNITS_ARRAY[] = { "mph", "km/h", "knots" };
	static final int URBAN_AREA = 0;
	static final String VERSIONS[] = { "Pro", "Lite" };
	static final boolean VODAFONE_SHOP = false;
	static boolean mAdvancedHudChecked;
	static boolean mAdvancedZoomChecked;
	static boolean mBackgroundChecked;
	static int mCurrentVersion;
	static boolean mCustomColorsChecked;
	static boolean mDigitAddlDataToggled;
	static int mDigitDataSelected;
	static boolean mDigitSpeedoChecked;
	static int mDisplayUnits;
	static boolean mDsblRotationChecked;
	static boolean mFullScreenChecked;
	private static String mGPXFileLocation;
	private static boolean mHasGPSFix;
	private static boolean mHasNetworkAccess;
	static boolean mIsGPSEnabled;
	static boolean mIsRecording;
	private static boolean mIsScreenSupported;
	static boolean mMaverickInst;
	static String mMaverickVersion;
	static boolean mMaxSpeedoChecked;
	static int mMaxSpeedoLimit;
	private static boolean mOpenSpotInst;
	static int mPrimaryTextColor;
	private static int mScreenLayoutSize;
	static float mScreenRatio;
	static int mSecondaryTextColor;
	static int mSelectedDashboard;
	static int mSpeedBarColor;
	static int mStoredOrientation;
	static boolean mStreetAddrChecked;
	static boolean mUseHudChecked;
	static int mVersionCode;
	static String mVersionName;
	static boolean mWarningChecked;
	private boolean m100KphReached;
	private boolean m60MphReached;
	private TextView mAccelerationInfo;
	private LinearLayout mAccelerationLayout;
	private LinearLayout mAccelerationLayoutT;
	private Location mAcclStartLocation;
	private RelativeLayout mAccuracyNotification;
	private Address mAddress;
	final Runnable mAddressFound;
	private TextView mAddressLine0;
	private TextView mAddressLine1;
	private String mAddressString;
	private RelativeLayout mAddressView;
	private LinearLayout mAdvancedScreen;
	private Uri mAlertSoundUri;
	private ServiceConnection mBackgroundConn;
	private TextView mCompassElevation;
	private CompassMode mCompassMode;
	private TextView mCompassOdometer;
	private RelativeLayout mCompassScreen;
	private TextView mCompassSource;
	private TextView mCompassSpeed;
	private TextView mCompassTime;
	private CompassView mCompassView;
	private Button mConfirm0To100Button;
	private Button mConfirm0To60Button;
	private Button mConfirmQtrButton;
	private DecimalFormat mCoordFormat;
	private int mCurrentSpeedLimit;
	private SimpleDateFormat mDateFormat;
	private Button mDiscard0To100Button;
	private Button mDiscard0To60Button;
	private Button mDiscardQtrButton;
	private boolean mExitButtonPressed;
	private Button mExportGPXButton;
	private boolean mFeaturedButtonPressed;
	private long mFirstFixTime;
	private ImageView mFreewayLimitDec;
	private ImageView mFreewayLimitInc;
	private TextView mFreewayLimitNumbers;
	private ImageView mFreewayLimitSign;
	private RelativeLayout mFreewayLimitToggle;
	private int mFreewaySpeedLimit;
	private Button mFrom0To100Button;
	private TextView mFrom0To100Info;
	private TextView mFrom0To100Meters;
	private TextView mFrom0To100Result;
	private TextView mFrom0To100ResultT;
	private TableRow mFrom0To100Row;
	private TableRow mFrom0To100RowT;
	private LinearLayout mFrom0To100Screen;
	private TextView mFrom0To100Speed;
	private String mFrom0To100String;
	private TextView mFrom0To100Time;
	private Button mFrom0To60Button;
	private TextView mFrom0To60Feet;
	private TextView mFrom0To60Info;
	private TextView mFrom0To60Result;
	private TextView mFrom0To60ResultT;
	private TableRow mFrom0To60Row;
	private TableRow mFrom0To60RowT;
	private LinearLayout mFrom0To60Screen;
	private TextView mFrom0To60Speed;
	private String mFrom0To60String;
	private TextView mFrom0To60Time;
	final Runnable mGPSIsDisabled;
	private MyGPSListener mGPSListener;
	private TextView mGPXExportStatus;
	private Geocoder mGeocoder;
	private Button mGoogleMapsButton;
	private Button mGoogleMapsButtonT;
	private GraphView mGraphView;
	final Handler mHandler;
	private TextView mHeading;
	private ImageView mHighwayLimitDec;
	private ImageView mHighwayLimitInc;
	private TextView mHighwayLimitNumbers;
	private ImageView mHighwayLimitSign;
	private RelativeLayout mHighwayLimitToggle;
	private int mHighwaySpeedLimit;
	private HudMode mHudMode;
	private RelativeLayout mHudScreen;
	private Location mLastAddress;
	private Location mLastLocation;
	private long mLastLocationTime;
	private Location mLastTrackLocation;
	private LocationListener mLocationListener;
	private LocationManager mLocationManager;
	private FilenameFilter mLogExtensionFilter;
	private String mLogFilesList[];
	private ImageView mLogo;
	private ImageView mLookoutBanner;
	private TextView mLowAccuracy;
	private LinearLayout mMainScreen;
	private Button mMaverickButton;
	private Button mMaverickButtonT;
	private ImageView mMaxField;
	private TextView mMaxSpeed;
	private int mMinDistBetweenPts;
	private int mMinTimeBetweenPts;
	private boolean mMinimizeButtonPressed;
	private int mMinimumAccuracy;
	private boolean mNarrowingChecked;
	final Runnable mNetworkFailure;
	private boolean mNotifiedAboutGPS;
	private boolean mNotifiedAboutNetwork;
	private boolean mNotifiedAboutScreen;
	private TextView mNumOfSatellites;
	private TextView mNumberOfSats;
	private TextView mOdometer;
	private ImageView mOdometerField;
	private Button mOpenSpotButton;
	private Button mOpenSpotButtonT;
	private SensorEventListener mOrientationListener;
	private ProgressDialog mProgressDialog;
	private boolean mQtrMileReached;
	private String mQtrMileString;
	private Button mQuarterMileButton;
	private TextView mQuarterMileDist;
	private TextView mQuarterMileInfo;
	private TextView mQuarterMileResult;
	private TextView mQuarterMileResultT;
	private LinearLayout mQuarterMileScreen;
	private TextView mQuarterMileSpeed;
	private TextView mQuarterMileTime;
	private TextView mQuarterMileUnits;
	private LinearLayout mQuickLaunchLayout;
	private LinearLayout mQuickLaunchLayoutT;
	private Button mRecordingButton;
	private TextView mRecordingStatus;
	private boolean mRunInBGChecked;
	private SatelliteView mSatelliteView;
	private Button mSendGPXButton;
	private boolean mSendTrackInit;
	private SensorManager mSensorManager;
	private long mSessionMovingTime;
	private long mSessionStartTime;
	private long mSessionTotalTime;
	private boolean mSettingsButtonPressed;
	private boolean mShareButtonPressed;
	private ImageView mSignalStrength;
	private boolean mSoundAlertToggled;
	private TextView mSpeedMovingAvg;
	private TextView mSpeedMovingAvgT;
	private TextView mSpeedOverallAvg;
	private TextView mSpeedOverallAvgT;
	private RelativeLayout mSpeedView;
	private int mSpeedWarning;
	private SpeedometerView mSpeedometerView;
	private RelativeLayout mStartupScreen;
	private long mStateChangedTime;
	private TextView mStatusMessage;
	private String mStored0To100Time;
	private String mStored0To60Time;
	private float mStoredDistance;
	private float mStoredMaxSpeed;
	private long mStoredMovingTime;
	private String mStoredQtrMileTime;
	private long mStoredTotalTime;
	private RelativeLayout mSwitchboard;
	private String mTemp0To100Time;
	private String mTemp0To60Time;
	private String mTempQtrMileTime;
	private SimpleDateFormat mTimeFormat;
	private TextView mTipMessage;
	private LinearLayout mTipsLayout;
	private ImageView mTownLimitDec;
	private ImageView mTownLimitInc;
	private TextView mTownLimitNumbers;
	private ImageView mTownLimitSign;
	private RelativeLayout mTownLimitToggle;
	private int mTownSpeedLimit;
	private StringBuilder mTrackBuffer;
	private File mTrackLogFile;
	private boolean mTrackLoggingChecked;
	private TextView mTripDistance;
	private TextView mTripTimeMoving;
	private TextView mTripTimeMovingT;
	private TextView mTripTimeStopped;
	private TextView mTripTimeStoppedT;
	private TextView mTripTimeTotal;
	private TextView mTripTimeTotalT;
	final Runnable mUnableToGetAddress;
	private ServiceConnection mUpdateWidgetConn;
	private ImageView mUpgradeBanner;
	private boolean mVehicleIsMoving;
	private boolean mVibrationChecked;
	private Vibrator mVibrator;
	private ViewStub mViewStub;
	private Ringtone mWarningSound;
	final Handler progressHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Context context;
		Locale locale;
		Geocoder geocoder;
		View view;
		ProgressDialog progressdialog;
		DecimalFormatSymbols decimalformatsymbols;

		PackageInfo packageinfo = null;
		try {
			packageinfo = getPackageManager().getPackageInfo(
					"com.codesector.speedview.pro", 128);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		mVersionCode = packageinfo.versionCode;
		mVersionName = packageinfo.versionName;

		int i = getResources().getConfiguration().screenLayout;
		DisplayMetrics displaymetrics;
		if ((i & 15) == 4) {
			mScreenLayoutSize = 4;
			mIsScreenSupported = true;
		} else if ((i & 15) == 3) {
			mScreenLayoutSize = 3;
			mIsScreenSupported = false;
		} else if ((i & 15) == 2) {
			mScreenLayoutSize = 2;
			mIsScreenSupported = true;
		}
		displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		if (displaymetrics.densityDpi == 160) {
			if (mScreenLayoutSize == 4)
				mScreenRatio = 1.5F;
			else
				mScreenRatio = displaymetrics.density;
		} else {
			mScreenRatio = displaymetrics.density;
		}
		getWindow().setFlags(128, 128);
		getWindow().setFormat(1);
		getWindow().addFlags(4096);
		setContentView(R.layout.main);
		mViewStub = (ViewStub) findViewById(2131296303);
		if (mViewStub != null)
			view = mViewStub.inflate();
		context = getBaseContext();
		locale = Locale.getDefault();
		mGeocoder = new Geocoder(context, locale);
		mAddressView = (RelativeLayout) findViewById(2131296261);
		mAddressLine0 = (TextView) findViewById(2131296263);
		mAddressLine1 = (TextView) findViewById(2131296264);
		mSignalStrength = (ImageView) findViewById(2131296266);
		mNumberOfSats = (TextView) findViewById(2131296267);
		mAddressView.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				if (!SpeedView.mStreetAddrChecked)
					return;
				if (!SpeedView.mHasNetworkAccess)
					return;
				if (!SpeedView.mHasGPSFix) {
					return;
				} else {
					mAddressLine0.setText(2131099728);
					mAddressLine1.setText(2131099729);
					SpeedView speedview = SpeedView.this;
					Location location = getLastLocation();
					speedview.displayAddress(location);
					return;
				}
			}
		});
		mStartupScreen = (RelativeLayout) findViewById(2131296292);
		mMainScreen = (LinearLayout) findViewById(2131296302);
		mCompassScreen = (RelativeLayout) findViewById(2131296317);
		mHudScreen = (RelativeLayout) findViewById(2131296324);
		mAdvancedScreen = (LinearLayout) findViewById(2131296326);
		mSelectedDashboard = 0;
		mLocationManager = (LocationManager) getSystemService("location");
		mLocationListener = new MyLocationListener();
		mGPSListener = new MyGPSListener();
		mLastLocation = mLocationManager.getLastKnownLocation("gps");
		mSensorManager = (SensorManager) getSystemService("sensor");
		mVibrator = (Vibrator) getSystemService("vibrator");
		mCoordFormat = new DecimalFormat("0.000000");
		decimalformatsymbols = mCoordFormat.getDecimalFormatSymbols();
		decimalformatsymbols.setDecimalSeparator('.');
		mCoordFormat.setDecimalFormatSymbols(decimalformatsymbols);
		mDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		mTimeFormat = new SimpleDateFormat("HH:mm:ss");
		mTimeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		mLogExtensionFilter = new FilenameFilter() {

			public boolean accept(File file, String s) {
				return s.toLowerCase().endsWith(".log");
			}

		};
		progressdialog = new ProgressDialog(this);
		mProgressDialog = progressdialog;
		mProgressDialog.setProgressStyle(0);
		mProgressDialog.setMessage(getString(2131099779));
		mProgressDialog.setCancelable(false);
		mSpeedView = (RelativeLayout) findViewById(2131296291);
		mAccuracyNotification = (RelativeLayout) findViewById(2131296314);
		mAccelerationLayout = (LinearLayout) findViewById(2131296351);
		mFrom0To60Screen = (LinearLayout) findViewById(2131296384);
		mFrom0To100Screen = (LinearLayout) findViewById(2131296395);
		mQuarterMileScreen = (LinearLayout) findViewById(2131296406);
		mQuickLaunchLayout = (LinearLayout) findViewById(2131296376);
		mSwitchboard = (RelativeLayout) findViewById(2131296417);
		mSatelliteView = (SatelliteView) findViewById(2131296295);
		mCompassView = (CompassView) findViewById(2131296306);
		mSpeedometerView = (SpeedometerView) findViewById(2131296307);
		mGraphView = (GraphView) findViewById(2131296313);
		mCompassMode = (CompassMode) findViewById(2131296318);
		mHudMode = (HudMode) findViewById(2131296325);
		mStatusMessage = (TextView) findViewById(2131296296);
		mNumOfSatellites = (TextView) findViewById(2131296297);
		mTipMessage = (TextView) findViewById(2131296299);
		mOdometerField = (ImageView) findViewById(2131296309);
		mOdometer = (TextView) findViewById(2131296310);
		mMaxField = (ImageView) findViewById(2131296311);
		mMaxSpeed = (TextView) findViewById(2131296312);
		mLowAccuracy = (TextView) findViewById(2131296315);
		mHeading = (TextView) findViewById(2131296436);
		mCompassSpeed = (TextView) findViewById(2131296319);
		mCompassOdometer = (TextView) findViewById(2131296320);
		mCompassElevation = (TextView) findViewById(2131296321);
		mCompassTime = (TextView) findViewById(2131296322);
		mCompassSource = (TextView) findViewById(2131296323);
		mTripDistance = (TextView) findViewById(2131296332);
		mTripTimeMoving = (TextView) findViewById(2131296335);
		mTripTimeStopped = (TextView) findViewById(2131296338);
		mTripTimeTotal = (TextView) findViewById(2131296341);
		mSpeedMovingAvg = (TextView) findViewById(2131296344);
		mSpeedOverallAvg = (TextView) findViewById(2131296347);
		mAccelerationInfo = (TextView) findViewById(2131296354);
		mFrom0To60Row = (TableRow) findViewById(2131296356);
		mFrom0To100Row = (TableRow) findViewById(2131296359);
		mFrom0To60Result = (TextView) findViewById(2131296358);
		mFrom0To100Result = (TextView) findViewById(2131296361);
		mQuarterMileResult = (TextView) findViewById(2131296364);
		mFrom0To60Button = (Button) findViewById(2131296366);
		mFrom0To100Button = (Button) findViewById(2131296367);
		mQuarterMileButton = (Button) findViewById(2131296368);
		mRecordingStatus = (TextView) findViewById(2131296349);
		mRecordingButton = (Button) findViewById(2131296350);
		mGPXExportStatus = (TextView) findViewById(2131296372);
		mExportGPXButton = (Button) findViewById(2131296374);
		registerForContextMenu(mExportGPXButton);
		mSendGPXButton = (Button) findViewById(2131296375);
		mGoogleMapsButton = (Button) findViewById(2131296381);
		mOpenSpotButton = (Button) findViewById(2131296382);
		mMaverickButton = (Button) findViewById(2131296383);
		mFrom0To60Time = (TextView) findViewById(2131296386);
		mFrom0To60Feet = (TextView) findViewById(2131296387);
		mFrom0To60Speed = (TextView) findViewById(2131296389);
		mFrom0To60Info = (TextView) findViewById(2131296391);
		mConfirm0To60Button = (Button) findViewById(2131296393);
		mDiscard0To60Button = (Button) findViewById(2131296394);
		mFrom0To100Time = (TextView) findViewById(2131296397);
		mFrom0To100Meters = (TextView) findViewById(2131296398);
		mFrom0To100Speed = (TextView) findViewById(2131296400);
		mFrom0To100Info = (TextView) findViewById(2131296402);
		mConfirm0To100Button = (Button) findViewById(2131296404);
		mDiscard0To100Button = (Button) findViewById(2131296405);
		mQuarterMileTime = (TextView) findViewById(2131296408);
		mQuarterMileSpeed = (TextView) findViewById(2131296409);
		mQuarterMileDist = (TextView) findViewById(2131296411);
		mQuarterMileUnits = (TextView) findViewById(2131296412);
		mQuarterMileInfo = (TextView) findViewById(2131296413);
		mConfirmQtrButton = (Button) findViewById(2131296415);
		mDiscardQtrButton = (Button) findViewById(2131296416);
		mTownLimitToggle = (RelativeLayout) findViewById(2131296418);
		mTownLimitSign = (ImageView) findViewById(2131296419);
		mTownLimitNumbers = (TextView) findViewById(2131296420);
		mTownLimitDec = (ImageView) findViewById(2131296421);
		mTownLimitInc = (ImageView) findViewById(2131296422);
		mHighwayLimitToggle = (RelativeLayout) findViewById(2131296423);
		mHighwayLimitSign = (ImageView) findViewById(2131296424);
		mHighwayLimitNumbers = (TextView) findViewById(2131296425);
		mHighwayLimitDec = (ImageView) findViewById(2131296426);
		mHighwayLimitInc = (ImageView) findViewById(2131296427);
		mFreewayLimitToggle = (RelativeLayout) findViewById(2131296428);
		mFreewayLimitSign = (ImageView) findViewById(2131296429);
		mFreewayLimitNumbers = (TextView) findViewById(2131296430);
		mFreewayLimitDec = (ImageView) findViewById(2131296431);
		mFreewayLimitInc = (ImageView) findViewById(2131296432);
		mStartupScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				switchToScreen(1);
			}
		});
		mStartupScreen.setOnLongClickListener(new View.OnLongClickListener() {

			public boolean onLongClick(View view) {
				boolean flag = false;
				if (SpeedView.mWarningChecked) {
					mSwitchboard.setVisibility(0);
					flag = true;
				}
				return flag;
			}
		});
		mMainScreen.setOnLongClickListener(new View.OnLongClickListener() {

			public boolean onLongClick(View view) {
				boolean flag = false;
				if (SpeedView.mWarningChecked) {
					mSwitchboard.setVisibility(0);
					flag = true;
				}
				return flag;
			}
		});
		mCompassView.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				switchToScreen(2);
			}
		});
		mCompassView.setOnLongClickListener(new View.OnLongClickListener() {

			public boolean onLongClick(View view) {
				boolean flag = false;
				if (SpeedView.mWarningChecked) {
					mSwitchboard.setVisibility(0);
					flag = true;
				}
				return flag;
			}
		});
		mSpeedometerView.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				switchToScreen(3);
			}
		});
		mSpeedometerView.setOnLongClickListener(new View.OnLongClickListener() {

			public boolean onLongClick(View view) {
				boolean flag = false;
				if (SpeedView.mWarningChecked) {
					mSwitchboard.setVisibility(0);
					flag = true;
				}
				return flag;
			}
		});
		mOdometerField.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				SpeedView speedview = SpeedView.this;
				AlertDialog.Builder builder = new AlertDialog.Builder(speedview);
				builder.setTitle(2131099714).setMessage(2131099715);
				builder.setPositiveButton(2131099724,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								resetOdometer();
								displayStoredData();
								Toast.makeText(getBaseContext(), 2131099700, 1)
										.show();
							}
						});
				builder.setNegativeButton(2131099725,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {

							}
						}).show();
			}
		});
		mOdometer.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				SpeedView speedview = SpeedView.this;
				AlertDialog.Builder builder = new AlertDialog.Builder(speedview);
				builder.setTitle(2131099714).setMessage(2131099715);
				builder.setPositiveButton(2131099724,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								resetOdometer();
								displayStoredData();
								Toast.makeText(getBaseContext(), 2131099700, 1)
										.show();
							}
						});
				builder.setNegativeButton(2131099725,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {

							}
						}).show();
			}
		});
		mMaxField.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				SpeedView speedview = SpeedView.this;
				AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
						speedview);
				builder.setTitle(2131099716).setMessage(2131099717);
				builder.setPositiveButton(2131099724,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								resetMaxSpeed();
								Toast.makeText(getBaseContext(), 2131099701, 1)
										.show();
							}
						});
				builder.setNegativeButton(2131099725,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {

							}
						}).show();
			}
		});
		mMaxSpeed.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				SpeedView speedview = SpeedView.this;
				AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
						speedview);
				builder.setTitle(2131099716).setMessage(2131099717);
				builder.setPositiveButton(2131099724,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								resetMaxSpeed();
								Toast.makeText(getBaseContext(), 2131099701, 1)
										.show();
							}
						});
				builder.setNegativeButton(2131099725,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {

							}
						}).show();
			}
		});
		// TODO
		// _lcls22 = new _cls22();
		// mGraphView.setOnClickListener(_lcls22);
		mGraphView.setOnLongClickListener(new View.OnLongClickListener() {

			public boolean onLongClick(View view) {
				boolean flag = false;
				if (SpeedView.mWarningChecked) {
					mSwitchboard.setVisibility(0);
					flag = true;
				}
				return flag;
			}
		});
		mCompassScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				switchToScreen(1);
			}
		});
		mCompassScreen.setOnLongClickListener(new View.OnLongClickListener() {

			public boolean onLongClick(View view) {
				boolean flag = false;
				if (SpeedView.mWarningChecked) {
					mSwitchboard.setVisibility(0);
					flag = true;
				}
				return flag;
			}
		});
		mHudScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				switchToScreen(1);
			}
		});
		mHudScreen.setOnLongClickListener(new View.OnLongClickListener() {

			public boolean onLongClick(View view) {
				boolean flag = false;
				if (SpeedView.mWarningChecked) {
					mSwitchboard.setVisibility(0);
					flag = true;
				}
				return flag;
			}
		});
		mFrom0To60Button.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				mAcclStartLocation = null;
				mFrom0To60Time.setText(2131099787);
				mFrom0To60Feet.setText(2131099788);
				mFrom0To60Speed.setText("0");
				mFrom0To60Speed.setTextColor(-1);
				mFrom0To60Info.setText(2131099790);
				mFrom0To60Info.setTextColor(-3355444);
				mConfirm0To60Button.setEnabled(false);
				mDiscard0To60Button.setEnabled(false);
				mFrom0To60Screen.setVisibility(0);
			}
		});
		mFrom0To100Button.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				mAcclStartLocation = null;
				mFrom0To100Time.setText(2131099787);
				mFrom0To100Meters.setText(2131099795);
				mFrom0To100Speed.setText("0");
				mFrom0To100Speed.setTextColor(-1);
				mFrom0To100Info.setText(2131099796);
				mFrom0To100Info.setTextColor(-3355444);
				mConfirm0To100Button.setEnabled(false);
				mDiscard0To100Button.setEnabled(false);
				mFrom0To100Screen.setVisibility(0);

			}
		});
		mQuarterMileButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				mAcclStartLocation = null;
				mQuarterMileTime.setText(2131099787);
				StringBuilder stringbuilder = new StringBuilder("0 ");
				String s = SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits];
				String s1 = stringbuilder.append(s).toString();
				mQuarterMileSpeed.setText(s1);
				mQuarterMileDist.setText("0");
				mQuarterMileDist.setTextColor(-1);
				String s2;
				if (SpeedView.mDisplayUnits == 1)
					s2 = getString(2131099800);
				else
					s2 = getString(2131099799);
				mQuarterMileUnits.setText(s2);
				mQuarterMileInfo.setText(2131099801);
				mQuarterMileInfo.setTextColor(-3355444);
				mConfirmQtrButton.setEnabled(false);
				mDiscardQtrButton.setEnabled(false);
				mQuarterMileScreen.setVisibility(0);

			}
		});
		mFrom0To60Screen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				m60MphReached = false;
				mFrom0To60Screen.setVisibility(4);
			}
		});
		mConfirm0To60Button.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				SpeedView speedview = SpeedView.this;
				speedview.mStored0To60Time = mTemp0To60Time;
				mAccelerationInfo.setText(2131099759);
				mFrom0To60Screen.setVisibility(4);
			}
		});
		mDiscard0To60Button.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				m60MphReached = false;
				mFrom0To60Screen.setVisibility(4);
			}
		});
		mFrom0To100Screen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				m100KphReached = false;
				mFrom0To100Screen.setVisibility(4);
			}
		});
		mConfirm0To100Button.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				SpeedView speedview = SpeedView.this;
				speedview.mStored0To100Time = mTemp0To100Time;
				mAccelerationInfo.setText(2131099759);
				mFrom0To100Screen.setVisibility(4);

			}
		});
		mDiscard0To100Button.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				m100KphReached = false;
				mFrom0To100Screen.setVisibility(4);
			}
		});
		mQuarterMileScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				mQtrMileReached = false;
				mQuarterMileScreen.setVisibility(4);

			}
		});
		mConfirmQtrButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				SpeedView speedview = SpeedView.this;
				String s = mTempQtrMileTime;
				speedview.mStoredQtrMileTime = s;
				mAccelerationInfo.setText(2131099759);
				mQuarterMileScreen.setVisibility(4);

			}
		});
		mDiscardQtrButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				mQtrMileReached = false;
				mQuarterMileScreen.setVisibility(4);
			}
		});
		// TODO
		// _lcls40 = new _cls40();
		// mRecordingButton.setOnClickListener(_lcls40);
		mExportGPXButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				String s = Environment.getExternalStorageState();
				if (!"mounted".equals(s)) {
					Toast.makeText(getBaseContext(), 2131099812, 1).show();
					return;
				} else {
					return;
				}
			}
		});
		mSendGPXButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				String s = Environment.getExternalStorageState();
				if (!"mounted".equals(s)) {
					Toast.makeText(getBaseContext(), 2131099812, 1).show();
					return;
				} else {
					return;
				}
			}
		});
		mGoogleMapsButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClassName("com.google.android.apps.maps",
						"com.google.android.maps.MapsActivity");
				startActivity(intent);
			}
		});
		mOpenSpotButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				if (SpeedView.mOpenSpotInst) {
					Intent intent = new Intent();
					intent.setClassName("com.googlelabs.openspot",
							"com.google.android.apps.openspot.activities.MainActivity");
					startActivity(intent);
					return;
				} else {
					SpeedView speedview = SpeedView.this;
					Uri uri = Uri
							.parse("market://details?id=com.googlelabs.openspot");
					Intent intent2 = new Intent("android.intent.action.VIEW",
							uri);
					speedview.startActivity(intent2);
					return;
				}
			}
		});
		mMaverickButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				if (SpeedView.mMaverickInst) {
					Intent intent = new Intent();
					if (SpeedView.mMaverickVersion == SpeedView.VERSIONS[0])
						intent.setClassName("com.codesector.maverick.full",
								"com.codesector.maverick.full.Main");
					else
						intent.setClassName("com.codesector.maverick.lite",
								"com.codesector.maverick.lite.Main");
					startActivity(intent);
					return;
				} else {
					SpeedView speedview = SpeedView.this;
					Uri uri = Uri
							.parse("market://details?id=com.codesector.maverick.lite");
					Intent intent3 = new Intent("android.intent.action.VIEW",
							uri);
					speedview.startActivity(intent3);
					return;
				}
			}
		});
		mSwitchboard.setOnLongClickListener(new View.OnLongClickListener() {

			public boolean onLongClick(View v) {
				mSwitchboard.setVisibility(4);
				Context context = getBaseContext();
				String s = String.valueOf(getString(2131099830));
				StringBuilder stringbuilder = (new StringBuilder(s))
						.append(" ");
				stringbuilder.append(mSpeedWarning).append(" ");
				String s1 = SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits];
				String s2 = stringbuilder.append(s1).toString();
				Toast.makeText(context, s2, 1).show();
				return true;

			}
		});
		mTownLimitToggle.setOnLongClickListener(new View.OnLongClickListener() {

			public boolean onLongClick(View v) {
				v.performClick();
				mSwitchboard.setVisibility(4);
				Context context = getBaseContext();
				String s = String.valueOf(getString(2131099830));
				StringBuilder stringbuilder = (new StringBuilder(s))
						.append(" ");
				stringbuilder.append(mSpeedWarning).append(" ");
				String s1 = SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits];
				String s2 = stringbuilder.append(s1).toString();
				Toast.makeText(context, s2, 1).show();
				return true;

			}
		});
		mTownLimitToggle.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				mTownLimitSign.setAlpha(250);
				mHighwayLimitSign.setAlpha(100);
				mFreewayLimitSign.setAlpha(100);
				mTownLimitDec.setAlpha(250);
				mTownLimitInc.setAlpha(250);
				mHighwayLimitDec.setAlpha(100);
				mHighwayLimitInc.setAlpha(100);
				mFreewayLimitDec.setAlpha(100);
				mFreewayLimitInc.setAlpha(100);
				mCurrentSpeedLimit = 0;
				SpeedView speedview = SpeedView.this;
				speedview.mSpeedWarning = mTownSpeedLimit;

			}
		});
		mTownLimitDec.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				SpeedView speedview = SpeedView.this;
				speedview.mTownSpeedLimit = speedview.mTownSpeedLimit + -1;
				mTownLimitNumbers.setText(mTownSpeedLimit);
				mTownLimitToggle.performClick();

			}
		});
		mTownLimitInc.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				SpeedView speedview = SpeedView.this;
				speedview.mTownSpeedLimit = speedview.mTownSpeedLimit + 1;
				mTownLimitNumbers.setText(mTownSpeedLimit);
				mTownLimitToggle.performClick();

			}
		});
		mHighwayLimitToggle
				.setOnLongClickListener(new View.OnLongClickListener() {

					public boolean onLongClick(View v) {
						v.performClick();
						mSwitchboard.setVisibility(4);
						Context context = getBaseContext();
						String s = String.valueOf(getString(2131099830));
						StringBuilder stringbuilder = (new StringBuilder(s))
								.append(" ");
						stringbuilder.append(mSpeedWarning).append(" ");
						String s1 = SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits];
						String s2 = stringbuilder.append(s1).toString();
						Toast.makeText(context, s2, Toast.LENGTH_SHORT).show();
						return true;
					}
				});
		mHighwayLimitToggle.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				mHighwayLimitSign.setAlpha(250);
				mTownLimitSign.setAlpha(100);
				mFreewayLimitSign.setAlpha(100);
				mHighwayLimitDec.setAlpha(250);
				mHighwayLimitInc.setAlpha(250);
				mTownLimitDec.setAlpha(100);
				mTownLimitInc.setAlpha(100);
				mFreewayLimitDec.setAlpha(100);
				mFreewayLimitInc.setAlpha(100);
				mCurrentSpeedLimit = 1;
				SpeedView speedview = SpeedView.this;
				speedview.mSpeedWarning = mHighwaySpeedLimit;
			}
		});
		mHighwayLimitDec.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				SpeedView speedview = SpeedView.this;
				speedview.mHighwaySpeedLimit = speedview.mHighwaySpeedLimit - 1;
				mHighwayLimitNumbers.setText(mHighwaySpeedLimit);
				mHighwayLimitToggle.performClick();
			}
		});
		mHighwayLimitInc.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				SpeedView speedview = SpeedView.this;
				speedview.mHighwaySpeedLimit = speedview.mHighwaySpeedLimit + 1;
				mHighwayLimitNumbers.setText(mHighwaySpeedLimit);
				mHighwayLimitToggle.performClick();
			}
		});
		mFreewayLimitToggle
				.setOnLongClickListener(new View.OnLongClickListener() {

					public boolean onLongClick(View v) {
						v.performClick();
						mSwitchboard.setVisibility(4);
						Context context = getBaseContext();
						String s = String.valueOf(getString(2131099830));
						StringBuilder stringbuilder = (new StringBuilder(s))
								.append(" ");
						stringbuilder.append(mSpeedWarning).append(" ");
						String s1 = SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits];
						stringbuilder.append(s1).toString();
						Toast.makeText(context, stringbuilder.toString(),
								Toast.LENGTH_LONG).show();
						return true;
					}
				});
		mFreewayLimitToggle.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				mFreewayLimitSign.setAlpha(250);
				mTownLimitSign.setAlpha(100);
				mHighwayLimitSign.setAlpha(100);
				mFreewayLimitDec.setAlpha(250);
				mFreewayLimitInc.setAlpha(250);
				mTownLimitDec.setAlpha(100);
				mTownLimitInc.setAlpha(100);
				mHighwayLimitDec.setAlpha(100);
				mHighwayLimitInc.setAlpha(100);
				mCurrentSpeedLimit = 2;
				SpeedView speedview = SpeedView.this;
				speedview.mSpeedWarning = mFreewaySpeedLimit;
			}
		});
		mFreewayLimitDec.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				SpeedView speedview = SpeedView.this;
				speedview.mFreewaySpeedLimit = speedview.mFreewaySpeedLimit - 1;
				mFreewayLimitNumbers.setText(mFreewaySpeedLimit);
				mFreewayLimitToggle.performClick();
			}
		});
		mFreewayLimitInc.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				SpeedView speedview = SpeedView.this;
				speedview.mFreewaySpeedLimit = speedview.mFreewaySpeedLimit + 1;
				mFreewayLimitNumbers.setText(mFreewaySpeedLimit);
				mFreewayLimitToggle.performClick();
			}
		});
	}

	private void switchToScreen(int i) {
		switch (i) {
		default:
			return;
		case 0: // '\0'
			mMainScreen.setVisibility(4);
			mCompassScreen.setVisibility(4);
			mHudScreen.setVisibility(4);
			mAdvancedScreen.setVisibility(4);
			mStartupScreen.setVisibility(0);
			mSelectedDashboard = 0;
		case 1: // '\001'
			mStartupScreen.setVisibility(4);
			mCompassScreen.setVisibility(4);
			mHudScreen.setVisibility(4);
			mAdvancedScreen.setVisibility(4);
			mMainScreen.setVisibility(0);
			mSelectedDashboard = 1;
		case 2: // '\002'
			mStartupScreen.setVisibility(4);
			mMainScreen.setVisibility(4);
			mHudScreen.setVisibility(4);
			mAdvancedScreen.setVisibility(4);
			mCompassScreen.setVisibility(0);
			mSelectedDashboard = 2;
		case 3: // '\003'
			mStartupScreen.setVisibility(4);
			mMainScreen.setVisibility(4);
			mCompassScreen.setVisibility(4);
			mAdvancedScreen.setVisibility(4);
			mHudScreen.setVisibility(0);
			mSelectedDashboard = 3;
		case 4: // '\004'
			mStartupScreen.setVisibility(4);
			mMainScreen.setVisibility(4);
			mCompassScreen.setVisibility(4);
			mHudScreen.setVisibility(4);
			mAdvancedScreen.setVisibility(0);
			mSelectedDashboard = 4;
			break;
		}
	}

	private void resetMaxSpeed() {
		mStoredMaxSpeed = 0F;
		if (mSelectedDashboard == 4) {
			return;
		} else {
			mOdometer.setText(distanceToString());
			mMaxSpeed.setText(getDisplaySpeed(mStoredMaxSpeed));
			return;
		}
	}

	private void resetOdometer() {
		mStoredDistance = 0F;
		mStoredMovingTime = 0L;
		mStoredTotalTime = 0L;
		mFirstFixTime = 0L;
		mSessionMovingTime = 0L;
		mSessionTotalTime = 0L;
	}

	private Location getLastLocation() {
		return mLastLocation;
	}

	private void displayAddress(final Location location) {
		(new Thread() {

			public void run() {
				try {
					List list = mGeocoder.getFromLocation(
							location.getLatitude(), location.getLongitude(), 1);
					if (list.size() > 0) {
						mAddress = (Address) list.get(0);
						mHandler.post(mAddressFound);
					} else {
						mHandler.post(mUnableToGetAddress);
					}
				} catch (Exception exception) {
					mHandler.post(mNetworkFailure);
				}
			}

		}).start();
	}

	protected void onPause() {
		super.onPause();
		mLocationManager.removeUpdates(mLocationListener);
		mLocationManager.removeGpsStatusListener(mGPSListener);
		mSensorManager.unregisterListener(mOrientationListener);
		saveUserPreferences();
		if (mTrackLoggingChecked)
			saveCurrentTrack();
		if ((mRunInBGChecked && !mExitButtonPressed && !mShareButtonPressed
				&& !mSettingsButtonPressed && !mFeaturedButtonPressed || mMinimizeButtonPressed)
				&& mIsGPSEnabled && mIsRecording) {
			Intent intent = new Intent(this,
					com.codesector.speedview.pro.BackgroundService.class);
			intent.putExtra("distance", mStoredDistance);
			intent.putExtra("max_speed", mStoredMaxSpeed);
			intent.putExtra("moving_time", mStoredMovingTime
					+ mSessionMovingTime);
			intent.putExtra("total_time", mStoredTotalTime + mSessionTotalTime);
			intent.putExtra("display_units", mDisplayUnits);
			intent.putExtra("warning_checked", mWarningChecked);
			intent.putExtra("speed_warning", mSpeedWarning);
			intent.putExtra("sould_alert_toggled", mSoundAlertToggled);
			if (mWarningChecked && mSoundAlertToggled && mWarningSound != null) {
				String s;
				if (mAlertSoundUri == null)
					s = "";
				else
					s = mAlertSoundUri.toString();
				intent.putExtra("alert_sound_uri", s);
			}
			intent.putExtra("vibration_checked", mVibrationChecked);
			intent.putExtra("track_logging_checked", mTrackLoggingChecked);
			intent.putExtra("min_time_between_pts", mMinTimeBetweenPts);
			intent.putExtra("min_dist_between_pts", mMinDistBetweenPts);
			intent.putExtra("narrowing_checked", mNarrowingChecked);
			intent.putExtra("minimum_accuracy", mMinimumAccuracy);
			startService(intent);
		}
		mSessionMovingTime = 0L;
		mSessionTotalTime = 0L;
	}

	private void saveUserPreferences() {
		SharedPreferences.Editor editor = getSharedPreferences("PrefsFile", 0)
				.edit();
		editor.putInt("currentVersion", mVersionCode);
		editor.putInt("storedOrientation", mStoredOrientation);
		editor.putBoolean("isRecording", mIsRecording);
		editor.putFloat("storedDistance", mStoredDistance);
		editor.putFloat("storedMaxSpeed", mStoredMaxSpeed);
		editor.putString("stored0To60Time", mStored0To60Time);
		editor.putString("stored0To100Time", mStored0To100Time);
		editor.putString("storedQtrMileTime", mStoredQtrMileTime);
		editor.putLong("storedMovingTime", mStoredMovingTime
				+ mSessionMovingTime);
		editor.putLong("storedTotalTime", mStoredTotalTime + mSessionTotalTime);
		editor.putBoolean("warningChecked", mWarningChecked);
		editor.putInt("currentSpeedLimit", mCurrentSpeedLimit);
		editor.putInt("townSpeedLimit", mTownSpeedLimit);
		editor.putInt("highwaySpeedLimit", mHighwaySpeedLimit);
		editor.putInt("freewaySpeedLimit", mFreewaySpeedLimit);
		editor.putInt("speedBarColor", mSpeedBarColor);
		editor.putInt("primaryTextColor", mPrimaryTextColor);
		editor.putInt("secondaryTextColor", mSecondaryTextColor);
		editor.putInt("graphArrayPointer", mGraphView.mArrayPointer);
		editor.putString("graphHexString", mGraphView.getHexArray());
		editor.putBoolean("notifiedAboutScreen", mNotifiedAboutScreen);
		editor.putBoolean("notifiedAboutGPS", mNotifiedAboutGPS);
		editor.putBoolean("notifiedAboutNetwork", mNotifiedAboutNetwork);
		editor.commit();
	}

	private void displayStoredData() {
		float f = 0F;
		float f1 = 0F;
		if (mStoredMovingTime + mSessionMovingTime != 0L) {
			float f3 = (float) (mStoredMovingTime + mSessionMovingTime) / 1000F;
			f = mStoredDistance / f3;
		}
		if (mStoredTotalTime + mSessionTotalTime != 0L) {
			float f5 = (float) (mStoredTotalTime + mSessionTotalTime) / 1000F;
			f1 = mStoredDistance / f5;
		}
		if (mSelectedDashboard == 4) {
			mTripDistance.setText(distanceToString());
			mTripTimeMoving.setText(getElapsedTimeString(mStoredMovingTime
					+ mSessionMovingTime));
			mTripTimeStopped.setText(getElapsedTimeString(mStoredTotalTime
					+ mSessionTotalTime - mStoredMovingTime
					- mSessionMovingTime));
			mTripTimeTotal.setText(getElapsedTimeString(mStoredTotalTime
					+ mSessionTotalTime));
			mSpeedMovingAvg.setText(String.valueOf(getDisplaySpeed(f)) + " "
					+ UNITS_ARRAY[mDisplayUnits]);
			mSpeedOverallAvg.setText(String.valueOf(getDisplaySpeed(f1)) + " "
					+ UNITS_ARRAY[mDisplayUnits]);
			mFrom0To60Result.setText(mStored0To60Time);
			mFrom0To100Result.setText(mStored0To100Time);
			mQuarterMileResult.setText(mStoredQtrMileTime);
		} else {
			mOdometer.setText(distanceToString());
			mMaxSpeed.setText(getDisplaySpeed(mStoredMaxSpeed));
		}
	}

	// TODO
	private String getElapsedTimeString(long l) {
		Object aobj[] = new Object[1];
		Integer integer = Integer.valueOf(2);
		aobj[0] = integer;
		String s = String.format("%%0%dd", aobj);
		long l1 = l / 1000L;
		Object aobj1[] = new Object[1];
		aobj1[0] = Long.valueOf(l1 % 60L);
		String s1 = String.format(s, aobj1);
		Object aobj2[] = new Object[1];
		aobj2[0] = Long.valueOf((l1 % 3600L) / 60L);
		String s2 = String.format(s, aobj2);
		Object aobj3[] = new Object[1];
		aobj3[0] = Long.valueOf(l1 / 3600L);
		String s3 = String.valueOf(String.format(s, aobj3));
		return (new StringBuilder(s3)).append(":").append(s2).append(":")
				.append(s1).toString();
	}

	public void refreshMainScreen() {
		if (!mDsblRotationChecked || mStoredOrientation != 1
				&& mStoredOrientation != 3) {
			if (mDsblRotationChecked)
				return;
			if (getResources().getConfiguration().orientation != 2)
				return;
		}
		switch (mDisplayUnits) {
		default:
			return;

		case 0: // '\0'
			mFrom0To100RowT.setVisibility(8);
			mFrom0To60RowT.setVisibility(0);
			mAccelerationLayoutT.setVisibility(0);
			return;

		case 1: // '\001'
			mFrom0To60RowT.setVisibility(8);
			mFrom0To100RowT.setVisibility(0);
			mAccelerationLayoutT.setVisibility(0);
			return;

		case 2: // '\002'
			mAccelerationLayoutT.setVisibility(8);
			break;
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(2131230720, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public void onCreateContextMenu(ContextMenu contextmenu, View view,
			android.view.ContextMenu.ContextMenuInfo contextmenuinfo) {
		super.onCreateContextMenu(contextmenu, view, contextmenuinfo);
		contextmenu.setHeaderTitle(2131099778);
		if (mLogFilesList == null)
			return;
		if (mLogFilesList.length <= 0)
			return;
		int i = 0;
		while (i < mLogFilesList.length) {
			MenuItem menuitem = contextmenu.add(0, i, 0, mLogFilesList[i]);
			i++;
		}
	}

	public boolean onContextItemSelected(MenuItem menuitem) {
		String s = mLogFilesList[menuitem.getItemId()];
		exportTrackFileEx(s);
		return super.onContextItemSelected(menuitem);
	}

	private void setFullScreenMode(boolean flag) {
		WindowManager.LayoutParams layoutparams = getWindow().getAttributes();
		if (flag) {
			layoutparams.flags = layoutparams.flags | 1024;
		} else {
			layoutparams.flags = layoutparams.flags & -1025;
		}
		getWindow().setAttributes(layoutparams);
	}

	private void exportTrackFileEx(final String file) {
		mProgressDialog.show();
		(new _cls90(file)).start();
	}

	// TODO
	private class _cls90 extends Thread {

		public _cls90(String s) {

		}
	}

	private void exportTrackFile(boolean flag) {
		saveCurrentTrack();
		mSendTrackInit = flag;
		StringBuilder stringbuilder = new StringBuilder();
		File file = Environment.getExternalStorageDirectory();
		String s = stringbuilder.append(file).append("/speedview/logs")
				.toString();
		File file1 = new File(s);
		if (!file1.exists()) {
			progressHandler.sendEmptyMessage(0);
			return;
		}
		mLogFilesList = file1.list(mLogExtensionFilter);
		if (mLogFilesList != null) {
			if (mLogFilesList.length > 1) {
				Arrays.sort(mLogFilesList);
				mExportGPXButton.showContextMenu();
				return;
			} else {
				exportTrackFileEx(mLogFilesList[0]);
				return;
			}
		} else {
			progressHandler.sendEmptyMessage(0);
			return;
		}
	}

	private void resetAcclTimes() {
		mStored0To60Time = getString(2131099663);
		mStored0To100Time = getString(2131099663);
		mStoredQtrMileTime = getString(2131099663);
	}

	private void checkGPSEnabled() {
		if (!mLocationManager.isProviderEnabled("gps")) {
			if (!mNotifiedAboutGPS) {
				AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
						this);
				builder.setTitle(2131099709).setMessage(2131099710);
				builder.setPositiveButton(2131099693,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								SpeedView speedview = SpeedView.this;
								Intent intent = new Intent(
										"android.settings.LOCATION_SOURCE_SETTINGS");
								speedview.startActivity(intent);

							}
						});
				builder.setNeutralButton(2131099711,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {

							}
						});
				builder.setNegativeButton(2131099691,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								mExitButtonPressed = true;
								finish();
							}
						}).show();
				mNotifiedAboutGPS = true;
			}
			mStatusMessage.setText(2131099734);
			mNumOfSatellites.setText(2131099735);
			mHandler.post(mGPSIsDisabled);
			mTipMessage.setText(2131099667);
			mRecordingStatus.setTextColor(-7829368);
			mRecordingButton.setEnabled(false);
			mIsGPSEnabled = false;
			return;
		} else {
			mRecordingStatus.setTextColor(-3355444);
			mRecordingButton.setEnabled(true);
			mIsGPSEnabled = true;
			return;
		}
	}

	private boolean isNetworkAvailable() {
		boolean flag = true;
		if (((ConnectivityManager) getSystemService("connectivity"))
				.getActiveNetworkInfo() == null) {
			if (!mNotifiedAboutNetwork) {
				AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
						this);
				builder.setTitle(2131099712).setMessage(2131099713);
				builder.setPositiveButton(2131099693,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								SpeedView speedview = SpeedView.this;
								Intent intent = new Intent(
										"android.settings.WIRELESS_SETTINGS");
								speedview.startActivity(intent);

							}
						});
				builder.setNeutralButton(2131099711,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {

							}
						});
				builder.setNegativeButton(2131099691,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								mExitButtonPressed = true;
								finish();
							}
						}).show();
				mNotifiedAboutNetwork = true;
			}
			flag = false;
		}
		return flag;
	}

	private boolean hasMatchingActivity(String s, String s1) {
		Intent intent = new Intent();
		intent.setClassName(s, s1);
		boolean flag;
		if (getPackageManager().queryIntentActivities(intent, 65536).size() > 0)
			flag = true;
		else
			flag = false;
		return flag;
	}

	public SpeedView() {
		mTrackBuffer = new StringBuilder();
		mBackgroundConn = new _cls1();
		mOrientationListener = new _cls3();
		progressHandler = new _cls4();
		mHandler = new Handler();
		mAddressFound = new _cls5();
		mUnableToGetAddress = new _cls6();
		mNetworkFailure = new _cls7();
		mGPSIsDisabled = new _cls8();
	}

	private class _cls1 implements ServiceConnection {

		public void onServiceConnected(ComponentName componentname,
				IBinder ibinder) {
			SpeedView speedview = SpeedView.this;
			speedview.mStoredDistance = ((BackgroundService.LocalBinder) ibinder)
					.getStoredDistance();
			speedview.mStoredMaxSpeed = ((BackgroundService.LocalBinder) ibinder)
					.getStoredMaxSpeed();
			speedview.mStoredMovingTime = ((BackgroundService.LocalBinder) ibinder)
					.getStoredMovingTime();
			speedview.mStoredTotalTime = ((BackgroundService.LocalBinder) ibinder)
					.getStoredTotalTime();
			displayStoredData();
			((BackgroundService.LocalBinder) ibinder).dumpCurrentTrack();
			speedview.unbindService(mBackgroundConn);
			Intent intent = new Intent(speedview,
					com.codesector.speedview.pro.BackgroundService.class);
			speedview.stopService(intent);
		}

		public void onServiceDisconnected(ComponentName componentname) {
		}

	}

	private class _cls3 implements SensorEventListener {

		public void onAccuracyChanged(Sensor sensor, int i) {
		}

		public void onSensorChanged(SensorEvent sensorevent) {
			float f = sensorevent.values[0];
			if (mLastLocation != null && mLastLocation.getSpeed() >= 1.4F)
				return;
			if (SpeedView.mSelectedDashboard == 2) {
				mCompassMode.onSensorChanged(f);
				mCompassSource.setText(2131099747);
				return;
			}
			if (SpeedView.mSelectedDashboard != 1) {
				return;
			} else {
				mCompassView.onSpeedChanged(f);
				return;
			}
		}
	}

	private class _cls4 extends Handler {

		public void handleMessage(Message message) {
			mProgressDialog.dismiss();
			switch (message.what) {
			default:
				return;
			case 0: // '\0'
				SpeedView speedview = SpeedView.this;
				AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
						speedview);
				builder.setTitle(2131099776).setMessage(2131099777);
				android.app.AlertDialog alertdialog = builder.setNeutralButton(
						2131099939, new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {

							}
						}).show();
				return;

			case 1: // '\001'
				if (mSendTrackInit) {
					String s = message.getData().getString("file_path");
					Intent intent = new Intent("android.intent.action.SEND");
					intent.setType("application/gpx");
					intent.putExtra("android.intent.extra.SUBJECT",
							getString(2131099780));
					intent.putExtra("android.intent.extra.TEXT",
							getString(2131099781));
					Uri uri = Uri.parse((new StringBuilder("file://"))
							.append(s).toString());
					intent.putExtra("android.intent.extra.STREAM", uri);
					Intent intent5 = Intent.createChooser(intent,
							getString(2131099773));
					SpeedView.this.startActivity(intent5);
					return;
				} else {
					String s4 = String.valueOf(getString(2131099771));
					StringBuilder stringbuilder = new StringBuilder(s4);
					stringbuilder.append(SpeedView.mGPXFileLocation);
					stringbuilder.append(getString(2131099772)).toString();
					mGPXExportStatus.setText(stringbuilder.toString());
					return;
				}

			case 2: // '\002'
				SpeedView speedview2 = SpeedView.this;
				AlertDialog.Builder builder1 = (new android.app.AlertDialog.Builder(
						speedview2)).setTitle(2131099726)
						.setMessage(2131099727);
				builder1.setNeutralButton(2131099939,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {

							}
						}).show();
			}
		}
	}

	private class _cls5 implements Runnable {

		public void run() {
			if (mAddress == null)
				return;
			String s = mAddress.getAddressLine(0);
			String s1 = mAddress.getAddressLine(1);
			String s2 = mAddress.getAddressLine(2);
			if (s != null)
				SpeedView.this.mAddressString = s;
			if (s1 != null) {
				String s4 = String.valueOf(SpeedView.this.mAddressString);
				SpeedView.this.mAddressString = (new StringBuilder(s4))
						.append(", ").append(s1).toString();
				if (s2 != null) {
					String s6 = String.valueOf(s1);
					s1 = (new StringBuilder(s6)).append(", ").append(s2)
							.toString();
					String s7 = String.valueOf(SpeedView.this.mAddressString);
					String s8 = (new StringBuilder(s7)).append(", ").append(s2)
							.toString();
					SpeedView.this.mAddressString = s8;
				}
			} else {
				s1 = "";
			}
			if (s == null)
				s = "";
			mAddressLine0.setText(s);
			mAddressLine1.setText(s1);
		}
	}

	private class _cls6 implements Runnable {

		public void run() {
			mAddressLine0.setText(2131099730);
			mAddressLine1.setText(2131099731);
		}
	}

	private class _cls7 implements Runnable {

		public void run() {
			mAddressLine0.setText(2131099732);
			mAddressLine1.setText(2131099733);
		}
	}

	private class _cls8 implements Runnable {

		public void run() {
			mAddressLine0.setText(2131099734);
			mAddressLine1.setText(2131099736);
		}
	}

	// TODO
	private String distanceToString() {
		return "";
	}

	// TODO
	private int getDisplaySpeed(float f) {
		return 0;
	}

	// TODO
	private void saveCurrentTrack() {

	}

	// TODO
	protected Dialog onCreateDialog(int i) {
		return null;
	}

	// TODO
	public boolean onOptionsItemSelected(MenuItem menuitem) {
		return false;
	}

	// TODO
	public boolean onPrepareOptionsMenu(Menu menu) {
		return false;
	}

	// TODO
	public void selectTab(int i) {

	}

	// TODO
	protected void onResume() {

	}

	// TODO
	public boolean onKeyDown(int i, KeyEvent keyevent) {
		return false;
	}

	// TODO
	private class MyLocationListener implements LocationListener {

		public void onLocationChanged(Location location) {

		}

		public void onProviderDisabled(String s) {

		}

		public void onProviderEnabled(String s) {

		}

		public void onStatusChanged(String s, int i, Bundle bundle) {

		}

	}

	// TODO
	private class MyGPSListener implements android.location.GpsStatus.Listener {

		public void onGpsStatusChanged(int event) {

		}

	}
}