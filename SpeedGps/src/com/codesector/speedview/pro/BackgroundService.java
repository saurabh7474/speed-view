/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.codesector.speedview.pro;

import android.annotation.SuppressLint;
import android.app.*;
import android.content.ComponentName;
import android.content.Intent;
import android.location.*;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.*;
import android.widget.RemoteViews;
import java.io.*;
import java.text.*;
import java.util.*;

// Referenced classes of package com.codesector.speedview.pro:
//            WidgetProvider, SpeedView

public class BackgroundService extends Service {
	public class LocalBinder extends Binder {

		void dumpCurrentTrack() {
			saveCurrentTrack();
		}

		BackgroundService getService() {
			return BackgroundService.this;
		}

		float getStoredDistance() {
			return mStoredDistance;
		}

		float getStoredMaxSpeed() {
			return mStoredMaxSpeed;
		}

		long getStoredMovingTime() {
			return mStoredMovingTime + mSessionMovingTime;
		}

		long getStoredTotalTime() {
			return mStoredTotalTime + mSessionTotalTime;
		}
	}

	private class MyGPSListener implements android.location.GpsStatus.Listener {

		public void onGpsStatusChanged(int i) {
			switch (i) {
			case GpsStatus.GPS_EVENT_STARTED :
				if (!mIsRunning)
					return;
				Iterator iterator = gpsStatus.getSatellites().iterator();
				int j = 0;
				gpsStatus = mLocationManager.getGpsStatus(gpsStatus);
				do {
					if (!iterator.hasNext()) {
						String s;
						String s2;
						if (mLastLocation != null) {
							if (SystemClock.elapsedRealtime() - mLastLocationTime < 10000L)
								hasGPSFix = true;
							else
								hasGPSFix = false;
						}
						if (hasGPSFix)
							return;
						if (j < 4)
							s = getString(2131099651);
						else
							s = getString(2131099653);
						StringBuilder stringbuilder = (new StringBuilder(
								String.valueOf(j))).append(" ");
						if (j != 1)
							s2 = getString(2131099654);
						else
							s2 = getString(2131099655);
						stringbuilder.append(s2).toString();
						showNotification(s, stringbuilder.toString());
						return;
					}
					iterator.next();
					j++;
				} while (true);
			case 2: // '\002'
			case 3: // '\003'
			case 4: // '\004'
			}

		}

		private GpsStatus gpsStatus;
		private boolean hasGPSFix;
	}

	private class MyLocationListener implements LocationListener {

		public void onLocationChanged(Location location) {
			if (location != null) {
				Message message = mServiceHandler.obtainMessage();
				message.arg1 = 2;
				message.obj = location;
				mServiceHandler.sendMessage(message);
			}
		}

		public void onProviderDisabled(String s) {
			showNotification(getString(2131099709), getString(2131099710));
		}

		public void onProviderEnabled(String s) {
			showNotification(getString(2131099651), getString(2131099652));
		}

		public void onStatusChanged(String s, int i, Bundle bundle) {
		}
	}

	private final class ServiceHandler extends Handler {

