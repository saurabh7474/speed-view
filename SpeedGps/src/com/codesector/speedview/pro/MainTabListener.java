
package com.codesector.speedview.pro;

import android.app.FragmentTransaction;
import com.codesector.speedview.pro.R;


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

