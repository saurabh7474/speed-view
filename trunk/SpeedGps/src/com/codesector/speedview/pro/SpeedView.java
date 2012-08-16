/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.codesector.speedview.pro;

import android.app.*;
import android.content.*;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.*;
import android.location.*;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.*;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.*;
import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;
import java.io.*;
import java.text.*;
import java.util.*;

// Referenced classes of package com.codesector.speedview.pro:
//            GraphView, SatelliteView, CompassView, SpeedometerView, 
//            CompassMode, HudMode, TabletHelper, ShareActivity, 
//            SettingsActivity, FeaturedActivity, BackgroundService, UpdateWidgetService, 
//            DisplayHelper

public class SpeedView extends Activity
{
    private class MyGPSListener
        implements android.location.GpsStatus.Listener
    {

        public void onGpsStatusChanged(int i)
        {
            mSecondsElapsed = (int)((System.nanoTime() - mSessionStartTime) / 1000000000L);
            i;
            JVM INSTR tableswitch 1 4: default 52
        //                       1 52
        //                       2 52
        //                       3 1280
        //                       4 53;
               goto _L1 _L1 _L1 _L2 _L3
_L1:
            return;
_L3:
            int j;
            int k;
            LocationManager locationmanager = mLocationManager;
            GpsStatus gpsstatus;
            Iterator iterator;
            if(gpsStatus != null)
                gpsstatus = gpsStatus;
            else
                gpsstatus = null;
            gpsStatus = locationmanager.getGpsStatus(gpsstatus);
            mSatelliteView.setSatellites(gpsStatus);
            iterator = gpsStatus.getSatellites().iterator();
            j = 0;
            k = 0;
            do
            {
                if(!iterator.hasNext())
                {
                    if(mLastLocation != null)
                    {
                        GpsSatellite gpssatellite;
                        TextView textview4;
                        StringBuilder stringbuilder3;
                        SpeedView speedview;
                        boolean flag;
                        if(SystemClock.elapsedRealtime() - mLastLocationTime < 10000L)
                            flag = true;
                        else
                            flag = false;
                        SpeedView.mHasGPSFix = flag;
                    }
                    if(!SpeedView.mHasGPSFix)
                        break;
                    if(SpeedView.mSelectedDashboard == 1)
                        if(SpeedView.mStreetAddrChecked && SpeedView.mHasNetworkAccess)
                        {
                            textview4 = mNumberOfSats;
                            stringbuilder3 = (new StringBuilder(String.valueOf(k))).append(" ");
                            speedview = SpeedView.this;
                            int l;
                            if(j != 1)
                                l = 2131099739;
                            else
                                l = 2131099740;
                            textview4.setText(stringbuilder3.append(speedview.getString(l)).toString());
                        } else
                        {
                            TextView textview3 = mAddressLine1;
                            StringBuilder stringbuilder2 = (new StringBuilder(String.valueOf(j))).append(" ");
                            String s3;
                            if(j != 1)
                                s3 = getString(2131099654);
                            else
                                s3 = getString(2131099655);
                            textview3.setText(stringbuilder2.append(s3).toString());
                            mNumberOfSats.setText((new StringBuilder(String.valueOf(k))).append(" ").append(getString(2131099741)).toString());
                        }
                    if(SpeedView.mIsTablet && mActionBar != null && mTabletHelper.getNavItemCount(mActionBar) == 5)
                        mTabletHelper.removeStartupTab(mActionBar);
                    continue; /* Loop/switch isn't completed */
                }
                gpssatellite = (GpsSatellite)iterator.next();
                j++;
                if(gpssatellite.usedInFix())
                    k++;
            } while(true);
            if(SpeedView.mSelectedDashboard != 0) goto _L5; else goto _L4
_L4:
            String s;
            TextView textview;
            StringBuilder stringbuilder;
            TextView textview1;
            StringBuilder stringbuilder1;
            String s1;
            if(j < 4)
                mStatusMessage.setText(2131099651);
            else
                mStatusMessage.setText(2131099653);
            textview1 = mNumOfSatellites;
            stringbuilder1 = (new StringBuilder(String.valueOf(j))).append(" ");
            if(j != 1)
                s1 = getString(2131099654);
            else
                s1 = getString(2131099655);
            textview1.setText(stringbuilder1.append(s1).toString());
            if(j < 4)
            {
                if(mSecondsElapsed < 60)
                {
                    mTipMessage.setText(2131099656);
                } else
                {
                    TextView textview2 = mTipMessage;
                    String s2;
                    if(j != 0)
                        s2 = getString(2131099658);
                    else
                        s2 = getString(2131099659);
                    textview2.setText((new StringBuilder(String.valueOf(s2))).append(" ").append(getString(2131099660)).toString());
                }
            } else
            {
                mTipMessage.setText(2131099657);
            }
_L10:
            if(j < 4)
                mAddressLine0.setText(2131099651);
            else
                mAddressLine0.setText(2131099653);
            textview = mAddressLine1;
            stringbuilder = (new StringBuilder(String.valueOf(j))).append(" ");
            if(j != 1)
                s = getString(2131099654);
            else
                s = getString(2131099655);
            textview.setText(stringbuilder.append(s).toString());
            mSignalStrength.setImageResource(2130837517);
            mNumberOfSats.setText((new StringBuilder(String.valueOf(k))).append(" ").append(getString(2131099741)).toString());
            if(mAccuracyNotification.getVisibility() == 0)
            {
                mGraphView.setVisibility(0);
                mAccuracyNotification.setVisibility(8);
            }
            mLastAddress = null;
            continue; /* Loop/switch isn't completed */
_L5:
            SpeedView.mSelectedDashboard;
            JVM INSTR tableswitch 1 3: default 916
        //                       1 973
        //                       2 1087
        //                       3 1229;
               goto _L6 _L7 _L8 _L9
_L6:
            if(SpeedView.mIsTablet && mActionBar != null && mTabletHelper.getNavItemCount(mActionBar) == 4)
                mTabletHelper.addStartupTab(mActionBar);
              goto _L10
_L7:
            if(SpeedView.mStoredOrientation != 0 && mHeading != null)
                mHeading.setText(2131099663);
            mSpeedometerView.onSpeedChanged(-1, mSpeedWarning, mStoredMaxSpeed);
            if(SpeedView.mDigitSpeedoChecked && SpeedView.mDigitAddlDataToggled)
                mSpeedometerView.refreshView();
            if(SpeedView.mCustomColorsChecked)
                mMaxSpeed.setTextColor(SpeedView.mSecondaryTextColor);
            else
                mMaxSpeed.setTextColor(-3355444);
              goto _L6
_L8:
            mCompassSpeed.setText(getBaseContext().getResources().getString(2131099743));
            mCompassOdometer.setText(getBaseContext().getResources().getString(2131099744));
            mCompassElevation.setText(getBaseContext().getResources().getString(2131099745));
            mCompassTime.setText(getBaseContext().getResources().getString(2131099746));
            if(SpeedView.mCustomColorsChecked)
                mCompassSpeed.setTextColor(SpeedView.mSecondaryTextColor);
            else
                mCompassSpeed.setTextColor(-3355444);
              goto _L6
_L9:
            mHudMode.onSpeedChanged(-1, mSpeedWarning);
              goto _L6
_L2:
            SpeedView.mHasGPSFix = true;
            if(true) goto _L1; else goto _L11
_L11:
        }

        private GpsStatus gpsStatus;
        private int mSecondsElapsed;
        final SpeedView this$0;

        private MyGPSListener()
        {
            this$0 = SpeedView.this;
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
            if(location != null) goto _L2; else goto _L1
_L1:
            return;
_L2:
            float f;
            double d;
            double d1;
            float f1;
            double d2;
            float f2;
            long l;
            if(SpeedView.mSelectedDashboard == 0)
            {
                switchToScreen(1);
                if(SpeedView.mIsTablet && mActionBar != null)
                {
                    mTabletHelper.setSelectedNavItem(mActionBar, 1);
                    mTabletHelper.removeStartupTab(mActionBar);
                }
            }
            mLastLocationTime = SystemClock.elapsedRealtime();
            if((!mNarrowingChecked || location.getSpeed() >= 66.7F) && mNarrowingChecked)
                continue; /* Loop/switch isn't completed */
            f = location.getSpeed();
            mSpeed = getDisplaySpeed(f);
            d = location.getLatitude();
            d1 = location.getLongitude();
            f1 = location.getAccuracy();
            mAccuracy = (int)f1;
            d2 = location.getAltitude();
            f2 = location.getBearing();
            l = location.getTime();
            if(SpeedView.mSelectedDashboard == 4 && f == 0.0F)
            {
                mFrom0To60Button.setEnabled(true);
                mFrom0To100Button.setEnabled(true);
                mQuarterMileButton.setEnabled(true);
            } else
            {
                mFrom0To60Button.setEnabled(false);
                mFrom0To100Button.setEnabled(false);
                mQuarterMileButton.setEnabled(false);
            }
            if(mFrom0To60Screen.getVisibility() != 0 && mFrom0To100Screen.getVisibility() != 0 && mQuarterMileScreen.getVisibility() != 0)
                break; /* Loop/switch isn't completed */
            if(mFrom0To60Screen.getVisibility() == 0)
            {
                int k1 = (int)(2.2400000000000002D * (double)f);
                if(mLastLocation.getSpeed() == 0.0F && f > 0.0F && !m60MphReached)
                {
                    mAcclStartLocation = mLastLocation;
                    mFrom0To60Speed.setText((new StringBuilder()).append(k1).toString());
                    mFrom0To60Info.setText(2131099791);
                    mFrom0To60Info.setTextColor(-65536);
                    m60MphReached = false;
                } else
                if(f != 0.0F && f <= 26.8224F && !m60MphReached)
                {
                    long l3 = l - mAcclStartLocation.getTime();
                    int i2 = (int)(l3 % 1000L);
                    int j2 = (int)(l3 / 1000L);
                    mFrom0To60String = (new StringBuilder(String.valueOf(j2))).append(".").append(i2).append(" ").append(getString(2131099789)).toString();
                    mFrom0To60Time.setText(mFrom0To60String);
                    mFrom0To60Feet.setText((new StringBuilder()).append((int)(3.2808F * location.distanceTo(mAcclStartLocation))).append(" ft").toString());
                    mFrom0To60Speed.setText((new StringBuilder()).append(k1).toString());
                    mConfirm0To60Button.setEnabled(false);
                    mDiscard0To60Button.setEnabled(false);
                } else
                if(f > 26.8224F && !m60MphReached)
                {
                    mFrom0To60Speed.setTextColor(-16776961);
                    mFrom0To60Speed.setText((new StringBuilder()).append(k1).toString());
                    mFrom0To60Info.setTextColor(-3355444);
                    mFrom0To60Info.setText((new StringBuilder(String.valueOf(getString(2131099792)))).append(" ").append(mFrom0To60String).toString());
                    m60MphReached = true;
                    mTemp0To60Time = mFrom0To60String;
                    mConfirm0To60Button.setEnabled(true);
                    mDiscard0To60Button.setEnabled(true);
                }
            }
            if(mFrom0To100Screen.getVisibility() == 0)
            {
                int k = (int)(3.6000000000000001D * (double)f);
                if(mLastLocation.getSpeed() == 0.0F && f > 0.0F && !m100KphReached)
                {
                    mAcclStartLocation = mLastLocation;
                    mFrom0To100Speed.setText((new StringBuilder()).append(k).toString());
                    mFrom0To100Info.setText(2131099791);
                    mFrom0To100Info.setTextColor(-65536);
                    m100KphReached = false;
                } else
                if(f != 0.0F && f <= 27.78F && !m100KphReached)
                {
                    long l2 = l - mAcclStartLocation.getTime();
                    int i1 = (int)(l2 % 1000L);
                    int j1 = (int)(l2 / 1000L);
                    mFrom0To100String = (new StringBuilder(String.valueOf(j1))).append(".").append(i1).append(" ").append(getString(2131099789)).toString();
                    mFrom0To100Time.setText(mFrom0To100String);
                    mFrom0To100Meters.setText((new StringBuilder()).append((int)location.distanceTo(mAcclStartLocation)).append(" m").toString());
                    mFrom0To100Speed.setText((new StringBuilder()).append(k).toString());
                    mConfirm0To100Button.setEnabled(false);
                    mDiscard0To100Button.setEnabled(false);
                } else
                if(f > 27.78F && !m100KphReached)
                {
                    mFrom0To100Speed.setTextColor(-16776961);
                    mFrom0To100Speed.setText((new StringBuilder()).append(k).toString());
                    mFrom0To100Info.setTextColor(-3355444);
                    mFrom0To100Info.setText((new StringBuilder(String.valueOf(getString(2131099797)))).append(" ").append(mFrom0To100String).toString());
                    m100KphReached = true;
                    mTemp0To100Time = mFrom0To100String;
                    mConfirm0To100Button.setEnabled(true);
                    mDiscard0To100Button.setEnabled(true);
                }
            }
            if(mQuarterMileScreen.getVisibility() == 0)
                if(mLastLocation.getSpeed() == 0.0F && f > 0.0F && !mQtrMileReached)
                {
                    mAcclStartLocation = mLastLocation;
                    if(SpeedView.mDisplayUnits == 1)
                        mQuarterMileDist.setText((new StringBuilder()).append((int)location.distanceTo(mAcclStartLocation)).toString());
                    else
                        mQuarterMileDist.setText((new StringBuilder()).append((int)(3.2808F * location.distanceTo(mAcclStartLocation))).toString());
                    mQuarterMileInfo.setText(2131099791);
                    mQuarterMileInfo.setTextColor(-65536);
                    mQtrMileReached = false;
                } else
                if(mAcclStartLocation != null && location.distanceTo(mAcclStartLocation) <= 402.336F && !mQtrMileReached)
                {
                    long l1 = l - mAcclStartLocation.getTime();
                    int i = (int)(l1 % 1000L);
                    int j = (int)(l1 / 1000L);
                    mQtrMileString = (new StringBuilder(String.valueOf(j))).append(".").append(i).append(" ").append(getString(2131099789)).toString();
                    mQuarterMileTime.setText(mQtrMileString);
                    mQuarterMileSpeed.setText((new StringBuilder(String.valueOf(mSpeed))).append(" ").append(SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits]).toString());
                    if(SpeedView.mDisplayUnits == 1)
                        mQuarterMileDist.setText((new StringBuilder()).append((int)location.distanceTo(mAcclStartLocation)).toString());
                    else
                        mQuarterMileDist.setText((new StringBuilder()).append((int)(3.2808F * location.distanceTo(mAcclStartLocation))).toString());
                    mConfirmQtrButton.setEnabled(false);
                    mDiscardQtrButton.setEnabled(false);
                } else
                if(mAcclStartLocation != null && location.distanceTo(mAcclStartLocation) > 402.336F && !mQtrMileReached)
                {
                    mQuarterMileDist.setTextColor(-16776961);
                    if(SpeedView.mDisplayUnits == 1)
                        mQuarterMileDist.setText((new StringBuilder()).append((int)location.distanceTo(mAcclStartLocation)).toString());
                    else
                        mQuarterMileDist.setText((new StringBuilder()).append((int)(3.2808F * location.distanceTo(mAcclStartLocation))).toString());
                    mQuarterMileInfo.setTextColor(-3355444);
                    mQuarterMileInfo.setText((new StringBuilder(String.valueOf(getString(2131099802)))).append(" ").append(mQtrMileString).toString());
                    mQtrMileReached = true;
                    mTempQtrMileTime = mQtrMileString;
                    mConfirmQtrButton.setEnabled(true);
                    mDiscardQtrButton.setEnabled(true);
                }
_L15:
            mLastLocation = location;
            if(true) goto _L1; else goto _L3
_L3:
            if(f1 >= SpeedView.ACCURACY_VALUES[mMinimumAccuracy])
                break MISSING_BLOCK_LABEL_3999;
            mGraphView.setVisibility(0);
            mAccuracyNotification.setVisibility(8);
            mHeadingString = (new StringBuilder(String.valueOf((int)f2))).append("\260 ").append(SpeedView.COMPASS_DIRECTIONS[(int)((22.5D + (double)f2) / 45D)]).toString();
            SpeedView.mDisplayUnits;
            JVM INSTR tableswitch 0 2: default 2128
        //                       0 3147
        //                       1 3180
        //                       2 3147;
               goto _L4 _L5 _L6 _L5
_L4:
            if(SpeedView.mSelectedDashboard == 2)
            {
                if(f > 1.4F)
                {
                    mCompassMode.onLocationChanged(location, mSpeed, mSpeedWarning);
                    mCompassSource.setText(2131099748);
                }
                mCompassSpeed.setText((new StringBuilder(String.valueOf(mSpeed))).append(" ").append(SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits]).toString());
                mCompassOdometer.setText(distanceToString());
                mCompassElevation.setText(mAltitudeString);
                SpeedView speedview;
                float f3;
                SpeedView speedview2;
                SimpleDateFormat simpledateformat1;
                TextView textview;
                Long long2;
                if(!DateFormat.is24HourFormat(getApplicationContext()))
                    simpledateformat1 = new SimpleDateFormat("h:mm a");
                else
                    simpledateformat1 = new SimpleDateFormat("HH:mm");
                textview = mCompassTime;
                long2 = Long.valueOf(l);
                textview.setText(simpledateformat1.format(long2));
            } else
            if(SpeedView.mStoredOrientation == 0)
            {
                if(f > 1.4F)
                    mCompassView.onSpeedChanged(f2);
            } else
            if(mHeading != null)
                mHeading.setText(mHeadingString);
            if(f > mStoredMaxSpeed)
                mStoredMaxSpeed = f;
            if(!SpeedView.mDigitSpeedoChecked || !SpeedView.mDigitAddlDataToggled) goto _L8; else goto _L7
_L7:
            SpeedView.mDigitDataSelected;
            JVM INSTR tableswitch 0 4: default 2372
        //                       0 3280
        //                       1 3337
        //                       2 3458
        //                       3 3493
        //                       4 3547;
               goto _L9 _L10 _L11 _L12 _L13 _L14
_L9:
            if(SpeedView.mSelectedDashboard == 3)
                if(SpeedView.mAdvancedHudChecked || SpeedView.mAdvancedZoomChecked)
                    mHudMode.onSpeedChanged(mSpeed, mSpeedWarning, distanceToString(), mHeadingString, mAltitudeString);
                else
                    mHudMode.onSpeedChanged(mSpeed, mSpeedWarning);
            mGraphView.onSpeedChanged(mSpeed);
            if(mFirstFixTime != 0L)
            {
                if(mLastLocation != null && f != 0.0F)
                {
                    speedview2 = SpeedView.this;
                    speedview2.mStoredDistance = speedview2.mStoredDistance + location.distanceTo(mLastLocation);
                }
                SimpleDateFormat simpledateformat;
                SpeedometerView speedometerview;
                int i3;
                int j3;
                float f4;
                Long long1;
                float f5;
                String s;
                if(f > 0.0F)
                {
                    if(!mVehicleIsMoving)
                    {
                        mVehicleIsMoving = true;
                        mStateChangedTime = l;
                    }
                    mSessionMovingTime = l - mStateChangedTime;
                } else
                if(mVehicleIsMoving)
                {
                    mVehicleIsMoving = false;
                    SpeedView speedview1 = SpeedView.this;
                    speedview1.mStoredMovingTime = speedview1.mStoredMovingTime + (l - mStateChangedTime);
                    mSessionMovingTime = 0L;
                    mStateChangedTime = l;
                }
                mSessionTotalTime = l - mFirstFixTime;
            }
            displayStoredData();
            if(SpeedView.mWarningChecked)
            {
                if(mSpeed > mSpeedWarning && mSoundAlertToggled)
                {
                    if(mWarningSound != null && !mWarningSound.getTitle(getBaseContext()).equals("Unknown ringtone"))
                    {
                        if(!mWarningSound.isPlaying())
                            mWarningSound.play();
                    } else
                    {
                        Toast.makeText(getBaseContext(), 2131099705, 1).show();
                    }
                } else
                if(mWarningSound != null && mWarningSound.isPlaying())
                    mWarningSound.stop();
                if(mSpeed > mSpeedWarning && mVibrationChecked)
                    mVibrator.vibrate(300L);
                if(mSpeed > mSpeedWarning && !mLimitFlag)
                {
                    if(SpeedView.mSelectedDashboard != 3)
                        Toast.makeText(getBaseContext(), 2131099706, 1).show();
                    mCompassSpeed.setTextColor(-65536);
                    mMaxSpeed.setTextColor(-65536);
                    mLimitFlag = true;
                } else
                if(mSpeed <= mSpeedWarning && mLimitFlag)
                {
                    if(SpeedView.mCustomColorsChecked)
                    {
                        mCompassSpeed.setTextColor(SpeedView.mSecondaryTextColor);
                        mMaxSpeed.setTextColor(SpeedView.mSecondaryTextColor);
                    } else
                    {
                        mCompassSpeed.setTextColor(-3355444);
                        mMaxSpeed.setTextColor(-3355444);
                    }
                    mLimitFlag = false;
                }
            }
            if(SpeedView.mStreetAddrChecked && SpeedView.mHasNetworkAccess)
            {
                if(mLastAddress != null)
                {
                    f3 = location.distanceTo(mLastAddress);
                    int k2;
                    if(f > 15.6F)
                        k2 = 1000;
                    else
                        k2 = 100;
                    if(f3 > (float)k2)
                    {
                        displayAddress(location);
                        mLastAddress = location;
                    }
                } else
                {
                    displayAddress(location);
                    mLastAddress = location;
                }
            } else
            {
                mAddressLine0.setText(2131099737);
            }
            if(mAccuracy < 500)
            {
                if(mAccuracy > 50 && mAccuracy < 500)
                    mSignalStrength.setImageResource(2130837516);
                else
                    mSignalStrength.setImageResource(2130837515);
            } else
            {
                mSignalStrength.setImageResource(2130837518);
            }
            if(mTrackLoggingChecked)
                if(mLastTrackLocation != null)
                {
                    if(l - mLastTrackLocation.getTime() > (long)(1000 * SpeedView.MIN_TIME_VALUES[mMinTimeBetweenPts]) && location.distanceTo(mLastTrackLocation) > (float)SpeedView.MIN_DISTANCE_VALUES[mMinDistBetweenPts])
                    {
                        mTrackBuffer.append(mCoordFormat.format(d)).append("|").append(mCoordFormat.format(d1)).append("|").append(l / 1000L - 1280000000L).append("|").append(f).append("|").append((int)d2).append("\n");
                        if(mTrackBuffer.length() > 2000)
                            saveCurrentTrack();
                        mLastTrackLocation = location;
                    }
                } else
                {
                    mLastTrackLocation = location;
                }
_L16:
            if(mFirstFixTime == 0L)
            {
                mFirstFixTime = l;
                speedview = SpeedView.this;
                boolean flag;
                if(f > 0.0F)
                    flag = true;
                else
                    flag = false;
                speedview.mVehicleIsMoving = flag;
                mStateChangedTime = mFirstFixTime;
            }
              goto _L15
_L5:
            mAltitudeString = (new StringBuilder(String.valueOf((int)(d2 / 0.30480000000000002D)))).append(" ft \u2191").toString();
              goto _L4
_L6:
            mAltitudeString = (new StringBuilder(String.valueOf((int)d2))).append(" m \u2191").toString();
              goto _L4
_L10:
            s = getElapsedTimeString(mStoredTotalTime + mSessionTotalTime);
            mSpeedometerView.onSpeedChanged(mSpeed, mSpeedWarning, mStoredMaxSpeed, s);
              goto _L9
_L11:
            f5 = 0.0F;
            if(mStoredTotalTime + mSessionTotalTime != 0L)
                f5 = mStoredDistance / ((float)(mStoredTotalTime + mSessionTotalTime) / 1000F);
            mSpeedometerView.onSpeedChanged(mSpeed, mSpeedWarning, mStoredMaxSpeed, (new StringBuilder()).append(getDisplaySpeed(f5)).append(" ").append(SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits]).toString());
              goto _L9