		public void handleMessage(Message message) {
			switch (message.arg1) {
			case 1:
				Bundle bundle = (Bundle) message.obj;
				mStoredDistance = bundle.getFloat("distance");
				mStoredMaxSpeed = bundle.getFloat("max_speed");
				mStoredMovingTime = bundle.getLong("moving_time");
				mStoredTotalTime = bundle.getLong("total_time");
				mDisplayUnits = bundle.getInt("display_units");
				mWarningChecked = bundle.getBoolean("warning_checked");
				mSpeedWarning = bundle.getInt("speed_warning");
				mSoundAlertToggled = bundle.getBoolean("sould_alert_toggled");
				String s = bundle.getString("alert_sound_uri");
				if (s != null) {
					BackgroundService backgroundservice3 = BackgroundService.this;
					Uri uri;
					if (s.equals(""))
						uri = null;
					else
						uri = Uri.parse(s);
					backgroundservice3.mAlertSoundUri = uri;
					mWarningSound = RingtoneManager.getRingtone(
							getBaseContext(), mAlertSoundUri);
				}
				mVibrationChecked = bundle.getBoolean("vibration_checked");
				mTrackLoggingChecked = bundle
						.getBoolean("track_logging_checked");
				mMinTimeBetweenPts = bundle.getInt("min_time_between_pts");
				mMinDistBetweenPts = bundle.getInt("min_dist_between_pts");
				mNarrowingChecked = bundle.getBoolean("narrowing_checked");
				mMinimumAccuracy = bundle.getInt("minimum_accuracy");
				showNotification(getString(2131099651), getString(2131099652));
				mIsRunning = true;
				break;
			case 2:
				Location location = (Location) message.obj;
				if (location != null) {
					mLastLocationTime = SystemClock.elapsedRealtime();
					if (mNarrowingChecked && location.getSpeed() < 66.7F
							|| !mNarrowingChecked) {
						float f = location.getSpeed();
						mSpeed = getDisplaySpeed(f);
						if (mTrackLoggingChecked)
							if (mLastTrackLocation != null) {
								if (location.getTime()
										- mLastTrackLocation.getTime() > (long) (1000 * SpeedView.MIN_TIME_VALUES[mMinTimeBetweenPts])
										&& location
												.distanceTo(mLastTrackLocation) > (float) SpeedView.MIN_DISTANCE_VALUES[mMinDistBetweenPts]) {
									mTrackBuffer
											.append(mCoordFormat
													.format(location
															.getLatitude()))
											.append("|")
											.append(mCoordFormat
													.format(location
															.getLongitude()))
											.append("|")
											.append(location.getTime() / 1000L - 1280000000L)
											.append("|")
											.append(location.getSpeed())
											.append("|")
											.append((int) location
													.getAltitude())
											.append("\n");
									if (mTrackBuffer.length() > 2000)
										saveCurrentTrack();
									mLastTrackLocation = location;
								}
							} else {
								mLastTrackLocation = location;
							}
						if (location.getAccuracy() < SpeedView.ACCURACY_VALUES[mMinimumAccuracy]) {
							if (mLastLocation != null
									&& location.getSpeed() != 0.0F) {
								BackgroundService backgroundservice2 = BackgroundService.this;
								backgroundservice2.mStoredDistance = backgroundservice2.mStoredDistance
										+ location.distanceTo(mLastLocation);
							}
							if (f > mStoredMaxSpeed)
								mStoredMaxSpeed = f;
							if (mFirstFixMillis != 0L) {
								BackgroundService backgroundservice;
								if (location.getSpeed() > 0.0F) {
									if (!mVehicleIsMoving) {
										mVehicleIsMoving = true;
										mStateChangedMillis = location
												.getTime();
									}
									mSessionMovingTime = location.getTime()
											- mStateChangedMillis;
								} else if (mVehicleIsMoving) {
									mVehicleIsMoving = false;
									BackgroundService backgroundservice1 = BackgroundService.this;
									backgroundservice1.mStoredMovingTime = backgroundservice1.mStoredMovingTime
											+ (location.getTime() - mStateChangedMillis);
									mSessionMovingTime = 0L;
									mStateChangedMillis = location.getTime();
								}
								mSessionTotalTime = location.getTime()
										- mFirstFixMillis;
							}
							if (mWarningChecked)
								if (mSpeed > mSpeedWarning) {
									if (mSoundAlertToggled
											&& mWarningSound != null
											&& !mWarningSound.getTitle(
													getBaseContext()).equals(
													"Unknown ringtone")
											&& !mWarningSound.isPlaying())
										mWarningSound.play();
									if (mVibrationChecked)
										mVibrator.vibrate(300L);
								} else if (mWarningSound != null
										&& mWarningSound.isPlaying())
									mWarningSound.stop();
						}
						if (mFirstFixMillis == 0L) {
							mFirstFixMillis = location.getTime();
							if (location.getSpeed() > 0.0F)
								mVehicleIsMoving = true;
							else
								mVehicleIsMoving = false;
							mStateChangedMillis = mFirstFixMillis;
						}
						mLastLocation = location;
					}
					showNotification(
							(new StringBuilder(
									String.valueOf(getString(2131099743))))
									.append(": ")
									.append(mSpeed)
									.append(" ")
									.append(SpeedView.UNITS_ARRAY[mDisplayUnits])
									.toString(),
							(new StringBuilder(String
									.valueOf(getString(2131099750))))
									.append(": ").append(distanceToString())
									.toString());
				}
				break;
			}
		}

