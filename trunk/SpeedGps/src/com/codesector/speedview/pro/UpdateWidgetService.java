/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.codesector.speedview.pro;

import android.app.*;
import android.appwidget.AppWidgetManager;
import android.content.*;
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
//            SpeedView

public class UpdateWidgetService extends Service
{
    public class LocalBinder extends Binder
    {

        void dumpCurrentTrack()
        {
            saveCurrentTrack();
        }

        UpdateWidgetService getService()
        {
            return UpdateWidgetService.this;
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

        final UpdateWidgetService this$0;

        public LocalBinder()
        {
            this$0 = UpdateWidgetService.this;
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
            Iterator iterator;
            int j;
            LocationManager locationmanager = mLocationManager;
            GpsStatus gpsstatus;
            RemoteViews remoteviews;
            StringBuilder stringbuilder;
            if(gpsStatus != null)
                gpsstatus = gpsStatus;
            else
                gpsstatus = null;
            gpsStatus = locationmanager.getGpsStatus(gpsstatus);
            iterator = gpsStatus.getSatellites().iterator();
            j = 0;
_L5:
            if(iterator.hasNext()) goto _L4; else goto _L3
_L3:
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
                String s;
                if(j < 4)
                    mRemoteViews.setTextViewText(2131296657, getString(2131099651));
                else
                    mRemoteViews.setTextViewText(2131296657, getString(2131099653));
                remoteviews = mRemoteViews;
                stringbuilder = (new StringBuilder(String.valueOf(j))).append(" ");
                if(j != 1)
                    s = getString(2131099919).toUpperCase();
                else
                    s = getString(2131099918).toUpperCase();
                remoteviews.setTextViewText(2131296658, stringbuilder.append(s).toString());
                mRemoteViews.setViewVisibility(2131296656, 0);
                mAppWidgetManager.updateAppWidget(mAppWidgetId, mRemoteViews);
            }
              goto _L1
_L4:
            iterator.next();
            j++;
              goto _L5
        }

        private GpsStatus gpsStatus;
        private boolean hasGPSFix;
        final UpdateWidgetService this$0;

        private MyGPSListener()
        {
            this$0 = UpdateWidgetService.this;
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
            mRemoteViews.setTextViewText(2131296657, getString(2131099709));
            mRemoteViews.setTextViewText(2131296658, getString(2131099924).toUpperCase());
            mRemoteViews.setViewVisibility(2131296658, 0);
            mRemoteViews.setViewVisibility(2131296656, 0);
            mAppWidgetManager.updateAppWidget(mAppWidgetId, mRemoteViews);
        }

        public void onProviderEnabled(String s)
        {
            mRemoteViews.setTextViewText(2131296657, getString(2131099651));
            mRemoteViews.setTextViewText(2131296658, getString(2131099917).toUpperCase());
            mAppWidgetManager.updateAppWidget(mAppWidgetId, mRemoteViews);
        }

        public void onStatusChanged(String s, int i, Bundle bundle)
        {
        }

        final UpdateWidgetService this$0;

