package com.test;

import java.text.SimpleDateFormat;

import android.app.ActionBar;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TextView;

import com.codesector.speedview.pro.INSTR;
import com.codesector.speedview.pro.SpeedView;
import com.codesector.speedview.pro.TabletHelper;
import com.codesector.speedview.pro._L1;
import com.codesector.speedview.pro._L3;
import com.codesector.speedview.pro.SpeedView.MyLocationListener;
import com.google.ads.c;

public class test {

    private class MyLocationListener
    implements LocationListener
{

    public void onLocationChanged(Location location)
    {
        float f;
        double d;
        double d1;
        float f2;
        double d2;
        float f3;
        long l1;
        if(location == null)
            return;
        if(SpeedView.mSelectedDashboard == 0)
        {
            switchToScreen(1);
        }
        mLastLocationTime = SystemClock.elapsedRealtime();
        if((!mNarrowingChecked || location.getSpeed() >= 66.7F) && mNarrowingChecked)
            return;
        float f1 = location.getSpeed();
        mSpeed = getDisplaySpeed(f1);
        d = location.getLatitude();
        d1 = location.getLongitude();
        mAccuracy = (int)location.getAccuracy();
        d2 = location.getAltitude();
        f3 = location.getBearing();
        l1 = location.getTime();
        SpeedView speedview2;
        Location location1;
        TextView textview;
        StringBuilder stringbuilder;
        int i1;
        String s;
        SpeedView speedview3;
        Location location2;
        TextView textview1;
        StringBuilder stringbuilder1;
        int k1;
        String s1;
        SpeedView speedview4;
        Location location3;
        TextView textview2;
        StringBuilder stringbuilder2;
        Location location4;
        int i2;
        String s2;
        SpeedView speedview5;
        Location location5;
        if(SpeedView.mSelectedDashboard == 4 && f == 0F)
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
        if(mFrom0To60Screen.getVisibility() != 0 && mFrom0To100Screen.getVisibility() != 0 && mQuarterMileScreen.getVisibility() != 0) goto _L2; else goto _L1
_L1:
        if(mFrom0To60Screen.getVisibility() == 0)
        {
            int k = (int)((double)f * 2.2400000000000002D);
            if(mLastLocation.getSpeed() == 0F && f > 0F && !m60MphReached)
            {
                mAcclStartLocation = mLastLocation;
                mFrom0To60Speed.setText(k + "");
                mFrom0To60Info.setText(2131099791);
                mFrom0To60Info.setTextColor(-65536);
                m60MphReached = 0;
            } else
            if(f != 0F && f <= 26.8224F && !m60MphReached)
            {
                long l2 = mAcclStartLocation.getTime();
                long l3 = l1 - l2;
                int j2 = (int)(l3 % 1000L);
                int k2 = (int)(l3 / 1000L);
                StringBuilder stringbuilder3 = (new StringBuilder(String.valueOf(k2))).append(".").append(j2).append(" ");
                mFrom0To60String = stringbuilder3.append(getString(2131099789)).toString();
                mFrom0To60Time.setText(mFrom0To60String);
                StringBuilder stringbuilder4 = new StringBuilder();
                int i3 = (int)(location.distanceTo(mAcclStartLocation) * 3.2808F);
                String s7 = stringbuilder4.append(i3).append(" ft").toString();
                mFrom0To60Feet.setText(s7);
                TextView textview5 = mFrom0To60Speed;
                StringBuilder stringbuilder5 = new StringBuilder();
                int j3 = k;
                String s8 = stringbuilder5.append(j3).toString();
                textview5.setText(s8);
                mConfirm0To60Button.setEnabled(false);
                mDiscard0To60Button.setEnabled(false);
            } else
            if(f > 26.8224F && !m60MphReached)
            {
                mFrom0To60Speed.setTextColor(-16776961);
                StringBuilder stringbuilder6 = new StringBuilder();
                stringbuilder6.append(k).toString();
                mFrom0To60Speed.setText(stringbuilder6.toString());
                mFrom0To60Info.setTextColor(-3355444);
                StringBuilder stringbuilder7 = (new StringBuilder(String.valueOf(getString(2131099792)))).append(" ");
                stringbuilder7.append(mFrom0To60String).toString();
                mFrom0To60Info.setText(stringbuilder7.toString());
                m60MphReached = 1;
              	mTemp0To60Time = mFrom0To60String;
                mConfirm0To60Button.setEnabled(true);
                mDiscard0To60Button.setEnabled(true);
            }
        }
        if(mFrom0To100Screen.getVisibility() == 0)
        {
            int j1 = (int)((double)f * 3.6000000000000001D);
            if(mLastLocation.getSpeed() == 0F && f > 0F && !m100KphReached)
            {
                mAcclStartLocation = mLastLocation;
                stringbuilder1 = new StringBuilder();
                s1 = stringbuilder1.append(j1).toString();
                mFrom0To100Speed.setText(s1);
                mFrom0To100Info.setText(2131099791);
                mFrom0To100Info.setTextColor(-65536);
                m100KphReached = 0;
            } else
            if(f != 0F && f <= 27.78F && !m100KphReached)
            {
                long l4 = mAcclStartLocation.getTime();
                long l5 = l1 - l4;
                int i4 = (int)(l5 % 1000L);
                int j4 = (int)(l5 / 1000L);
                StringBuilder stringbuilder8 = (new StringBuilder(String.valueOf(j4))).append(".").append(i4).append(" ");
                mFrom0To100String = stringbuilder8.append(getString(2131099789)).toString();
                mFrom0To100Time.setText(mFrom0To100String);
                StringBuilder stringbuilder9 = new StringBuilder();
                int k4 = (int)location.distanceTo(mAcclStartLocation);
                stringbuilder9.append(k4).append(" m").toString();
                mFrom0To100Meters.setText(stringbuilder9.toString());
                StringBuilder stringbuilder10 = new StringBuilder();
                stringbuilder10.append(j1).toString();
                mFrom0To100Speed.setText(stringbuilder10.toString());
                mConfirm0To100Button.setEnabled(false);
                mDiscard0To100Button.setEnabled(false);
            } else
            if(f > 27.78F && !m100KphReached)
            {
                mFrom0To100Speed.setTextColor(-16776961);
                StringBuilder stringbuilder11 = new StringBuilder();
                stringbuilder11.append(j1).toString();
                mFrom0To100Speed.setText(stringbuilder11.toString());
                mFrom0To100Info.setTextColor(-3355444);
                StringBuilder stringbuilder12 = (new StringBuilder(String.valueOf(getString(2131099797)))).append(" ");
                String s23 = stringbuilder12.append(mFrom0To100String).toString();
                mFrom0To100Info.setText(s23);
                m100KphReached = 1;
                mTemp0To100Time = mFrom0To100String;
                mConfirm0To100Button.setEnabled(true);
                mDiscard0To100Button.setEnabled(true);
            }
        }
        if(mQuarterMileScreen.getVisibility() == 0)
            if(mLastLocation.getSpeed() == 0F && f > 0F && !mQtrMileReached)
            {
                mAcclStartLocation = mLastLocation;
                if(SpeedView.mDisplayUnits == 1)
                {
                    stringbuilder2 = new StringBuilder();
                    i2 = (int)location.distanceTo(mAcclStartLocation);
                    s2 = stringbuilder2.append(i2).toString();
                    mQuarterMileDist.setText(s2);
                } else
                {
                    StringBuilder stringbuilder13 = new StringBuilder();
                    int k5 = (int)(location.distanceTo(mAcclStartLocation) * 3.2808F);
                    stringbuilder13.append(k5).toString();
                    mQuarterMileDist.setText(stringbuilder13.toString());
                }
                mQuarterMileInfo.setText(2131099791);
                mQuarterMileInfo.setTextColor(-65536);
                mQtrMileReached = 0;
            } else
            {
label0:
                {
                    if(mAcclStartLocation == null)
                        break label0;
                    if(location.distanceTo(mAcclStartLocation) > 402.336F || mQtrMileReached)
                        break label0;
                    long l7 = l1 - mAcclStartLocation.getTime();
                    int i6 = (int)(l7 % 1000L);
                    int j6 = (int)(l7 / 1000L);
                    StringBuilder stringbuilder14 = (new StringBuilder(String.valueOf(j6))).append(".").append(i6).append(" ");
                    mQtrMileString = stringbuilder14.append(getString(2131099789)).toString();
                    mQuarterMileTime.setText(mQtrMileString);
                    StringBuilder stringbuilder15 = (new StringBuilder(String.valueOf(mSpeed))).append(" ");
                    String s31 = SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits];
                    String s32 = stringbuilder15.append(s31).toString();
                    mQuarterMileSpeed.setText(s32);
                    if(SpeedView.mDisplayUnits == 1)
                    {
                        StringBuilder stringbuilder16 = new StringBuilder();
                        int i7 = (int)location.distanceTo(mAcclStartLocation);
                        stringbuilder16.append(i7).toString();
                        mQuarterMileDist.setText(stringbuilder16.toString());
                    } else
                    {
                        StringBuilder stringbuilder17 = new StringBuilder();
                        int j7 = (int)(location.distanceTo(mAcclStartLocation) * 3.2808F);
                        stringbuilder17.append(j7).toString();
                        mQuarterMileDist.setText(stringbuilder17.toString());
                    }
                    mConfirmQtrButton.setEnabled(false);
                    mDiscardQtrButton.setEnabled(false);
                }
            }
_L3:
	mLastLocation = location;
        return;
        if(mAcclStartLocation != null)
        {
            if(location.distanceTo(mAcclStartLocation) > 402.336F && !mQtrMileReached)
            {
                mQuarterMileDist.setTextColor(-16776961);
                TextView textview19;
                String s36;
                StringBuilder stringbuilder19;
                String s37;
                String s38;
                SpeedView speedview11;
                String s39;
                if(SpeedView.mDisplayUnits == 1)
                {
                    StringBuilder stringbuilder18 = new StringBuilder();
                    stringbuilder18.append((int)location.distanceTo(mAcclStartLocation)).toString();
                    mQuarterMileDist.setText(stringbuilder18.toString());
                } else
                {
                    StringBuilder stringbuilder20 = new StringBuilder();
                    int i8 = (int)(location.distanceTo(mAcclStartLocation) * 3.2808F);
                    String s40 = stringbuilder20.append(i8).toString();
                    mQuarterMileDist.setText(s40);
                }
                mQuarterMileInfo.setTextColor(-3355444);
                stringbuilder19 = (new StringBuilder(String.valueOf(getString(2131099802)))).append(" ");
                mQuarterMileInfo.setText(stringbuilder19.append(mQtrMileString).toString());
                mQtrMileReached = 1;
                mTempQtrMileTime = mQtrMileString;
                mConfirmQtrButton.setEnabled(true);
                mDiscardQtrButton.setEnabled(true);
            }
        }
          goto _L3
_L2:
        if(f2 >= SpeedView.ACCURACY_VALUES[mMinimumAccuracy])
            break MISSING_BLOCK_LABEL_5665;
        mGraphView.setVisibility(0);
        mAccuracyNotification.setVisibility(8);
        String s41 = String.valueOf((int)f3);
        StringBuilder stringbuilder21 = (new StringBuilder(s41)).append("\260 ");
        int k8 = (int)(((double)f3 + 22.5D) / 45D);
        String s42 = SpeedView.COMPASS_DIRECTIONS[k8];
        mHeadingString = stringbuilder21.append(s42).toString();
        SpeedView.mDisplayUnits;
        JVM INSTR tableswitch 0 2: default 2748
    //                       0 4198
    //                       1 4247
    //                       2 4198;
           goto _L4 _L5 _L6 _L5
_L4:
        if(SpeedView.mSelectedDashboard == 2)
        {
            if(f > 1.4F)
            {
                mCompassMode.onLocationChanged(location, mSpeed, mSpeedWarning);
                mCompassSource.setText(2131099748);
            }
            StringBuilder stringbuilder22 = (new StringBuilder(String.valueOf(mSpeed))).append(" ");
            stringbuilder22.append(SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits]).toString();
            mCompassSpeed.setText(stringbuilder22.toString());
            mCompassOdometer.setText(distanceToString());
            mCompassElevation.setText(mAltitudeString);
            SimpleDateFormat simpledateformat;
            TextView textview24;
            Long long1;
            String s49;
            float f5;
            SpeedView speedview12;
            float f6;
            HudMode hudmode;
            int k9;
            int l9;
            String s50;
            String s51;
            String s52;
            GraphView graphview;
            int i10;
            float f7;
            Location location16;
            float f8;
            float f9;
            long l10;
            long l11;
            long l12;
            long l13;
            long l14;
            int j10;
            int k10;
            Ringtone ringtone;
            Context context;
            int i11;
            int j11;
            int k11;
            int i12;
            Location location17;
            float f10;
            float f11;
            Location location18;
            Location location19;
            long l15;
            long l16;
            int ai[];
            int k12;
            long l17;
            Location location20;
            float f12;
            int ai1[];
            int i13;
            float f13;
            StringBuilder stringbuilder23;
            DecimalFormat decimalformat;
            double d3;
            String s53;
            StringBuilder stringbuilder24;
            DecimalFormat decimalformat1;
            double d4;
            String s54;
            StringBuilder stringbuilder25;
            long l18;
            StringBuilder stringbuilder26;
            float f14;
            StringBuilder stringbuilder27;
            int j13;
            StringBuilder stringbuilder28;
            SpeedView speedview19;
            Location location21;
            long l19;
            long l20;
            String s55;
            String s56;
            String s57;
            String s58;
            if(!DateFormat.is24HourFormat(getApplicationContext()))
            {
                simpledateformat = new SimpleDateFormat("h:mm a");
            } else
            {
                simpledateformat = new SimpleDateFormat("HH:mm")
            }
            mCompassTime.setText(simpledateformat.format(Long.valueOf(l1)));
        } else
        if(SpeedView.mStoredOrientation == 0)
        {
            if(f > 1.4F)
            {
                mCompassView.onSpeedChanged(f3);
            }
        } else
        if(mHeading != null)
        {
            mHeading.setText(mHeadingString);
        }
        if(f > mStoredMaxSpeed)
        {
        	mStoredMaxSpeed = f;
        }
        if(!SpeedView.mDigitSpeedoChecked || !SpeedView.mDigitAddlDataToggled) goto _L8; else goto _L7
_L7:
        SpeedView.mDigitDataSelected;
        JVM INSTR tableswitch 0 4: default 3072
    //                       0 4397
    //                       1 4530
    //                       2 4796
    //                       3 4871
    //                       4 4973;
           goto _L9 _L10 _L11 _L12 _L13 _L14
_L9:
        if(SpeedView.mSelectedDashboard == 3)
            if(SpeedView.mAdvancedHudChecked || SpeedView.mAdvancedZoomChecked)
            {
                mHudMode.onSpeedChanged(mSpeed, mSpeedWarning, distanceToString(), mHeadingString, mAltitudeString);
            } else
            {
                mHudMode.onSpeedChanged(mSpeed, mSpeedWarning);
            }
        mGraphView.onSpeedChanged(mSpeed);
        if(mFirstFixTime != 0L)
        {
            if(mLastLocation != null && f != 0F)
            {
            	mStoredDistance = mStoredDistance + location.distanceTo(mLastLocation);
            }
            SimpleDateFormat simpledateformat1;
            SpeedView speedview23;
            long l21;
            long l22;
            long l23;
            String s60;
            SpeedometerView speedometerview;
            int k13;
            int i14;
            float f16;
            String s61;
            float f17;
            long l24;
            long l25;
            float f18;
            long l26;
            long l27;
            float f19;
            int j14;
            int k14;
            float f20;
            StringBuilder stringbuilder29;
            int i15;
            StringBuilder stringbuilder30;
            String as3[];
            int j15;
            String s62;
            String s63;
            int k15;
            int i16;
            float f21;
            String s64;
            int j16;
            int k16;
            float f22;
            String s65;
            String s66;
            int i17;
            int j17;
            float f23;
            Long long2;
            String s67;
            int k17;
            int i18;
            float f24;
            if(f > 0F)
            {
                if(!mVehicleIsMoving)
                {
                    mVehicleIsMoving = 1;
                    mStateChangedTime = l1;
                }
                mSessionMovingTime = l1 - mStateChangedTime;
            } else
            if(mVehicleIsMoving)
            {
                mVehicleIsMoving = 0;
                mStoredMovingTime = mStoredMovingTime + l1 - mStateChangedTime;
                mSessionMovingTime = 0L;
                mStateChangedTime = l1;
            }
            mSessionTotalTime = l1 - mFirstFixTime;
        }
        displayStoredData();
        if(!SpeedView.mWarningChecked) goto _L16; else goto _L15
_L15:
        if(mSpeed <= mSpeedWarning || !mSoundAlertToggled) goto _L18; else goto _L17
_L17:
        if(mWarningSound == null) goto _L20; else goto _L19
_L19:
        if(mWarningSound.getTitle(getBaseContext()).equals("Unknown ringtone")) goto _L20; else goto _L21
_L21:
        if(!mWarningSound.isPlaying())
            mWarningSound.play();
_L22:
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
        {
            if(mSpeedWarning <= mSpeed && mLimitFlag)
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
_L16:
        if(SpeedView.mStreetAddrChecked && SpeedView.mHasNetworkAccess)
        {
            if(mLastAddress != null)
            {
                f10 = location.distanceTo(mLastAddress);
                if(f > 15.6F)
                	f11 = 1000;
                else
                	f11 = 100;
                if(f10 > f11)
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
                l16 = l1 - mLastTrackLocation.getTime();
                l17 = SpeedView.MIN_TIME_VALUES[mMinTimeBetweenPts] * 1000;
                if(l16 > l17)
                {
                    f12 = location.distanceTo(mLastTrackLocation);
                    f13 = SpeedView.MIN_DISTANCE_VALUES[mMinDistBetweenPts];
                    if(f12 > f13)
                    {
                        stringbuilder24 = mTrackBuffer.append(mCoordFormat.format(d)).append("|");
                        stringbuilder24.append(mCoordFormat.format(d1)).append("|");
                        stringbuilder24.append(l1 / 1000L - 1280000000L).append("|");
                        stringbuilder24.append(f).append("|");
                        stringbuilder24.append((int)d2).append("\n");
                        if(mTrackBuffer.length() > 2000)
                            saveCurrentTrack();
                        mLastTrackLocation = location;
                    }
                }
            } else
            {
            	mLastTrackLocation = location;
            }
_L23:
        if(mFirstFixTime == 0L)
        {
            mFirstFixTime = l1;
            boolean flag;
            String s68;
            StringBuilder stringbuilder31;
            int j20;
            String s69;
            if(f > 0F)
                flag = true;
            else
                flag = false;
            mVehicleIsMoving = flag;
            mStateChangedTime = mFirstFixTime;
        }
          goto _L3
_L5:
          mAltitudeString = (new StringBuilder(String.valueOf((int)(d2 / 0.30480000000000002D)))).append(" ft \u2191").toString();
          goto _L4
_L6:
		mAltitudeString = (new StringBuilder(String.valueOf((int)d2))).append(" m \u2191").toString();
          goto _L4
_L10:
        mSpeedometerView.onSpeedChanged(mSpeed, mSpeedWarning, mStoredMaxSpeed, getElapsedTimeString(mStoredTotalTime + mSessionTotalTime));
          goto _L9
_L11:
        f17 = 0F;
        if(mStoredTotalTime + mSessionTotalTime != 0L)
        {
            f19 = (float)(mStoredTotalTime + mSessionTotalTime) / 1000F;
            f17 = mStoredDistance / f19;
        }
        stringbuilder29 = new StringBuilder();
        stringbuilder30 = stringbuilder29.append(getDisplaySpeed(f17)).append(" ");
        stringbuilder30.append(SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits]).toString();
        mSpeedometerView.onSpeedChanged(mSpeed, mSpeedWarning, mStoredMaxSpeed, stringbuilder30.toString());
          goto _L9
_L12:
        mSpeedometerView.onSpeedChanged(mSpeed, mSpeedWarning, mStoredMaxSpeed, mAltitudeString);
          goto _L9
_L13:
        mSpeedometerView.onSpeedChanged(mSpeed, mSpeedWarning, mStoredMaxSpeed, (new StringBuilder(String.valueOf(mAccuracy))).append(" m").toString());
          goto _L9
_L14:
        if(!DateFormat.is24HourFormat(getApplicationContext()))
        {
            simpledateformat1 = new SimpleDateFormat("h:mm a");
        } else
        {
            simpledateformat1 = new SimpleDateFormat("HH:mm");
        }
        mSpeedometerView.onSpeedChanged(mSpeed, mSpeedWarning, mStoredMaxSpeed, simpledateformat1.format(Long.valueOf(l1)));
          goto _L9
_L8:
          mSpeedometerView.onSpeedChanged(mSpeed, mSpeedWarning, mStoredMaxSpeed);
          goto _L9
_L20:
        Toast.makeText(getBaseContext(), 2131099705, 1).show();
          goto _L22
_L18:
        if(mWarningSound != null && mWarningSound.isPlaying())
            mWarningSound.stop();
          goto _L22
        mAccuracyNotification.setVisibility(0);
        mGraphView.setVisibility(8);
        stringbuilder31 = (new StringBuilder(String.valueOf(getString(2131099661)))).append(" (");
        stringbuilder31.append(mAccuracy).append(" m)").toString();
        mLowAccuracy.setText(stringbuilder31.toString());
          goto _L23
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

    private MyLocationListener()
    {
        super();
        mLimitFlag = false;
    }

    MyLocationListener(MyLocationListener mylocationlistener)
    {
        this();
    }
}



}
