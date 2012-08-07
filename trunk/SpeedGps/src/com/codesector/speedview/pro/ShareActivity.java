package com.codesector.speedview.pro;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.codesector.speedview.pro.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class ShareActivity extends MapActivity {
	private static final int SELECT_APP = 0;
	private static final int SHARE_BY_EMAIL = 1;
	private String mAddressString;
	private CheckBox mCoordAddrCheckbox;
	private GeoPoint mGeoPoint;
	private MapOverlay mItemizedOverlay;
	private Location mLastLocation;
	private CheckBox mMapImageCheckbox;
	private List<Overlay> mMapOverlays;
	private MapView mMapView;
	private String mNiceLat;
	private String mNiceLon;
	private Button mSelectAppButton;
	private Button mShareByEmailButton;
	private LinearLayout mShareLocScreen;
	private TextView mShareLocStatus;
	private CheckBox mWebLinkCheckbox;

	 private void setFullScreenMode(boolean flag)
	    {
	        android.view.WindowManager.LayoutParams layoutparams = getWindow().getAttributes();
	        if(flag)
	            layoutparams.flags = 1024 | layoutparams.flags;
	        else
	            layoutparams.flags = -1025 & layoutparams.flags;
	        getWindow().setAttributes(layoutparams);
	    }

	private void shareLocation(int paramInt) {
		Intent localIntent = new Intent("android.intent.action.SEND");
		localIntent.setType("plain/text");
		if (paramInt != 0)
			localIntent.setClassName("com.google.android.gm",
					"com.google.android.gm.ComposeActivityGmail");
		localIntent.putExtra("android.intent.extra.SUBJECT",
				getString(2131099813));
		Object localObject = getString(2131099814) + ":\n\n"
				+ getString(2131099804) + ": " + this.mNiceLat + "\n"
				+ getString(2131099805) + ": " + this.mNiceLon;
		if (SpeedView.mStreetAddrChecked)
			localObject = localObject + "\nStreet address: "
					+ this.mAddressString;
		if (this.mWebLinkCheckbox.isChecked())
			localObject = localObject
					+ "\n"
					+ getString(2131099806)
					+ ": http://maps.google.com/maps?q="
					+ LocationUtils
							.getPlainDD(this.mLastLocation.getLatitude())
					+ ","
					+ LocationUtils.getPlainDD(this.mLastLocation
							.getLongitude());
		if (this.mMapImageCheckbox.isChecked())
			;
		try {
			Bitmap localBitmap = Bitmap.createBitmap(this.mMapView.getWidth(),
					this.mMapView.getHeight(), Bitmap.Config.RGB_565);
			this.mMapView.draw(new Canvas(localBitmap));
			ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
			localBitmap.compress(Bitmap.CompressFormat.PNG, 40,
					localByteArrayOutputStream);
			File localFile1 = new File(Environment
					.getExternalStorageDirectory().getAbsolutePath()
					+ "/speedview/images");
			if (!localFile1.exists())
				localFile1.mkdirs();
			File localFile2 = new File(localFile1, "map_image.png");
			localFile2.createNewFile();
			new FileOutputStream(localFile2).write(localByteArrayOutputStream
					.toByteArray());
			localIntent.putExtra("android.intent.extra.STREAM",
					Uri.fromFile(localFile2));
			localObject = localObject + "\n" + getString(2131099807) + " "
					+ getString(2131099815) + ".";
			localIntent.putExtra("android.intent.extra.TEXT", localObject
					+ "\n\n" + getString(2131099817));
			if (paramInt == 0)
				localIntent = Intent.createChooser(localIntent,
						getString(2131099773));
			startActivity(localIntent);
			return;
		} catch (Exception localException) {
			while (true) {
				localException.printStackTrace();
				localObject = localObject + "\n" + getString(2131099816) + ".";
			}
		}
	}

	protected boolean isRouteDisplayed() {
		return false;
	}

	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		//TODO
	}
}