        private MyLocationListener()
        {
            this$0 = UpdateWidgetService.this;
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
        //                       2 292;
               goto _L1 _L2 _L3
_L1:
            return;
_L2:
            if(mTrackLoggingChecked)
            {
                mRemoteViews.setTextViewText(2131296657, getString(2131099915));
                mRemoteViews.setTextViewText(2131296658, getString(2131099916).toUpperCase());
            } else
            {
                mRemoteViews.setTextViewText(2131296657, getString(2131099913));
                mRemoteViews.setTextViewText(2131296658, getString(2131099914).toUpperCase());
            }
            mRemoteViews.setViewVisibility(2131296658, 0);
            if(mCustomColorsChecked)
            {
                mRemoteViews.setTextColor(2131296660, mPrimaryTextColor);
                mRemoteViews.setTextColor(2131296661, mSecondaryTextColor);
                mRemoteViews.setTextColor(2131296662, mSecondaryTextColor);
            } else
            {
                mRemoteViews.setTextColor(2131296660, -1);
                mRemoteViews.setTextColor(2131296661, -3355444);
                mRemoteViews.setTextColor(2131296662, -3355444);
            }
            mAppWidgetManager.updateAppWidget(mAppWidgetId, mRemoteViews);
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
                            UpdateWidgetService updatewidgetservice2 = UpdateWidgetService.this;
                            updatewidgetservice2.mStoredDistance = updatewidgetservice2.mStoredDistance + location.distanceTo(mLastLocation);
                        }
                        if(f > mStoredMaxSpeed)
                            mStoredMaxSpeed = f;
                        if(mFirstFixMillis != 0L)
                        {
                            UpdateWidgetService updatewidgetservice;
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
                                UpdateWidgetService updatewidgetservice1 = UpdateWidgetService.this;
                                updatewidgetservice1.mStoredMovingTime = updatewidgetservice1.mStoredMovingTime + (location.getTime() - mStateChangedMillis);
                                mSessionMovingTime = 0L;
                                mStateChangedMillis = location.getTime();
                            }
                            mSessionTotalTime = location.getTime() - mFirstFixMillis;
                        }
                        if(mWarningChecked)
                        {
                            if(mSpeed > mSpeedWarning)
                            {
                                if(mSoundAlertToggled && mWarningSound != null && !mWarningSound.getTitle(getBaseContext()).equals("Unknown ringtone") && !mWarningSound.isPlaying())
                                    mWarningSound.play();
                                if(mVibrationChecked)
                                    mVibrator.vibrate(300L);
                            } else
                            if(mWarningSound != null && mWarningSound.isPlaying())
                                mWarningSound.stop();
                            if(mSpeed > mSpeedWarning)
                            {
                                mRemoteViews.setTextColor(2131296660, -65536);
                                mRemoteViews.setTextColor(2131296662, -65536);
                            } else
                            if(mCustomColorsChecked)
                            {
                                mRemoteViews.setTextColor(2131296660, mPrimaryTextColor);
                                mRemoteViews.setTextColor(2131296662, mSecondaryTextColor);
                            } else
                            {
                                mRemoteViews.setTextColor(2131296660, -1);
                                mRemoteViews.setTextColor(2131296662, -3355444);
                            }
                        }
                    }
                    if(mFirstFixMillis == 0L)
                    {
                        mFirstFixMillis = location.getTime();
                        updatewidgetservice = UpdateWidgetService.this;
                        boolean flag;
                        if(location.getSpeed() > 0.0F)
                            flag = true;
                        else
                            flag = false;
                        updatewidgetservice.mVehicleIsMoving = flag;
                        mStateChangedMillis = mFirstFixMillis;
                    }
                    mLastLocation = location;
                }
                mRemoteViews.setViewVisibility(2131296656, 8);
                mRemoteViews.setTextViewText(2131296660, (new StringBuilder(String.valueOf(mSpeed))).append(" ").append(SpeedView.UNITS_ARRAY[mDisplayUnits]).toString());
                mRemoteViews.setTextViewText(2131296661, (new StringBuilder()).append(distanceToString()).toString());
                mRemoteViews.setTextViewText(2131296662, (new StringBuilder()).append(getDisplaySpeed(mStoredMaxSpeed)).toString());
                mAppWidgetManager.updateAppWidget(mAppWidgetId, mRemoteViews);
                mHasBeenUpdated = true;
            }
            if(true) goto _L1; else goto _L4