_L12:
            mSpeedometerView.onSpeedChanged(mSpeed, mSpeedWarning, mStoredMaxSpeed, mAltitudeString);
              goto _L9
_L13:
            mSpeedometerView.onSpeedChanged(mSpeed, mSpeedWarning, mStoredMaxSpeed, (new StringBuilder(String.valueOf(mAccuracy))).append(" m").toString());
              goto _L9
_L14:
            if(!DateFormat.is24HourFormat(getApplicationContext()))
                simpledateformat = new SimpleDateFormat("h:mm a");
            else
                simpledateformat = new SimpleDateFormat("HH:mm");
            speedometerview = mSpeedometerView;
            i3 = mSpeed;
            j3 = mSpeedWarning;
            f4 = mStoredMaxSpeed;
            long1 = Long.valueOf(l);
            speedometerview.onSpeedChanged(i3, j3, f4, simpledateformat.format(long1));
              goto _L9
_L8:
            mSpeedometerView.onSpeedChanged(mSpeed, mSpeedWarning, mStoredMaxSpeed);
              goto _L9
            mAccuracyNotification.setVisibility(0);
            mGraphView.setVisibility(8);
            mLowAccuracy.setText((new StringBuilder(String.valueOf(getString(2131099661)))).append(" (").append(mAccuracy).append(" m)").toString());
              goto _L16
        }

        public void onProviderDisabled(String s)
        {
        }

        public void onProviderEnabled(String s)
        {
        }

        public void onStatusChanged(String s, int i, Bundle bundle)
        {
        }

        private int mAccuracy;
        private String mAltitudeString;
        private String mHeadingString;
        private boolean mLimitFlag;
        private int mSpeed;
        final SpeedView this$0;

        private MyLocationListener()
        {
            this$0 = SpeedView.this;
            super();
            mLimitFlag = false;
        }

        MyLocationListener(MyLocationListener mylocationlistener)
        {
            this();
        }
    }


    public SpeedView()
    {
        mTrackBuffer = new StringBuilder();
        mBackgroundConn = new ServiceConnection() {

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                mStoredDistance = ((BackgroundService.LocalBinder)ibinder).getStoredDistance();
                mStoredMaxSpeed = ((BackgroundService.LocalBinder)ibinder).getStoredMaxSpeed();
                mStoredMovingTime = ((BackgroundService.LocalBinder)ibinder).getStoredMovingTime();
                mStoredTotalTime = ((BackgroundService.LocalBinder)ibinder).getStoredTotalTime();
                displayStoredData();
                ((BackgroundService.LocalBinder)ibinder).dumpCurrentTrack();
                try
                {
                    unbindService(mBackgroundConn);
                }
                catch(IllegalArgumentException illegalargumentexception)
                {
                    illegalargumentexception.printStackTrace();
                }
                stopService(new Intent(SpeedView.this, com/codesector/speedview/pro/BackgroundService));
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
;
        mUpdateWidgetConn = new ServiceConnection() {

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                mStoredDistance = ((UpdateWidgetService.LocalBinder)ibinder).getStoredDistance();
                mStoredMaxSpeed = ((UpdateWidgetService.LocalBinder)ibinder).getStoredMaxSpeed();
                mStoredMovingTime = ((UpdateWidgetService.LocalBinder)ibinder).getStoredMovingTime();
                mStoredTotalTime = ((UpdateWidgetService.LocalBinder)ibinder).getStoredTotalTime();
                displayStoredData();
                ((UpdateWidgetService.LocalBinder)ibinder).dumpCurrentTrack();
                try
                {
                    unbindService(mUpdateWidgetConn);
                }
                catch(IllegalArgumentException illegalargumentexception)
                {
                    illegalargumentexception.printStackTrace();
                }
                stopService(new Intent(SpeedView.this, com/codesector/speedview/pro/UpdateWidgetService));
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
;
        mOrientationListener = new SensorEventListener() {

            public void onAccuracyChanged(Sensor sensor, int i)
            {
            }

            public void onSensorChanged(SensorEvent sensorevent)
            {
                float f = sensorevent.values[0];
                if(mLastLocation != null && mLastLocation.getSpeed() >= 1.4F) goto _L2; else goto _L1
_L1:
                if(SpeedView.mSelectedDashboard != 2) goto _L4; else goto _L3
_L3:
                mCompassMode.onSensorChanged(f);
                mCompassSource.setText(2131099747);
_L2:
                return;
_L4:
                if(SpeedView.mSelectedDashboard == 1)
                    mCompassView.onSpeedChanged(f);
                if(true) goto _L2; else goto _L5
_L5:
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
;
    }

    private void checkGPSEnabled()
    {
        if(!mLocationManager.isProviderEnabled("gps"))
        {
            if(!mNotifiedAboutGPS)
            {
                (new android.app.AlertDialog.Builder(this)).setTitle(2131099709).setMessage(2131099710).setPositiveButton(2131099693, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
                    }

                    final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
                }
).setNeutralButton(2131099711, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                    }

                    final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
                }
).setNegativeButton(2131099691, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        mExitButtonPressed = true;
                        finish();
                    }

                    final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
                }
).show();
                mNotifiedAboutGPS = true;
            }
            mStatusMessage.setText(2131099734);
            mNumOfSatellites.setText(2131099735);
            mHandler.post(mGPSIsDisabled);
            mTipMessage.setText(2131099667);
            mRecordingStatus.setTextColor(-7829368);
            mRecordingButton.setEnabled(false);
            mIsGPSEnabled = false;
        } else
        {
            mRecordingStatus.setTextColor(-3355444);
            mRecordingButton.setEnabled(true);
            mIsGPSEnabled = true;
        }
    }

    private void displayAddress(final Location location)
    {
        (new Thread() {

            public void run()
            {
                try
                {
                    List list = mGeocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    if(list.size() > 0)
                    {
                        mAddress = (Address)list.get(0);
                        mHandler.post(mAddressFound);
                    } else
                    {
                        mHandler.post(mUnableToGetAddress);
                    }
                }
                catch(Exception exception)
                {
                    mHandler.post(mNetworkFailure);
                }
            }

            final SpeedView this$0;
            private final Location val$location;

            
            {
                this$0 = SpeedView.this;
                location = location1;
                super();
            }
        }
).start();
    }

    private void displayStoredData()
    {
        float f = 0.0F;
        float f1 = 0.0F;
        if(mStoredMovingTime + mSessionMovingTime != 0L)
            f = mStoredDistance / ((float)(mStoredMovingTime + mSessionMovingTime) / 1000F);
        if(mStoredTotalTime + mSessionTotalTime != 0L)
            f1 = mStoredDistance / ((float)(mStoredTotalTime + mSessionTotalTime) / 1000F);
        if(mSelectedDashboard == 4)
        {
            mTripDistance.setText(distanceToString());
            mTripTimeMoving.setText(getElapsedTimeString(mStoredMovingTime + mSessionMovingTime));
            mTripTimeStopped.setText(getElapsedTimeString((mStoredTotalTime + mSessionTotalTime) - (mStoredMovingTime + mSessionMovingTime)));
            mTripTimeTotal.setText(getElapsedTimeString(mStoredTotalTime + mSessionTotalTime));
            mSpeedMovingAvg.setText((new StringBuilder(String.valueOf(getDisplaySpeed(f)))).append(" ").append(UNITS_ARRAY[mDisplayUnits]).toString());
            mSpeedOverallAvg.setText((new StringBuilder(String.valueOf(getDisplaySpeed(f1)))).append(" ").append(UNITS_ARRAY[mDisplayUnits]).toString());
            mFrom0To60Result.setText(mStored0To60Time);
            mFrom0To100Result.setText(mStored0To100Time);
            mQuarterMileResult.setText(mStoredQtrMileTime);
        } else
        {
            mOdometer.setText(distanceToString());
            mMaxSpeed.setText((new StringBuilder()).append(getDisplaySpeed(mStoredMaxSpeed)).toString());
        }
        if(mIsTablet)
        {
            mTripTimeMovingT.setText(getElapsedTimeString(mStoredMovingTime + mSessionMovingTime));
            mTripTimeStoppedT.setText(getElapsedTimeString((mStoredTotalTime + mSessionTotalTime) - (mStoredMovingTime + mSessionMovingTime)));
            mTripTimeTotalT.setText(getElapsedTimeString(mStoredTotalTime + mSessionTotalTime));
            mSpeedMovingAvgT.setText((new StringBuilder(String.valueOf(getDisplaySpeed(f)))).append(" ").append(UNITS_ARRAY[mDisplayUnits]).toString());
            mSpeedOverallAvgT.setText((new StringBuilder(String.valueOf(getDisplaySpeed(f1)))).append(" ").append(UNITS_ARRAY[mDisplayUnits]).toString());
            mFrom0To60ResultT.setText(mStored0To60Time);
            mFrom0To100ResultT.setText(mStored0To100Time);
            mQuarterMileResultT.setText(mStoredQtrMileTime);
        }
    }

    private String distanceToString()
    {
        mDisplayUnits;
        JVM INSTR tableswitch 0 2: default 28
    //                   0 34
    //                   1 116
    //                   2 198;
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

    private void exportTrackFile(boolean flag)
    {
        saveCurrentTrack();
        mSendTrackInit = flag;
        File file = new File((new StringBuilder()).append(Environment.getExternalStorageDirectory()).append("/speedview/logs").toString());
        if(!file.exists())
        {
            progressHandler.sendEmptyMessage(0);
        } else
        {
            mLogFilesList = file.list(mLogExtensionFilter);
            if(mLogFilesList != null)
            {
                if(mLogFilesList.length > 1)
                {
                    Arrays.sort(mLogFilesList);
                    mExportGPXButton.showContextMenu();
                } else
                {
                    exportTrackFileEx(mLogFilesList[0]);
                }
            } else
            {
                progressHandler.sendEmptyMessage(0);
            }
        }
    }

    private void exportTrackFileEx(final String file)
    {
        mProgressDialog.show();
        (new Thread() {

            public void run()
            {
                try
                {
                    mTrackLogFile = new File((new StringBuilder()).append(Environment.getExternalStorageDirectory()).append("/speedview/logs").toString(), file);
                    File file1;
                    File file2;
                    BufferedReader bufferedreader;
                    BufferedWriter bufferedwriter;
                    if(SpeedView.mMaverickInst)
                        file1 = new File((new StringBuilder()).append(Environment.getExternalStorageDirectory()).append("/maverick/tracks").toString());
                    else
                        file1 = new File((new StringBuilder()).append(Environment.getExternalStorageDirectory()).append("/speedview/tracks").toString());
                    if(!file1.exists())
                        file1.mkdirs();
                    file2 = new File(file1, (new StringBuilder("SpeedView [")).append(file.substring(0, 10)).append("].gpx").toString());
                    bufferedreader = new BufferedReader(new FileReader(mTrackLogFile), 8192);
                    bufferedwriter = new BufferedWriter(new FileWriter(file2), 8192);
                    bufferedwriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                    bufferedwriter.write("<gpx\n");
                    bufferedwriter.write(" version=\"1.0\"\n");
                    bufferedwriter.write(" creator=\"SpeedView - http://www.codesector.com\"\n");
                    bufferedwriter.write(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
                    bufferedwriter.write(" xmlns=\"http://www.topografix.com/GPX/1/0\"\n");
                    bufferedwriter.write(" xsi:schemaLocation=\"http://www.topografix.com/GPX/1/0 http://www.topografix.com/GPX/1/0/gpx.xsd\">\n");
                    bufferedwriter.write((new StringBuilder("<time>")).append(mDateFormat.format(new Date())).append("T").append(mTimeFormat.format(new Date())).append("Z").append("</time>\n").toString());
                    bufferedwriter.write("<trk>\n");
                    bufferedwriter.write(" <name>SpeedView</name>\n");
                    bufferedwriter.write(" <trkseg>\n");
                    String[] _tmp = (String[])null;
                    do
                    {
                        String s = bufferedreader.readLine();
                        if(s == null)
                        {
                            bufferedwriter.write(" </trkseg>\n");
                            bufferedwriter.write("</trk>\n");
                            bufferedwriter.write("</gpx>\n");
                            bufferedwriter.flush();
                            bufferedwriter.close();
                            bufferedreader.close();
                            Message message = progressHandler.obtainMessage();
                            message.what = 1;
                            Bundle bundle = new Bundle();
                            bundle.putString("file_path", file2.getAbsolutePath());
                            message.setData(bundle);
                            progressHandler.sendMessage(message);
                            break;
                        }
                        String as[] = s.split("\\|");
                        long l = 1000L * (1280000000L + Long.parseLong(as[2]));
                        float f = Float.parseFloat(as[3]);
                        bufferedwriter.write((new StringBuilder("  <trkpt lat=\"")).append(as[0]).append("\" lon=\"").append(as[1]).append("\">\n").toString());
                        bufferedwriter.write((new StringBuilder("    <time>")).append(mDateFormat.format(new Date(l))).append("T").append(mTimeFormat.format(new Date(l))).append("Z").append("</time>\n").toString());
                        bufferedwriter.write((new StringBuilder("    <desc>")).append(getDisplaySpeed(f)).append(" ").append(SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits]).append("</desc>\n").toString());
                        bufferedwriter.write((new StringBuilder("    <ele>")).append(as[4]).append("</ele>\n").toString());
                        bufferedwriter.write("  </trkpt>\n");
                    } while(true);
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                    progressHandler.sendEmptyMessage(2);
                }
            }

            final SpeedView this$0;
            private final String val$file;

            
            {
                this$0 = SpeedView.this;
                file = s;
                super();
            }
        }
).start();
    }

    private int getDisplaySpeed(float f)
    {
        mDisplayUnits;
        JVM INSTR tableswitch 0 2: default 28
    //                   0 32
    //                   1 43
    //                   2 54;
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

    private String getElapsedTimeString(long l)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(2);
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
        return (new StringBuilder(String.valueOf(String.format(s, aobj3)))).append(":").append(s2).append(":").append(s1).toString();
    }

    private Location getLastLocation()
    {
        return mLastLocation;
    }

    private boolean hasMatchingActivity(String s, String s1)
    {
        Intent intent = new Intent();
        intent.setClassName(s, s1);
        boolean flag;
        if(getPackageManager().queryIntentActivities(intent, 65536).size() > 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private boolean isNetworkAvailable()
    {
        boolean flag = true;
        if(((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo() == null)
        {
            if(!mNotifiedAboutNetwork)
            {
                (new android.app.AlertDialog.Builder(this)).setTitle(2131099712).setMessage(2131099713).setPositiveButton(2131099693, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        startActivity(new Intent("android.settings.WIRELESS_SETTINGS"));
                    }

                    final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
                }
).setNeutralButton(2131099711, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                    }

                    final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
                }
).setNegativeButton(2131099691, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        mExitButtonPressed = true;
                        finish();
                    }

                    final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
                }
).show();
                mNotifiedAboutNetwork = flag;
            }
            flag = false;
        }
        return flag;
    }

    private void refreshAdView()
    {
        try
        {
            if(mSelectedDashboard == 0)
            {
                mAdViewStartup.loadAd(new AdRequest());
                if(mAdViewMain != null)
                    mAdViewMain.stopLoading();
                break MISSING_BLOCK_LABEL_104;
            }
            if(mSelectedDashboard == 1)
            {
                if(mAdViewMain != null)
                    mAdViewMain.loadAd(new AdRequest());
                mAdViewStartup.stopLoading();
                break MISSING_BLOCK_LABEL_104;
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            break MISSING_BLOCK_LABEL_104;
        }
        mAdViewStartup.stopLoading();
        if(mAdViewMain != null)
            mAdViewMain.stopLoading();
    }

    private void resetAcclTimes()
    {
        mStored0To60Time = getString(2131099663);
        mStored0To100Time = getString(2131099663);
        mStoredQtrMileTime = getString(2131099663);
    }

    private void resetMaxSpeed()
    {
        mStoredMaxSpeed = 0.0F;
        if(mSelectedDashboard != 4)
        {
            mOdometer.setText(distanceToString());
            mMaxSpeed.setText((new StringBuilder()).append(getDisplaySpeed(mStoredMaxSpeed)).toString());
        }
    }

    private void resetOdometer()
    {
        mStoredDistance = 0.0F;
        mStoredMovingTime = 0L;
        mStoredTotalTime = 0L;
        mFirstFixTime = 0L;
        mSessionMovingTime = 0L;
        mSessionTotalTime = 0L;
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

    private void saveUserPreferences()
    {
        android.content.SharedPreferences.Editor editor = getSharedPreferences("PrefsFile", 0).edit();
        editor.putInt("currentVersion", mVersionCode);
        editor.putInt("storedOrientation", mStoredOrientation);
        editor.putBoolean("isRecording", mIsRecording);
        editor.putFloat("storedDistance", mStoredDistance);
        editor.putFloat("storedMaxSpeed", mStoredMaxSpeed);
        editor.putString("stored0To60Time", mStored0To60Time);
        editor.putString("stored0To100Time", mStored0To100Time);
        editor.putString("storedQtrMileTime", mStoredQtrMileTime);
        editor.putLong("storedMovingTime", mStoredMovingTime + mSessionMovingTime);
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

    private void setFullScreenMode(boolean flag)
    {
        android.view.WindowManager.LayoutParams layoutparams = getWindow().getAttributes();
        if(flag)
            layoutparams.flags = 1024 | layoutparams.flags;
        else
            layoutparams.flags = -1025 & layoutparams.flags;
        getWindow().setAttributes(layoutparams);
    }

    private void switchToScreen(int i)
    {
        i;
        JVM INSTR tableswitch 0 4: default 36
    //                   0 37
    //                   1 101
    //                   2 165
    //                   3 229
    //                   4 293;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return;
_L2:
        mMainScreen.setVisibility(4);
        mCompassScreen.setVisibility(4);
        mHudScreen.setVisibility(4);
        mAdvancedScreen.setVisibility(4);
        mStartupScreen.setVisibility(0);
        mSelectedDashboard = 0;
        if(mAnalyticsTracker != null)
            mAnalyticsTracker.trackPageView("/startupScreen");
        continue; /* Loop/switch isn't completed */
_L3:
        mStartupScreen.setVisibility(4);
        mCompassScreen.setVisibility(4);
        mHudScreen.setVisibility(4);
        mAdvancedScreen.setVisibility(4);
        mMainScreen.setVisibility(0);
        mSelectedDashboard = 1;
        if(mAnalyticsTracker != null)
            mAnalyticsTracker.trackPageView("/mainScreen");
        continue; /* Loop/switch isn't completed */
_L4:
        mStartupScreen.setVisibility(4);
        mMainScreen.setVisibility(4);
        mHudScreen.setVisibility(4);
        mAdvancedScreen.setVisibility(4);
        mCompassScreen.setVisibility(0);
        mSelectedDashboard = 2;
        if(mAnalyticsTracker != null)
            mAnalyticsTracker.trackPageView("/compassScreen");
        continue; /* Loop/switch isn't completed */
_L5:
        mStartupScreen.setVisibility(4);
        mMainScreen.setVisibility(4);
        mCompassScreen.setVisibility(4);
        mAdvancedScreen.setVisibility(4);
        mHudScreen.setVisibility(0);
        mSelectedDashboard = 3;
        if(mAnalyticsTracker != null)
            mAnalyticsTracker.trackPageView("/hudScreen");
        continue; /* Loop/switch isn't completed */
_L6:
        mStartupScreen.setVisibility(4);
        mMainScreen.setVisibility(4);
        mCompassScreen.setVisibility(4);
        mHudScreen.setVisibility(4);
        mAdvancedScreen.setVisibility(0);
        mSelectedDashboard = 4;
        if(mAnalyticsTracker != null)
            mAnalyticsTracker.trackPageView("/advancedScreen");
        if(true) goto _L1; else goto _L7
_L7:
    }

    public boolean onContextItemSelected(MenuItem menuitem)
    {
        exportTrackFileEx(mLogFilesList[menuitem.getItemId()]);
        return super.onContextItemSelected(menuitem);
    }

    public void onContextMenuClosed(Menu menu)
    {
        super.onContextMenuClosed(menu);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        int i;
        DisplayMetrics displaymetrics;
        DecimalFormatSymbols decimalformatsymbols;
        try
        {
            PackageInfo packageinfo = getPackageManager().getPackageInfo("com.codesector.speedview.pro", 128);
            mVersionCode = packageinfo.versionCode;
            mVersionName = packageinfo.versionName;
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            namenotfoundexception.printStackTrace();
        }
        i = getResources().getConfiguration().screenLayout;
        if((i & 15) == 4)
        {
            mScreenLayoutSize = 4;
            mIsScreenSupported = true;
            mIsTablet = true;
        } else
        if((i & 15) == 3)
        {
            mScreenLayoutSize = 3;
            mIsScreenSupported = false;
        } else
        if((i & 15) == 2)
        {
            mScreenLayoutSize = 2;
            mIsScreenSupported = true;
        }
        displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        if(displaymetrics.densityDpi == 160)
        {
            if(mScreenLayoutSize == 4)
                mScreenRatio = 1.5F;
            else
                mScreenRatio = displaymetrics.density;
        } else
        {
            mScreenRatio = displaymetrics.density;
        }
        if(mIsTablet)
            setTheme(16973931);
        getWindow().setFlags(128, 128);
        getWindow().setFormat(1);
        getWindow().addFlags(4096);
        setContentView(2130903046);
        mViewStub = (ViewStub)findViewById(2131296303);
        if(mViewStub != null)
            mViewStub.inflate();
        mGeocoder = new Geocoder(getBaseContext(), Locale.getDefault());
        mAddressView = (RelativeLayout)findViewById(2131296261);
        mAddressLine0 = (TextView)findViewById(2131296263);
        mAddressLine1 = (TextView)findViewById(2131296264);
        mSignalStrength = (ImageView)findViewById(2131296266);
        mNumberOfSats = (TextView)findViewById(2131296267);
        mAddressView.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(SpeedView.mStreetAddrChecked && SpeedView.mHasNetworkAccess && SpeedView.mHasGPSFix)
                {
                    mAddressLine0.setText(2131099728);
                    mAddressLine1.setText(2131099729);
                    displayAddress(getLastLocation());
                }
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mStartupScreen = (RelativeLayout)findViewById(2131296292);
        mMainScreen = (LinearLayout)findViewById(2131296302);
        mCompassScreen = (RelativeLayout)findViewById(2131296317);
        mHudScreen = (RelativeLayout)findViewById(2131296324);
        mAdvancedScreen = (LinearLayout)findViewById(2131296326);
        mSelectedDashboard = 0;
        mLocationManager = (LocationManager)getSystemService("location");
        mLocationListener = new MyLocationListener(null);
        mGPSListener = new MyGPSListener(null);
        mLastLocation = mLocationManager.getLastKnownLocation("gps");
        mSensorManager = (SensorManager)getSystemService("sensor");
        mVibrator = (Vibrator)getSystemService("vibrator");
        if(((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo() != null)
        {
            mAnalyticsTracker = GoogleAnalyticsTracker.getInstance();
            mAnalyticsTracker.startNewSession("UA-128823-8", this);
            mAnalyticsTracker.trackPageView("/startupScreen");
            mAnalyticsTracker.dispatch();
        }
        mCoordFormat = new DecimalFormat("0.000000");
        decimalformatsymbols = mCoordFormat.getDecimalFormatSymbols();
        decimalformatsymbols.setDecimalSeparator('.');
        mCoordFormat.setDecimalFormatSymbols(decimalformatsymbols);
        mDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mTimeFormat = new SimpleDateFormat("HH:mm:ss");
        mTimeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        mLogExtensionFilter = new FilenameFilter() {

            public boolean accept(File file, String s)
            {
                return s.toLowerCase().endsWith(".log");
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
;
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(0);
        mProgressDialog.setMessage(getString(2131099779));
        mProgressDialog.setCancelable(false);
        mSpeedView = (RelativeLayout)findViewById(2131296291);
        mAccuracyNotification = (RelativeLayout)findViewById(2131296314);
        mAccelerationLayout = (LinearLayout)findViewById(2131296351);
        mFrom0To60Screen = (LinearLayout)findViewById(2131296384);
        mFrom0To100Screen = (LinearLayout)findViewById(2131296395);
        mQuarterMileScreen = (LinearLayout)findViewById(2131296406);
        mQuickLaunchLayout = (LinearLayout)findViewById(2131296376);
        mSwitchboard = (RelativeLayout)findViewById(2131296417);
        mSatelliteView = (SatelliteView)findViewById(2131296295);
        mCompassView = (CompassView)findViewById(2131296306);
        mSpeedometerView = (SpeedometerView)findViewById(2131296307);
        mGraphView = (GraphView)findViewById(2131296313);
        mCompassMode = (CompassMode)findViewById(2131296318);
        mHudMode = (HudMode)findViewById(2131296325);
        mStatusMessage = (TextView)findViewById(2131296296);
        mNumOfSatellites = (TextView)findViewById(2131296297);
        mTipMessage = (TextView)findViewById(2131296299);
        mOdometerField = (ImageView)findViewById(2131296309);
        mOdometer = (TextView)findViewById(2131296310);
        mMaxField = (ImageView)findViewById(2131296311);
        mMaxSpeed = (TextView)findViewById(2131296312);
        mLowAccuracy = (TextView)findViewById(2131296315);
        mHeading = (TextView)findViewById(2131296436);
        mCompassSpeed = (TextView)findViewById(2131296319);
        mCompassOdometer = (TextView)findViewById(2131296320);
        mCompassElevation = (TextView)findViewById(2131296321);
        mCompassTime = (TextView)findViewById(2131296322);
        mCompassSource = (TextView)findViewById(2131296323);
        mTripDistance = (TextView)findViewById(2131296332);
        mTripTimeMoving = (TextView)findViewById(2131296335);
        mTripTimeStopped = (TextView)findViewById(2131296338);
        mTripTimeTotal = (TextView)findViewById(2131296341);
        mSpeedMovingAvg = (TextView)findViewById(2131296344);
        mSpeedOverallAvg = (TextView)findViewById(2131296347);
        mAccelerationInfo = (TextView)findViewById(2131296354);
        mFrom0To60Row = (TableRow)findViewById(2131296356);
        mFrom0To100Row = (TableRow)findViewById(2131296359);
        mFrom0To60Result = (TextView)findViewById(2131296358);
        mFrom0To100Result = (TextView)findViewById(2131296361);
        mQuarterMileResult = (TextView)findViewById(2131296364);
        mFrom0To60Button = (Button)findViewById(2131296366);
        mFrom0To100Button = (Button)findViewById(2131296367);
        mQuarterMileButton = (Button)findViewById(2131296368);
        mRecordingStatus = (TextView)findViewById(2131296349);
        mRecordingButton = (Button)findViewById(2131296350);
        mGPXExportStatus = (TextView)findViewById(2131296372);
        mExportGPXButton = (Button)findViewById(2131296374);
        registerForContextMenu(mExportGPXButton);
        mSendGPXButton = (Button)findViewById(2131296375);
        mGoogleMapsButton = (Button)findViewById(2131296381);
        mOpenSpotButton = (Button)findViewById(2131296382);
        mMaverickButton = (Button)findViewById(2131296383);
        mFrom0To60Time = (TextView)findViewById(2131296386);
        mFrom0To60Feet = (TextView)findViewById(2131296387);
        mFrom0To60Speed = (TextView)findViewById(2131296389);
        mFrom0To60Info = (TextView)findViewById(2131296391);
        mConfirm0To60Button = (Button)findViewById(2131296393);
        mDiscard0To60Button = (Button)findViewById(2131296394);
        mFrom0To100Time = (TextView)findViewById(2131296397);
        mFrom0To100Meters = (TextView)findViewById(2131296398);
        mFrom0To100Speed = (TextView)findViewById(2131296400);
        mFrom0To100Info = (TextView)findViewById(2131296402);
        mConfirm0To100Button = (Button)findViewById(2131296404);
        mDiscard0To100Button = (Button)findViewById(2131296405);
        mQuarterMileTime = (TextView)findViewById(2131296408);
        mQuarterMileSpeed = (TextView)findViewById(2131296409);
        mQuarterMileDist = (TextView)findViewById(2131296411);
        mQuarterMileUnits = (TextView)findViewById(2131296412);
        mQuarterMileInfo = (TextView)findViewById(2131296413);
        mConfirmQtrButton = (Button)findViewById(2131296415);
        mDiscardQtrButton = (Button)findViewById(2131296416);
        mTownLimitToggle = (RelativeLayout)findViewById(2131296418);
        mTownLimitSign = (ImageView)findViewById(2131296419);
        mTownLimitNumbers = (TextView)findViewById(2131296420);
        mTownLimitDec = (ImageView)findViewById(2131296421);
        mTownLimitInc = (ImageView)findViewById(2131296422);
        mHighwayLimitToggle = (RelativeLayout)findViewById(2131296423);
        mHighwayLimitSign = (ImageView)findViewById(2131296424);
        mHighwayLimitNumbers = (TextView)findViewById(2131296425);
        mHighwayLimitDec = (ImageView)findViewById(2131296426);
        mHighwayLimitInc = (ImageView)findViewById(2131296427);
        mFreewayLimitToggle = (RelativeLayout)findViewById(2131296428);
        mFreewayLimitSign = (ImageView)findViewById(2131296429);
        mFreewayLimitNumbers = (TextView)findViewById(2131296430);
        mFreewayLimitDec = (ImageView)findViewById(2131296431);
        mFreewayLimitInc = (ImageView)findViewById(2131296432);
        if(mIsTablet)
        {
            mTabletHelper = new TabletHelper(this);
            mActionBar = mTabletHelper.getActionBar();
            if(mActionBar != null)
                mTabletHelper.enableTabs(mActionBar);
            mAccelerationLayoutT = (LinearLayout)findViewById(2131296461);
            mTripTimeMovingT = (TextView)findViewById(2131296448);
            mTripTimeStoppedT = (TextView)findViewById(2131296451);
            mTripTimeTotalT = (TextView)findViewById(2131296454);
            mSpeedMovingAvgT = (TextView)findViewById(2131296457);
            mSpeedOverallAvgT = (TextView)findViewById(2131296460);
            mFrom0To60RowT = (TableRow)findViewById(2131296463);
            mFrom0To100RowT = (TableRow)findViewById(2131296466);
            mFrom0To60ResultT = (TextView)findViewById(2131296465);
            mFrom0To100ResultT = (TextView)findViewById(2131296468);
            mQuarterMileResultT = (TextView)findViewById(2131296471);
            mQuickLaunchLayoutT = (LinearLayout)findViewById(2131296472);
            mGoogleMapsButtonT = (Button)findViewById(2131296473);
            mOpenSpotButtonT = (Button)findViewById(2131296474);
            mMaverickButtonT = (Button)findViewById(2131296475);
        }
        mStartupScreen.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                switchToScreen(1);
                if(SpeedView.mIsTablet && mActionBar != null)
                    mTabletHelper.setSelectedNavItem(mActionBar, 1);
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mStartupScreen.setOnLongClickListener(new android.view.View.OnLongClickListener() {

            public boolean onLongClick(View view)
            {
                boolean flag = false;
                if(SpeedView.mWarningChecked)
                {
                    mSwitchboard.setVisibility(0);
                    flag = true;
                }
                return flag;
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mMainScreen.setOnLongClickListener(new android.view.View.OnLongClickListener() {

            public boolean onLongClick(View view)
            {
                boolean flag = false;
                if(SpeedView.mWarningChecked)
                {
                    mSwitchboard.setVisibility(0);
                    flag = true;
                }
                return flag;
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mCompassView.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                switchToScreen(2);
                if(SpeedView.mIsTablet && mActionBar != null)
                    mTabletHelper.setSelectedNavItem(mActionBar, 2);
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mCompassView.setOnLongClickListener(new android.view.View.OnLongClickListener() {

            public boolean onLongClick(View view)
            {
                boolean flag = false;
                if(SpeedView.mWarningChecked)
                {
                    mSwitchboard.setVisibility(0);
                    flag = true;
                }
                return flag;
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mSpeedometerView.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                switchToScreen(3);
                if(SpeedView.mIsTablet && mActionBar != null)
                    mTabletHelper.setSelectedNavItem(mActionBar, 3);
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mSpeedometerView.setOnLongClickListener(new android.view.View.OnLongClickListener() {

            public boolean onLongClick(View view)
            {
                boolean flag = false;
                if(SpeedView.mWarningChecked)
                {
                    mSwitchboard.setVisibility(0);
                    flag = true;
                }
                return flag;
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mOdometerField.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                (new android.app.AlertDialog.Builder(SpeedView.this)).setTitle(2131099714).setMessage(2131099715).setPositiveButton(2131099724, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int j)
                    {
                        resetOdometer();
                        displayStoredData();
                        Toast.makeText(getBaseContext(), 2131099700, 1).show();
                    }

                    final _cls18 this$1;

                    
                    {
                        this$1 = _cls18.this;
                        super();
                    }
                }
).setNegativeButton(2131099725, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int j)
                    {
                    }

                    final _cls18 this$1;

                    
                    {
                        this$1 = _cls18.this;
                        super();
                    }
                }
).show();
            }

            final SpeedView this$0;


            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mOdometer.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                (new android.app.AlertDialog.Builder(SpeedView.this)).setTitle(2131099714).setMessage(2131099715).setPositiveButton(2131099724, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int j)
                    {
                        resetOdometer();
                        displayStoredData();
                        Toast.makeText(getBaseContext(), 2131099700, 1).show();
                    }

                    final _cls19 this$1;

                    
                    {
                        this$1 = _cls19.this;
                        super();
                    }
                }
).setNegativeButton(2131099725, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int j)
                    {
                    }

                    final _cls19 this$1;

                    
                    {
                        this$1 = _cls19.this;
                        super();
                    }
                }
).show();
            }

            final SpeedView this$0;


            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mMaxField.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                (new android.app.AlertDialog.Builder(SpeedView.this)).setTitle(2131099716).setMessage(2131099717).setPositiveButton(2131099724, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int j)
                    {
                        resetMaxSpeed();
                        Toast.makeText(getBaseContext(), 2131099701, 1).show();
                    }

                    final _cls20 this$1;

                    
                    {
                        this$1 = _cls20.this;
                        super();
                    }
                }
).setNegativeButton(2131099725, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int j)
                    {
                    }

                    final _cls20 this$1;

                    
                    {
                        this$1 = _cls20.this;
                        super();
                    }
                }
).show();
            }

            final SpeedView this$0;


            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mMaxSpeed.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                (new android.app.AlertDialog.Builder(SpeedView.this)).setTitle(2131099716).setMessage(2131099717).setPositiveButton(2131099724, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int j)
                    {
                        resetMaxSpeed();
                        Toast.makeText(getBaseContext(), 2131099701, 1).show();
                    }

                    final _cls21 this$1;

                    
                    {
                        this$1 = _cls21.this;
                        super();
                    }
                }
).setNegativeButton(2131099725, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int j)
                    {
                    }

                    final _cls21 this$1;

                    
                    {
                        this$1 = _cls21.this;
                        super();
                    }
                }
).show();
            }

            final SpeedView this$0;


            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mGraphView.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                SpeedView.mDisplayUnits;
                JVM INSTR tableswitch 0 2: default 28
            //                           0 78
            //                           1 138
            //                           2 198;
                   goto _L1 _L2 _L3 _L4
