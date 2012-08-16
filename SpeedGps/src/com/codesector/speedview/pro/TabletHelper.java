/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) radix(10) lradix(10) 

package com.codesector.speedview.pro;

import android.app.ActionBar;

// Referenced classes of package com.codesector.speedview.pro:
//            MainTabListener, SpeedView

class TabletHelper
{

    public TabletHelper(SpeedView speedview)
    {
        mBaseClass = speedview;
    }

    public void addStartupTab(ActionBar actionbar)
    {
        actionbar.addTab(actionbar.newTab().setText(2131099678).setTabListener(mTabListener), 0);
    }

    public void enableTabs(ActionBar actionbar)
    {
        actionbar.setNavigationMode(2);
        mTabListener = new MainTabListener(mBaseClass);
        actionbar.addTab(actionbar.newTab().setText(2131099678).setTabListener(mTabListener));
        actionbar.addTab(actionbar.newTab().setText(2131099679).setTabListener(mTabListener));
        actionbar.addTab(actionbar.newTab().setText(2131099680).setTabListener(mTabListener));
        actionbar.addTab(actionbar.newTab().setText(2131099681).setTabListener(mTabListener));
        actionbar.addTab(actionbar.newTab().setText(2131099683).setTabListener(mTabListener));
    }

    public ActionBar getActionBar()
    {
        return mBaseClass.getActionBar();
    }

    public String getFirstTabText(ActionBar actionbar)
    {
        return (String)actionbar.getTabAt(0).getText();
    }

    public int getNavItemCount(ActionBar actionbar)
    {
        return actionbar.getNavigationItemCount();
    }

    public void hideActionBar(ActionBar actionbar)
    {
        actionbar.hide();
    }

    public void onResumeActivity(ActionBar actionbar)
    {
        if(actionbar.getNavigationItemCount() > 0)
        {
            int i = SpeedView.mSelectedDashboard;
            int j = 3;
            if(actionbar.getNavigationItemCount() == 4)
                j--;
            actionbar.removeTabAt(j);
            android.app.ActionBar.Tab tab = actionbar.newTab();
            int k;
            if(SpeedView.mUseHudChecked)
                k = 2131099681;
            else
                k = 2131099682;
            actionbar.addTab(tab.setText(k).setTabListener(mTabListener), j);
            if(i == 3)
                setSelectedNavItem(actionbar, 3);
        }
        mBaseClass.refreshMainScreen();
    }

    public void removeStartupTab(ActionBar actionbar)
    {
        actionbar.removeTabAt(0);
    }

    public void setSelectedNavItem(ActionBar actionbar, int i)
    {
        if(actionbar.getNavigationItemCount() == 4)
            i--;
        actionbar.setSelectedNavigationItem(i);
    }

    public void showActionBar(ActionBar actionbar)
    {
        actionbar.show();
    }

    private SpeedView mBaseClass;
    private MainTabListener mTabListener;
}


/*
	DECOMPILATION REPORT

	Decompiled from: H:\AndroidProject\SpeedGps\libs\GPS测速.jar
	Total time: 15 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/