		private int mSpeed;

		public ServiceHandler(Looper looper) {
			super(looper);
		}
	}

	public BackgroundService() {
		mTrackBuffer = new StringBuilder();
	}

	private String distanceToString() {
		String s = "";
		switch (mDisplayUnits) {
		case 0:
			if (mStoredDistance < 1609344F)
				s = (new StringBuilder(
						String.valueOf((new DecimalFormat("0.00"))
								.format((double) mStoredDistance / 1609.3440000000001D))))
						.append(" mi").toString();
			else
				s = (new DecimalFormat("#,###"))
						.format((double) mStoredDistance / 1609.3440000000001D);
			break;
		case 1:
			if (mStoredDistance < 1000000F)
				s = (new StringBuilder(
						String.valueOf((new DecimalFormat("0.00"))
								.format(mStoredDistance / 1000F)))).append(
						" km").toString();
			else
				s = (new DecimalFormat("#,###"))
						.format(mStoredDistance / 1000F);
			break;
		case 2:
			if (mStoredDistance < 1852000F)
				s = (new StringBuilder(
						String.valueOf((new DecimalFormat("0.00"))
								.format(mStoredDistance / 1852F))))
						.append(" M").toString();
			else
				s = (new DecimalFormat("#,###"))
						.format(mStoredDistance / 1852F);
			break;
		}
		return s;
	}

	private int getDisplaySpeed(float f) {
		int i;
		switch (mDisplayUnits) {
		case 0:
			i = (int) (2.2400000000000002D * (double) f);
			break;
		case 1:
			i = (int) (3.6000000000000001D * (double) f);
			break;
		case 2:
			i = (int) (1.9438445D * (double) f);
			break;
		default:
			i = 0;
			break;
		}
		return i;
	}

	private void handleStart(Intent intent) {
		Message message = mServiceHandler.obtainMessage();
		message.arg1 = 1;
		message.obj = intent.getExtras();
		mServiceHandler.sendMessage(message);
		mLocationManager.requestLocationUpdates("gps", 0L, 0.0F,
				mLocationListener);
		mLocationManager.addGpsStatusListener(mGPSListener);
	}