_L1:
                switchToScreen(4);
                if(SpeedView.mIsTablet && mActionBar != null)
                    mTabletHelper.setSelectedNavItem(mActionBar, 4);
                displayStoredData();
                return;
_L2:
                mFrom0To100Row.setVisibility(8);
                mFrom0To60Row.setVisibility(0);
                mFrom0To100Button.setVisibility(8);
                mFrom0To60Button.setVisibility(0);
                mAccelerationLayout.setVisibility(0);
                continue; /* Loop/switch isn't completed */
_L3:
                mFrom0To60Row.setVisibility(8);
                mFrom0To100Row.setVisibility(0);
                mFrom0To60Button.setVisibility(8);
                mFrom0To100Button.setVisibility(0);
                mAccelerationLayout.setVisibility(0);
                continue; /* Loop/switch isn't completed */
_L4:
                mAccelerationLayout.setVisibility(8);
                if(true) goto _L1; else goto _L5
_L5:
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mGraphView.setOnLongClickListener(new android.view.View.OnLongClickListener() {

            public boolean onLongClick(View view)
            {
                boolean flag = false;
                if(SpeedView.mWarningChecked)
                {
                    mSwitchboard.setVisibility(0);
                    flag = true;
                }
                return flag;
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mCompassScreen.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                switchToScreen(1);
                if(SpeedView.mIsTablet && mActionBar != null)
                    mTabletHelper.setSelectedNavItem(mActionBar, 1);
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mCompassScreen.setOnLongClickListener(new android.view.View.OnLongClickListener() {

            public boolean onLongClick(View view)
            {
                boolean flag = false;
                if(SpeedView.mWarningChecked)
                {
                    mSwitchboard.setVisibility(0);
                    flag = true;
                }
                return flag;
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mHudScreen.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                switchToScreen(1);
                if(SpeedView.mIsTablet && mActionBar != null)
                    mTabletHelper.setSelectedNavItem(mActionBar, 1);
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mHudScreen.setOnLongClickListener(new android.view.View.OnLongClickListener() {

            public boolean onLongClick(View view)
            {
                boolean flag = false;
                if(SpeedView.mWarningChecked)
                {
                    mSwitchboard.setVisibility(0);
                    flag = true;
                }
                return flag;
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mFrom0To60Button.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
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

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mFrom0To100Button.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
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

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mQuarterMileButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                mAcclStartLocation = null;
                mQuarterMileTime.setText(2131099787);
                mQuarterMileSpeed.setText((new StringBuilder("0 ")).append(SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits]).toString());
                mQuarterMileDist.setText("0");
                mQuarterMileDist.setTextColor(-1);
                TextView textview = mQuarterMileUnits;
                String s;
                if(SpeedView.mDisplayUnits == 1)
                    s = getString(2131099800);
                else
                    s = getString(2131099799);
                textview.setText(s);
                mQuarterMileInfo.setText(2131099801);
                mQuarterMileInfo.setTextColor(-3355444);
                mConfirmQtrButton.setEnabled(false);
                mDiscardQtrButton.setEnabled(false);
                mQuarterMileScreen.setVisibility(0);
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mFrom0To60Screen.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                m60MphReached = false;
                mFrom0To60Screen.setVisibility(4);
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mConfirm0To60Button.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                mStored0To60Time = mTemp0To60Time;
                mAccelerationInfo.setText(2131099759);
                mFrom0To60Screen.setVisibility(4);
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mDiscard0To60Button.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                m60MphReached = false;
                mFrom0To60Screen.setVisibility(4);
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mFrom0To100Screen.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                m100KphReached = false;
                mFrom0To100Screen.setVisibility(4);
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mConfirm0To100Button.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                mStored0To100Time = mTemp0To100Time;
                mAccelerationInfo.setText(2131099759);
                mFrom0To100Screen.setVisibility(4);
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mDiscard0To100Button.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                m100KphReached = false;
                mFrom0To100Screen.setVisibility(4);
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mQuarterMileScreen.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                mQtrMileReached = false;
                mQuarterMileScreen.setVisibility(4);
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mConfirmQtrButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                mStoredQtrMileTime = mTempQtrMileTime;
                mAccelerationInfo.setText(2131099759);
                mQuarterMileScreen.setVisibility(4);
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mDiscardQtrButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                mQtrMileReached = false;
                mQuarterMileScreen.setVisibility(4);
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mRecordingButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(SpeedView.mIsRecording)
                {
                    mRecordingStatus.setText(2131099767);
                    mRecordingButton.setText(2131099766);
                    mLocationManager.removeUpdates(mLocationListener);
                    mLocationManager.removeGpsStatusListener(mGPSListener);
                    mSatelliteView.clearSatellites();
                    mStatusMessage.setText(2131099664);
                    mNumOfSatellites.setText(2131099665);
                    mTipMessage.setText(2131099666);
                    mAddressLine0.setText(2131099664);
                    mAddressLine1.setText(2131099665);
                    mSignalStrength.setImageResource(2130837517);
                    mNumberOfSats.setText(2131099738);
                    if(SpeedView.mDigitSpeedoChecked && SpeedView.mDigitAddlDataToggled)
                        mSpeedometerView.refreshView();
                    mSpeedometerView.onSpeedChanged(-1);
                    if(SpeedView.mIsTablet && mActionBar != null && mTabletHelper.getNavItemCount(mActionBar) == 4)
                        mTabletHelper.addStartupTab(mActionBar);
                    SpeedView speedview = SpeedView.this;
                    speedview.mStoredMovingTime = speedview.mStoredMovingTime + mSessionMovingTime;
                    SpeedView speedview1 = SpeedView.this;
                    speedview1.mStoredTotalTime = speedview1.mStoredTotalTime + mSessionTotalTime;
                    mSessionMovingTime = 0L;
                    mSessionTotalTime = 0L;
                    mFirstFixTime = 0L;
                    SpeedView.mHasGPSFix = false;
                    SpeedView.mIsRecording = false;
                } else
                {
                    mRecordingStatus.setText(2131099764);
                    mRecordingButton.setText(2131099765);
                    try
                    {
                        mLocationManager.requestLocationUpdates("gps", 0L, 0.0F, mLocationListener);
                        mLocationManager.addGpsStatusListener(mGPSListener);
                    }
                    catch(Exception exception)
                    {
                        exception.printStackTrace();
                    }
                    mAddressLine0.setText(2131099651);
                    mAddressLine1.setText(2131099652);
                    SpeedView.mIsRecording = true;
                }
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mExportGPXButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(!"mounted".equals(Environment.getExternalStorageState()))
                    Toast.makeText(getBaseContext(), 2131099812, 1).show();
                else
                    exportTrackFile(false);
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mSendGPXButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(!"mounted".equals(Environment.getExternalStorageState()))
                    Toast.makeText(getBaseContext(), 2131099812, 1).show();
                else
                    exportTrackFile(true);
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mGoogleMapsButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                Intent intent = new Intent();
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
_L1:
                return;
                ActivityNotFoundException activitynotfoundexception;
                activitynotfoundexception;
                activitynotfoundexception.printStackTrace();
                  goto _L1
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mOpenSpotButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(SpeedView.mOpenSpotInst)
                {
                    Intent intent = new Intent();
                    intent.setClassName("com.googlelabs.openspot", "com.google.android.apps.openspot.activities.MainActivity");
                    startActivity(intent);
                } else
                {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.googlelabs.openspot")));
                }
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mMaverickButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(SpeedView.mMaverickInst)
                {
                    Intent intent = new Intent();
                    if(SpeedView.mMaverickVersion == SpeedView.VERSIONS[0])
                        intent.setClassName("com.codesector.maverick.full", "com.codesector.maverick.full.Main");
                    else
                        intent.setClassName("com.codesector.maverick.lite", "com.codesector.maverick.lite.Main");
                    startActivity(intent);
                } else
                {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.codesector.maverick.lite")));
                }
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mSwitchboard.setOnLongClickListener(new android.view.View.OnLongClickListener() {

            public boolean onLongClick(View view)
            {
                mSwitchboard.setVisibility(4);
                Toast.makeText(getBaseContext(), (new StringBuilder(String.valueOf(getString(2131099830)))).append(" ").append(mSpeedWarning).append(" ").append(SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits]).toString(), 1).show();
                return true;
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mTownLimitToggle.setOnLongClickListener(new android.view.View.OnLongClickListener() {

            public boolean onLongClick(View view)
            {
                view.performClick();
                mSwitchboard.setVisibility(4);
                Toast.makeText(getBaseContext(), (new StringBuilder(String.valueOf(getString(2131099830)))).append(" ").append(mSpeedWarning).append(" ").append(SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits]).toString(), 1).show();
                return true;
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mTownLimitToggle.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
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
                mSpeedWarning = mTownSpeedLimit;
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mTownLimitDec.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                SpeedView speedview = SpeedView.this;
                speedview.mTownSpeedLimit = -1 + speedview.mTownSpeedLimit;
                mTownLimitNumbers.setText((new StringBuilder()).append(mTownSpeedLimit).toString());
                mTownLimitToggle.performClick();
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mTownLimitInc.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                SpeedView speedview = SpeedView.this;
                speedview.mTownSpeedLimit = 1 + speedview.mTownSpeedLimit;
                mTownLimitNumbers.setText((new StringBuilder()).append(mTownSpeedLimit).toString());
                mTownLimitToggle.performClick();
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mHighwayLimitToggle.setOnLongClickListener(new android.view.View.OnLongClickListener() {

            public boolean onLongClick(View view)
            {
                view.performClick();
                mSwitchboard.setVisibility(4);
                Toast.makeText(getBaseContext(), (new StringBuilder(String.valueOf(getString(2131099830)))).append(" ").append(mSpeedWarning).append(" ").append(SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits]).toString(), 1).show();
                return true;
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mHighwayLimitToggle.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
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
                mSpeedWarning = mHighwaySpeedLimit;
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mHighwayLimitDec.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                SpeedView speedview = SpeedView.this;
                speedview.mHighwaySpeedLimit = -1 + speedview.mHighwaySpeedLimit;
                mHighwayLimitNumbers.setText((new StringBuilder()).append(mHighwaySpeedLimit).toString());
                mHighwayLimitToggle.performClick();
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mHighwayLimitInc.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                SpeedView speedview = SpeedView.this;
                speedview.mHighwaySpeedLimit = 1 + speedview.mHighwaySpeedLimit;
                mHighwayLimitNumbers.setText((new StringBuilder()).append(mHighwaySpeedLimit).toString());
                mHighwayLimitToggle.performClick();
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mFreewayLimitToggle.setOnLongClickListener(new android.view.View.OnLongClickListener() {

            public boolean onLongClick(View view)
            {
                view.performClick();
                mSwitchboard.setVisibility(4);
                Toast.makeText(getBaseContext(), (new StringBuilder(String.valueOf(getString(2131099830)))).append(" ").append(mSpeedWarning).append(" ").append(SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits]).toString(), 1).show();
                return true;
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mFreewayLimitToggle.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
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
                mSpeedWarning = mFreewaySpeedLimit;
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mFreewayLimitDec.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                SpeedView speedview = SpeedView.this;
                speedview.mFreewaySpeedLimit = -1 + speedview.mFreewaySpeedLimit;
                mFreewayLimitNumbers.setText((new StringBuilder()).append(mFreewaySpeedLimit).toString());
                mFreewayLimitToggle.performClick();
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        mFreewayLimitInc.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                SpeedView speedview = SpeedView.this;
                speedview.mFreewaySpeedLimit = 1 + speedview.mFreewaySpeedLimit;
                mFreewayLimitNumbers.setText((new StringBuilder()).append(mFreewaySpeedLimit).toString());
                mFreewayLimitToggle.performClick();
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        if(mIsTablet)
        {
            mGoogleMapsButtonT.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    Intent intent = new Intent();
                    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    startActivity(intent);
_L1:
                    return;
                    ActivityNotFoundException activitynotfoundexception;
                    activitynotfoundexception;
                    activitynotfoundexception.printStackTrace();
                      goto _L1
                }

                final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
            }
);
            mOpenSpotButtonT.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    if(SpeedView.mOpenSpotInst)
                    {
                        Intent intent = new Intent();
                        intent.setClassName("com.googlelabs.openspot", "com.google.android.apps.openspot.activities.MainActivity");
                        startActivity(intent);
                    } else
                    {
                        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.googlelabs.openspot")));
                    }
                }

                final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
            }
);
            mMaverickButtonT.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    if(SpeedView.mMaverickInst)
                    {
                        Intent intent = new Intent();
                        if(SpeedView.mMaverickVersion == SpeedView.VERSIONS[0])
                            intent.setClassName("com.codesector.maverick.full", "com.codesector.maverick.full.Main");
                        else
                            intent.setClassName("com.codesector.maverick.lite", "com.codesector.maverick.lite.Main");
                        startActivity(intent);
                    } else
                    {
                        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.codesector.maverick.lite")));
                    }
                }

                final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
            }
);
        }
    }

    public void onCreateContextMenu(ContextMenu contextmenu, View view, android.view.ContextMenu.ContextMenuInfo contextmenuinfo)
    {
        super.onCreateContextMenu(contextmenu, view, contextmenuinfo);
        contextmenu.setHeaderTitle(2131099778);
        if(mLogFilesList == null || mLogFilesList.length <= 0) goto _L2; else goto _L1
_L1:
        int i = 0;
_L5:
        if(i < mLogFilesList.length) goto _L3; else goto _L2
_L2:
        return;
_L3:
        contextmenu.add(0, i, 0, mLogFilesList[i]);
        i++;
        if(true) goto _L5; else goto _L4
_L4:
    }

    protected Dialog onCreateDialog(int i)
    {
        i;
        JVM INSTR tableswitch 0 2: default 28
    //                   0 32
    //                   1 208
    //                   2 357;
           goto _L1 _L2 _L3 _L4
_L1:
        final Dialog dialog = null;
_L6:
        return dialog;
_L2:
        dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(2130903045);
        final ScrollView helpView = (ScrollView)dialog.findViewById(2131296278);
        final LinearLayout helpButtonsLayout = (LinearLayout)dialog.findViewById(2131296282);
        Button button4 = (Button)dialog.findViewById(2131296283);
        Button button5 = (Button)dialog.findViewById(2131296284);
        Button button6 = (Button)dialog.findViewById(2131296290);
        final ScrollView faqView = (ScrollView)dialog.findViewById(2131296285);
        final LinearLayout faqButtonLayout = (LinearLayout)dialog.findViewById(2131296289);
        (TextView)dialog.findViewById(2131296288);
        button4.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                dialog.dismiss();
            }

            final SpeedView this$0;
            private final Dialog val$dialog;

            
            {
                this$0 = SpeedView.this;
                dialog = dialog1;
                super();
            }
        }
);
        button5.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                helpView.setVisibility(8);
                helpButtonsLayout.setVisibility(8);
                faqView.setVisibility(0);
                faqButtonLayout.setVisibility(0);
            }

            final SpeedView this$0;
            private final LinearLayout val$faqButtonLayout;
            private final ScrollView val$faqView;
            private final LinearLayout val$helpButtonsLayout;
            private final ScrollView val$helpView;

            
            {
                this$0 = SpeedView.this;
                helpView = scrollview;
                helpButtonsLayout = linearlayout;
                faqView = scrollview1;
                faqButtonLayout = linearlayout1;
                super();
            }
        }
);
        button6.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                faqView.setVisibility(8);
                faqButtonLayout.setVisibility(8);
                helpView.setVisibility(0);
                helpButtonsLayout.setVisibility(0);
            }

            final SpeedView this$0;
            private final LinearLayout val$faqButtonLayout;
            private final ScrollView val$faqView;
            private final LinearLayout val$helpButtonsLayout;
            private final ScrollView val$helpView;

            
            {
                this$0 = SpeedView.this;
                faqView = scrollview;
                faqButtonLayout = linearlayout;
                helpView = scrollview1;
                helpButtonsLayout = linearlayout1;
                super();
            }
        }
);
        continue; /* Loop/switch isn't completed */
