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

import java.io.*;
import java.text.*;
import java.util.*;

public class SpeedView extends Activity {

	private class MyGPSListener implements android.location.GpsStatus.Listener {
		private GpsStatus gpsStatus;
		private int mSecondsElapsed;
		private int k = 0;
		private Iterator iterator;
		private GpsSatellite gpssatellite;
		private int i1 = 0;

		public void onGpsStatusChanged(int i) {
			mSecondsElapsed = (int) ((System.nanoTime() - mSessionStartTime) / 1000000000L);
			switch (i) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				SpeedView.mHasGPSFix = true;
				break;
			case 4:
				GpsStatus gpsstatus;
				if (gpsStatus != null)
					gpsstatus = gpsStatus;
				else
					gpsstatus = null;
				gpsStatus = mLocationManager.getGpsStatus(gpsstatus);
				mSatelliteView.setSatellites(gpsStatus);
				iterator = gpsStatus.getSatellites().iterator();
				int i1 = 0;

				if (SpeedView.mSelectedDashboard != 0) {
					switch (SpeedView.mSelectedDashboard) {
					case 1:
						if (SpeedView.mStoredOrientation != 0
								&& mHeading != null)
							mHeading.setText(2131099663);
						mSpeedometerView.onSpeedChanged(-1, mSpeedWarning,
								mStoredMaxSpeed);
						if (SpeedView.mDigitSpeedoChecked
								&& SpeedView.mDigitAddlDataToggled)
							mSpeedometerView.refreshView();
						if (SpeedView.mCustomColorsChecked) {
							mMaxSpeed
									.setTextColor(SpeedView.mSecondaryTextColor);
						} else {
							mMaxSpeed.setTextColor(-3355444);
						}
						break;
					case 2:
						mCompassSpeed.setText(getBaseContext().getResources()
								.getString(2131099743));
						mCompassOdometer.setText(getBaseContext()
								.getResources().getString(2131099744));
						mCompassElevation.setText(getBaseContext()
								.getResources().getString(2131099745));
						mCompassTime.setText(getBaseContext().getResources()
								.getString(2131099746));
						if (SpeedView.mCustomColorsChecked) {
							mCompassSpeed
									.setTextColor(SpeedView.mSecondaryTextColor);
						} else {
							mCompassSpeed.setTextColor(-3355444);
						}
						// goto _L6
						break;
					case 3:
						mHudMode.onSpeedChanged(-1, mSpeedWarning);
						break;
					}
					if (k < 4)
						mAddressLine0.setText(2131099651);
					else
						mAddressLine0.setText(2131099653);
					StringBuilder stringbuilder4 = (new StringBuilder(
							String.valueOf(k))).append(" ");
					String s13;
					if (k != 1)
						s13 = getString(2131099654);
					else
						s13 = getString(2131099655);
					stringbuilder4.append(s13).toString();
					mAddressLine1.setText(stringbuilder4.toString());
					mSignalStrength.setImageResource(2130837517);
					StringBuilder stringbuilder5 = (new StringBuilder(
							String.valueOf(i1))).append(" ");
					stringbuilder5.append(getString(2131099741)).toString();
					mNumberOfSats.setText(stringbuilder5.toString());
					if (mAccuracyNotification.getVisibility() == 0) {
						mGraphView.setVisibility(0);
						mAccuracyNotification.setVisibility(8);
					}
					mLastAddress = null;
					return;

				} else {
					if (k < 4)
						mStatusMessage.setText(2131099651);
					else
						mStatusMessage.setText(2131099653);
					StringBuilder stringbuilder3 = (new StringBuilder(
							String.valueOf(k))).append(" ");
					String s10;
					if (k != 1)
						s10 = getString(2131099654);
					else
						s10 = getString(2131099655);
					stringbuilder3.append(s10).toString();
					mNumOfSatellites.setText(stringbuilder3.toString());
					if (k < 4) {
						if (mSecondsElapsed < 60) {
							mTipMessage.setText(2131099656);
						} else {
							String s18;
							if (k != 0)
								s18 = getString(2131099658);
							else
								s18 = getString(2131099659);
							StringBuilder stringbuilder6 = new StringBuilder(
									String.valueOf(s18));
							stringbuilder6.append(" ");
							stringbuilder6.append(getString(2131099660))
									.toString();
							mTipMessage.setText(stringbuilder6.toString());
						}
					} else {
						mTipMessage.setText(2131099657);
					}
				}
				break;
			}