	private void saveCurrentTrack() {
		if (mTrackBuffer.length() != 0) {
			if (mTrackLogFile != null) {
				RandomAccessFile randomaccessfile;
				try {
					randomaccessfile = new RandomAccessFile(mTrackLogFile,
							"rwd");
					randomaccessfile.seek(randomaccessfile.length());
					randomaccessfile.writeBytes(mTrackBuffer.toString());
					randomaccessfile.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

				mTrackBuffer.setLength(0);
			} else {
				File file;
				String as[];
				int i;
				file = new File((new StringBuilder())
						.append(Environment.getExternalStorageDirectory())
						.append("/speedview/logs").toString());
				if (!file.exists())
					file.mkdirs();
				as = file.list(mLogExtensionFilter);
				Arrays.sort(as);
				i = 0;
				if (i < -3 + as.length) {
					(new File(file, as[i])).delete();
					i++;
				} else {
					mTrackLogFile = new File(file, (new StringBuilder(
							String.valueOf(mDateFormat.format(new Date()))))
							.append(".log").toString());
				}
			}
		}
	}

	@SuppressLint("NewApi")
	private void showNotification(String s, String s1) {
		String s2 = null;
		if (mBuilder == null) {
			if (mNotification == null) {
				if (mTrackLoggingChecked)
					s2 = getString(2131099916);
				mNotification = new Notification(2130837530, s2,
						System.currentTimeMillis());
				mNotification.flags = 34;
			}
			mNotification.setLatestEventInfo(this, s, s1, mPendingIntent);
		} else {
			if (mNotification == null) {
				android.app.Notification.Builder builder = mBuilder
						.setContentIntent(mPendingIntent).setSmallIcon(
								2130837530);
				if (mTrackLoggingChecked)
					s2 = getString(2131099916);
				builder.setTicker(s2).setWhen(System.currentTimeMillis())
						.setContentTitle(s).setContentText(s1)
						.setAutoCancel(false).setOngoing(true);
			} else {
				mBuilder.setContentTitle(s).setContentText(s1);
			}
			mNotification = mBuilder.getNotification();
		}
		mNotificationManager.notify(2131099648, mNotification);
	}

	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	public void onCreate() {
		mLocationManager = (LocationManager) getSystemService("location");
		mLocationListener = new MyLocationListener();
		mGPSListener = new MyGPSListener();
		mCoordFormat = new DecimalFormat("0.000000");
		DecimalFormatSymbols decimalformatsymbols = mCoordFormat
				.getDecimalFormatSymbols();
		decimalformatsymbols.setDecimalSeparator('.');
		mCoordFormat.setDecimalFormatSymbols(decimalformatsymbols);
		mDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		mVibrator = (Vibrator) getSystemService("vibrator");
		mLogExtensionFilter = new FilenameFilter() {

			public boolean accept(File file, String s) {
				return s.toLowerCase().endsWith(".log");
			}
		};
		mNotificationManager = (NotificationManager) getSystemService("notification");
		ComponentName componentname;
		HandlerThread handlerthread;
		try {
			mBuilder = new android.app.Notification.Builder(getBaseContext());
		} catch (NoClassDefFoundError noclassdeffounderror) {
			noclassdeffounderror.printStackTrace();
		}
		mRemoteViews = new RemoteViews(getPackageName(), 2130903051);
		mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
				SpeedView.class), 0);
		handlerthread = new HandlerThread("BackgroundService");
		handlerthread.start();
		mLooper = handlerthread.getLooper();
		mServiceHandler = new ServiceHandler(mLooper);
	}

	public void onDestroy() {
		if (mIsRunning) {
			mLocationManager.removeUpdates(mLocationListener);
			mLocationManager.removeGpsStatusListener(mGPSListener);
			mLooper.quit();
			mNotificationManager.cancel(2131099648);
		}
		mIsRunning = false;
	}

	public void onStart(Intent intent, int i) {
		handleStart(intent);
	}

	public int onStartCommand(Intent intent, int i, int j) {
		handleStart(intent);
		return 2;
	}

	private Uri mAlertSoundUri;
	private final IBinder mBinder = new LocalBinder();
	private android.app.Notification.Builder mBuilder;
	private DecimalFormat mCoordFormat;
	private SimpleDateFormat mDateFormat;
	private int mDisplayUnits;
	private long mFirstFixMillis;
	private MyGPSListener mGPSListener;
	private boolean mIsRunning;
	private Location mLastLocation;
	private long mLastLocationTime;
	private Location mLastTrackLocation;
	private LocationListener mLocationListener;
	private LocationManager mLocationManager;
	private FilenameFilter mLogExtensionFilter;
	private volatile Looper mLooper;
	private int mMinDistBetweenPts;
	private int mMinTimeBetweenPts;
	private int mMinimumAccuracy;
	private boolean mNarrowingChecked;
	private Notification mNotification;
	private NotificationManager mNotificationManager;
	private PendingIntent mPendingIntent;
	private RemoteViews mRemoteViews;
	private volatile ServiceHandler mServiceHandler;
	private long mSessionMovingTime;
	private long mSessionTotalTime;
	private boolean mSoundAlertToggled;
	private int mSpeedWarning;
	private long mStateChangedMillis;
	private float mStoredDistance;
	private float mStoredMaxSpeed;
	private long mStoredMovingTime;
	private long mStoredTotalTime;
	private StringBuilder mTrackBuffer;
	private File mTrackLogFile;
	private boolean mTrackLoggingChecked;
	private boolean mVehicleIsMoving;
	private boolean mVibrationChecked;
	private Vibrator mVibrator;
	private boolean mWarningChecked;
	private Ringtone mWarningSound;
}

/*
 * DECOMPILATION REPORT
 *
 * Decompiled from: H:\AndroidProject\SpeedGps\libs\GPS娴嬮�.jar Total time: 71 ms
 * Jad reported messages/errors: Couldn't fully decompile method
 * onGpsStatusChanged Couldn't fully decompile method handleMessage Couldn't
 * fully decompile method distanceToString Couldn't fully decompile method
 * getDisplaySpeed Couldn't fully decompile method handleStart Couldn't resolve
 * all exception handlers in method handleStart Couldn't fully decompile method
 * saveCurrentTrack Couldn't resolve all exception handlers in method
 * saveCurrentTrack Exit status: 0 Caught exceptions:
 */