_L3:
        dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(2130903040);
        ((TextView)dialog.findViewById(2131296257)).setText((new StringBuilder(String.valueOf(getString(2131099929)))).append(" ").append(mVersionName).append(" ").append(getString(2131099930)).toString());
        Button button2 = (Button)dialog.findViewById(2131296259);
        Button button3 = (Button)dialog.findViewById(2131296260);
        if(!mHasNetworkAccess)
            button3.setEnabled(false);
        button2.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                dialog.dismiss();
            }

            final SpeedView this$0;
            private final Dialog val$dialog;

            
            {
                this$0 = SpeedView.this;
                dialog = dialog1;
                super();
            }
        }
);
        button3.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("plain/text");
                intent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                intent.setData(Uri.parse("speedview@codesector.com"));
                String as[] = new String[1];
                as[0] = "speedview@codesector.com";
                intent.putExtra("android.intent.extra.EMAIL", as);
                intent.putExtra("android.intent.extra.SUBJECT", (new StringBuilder(String.valueOf(getString(2131099648)))).append(" ").append(SpeedView.mVersionName).append(" ").append(getString(2131099930)).toString());
                startActivity(intent);
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        continue; /* Loop/switch isn't completed */
_L4:
        dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(2130903043);
        (TextView)dialog.findViewById(2131296270);
        Button button = (Button)dialog.findViewById(2131296271);
        Button button1 = (Button)dialog.findViewById(2131296272);
        if(!mHasNetworkAccess)
            button1.setEnabled(false);
        button.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                dialog.dismiss();
            }

            final SpeedView this$0;
            private final Dialog val$dialog;

            
            {
                this$0 = SpeedView.this;
                dialog = dialog1;
                super();
            }
        }
);
        button1.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                Uri uri = Uri.parse("http://blog.codesector.com/category/code-sector-software/speedview/");
                startActivity(new Intent("android.intent.action.VIEW", uri));
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
);
        if(true) goto _L6; else goto _L5
