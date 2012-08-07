package com.codesector.speedview.pro;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.RemoteViews;
import com.codesector.speedview.pro.R;
import java.util.Iterator;
import java.util.List;

public class WidgetProvider extends AppWidgetProvider {
	public void onDeleted(Context paramContext, int[] paramArrayOfInt) {
		//TODO
	}

	public void onDisabled(Context paramContext) {
	}

	public void onEnabled(Context paramContext) {
	}

	public void onUpdate(Context paramContext,
			AppWidgetManager paramAppWidgetManager, int[] paramArrayOfInt) {
		//TODO
	}
}