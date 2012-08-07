package com.codesector.speedview.pro;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import com.codesector.speedview.pro.R;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;
import java.util.ArrayList;

class MapOverlay extends ItemizedOverlay {

	public MapOverlay(Drawable drawable, Context context) {
		super(boundCenterBottom(drawable));
		mOverlays = new ArrayList();
		mContext = context;
	}

	public void addOverlay(OverlayItem overlayitem) {
		mOverlays.add(overlayitem);
		populate();
	}

	protected OverlayItem createItem(int i) {
		return (OverlayItem) mOverlays.get(i);
	}

	protected boolean onTap(int i) {
		OverlayItem overlayitem = (OverlayItem) mOverlays.get(i);
		android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
				mContext);
		builder.setTitle(overlayitem.getTitle());
		builder.setMessage(overlayitem.getSnippet());
		builder.setNeutralButton(2131099939,
				new android.content.DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialoginterface, int j) {
					}
				});
		builder.show();
		return true;
	}

	public int size() {
		return mOverlays.size();
	}

	private Context mContext;
	private ArrayList mOverlays;
}

/*
 * DECOMPILATION REPORT
 *
 * Decompiled from: E:\SmartActionProject\SpeedGps\libs\GPS����.jar Total time: 15
 * ms Jad reported messages/errors: Exit status: 0 Caught exceptions:
 */