_L5:
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(2131230720, menu);
        return super.onCreateOptionsMenu(menu);
    }

    protected void onDestroy()
    {
        super.onDestroy();
        if(mAnalyticsTracker != null)
            mAnalyticsTracker.stopSession();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag = true;
        if(i != 4) goto _L2; else goto _L1
_L1:
        if(mSwitchboard.getVisibility() != 0) goto _L4; else goto _L3
_L3:
        Toast.makeText(getBaseContext(), (new StringBuilder(String.valueOf(getString(2131099830)))).append(" ").append(mSpeedWarning).append(" ").append(UNITS_ARRAY[mDisplayUnits]).toString(), flag).show();
        mSwitchboard.setVisibility(4);
_L6:
        return flag;
_L4:
        if(mFrom0To60Screen.getVisibility() == 0)
        {
            m60MphReached = false;
            mFrom0To60Screen.setVisibility(4);
            continue; /* Loop/switch isn't completed */
        }
        if(mFrom0To100Screen.getVisibility() == 0)
        {
            m100KphReached = false;
            mFrom0To100Screen.setVisibility(4);
            continue; /* Loop/switch isn't completed */
        }
        if(mQuarterMileScreen.getVisibility() == 0)
        {
            mQtrMileReached = false;
            mQuarterMileScreen.setVisibility(4);
            continue; /* Loop/switch isn't completed */
        }
        if(mSelectedDashboard == flag && !mHasGPSFix)
        {
            switchToScreen(0);
            if(mIsTablet && mActionBar != null)
                mTabletHelper.setSelectedNavItem(mActionBar, 0);
            continue; /* Loop/switch isn't completed */
        }
        if(mSelectedDashboard == 2 || mSelectedDashboard == 3 || mSelectedDashboard == 4)
        {
            switchToScreen(flag);
            if(mIsTablet && mActionBar != null)
                mTabletHelper.setSelectedNavItem(mActionBar, flag);
            continue; /* Loop/switch isn't completed */
        }
_L2:
        flag = super.onKeyDown(i, keyevent);
        if(true) goto _L6; else goto _L5
_L5:
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR tableswitch 2131296664 2131296693: default 140
    //                   2131296664 146
    //                   2131296665 158
    //                   2131296666 170
    //                   2131296667 140
    //                   2131296668 183
    //                   2131296669 287
    //                   2131296670 391
    //                   2131296671 495
    //                   2131296672 140
    //                   2131296673 520
    //                   2131296674 528
    //                   2131296675 536
    //                   2131296676 544
    //                   2131296677 140
    //                   2131296678 552
    //                   2131296679 140
    //                   2131296680 694
    //                   2131296681 749
    //                   2131296682 804
    //                   2131296683 859
    //                   2131296684 914
    //                   2131296685 969
    //                   2131296686 981
    //                   2131296687 993
    //                   2131296688 1063
    //                   2131296689 1459
    //                   2131296690 1484
    //                   2131296691 1509
    //                   2131296692 1534
    //                   2131296693 1574;
           goto _L1 _L2 _L3 _L4 _L1 _L5 _L6 _L7 _L8 _L1 _L9 _L10 _L11 _L12 _L1 _L13 _L1 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27
_L1:
        return super.onOptionsItemSelected(menuitem);
_L2:
        setRequestedOrientation(1);
        mStoredOrientation = 0;
        continue; /* Loop/switch isn't completed */
_L3:
        setRequestedOrientation(0);
        mStoredOrientation = 1;
        continue; /* Loop/switch isn't completed */
_L4:
        setRequestedOrientation(8);
        mStoredOrientation = 3;
        continue; /* Loop/switch isn't completed */
_L5:
        mCurrentSpeedLimit = 0;
        mSpeedWarning = mTownSpeedLimit;
        Toast.makeText(getBaseContext(), (new StringBuilder(String.valueOf(getString(2131099830)))).append(" ").append(mSpeedWarning).append(" ").append(UNITS_ARRAY[mDisplayUnits]).toString(), 1).show();
        mTownLimitSign.setAlpha(250);
        mHighwayLimitSign.setAlpha(100);
        mFreewayLimitSign.setAlpha(100);
        continue; /* Loop/switch isn't completed */
_L6:
        mCurrentSpeedLimit = 1;
        mSpeedWarning = mHighwaySpeedLimit;
        Toast.makeText(getBaseContext(), (new StringBuilder(String.valueOf(getString(2131099830)))).append(" ").append(mSpeedWarning).append(" ").append(UNITS_ARRAY[mDisplayUnits]).toString(), 1).show();
        mHighwayLimitSign.setAlpha(250);
        mTownLimitSign.setAlpha(100);
        mFreewayLimitSign.setAlpha(100);
        continue; /* Loop/switch isn't completed */
_L7:
        mCurrentSpeedLimit = 2;
        mSpeedWarning = mFreewaySpeedLimit;
        Toast.makeText(getBaseContext(), (new StringBuilder(String.valueOf(getString(2131099830)))).append(" ").append(mSpeedWarning).append(" ").append(UNITS_ARRAY[mDisplayUnits]).toString(), 1).show();
        mFreewayLimitSign.setAlpha(250);
        mTownLimitSign.setAlpha(100);
        mHighwayLimitSign.setAlpha(100);
        continue; /* Loop/switch isn't completed */
_L8:
        mWarningChecked = false;
        Toast.makeText(getBaseContext(), getString(2131099699), 1).show();
        continue; /* Loop/switch isn't completed */
_L9:
        switchToScreen(0);
        continue; /* Loop/switch isn't completed */
_L10:
        switchToScreen(1);
        continue; /* Loop/switch isn't completed */
_L11:
        switchToScreen(2);
        continue; /* Loop/switch isn't completed */
_L12:
        switchToScreen(3);
        continue; /* Loop/switch isn't completed */
_L13:
        mDisplayUnits;
        JVM INSTR tableswitch 0 2: default 580
    //                   0 592
    //                   1 637
    //                   2 682;
           goto _L28 _L29 _L30 _L31
_L28:
        switchToScreen(4);
        displayStoredData();
        continue; /* Loop/switch isn't completed */
_L29:
        mFrom0To100Row.setVisibility(8);
        mFrom0To60Row.setVisibility(0);
        mFrom0To100Button.setVisibility(8);
        mFrom0To60Button.setVisibility(0);
        mAccelerationLayout.setVisibility(0);
        continue; /* Loop/switch isn't completed */
_L30:
        mFrom0To60Row.setVisibility(8);
        mFrom0To100Row.setVisibility(0);
        mFrom0To60Button.setVisibility(8);
        mFrom0To100Button.setVisibility(0);
        mAccelerationLayout.setVisibility(0);
        continue; /* Loop/switch isn't completed */
_L31:
        mAccelerationLayout.setVisibility(8);
        if(true) goto _L28; else goto _L14
_L14:
        (new android.app.AlertDialog.Builder(this)).setTitle(2131099714).setMessage(2131099715).setPositiveButton(2131099724, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                resetOdometer();
                displayStoredData();
                Toast.makeText(getBaseContext(), 2131099700, 1).show();
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
).setNegativeButton(2131099725, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
).show();
        continue; /* Loop/switch isn't completed */
_L15:
        (new android.app.AlertDialog.Builder(this)).setTitle(2131099716).setMessage(2131099717).setPositiveButton(2131099724, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                resetMaxSpeed();
                Toast.makeText(getBaseContext(), 2131099701, 1).show();
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
).setNegativeButton(2131099725, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
).show();
        continue; /* Loop/switch isn't completed */
_L16:
        (new android.app.AlertDialog.Builder(this)).setTitle(2131099718).setMessage(2131099719).setPositiveButton(2131099724, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                resetAcclTimes();
                displayStoredData();
                Toast.makeText(getBaseContext(), 2131099702, 1).show();
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
).setNegativeButton(2131099725, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
).show();
        continue; /* Loop/switch isn't completed */
_L17:
        (new android.app.AlertDialog.Builder(this)).setTitle(2131099720).setMessage(2131099721).setPositiveButton(2131099724, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                mGraphView.resetHexArray();
                Toast.makeText(getBaseContext(), 2131099703, 1).show();
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
).setNegativeButton(2131099725, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
).show();
        continue; /* Loop/switch isn't completed */
_L18:
        (new android.app.AlertDialog.Builder(this)).setTitle(2131099722).setMessage(2131099723).setPositiveButton(2131099724, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                resetOdometer();
                resetMaxSpeed();
                resetAcclTimes();
                mGraphView.resetHexArray();
                displayStoredData();
                Toast.makeText(getBaseContext(), 2131099704, 1).show();
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
).setNegativeButton(2131099725, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
            }

            final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
        }
).show();
        continue; /* Loop/switch isn't completed */
