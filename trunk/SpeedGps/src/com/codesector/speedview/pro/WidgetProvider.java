/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.codesector.speedview.pro;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.*;
import android.widget.RemoteViews;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.codesector.speedview.pro:
//            UpdateWidgetService

public class WidgetProvider extends AppWidgetProvider {
	public void onDeleted(Context context, int ai[])
    {
        AppWidgetManager appwidgetmanager;
        int ai1[];
        RemoteViews remoteviews;
        SharedPreferences sharedpreferences;
        int i;
        ActivityManager activitymanager;
        appwidgetmanager = AppWidgetManager.getInstance(context);
        ai1 = appwidgetmanager.getAppWidgetIds(new ComponentName(context, com/codesector/speedview/pro/WidgetProvider));
        remoteviews = new RemoteViews(context.getPackageName(), 2130903051);
        sharedpreferences = context.getSharedPreferences("PrefsFile", 0);
        i = sharedpreferences.getInt("activeWidgetId", 0);
        activitymanager = (ActivityManager)context.getSystemService("activity");
        if(ai1.length != 0 && (ai1.length <= 0 || ai1[0] == i)) goto _L2; else goto _L1
_L1:
        Iterator iterator = activitymanager.getRunningServices(2147483647).iterator();
_L6:
        if(iterator.hasNext()) goto _L3; else goto _L2
_L2:
        if(ai1.length <= 0 || ai1[0] == i) goto _L5; else goto _L4
_L4:
        int j;
        boolean flag;
        Iterator iterator1;
        j = ai1[-1 + ai1.length];
        android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("activeWidgetId", j);
        editor.commit();
        Intent intent = new Intent(context, com/codesector/speedview/pro/UpdateWidgetService);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        intent.putExtra("appWidgetId", j);
        remoteviews.setOnClickPendingIntent(2131296655, PendingIntent.getService(context, 0, intent, 134217728));
        flag = false;
        iterator1 = activitymanager.getRunningServices(2147483647).iterator();
_L7:
        if(iterator1.hasNext())
            break MISSING_BLOCK_LABEL_319;
        if(flag)
        {
            remoteviews.setTextViewText(2131296657, context.getString(2131099923));
            remoteviews.setViewVisibility(2131296658, 8);
        } else
        {
            remoteviews.setTextViewText(2131296657, context.getString(2131099911));
            remoteviews.setTextViewText(2131296658, context.getString(2131099912).toUpperCase());
            remoteviews.setViewVisibility(2131296658, 0);
        }
        appwidgetmanager.updateAppWidget(j, remoteviews);
_L5:
        return;
_L3:
        if("com.codesector.speedview.pro.UpdateWidgetService".equals(((android.app.ActivityManager.RunningServiceInfo)iterator.next()).service.getClassName()))
            context.stopService(new Intent(context, UpdateWidgetService.class));
          goto _L6
        if("com.codesector.speedview.pro.BackgroundService".equals(((android.app.ActivityManager.RunningServiceInfo)iterator1.next()).service.getClassName()))
            flag = true;
          goto _L7
    }

	public void onDisabled(Context context) {
	}

	public void onEnabled(Context context) {
	}

	public void onUpdate(Context context, AppWidgetManager appwidgetmanager, int ai[])
    {
        int ai1[];
        RemoteViews remoteviews;
        ai1 = appwidgetmanager.getAppWidgetIds(new ComponentName(context, WidgetProvider.class));
        remoteviews = new RemoteViews(context.getPackageName(), 2130903051);
        if(ai1.length <= 1) goto _L2; else goto _L1
_L1:
        remoteviews.setTextViewText(2131296657, context.getString(2131099922));
        remoteviews.setViewVisibility(2131296658, 8);
        appwidgetmanager.updateAppWidget(ai1[-1 + ai1.length], remoteviews);
_L6:
        return;
_L2:
        if(ai1.length != 1) goto _L4; else goto _L3
_L3:
        int i;
        boolean flag;
        Iterator iterator;
        i = ai1[0];
        android.content.SharedPreferences.Editor editor = context.getSharedPreferences("PrefsFile", 0).edit();
        editor.putInt("activeWidgetId", i);
        editor.commit();
        Intent intent = new Intent(context,  UpdateWidgetService.class);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        intent.putExtra("appWidgetId", i);
        remoteviews.setOnClickPendingIntent(2131296655, PendingIntent.getService(context, 0, intent, 134217728));
        flag = false;
        iterator = ((ActivityManager)context.getSystemService("activity")).getRunningServices(2147483647).iterator();
_L7:
label0:
        {
            if(iterator.hasNext())
                break label0;
            if(flag)
            {
                remoteviews.setTextViewText(2131296657, context.getString(2131099923));
                remoteviews.setViewVisibility(2131296658, 8);
            } else
            {
                remoteviews.setTextViewText(2131296657, context.getString(2131099911));
                remoteviews.setTextViewText(2131296658, context.getString(2131099912).toUpperCase());
            }
            appwidgetmanager.updateAppWidget(i, remoteviews);
        }
_L4:
        if(true) goto _L6; else goto _L5
_L5:
        if("com.codesector.speedview.pro.BackgroundService".equals(((android.app.ActivityManager.RunningServiceInfo)iterator.next()).service.getClassName()))
            flag = true;
          goto _L7
    }
}

/*
 * DECOMPILATION REPORT
 * 
 * Decompiled from: H:\AndroidProject\SpeedGps\libs\GPS测速.jar Total time: 19 ms
 * Jad reported messages/errors: Couldn't fully decompile method onDeleted
 * Couldn't fully decompile method onUpdate Exit status: 0 Caught exceptions:
 */