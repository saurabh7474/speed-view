/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.codesector.speedview.pro;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.*;
import com.google.android.maps.*;
import java.io.*;
import java.util.List;

// Referenced classes of package com.codesector.speedview.pro:
//            SpeedView, LocationUtils, MapOverlay

public class ShareActivity extends MapActivity
{

    public ShareActivity()
    {
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

    private void shareLocation(int i)
    {
        Intent intent;
        String s;
        intent = new Intent("android.intent.action.SEND");
        intent.setType("plain/text");
        if(i != 0)
            intent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
        intent.putExtra("android.intent.extra.SUBJECT", getString(2131099813));
        s = (new StringBuilder(String.valueOf(getString(2131099814)))).append(":\n\n").append(getString(2131099804)).append(": ").append(mNiceLat).append("\n").append(getString(2131099805)).append(": ").append(mNiceLon).toString();
        if(SpeedView.mStreetAddrChecked)
            s = (new StringBuilder(String.valueOf(s))).append("\nStreet address: ").append(mAddressString).toString();
        if(mWebLinkCheckbox.isChecked())
            s = (new StringBuilder(String.valueOf(s))).append("\n").append(getString(2131099806)).append(": http://maps.google.com/maps?q=").append(LocationUtils.getPlainDD(mLastLocation.getLatitude())).append(",").append(LocationUtils.getPlainDD(mLastLocation.getLongitude())).toString();
        if(!mMapImageCheckbox.isChecked())
            break MISSING_BLOCK_LABEL_452;
        String s1;
        Bitmap bitmap = Bitmap.createBitmap(mMapView.getWidth(), mMapView.getHeight(), android.graphics.Bitmap.Config.RGB_565);
        mMapView.draw(new Canvas(bitmap));
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 40, bytearrayoutputstream);
        File file = new File((new StringBuilder(String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath()))).append("/speedview/images").toString());
        if(!file.exists())
            file.mkdirs();
        File file1 = new File(file, "map_image.png");
        file1.createNewFile();
        (new FileOutputStream(file1)).write(bytearrayoutputstream.toByteArray());
        intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(file1));
        s1 = (new StringBuilder(String.valueOf(s))).append("\n").append(getString(2131099807)).append(" ").append(getString(2131099815)).append(".").toString();
        s = s1;