_L19:
        mMinimizeButtonPressed = true;
        finish();
        continue; /* Loop/switch isn't completed */
_L20:
        mExitButtonPressed = true;
        finish();
        continue; /* Loop/switch isn't completed */
_L21:
        Intent intent1 = new Intent(this, com/codesector/speedview/pro/ShareActivity);
        intent1.putExtra("last_location", mLastLocation);
        intent1.putExtra("address_string", mAddressString);
        startActivity(intent1);
        mShareButtonPressed = true;
        if(mAnalyticsTracker != null)
            mAnalyticsTracker.trackPageView("/shareLocation");
        continue; /* Loop/switch isn't completed */
_L22:
        Intent intent = new Intent(this, com/codesector/speedview/pro/SettingsActivity);
        intent.putExtra("display_units", mDisplayUnits);
        intent.putExtra("warning_checked", mWarningChecked);
        intent.putExtra("current_speed_limit", mCurrentSpeedLimit);
        intent.putExtra("town_speed_limit", mTownSpeedLimit);
        intent.putExtra("highway_speed_limit", mHighwaySpeedLimit);
        intent.putExtra("freeway_speed_limit", mFreewaySpeedLimit);
        intent.putExtra("sound_alert_toggled", mSoundAlertToggled);
        intent.putExtra("alert_sound_uri", mAlertSoundUri);
        intent.putExtra("vibration_checked", mVibrationChecked);
        intent.putExtra("digit_speedo_checked", mDigitSpeedoChecked);
        intent.putExtra("digit_addl_data_toggled", mDigitAddlDataToggled);
        intent.putExtra("digit_data_selected", mDigitDataSelected);
        intent.putExtra("max_speedo_checked", mMaxSpeedoChecked);
        intent.putExtra("max_speedo_limit", mMaxSpeedoLimit);
        intent.putExtra("use_hud_checked", mUseHudChecked);
        intent.putExtra("advanced_hud_checked", mAdvancedHudChecked);
        intent.putExtra("advanced_zoom_checked", mAdvancedZoomChecked);
        intent.putExtra("custom_colors_checked", mCustomColorsChecked);
        intent.putExtra("speed_bar_color", mSpeedBarColor);
        intent.putExtra("primary_text_color", mPrimaryTextColor);
        intent.putExtra("secondary_text_color", mSecondaryTextColor);
        intent.putExtra("run_in_bg_checked", mRunInBGChecked);
        intent.putExtra("track_logging_checked", mTrackLoggingChecked);
        intent.putExtra("min_time_between_pts", mMinTimeBetweenPts);
        intent.putExtra("min_dist_between_pts", mMinDistBetweenPts);
        intent.putExtra("narrowing_checked", mNarrowingChecked);
        intent.putExtra("minimum_accuracy", mMinimumAccuracy);
        intent.putExtra("street_addr_checked", mStreetAddrChecked);
        intent.putExtra("dsbl_rotation_checked", mDsblRotationChecked);
        intent.putExtra("full_screen_checked", mFullScreenChecked);
        intent.putExtra("background_checked", mBackgroundChecked);
        startActivity(intent);
        mSettingsButtonPressed = true;
        if(mAnalyticsTracker != null)
            mAnalyticsTracker.trackPageView("/settings");
        continue; /* Loop/switch isn't completed */
_L23:
        showDialog(0);
        if(mAnalyticsTracker != null)
            mAnalyticsTracker.trackPageView("/help");
        continue; /* Loop/switch isn't completed */
_L24:
        showDialog(1);
        if(mAnalyticsTracker != null)
            mAnalyticsTracker.trackPageView("/about");
        continue; /* Loop/switch isn't completed */
_L25:
        showDialog(2);
        if(mAnalyticsTracker != null)
            mAnalyticsTracker.trackPageView("/changelog");
        continue; /* Loop/switch isn't completed */
_L26:
        startActivity(new Intent(this, com/codesector/speedview/pro/FeaturedActivity));
        mFeaturedButtonPressed = true;
        if(mAnalyticsTracker != null)
            mAnalyticsTracker.trackPageView("/featuredApps");
        continue; /* Loop/switch isn't completed */
_L27:
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.codesector.speedview.pro")));
        if(mAnalyticsTracker != null)
            mAnalyticsTracker.trackPageView("/upgradeToPro");
        if(true) goto _L1; else goto _L32
