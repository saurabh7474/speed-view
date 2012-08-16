/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.codesector.speedview.pro;

import android.app.*;
import android.appwidget.AppWidgetManager;
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

public class BackgroundService extends Service
{
    public class LocalBinder extends Binder
    {

        void dumpCurrentTrack()
        {
            saveCurrentTrack();
        }

        BackgroundService getService()
        {
            return BackgroundService.this;
        }

        float getStoredDistance()
        {
            return mStoredDistance;
        }

        float getStoredMaxSpeed()
        {
            return mStoredMaxSpeed;
        }

        long getStoredMovingTime()
        {
            return mStoredMovingTime + mSessionMovingTime;
        }

        long getStoredTotalTime()
        {
            return mStoredTotalTime + mSessionTotalTime;
        }

        final BackgroundService this$0;

        public LocalBinder()
        {
            this$0 = BackgroundService.this;
            super();
        }
    }

    private class MyGPSListener
        implements android.location.GpsStatus.Listener
    {

        public void onGpsStatusChanged(int i)
        {
            i;
            JVM INSTR tableswitch 1 4: default 32
        //                       1 32
        //                       2 32
        //                       3 32
        //                       4 33;
               goto _L1 _L1 _L1 _L1 _L2
_L1:
            return;
_L2:
            if(!mIsRunning) goto _L1; else goto _L3
_L3:
            Iterator iterator;
            int j;
            LocationManager locationmanager = mLocationManager;
            GpsStatus gpsstatus;
            BackgroundService backgroundservice;
            StringBuilder stringbuilder;
            if(gpsStatus != null)
                gpsstatus = gpsStatus;
            else
                gpsstatus = null;
            gpsStatus = locationmanager.getGpsStatus(gpsstatus);
            iterator = gpsStatus.getSatellites().iterator();
            j = 0;
_L6:
            if(iterator.hasNext()) goto _L5; else goto _L4
_L4:
            if(mLastLocation != null)
            {
                boolean flag;
                if(SystemClock.elapsedRealtime() - mLastLocationTime < 10000L)
                    flag = true;
                else
                    flag = false;
                hasGPSFix = flag;
            }
            if(!hasGPSFix)
            {
                backgroundservice = BackgroundService.this;
                String s;
                String s1;
                if(j < 4)
                    s = getString(2131099651);
                else
                    s = getString(2131099653);
                stringbuilder = (new StringBuilder(String.valueOf(j))).append(" ");
                if(j != 1)
                    s1 = getString(2131099654);
                else
                    s1 = getString(2131099655);
                backgroundservice.showNotification(s, stringbuilder.append(s1).toString());
            }
              goto _L1
_L5:
            iterator.next();
            j++;
              goto _L6
        }

        private GpsStatus gpsStatus;
        private boolean hasGPSFix;
        final BackgroundService this$0;

        private MyGPSListener()
        {
            this$0 = BackgroundService.this;
            super();
        }

        MyGPSListener(MyGPSListener mygpslistener)
        {
            this();
        }
    }

    private class MyLocationListener
        implements LocationListener
    {

        public void onLocationChanged(Location location)
        {
            if(location != null)
            {
                Message message = mServiceHandler.obtainMessage();
                message.arg1 = 2;
                message.obj = location;
                mServiceHandler.sendMessage(message);
            }
        }

        public void onProviderDisabled(String s)
        {
            showNotification(getString(2131099709), getString(2131099710));
        }

        public void onProviderEnabled(String s)
        {
            showNotification(getString(2131099651), getString(2131099652));
        }

        public void onStatusChanged(String s, int i, Bundle bundle)
        {
        }

        final BackgroundService this$0;

        private MyLocationListener()
        {
            this$0 = BackgroundService.this;
            super();
        }

        MyLocationListener(MyLocationListener mylocationlistener)
        {
            this();
        }
    }

