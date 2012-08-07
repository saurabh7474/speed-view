package com.codesector.speedview.pro;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.os.Vibrator;
import android.widget.RemoteViews;
import com.codesector.speedview.pro.R;
import java.io.File;
import java.io.FilenameFilter;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

public class BackgroundService extends Service {
	private Uri mAlertSoundUri;
	private int[] mAllWidgetIds;
	private AppWidgetManager mAppWidgetManager;
	private final IBinder mBinder = new LocalBinder();
	private Notification.Builder mBuilder;
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
	private StringBuilder mTrackBuffer = new StringBuilder();
	private File mTrackLogFile;
	private boolean mTrackLoggingChecked;
	private boolean mVehicleIsMoving;
	private boolean mVibrationChecked;
	private Vibrator mVibrator;
	private boolean mWarningChecked;
	private Ringtone mWarningSound;

	private String distanceToString() {
		// TODO
		return "";
	}

	private int getDisplaySpeed(float paramFloat) {
		int i;
		switch (this.mDisplayUnits) {
		default:
			i = 0;
		case 0:
			i = (int) (2.24D * paramFloat);
		case 1:
			i = (int) (3.6D * paramFloat);
		case 2:
			i = (int) (1.9438445D * paramFloat);
		}
		return i;
	}

	private void handleStart(Intent paramIntent) {
		Message localMessage = this.mServiceHandler.obtainMessage();
		localMessage.arg1 = 1;
		localMessage.obj = paramIntent.getExtras();
		this.mServiceHandler.sendMessage(localMessage);
		try {
			this.mLocationManager.requestLocationUpdates("gps", 0L, 0.0F,
					this.mLocationListener);
			this.mLocationManager.addGpsStatusListener(this.mGPSListener);
			return;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	private void saveCurrentTrack() {
		// TODO
	}

	private void showNotification(String paramString1, String paramString2) {
		// TODO
	}

	public IBinder onBind(Intent paramIntent) {
		return this.mBinder;
	}

	public void onCreate() {
		this.mLocationManager = ((LocationManager) getSystemService("location"));
		this.mLocationListener = new MyLocationListener();
		this.mGPSListener = new MyGPSListener();
		this.mCoordFormat = new DecimalFormat("0.000000");
		DecimalFormatSymbols localDecimalFormatSymbols = this.mCoordFormat
				.getDecimalFormatSymbols();
		localDecimalFormatSymbols.setDecimalSeparator('.');
		this.mCoordFormat.setDecimalFormatSymbols(localDecimalFormatSymbols);
		this.mDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.mVibrator = ((Vibrator) getSystemService("vibrator"));
		this.mLogExtensionFilter = new FilenameFilter() {
			public boolean accept(File paramFile, String paramString) {
				return paramString.toLowerCase().endsWith(".log");
			}
		};
		this.mNotificationManager = ((NotificationManager) getSystemService("notification"));
		try {
			this.mBuilder = new Notification.Builder(getBaseContext());
			this.mAppWidgetManager = AppWidgetManager.getInstance(this);
			ComponentName localComponentName = new ComponentName(this,
					WidgetProvider.class);
			this.mAllWidgetIds = this.mAppWidgetManager
					.getAppWidgetIds(localComponentName);
			this.mRemoteViews = new RemoteViews(getPackageName(), 2130903051);
			this.mPendingIntent = PendingIntent.getActivity(this, 0,
					new Intent(this, SpeedView.class), 0);
			HandlerThread localHandlerThread = new HandlerThread(
					"BackgroundService");
			localHandlerThread.start();
			this.mLooper = localHandlerThread.getLooper();
			this.mServiceHandler = new ServiceHandler(this.mLooper);
			return;
		} catch (NoClassDefFoundError localNoClassDefFoundError) {
			localNoClassDefFoundError.printStackTrace();
		}
	}

	public void onDestroy() {
		if (this.mIsRunning) {
			this.mLocationManager.removeUpdates(this.mLocationListener);
			this.mLocationManager.removeGpsStatusListener(this.mGPSListener);
			this.mLooper.quit();
			this.mNotificationManager.cancel(2131099648);
			this.mAllWidgetIds = this.mAppWidgetManager
					.getAppWidgetIds(new ComponentName(this,
							WidgetProvider.class));
			if (this.mAllWidgetIds.length > 0) {
				this.mRemoteViews.setTextViewText(2131296657,
						getString(2131099911));
				this.mRemoteViews.setTextViewText(2131296658,
						getString(2131099912).toUpperCase());
				this.mRemoteViews.setViewVisibility(2131296658, 0);
				this.mRemoteViews.setViewVisibility(2131296656, 0);
				this.mAppWidgetManager.updateAppWidget(this.mAllWidgetIds[0],
						this.mRemoteViews);
			}
		}
		this.mIsRunning = false;
	}

	public void onStart(Intent paramIntent, int paramInt) {
		handleStart(paramIntent);
	}

	public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2) {
		handleStart(paramIntent);
		return 2;
	}

	public class LocalBinder extends Binder {
		public LocalBinder() {
		}

		void dumpCurrentTrack() {
			BackgroundService.this.saveCurrentTrack();
		}

		BackgroundService getService() {
			return BackgroundService.this;
		}

		float getStoredDistance() {
			return BackgroundService.this.mStoredDistance;
		}

		float getStoredMaxSpeed() {
			return BackgroundService.this.mStoredMaxSpeed;
		}

		long getStoredMovingTime() {
			return BackgroundService.this.mStoredMovingTime
					+ BackgroundService.this.mSessionMovingTime;
		}

		long getStoredTotalTime() {
			return BackgroundService.this.mStoredTotalTime
					+ BackgroundService.this.mSessionTotalTime;
		}
	}

	private class MyGPSListener implements GpsStatus.Listener {

		public void onGpsStatusChanged(int event) {

		}
		// TODO
	}

	private class MyLocationListener implements LocationListener {
		private MyLocationListener() {
		}

		public void onLocationChanged(Location paramLocation) {
			if (paramLocation != null) {
				Message localMessage = BackgroundService.this.mServiceHandler
						.obtainMessage();
				localMessage.arg1 = 2;
				localMessage.obj = paramLocation;
				BackgroundService.this.mServiceHandler
						.sendMessage(localMessage);
			}
		}

		public void onProviderDisabled(String paramString) {
			BackgroundService.this.showNotification(
					BackgroundService.this.getString(2131099709),
					BackgroundService.this.getString(2131099710));
		}

		public void onProviderEnabled(String paramString) {
			BackgroundService.this.showNotification(
					BackgroundService.this.getString(2131099651),
					BackgroundService.this.getString(2131099652));
		}

		public void onStatusChanged(String paramString, int paramInt,
				Bundle paramBundle) {
		}
	}

	private final class ServiceHandler extends Handler {
		// TODO
		public ServiceHandler(Looper looper) {
			super(looper);
		}
	}
}