			if (!iterator.hasNext()) {
				if (mLastLocation != null) {
					if (SystemClock.elapsedRealtime() - mLastLocationTime < 10000L)
						SpeedView.mHasGPSFix = true;
					else
						SpeedView.mHasGPSFix = false;
				}
				if (SpeedView.mHasGPSFix) {
					if (SpeedView.mSelectedDashboard == 1)
						if (SpeedView.mStreetAddrChecked
								&& SpeedView.mHasNetworkAccess) {
							StringBuilder stringbuilder = (new StringBuilder(
									String.valueOf(i1))).append(" ");
							int j1;
							if (k != 1)
								j1 = 2131099739;
							else
								j1 = 2131099740;
							stringbuilder.append(getString(j1)).toString();
							mNumberOfSats.setText(stringbuilder.toString());
						} else {
							StringBuilder stringbuilder1 = (new StringBuilder(
									String.valueOf(k))).append(" ");
							String s4;
							StringBuilder stringbuilder2;
							if (k != 1)
								s4 = getString(2131099654);
							else
								s4 = getString(2131099655);
							stringbuilder1.append(s4).toString();
							mAddressLine1.setText(stringbuilder1.toString());
							stringbuilder2 = (new StringBuilder(
									String.valueOf(i1))).append(" ");
							stringbuilder2.append(getString(2131099741))
									.toString();
							mNumberOfSats.setText(stringbuilder2.toString());
						}
				}
			} else {
				gpssatellite = (GpsSatellite) iterator.next();
				k++;
				if (gpssatellite.usedInFix())
					i1++;
			}
		}

	}

	private class MyLocationListener implements LocationListener {
		private int mAccuracy;
		private String mAltitudeString;
		private String mHeadingString;
		private boolean mLimitFlag;
		private int mSpeed;
		private float f;

		public void onLocationChanged(Location location) {
			if (location == null)
				return;
			if (SpeedView.mSelectedDashboard == 0) {
				switchToScreen(1);
			}
			SystemClock.elapsedRealtime();
			if ((!mNarrowingChecked || location.getSpeed() >= 66.7F)
					&& mNarrowingChecked)
				return;
			float f1 = location.getSpeed();
			mSpeed = getDisplaySpeed(f1);
			double d = location.getLatitude();
			double d1 = location.getLongitude();
			double f2 = (int) location.getAccuracy();
			double d2 = location.getAltitude();
			double f3 = location.getBearing();
			double l1 = location.getTime();
			if (SpeedView.mSelectedDashboard == 4 && f == 0F) {
				mFrom0To60Button.setEnabled(true);
				mFrom0To100Button.setEnabled(true);
				mQuarterMileButton.setEnabled(true);
			} else {
				mFrom0To60Button.setEnabled(false);
				mFrom0To100Button.setEnabled(false);
				mQuarterMileButton.setEnabled(false);
			}
			if (mFrom0To60Screen.getVisibility() != 0
					&& mFrom0To100Screen.getVisibility() != 0
					&& mQuarterMileScreen.getVisibility() != 0) {
				if (f2 >= SpeedView.ACCURACY_VALUES[mMinimumAccuracy])
					return;
				mGraphView.setVisibility(0);
				mAccuracyNotification.setVisibility(8);
				StringBuilder stringbuilder21 = (new StringBuilder(
						String.valueOf((int) f3))).append("\260 ");
				int k8 = (int) (((double) f3 + 22.5D) / 45D);
				mHeadingString = stringbuilder21.append(
						SpeedView.COMPASS_DIRECTIONS[k8]).toString();
			} else {
				if (mFrom0To60Screen.getVisibility() == 0) {
					int k = (int) ((double) f * 2.2400000000000002D);
					if (mLastLocation.getSpeed() == 0F && f > 0F
							&& !m60MphReached) {
						mAcclStartLocation = mLastLocation;
						mFrom0To60Speed.setText(k + "");
						mFrom0To60Info.setText(2131099791);
						mFrom0To60Info.setTextColor(-65536);
						m60MphReached = false;
					} else if (f != 0F && f <= 26.8224F && !m60MphReached) {
						long l3 = (long) (l1 - mAcclStartLocation.getTime());
						int j2 = (int) (l3 % 1000L);
						int k2 = (int) (l3 / 1000L);
						StringBuilder stringbuilder3 = (new StringBuilder(
								String.valueOf(k2))).append(".").append(j2)
								.append(" ");
						mFrom0To60String = stringbuilder3.append(
								getString(2131099789)).toString();
						mFrom0To60Time.setText(mFrom0To60String);
						StringBuilder stringbuilder4 = new StringBuilder();
						int i3 = (int) (location.distanceTo(mAcclStartLocation) * 3.2808F);
						String s7 = stringbuilder4.append(i3).append(" ft")
								.toString();
						mFrom0To60Feet.setText(s7);
						StringBuilder stringbuilder5 = new StringBuilder();
						stringbuilder5.append(k).toString();
						mFrom0To60Speed.setText(stringbuilder5.toString());
						mConfirm0To60Button.setEnabled(false);
						mDiscard0To60Button.setEnabled(false);
					} else if (f > 26.8224F && !m60MphReached) {
						mFrom0To60Speed.setTextColor(-16776961);
						StringBuilder stringbuilder6 = new StringBuilder();
						stringbuilder6.append(k).toString();
						mFrom0To60Speed.setText(stringbuilder6.toString());
						mFrom0To60Info.setTextColor(-3355444);
						StringBuilder stringbuilder7 = (new StringBuilder(
								String.valueOf(getString(2131099792))))
								.append(" ");
						stringbuilder7.append(mFrom0To60String).toString();
						mFrom0To60Info.setText(stringbuilder7.toString());
						m60MphReached = true;
						mTemp0To60Time = mFrom0To60String;
						mConfirm0To60Button.setEnabled(true);
						mDiscard0To60Button.setEnabled(true);
					}
				}

				if (mFrom0To100Screen.getVisibility() == 0) {
					int j1 = (int) ((double) f * 3.6000000000000001D);
					if (mLastLocation.getSpeed() == 0F && f > 0F
							&& !m100KphReached) {
						mAcclStartLocation = mLastLocation;
						StringBuilder stringbuilder1 = new StringBuilder();
						stringbuilder1.append(j1).toString();
						mFrom0To100Speed.setText(stringbuilder1.toString());
						mFrom0To100Info.setText(2131099791);
						mFrom0To100Info.setTextColor(-65536);
						m100KphReached = false;
					} else if (f != 0F && f <= 27.78F && !m100KphReached) {
						long l4 = mAcclStartLocation.getTime();
						long l5 = (long) (l1 - l4);
						int i4 = (int) (l5 % 1000L);
						int j4 = (int) (l5 / 1000L);
						StringBuilder stringbuilder8 = (new StringBuilder(
								String.valueOf(j4))).append(".").append(i4)
								.append(" ");
						mFrom0To100String = stringbuilder8.append(
								getString(2131099789)).toString();
						mFrom0To100Time.setText(mFrom0To100String);
						StringBuilder stringbuilder9 = new StringBuilder();
						int k4 = (int) location.distanceTo(mAcclStartLocation);
						stringbuilder9.append(k4).append(" m").toString();
						mFrom0To100Meters.setText(stringbuilder9.toString());
						StringBuilder stringbuilder10 = new StringBuilder();
						stringbuilder10.append(j1).toString();
						mFrom0To100Speed.setText(stringbuilder10.toString());
						mConfirm0To100Button.setEnabled(false);
						mDiscard0To100Button.setEnabled(false);
					} else if (f > 27.78F && !m100KphReached) {
						mFrom0To100Speed.setTextColor(-16776961);
						StringBuilder stringbuilder11 = new StringBuilder();
						stringbuilder11.append(j1).toString();
						mFrom0To100Speed.setText(stringbuilder11.toString());
						mFrom0To100Info.setTextColor(-3355444);
						StringBuilder stringbuilder12 = (new StringBuilder(
								String.valueOf(getString(2131099797))))
								.append(" ");
						String s23 = stringbuilder12.append(mFrom0To100String)
								.toString();
						mFrom0To100Info.setText(s23);
						m100KphReached = true;
						mTemp0To100Time = mFrom0To100String;
						mConfirm0To100Button.setEnabled(true);
						mDiscard0To100Button.setEnabled(true);
					}
				}

				if (mQuarterMileScreen.getVisibility() == 0) {
					if (mLastLocation.getSpeed() == 0F && f > 0F
							&& !mQtrMileReached) {
						mAcclStartLocation = mLastLocation;
						if (SpeedView.mDisplayUnits == 1) {
							StringBuilder stringbuilder2 = new StringBuilder();
							stringbuilder2.append(
									(int) location
											.distanceTo(mAcclStartLocation))
									.toString();
							mQuarterMileDist.setText(stringbuilder2.toString());
						} else {
							StringBuilder stringbuilder13 = new StringBuilder();
							stringbuilder13
									.append((int) (location
											.distanceTo(mAcclStartLocation) * 3.2808F))
									.toString();
							mQuarterMileDist
									.setText(stringbuilder13.toString());
						}
						mQuarterMileInfo.setText(2131099791);
						mQuarterMileInfo.setTextColor(-65536);
						mQtrMileReached = false;
					} else {
						if (location.distanceTo(mAcclStartLocation) > 402.336F
								|| mQtrMileReached)
							return;
						long l7 = (long) (l1 - mAcclStartLocation.getTime());
						int i6 = (int) (l7 % 1000L);
						int j6 = (int) (l7 / 1000L);
						StringBuilder stringbuilder14 = (new StringBuilder(
								String.valueOf(j6))).append(".").append(i6)
								.append(" ");
						mQtrMileString = stringbuilder14.append(
								getString(2131099789)).toString();
						mQuarterMileTime.setText(mQtrMileString);
						StringBuilder stringbuilder15 = (new StringBuilder(
								String.valueOf(mSpeed))).append(" ");
						String s31 = SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits];
						String s32 = stringbuilder15.append(s31).toString();
						mQuarterMileSpeed.setText(s32);
						if (SpeedView.mDisplayUnits == 1) {
							StringBuilder stringbuilder16 = new StringBuilder();
							int i7 = (int) location
									.distanceTo(mAcclStartLocation);
							stringbuilder16.append(i7).toString();
							mQuarterMileDist
									.setText(stringbuilder16.toString());
						} else {
							StringBuilder stringbuilder17 = new StringBuilder();
							int j7 = (int) (location
									.distanceTo(mAcclStartLocation) * 3.2808F);
							stringbuilder17.append(j7).toString();
							mQuarterMileDist
									.setText(stringbuilder17.toString());
						}
						mConfirmQtrButton.setEnabled(false);
						mDiscardQtrButton.setEnabled(false);
					}

				}

				switch (SpeedView.mDisplayUnits) {
				case 0:
					mAltitudeString = (new StringBuilder(
							String.valueOf((int) (d2 / 0.30480000000000002D))))
							.append(" ft \u2191").toString();
					break;
				case 1:
					mAltitudeString = (new StringBuilder(
							String.valueOf((int) d2))).append(" m \u2191")
							.toString();
					break;
				case 2:
					mAltitudeString = (new StringBuilder(
							String.valueOf((int) (d2 / 0.30480000000000002D))))
							.append(" ft \u2191").toString();
					break;
				}
				if (SpeedView.mSelectedDashboard == 2) {
					if (f > 1.4F) {
						mCompassMode.onLocationChanged(location, mSpeed,
								mSpeedWarning);
						mCompassSource.setText(2131099748);
					}
					StringBuilder stringbuilder22 = (new StringBuilder(
							String.valueOf(mSpeed))).append(" ");
					stringbuilder22.append(
							SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits])
							.toString();
					mCompassSpeed.setText(stringbuilder22.toString());
					mCompassOdometer.setText(distanceToString());
					mCompassElevation.setText(mAltitudeString);
					SimpleDateFormat simpledateformat;
					if (!DateFormat.is24HourFormat(getApplicationContext())) {
						simpledateformat = new SimpleDateFormat("h:mm a");
					} else {
						simpledateformat = new SimpleDateFormat("HH:mm");
					}
					mCompassTime.setText(simpledateformat.format(l1 + ""));
				} else if (SpeedView.mStoredOrientation == 0) {
					if (f > 1.4F) {
						mCompassView.onSpeedChanged((float) f3);
					}
				} else if (mHeading != null) {
					mHeading.setText(mHeadingString);
				}
				if (f > mStoredMaxSpeed) {
					mStoredMaxSpeed = f;
				}
				if (!SpeedView.mDigitSpeedoChecked
						|| !SpeedView.mDigitAddlDataToggled) {
					mSpeedometerView.onSpeedChanged(mSpeed, mSpeedWarning,
							mStoredMaxSpeed);
				} else {

				}

				if (!SpeedView.mWarningChecked) {
					if (SpeedView.mStreetAddrChecked
							&& SpeedView.mHasNetworkAccess) {
						if (mLastAddress != null) {
							float f11;
							if (f > 15.6F)
								f11 = 1000;
							else
								f11 = 100;
							if (location.distanceTo(mLastAddress) > f11) {
								displayAddress(location);
								mLastAddress = location;
							} else {
								displayAddress(location);
								mLastAddress = location;
							}
						} else {
							mAddressLine0.setText(2131099737);
						}
						if (mAccuracy < 500) {
							if (mAccuracy > 50 && mAccuracy < 500)
								mSignalStrength.setImageResource(2130837516);
							else
								mSignalStrength.setImageResource(2130837515);
						} else {
							mSignalStrength.setImageResource(2130837518);
						}
					}
				}

				if (mTrackLoggingChecked) {
					if (mLastTrackLocation != null) {
						if ((l1 - mLastTrackLocation.getTime()) > SpeedView.MIN_TIME_VALUES[mMinTimeBetweenPts] * 1000) {
							if (location.distanceTo(mLastTrackLocation) > SpeedView.MIN_DISTANCE_VALUES[mMinDistBetweenPts]) {
								StringBuilder stringbuilder24 = mTrackBuffer
										.append(mCoordFormat.format(d)).append(
												"|");
								stringbuilder24.append(mCoordFormat.format(d1))
										.append("|");
								stringbuilder24
										.append(l1 / 1000L - 1280000000L)
										.append("|");
								stringbuilder24.append(f).append("|");
								stringbuilder24.append((int) d2).append("\n");
								if (mTrackBuffer.length() > 2000)
									saveCurrentTrack();
								mLastTrackLocation = location;
							}
						}
					} else {
						mLastTrackLocation = location;
					}
				} else {
					if (mSpeed <= mSpeedWarning || !mSoundAlertToggled) {
						if (mWarningSound != null && mWarningSound.isPlaying())
							mWarningSound.stop();
					} else {
						if (mWarningSound == null) {
							Toast.makeText(getBaseContext(), 2131099705,
									Toast.LENGTH_LONG).show();
						} else {
							if (mWarningSound.getTitle(getBaseContext())
									.equals("Unknown ringtone")) {
								Toast.makeText(getBaseContext(), 2131099705,
										Toast.LENGTH_LONG).show();
							} else {
								if (!mWarningSound.isPlaying())
									mWarningSound.play();
							}
						}

						if (mSpeed > mSpeedWarning && mVibrationChecked)
							mVibrator.vibrate(300L);
						if (mSpeed > mSpeedWarning && !mLimitFlag) {
							if (SpeedView.mSelectedDashboard != 3)
								Toast.makeText(getBaseContext(), 2131099706, 1)
										.show();
							mCompassSpeed.setTextColor(-65536);
							mMaxSpeed.setTextColor(-65536);
							mLimitFlag = true;
						} else {
							if (mSpeedWarning <= mSpeed && mLimitFlag) {
								if (SpeedView.mCustomColorsChecked) {
									mCompassSpeed
											.setTextColor(SpeedView.mSecondaryTextColor);
									mMaxSpeed
											.setTextColor(SpeedView.mSecondaryTextColor);
								} else {
									mCompassSpeed.setTextColor(-3355444);
									mMaxSpeed.setTextColor(-3355444);
								}
								mLimitFlag = false;
							}
						}

					}
				}
				mAccuracyNotification.setVisibility(0);
				mGraphView.setVisibility(8);
				StringBuilder stringbuilder31 = (new StringBuilder(
						String.valueOf(getString(2131099661)))).append(" (");
				stringbuilder31.append(mAccuracy).append(" m)").toString();
				mLowAccuracy.setText(stringbuilder31.toString());
				if (mFirstFixTime == 0L) {
					mFirstFixTime = (long) l1;
					if (f > 0F) {
					} else {
					}
				}
				mLastLocation = location;
				if (mAcclStartLocation != null) {
					if (location.distanceTo(mAcclStartLocation) > 402.336F
							&& !mQtrMileReached) {
						mQuarterMileDist.setTextColor(-16776961);
						if (SpeedView.mDisplayUnits == 1) {
							StringBuilder stringbuilder18 = new StringBuilder();
							stringbuilder18.append(
									(int) location
											.distanceTo(mAcclStartLocation))
									.toString();
							mQuarterMileDist
									.setText(stringbuilder18.toString());
						} else {
							StringBuilder stringbuilder20 = new StringBuilder();
							int i8 = (int) (location
									.distanceTo(mAcclStartLocation) * 3.2808F);
							String s40 = stringbuilder20.append(i8).toString();
							mQuarterMileDist.setText(s40);
						}
						mQuarterMileInfo.setTextColor(-3355444);
						StringBuilder stringbuilder19 = (new StringBuilder(
								String.valueOf(getString(2131099802))))
								.append(" ");
						mQuarterMileInfo.setText(stringbuilder19.append(
								mQtrMileString).toString());
						mQtrMileReached = true;
						mTempQtrMileTime = mQtrMileString;
						mConfirmQtrButton.setEnabled(true);
						mDiscardQtrButton.setEnabled(true);
					}
				}
			}
		}

		private MyLocationListener() {
			super();
			mLimitFlag = false;
		}

		public void onStatusChanged(String s, int i, android.os.Bundle bundle) {

		}

		public void onProviderEnabled(String s) {

		}

		public void onProviderDisabled(String s) {

		}
	}

	private void checkGPSEnabled() {
		if (!mLocationManager.isProviderEnabled("gps")) {
			if (!mNotifiedAboutGPS) {
				(new android.app.AlertDialog.Builder(this))
						.setTitle(2131099709)
						.setMessage(2131099710)
						.setPositiveButton(
								2131099693,
								new android.content.DialogInterface.OnClickListener() {

									public void onClick(
											DialogInterface dialoginterface,
											int i) {
										startActivity(new Intent(
												"android.settings.LOCATION_SOURCE_SETTINGS"));
									}
								})
						.setNeutralButton(
								2131099711,
								new android.content.DialogInterface.OnClickListener() {

									public void onClick(
											DialogInterface dialoginterface,
											int i) {
									}
								})
						.setNegativeButton(
								2131099691,
								new android.content.DialogInterface.OnClickListener() {

									public void onClick(
											DialogInterface dialoginterface,
											int i) {
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
		} else {
			mRecordingStatus.setTextColor(-3355444);
			mRecordingButton.setEnabled(true);
			mIsGPSEnabled = true;
		}
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

	private void displayStoredData() {
		float f = 0.0F;
		float f1 = 0.0F;
		if (mStoredMovingTime + mSessionMovingTime != 0L)
			f = mStoredDistance
					/ ((float) (mStoredMovingTime + mSessionMovingTime) / 1000F);
		if (mStoredTotalTime + mSessionTotalTime != 0L)
			f1 = mStoredDistance
					/ ((float) (mStoredTotalTime + mSessionTotalTime) / 1000F);
		if (mSelectedDashboard == 4) {
			mTripDistance.setText(distanceToString());
			mTripTimeMoving.setText(getElapsedTimeString(mStoredMovingTime
					+ mSessionMovingTime));
			mTripTimeStopped
					.setText(getElapsedTimeString((mStoredTotalTime + mSessionTotalTime)
							- (mStoredMovingTime + mSessionMovingTime)));
			mTripTimeTotal.setText(getElapsedTimeString(mStoredTotalTime
					+ mSessionTotalTime));
			mSpeedMovingAvg.setText((new StringBuilder(String
					.valueOf(getDisplaySpeed(f)))).append(" ")
					.append(UNITS_ARRAY[mDisplayUnits]).toString());
			mSpeedOverallAvg.setText((new StringBuilder(String
					.valueOf(getDisplaySpeed(f1)))).append(" ")
					.append(UNITS_ARRAY[mDisplayUnits]).toString());
			mFrom0To60Result.setText(mStored0To60Time);
			mFrom0To100Result.setText(mStored0To100Time);
			mQuarterMileResult.setText(mStoredQtrMileTime);
		} else {
			mOdometer.setText(distanceToString());
			mMaxSpeed.setText((new StringBuilder()).append(
					getDisplaySpeed(mStoredMaxSpeed)).toString());
		}
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

	private void exportTrackFile(boolean flag) {
		saveCurrentTrack();
		mSendTrackInit = flag;
		File file = new File((new StringBuilder())
				.append(Environment.getExternalStorageDirectory())
				.append("/speedview/logs").toString());
		if (!file.exists()) {
			progressHandler.sendEmptyMessage(0);
		} else {
			mLogFilesList = file.list(mLogExtensionFilter);
			if (mLogFilesList != null) {
				if (mLogFilesList.length > 1) {
					Arrays.sort(mLogFilesList);
					mExportGPXButton.showContextMenu();
				} else {
					exportTrackFileEx(mLogFilesList[0]);
				}
			} else {
				progressHandler.sendEmptyMessage(0);
			}
		}
	}

	private void exportTrackFileEx(final String file) {
		mProgressDialog.show();
		(new Thread() {

			public void run() {
				try {
					mTrackLogFile = new File((new StringBuilder())
							.append(Environment.getExternalStorageDirectory())
							.append("/speedview/logs").toString(), file);
					File file1;
					File file2;
					BufferedReader bufferedreader;
					BufferedWriter bufferedwriter;
					if (SpeedView.mMaverickInst)
						file1 = new File((new StringBuilder())
								.append(Environment
										.getExternalStorageDirectory())
								.append("/maverick/tracks").toString());
					else
						file1 = new File((new StringBuilder())
								.append(Environment
										.getExternalStorageDirectory())
								.append("/speedview/tracks").toString());
					if (!file1.exists())
						file1.mkdirs();
					file2 = new File(file1, (new StringBuilder("SpeedView ["))
							.append(file.substring(0, 10)).append("].gpx")
							.toString());
					bufferedreader = new BufferedReader(new FileReader(
							mTrackLogFile), 8192);
					bufferedwriter = new BufferedWriter(new FileWriter(file2),
							8192);
					bufferedwriter
							.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
					bufferedwriter.write("<gpx\n");
					bufferedwriter.write(" version=\"1.0\"\n");
					bufferedwriter
							.write(" creator=\"SpeedView - http://www.codesector.com\"\n");
					bufferedwriter
							.write(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
					bufferedwriter
							.write(" xmlns=\"http://www.topografix.com/GPX/1/0\"\n");
					bufferedwriter
							.write(" xsi:schemaLocation=\"http://www.topografix.com/GPX/1/0 http://www.topografix.com/GPX/1/0/gpx.xsd\">\n");
					bufferedwriter.write((new StringBuilder("<time>"))
							.append(mDateFormat.format(new Date())).append("T")
							.append(mTimeFormat.format(new Date())).append("Z")
							.append("</time>\n").toString());
					bufferedwriter.write("<trk>\n");
					bufferedwriter.write(" <name>SpeedView</name>\n");
					bufferedwriter.write(" <trkseg>\n");
					do {
						String s = bufferedreader.readLine();
						if (s == null) {
							bufferedwriter.write(" </trkseg>\n");
							bufferedwriter.write("</trk>\n");
							bufferedwriter.write("</gpx>\n");
							bufferedwriter.flush();
							bufferedwriter.close();
							bufferedreader.close();
							Message message = progressHandler.obtainMessage();
							message.what = 1;
							Bundle bundle = new Bundle();
							bundle.putString("file_path",
									file2.getAbsolutePath());
							message.setData(bundle);
							progressHandler.sendMessage(message);
							break;
						}
						String as[] = s.split("\\|");
						long l = 1000L * (1280000000L + Long.parseLong(as[2]));
						float f = Float.parseFloat(as[3]);
						bufferedwriter.write((new StringBuilder(
								"  <trkpt lat=\"")).append(as[0])
								.append("\" lon=\"").append(as[1])
								.append("\">\n").toString());
						bufferedwriter.write((new StringBuilder("    <time>"))
								.append(mDateFormat.format(new Date(l)))
								.append("T")
								.append(mTimeFormat.format(new Date(l)))
								.append("Z").append("</time>\n").toString());
						bufferedwriter
								.write((new StringBuilder("    <desc>"))
										.append(getDisplaySpeed(f))
										.append(" ")
										.append(SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits])
										.append("</desc>\n").toString());
						bufferedwriter.write((new StringBuilder("    <ele>"))
								.append(as[4]).append("</ele>\n").toString());
						bufferedwriter.write("  </trkpt>\n");
					} while (true);
				} catch (Exception exception) {
					exception.printStackTrace();
					progressHandler.sendEmptyMessage(2);
				}
			}
		}).start();
	}

	private int getDisplaySpeed(float f) {
		int i = 0;
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
		}
		return i;
	}

	private String getElapsedTimeString(long l) {
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
		return (new StringBuilder(String.valueOf(String.format(s, aobj3))))
				.append(":").append(s2).append(":").append(s1).toString();
	}

	private Location getLastLocation() {
		return mLastLocation;
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

	private boolean isNetworkAvailable() {
		boolean flag = true;
		if (((ConnectivityManager) getSystemService("connectivity"))
				.getActiveNetworkInfo() == null) {
			if (!mNotifiedAboutNetwork) {
				(new android.app.AlertDialog.Builder(this))
						.setTitle(2131099712)
						.setMessage(2131099713)
						.setPositiveButton(
								2131099693,
								new android.content.DialogInterface.OnClickListener() {

									public void onClick(
											DialogInterface dialoginterface,
											int i) {
										startActivity(new Intent(
												"android.settings.WIRELESS_SETTINGS"));
									}
								})
						.setNeutralButton(
								2131099711,
								new android.content.DialogInterface.OnClickListener() {

									public void onClick(
											DialogInterface dialoginterface,
											int i) {
									}
								})
						.setNegativeButton(
								2131099691,
								new android.content.DialogInterface.OnClickListener() {

									public void onClick(
											DialogInterface dialoginterface,
											int i) {
										mExitButtonPressed = true;
										finish();
									}
								}).show();
				mNotifiedAboutNetwork = flag;
			}
			flag = false;
		}
		return flag;
	}

	private void resetAcclTimes() {
		mStored0To60Time = getString(2131099663);
		mStored0To100Time = getString(2131099663);
		mStoredQtrMileTime = getString(2131099663);
	}

	private void resetMaxSpeed() {
		mStoredMaxSpeed = 0.0F;
		if (mSelectedDashboard != 4) {
			mOdometer.setText(distanceToString());
			mMaxSpeed.setText((new StringBuilder()).append(
					getDisplaySpeed(mStoredMaxSpeed)).toString());
		}
	}

	private void resetOdometer() {
		mStoredDistance = 0.0F;
		mStoredMovingTime = 0L;
		mStoredTotalTime = 0L;
		mSessionMovingTime = 0L;
		mSessionTotalTime = 0L;
	}

	private void saveCurrentTrack() {
		if (mTrackBuffer.length() != 0) {
			if (mTrackLogFile != null) {
				RandomAccessFile randomaccessfile = null;
				try {
					randomaccessfile = new RandomAccessFile(mTrackLogFile,
							"rwd");
					randomaccessfile.seek(randomaccessfile.length());
					randomaccessfile.writeBytes(mTrackBuffer.toString());
					randomaccessfile.close();
					mTrackBuffer.setLength(0);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				File file = new File((new StringBuilder())
						.append(Environment.getExternalStorageDirectory())
						.append("/speedview/logs").toString());
				String as[] = file.list(mLogExtensionFilter);
				if (!file.exists())
					file.mkdirs();
				Arrays.sort(as);
				for (int i = 0; i < as.length - 3; i++) {
					(new File(file, as[i])).delete();
				}
				mTrackLogFile = new File(file, (new StringBuilder(
						String.valueOf(mDateFormat.format(new Date()))))
						.append(".log").toString());
			}
		}
	}

	private void saveUserPreferences() {
		android.content.SharedPreferences.Editor editor = getSharedPreferences(
				"PrefsFile", 0).edit();
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

	private void setFullScreenMode(boolean flag) {
		android.view.WindowManager.LayoutParams layoutparams = getWindow()
				.getAttributes();
		if (flag)
			layoutparams.flags = 1024 | layoutparams.flags;
		else
			layoutparams.flags = -1025 & layoutparams.flags;
		getWindow().setAttributes(layoutparams);
	}

	private void switchToScreen(int i) {

		switch (i) {
		case 0:
			mMainScreen.setVisibility(4);
			mCompassScreen.setVisibility(4);
			mHudScreen.setVisibility(4);
			mAdvancedScreen.setVisibility(4);
			mStartupScreen.setVisibility(0);
			mSelectedDashboard = 0;
			break;
		case 1:
			mStartupScreen.setVisibility(4);
			mCompassScreen.setVisibility(4);
			mHudScreen.setVisibility(4);
			mAdvancedScreen.setVisibility(4);
			mMainScreen.setVisibility(0);
			mSelectedDashboard = 1;
			break;
		case 2:
			mStartupScreen.setVisibility(4);
			mMainScreen.setVisibility(4);
			mHudScreen.setVisibility(4);
			mAdvancedScreen.setVisibility(4);
			mCompassScreen.setVisibility(0);
			mSelectedDashboard = 2;
			break;
		case 3:
			mStartupScreen.setVisibility(4);
			mMainScreen.setVisibility(4);
			mCompassScreen.setVisibility(4);
			mAdvancedScreen.setVisibility(4);
			mHudScreen.setVisibility(0);
			mSelectedDashboard = 3;
			break;
		case 4:
			mStartupScreen.setVisibility(4);
			mMainScreen.setVisibility(4);
			mCompassScreen.setVisibility(4);
			mHudScreen.setVisibility(4);
			mAdvancedScreen.setVisibility(0);
			mSelectedDashboard = 4;
			break;
		}
	}

	public boolean onContextItemSelected(MenuItem menuitem) {
		exportTrackFileEx(mLogFilesList[menuitem.getItemId()]);
		return super.onContextItemSelected(menuitem);
	}

	public void onContextMenuClosed(Menu menu) {
		super.onContextMenuClosed(menu);
	}

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		int i;
		DisplayMetrics displaymetrics;
		DecimalFormatSymbols decimalformatsymbols;
		try {
			PackageInfo packageinfo = getPackageManager().getPackageInfo(
					"com.codesector.speedview.pro", 128);
			mVersionCode = packageinfo.versionCode;
			mVersionName = packageinfo.versionName;
		} catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception) {
			namenotfoundexception.printStackTrace();
		}
		i = getResources().getConfiguration().screenLayout;
		if ((i & 15) == 4) {
			mScreenLayoutSize = 4;
		} else if ((i & 15) == 3) {
			mScreenLayoutSize = 3;
		} else if ((i & 15) == 2) {
			mScreenLayoutSize = 2;
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
		setContentView(2130903046);
		mViewStub = (ViewStub) findViewById(2131296303);
		if (mViewStub != null)
			mViewStub.inflate();
		mGeocoder = new Geocoder(getBaseContext(), Locale.getDefault());
		mAddressView = (RelativeLayout) findViewById(2131296261);
		mAddressLine0 = (TextView) findViewById(2131296263);
		mAddressLine1 = (TextView) findViewById(2131296264);
		mSignalStrength = (ImageView) findViewById(2131296266);
		mNumberOfSats = (TextView) findViewById(2131296267);
		mAddressView
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						if (SpeedView.mStreetAddrChecked
								&& SpeedView.mHasNetworkAccess
								&& SpeedView.mHasGPSFix) {
							mAddressLine0.setText(2131099728);
							mAddressLine1.setText(2131099729);
							displayAddress(getLastLocation());
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
		mProgressDialog = new ProgressDialog(this);
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
		mStartupScreen
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						switchToScreen(1);
					}
				});
		mStartupScreen
				.setOnLongClickListener(new android.view.View.OnLongClickListener() {

					public boolean onLongClick(View view) {
						boolean flag = false;
						if (SpeedView.mWarningChecked) {
							mSwitchboard.setVisibility(0);
							flag = true;
						}
						return flag;
					}
				});
		mMainScreen
				.setOnLongClickListener(new android.view.View.OnLongClickListener() {

					public boolean onLongClick(View view) {
						boolean flag = false;
						if (SpeedView.mWarningChecked) {
							mSwitchboard.setVisibility(0);
							flag = true;
						}
						return flag;
					}
				});
		mCompassView
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						switchToScreen(2);
					}
				});
		mCompassView
				.setOnLongClickListener(new android.view.View.OnLongClickListener() {

					public boolean onLongClick(View view) {
						boolean flag = false;
						if (SpeedView.mWarningChecked) {
							mSwitchboard.setVisibility(0);
							flag = true;
						}
						return flag;
					}
				});
		mSpeedometerView
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						switchToScreen(3);
					}
				});
		mSpeedometerView
				.setOnLongClickListener(new android.view.View.OnLongClickListener() {

					public boolean onLongClick(View view) {
						boolean flag = false;
						if (SpeedView.mWarningChecked) {
							mSwitchboard.setVisibility(0);
							flag = true;
						}
						return flag;
					}
				});
		mOdometerField
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						(new android.app.AlertDialog.Builder(SpeedView.this))
								.setTitle(2131099714)
								.setMessage(2131099715)
								.setPositiveButton(
										2131099724,
										new android.content.DialogInterface.OnClickListener() {

											public void onClick(
													DialogInterface dialoginterface,
													int j) {
												resetOdometer();
												displayStoredData();
												Toast.makeText(
														getBaseContext(),
														2131099700, 1).show();
											}
										})
								.setNegativeButton(
										2131099725,
										new android.content.DialogInterface.OnClickListener() {

											public void onClick(
													DialogInterface dialoginterface,
													int j) {
											}
										}).show();
					}
				});
		mOdometer.setOnClickListener(new android.view.View.OnClickListener() {

			public void onClick(View view) {
				(new android.app.AlertDialog.Builder(SpeedView.this))
						.setTitle(2131099714)
						.setMessage(2131099715)
						.setPositiveButton(
								2131099724,
								new android.content.DialogInterface.OnClickListener() {

									public void onClick(
											DialogInterface dialoginterface,
											int j) {
										resetOdometer();
										displayStoredData();
										Toast.makeText(getBaseContext(),
												2131099700, 1).show();
									}
								})
						.setNegativeButton(
								2131099725,
								new android.content.DialogInterface.OnClickListener() {

									public void onClick(
											DialogInterface dialoginterface,
											int j) {
									}
								}).show();
			}
		});
		mMaxField.setOnClickListener(new android.view.View.OnClickListener() {

			public void onClick(View view) {
				(new android.app.AlertDialog.Builder(SpeedView.this))
						.setTitle(2131099716)
						.setMessage(2131099717)
						.setPositiveButton(
								2131099724,
								new android.content.DialogInterface.OnClickListener() {

									public void onClick(
											DialogInterface dialoginterface,
											int j) {
										resetMaxSpeed();
										Toast.makeText(getBaseContext(),
												2131099701, 1).show();
									}
								})
						.setNegativeButton(
								2131099725,
								new android.content.DialogInterface.OnClickListener() {

									public void onClick(
											DialogInterface dialoginterface,
											int j) {
									}
								}).show();
			}
		});
		mMaxSpeed.setOnClickListener(new android.view.View.OnClickListener() {

			public void onClick(View view) {
				(new android.app.AlertDialog.Builder(SpeedView.this))
						.setTitle(2131099716)
						.setMessage(2131099717)
						.setPositiveButton(
								2131099724,
								new android.content.DialogInterface.OnClickListener() {

									public void onClick(
											DialogInterface dialoginterface,
											int j) {
										resetMaxSpeed();
										Toast.makeText(getBaseContext(),
												2131099701, 1).show();
									}
								})
						.setNegativeButton(
								2131099725,
								new android.content.DialogInterface.OnClickListener() {

									public void onClick(
											DialogInterface dialoginterface,
											int j) {
									}
								}).show();
			}
		});
		mGraphView.setOnClickListener(new android.view.View.OnClickListener() {

			public void onClick(View view) {
				switch (SpeedView.mDisplayUnits) {
				case 0:
					mFrom0To100Row.setVisibility(8);
					mFrom0To60Row.setVisibility(0);
					mFrom0To100Button.setVisibility(8);
					mFrom0To60Button.setVisibility(0);
					mAccelerationLayout.setVisibility(0);
					break;
				case 1:
					mFrom0To60Row.setVisibility(8);
					mFrom0To100Row.setVisibility(0);
					mFrom0To60Button.setVisibility(8);
					mFrom0To100Button.setVisibility(0);
					mAccelerationLayout.setVisibility(0);
					break;
				case 2:
					mAccelerationLayout.setVisibility(8);
					break;
				}

				switchToScreen(4);
				displayStoredData();
			}
		});
		mGraphView
				.setOnLongClickListener(new android.view.View.OnLongClickListener() {

					public boolean onLongClick(View view) {
						boolean flag = false;
						if (SpeedView.mWarningChecked) {
							mSwitchboard.setVisibility(0);
							flag = true;
						}
						return flag;
					}
				});
		mCompassScreen
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						switchToScreen(1);
					}
				});
		mCompassScreen
				.setOnLongClickListener(new android.view.View.OnLongClickListener() {

					public boolean onLongClick(View view) {
						boolean flag = false;
						if (SpeedView.mWarningChecked) {
							mSwitchboard.setVisibility(0);
							flag = true;
						}
						return flag;
					}
				});
		mHudScreen.setOnClickListener(new android.view.View.OnClickListener() {

			public void onClick(View view) {
				switchToScreen(1);
			}
		});
		mHudScreen
				.setOnLongClickListener(new android.view.View.OnLongClickListener() {

					public boolean onLongClick(View view) {
						boolean flag = false;
						if (SpeedView.mWarningChecked) {
							mSwitchboard.setVisibility(0);
							flag = true;
						}
						return flag;
					}
				});
		mFrom0To60Button
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
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
		mFrom0To100Button
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
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
		mQuarterMileButton
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						mQuarterMileTime.setText(2131099787);
						mQuarterMileSpeed
								.setText((new StringBuilder("0 "))
										.append(SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits])
										.toString());
						mQuarterMileDist.setText("0");
						mQuarterMileDist.setTextColor(-1);
						TextView textview = mQuarterMileUnits;
						String s;
						if (SpeedView.mDisplayUnits == 1)
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
				});
		mFrom0To60Screen
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						mFrom0To60Screen.setVisibility(4);
					}
				});
		mConfirm0To60Button
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						mStored0To60Time = mTemp0To60Time;
						mAccelerationInfo.setText(2131099759);
						mFrom0To60Screen.setVisibility(4);
					}
				});
		mDiscard0To60Button
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						mFrom0To60Screen.setVisibility(4);
					}
				});
		mFrom0To100Screen
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						mFrom0To100Screen.setVisibility(4);
					}
				});
		mConfirm0To100Button
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						mStored0To100Time = mTemp0To100Time;
						mAccelerationInfo.setText(2131099759);
						mFrom0To100Screen.setVisibility(4);
					}
				});
		mDiscard0To100Button
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						mFrom0To100Screen.setVisibility(4);
					}
				});
		mQuarterMileScreen
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						mQuarterMileScreen.setVisibility(4);
					}
				});
		mConfirmQtrButton
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						mStoredQtrMileTime = mTempQtrMileTime;
						mAccelerationInfo.setText(2131099759);
						mQuarterMileScreen.setVisibility(4);
					}
				});
		mDiscardQtrButton
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						mQuarterMileScreen.setVisibility(4);
					}
				});
		mRecordingButton
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						if (SpeedView.mIsRecording) {
							mRecordingStatus.setText(2131099767);
							mRecordingButton.setText(2131099766);
							mLocationManager.removeUpdates(mLocationListener);
							mLocationManager
									.removeGpsStatusListener(mGPSListener);
							mSatelliteView.clearSatellites();
							mStatusMessage.setText(2131099664);
							mNumOfSatellites.setText(2131099665);
							mTipMessage.setText(2131099666);
							mAddressLine0.setText(2131099664);
							mAddressLine1.setText(2131099665);
							mSignalStrength.setImageResource(2130837517);
							mNumberOfSats.setText(2131099738);
							if (SpeedView.mDigitSpeedoChecked
									&& SpeedView.mDigitAddlDataToggled)
								mSpeedometerView.refreshView();
							mSpeedometerView.onSpeedChanged(-1);
							SpeedView speedview = SpeedView.this;
							speedview.mStoredMovingTime = speedview.mStoredMovingTime
									+ mSessionMovingTime;
							SpeedView speedview1 = SpeedView.this;
							speedview1.mStoredTotalTime = speedview1.mStoredTotalTime
									+ mSessionTotalTime;
							mSessionMovingTime = 0L;
							mSessionTotalTime = 0L;
							SpeedView.mHasGPSFix = false;
							SpeedView.mIsRecording = false;
						} else {
							mRecordingStatus.setText(2131099764);
							mRecordingButton.setText(2131099765);
							try {
								mLocationManager.requestLocationUpdates("gps",
										0L, 0.0F, mLocationListener);
								mLocationManager
										.addGpsStatusListener(mGPSListener);
							} catch (Exception exception) {
								exception.printStackTrace();
							}
							mAddressLine0.setText(2131099651);
							mAddressLine1.setText(2131099652);
							SpeedView.mIsRecording = true;
						}
					}
				});
		mExportGPXButton
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						if (!"mounted".equals(Environment
								.getExternalStorageState()))
							Toast.makeText(getBaseContext(), 2131099812, 1)
									.show();
						else
							exportTrackFile(false);
					}
				});
		mSendGPXButton
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						if (!"mounted".equals(Environment
								.getExternalStorageState()))
							Toast.makeText(getBaseContext(), 2131099812, 1)
									.show();
						else
							exportTrackFile(true);
					}
				});
		mGoogleMapsButton
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						Intent intent = new Intent();
						intent.setClassName("com.google.android.apps.maps",
								"com.google.android.maps.MapsActivity");
						startActivity(intent);
					}
				});
		mOpenSpotButton
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						if (SpeedView.mOpenSpotInst) {
							Intent intent = new Intent();
							intent.setClassName("com.googlelabs.openspot",
									"com.google.android.apps.openspot.activities.MainActivity");
							startActivity(intent);
						} else {
							startActivity(new Intent(
									"android.intent.action.VIEW",
									Uri.parse("market://details?id=com.googlelabs.openspot")));
						}
					}
				});
		mMaverickButton
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						if (SpeedView.mMaverickInst) {
							Intent intent = new Intent();
							if (SpeedView.mMaverickVersion == SpeedView.VERSIONS[0])
								intent.setClassName(
										"com.codesector.maverick.full",
										"com.codesector.maverick.full.Main");
							else
								intent.setClassName(
										"com.codesector.maverick.lite",
										"com.codesector.maverick.lite.Main");
							startActivity(intent);
						} else {
							startActivity(new Intent(
									"android.intent.action.VIEW",
									Uri.parse("market://details?id=com.codesector.maverick.lite")));
						}
					}
				});
		mSwitchboard
				.setOnLongClickListener(new android.view.View.OnLongClickListener() {

					public boolean onLongClick(View view) {
						mSwitchboard.setVisibility(4);
						Toast.makeText(
								getBaseContext(),
								(new StringBuilder(String
										.valueOf(getString(2131099830))))
										.append(" ")
										.append(mSpeedWarning)
										.append(" ")
										.append(SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits])
										.toString(), 1).show();
						return true;
					}
				});
		mTownLimitToggle
				.setOnLongClickListener(new android.view.View.OnLongClickListener() {

					public boolean onLongClick(View view) {
						view.performClick();
						mSwitchboard.setVisibility(4);
						Toast.makeText(
								getBaseContext(),
								(new StringBuilder(String
										.valueOf(getString(2131099830))))
										.append(" ")
										.append(mSpeedWarning)
										.append(" ")
										.append(SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits])
										.toString(), 1).show();
						return true;
					}
				});
		mTownLimitToggle
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
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
				});
		mTownLimitDec
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						SpeedView speedview = SpeedView.this;
						speedview.mTownSpeedLimit = -1
								+ speedview.mTownSpeedLimit;
						mTownLimitNumbers.setText((new StringBuilder()).append(
								mTownSpeedLimit).toString());
						mTownLimitToggle.performClick();
					}
				});
		mTownLimitInc
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						SpeedView speedview = SpeedView.this;
						speedview.mTownSpeedLimit = 1 + speedview.mTownSpeedLimit;
						mTownLimitNumbers.setText((new StringBuilder()).append(
								mTownSpeedLimit).toString());
						mTownLimitToggle.performClick();
					}
				});
		mHighwayLimitToggle
				.setOnLongClickListener(new android.view.View.OnLongClickListener() {

					public boolean onLongClick(View view) {
						view.performClick();
						mSwitchboard.setVisibility(4);
						Toast.makeText(
								getBaseContext(),
								(new StringBuilder(String
										.valueOf(getString(2131099830))))
										.append(" ")
										.append(mSpeedWarning)
										.append(" ")
										.append(SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits])
										.toString(), 1).show();
						return true;
					}
				});
		mHighwayLimitToggle
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
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
				});
		mHighwayLimitDec
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						SpeedView speedview = SpeedView.this;
						speedview.mHighwaySpeedLimit = -1
								+ speedview.mHighwaySpeedLimit;
						mHighwayLimitNumbers.setText((new StringBuilder())
								.append(mHighwaySpeedLimit).toString());
						mHighwayLimitToggle.performClick();
					}
				});
		mHighwayLimitInc
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						SpeedView speedview = SpeedView.this;
						speedview.mHighwaySpeedLimit = 1 + speedview.mHighwaySpeedLimit;
						mHighwayLimitNumbers.setText((new StringBuilder())
								.append(mHighwaySpeedLimit).toString());
						mHighwayLimitToggle.performClick();
					}
				});
		mFreewayLimitToggle
				.setOnLongClickListener(new android.view.View.OnLongClickListener() {

					public boolean onLongClick(View view) {
						view.performClick();
						mSwitchboard.setVisibility(4);
						Toast.makeText(
								getBaseContext(),
								(new StringBuilder(String
										.valueOf(getString(2131099830))))
										.append(" ")
										.append(mSpeedWarning)
										.append(" ")
										.append(SpeedView.UNITS_ARRAY[SpeedView.mDisplayUnits])
										.toString(), 1).show();
						return true;
					}
				});
		mFreewayLimitToggle
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
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
				});
		mFreewayLimitDec
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						SpeedView speedview = SpeedView.this;
						speedview.mFreewaySpeedLimit = -1
								+ speedview.mFreewaySpeedLimit;
						mFreewayLimitNumbers.setText((new StringBuilder())
								.append(mFreewaySpeedLimit).toString());
						mFreewayLimitToggle.performClick();
					}
				});
		mFreewayLimitInc
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						SpeedView speedview = SpeedView.this;
						speedview.mFreewaySpeedLimit = 1 + speedview.mFreewaySpeedLimit;
						mFreewayLimitNumbers.setText((new StringBuilder())
								.append(mFreewaySpeedLimit).toString());
						mFreewayLimitToggle.performClick();
					}
				});
	}

	public void onCreateContextMenu(ContextMenu contextmenu, View view,
			android.view.ContextMenu.ContextMenuInfo contextmenuinfo) {
		super.onCreateContextMenu(contextmenu, view, contextmenuinfo);
		contextmenu.setHeaderTitle(2131099778);
		if (mLogFilesList == null || mLogFilesList.length <= 0) {
			return;
		} else {
			for (int i = 0; i < mLogFilesList.length; i++) {
				contextmenu.add(0, i, 0, mLogFilesList[i]);
			}
		}
	}

	protected Dialog onCreateDialog(int i) {
		final Dialog dialog = null;
		switch (i) {
		case 0:
			dialog.requestWindowFeature(1);
			dialog.setContentView(2130903045);
			final ScrollView helpView = (ScrollView) dialog
					.findViewById(2131296278);
			final LinearLayout helpButtonsLayout = (LinearLayout) dialog
					.findViewById(2131296282);
			Button button4 = (Button) dialog.findViewById(2131296283);
			Button button5 = (Button) dialog.findViewById(2131296284);
			Button button6 = (Button) dialog.findViewById(2131296290);
			final ScrollView faqView = (ScrollView) dialog
					.findViewById(2131296285);
			final LinearLayout faqButtonLayout = (LinearLayout) dialog
					.findViewById(2131296289);
			// (TextView)dialog.findViewById(2131296288);
			button4.setOnClickListener(new android.view.View.OnClickListener() {

				public void onClick(View view) {
					dialog.dismiss();
				}
			});
			button5.setOnClickListener(new android.view.View.OnClickListener() {

				public void onClick(View view) {
					helpView.setVisibility(8);
					helpButtonsLayout.setVisibility(8);
					faqView.setVisibility(0);
					faqButtonLayout.setVisibility(0);
				}
			});
			button6.setOnClickListener(new android.view.View.OnClickListener() {

				public void onClick(View view) {
					faqView.setVisibility(8);
					faqButtonLayout.setVisibility(8);
					helpView.setVisibility(0);
					helpButtonsLayout.setVisibility(0);
				}
			});
			break;
		case 1:
			dialog.requestWindowFeature(1);
			dialog.setContentView(2130903040);
			((TextView) dialog.findViewById(2131296257))
					.setText((new StringBuilder(String
							.valueOf(getString(2131099929)))).append(" ")
							.append(mVersionName).append(" ")
							.append(getString(2131099930)).toString());
			Button button2 = (Button) dialog.findViewById(2131296259);
			Button button3 = (Button) dialog.findViewById(2131296260);
			if (!mHasNetworkAccess)
				button3.setEnabled(false);
			button2.setOnClickListener(new android.view.View.OnClickListener() {

				public void onClick(View view) {
					dialog.dismiss();
				}
			});
			button3.setOnClickListener(new android.view.View.OnClickListener() {

				public void onClick(View view) {
					Intent intent = new Intent("android.intent.action.SEND");
					intent.setType("plain/text");
					intent.setClassName("com.google.android.gm",
							"com.google.android.gm.ComposeActivityGmail");
					intent.setData(Uri.parse("speedview@codesector.com"));
					String as[] = new String[1];
					as[0] = "speedview@codesector.com";
					intent.putExtra("android.intent.extra.EMAIL", as);
					intent.putExtra(
							"android.intent.extra.SUBJECT",
							(new StringBuilder(String
									.valueOf(getString(2131099648))))
									.append(" ").append(SpeedView.mVersionName)
									.append(" ").append(getString(2131099930))
									.toString());
					startActivity(intent);
				}
			});
			break;
		case 2:
			dialog.requestWindowFeature(1);
			dialog.setContentView(2130903043);
			// (TextView)dialog.findViewById(2131296270);
			Button button = (Button) dialog.findViewById(2131296271);
			Button button1 = (Button) dialog.findViewById(2131296272);
			if (!mHasNetworkAccess)
				button1.setEnabled(false);
			button.setOnClickListener(new android.view.View.OnClickListener() {

				public void onClick(View view) {
					dialog.dismiss();
				}
			});
			button1.setOnClickListener(new android.view.View.OnClickListener() {

				public void onClick(View view) {
					Uri uri = Uri
							.parse("http://blog.codesector.com/category/code-sector-software/speedview/");
					startActivity(new Intent("android.intent.action.VIEW", uri));
				}
			});
			break;
		}

		return dialog;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(2131230720, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onKeyDown(int i, KeyEvent keyevent) {
		boolean flag = true;
		if (i != 4) {
			flag = super.onKeyDown(i, keyevent);
		} else {
			if (mSwitchboard.getVisibility() != 0) {
				if (mFrom0To60Screen.getVisibility() == 0) {
					mFrom0To60Screen.setVisibility(4);
				}
				if (mFrom0To100Screen.getVisibility() == 0) {
					mFrom0To100Screen.setVisibility(4);
				}
				if (mQuarterMileScreen.getVisibility() == 0) {
					mQuarterMileScreen.setVisibility(4);
				}
				if (mSelectedDashboard == 0 && !mHasGPSFix) {
					switchToScreen(0);
				}
				if (mSelectedDashboard == 2 || mSelectedDashboard == 3
						|| mSelectedDashboard == 4) {
					switchToScreen(1);
				}
			} else {
				Toast.makeText(
						getBaseContext(),
						(new StringBuilder(String
								.valueOf(getString(2131099830)))).append(" ")
								.append(mSpeedWarning).append(" ")
								.append(UNITS_ARRAY[mDisplayUnits]).toString(),
						1).show();
				mSwitchboard.setVisibility(4);
			}
		}
		return flag;
	}

	public boolean onOptionsItemSelected(MenuItem menuitem) {

		switch (menuitem.getItemId()) {
		case 2131296693:
			setRequestedOrientation(1);
			mStoredOrientation = 0;
			break;
		case 2131296664:
			setRequestedOrientation(0);
			mStoredOrientation = 1;
			break;
		case 2131296665:
			setRequestedOrientation(8);
			mStoredOrientation = 3;
			break;
		case 2131296666:

			break;
		case 2131296667:
			mCurrentSpeedLimit = 0;
			mSpeedWarning = mTownSpeedLimit;
			Toast.makeText(
					getBaseContext(),
					(new StringBuilder(String.valueOf(getString(2131099830))))
							.append(" ").append(mSpeedWarning).append(" ")
							.append(UNITS_ARRAY[mDisplayUnits]).toString(), 1)
					.show();
			mTownLimitSign.setAlpha(250);
			mHighwayLimitSign.setAlpha(100);
			mFreewayLimitSign.setAlpha(100);
			break;
		case 2131296668:
			mCurrentSpeedLimit = 1;
			mSpeedWarning = mHighwaySpeedLimit;
			Toast.makeText(
					getBaseContext(),
					(new StringBuilder(String.valueOf(getString(2131099830))))
							.append(" ").append(mSpeedWarning).append(" ")
							.append(UNITS_ARRAY[mDisplayUnits]).toString(), 1)
					.show();
			mHighwayLimitSign.setAlpha(250);
			mTownLimitSign.setAlpha(100);
			mFreewayLimitSign.setAlpha(100);
			break;
		case 2131296669:
			mCurrentSpeedLimit = 2;
			mSpeedWarning = mFreewaySpeedLimit;
			Toast.makeText(
					getBaseContext(),
					(new StringBuilder(String.valueOf(getString(2131099830))))
							.append(" ").append(mSpeedWarning).append(" ")
							.append(UNITS_ARRAY[mDisplayUnits]).toString(), 1)
					.show();
			mFreewayLimitSign.setAlpha(250);
			mTownLimitSign.setAlpha(100);
			mHighwayLimitSign.setAlpha(100);
			break;
		case 2131296670:
			mWarningChecked = false;
			Toast.makeText(getBaseContext(), getString(2131099699), 1).show();
			break;
		case 2131296671:
			switchToScreen(0);
			break;
		case 2131296672:
			switchToScreen(1);
			break;
		case 2131296673:
			switchToScreen(2);
			break;
		case 2131296674:
			switchToScreen(3);
			break;
		case 2131296675:

			break;
		case 2131296676:

			switch (mDisplayUnits) {
			case 0:
				switchToScreen(4);
				displayStoredData();
				break;
			case 1:
				mFrom0To100Row.setVisibility(8);
				mFrom0To60Row.setVisibility(0);
				mFrom0To100Button.setVisibility(8);
				mFrom0To60Button.setVisibility(0);
				mAccelerationLayout.setVisibility(0);
				break;
			case 2:
				mFrom0To60Row.setVisibility(8);
				mFrom0To100Row.setVisibility(0);
				mFrom0To60Button.setVisibility(8);
				mFrom0To100Button.setVisibility(0);
				mAccelerationLayout.setVisibility(0);
				break;
			}
			mAccelerationLayout.setVisibility(8);

			break;
		case 2131296677:

			break;
		case 2131296678:
			(new android.app.AlertDialog.Builder(this))
					.setTitle(2131099714)
					.setMessage(2131099715)
					.setPositiveButton(
							2131099724,
							new android.content.DialogInterface.OnClickListener() {

								public void onClick(
										DialogInterface dialoginterface, int i) {
									resetOdometer();
									displayStoredData();
									Toast.makeText(getBaseContext(),
											2131099700, 1).show();
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
		case 2131296679:
			(new android.app.AlertDialog.Builder(this))
					.setTitle(2131099716)
					.setMessage(2131099717)
					.setPositiveButton(
							2131099724,
							new android.content.DialogInterface.OnClickListener() {

								public void onClick(
										DialogInterface dialoginterface, int i) {
									resetMaxSpeed();
									Toast.makeText(getBaseContext(),
											2131099701, 1).show();
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
		case 2131296680:
			(new android.app.AlertDialog.Builder(this))
					.setTitle(2131099718)
					.setMessage(2131099719)
					.setPositiveButton(
							2131099724,
							new android.content.DialogInterface.OnClickListener() {

								public void onClick(
										DialogInterface dialoginterface, int i) {
									resetAcclTimes();
									displayStoredData();
									Toast.makeText(getBaseContext(),
											2131099702, 1).show();
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
		case 2131296681:
			(new android.app.AlertDialog.Builder(this))
					.setTitle(2131099720)
					.setMessage(2131099721)
					.setPositiveButton(
							2131099724,
							new android.content.DialogInterface.OnClickListener() {

								public void onClick(
										DialogInterface dialoginterface, int i) {
									mGraphView.resetHexArray();
									Toast.makeText(getBaseContext(),
											2131099703, 1).show();
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
		case 2131296682:
			(new android.app.AlertDialog.Builder(this))
					.setTitle(2131099722)
					.setMessage(2131099723)
					.setPositiveButton(
							2131099724,
							new android.content.DialogInterface.OnClickListener() {

								public void onClick(
										DialogInterface dialoginterface, int i) {
									resetOdometer();
									resetMaxSpeed();
									resetAcclTimes();
									mGraphView.resetHexArray();
									displayStoredData();
									Toast.makeText(getBaseContext(),
											2131099704, 1).show();
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
		case 2131296683:
			mMinimizeButtonPressed = true;
			finish();
			break;
		case 2131296684:
			mExitButtonPressed = true;
			finish();
			break;
		case 2131296685:
			Intent intent1 = new Intent(this, ShareActivity.class);
			intent1.putExtra("last_location", mLastLocation);
			intent1.putExtra("address_string", mAddressString);
			startActivity(intent1);
			mShareButtonPressed = true;
			break;
		case 2131296686:
			Intent intent = new Intent(this, SettingsActivity.class);
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
			break;
		case 2131296687:
			showDialog(0);
			break;
		case 2131296688:
			showDialog(1);
			break;
		case 2131296689:
			showDialog(2);
			break;
		case 2131296690:
			startActivity(new Intent(this, FeaturedActivity.class));
			mFeaturedButtonPressed = true;
			break;
		case 2131296691:
			startActivity(new Intent(
					"android.intent.action.VIEW",
					Uri.parse("market://details?id=com.codesector.speedview.pro")));
			break;
		case 2131296692:
			break;

		}
		return super.onOptionsItemSelected(menuitem);
	}

	public void onOptionsMenuClosed(Menu menu) {
		super.onOptionsMenuClosed(menu);
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
			Intent intent = new Intent(this, BackgroundService.class);
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

	public boolean onPrepareOptionsMenu(Menu menu) {
		if (mFrom0To60Screen.getVisibility() != 0
				&& mFrom0To100Screen.getVisibility() != 0
				&& mQuarterMileScreen.getVisibility() != 0
				&& mSwitchboard.getVisibility() != 0) {
			if (!mDsblRotationChecked) {
				menu.findItem(2131296663).setVisible(false);
				menu.findItem(2131296667).setVisible(mWarningChecked);
				if (!mWarningChecked) {
					switch (mSelectedDashboard) {
					case 0:
						menu.findItem(2131296673).setChecked(true);
						break;
					case 1:
						menu.findItem(2131296674).setChecked(true);
						break;
					case 2:
						menu.findItem(2131296675).setChecked(true);
						break;
					case 3:
						menu.findItem(2131296676).setChecked(true);
						break;
					case 4:
						menu.findItem(2131296678).setChecked(true);
						break;
					}

					// _L14:
					boolean flag2;
					int k1;
					int i2 = 0;
					int j2 = 0;
					boolean flag3 = false;
					int k2 = 0;
					if (mHasGPSFix)
						flag2 = false;
					else
						flag2 = true;
					menu.findItem(2131296673).setVisible(flag2);
					if (mUseHudChecked)
						k1 = 2131099681;
					else
						k1 = 2131099682;
					menu.findItem(2131296676).setTitle(k1);
					int l1 = 0;
					if (mStoredDistance != 0F || mStoredMovingTime != 0L
							|| mStoredTotalTime != 0L) {
						menu.findItem(2131296680).setVisible(true);
						i2 = l1 + 1;
					} else {
						menu.findItem(2131296680).setVisible(false);
					}
					if (mStoredMaxSpeed != 0F) {
						menu.findItem(2131296681).setVisible(true);
						j2 = i2 + 1;
					} else {
						menu.findItem(2131296681).setVisible(false);
					}
					if (!mStored0To60Time.equals(getString(2131099663))) {
					} else {
						if (!mStored0To100Time.equals(getString(2131099663))) {
						} else {
							if (mStoredQtrMileTime
									.equals(getString(2131099663))) {
								menu.findItem(2131296682).setVisible(false);
								boolean flag4;
								boolean flag5;
								boolean flag7;
								boolean flag8;
								if (!mGraphView.isHexArrayEmpty()) {
									menu.findItem(2131296683).setVisible(true);
									flag3 = true;
									k2++;
								} else {
									menu.findItem(2131296683).setVisible(false);
								}
								menu.findItem(2131296679).setEnabled(flag3);
								if (k2 > 1)
									flag4 = true;
								else
									flag4 = false;
								menu.findItem(2131296684).setVisible(flag4);
								if (mIsGPSEnabled && !mRunInBGChecked)
									flag5 = true;
								else
									flag5 = false;
								menu.findItem(2131296685).setVisible(flag5);
								menu.findItem(2131296685).setEnabled(
										mIsRecording);
								if (mIsGPSEnabled && mRunInBGChecked)
									flag7 = true;
								else
									flag7 = false;
								menu.findItem(2131296686).setVisible(flag7);
								if (mHasNetworkAccess && mHasGPSFix)
									flag8 = true;
								else
									flag8 = false;
								menu.findItem(2131296687).setEnabled(flag8);
								menu.findItem(2131296692).setVisible(true);
								menu.findItem(2131296692).setEnabled(
										mHasNetworkAccess);
								menu.findItem(2131296693).setVisible(false);
								menu.findItem(2131296693).setEnabled(
										mHasNetworkAccess);
							} else {
								menu.findItem(2131296682).setVisible(true);
								flag3 = true;
								k2 = j2 + 1;
							}
						}
					}
				} else {
					String s = String.valueOf(getString(2131099673));
					StringBuilder stringbuilder = (new StringBuilder(s))
							.append(" (");
					StringBuilder stringbuilder1 = stringbuilder.append(
							mTownSpeedLimit).append(" ");
					String s1 = UNITS_ARRAY[mDisplayUnits];
					String s2 = stringbuilder1.append(s1).append(")")
							.toString();
					menu.findItem(2131296668).setTitle(s2);
					String s3 = String.valueOf(getString(2131099674));
					StringBuilder stringbuilder2 = (new StringBuilder(s3))
							.append(" (");
					StringBuilder stringbuilder3 = stringbuilder2.append(
							mHighwaySpeedLimit).append(" ");
					String s4 = UNITS_ARRAY[mDisplayUnits];
					String s5 = stringbuilder3.append(s4).append(")")
							.toString();
					menu.findItem(2131296669).setTitle(s5);
					String s6 = String.valueOf(getString(2131099675));
					StringBuilder stringbuilder4 = (new StringBuilder(s6))
							.append(" (");
					StringBuilder stringbuilder5 = stringbuilder4.append(
							mFreewaySpeedLimit).append(" ");
					String s8 = stringbuilder5
							.append(UNITS_ARRAY[mDisplayUnits]).append(")")
							.toString();
					menu.findItem(2131296670).setTitle(s8);
					switch (mCurrentSpeedLimit) {
					case 0:
						menu.findItem(2131296668).setChecked(true);
						break;
					case 1:
						menu.findItem(2131296669).setChecked(true);
						break;
					case 2:
						menu.findItem(2131296670).setChecked(true);
						break;
					}
				}
			} else {
				menu.findItem(2131296663).setVisible(true);
				if (Integer.parseInt(android.os.Build.VERSION.SDK) < 9)
					menu.findItem(2131296666).setVisible(false);
				switch (mStoredOrientation) {
				case 0:
					menu.findItem(2131296664).setChecked(true);
					break;
				case 1:
					menu.findItem(2131296665).setChecked(true);
					break;
				case 2:
					break;
				case 3:
					menu.findItem(2131296666).setChecked(true);
					break;
				}
			}
		}

		return super.onPrepareOptionsMenu(menu);
	}

	protected void onResume() {
		super.onResume();
		SharedPreferences sharedpreferences = getSharedPreferences("PrefsFile",
				0);
		mNotifiedAboutScreen = sharedpreferences.getBoolean(
				"notifiedAboutScreen", false);
		if (!mIsScreenSupported && !mNotifiedAboutScreen) {
			AlertDialog.Builder builder = (new AlertDialog.Builder(this))
					.setTitle(2131099707).setMessage(2131099708);
			builder.setPositiveButton(2131099711, null);
			builder.setNegativeButton(2131099691,
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							finish();
						}
					}).show();
			mNotifiedAboutScreen = true;
		}
		mNotifiedAboutGPS = sharedpreferences.getBoolean("notifiedAboutGPS",
				false);
		checkGPSEnabled();
		mNotifiedAboutNetwork = sharedpreferences.getBoolean(
				"notifiedAboutNetwork", false);
		mHasNetworkAccess = isNetworkAvailable();
		mCurrentVersion = sharedpreferences.getInt("currentVersion", 0);
		if (mCurrentVersion < mVersionCode) {
			AlertDialog.Builder builder2 = (new AlertDialog.Builder(this))
					.setTitle(2131099946).setMessage(2131099947);
			builder2.setNeutralButton(2131099857, null).show();
		}
		if (mCurrentVersion == 0) {
			final Dialog dialog = new Dialog(this, 16973834);
			dialog.setContentView(2130903049);
			TextView textview = (TextView) dialog.findViewById(2131296647);
			TextView textview1 = (TextView) dialog.findViewById(2131296649);
			StringBuilder stringbuilder = (new StringBuilder(
					getString(2131099929))).append(" ");
			stringbuilder.append(mVersionName).append(" ");
			stringbuilder.append(getString(2131099930));
			textview.setText(stringbuilder.toString());
			Button button = (Button) dialog.findViewById(2131296650);
			Button button1 = (Button) dialog.findViewById(2131296651);
			if (!mHasNetworkAccess)
				button1.setEnabled(false);
			button.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					dialog.dismiss();
				}
			});
			button1.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					Uri uri = Uri.parse("http://www.codesector.com/speedview");
					Intent intent = new Intent("android.intent.action.VIEW",
							uri);
					startActivity(intent);
				}
			});
			dialog.show();
		}
		if (Integer.parseInt(android.os.Build.VERSION.SDK) < 5) {
			if (hasMatchingActivity("com.codesector.maverick.full",
					"com.codesector.maverick.full.Main")) {
				mMaverickInst = true;
				mMaverickVersion = VERSIONS[0];
				mMaverickButton.setEnabled(true);
			} else if (hasMatchingActivity("com.codesector.maverick.lite",
					"com.codesector.maverick.lite.Main")) {
				mMaverickInst = true;
				mMaverickVersion = VERSIONS[1];
				mMaverickButton.setEnabled(true);
			} else {
				mMaverickInst = false;
				if (mHasNetworkAccess) {
					mMaverickButton.setEnabled(true);
					mMaverickButton.setTextColor(-7829368);
				}
			}
		}
			if (mMaverickInst)
				mGPXFileLocation = "/maverick/tracks";
			else
				mGPXFileLocation = "/speedview/tracks";
			mIsRecording = sharedpreferences.getBoolean("isRecording", true);
			if (mIsGPSEnabled && !mIsRecording) {
				mSatelliteView.clearSatellites();
				mStatusMessage.setText(2131099664);
				mNumOfSatellites.setText(2131099665);
				mTipMessage.setText(2131099666);
				mAddressLine0.setText(2131099664);
				mAddressLine1.setText(2131099665);
			}
			mStoredDistance = sharedpreferences.getFloat("storedDistance", 0F);
			mStoredMaxSpeed = sharedpreferences.getFloat("storedMaxSpeed", 0F);
			mStoredMovingTime = sharedpreferences.getLong("storedMovingTime",
					0L);
			mStoredTotalTime = sharedpreferences.getLong("storedTotalTime", 0L);
			mStored0To60Time = sharedpreferences.getString("stored0To60Time",
					getString(2131099663));
			mStored0To100Time = sharedpreferences.getString("stored0To100Time",
					getString(2131099663));
			mStoredQtrMileTime = sharedpreferences.getString(
					"storedQtrMileTime", getString(2131099663));
			mDisplayUnits = sharedpreferences.getInt("displayUnits", 0);
			mWarningChecked = sharedpreferences.getBoolean("warningChecked",
					false);
			mCurrentSpeedLimit = sharedpreferences.getInt("currentSpeedLimit",
					0);
			mTownSpeedLimit = sharedpreferences.getInt("townSpeedLimit", 30);
			mHighwaySpeedLimit = sharedpreferences.getInt("highwaySpeedLimit",
					55);
			mFreewaySpeedLimit = sharedpreferences.getInt("freewaySpeedLimit",
					65);
			mSoundAlertToggled = sharedpreferences.getBoolean(
					"soundAlertToggled", false);
			if (sharedpreferences.getString("alertSoundUri", "").equals(""))
				mAlertSoundUri = null;
			else
				mAlertSoundUri = Uri.parse(sharedpreferences.getString(
						"alertSoundUri", ""));
			if (mAlertSoundUri != null) {
				mWarningSound = RingtoneManager.getRingtone(getBaseContext(),
						mAlertSoundUri);
			}
			mVibrationChecked = sharedpreferences.getBoolean(
					"vibrationChecked", false);
			mDigitSpeedoChecked = sharedpreferences.getBoolean(
					"digitSpeedoChecked", false);
			mDigitAddlDataToggled = sharedpreferences.getBoolean(
					"digitAddlDataToggled", false);
			mDigitDataSelected = sharedpreferences.getInt("digitDataSelected",
					-1);
			mMaxSpeedoChecked = sharedpreferences.getBoolean(
					"maxSpeedoChecked", false);
			mMaxSpeedoLimit = sharedpreferences.getInt("maxSpeedoLimit", 160);
			mUseHudChecked = sharedpreferences.getBoolean("useHudChecked",
					false);
			mAdvancedHudChecked = sharedpreferences.getBoolean(
					"advancedHudChecked", false);
			mAdvancedZoomChecked = sharedpreferences.getBoolean(
					"advancedZoomChecked", false);
			mCustomColorsChecked = sharedpreferences.getBoolean(
					"customColorsChecked", false);
			mSpeedBarColor = sharedpreferences.getInt("speedBarColor",
					-16776961);
			mPrimaryTextColor = sharedpreferences
					.getInt("primaryTextColor", -1);
			mSecondaryTextColor = sharedpreferences.getInt(
					"secondaryTextColor", -3355444);
			mRunInBGChecked = sharedpreferences.getBoolean("runInBGChecked",
					false);
			mTrackLoggingChecked = sharedpreferences.getBoolean(
					"trackLoggingChecked", false);
			mMinTimeBetweenPts = sharedpreferences.getInt("minTimeBetweenPts",
					0);
			mMinDistBetweenPts = sharedpreferences.getInt("minDistBetweenPts",
					4);
			mNarrowingChecked = sharedpreferences.getBoolean(
					"narrowingChecked", true);
			mMinimumAccuracy = sharedpreferences.getInt("minimumAccuracy", 4);
			mStreetAddrChecked = sharedpreferences.getBoolean(
					"streetAddrChecked", true);
			mDsblRotationChecked = sharedpreferences.getBoolean(
					"dsblRotationChecked", true);
			mFullScreenChecked = sharedpreferences.getBoolean(
					"fullScreenChecked", true);
			mBackgroundChecked = sharedpreferences.getBoolean(
					"backgroundChecked", true);
			if (!mFullScreenChecked) {
				if (mDsblRotationChecked
						&& (mStoredOrientation == 1 || mStoredOrientation == 3)
						|| !mDsblRotationChecked
						&& getResources().getConfiguration().orientation == 2)
					setFullScreenMode(true);
				else
					setFullScreenMode(false);
			} else {
				setFullScreenMode(true);
			}
			if (mBackgroundChecked) {
				mSpeedView.setBackgroundResource(2130837508);
				mStartupScreen.setBackgroundResource(2130837508);
			} else {
				mSpeedView.setBackgroundColor(-16777216);
				mStartupScreen.setBackgroundColor(-16777216);
			}
			if (!hasMatchingActivity("com.googlelabs.openspot",
					"com.google.android.apps.openspot.activities.MainActivity")) {

				mOpenSpotInst = false;
				if (mHasNetworkAccess) {
					mOpenSpotButton.setEnabled(true);
					mOpenSpotButton.setTextColor(-7829368);
				}

			} else {
				mOpenSpotInst = true;
				mOpenSpotButton.setEnabled(true);
			}

		if (!mWarningChecked) {
			mSpeedometerView.setDisplayUnits(mDisplayUnits);
			if (mCustomColorsChecked) {
				mOdometer.setTextColor(mSecondaryTextColor);
				mMaxSpeed.setTextColor(mSecondaryTextColor);
				if (mStoredOrientation != 0 && mHeading != null) {
					mHeading.setTextColor(mSecondaryTextColor);
				}
				mCompassSpeed.setTextColor(mSecondaryTextColor);
				mCompassOdometer.setTextColor(mSecondaryTextColor);
				mCompassElevation.setTextColor(mSecondaryTextColor);
				mCompassTime.setTextColor(mSecondaryTextColor);
			} else {
				mOdometer.setTextColor(-3355444);
				mMaxSpeed.setTextColor(-3355444);
				if (mStoredOrientation != 0 && mHeading != null)
					mHeading.setTextColor(-3355444);
				mCompassSpeed.setTextColor(-3355444);
				mCompassOdometer.setTextColor(-3355444);
				mCompassElevation.setTextColor(-3355444);
				mCompassTime.setTextColor(-3355444);
			}
			if (mDigitSpeedoChecked && mDigitAddlDataToggled)
				mSpeedometerView.refreshView();
			mGraphView.mArrayPointer = sharedpreferences.getInt(
					"graphArrayPointer", 0);
			mGraphView.setHexArray(sharedpreferences.getString(
					"graphHexString", ""));
		} else {
			switch (mCurrentSpeedLimit) {
			case 0:
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
				break;
			case 1:
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
				break;
			case 2:
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
				break;
			}
			if (mTownSpeedLimit >= 100)
				mTownLimitNumbers.setTextSize(48F);
			else
				mTownLimitNumbers.setTextSize(60F);
			StringBuilder stringbuilder2 = new StringBuilder();
			stringbuilder2.append(mTownSpeedLimit).toString();
			mTownLimitNumbers.setText(stringbuilder2.toString());
			if (mHighwaySpeedLimit >= 100)
				mHighwayLimitNumbers.setTextSize(48F);
			else
				mHighwayLimitNumbers.setTextSize(60F);
			StringBuilder stringbuilder3 = new StringBuilder();
			stringbuilder3.append(mHighwaySpeedLimit).toString();
			mHighwayLimitNumbers.setText(stringbuilder3.toString());
			if (mFreewaySpeedLimit >= 100)
				mFreewayLimitNumbers.setTextSize(48F);
			else
				mFreewayLimitNumbers.setTextSize(60F);
			StringBuilder stringbuilder4 = new StringBuilder();
			stringbuilder4.append(mFreewaySpeedLimit).toString();
			mFreewayLimitNumbers.setText(stringbuilder4.toString());
		}

		if (mSelectedDashboard == 4) {
			switch (mDisplayUnits) {
			case 0:
				mFrom0To100Row.setVisibility(8);
				mFrom0To60Row.setVisibility(0);
				mFrom0To100Button.setVisibility(8);
				mFrom0To60Button.setVisibility(0);
				mAccelerationLayout.setVisibility(0);
				break;
			case 1:
				mFrom0To60Row.setVisibility(8);
				mFrom0To100Row.setVisibility(0);
				mFrom0To60Button.setVisibility(8);
				mFrom0To100Button.setVisibility(0);
				mAccelerationLayout.setVisibility(0);
				break;
			case 2:
				mAccelerationLayout.setVisibility(8);
				break;
			}
		}
		if (mIsRecording) {
			mLocationManager.requestLocationUpdates("gps", 0L, 0F,
					mLocationListener);
			mLocationManager.addGpsStatusListener(mGPSListener);
		} else {
			mRecordingStatus.setText(2131099767);
			mRecordingButton.setText(2131099766);
		}

		mAccelerationInfo.setText(2131099759);
		if (mTrackLoggingChecked) {
			mGPXExportStatus.setText(2131099770);
			mExportGPXButton.setEnabled(true);
			if (mHasNetworkAccess)
				mSendGPXButton.setEnabled(true);
		} else {
			mGPXExportStatus.setText(2131099769);
			mExportGPXButton.setEnabled(false);
			mSendGPXButton.setEnabled(false);
		}
		mSensorManager.registerListener(mOrientationListener,
				mSensorManager.getDefaultSensor(3), 2);
		mSessionStartTime = System.nanoTime();
		mFirstFixTime = 0L;
		mShareButtonPressed = false;
		mSettingsButtonPressed = false;
		mFeaturedButtonPressed = false;
		ActivityManager activitymanager = (ActivityManager) getSystemService("activity");
		Iterator iterator = activitymanager.getRunningServices(2147483647)
				.iterator();

		while (iterator.hasNext()) {
			String s21 = ((android.app.ActivityManager.RunningServiceInfo) iterator
					.next()).service.getClassName();
			if ("com.codesector.speedview.pro.BackgroundService".equals(s21)) {
				Intent intent = new Intent(this, BackgroundService.class);
				bindService(intent, mBackgroundConn, 0);
			}
		}

		displayStoredData();
		if (mDsblRotationChecked) {
			mStoredOrientation = sharedpreferences.getInt("storedOrientation",
					0);
			switch (mStoredOrientation) {
			case 2: // '\002'
			default:
				return;

			case 0: // '\0'
				setRequestedOrientation(1);
				return;

			case 1: // '\001'
				setRequestedOrientation(0);
				return;

			case 3: // '\003'
				setRequestedOrientation(8);
				break;
			}
			return;
		}
		setRequestedOrientation(4);
		if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 9) {
			mStoredOrientation = DisplayHelper.getRotation(this);
			return;
		}
		if (getResources().getConfiguration().orientation == 1) {
			mStoredOrientation = 0;
			return;
		}
		mStoredOrientation = 1;

	}

	public void refreshMainScreen() {
		if ((!mDsblRotationChecked || mStoredOrientation != 1
				&& mStoredOrientation != 3)
				&& (mDsblRotationChecked || getResources().getConfiguration().orientation != 2)) {
			return;
		} else {
			switch (mDisplayUnits) {
			case 0:
				mFrom0To100RowT.setVisibility(8);
				mFrom0To60RowT.setVisibility(0);
				mAccelerationLayoutT.setVisibility(0);
				break;
			case 1:
				mFrom0To60RowT.setVisibility(8);
				mFrom0To100RowT.setVisibility(0);
				mAccelerationLayoutT.setVisibility(0);
				break;
			case 2:
				mAccelerationLayoutT.setVisibility(8);
				break;
			}
		}
		return;
	}

	static final int ACCURACY = 3;
	static final float ACCURACY_VALUES[] = { 10F, 20F, 50F, 100F, 200F, 500F,
			1000F, 2000F, 5000F

	};
	static final int ADVANCED = 4;
	static final int AVERAGE_SPEED = 1;
	static final int COMPASS = 2;
	private static final String COMPASS_DIRECTIONS[] = { "N", "NE", "E", "SE",
			"S", "SW", "W", "NW", "N" };
	static final int DEF_MIN_ACCURACY = 4;
	static final int ELEVATION = 2;
	static final int FREEWAY = 2;
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
	final Runnable mAddressFound = new Runnable() {

		public void run() {
			if (mAddress != null) {
				String s = mAddress.getAddressLine(0);
				String s1 = mAddress.getAddressLine(1);
				String s2 = mAddress.getAddressLine(2);
				SpeedView speedview = SpeedView.this;
				String s3;
				TextView textview;
				if (s != null)
					s3 = s;
				else
					s3 = "";
				speedview.mAddressString = s3;
				if (s1 != null) {
					SpeedView speedview1 = SpeedView.this;
					speedview1.mAddressString = (new StringBuilder(
							String.valueOf(speedview1.mAddressString)))
							.append(", ").append(s1).toString();
					if (s2 != null) {
						s1 = (new StringBuilder(String.valueOf(s1)))
								.append(", ").append(s2).toString();
						SpeedView speedview2 = SpeedView.this;
						speedview2.mAddressString = (new StringBuilder(
								String.valueOf(speedview2.mAddressString)))
								.append(", ").append(s2).toString();
					}
				} else {
					s1 = "";
				}
				textview = mAddressLine0;
				if (s == null)
					s = "";
				textview.setText(s);
				mAddressLine1.setText(s1);
			}
		}
	};
	private TextView mAddressLine0;
	private TextView mAddressLine1;
	private String mAddressString;
	private RelativeLayout mAddressView;
	private LinearLayout mAdvancedScreen;
	private Uri mAlertSoundUri;
	private ServiceConnection mBackgroundConn = new ServiceConnection() {

		public void onServiceConnected(ComponentName componentname,
				IBinder ibinder) {
			mStoredDistance = ((BackgroundService.LocalBinder) ibinder)
					.getStoredDistance();
			mStoredMaxSpeed = ((BackgroundService.LocalBinder) ibinder)
					.getStoredMaxSpeed();
			mStoredMovingTime = ((BackgroundService.LocalBinder) ibinder)
					.getStoredMovingTime();
			mStoredTotalTime = ((BackgroundService.LocalBinder) ibinder)
					.getStoredTotalTime();
			displayStoredData();
			((BackgroundService.LocalBinder) ibinder).dumpCurrentTrack();
			try {
				unbindService(mBackgroundConn);
			} catch (IllegalArgumentException illegalargumentexception) {
				illegalargumentexception.printStackTrace();
			}
			stopService(new Intent(SpeedView.this, BackgroundService.class));
		}

		public void onServiceDisconnected(ComponentName componentname) {
		}
	};
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
	private TableRow mFrom0To60Row;
	private TableRow mFrom0To60RowT;
	private LinearLayout mFrom0To60Screen;
	private TextView mFrom0To60Speed;
	private String mFrom0To60String;
	private TextView mFrom0To60Time;

	private static boolean mIsScreenSupported;

	final Runnable mGPSIsDisabled = new Runnable() {

		public void run() {
			mAddressLine0.setText(2131099734);
			mAddressLine1.setText(2131099736);
		}
	};
	private MyGPSListener mGPSListener;
	private TextView mGPXExportStatus;
	private Geocoder mGeocoder;
	private Button mGoogleMapsButton;
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
	private TextView mLowAccuracy;
	private LinearLayout mMainScreen;
	private Button mMaverickButton;
	private ImageView mMaxField;
	private TextView mMaxSpeed;
	private int mMinDistBetweenPts;
	private int mMinTimeBetweenPts;
	private boolean mMinimizeButtonPressed;
	private int mMinimumAccuracy;
	private boolean mNarrowingChecked;
	final Runnable mNetworkFailure = new Runnable() {

		public void run() {
			mAddressLine0.setText(2131099732);
			mAddressLine1.setText(2131099733);
		}
	};
	private boolean mNotifiedAboutGPS;
	private boolean mNotifiedAboutNetwork;
	private boolean mNotifiedAboutScreen;
	private TextView mNumOfSatellites;
	private TextView mNumberOfSats;
	private TextView mOdometer;
	private ImageView mOdometerField;
	private Button mOpenSpotButton;
	private SensorEventListener mOrientationListener = new SensorEventListener() {

		public void onAccuracyChanged(Sensor sensor, int i) {
		}

		public void onSensorChanged(SensorEvent sensorevent) {
			float f = sensorevent.values[0];
			if (mLastLocation != null && mLastLocation.getSpeed() >= 1.4F) {
				if (SpeedView.mSelectedDashboard != 2) {
					if (SpeedView.mSelectedDashboard == 1)
						mCompassView.onSpeedChanged(f);
				} else {
					mCompassMode.onSensorChanged(f);
					mCompassSource.setText(2131099747);
				}
			}
			return;
		}
	};
	private ProgressDialog mProgressDialog;
	private boolean mQtrMileReached;
	private String mQtrMileString;
	private Button mQuarterMileButton;
	private TextView mQuarterMileDist;
	private TextView mQuarterMileInfo;
	private TextView mQuarterMileResult;
	private LinearLayout mQuarterMileScreen;
	private TextView mQuarterMileSpeed;
	private TextView mQuarterMileTime;
	private TextView mQuarterMileUnits;
	private LinearLayout mQuickLaunchLayout;
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
	private TextView mSpeedOverallAvg;
	private RelativeLayout mSpeedView;
	private int mSpeedWarning;
	private SpeedometerView mSpeedometerView;
	private RelativeLayout mStartupScreen;
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
	private ImageView mTownLimitDec;
	private ImageView mTownLimitInc;
	private TextView mTownLimitNumbers;
	private ImageView mTownLimitSign;
	private RelativeLayout mTownLimitToggle;
	private int mTownSpeedLimit;
	private StringBuilder mTrackBuffer = new StringBuilder();
	private File mTrackLogFile;
	private boolean mTrackLoggingChecked;
	private TextView mTripDistance;
	private TextView mTripTimeMoving;
	private TextView mTripTimeStopped;
	private TextView mTripTimeTotal;
	final Runnable mUnableToGetAddress = new Runnable() {

		public void run() {
			mAddressLine0.setText(2131099730);
			mAddressLine1.setText(2131099731);
		}
	};
	private boolean mVibrationChecked;
	private Vibrator mVibrator;
	private ViewStub mViewStub;
	private Ringtone mWarningSound;
	final Handler progressHandler = new Handler() {

		public void handleMessage(Message message) {
			mProgressDialog.dismiss();
			switch (message.what) {
			case 0:
				(new android.app.AlertDialog.Builder(SpeedView.this))
						.setTitle(2131099776)
						.setMessage(2131099777)
						.setNeutralButton(
								2131099939,
								new android.content.DialogInterface.OnClickListener() {

									public void onClick(
											DialogInterface dialoginterface,
											int i) {
									}
								}).show();
				break;
			case 1:
				if (mSendTrackInit) {
					String s = message.getData().getString("file_path");
					Intent intent = new Intent("android.intent.action.SEND");
					intent.setType("application/gpx");
					intent.putExtra("android.intent.extra.SUBJECT",
							getString(2131099780));
					intent.putExtra("android.intent.extra.TEXT",
							getString(2131099781));
					intent.putExtra("android.intent.extra.STREAM", Uri
							.parse((new StringBuilder("file://")).append(s)
									.toString()));
					startActivity(Intent.createChooser(intent,
							getString(2131099773)));
				} else {
					mGPXExportStatus.setText((new StringBuilder(String
							.valueOf(getString(2131099771))))
							.append(SpeedView.mGPXFileLocation)
							.append(getString(2131099772)).toString());
				}
				break;
			case 2:
				(new android.app.AlertDialog.Builder(SpeedView.this))
						.setTitle(2131099726)
						.setMessage(2131099727)
						.setNeutralButton(
								2131099939,
								new android.content.DialogInterface.OnClickListener() {

									public void onClick(
											DialogInterface dialoginterface,
											int i) {
									}
								}).show();
				break;
			}
			return;
		}
	};

}

/*
 * DECOMPILATION REPORT
 *
 * Decompiled from: H:\AndroidProject\SpeedGps\libs\GPS娴嬮�.jar Total time: 557
 * ms Jad reported messages/errors: Couldn't fully decompile method
 * onGpsStatusChanged Couldn't fully decompile method onLocationChanged Couldn't
 * fully decompile method onSensorChanged Couldn't fully decompile method
 * distanceToString Couldn't fully decompile method getDisplaySpeed Couldn't
 * resolve all exception handlers in method refreshAdView Couldn't fully
 * decompile method saveCurrentTrack Couldn't resolve all exception handlers in
 * method saveCurrentTrack Couldn't fully decompile method switchToScreen
 * Couldn't fully decompile method onClick Couldn't fully decompile method
 * onClick Couldn't resolve all exception handlers in method onClick Couldn't
 * fully decompile method onClick Couldn't resolve all exception handlers in
 * method onClick Couldn't fully decompile method onCreateContextMenu Couldn't
 * fully decompile method onCreateDialog Couldn't fully decompile method
 * onKeyDown Couldn't fully decompile method onOptionsItemSelected Couldn't
 * fully decompile method onPrepareOptionsMenu Couldn't fully decompile method
 * onResume Couldn't fully decompile method refreshMainScreen Couldn't fully
 * decompile method selectTab Couldn't fully decompile method handleMessage Exit
 * status: 0 Caught exceptions:
 */