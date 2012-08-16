/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.codesector.speedview.pro;

import android.app.Activity;
import android.view.Display;
import android.view.WindowManager;

// Referenced classes of package com.codesector.speedview.pro:
//            SpeedView

public class DisplayHelper extends Activity
{

    public DisplayHelper()
    {
    }

    public static int getRotation(SpeedView speedview)
    {
        return ((WindowManager)speedview.getSystemService("window")).getDefaultDisplay().getRotation();
    }
}


/*
	DECOMPILATION REPORT

	Decompiled from: H:\AndroidProject\SpeedGps\libs\GPS测速.jar
	Total time: 15 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/