    private final class ServiceHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message.arg1;
            JVM INSTR tableswitch 1 2: default 28
        //                       1 29
        //                       2 429;
               goto _L1 _L2 _L3
_L1:
            return;
_L2:
            Bundle bundle = (Bundle)message.obj;
            mStoredDistance = bundle.getFloat("distance");
            mStoredMaxSpeed = bundle.getFloat("max_speed");
            mStoredMovingTime = bundle.getLong("moving_time");
            mStoredTotalTime = bundle.getLong("total_time");
            mDisplayUnits = bundle.getInt("display_units");
            mWarningChecked = bundle.getBoolean("warning_checked");
            mSpeedWarning = bundle.getInt("speed_warning");
            mSoundAlertToggled = bundle.getBoolean("sould_alert_toggled");
            String s = bundle.getString("alert_sound_uri");
            if(s != null)
            {
                BackgroundService backgroundservice3 = BackgroundService.this;
                Uri uri;
                if(s.equals(""))
                    uri = null;
                else
                    uri = Uri.parse(s);
                backgroundservice3.mAlertSoundUri = uri;
                mWarningSound = RingtoneManager.getRingtone(getBaseContext(), mAlertSoundUri);
            }
            mVibrationChecked = bundle.getBoolean("vibration_checked");
            mTrackLoggingChecked = bundle.getBoolean("track_logging_checked");
            mMinTimeBetweenPts = bundle.getInt("min_time_between_pts");
            mMinDistBetweenPts = bundle.getInt("min_dist_between_pts");
            mNarrowingChecked = bundle.getBoolean("narrowing_checked");
            mMinimumAccuracy = bundle.getInt("minimum_accuracy");
            showNotification(getString(2131099651), getString(2131099652));
            if(mAllWidgetIds.length > 0)
            {
                mRemoteViews.setTextViewText(2131296657, getString(2131099923));
                mRemoteViews.setViewVisibility(2131296658, 8);
                mRemoteViews.setViewVisibility(2131296656, 0);
                mAppWidgetManager.updateAppWidget(mAllWidgetIds[0], mRemoteViews);
            }
            mIsRunning = true;
            continue; /* Loop/switch isn't completed */
_L3:
            Location location = (Location)message.obj;
            if(location != null)
            {
                mLastLocationTime = SystemClock.elapsedRealtime();
                if(mNarrowingChecked && location.getSpeed() < 66.7F || !mNarrowingChecked)
                {
                    float f = location.getSpeed();
                    mSpeed = getDisplaySpeed(f);
                    if(mTrackLoggingChecked)
                        if(mLastTrackLocation != null)
                        {
                            if(location.getTime() - mLastTrackLocation.getTime() > (long)(1000 * SpeedView.MIN_TIME_VALUES[mMinTimeBetweenPts]) && location.distanceTo(mLastTrackLocation) > (float)SpeedView.MIN_DISTANCE_VALUES[mMinDistBetweenPts])
                            {
                                mTrackBuffer.append(mCoordFormat.format(location.getLatitude())).append("|").append(mCoordFormat.format(location.getLongitude())).append("|").append(location.getTime() / 1000L - 1280000000L).append("|").append(location.getSpeed()).append("|").append((int)location.getAltitude()).append("\n");
                                if(mTrackBuffer.length() > 2000)
                                    saveCurrentTrack();
                                mLastTrackLocation = location;
                            }
                        } else
                        {
                            mLastTrackLocation = location;
                        }
                    if(location.getAccuracy() < SpeedView.ACCURACY_VALUES[mMinimumAccuracy])
                    {
                        if(mLastLocation != null && location.getSpeed() != 0.0F)
                        {
                            BackgroundService backgroundservice2 = BackgroundService.this;
                            backgroundservice2.mStoredDistance = backgroundservice2.mStoredDistance + location.distanceTo(mLastLocation);
                        }
                        if(f > mStoredMaxSpeed)
                            mStoredMaxSpeed = f;
                        if(mFirstFixMillis != 0L)
                        {
                            BackgroundService backgroundservice;
                            if(location.getSpeed() > 0.0F)
                            {
                                if(!mVehicleIsMoving)
                                {
                                    mVehicleIsMoving = true;
                                    mStateChangedMillis = location.getTime();
                                }
                                mSessionMovingTime = location.getTime() - mStateChangedMillis;
                            } else
                            if(mVehicleIsMoving)
                            {
                                mVehicleIsMoving = false;
                                BackgroundService backgroundservice1 = BackgroundService.this;
                                backgroundservice1.mStoredMovingTime = backgroundservice1.mStoredMovingTime + (location.getTime() - mStateChangedMillis);
                                mSessionMovingTime = 0L;
                                mStateChangedMillis = location.getTime();
                            }
                            mSessionTotalTime = location.getTime() - mFirstFixMillis;
                        }
                        if(mWarningChecked)
                            if(mSpeed > mSpeedWarning)
                            {
                                if(mSoundAlertToggled && mWarningSound != null && !mWarningSound.getTitle(getBaseContext()).equals("Unknown ringtone") && !mWarningSound.isPlaying())
                                    mWarningSound.play();
                                if(mVibrationChecked)
                                    mVibrator.vibrate(300L);
                            } else
                            if(mWarningSound != null && mWarningSound.isPlaying())
                                mWarningSound.stop();
                    }
                    if(mFirstFixMillis == 0L)
                    {
                        mFirstFixMillis = location.getTime();
                        backgroundservice = BackgroundService.this;
                        boolean flag;
                        if(location.getSpeed() > 0.0F)
                            flag = true;
                        else
                            flag = false;
                        backgroundservice.mVehicleIsMoving = flag;
                        mStateChangedMillis = mFirstFixMillis;
                    }
                    mLastLocation = location;
                }
                showNotification((new StringBuilder(String.valueOf(getString(2131099743)))).append(": ").append(mSpeed).append(" ").append(SpeedView.UNITS_ARRAY[mDisplayUnits]).toString(), (new StringBuilder(String.valueOf(getString(2131099750)))).append(": ").append(distanceToString()).toString());
            }
            if(true) goto _L1; else goto _L4
_L4:
        }

        private int mSpeed;
        final BackgroundService this$0;

        public ServiceHandler(Looper looper)
        {
            this$0 = BackgroundService.this;
            super(looper);
        }
    }


    public BackgroundService()
    {
        mTrackBuffer = new StringBuilder();
    }

    private String distanceToString()
    {
        mDisplayUnits;
        JVM INSTR tableswitch 0 2: default 32
    //                   0 38
    //                   1 120
    //                   2 202;
           goto _L1 _L2 _L3 _L4
_L1:
        String s = "";
_L6:
        return s;
_L2:
        if(mStoredDistance < 1609344F)
            s = (new StringBuilder(String.valueOf((new DecimalFormat("0.00")).format((double)mStoredDistance / 1609.3440000000001D)))).append(" mi").toString();
        else
            s = (new DecimalFormat("#,###")).format((double)mStoredDistance / 1609.3440000000001D);
        continue; /* Loop/switch isn't completed */
_L3:
        if(mStoredDistance < 1000000F)
            s = (new StringBuilder(String.valueOf((new DecimalFormat("0.00")).format(mStoredDistance / 1000F)))).append(" km").toString();
        else
            s = (new DecimalFormat("#,###")).format(mStoredDistance / 1000F);
        continue; /* Loop/switch isn't completed */
_L4:
        if(mStoredDistance < 1852000F)
            s = (new StringBuilder(String.valueOf((new DecimalFormat("0.00")).format(mStoredDistance / 1852F)))).append(" M").toString();
        else
            s = (new DecimalFormat("#,###")).format(mStoredDistance / 1852F);
        if(true) goto _L6; else goto _L5
_L5:
    }

    private int getDisplaySpeed(float f)
    {
        mDisplayUnits;
        JVM INSTR tableswitch 0 2: default 32
    //                   0 36
    //                   1 47
    //                   2 58;
           goto _L1 _L2 _L3 _L4
_L1:
        int i = 0;
_L6:
        return i;
_L2:
        i = (int)(2.2400000000000002D * (double)f);
        continue; /* Loop/switch isn't completed */
_L3:
        i = (int)(3.6000000000000001D * (double)f);
        continue; /* Loop/switch isn't completed */
_L4:
        i = (int)(1.9438445D * (double)f);
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void handleStart(Intent intent)
    {
        Message message = mServiceHandler.obtainMessage();
        message.arg1 = 1;
        message.obj = intent.getExtras();
        mServiceHandler.sendMessage(message);
        mLocationManager.requestLocationUpdates("gps", 0L, 0.0F, mLocationListener);
        mLocationManager.addGpsStatusListener(mGPSListener);
_L1:
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L1
    }

    private void saveCurrentTrack()
    {
        if(mTrackBuffer.length() != 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(mTrackLogFile != null) goto _L4; else goto _L3
_L3:
        File file;
        String as[];
        int i;
        file = new File((new StringBuilder()).append(Environment.getExternalStorageDirectory()).append("/speedview/logs").toString());
        if(!file.exists())
            file.mkdirs();
        as = file.list(mLogExtensionFilter);
        Arrays.sort(as);
        i = 0;
_L7:
        if(i < -3 + as.length) goto _L6; else goto _L5
_L5:
        mTrackLogFile = new File(file, (new StringBuilder(String.valueOf(mDateFormat.format(new Date())))).append(".log").toString());
_L4:
        RandomAccessFile randomaccessfile = new RandomAccessFile(mTrackLogFile, "rwd");
        randomaccessfile.seek(randomaccessfile.length());
        randomaccessfile.writeBytes(mTrackBuffer.toString());
        randomaccessfile.close();
        mTrackBuffer.setLength(0);
          goto _L1
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L1
_L6:
        (new File(file, as[i])).delete();
        i++;
          goto _L7
    }

    private void showNotification(String s, String s1)
    {
        String s2 = null;
        if(mBuilder == null)
        {
            if(mNotification == null)
            {
                if(mTrackLoggingChecked)
                    s2 = getString(2131099916);
                mNotification = new Notification(2130837530, s2, System.currentTimeMillis());
                mNotification.flags = 34;
            }
            mNotification.setLatestEventInfo(this, s, s1, mPendingIntent);
        } else
        {
            if(mNotification == null)
            {
                android.app.Notification.Builder builder = mBuilder.setContentIntent(mPendingIntent).setSmallIcon(2130837530);
                if(mTrackLoggingChecked)
                    s2 = getString(2131099916);
                builder.setTicker(s2).setWhen(System.currentTimeMillis()).setContentTitle(s).setContentText(s1).setAutoCancel(false).setOngoing(true);
            } else
            {
                mBuilder.setContentTitle(s).setContentText(s1);
            }
            mNotification = mBuilder.getNotification();
        }
        mNotificationManager.notify(2131099648, mNotification);
    }

    public IBinder onBind(Intent intent)
    {
        return mBinder;
    }

    public void onCreate()
    {
        mLocationManager = (LocationManager)getSystemService("location");
        mLocationListener = new MyLocationListener(null);
        mGPSListener = new MyGPSListener(null);
        mCoordFormat = new DecimalFormat("0.000000");
        DecimalFormatSymbols decimalformatsymbols = mCoordFormat.getDecimalFormatSymbols();
        decimalformatsymbols.setDecimalSeparator('.');
        mCoordFormat.setDecimalFormatSymbols(decimalformatsymbols);
        mDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mVibrator = (Vibrator)getSystemService("vibrator");
        mLogExtensionFilter = new FilenameFilter() {

            public boolean accept(File file, String s)
            {
                return s.toLowerCase().endsWith(".log");
            }

            final BackgroundService this$0;

            
            {
                this$0 = BackgroundService.this;
                super();
            }
        }
;
        mNotificationManager = (NotificationManager)getSystemService("notification");
        ComponentName componentname;
        HandlerThread handlerthread;
        try
        {
            mBuilder = new android.app.Notification.Builder(getBaseContext());
        }
        catch(NoClassDefFoundError noclassdeffounderror)
        {
            noclassdeffounderror.printStackTrace();
        }
        mAppWidgetManager = AppWidgetManager.getInstance(this);
        componentname = new ComponentName(this, com/codesector/speedview/pro/WidgetProvider);
        mAllWidgetIds = mAppWidgetManager.getAppWidgetIds(componentname);
        mRemoteViews = new RemoteViews(getPackageName(), 2130903051);
        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, com/codesector/speedview/pro/SpeedView), 0);
        handlerthread = new HandlerThread("BackgroundService");
        handlerthread.start();
        mLooper = handlerthread.getLooper();
        mServiceHandler = new ServiceHandler(mLooper);
    }

    public void onDestroy()
    {
        if(mIsRunning)
        {
            mLocationManager.removeUpdates(mLocationListener);
            mLocationManager.removeGpsStatusListener(mGPSListener);
            mLooper.quit();
            mNotificationManager.cancel(2131099648);
            mAllWidgetIds = mAppWidgetManager.getAppWidgetIds(new ComponentName(this, com/codesector/speedview/pro/WidgetProvider));
            if(mAllWidgetIds.length > 0)
            {
                mRemoteViews.setTextViewText(2131296657, getString(2131099911));
                mRemoteViews.setTextViewText(2131296658, getString(2131099912).toUpperCase());
                mRemoteViews.setViewVisibility(2131296658, 0);
                mRemoteViews.setViewVisibility(2131296656, 0);
                mAppWidgetManager.updateAppWidget(mAllWidgetIds[0], mRemoteViews);
            }
        }
        mIsRunning = false;
    }

    public void onStart(Intent intent, int i)
    {
        handleStart(intent);
    }

    public int onStartCommand(Intent intent, int i, int j)
    {
        handleStart(intent);
        return 2;
    }

    private Uri mAlertSoundUri;
    private int mAllWidgetIds[];
    private AppWidgetManager mAppWidgetManager;
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
	DECOMPILATION REPORT

	Decompiled from: H:\AndroidProject\SpeedGps\libs\GPS测速.jar
	Total time: 71 ms
	Jad reported messages/errors:
Couldn't fully decompile method onGpsStatusChanged
Couldn't fully decompile method handleMessage
Couldn't fully decompile method distanceToString
Couldn't fully decompile method getDisplaySpeed
Couldn't fully decompile method handleStart
Couldn't resolve all exception handlers in method handleStart
Couldn't fully decompile method saveCurrentTrack
Couldn't resolve all exception handlers in method saveCurrentTrack
	Exit status: 0
	Caught exceptions:
*/