_L4:
        }

        private int mSpeed;
        final UpdateWidgetService this$0;

        public ServiceHandler(Looper looper)
        {
            this$0 = UpdateWidgetService.this;
            super(looper);
        }
    }


    public UpdateWidgetService()
    {
        mTrackBuffer = new StringBuilder();
    }

    private String distanceToString()
    {
        mDisplayUnits;
        JVM INSTR tableswitch 0 2: default 32
    //                   0 37
    //                   1 118
    //                   2 200;
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
        mAppWidgetManager = AppWidgetManager.getInstance(getBaseContext());
        mRemoteViews = new RemoteViews(getPackageName(), 2130903051);
        if(intent != null && "android.appwidget.action.APPWIDGET_UPDATE".equals(intent.getAction()))
        {
            mIsGPSEnabled = mLocationManager.isProviderEnabled("gps");
            mAppWidgetId = intent.getExtras().getInt("appWidgetId");
            if(mIsServiceActive)
            {
                mRemoteViews.setTextViewText(2131296657, getString(2131099923));
                mRemoteViews.setViewVisibility(2131296658, 8);
                mAppWidgetManager.updateAppWidget(mAppWidgetId, mRemoteViews);
                mHasBeenHandled = true;
                stopSelf();
            } else
            if(!mIsGPSEnabled)
            {
                mRemoteViews.setTextViewText(2131296657, getString(2131099709));
                mRemoteViews.setTextViewText(2131296658, getString(2131099924).toUpperCase());
                mAppWidgetManager.updateAppWidget(mAppWidgetId, mRemoteViews);
                mHasBeenHandled = true;
                stopSelf();
            } else
            if(mIsRunning)
            {
                mRemoteViews.setTextViewText(2131296657, getString(2131099911));
                mRemoteViews.setTextViewText(2131296658, getString(2131099912).toUpperCase());
                mRemoteViews.setViewVisibility(2131296656, 0);
                mAppWidgetManager.updateAppWidget(mAppWidgetId, mRemoteViews);
                if(mHasBeenUpdated)
                {
                    android.content.SharedPreferences.Editor editor = getSharedPreferences("PrefsFile", 0).edit();
                    editor.putFloat("storedDistance", mStoredDistance);
                    editor.putFloat("storedMaxSpeed", mStoredMaxSpeed);
                    editor.putLong("storedMovingTime", mStoredMovingTime + mSessionMovingTime);
                    editor.putLong("storedTotalTime", mStoredTotalTime + mSessionTotalTime);
                    editor.commit();
                    saveCurrentTrack();
                }
                mHasBeenHandled = true;
                stopSelf();
            } else
            {
                Message message = mServiceHandler.obtainMessage();
                message.arg1 = 1;
                message.obj = intent.getExtras();
                mServiceHandler.sendMessage(message);
                try
                {
                    mLocationManager.requestLocationUpdates("gps", 0L, 0.0F, mLocationListener);
                    mLocationManager.addGpsStatusListener(mGPSListener);
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                }
            }
        } else
        {
            mAppWidgetId = getApplicationContext().getSharedPreferences("PrefsFile", 0).getInt("activeWidgetId", 0);
            mRemoteViews.setTextViewText(2131296657, getString(2131099726));
            mRemoteViews.setTextViewText(2131296658, getString(2131099925).toUpperCase());
            Intent intent1 = new Intent("android.intent.action.SEND");
            intent1.setType("plain/text");
            intent1.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
            intent1.setData(Uri.parse("speedview@codesector.com"));
            String as[] = new String[1];
            as[0] = "speedview@codesector.com";
            intent1.putExtra("android.intent.extra.EMAIL", as);
            intent1.putExtra("android.intent.extra.SUBJECT", (new StringBuilder(String.valueOf(getString(2131099648)))).append(" ").append(getString(2131099926)).toString());
            PendingIntent pendingintent = PendingIntent.getActivity(getBaseContext(), 0, intent1, 0);
            mRemoteViews.setOnClickPendingIntent(2131296655, pendingintent);
            mAppWidgetManager.updateAppWidget(mAppWidgetId, mRemoteViews);
            mHasBeenHandled = true;
            stopSelf();
        }
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

    public IBinder onBind(Intent intent)
    {
        return mBinder;
    }

    public void onCreate()
    {
        Iterator iterator = ((ActivityManager)getSystemService("activity")).getRunningServices(2147483647).iterator();
_L11:
        if(iterator.hasNext()) goto _L2; else goto _L1
_L1:
        mLocationManager = (LocationManager)getSystemService("location");
        mIsGPSEnabled = mLocationManager.isProviderEnabled("gps");
        if(mIsServiceActive || mIsRunning || !mIsGPSEnabled) goto _L4; else goto _L3
_L3:
        SharedPreferences sharedpreferences;
        int i;
        int j;
        int k;
        int l;
        mLocationListener = new MyLocationListener(null);
        mGPSListener = new MyGPSListener(null);
        mCoordFormat = new DecimalFormat("0.000000");
        DecimalFormatSymbols decimalformatsymbols = mCoordFormat.getDecimalFormatSymbols();
        decimalformatsymbols.setDecimalSeparator('.');
        mCoordFormat.setDecimalFormatSymbols(decimalformatsymbols);
        mDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mVibrator = (Vibrator)getSystemService("vibrator");
        mLogExtensionFilter = new FilenameFilter() {

            public boolean accept(File file, String s1)
            {
                return s1.toLowerCase().endsWith(".log");
            }

            final UpdateWidgetService this$0;

            
            {
                this$0 = UpdateWidgetService.this;
                super();
            }
        }
;
        sharedpreferences = getApplicationContext().getSharedPreferences("PrefsFile", 0);
        mStoredDistance = sharedpreferences.getFloat("storedDistance", 0.0F);
        mStoredMaxSpeed = sharedpreferences.getFloat("storedMaxSpeed", 0.0F);
        mStoredMovingTime = sharedpreferences.getLong("storedMovingTime", 0L);
        mStoredTotalTime = sharedpreferences.getLong("storedTotalTime", 0L);
        mDisplayUnits = sharedpreferences.getInt("displayUnits", 0);
        mWarningChecked = sharedpreferences.getBoolean("warningChecked", false);
        i = sharedpreferences.getInt("currentSpeedLimit", 0);
        j = sharedpreferences.getInt("townSpeedLimit", 30);
        k = sharedpreferences.getInt("highwaySpeedLimit", 55);
        l = sharedpreferences.getInt("freewaySpeedLimit", 65);
        if(!mWarningChecked) goto _L6; else goto _L5
_L5:
        i;
        JVM INSTR tableswitch 0 2: default 364
    //                   0 636
    //                   1 645
    //                   2 654;
           goto _L7 _L8 _L9 _L10
_L7:
        break; /* Loop/switch isn't completed */
_L10:
        break MISSING_BLOCK_LABEL_654;
_L6:
        mSoundAlertToggled = sharedpreferences.getBoolean("soundAlertToggled", false);
        String s = sharedpreferences.getString("alertSoundUri", "");
        Uri uri;
        HandlerThread handlerthread;
        if(s.equals(""))
            uri = null;
        else
            uri = Uri.parse(s);
        mAlertSoundUri = uri;
        if(mAlertSoundUri != null)
            mWarningSound = RingtoneManager.getRingtone(getBaseContext(), mAlertSoundUri);
        mVibrationChecked = sharedpreferences.getBoolean("vibrationChecked", false);
        mCustomColorsChecked = sharedpreferences.getBoolean("customColorsChecked", false);
        mPrimaryTextColor = sharedpreferences.getInt("primaryTextColor", -1);
        mSecondaryTextColor = sharedpreferences.getInt("secondaryTextColor", -3355444);
        mTrackLoggingChecked = sharedpreferences.getBoolean("trackLoggingChecked", false);
        mMinTimeBetweenPts = sharedpreferences.getInt("minTimeBetweenPts", 0);
        mMinDistBetweenPts = sharedpreferences.getInt("minDistBetweenPts", 4);
        mNarrowingChecked = sharedpreferences.getBoolean("narrowingChecked", true);
        mMinimumAccuracy = sharedpreferences.getInt("minimumAccuracy", 4);
        handlerthread = new HandlerThread("UpdateWidgetService");
        handlerthread.start();
        mLooper = handlerthread.getLooper();
        mServiceHandler = new ServiceHandler(mLooper);
_L4:
        return;
_L2:
        if("com.codesector.speedview.pro.BackgroundService".equals(((android.app.ActivityManager.RunningServiceInfo)iterator.next()).service.getClassName()))
            mIsServiceActive = true;
          goto _L11
_L8:
        mSpeedWarning = j;
          goto _L6
_L9:
        mSpeedWarning = k;
          goto _L6
        mSpeedWarning = l;
          goto _L6
    }

    public void onDestroy()
    {
        if(mIsRunning)
        {
            mLocationManager.removeUpdates(mLocationListener);
            mLocationManager.removeGpsStatusListener(mGPSListener);
            mLooper.quit();
        }
        if(!mHasBeenHandled)
        {
            mRemoteViews.setTextViewText(2131296657, getString(2131099911));
            mRemoteViews.setTextViewText(2131296658, getString(2131099912).toUpperCase());
            mRemoteViews.setViewVisibility(2131296658, 0);
            mRemoteViews.setViewVisibility(2131296656, 0);
            mAppWidgetManager.updateAppWidget(mAppWidgetId, mRemoteViews);
        }
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
    private int mAppWidgetId;
    private AppWidgetManager mAppWidgetManager;
    private final IBinder mBinder = new LocalBinder();
    private DecimalFormat mCoordFormat;
    private boolean mCustomColorsChecked;
    private SimpleDateFormat mDateFormat;
    private int mDisplayUnits;
    private long mFirstFixMillis;
    private MyGPSListener mGPSListener;
    private boolean mHasBeenHandled;
    private boolean mHasBeenUpdated;
    private boolean mIsGPSEnabled;
    private boolean mIsRunning;
    private boolean mIsServiceActive;
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
    private int mPrimaryTextColor;
    private RemoteViews mRemoteViews;
    private int mSecondaryTextColor;
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
	Total time: 59 ms
	Jad reported messages/errors:
Couldn't fully decompile method onGpsStatusChanged
Couldn't fully decompile method handleMessage
Couldn't fully decompile method distanceToString
Couldn't fully decompile method getDisplaySpeed
Couldn't fully decompile method saveCurrentTrack
Couldn't resolve all exception handlers in method saveCurrentTrack
Couldn't fully decompile method onCreate
	Exit status: 0
	Caught exceptions:
*/