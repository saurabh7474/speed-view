/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.codesector.speedview.pro;

import android.app.FragmentTransaction;

// Referenced classes of package com.codesector.speedview.pro:
//            SpeedView

class MainTabListener
    implements android.app.ActionBar.TabListener
{

    public MainTabListener(SpeedView speedview)
    {
        mBaseClass = speedview;
    }

    public void onTabReselected(android.app.ActionBar.Tab tab, FragmentTransaction fragmenttransaction)
    {
    }

    public void onTabSelected(android.app.ActionBar.Tab tab, FragmentTransaction fragmenttransaction)
    {
        int i = tab.getPosition();
        mBaseClass.selectTab(i);
    }

    public void onTabUnselected(android.app.ActionBar.Tab tab, FragmentTransaction fragmenttransaction)
    {
    }

    private SpeedView mBaseClass;
}


/*
	DECOMPILATION REPORT

	Decompiled from: H:\AndroidProject\SpeedGps\libs\GPS测速.jar
	Total time: 14 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/