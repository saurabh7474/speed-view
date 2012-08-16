/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.codesector.speedview.pro;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class LocationUtils
{

    public LocationUtils()
    {
    }

    public static String getDD(double d)
    {
        DecimalFormat decimalformat = new DecimalFormat("00.00000");
        DecimalFormatSymbols decimalformatsymbols = decimalformat.getDecimalFormatSymbols();
        decimalformatsymbols.setDecimalSeparator('.');
        decimalformat.setDecimalFormatSymbols(decimalformatsymbols);
        String s;
        if(d == 0.0D)
        {
            s = "0.00000\260";
        } else
        {
            if(d < 0.0D)
                d = -d;
            Object aobj[] = new Object[1];
            aobj[0] = decimalformat.format(d);
            s = String.format("%s\260", aobj);
        }
        return s;
    }

    private static String getDDM(double d)
    {
        DecimalFormat decimalformat = new DecimalFormat("00.000");
        DecimalFormatSymbols decimalformatsymbols = decimalformat.getDecimalFormatSymbols();
        decimalformatsymbols.setDecimalSeparator('.');
        decimalformat.setDecimalFormatSymbols(decimalformatsymbols);
        String s;
        if(d == 0.0D)
        {
            s = "0\26000.000'";
        } else
        {
            if(d < 0.0D)
                d = -d;
            int i = (int)Math.floor(d);
            double d1 = 60D * (d - (double)i);
            Object aobj[] = new Object[2];
            aobj[0] = Integer.valueOf(i);
            aobj[1] = decimalformat.format(d1);
            s = String.format("%s\260%s'", aobj);
        }
        return s;
    }

    private static String getDDMS(double d)
    {
        DecimalFormat decimalformat = new DecimalFormat("00.00");
        DecimalFormatSymbols decimalformatsymbols = decimalformat.getDecimalFormatSymbols();
        decimalformatsymbols.setDecimalSeparator('.');
        decimalformat.setDecimalFormatSymbols(decimalformatsymbols);
        String s;
        if(d == 0.0D)
        {
            s = "0\26000'00.00\"";
        } else
        {
            if(d < 0.0D)
                d = -d;
            int i = (int)Math.floor(d);
            double d1 = 60D * (d - (double)i);
            int j = (int)Math.floor(d1);
            double d2 = 60D * (d1 - (double)j);
            Object aobj[] = new Object[3];
            aobj[0] = Integer.valueOf(i);
            aobj[1] = Integer.valueOf(j);
            aobj[2] = decimalformat.format(d2);
            s = String.format("%s\260%s'%s\"", aobj);
        }
        return s;
    }

    public static String getNiceLatitude(double d, int i)
    {
        StringBuilder stringbuilder = new StringBuilder();
        i;
        JVM INSTR tableswitch 0 2: default 36
    //                   0 76
    //                   1 36
    //                   2 67;
           goto _L1 _L2 _L1 _L3
_L2:
        break MISSING_BLOCK_LABEL_76;
_L1:
        String s = getDDMS(d);
_L4:
        stringbuilder.append(s);
        if(d > 0.0D)
            stringbuilder.append(" N");
        else
            stringbuilder.append(" S");
        return stringbuilder.toString();
_L3:
        s = getDD(d);
          goto _L4
        s = getDDM(d);
          goto _L4
    }

    public static String getNiceLongitude(double d, int i)
    {
        StringBuilder stringbuilder = new StringBuilder();
        i;
        JVM INSTR tableswitch 0 2: default 36
    //                   0 78
    //                   1 87
    //                   2 43;
           goto _L1 _L2 _L3 _L4
_L1:
        String s1 = "";
_L5:
        return s1;
_L4:
        String s = getDD(d);
_L6:
        stringbuilder.append(s);
        if(d > 0.0D)
            stringbuilder.append(" E");
        else
            stringbuilder.append(" W");
        s1 = stringbuilder.toString();
        if(true) goto _L5; else goto _L2
_L2:
        s = getDDM(d);
          goto _L6
_L3:
        s = getDDMS(d);
          goto _L6
    }

    public static String getPlainDD(double d)
    {
        DecimalFormat decimalformat = new DecimalFormat("00.00000");
        DecimalFormatSymbols decimalformatsymbols = decimalformat.getDecimalFormatSymbols();
        decimalformatsymbols.setDecimalSeparator('.');
        decimalformat.setDecimalFormatSymbols(decimalformatsymbols);
        Object aobj[] = new Object[1];
        aobj[0] = decimalformat.format(d);
        return String.format("%s", aobj).replace(",", ".");
    }

    public static final int LOCATION_FMT_DD = 2;
    public static final int LOCATION_FMT_DDM = 0;
    public static final int LOCATION_FMT_DDMS = 1;
}


/*
	DECOMPILATION REPORT

	Decompiled from: H:\AndroidProject\SpeedGps\libs\GPS测速.jar
	Total time: 17 ms
	Jad reported messages/errors:
Couldn't fully decompile method getNiceLatitude
Couldn't fully decompile method getNiceLongitude
	Exit status: 0
	Caught exceptions:
*/