_L32:
    }

    public void onOptionsMenuClosed(Menu menu)
    {
        super.onOptionsMenuClosed(menu);
    }

    protected void onPause()
    {
        super.onPause();
        mLocationManager.removeUpdates(mLocationListener);
        mLocationManager.removeGpsStatusListener(mGPSListener);
        mSensorManager.unregisterListener(mOrientationListener);
        saveUserPreferences();
        if(mTrackLoggingChecked)
            saveCurrentTrack();
        if((mRunInBGChecked && !mExitButtonPressed && !mShareButtonPressed && !mSettingsButtonPressed && !mFeaturedButtonPressed || mMinimizeButtonPressed) && mIsGPSEnabled && mIsRecording)
        {
            Intent intent = new Intent(this, com/codesector/speedview/pro/BackgroundService);
            intent.putExtra("distance", mStoredDistance);
            intent.putExtra("max_speed", mStoredMaxSpeed);
            intent.putExtra("moving_time", mStoredMovingTime + mSessionMovingTime);
            intent.putExtra("total_time", mStoredTotalTime + mSessionTotalTime);
            intent.putExtra("display_units", mDisplayUnits);
            intent.putExtra("warning_checked", mWarningChecked);
            intent.putExtra("speed_warning", mSpeedWarning);
            intent.putExtra("sould_alert_toggled", mSoundAlertToggled);
            if(mWarningChecked && mSoundAlertToggled && mWarningSound != null)
            {
                String s;
                if(mAlertSoundUri == null)
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

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        boolean flag = false;
        if(mFrom0To60Screen.getVisibility() != 0 && mFrom0To100Screen.getVisibility() != 0 && mQuarterMileScreen.getVisibility() != 0 && mSwitchboard.getVisibility() != 0) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(!mDsblRotationChecked) goto _L4; else goto _L3
_L3:
        menu.findItem(2131296663).setVisible(true);
        if(Integer.parseInt(android.os.Build.VERSION.SDK) < 9)
            menu.findItem(2131296666).setVisible(false);
        mStoredOrientation;
        JVM INSTR tableswitch 0 3: default 128
    //                   0 942
    //                   1 961
    //                   2 128
    //                   3 980;
           goto _L5 _L6 _L7 _L5 _L8
_L5:
        menu.findItem(2131296667).setVisible(mWarningChecked);
        if(mIsTablet && mActionBar != null)
            menu.findItem(2131296672).setVisible(false);
        if(!mWarningChecked) goto _L10; else goto _L9
_L9:
        menu.findItem(2131296668).setTitle((new StringBuilder(String.valueOf(getString(2131099673)))).append(" (").append(mTownSpeedLimit).append(" ").append(UNITS_ARRAY[mDisplayUnits]).append(")").toString());
        menu.findItem(2131296669).setTitle((new StringBuilder(String.valueOf(getString(2131099674)))).append(" (").append(mHighwaySpeedLimit).append(" ").append(UNITS_ARRAY[mDisplayUnits]).append(")").toString());
        menu.findItem(2131296670).setTitle((new StringBuilder(String.valueOf(getString(2131099675)))).append(" (").append(mFreewaySpeedLimit).append(" ").append(UNITS_ARRAY[mDisplayUnits]).append(")").toString());
        mCurrentSpeedLimit;
        JVM INSTR tableswitch 0 2: default 420
    //                   0 1018
    //                   1 1037
    //                   2 1056;
           goto _L10 _L11 _L12 _L13
_L10:
        mSelectedDashboard;
        JVM INSTR tableswitch 0 4: default 456
    //                   0 1075
    //                   1 1094
    //                   2 1113
    //                   3 1132
    //                   4 1151;
           goto _L14 _L15 _L16 _L17 _L18 _L19
_L14:
        MenuItem menuitem = menu.findItem(2131296673);
        boolean flag1;
        MenuItem menuitem1;
        int i;
        boolean flag2;
        int j;
        MenuItem menuitem2;
        boolean flag3;
        MenuItem menuitem3;
        boolean flag4;
        MenuItem menuitem4;
        boolean flag5;
        MenuItem menuitem5;
        boolean flag6;
        if(mHasGPSFix)
            flag1 = false;
        else
            flag1 = true;
        menuitem.setVisible(flag1);
        menuitem1 = menu.findItem(2131296676);
        if(mUseHudChecked)
            i = 2131099681;
        else
            i = 2131099682;
        menuitem1.setTitle(i);
        flag2 = false;
        j = 0;
        if(mStoredDistance != 0.0F || mStoredMovingTime != 0L || mStoredTotalTime != 0L)
        {
            menu.findItem(2131296680).setVisible(true);
            flag2 = true;
            j = 0 + 1;
        } else
        {
            menu.findItem(2131296680).setVisible(false);
        }
        if(mStoredMaxSpeed != 0.0F)
        {
            menu.findItem(2131296681).setVisible(true);
            flag2 = true;
            j++;
        } else
        {
            menu.findItem(2131296681).setVisible(false);
        }
        if(!mStored0To60Time.equals(getString(2131099663)) || !mStored0To100Time.equals(getString(2131099663)) || !mStoredQtrMileTime.equals(getString(2131099663)))
        {
            menu.findItem(2131296682).setVisible(true);
            flag2 = true;
            j++;
        } else
        {
            menu.findItem(2131296682).setVisible(false);
        }
        if(!mGraphView.isHexArrayEmpty())
        {
            menu.findItem(2131296683).setVisible(true);
            flag2 = true;
            j++;
        } else
        {
            menu.findItem(2131296683).setVisible(false);
        }
        menu.findItem(2131296679).setEnabled(flag2);
        menuitem2 = menu.findItem(2131296684);
        if(j > 1)
            flag3 = true;
        else
            flag3 = false;
        menuitem2.setVisible(flag3);
        menuitem3 = menu.findItem(2131296685);
        if(mIsGPSEnabled && !mRunInBGChecked)
            flag4 = true;
        else
            flag4 = false;
        menuitem3.setVisible(flag4).setEnabled(mIsRecording);
        menuitem4 = menu.findItem(2131296686);
        if(mIsGPSEnabled && mRunInBGChecked)
            flag5 = true;
        else
            flag5 = false;
        menuitem4.setVisible(flag5);
        menuitem5 = menu.findItem(2131296687);
        if(mHasNetworkAccess && mHasGPSFix)
            flag6 = true;
        else
            flag6 = false;
        menuitem5.setEnabled(flag6);
        menu.findItem(2131296692).setVisible(true).setEnabled(mHasNetworkAccess);
        menu.findItem(2131296693).setVisible(false).setEnabled(mHasNetworkAccess);
        flag = super.onPrepareOptionsMenu(menu);
          goto _L1
_L6:
        menu.findItem(2131296664).setChecked(true);
          goto _L5
_L7:
        menu.findItem(2131296665).setChecked(true);
          goto _L5
_L8:
        menu.findItem(2131296666).setChecked(true);
          goto _L5
_L4:
        menu.findItem(2131296663).setVisible(false);
          goto _L5
_L11:
        menu.findItem(2131296668).setChecked(true);
          goto _L10
_L12:
        menu.findItem(2131296669).setChecked(true);
          goto _L10
_L13:
        menu.findItem(2131296670).setChecked(true);
          goto _L10
_L15:
        menu.findItem(2131296673).setChecked(true);
          goto _L14
_L16:
        menu.findItem(2131296674).setChecked(true);
          goto _L14
_L17:
        menu.findItem(2131296675).setChecked(true);
          goto _L14
_L18:
        menu.findItem(2131296676).setChecked(true);
          goto _L14
_L19:
        menu.findItem(2131296678).setChecked(true);
          goto _L14
    }

    protected void onResume()
    {
        Iterator iterator;
        Iterator iterator1;
        super.onResume();
        SharedPreferences sharedpreferences = getSharedPreferences("PrefsFile", 0);
        mNotifiedAboutScreen = sharedpreferences.getBoolean("notifiedAboutScreen", false);
        if(!mIsScreenSupported && !mNotifiedAboutScreen)
        {
            (new android.app.AlertDialog.Builder(this)).setTitle(2131099707).setMessage(2131099708).setPositiveButton(2131099711, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                }

                final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
            }
).setNegativeButton(2131099691, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    finish();
                }

                final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
            }
).show();
            mNotifiedAboutScreen = true;
        }
        mNotifiedAboutGPS = sharedpreferences.getBoolean("notifiedAboutGPS", false);
        checkGPSEnabled();
        mNotifiedAboutNetwork = sharedpreferences.getBoolean("notifiedAboutNetwork", false);
        mHasNetworkAccess = isNetworkAvailable();
        mCurrentVersion = sharedpreferences.getInt("currentVersion", 0);
        if(mCurrentVersion < mVersionCode)
            (new android.app.AlertDialog.Builder(this)).setTitle(2131099946).setMessage(2131099947).setNeutralButton(2131099857, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                }

                final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
            }
).show();
        if(mCurrentVersion == 0)
        {
            final Dialog dialog = new Dialog(this, 16973834);
            dialog.setContentView(2130903049);
            TextView textview = (TextView)dialog.findViewById(2131296647);
            (TextView)dialog.findViewById(2131296649);
            textview.setText((new StringBuilder(String.valueOf(getString(2131099929)))).append(" ").append(mVersionName).append(" ").append(getString(2131099930)).toString());
            Button button = (Button)dialog.findViewById(2131296650);
            Button button1 = (Button)dialog.findViewById(2131296651);
            if(!mHasNetworkAccess)
                button1.setEnabled(false);
            button.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    dialog.dismiss();
                }

                final SpeedView this$0;
                private final Dialog val$dialog;

            
            {
                this$0 = SpeedView.this;
                dialog = dialog1;
                super();
            }
            }
);
            button1.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    Uri uri1 = Uri.parse("http://www.codesector.com/speedview");
                    startActivity(new Intent("android.intent.action.VIEW", uri1));
                }

                final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
            }
);
            dialog.show();
        }
        String s;
        Uri uri;
        ActivityManager activitymanager;
        if(Integer.parseInt(android.os.Build.VERSION.SDK) >= 5)
            if(hasMatchingActivity("com.googlelabs.openspot", "com.google.android.apps.openspot.activities.MainActivity"))
            {
                mOpenSpotInst = true;
                mOpenSpotButton.setEnabled(true);
                if(mIsTablet)
                    mOpenSpotButtonT.setEnabled(true);
            } else
            {
                mOpenSpotInst = false;
                if(mHasNetworkAccess)
                {
                    mOpenSpotButton.setEnabled(true);
                    mOpenSpotButton.setTextColor(-7829368);
                    if(mIsTablet)
                    {
                        mOpenSpotButtonT.setEnabled(true);
                        mOpenSpotButtonT.setTextColor(-7829368);
                    }
                }
            }
        if(hasMatchingActivity("com.codesector.maverick.full", "com.codesector.maverick.full.Main"))
        {
            mMaverickInst = true;
            mMaverickVersion = VERSIONS[0];
            mMaverickButton.setEnabled(true);
            if(mIsTablet)
                mMaverickButtonT.setEnabled(true);
        } else
        if(hasMatchingActivity("com.codesector.maverick.lite", "com.codesector.maverick.lite.Main"))
        {
            mMaverickInst = true;
            mMaverickVersion = VERSIONS[1];
            mMaverickButton.setEnabled(true);
            if(mIsTablet)
                mMaverickButtonT.setEnabled(true);
        } else
        {
            mMaverickInst = false;
            if(mHasNetworkAccess)
            {
                mMaverickButton.setEnabled(true);
                mMaverickButton.setTextColor(-7829368);
                if(mIsTablet)
                {
                    mMaverickButtonT.setEnabled(true);
                    mMaverickButtonT.setTextColor(-7829368);
                }
            }
        }
        if(mMaverickInst)
            mGPXFileLocation = "/maverick/tracks";
        else
            mGPXFileLocation = "/speedview/tracks";
        mIsRecording = sharedpreferences.getBoolean("isRecording", true);
        if(mIsGPSEnabled && !mIsRecording)
        {
            mSatelliteView.clearSatellites();
            mStatusMessage.setText(2131099664);
            mNumOfSatellites.setText(2131099665);
            mTipMessage.setText(2131099666);
            mAddressLine0.setText(2131099664);
            mAddressLine1.setText(2131099665);
        }
        mStoredDistance = sharedpreferences.getFloat("storedDistance", 0.0F);
        mStoredMaxSpeed = sharedpreferences.getFloat("storedMaxSpeed", 0.0F);
        mStoredMovingTime = sharedpreferences.getLong("storedMovingTime", 0L);
        mStoredTotalTime = sharedpreferences.getLong("storedTotalTime", 0L);
        mStored0To60Time = sharedpreferences.getString("stored0To60Time", getString(2131099663));
        mStored0To100Time = sharedpreferences.getString("stored0To100Time", getString(2131099663));
        mStoredQtrMileTime = sharedpreferences.getString("storedQtrMileTime", getString(2131099663));
        mDisplayUnits = sharedpreferences.getInt("displayUnits", 0);
        mWarningChecked = sharedpreferences.getBoolean("warningChecked", false);
        mCurrentSpeedLimit = sharedpreferences.getInt("currentSpeedLimit", 0);
        mTownSpeedLimit = sharedpreferences.getInt("townSpeedLimit", 30);
        mHighwaySpeedLimit = sharedpreferences.getInt("highwaySpeedLimit", 55);
        mFreewaySpeedLimit = sharedpreferences.getInt("freewaySpeedLimit", 65);
        mSoundAlertToggled = sharedpreferences.getBoolean("soundAlertToggled", false);
        s = sharedpreferences.getString("alertSoundUri", "");
        if(s.equals(""))
            uri = null;
        else
            uri = Uri.parse(s);
        mAlertSoundUri = uri;
        if(mAlertSoundUri != null)
            mWarningSound = RingtoneManager.getRingtone(getBaseContext(), mAlertSoundUri);
        mVibrationChecked = sharedpreferences.getBoolean("vibrationChecked", false);
        mDigitSpeedoChecked = sharedpreferences.getBoolean("digitSpeedoChecked", false);
        mDigitAddlDataToggled = sharedpreferences.getBoolean("digitAddlDataToggled", false);
        mDigitDataSelected = sharedpreferences.getInt("digitDataSelected", -1);
        mMaxSpeedoChecked = sharedpreferences.getBoolean("maxSpeedoChecked", false);
        mMaxSpeedoLimit = sharedpreferences.getInt("maxSpeedoLimit", 160);
        mUseHudChecked = sharedpreferences.getBoolean("useHudChecked", false);
        mAdvancedHudChecked = sharedpreferences.getBoolean("advancedHudChecked", false);
        mAdvancedZoomChecked = sharedpreferences.getBoolean("advancedZoomChecked", false);
        mCustomColorsChecked = sharedpreferences.getBoolean("customColorsChecked", false);
        mSpeedBarColor = sharedpreferences.getInt("speedBarColor", -16776961);
        mPrimaryTextColor = sharedpreferences.getInt("primaryTextColor", -1);
        mSecondaryTextColor = sharedpreferences.getInt("secondaryTextColor", -3355444);
        mRunInBGChecked = sharedpreferences.getBoolean("runInBGChecked", false);
        mTrackLoggingChecked = sharedpreferences.getBoolean("trackLoggingChecked", false);
        mMinTimeBetweenPts = sharedpreferences.getInt("minTimeBetweenPts", 0);
        mMinDistBetweenPts = sharedpreferences.getInt("minDistBetweenPts", 4);
        mNarrowingChecked = sharedpreferences.getBoolean("narrowingChecked", true);
        mMinimumAccuracy = sharedpreferences.getInt("minimumAccuracy", 4);
        mStreetAddrChecked = sharedpreferences.getBoolean("streetAddrChecked", true);
        if(mIsTablet)
            mDsblRotationChecked = false;
        else
            mDsblRotationChecked = sharedpreferences.getBoolean("dsblRotationChecked", true);
        mFullScreenChecked = sharedpreferences.getBoolean("fullScreenChecked", true);
        mBackgroundChecked = sharedpreferences.getBoolean("backgroundChecked", true);
        if(!mFullScreenChecked)
        {
            if(mDsblRotationChecked && (mStoredOrientation == 1 || mStoredOrientation == 3) || !mDsblRotationChecked && getResources().getConfiguration().orientation == 2)
                setFullScreenMode(true);
            else
                setFullScreenMode(false);
        } else
        {
            setFullScreenMode(true);
        }
        if(mBackgroundChecked)
        {
            mSpeedView.setBackgroundResource(2130837508);
            mStartupScreen.setBackgroundResource(2130837508);
        } else
        {
            mSpeedView.setBackgroundColor(-16777216);
            mStartupScreen.setBackgroundColor(-16777216);
        }
        if(mIsTablet)
            mTabletHelper.onResumeActivity(mActionBar);
        if(!mWarningChecked) goto _L2; else goto _L1
_L1:
        mCurrentSpeedLimit;
        JVM INSTR tableswitch 0 2: default 1264
    //                   0 2111
    //                   1 2206
    //                   2 2301;
           goto _L3 _L4 _L5 _L6
_L3:
        if(mTownSpeedLimit >= 100)
            mTownLimitNumbers.setTextSize(48F);
        else
            mTownLimitNumbers.setTextSize(60F);
        mTownLimitNumbers.setText((new StringBuilder()).append(mTownSpeedLimit).toString());
        if(mHighwaySpeedLimit >= 100)
            mHighwayLimitNumbers.setTextSize(48F);
        else
            mHighwayLimitNumbers.setTextSize(60F);
        mHighwayLimitNumbers.setText((new StringBuilder()).append(mHighwaySpeedLimit).toString());
        if(mFreewaySpeedLimit >= 100)
            mFreewayLimitNumbers.setTextSize(48F);
        else
            mFreewayLimitNumbers.setTextSize(60F);
        mFreewayLimitNumbers.setText((new StringBuilder()).append(mFreewaySpeedLimit).toString());
_L2:
        mSpeedometerView.setDisplayUnits(mDisplayUnits);
        if(mCustomColorsChecked)
        {
            mOdometer.setTextColor(mSecondaryTextColor);
            mMaxSpeed.setTextColor(mSecondaryTextColor);
            if(mStoredOrientation != 0 && mHeading != null)
                mHeading.setTextColor(mSecondaryTextColor);
            mCompassSpeed.setTextColor(mSecondaryTextColor);
            mCompassOdometer.setTextColor(mSecondaryTextColor);
            mCompassElevation.setTextColor(mSecondaryTextColor);
            mCompassTime.setTextColor(mSecondaryTextColor);
        } else
        {
            mOdometer.setTextColor(-3355444);
            mMaxSpeed.setTextColor(-3355444);
            if(mStoredOrientation != 0 && mHeading != null)
                mHeading.setTextColor(-3355444);
            mCompassSpeed.setTextColor(-3355444);
            mCompassOdometer.setTextColor(-3355444);
            mCompassElevation.setTextColor(-3355444);
            mCompassTime.setTextColor(-3355444);
        }
        if(mDigitSpeedoChecked && mDigitAddlDataToggled)
            mSpeedometerView.refreshView();
        mGraphView.mArrayPointer = sharedpreferences.getInt("graphArrayPointer", 0);
        mGraphView.setHexArray(sharedpreferences.getString("graphHexString", ""));
        if(mSelectedDashboard != 4) goto _L8; else goto _L7
_L7:
        mDisplayUnits;
        JVM INSTR tableswitch 0 2: default 1584
    //                   0 2521
    //                   1 2566
    //                   2 2611;
           goto _L8 _L9 _L10 _L11