_L2:
        intent.putExtra("android.intent.extra.TEXT", (new StringBuilder(String.valueOf(s))).append("\n\n").append(getString(2131099817)).toString());
        if(i == 0)
            intent = Intent.createChooser(intent, getString(2131099773));
        startActivity(intent);
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
        s = (new StringBuilder(String.valueOf(s))).append("\n").append(getString(2131099816)).append(".").toString();
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected boolean isRouteDisplayed()
    {
        return false;
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        getWindow().setFlags(128, 128);
        setContentView(2130903048);
        mShareLocScreen = (LinearLayout)findViewById(2131296637);
        if(!SpeedView.mDsblRotationChecked)
            break MISSING_BLOCK_LABEL_727;
        SpeedView.mStoredOrientation;
        JVM INSTR tableswitch 0 3: default 80
    //                   0 702
    //                   1 710
    //                   2 80
    //                   3 718;
           goto _L1 _L2 _L3 _L1 _L4
_L1:
        Bundle bundle1;
        if(!SpeedView.mFullScreenChecked)
        {
            if(SpeedView.mDsblRotationChecked && (SpeedView.mStoredOrientation == 1 || SpeedView.mStoredOrientation == 3) || !SpeedView.mDsblRotationChecked && getResources().getConfiguration().orientation == 2)
                setFullScreenMode(true);
            else
                setFullScreenMode(false);
        } else
        {
            setFullScreenMode(true);
        }
        if(SpeedView.mBackgroundChecked)
            mShareLocScreen.setBackgroundResource(2130837508);
        else
            mShareLocScreen.setBackgroundColor(-16777216);
        mMapView = (MapView)findViewById(2131296638);
        mMapView.setBuiltInZoomControls(true);
        mMapOverlays = mMapView.getOverlays();
        mItemizedOverlay = new MapOverlay(getResources().getDrawable(2130837523), this);
        mCoordAddrCheckbox = (CheckBox)findViewById(2131296639);
        mMapImageCheckbox = (CheckBox)findViewById(2131296641);
        mWebLinkCheckbox = (CheckBox)findViewById(2131296640);
        mCoordAddrCheckbox.setPadding(8 + mCoordAddrCheckbox.getPaddingLeft(), 0, 0, 0);
        mMapImageCheckbox.setPadding(8 + mMapImageCheckbox.getPaddingLeft(), 0, 0, 0);
        mWebLinkCheckbox.setPadding(8 + mWebLinkCheckbox.getPaddingLeft(), 0, 0, 0);
        if(SpeedView.mStreetAddrChecked)
        {
            mCoordAddrCheckbox.setEnabled(true);
            mCoordAddrCheckbox.setChecked(false);
        } else
        {
            mCoordAddrCheckbox.setEnabled(false);
            mCoordAddrCheckbox.setChecked(true);
        }
        mShareLocStatus = (TextView)findViewById(2131296642);
        mSelectAppButton = (Button)findViewById(2131296644);
        mShareByEmailButton = (Button)findViewById(2131296645);
        bundle1 = getIntent().getExtras();
        mLastLocation = (Location)bundle1.get("last_location");
        mAddressString = bundle1.getString("address_string");
        if(mLastLocation != null)
        {
            mShareLocStatus.setText(2131099808);
            mSelectAppButton.setEnabled(true);
            mShareByEmailButton.setEnabled(true);
            double d = mLastLocation.getLatitude();
            double d1 = mLastLocation.getLongitude();
            mNiceLat = LocationUtils.getNiceLatitude(d, 1);
            mNiceLon = LocationUtils.getNiceLongitude(d1, 1);
            mGeoPoint = new GeoPoint((int)(1000000D * d), (int)(1000000D * d1));
            OverlayItem overlayitem = new OverlayItem(mGeoPoint, "You are here!", (new StringBuilder(String.valueOf(getString(2131099804)))).append(": ").append(mNiceLat).append(", ").append(getString(2131099805)).append(": ").append(mNiceLon).toString());
            mItemizedOverlay.addOverlay(overlayitem);
            mMapOverlays.add(mItemizedOverlay);
            MapController mapcontroller = mMapView.getController();
            mapcontroller.animateTo(mGeoPoint);
            mapcontroller.setZoom(17);
            mMapView.invalidate();
            if(SpeedView.mStreetAddrChecked)
            {
                if(mAddressString != null)
                {
                    mCoordAddrCheckbox.setText(mAddressString);
                    mCoordAddrCheckbox.setEnabled(true);
                } else
                {
                    mCoordAddrCheckbox.setText((new StringBuilder(String.valueOf(getString(2131099732)))).append(". ").append(getString(2131099733)).toString());
                    mCoordAddrCheckbox.setEnabled(false);
                }
            } else
            {
                mCoordAddrCheckbox.setText((new StringBuilder(String.valueOf(getString(2131099804)))).append(": ").append(mNiceLat).append(",\n").append(getString(2131099805)).append(": ").append(mNiceLon).toString());
            }
            mMapImageCheckbox.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    if(!"mounted".equals(Environment.getExternalStorageState()))
                    {
                        Toast.makeText(getBaseContext(), 2131099812, 1).show();
                        mMapImageCheckbox.setChecked(false);
                    } else
                    if(mMapImageCheckbox.isChecked())
                        mShareLocStatus.setText(2131099811);
                    else
                        mShareLocStatus.setText(2131099808);
                }

                final ShareActivity this$0;

            
            {
                this$0 = ShareActivity.this;
                super();
            }
            }
);
            mSelectAppButton.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    shareLocation(0);
                }

                final ShareActivity this$0;

            
            {
                this$0 = ShareActivity.this;
                super();
            }
            }
);
            mShareByEmailButton.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    shareLocation(1);
                }

                final ShareActivity this$0;

            
            {
                this$0 = ShareActivity.this;
                super();
            }
            }
);
        } else
        {
            if(SpeedView.mStreetAddrChecked)
                mCoordAddrCheckbox.setText(2131099730);
            else
                mCoordAddrCheckbox.setText(2131099819);
            mShareLocStatus.setText(2131099818);
            mSelectAppButton.setEnabled(false);
            mShareByEmailButton.setEnabled(false);
        }
        return;
_L2:
        setRequestedOrientation(1);
          goto _L1
_L3:
        setRequestedOrientation(0);
          goto _L1
_L4:
        setRequestedOrientation(8);
          goto _L1
        setRequestedOrientation(4);
          goto _L1
    }

    private static final int SELECT_APP = 0;
    private static final int SHARE_BY_EMAIL = 1;
    private String mAddressString;
    private CheckBox mCoordAddrCheckbox;
    private GeoPoint mGeoPoint;
    private MapOverlay mItemizedOverlay;
    private Location mLastLocation;
    private CheckBox mMapImageCheckbox;
    private List mMapOverlays;
    private MapView mMapView;
    private String mNiceLat;
    private String mNiceLon;
    private Button mSelectAppButton;
    private Button mShareByEmailButton;
    private LinearLayout mShareLocScreen;
    private TextView mShareLocStatus;
    private CheckBox mWebLinkCheckbox;



}


/*
	DECOMPILATION REPORT

	Decompiled from: H:\AndroidProject\SpeedGps\libs\GPS测速.jar
	Total time: 22 ms
	Jad reported messages/errors:
Couldn't fully decompile method shareLocation
Couldn't resolve all exception handlers in method shareLocation
Couldn't fully decompile method onCreate
	Exit status: 0
	Caught exceptions:
*/