_L8:
        if(!mIsRecording)
        {
            mRecordingStatus.setText(2131099767);
            mRecordingButton.setText(2131099766);
        } else
        {
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
        if(!mStored0To60Time.equals(getString(2131099663)) || !mStored0To100Time.equals(getString(2131099663)) || !mStoredQtrMileTime.equals(getString(2131099663)))
            mAccelerationInfo.setText(2131099759);
        if(mTrackLoggingChecked)
        {
            mGPXExportStatus.setText(2131099770);
            mExportGPXButton.setEnabled(true);
            if(mHasNetworkAccess)
                mSendGPXButton.setEnabled(true);
        } else
        {
            mGPXExportStatus.setText(2131099769);
            mExportGPXButton.setEnabled(false);
            mSendGPXButton.setEnabled(false);
        }
        mSensorManager.registerListener(mOrientationListener, mSensorManager.getDefaultSensor(3), 2);
        mSessionStartTime = System.nanoTime();
        mFirstFixTime = 0L;
        mShareButtonPressed = false;
        mSettingsButtonPressed = false;
        mFeaturedButtonPressed = false;
        activitymanager = (ActivityManager)getSystemService("activity");
        iterator = activitymanager.getRunningServices(2147483647).iterator();
_L20:
        if(iterator.hasNext()) goto _L13; else goto _L12
_L12:
        iterator1 = activitymanager.getRunningServices(2147483647).iterator();
_L21:
        if(iterator1.hasNext()) goto _L15; else goto _L14
_L14:
        displayStoredData();
        if(!mDsblRotationChecked)
            break MISSING_BLOCK_LABEL_2816;
        mStoredOrientation = sharedpreferences.getInt("storedOrientation", 0);
        mStoredOrientation;
        JVM INSTR tableswitch 0 3: default 1876
    //                   0 2791
    //                   1 2799
    //                   2 1876
    //                   3 2807;
           goto _L16 _L17 _L18 _L16 _L19
_L16:
        return;
_L4:
        mSpeedWarning = mTownSpeedLimit;
        mTownLimitSign.setAlpha(250);
        mHighwayLimitSign.setAlpha(100);
        mFreewayLimitSign.setAlpha(100);
        mTownLimitDec.setAlpha(250);
        mTownLimitInc.setAlpha(250);
        mHighwayLimitDec.setAlpha(100);
        mHighwayLimitInc.setAlpha(100);
        mFreewayLimitDec.setAlpha(100);
        mFreewayLimitInc.setAlpha(100);
          goto _L3
_L5:
        mSpeedWarning = mHighwaySpeedLimit;
        mHighwayLimitSign.setAlpha(250);
        mTownLimitSign.setAlpha(100);
        mFreewayLimitSign.setAlpha(100);
        mHighwayLimitDec.setAlpha(250);
        mHighwayLimitInc.setAlpha(250);
        mTownLimitDec.setAlpha(100);
        mTownLimitInc.setAlpha(100);
        mFreewayLimitDec.setAlpha(100);
        mFreewayLimitInc.setAlpha(100);
          goto _L3
_L6:
        mSpeedWarning = mFreewaySpeedLimit;
        mFreewayLimitSign.setAlpha(250);
        mTownLimitSign.setAlpha(100);
        mHighwayLimitSign.setAlpha(100);
        mFreewayLimitDec.setAlpha(250);
        mFreewayLimitInc.setAlpha(250);
        mTownLimitDec.setAlpha(100);
        mTownLimitInc.setAlpha(100);
        mHighwayLimitDec.setAlpha(100);
        mHighwayLimitInc.setAlpha(100);
          goto _L3
_L9:
        mFrom0To100Row.setVisibility(8);
        mFrom0To60Row.setVisibility(0);
        mFrom0To100Button.setVisibility(8);
        mFrom0To60Button.setVisibility(0);
        mAccelerationLayout.setVisibility(0);
          goto _L8
_L10:
        mFrom0To60Row.setVisibility(8);
        mFrom0To100Row.setVisibility(0);
        mFrom0To60Button.setVisibility(8);
        mFrom0To100Button.setVisibility(0);
        mAccelerationLayout.setVisibility(0);
          goto _L8
_L11:
        mAccelerationLayout.setVisibility(8);
          goto _L8
_L13:
        if("com.codesector.speedview.pro.BackgroundService".equals(((android.app.ActivityManager.RunningServiceInfo)iterator.next()).service.getClassName()))
            bindService(new Intent(this, com/codesector/speedview/pro/BackgroundService), mBackgroundConn, 0);
          goto _L20
_L15:
        if("com.codesector.speedview.pro.UpdateWidgetService".equals(((android.app.ActivityManager.RunningServiceInfo)iterator1.next()).service.getClassName()))
            bindService(new Intent(this, com/codesector/speedview/pro/UpdateWidgetService), mUpdateWidgetConn, 0);
          goto _L21
_L17:
        setRequestedOrientation(1);
          goto _L16
_L18:
        setRequestedOrientation(0);
          goto _L16
_L19:
        setRequestedOrientation(8);
          goto _L16
        setRequestedOrientation(4);
        if(Integer.parseInt(android.os.Build.VERSION.SDK) >= 9)
            mStoredOrientation = DisplayHelper.getRotation(this);
        else
        if(getResources().getConfiguration().orientation == 1)
            mStoredOrientation = 0;
        else
            mStoredOrientation = 1;
          goto _L16
    }

    protected void onStart()
    {
        super.onStart();
    }

    protected void onStop()
    {
        super.onStop();
    }

    public void refreshMainScreen()
    {
        if((!mDsblRotationChecked || mStoredOrientation != 1 && mStoredOrientation != 3) && (mDsblRotationChecked || getResources().getConfiguration().orientation != 2)) goto _L2; else goto _L1
_L1:
        mDisplayUnits;
        JVM INSTR tableswitch 0 2: default 68
    //                   0 69
    //                   1 97
    //                   2 125;
           goto _L2 _L3 _L4 _L5
_L2:
        return;
_L3:
        mFrom0To100RowT.setVisibility(8);
        mFrom0To60RowT.setVisibility(0);
        mAccelerationLayoutT.setVisibility(0);
        continue; /* Loop/switch isn't completed */
_L4:
        mFrom0To60RowT.setVisibility(8);
        mFrom0To100RowT.setVisibility(0);
        mAccelerationLayoutT.setVisibility(0);
        continue; /* Loop/switch isn't completed */
_L5:
        mAccelerationLayoutT.setVisibility(8);
        if(true) goto _L2; else goto _L6
_L6:
    }

    public void selectTab(int i)
    {
        if(mTabletHelper.getNavItemCount(mActionBar) == 4)
            i++;
        i;
        JVM INSTR tableswitch 0 4: default 52
    //                   0 53
    //                   1 61
    //                   2 69
    //                   3 77
    //                   4 85;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return;
_L2:
        switchToScreen(0);
        continue; /* Loop/switch isn't completed */
_L3:
        switchToScreen(1);
        continue; /* Loop/switch isn't completed */
_L4:
        switchToScreen(2);
        continue; /* Loop/switch isn't completed */
_L5:
        switchToScreen(3);
        continue; /* Loop/switch isn't completed */
_L6:
        mDisplayUnits;
        JVM INSTR tableswitch 0 2: default 116
    //                   0 128
    //                   1 173
    //                   2 218;
           goto _L7 _L8 _L9 _L10
_L10:
        break MISSING_BLOCK_LABEL_218;
_L7:
        break; /* Loop/switch isn't completed */
_L8:
        break; /* Loop/switch isn't completed */
_L12:
        switchToScreen(4);
        displayStoredData();
        if(true) goto _L1; else goto _L11
_L11:
        mFrom0To100Row.setVisibility(8);
        mFrom0To60Row.setVisibility(0);
        mFrom0To100Button.setVisibility(8);
        mFrom0To60Button.setVisibility(0);
        mAccelerationLayout.setVisibility(0);
          goto _L12
_L9:
        mFrom0To60Row.setVisibility(8);
        mFrom0To100Row.setVisibility(0);
        mFrom0To60Button.setVisibility(8);
        mFrom0To100Button.setVisibility(0);
        mAccelerationLayout.setVisibility(0);
          goto _L12
        mAccelerationLayout.setVisibility(8);
          goto _L12
    }

    private static final int ABOUT_DIALOG_ID = 1;
    static final int ACCURACY = 3;
    static final float ACCURACY_VALUES[];
    private static final int ADMOB_INTERVAL = 30;
    static final int ADVANCED = 4;
    static final int AVERAGE_SPEED = 1;
    private static final int CHANGELOG_DIALOG_ID = 2;
    static final int COMPASS = 2;
    private static final String COMPASS_DIRECTIONS[];
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
    static final int MIN_DISTANCE_VALUES[];
    static final int MIN_TIME_VALUES[];
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
    static final int TRIP_TIME;
    static final String UNITS_ARRAY[];
    static final int URBAN_AREA;
    static final String VERSIONS[];
    static final boolean VODAFONE_SHOP;
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
    static boolean mIsTablet;
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
    private ActionBar mActionBar;
    private AdView mAdViewMain;
    private AdView mAdViewStartup;
    private Address mAddress;
    final Runnable mAddressFound = new Runnable() {

        public void run()
        {
            if(mAddress != null)
            {
                String s = mAddress.getAddressLine(0);
                String s1 = mAddress.getAddressLine(1);
                String s2 = mAddress.getAddressLine(2);
                SpeedView speedview = SpeedView.this;
                String s3;
                TextView textview;
                if(s != null)
                    s3 = s;
                else
                    s3 = "";
                speedview.mAddressString = s3;
                if(s1 != null)
                {
                    SpeedView speedview1 = SpeedView.this;
                    speedview1.mAddressString = (new StringBuilder(String.valueOf(speedview1.mAddressString))).append(", ").append(s1).toString();
                    if(s2 != null)
                    {
                        s1 = (new StringBuilder(String.valueOf(s1))).append(", ").append(s2).toString();
                        SpeedView speedview2 = SpeedView.this;
                        speedview2.mAddressString = (new StringBuilder(String.valueOf(speedview2.mAddressString))).append(", ").append(s2).toString();
                    }
                } else
                {
                    s1 = "";
                }
                textview = mAddressLine0;
                if(s == null)
                    s = "";
                textview.setText(s);
                mAddressLine1.setText(s1);
            }
        }

        final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
    }
;
    private TextView mAddressLine0;
    private TextView mAddressLine1;
    private String mAddressString;
    private RelativeLayout mAddressView;
    private LinearLayout mAdvancedScreen;
    private Uri mAlertSoundUri;
    private GoogleAnalyticsTracker mAnalyticsTracker;
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
    final Runnable mGPSIsDisabled = new Runnable() {

        public void run()
        {
            mAddressLine0.setText(2131099734);
            mAddressLine1.setText(2131099736);
        }

        final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
    }
;
    private MyGPSListener mGPSListener;
    private TextView mGPXExportStatus;
    private Geocoder mGeocoder;
    private Button mGoogleMapsButton;
    private Button mGoogleMapsButtonT;
    private GraphView mGraphView;
    final Handler mHandler = new Handler();
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
    final Runnable mNetworkFailure = new Runnable() {

        public void run()
        {
            mAddressLine0.setText(2131099732);
            mAddressLine1.setText(2131099733);
        }

        final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
    }
;
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
    private TabletHelper mTabletHelper;
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
    final Runnable mUnableToGetAddress = new Runnable() {

        public void run()
        {
            mAddressLine0.setText(2131099730);
            mAddressLine1.setText(2131099731);
        }

        final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
    }
;
    private ServiceConnection mUpdateWidgetConn;
    private ImageView mUpgradeBanner;
    private boolean mVehicleIsMoving;
    private boolean mVibrationChecked;
    private Vibrator mVibrator;
    private ViewStub mViewStub;
    private Ringtone mWarningSound;
    final Handler progressHandler = new Handler() {

        public void handleMessage(Message message)
        {
            mProgressDialog.dismiss();
            message.what;
            JVM INSTR tableswitch 0 2: default 40
        //                       0 41
        //                       1 82
        //                       2 259;
               goto _L1 _L2 _L3 _L4
_L1:
            return;
_L2:
            (new android.app.AlertDialog.Builder(SpeedView.this)).setTitle(2131099776).setMessage(2131099777).setNeutralButton(2131099939, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                }

                final _cls4 this$1;

                    
                    {
                        this$1 = _cls4.this;
                        super();
                    }
            }
).show();
            continue; /* Loop/switch isn't completed */
_L3:
            if(mSendTrackInit)
            {
                String s = message.getData().getString("file_path");
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("application/gpx");
                intent.putExtra("android.intent.extra.SUBJECT", getString(2131099780));
                intent.putExtra("android.intent.extra.TEXT", getString(2131099781));
                intent.putExtra("android.intent.extra.STREAM", Uri.parse((new StringBuilder("file://")).append(s).toString()));
                startActivity(Intent.createChooser(intent, getString(2131099773)));
            } else
            {
                mGPXExportStatus.setText((new StringBuilder(String.valueOf(getString(2131099771)))).append(SpeedView.mGPXFileLocation).append(getString(2131099772)).toString());
            }
            continue; /* Loop/switch isn't completed */
_L4:
            (new android.app.AlertDialog.Builder(SpeedView.this)).setTitle(2131099726).setMessage(2131099727).setNeutralButton(2131099939, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                }

                final _cls4 this$1;

                    
                    {
                        this$1 = _cls4.this;
                        super();
                    }
            }
).show();
            if(true) goto _L1; else goto _L5
_L5:
        }

        final SpeedView this$0;

            
            {
                this$0 = SpeedView.this;
                super();
            }
    }
;

    static 
    {
        String as[] = new String[2];
        as[0] = "Pro";
        as[1] = "Lite";
        VERSIONS = as;
        String as1[] = new String[3];
        as1[0] = "mph";
        as1[1] = "km/h";
        as1[2] = "knots";
        UNITS_ARRAY = as1;
        float af[] = new float[9];
        af[0] = 10F;
        af[1] = 20F;
        af[2] = 50F;
        af[3] = 100F;
        af[4] = 200F;
        af[5] = 500F;
        af[6] = 1000F;
        af[7] = 2000F;
        af[8] = 5000F;
        ACCURACY_VALUES = af;
        int ai[] = new int[14];
        ai[0] = 1;
        ai[1] = 2;
        ai[2] = 3;
        ai[3] = 4;
        ai[4] = 5;
        ai[5] = 10;
        ai[6] = 20;
        ai[7] = 30;
        ai[8] = 60;
        ai[9] = 120;
        ai[10] = 300;
        ai[11] = 600;
        ai[12] = 900;
        ai[13] = 1800;
        MIN_TIME_VALUES = ai;
        int ai1[] = new int[7];
        ai1[0] = 1;
        ai1[1] = 2;
        ai1[2] = 3;
        ai1[3] = 4;
        ai1[4] = 5;
        ai1[5] = 10;
        ai1[6] = 100;
        MIN_DISTANCE_VALUES = ai1;
        String as2[] = new String[9];
        as2[0] = "N";
        as2[1] = "NE";
        as2[2] = "E";
        as2[3] = "SE";
        as2[4] = "S";
        as2[5] = "SW";
        as2[6] = "W";
        as2[7] = "NW";
        as2[8] = "N";
        COMPASS_DIRECTIONS = as2;
    }

















































































































































































}


/*
	DECOMPILATION REPORT

	Decompiled from: H:\AndroidProject\SpeedGps\libs\GPS测速.jar
	Total time: 557 ms
	Jad reported messages/errors:
Couldn't fully decompile method onGpsStatusChanged
Couldn't fully decompile method onLocationChanged
Couldn't fully decompile method onSensorChanged
Couldn't fully decompile method distanceToString
Couldn't fully decompile method getDisplaySpeed
Couldn't resolve all exception handlers in method refreshAdView
Couldn't fully decompile method saveCurrentTrack
Couldn't resolve all exception handlers in method saveCurrentTrack
Couldn't fully decompile method switchToScreen
Couldn't fully decompile method onClick
Couldn't fully decompile method onClick
Couldn't resolve all exception handlers in method onClick
Couldn't fully decompile method onClick
Couldn't resolve all exception handlers in method onClick
Couldn't fully decompile method onCreateContextMenu
Couldn't fully decompile method onCreateDialog
Couldn't fully decompile method onKeyDown
Couldn't fully decompile method onOptionsItemSelected
Couldn't fully decompile method onPrepareOptionsMenu
Couldn't fully decompile method onResume
Couldn't fully decompile method refreshMainScreen
Couldn't fully decompile method selectTab
Couldn't fully decompile method handleMessage
	Exit status: 0
	Caught exceptions:
*/