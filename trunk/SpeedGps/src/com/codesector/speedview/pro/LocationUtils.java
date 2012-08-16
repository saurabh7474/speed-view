/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.codesector.speedview.pro;

import android.annotation.SuppressLint;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class LocationUtils {

	public static String getDD(double d) {
		DecimalFormat decimalformat = new DecimalFormat("00.00000");
		DecimalFormatSymbols decimalformatsymbols = decimalformat
				.getDecimalFormatSymbols();
		decimalformatsymbols.setDecimalSeparator('.');
		decimalformat.setDecimalFormatSymbols(decimalformatsymbols);
		String s;
		if (d == 0.0D) {
			s = "0.00000\260";
		} else {
			if (d < 0.0D)
				d = -d;
			Object aobj[] = new Object[1];
			aobj[0] = decimalformat.format(d);
			s = String.format("%s\260", aobj);
		}
		return s;
	}

	private static String getDDM(double d) {
		DecimalFormat decimalformat = new DecimalFormat("00.000");
		DecimalFormatSymbols decimalformatsymbols = decimalformat
				.getDecimalFormatSymbols();
		decimalformatsymbols.setDecimalSeparator('.');
		decimalformat.setDecimalFormatSymbols(decimalformatsymbols);
		String s;
		if (d == 0.0D) {
			s = "0\26000.000'";
		} else {
			if (d < 0.0D)
				d = -d;
			int i = (int) Math.floor(d);
			double d1 = 60D * (d - (double) i);
			Object aobj[] = new Object[2];
			aobj[0] = Integer.valueOf(i);
			aobj[1] = decimalformat.format(d1);
			s = String.format("%s\260%s'", aobj);
		}
		return s;
	}

	private static String getDDMS(double d) {
		DecimalFormat decimalformat = new DecimalFormat("00.00");
		DecimalFormatSymbols decimalformatsymbols = decimalformat
				.getDecimalFormatSymbols();
		decimalformatsymbols.setDecimalSeparator('.');
		decimalformat.setDecimalFormatSymbols(decimalformatsymbols);
		String s;
		if (d == 0.0D) {
			s = "0\26000'00.00\"";
		} else {
			if (d < 0.0D)
				d = -d;
			int i = (int) Math.floor(d);
			double d1 = 60D * (d - (double) i);
			int j = (int) Math.floor(d1);
			double d2 = 60D * (d1 - (double) j);
			Object aobj[] = new Object[3];
			aobj[0] = Integer.valueOf(i);
			aobj[1] = Integer.valueOf(j);
			aobj[2] = decimalformat.format(d2);
			s = String.format("%s\260%s'%s\"", aobj);
		}
		return s;
	}

	@SuppressLint({ "ParserError", "ParserError" })
	public static String getNiceLatitude(double d, int i) {
		StringBuilder stringbuilder = new StringBuilder();
		String s = "";
		switch (i) {
		case 0:
			s = getDDMS(d);
			break;
		case 1:
			break;
		case 2:
			s = getDD(d);
			break;
		default:
			s = getDDM(d);
			break;
		}
		stringbuilder.append(s);
		if (d > 0.0D)
			stringbuilder.append(" N");
		else
			stringbuilder.append(" S");
		return stringbuilder.toString();
	}

	public static String getNiceLongitude(double d, int i) {
		StringBuilder stringbuilder = new StringBuilder();
		String s = "";
		switch (i) {
		case 0:
			s = getDDM(d);
			break;
		case 1:
			s = getDDMS(d);
			break;
		case 2:
			s = getDD(d);
			break;
		default:
			return "";
		}
		stringbuilder.append(s);
		if (d > 0.0D)
			stringbuilder.append(" E");
		else
			stringbuilder.append(" W");
		return stringbuilder.toString();
	}

	public static String getPlainDD(double d) {
		DecimalFormat decimalformat = new DecimalFormat("00.00000");
		DecimalFormatSymbols decimalformatsymbols = decimalformat
				.getDecimalFormatSymbols();
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
 * DECOMPILATION REPORT
 * 
 * Decompiled from: H:\AndroidProject\SpeedGps\libs\GPS测速.jar Total time: 17 ms
 * Jad reported messages/errors: Couldn't fully decompile method getNiceLatitude
 * Couldn't fully decompile method getNiceLongitude Exit status: 0 Caught
 * exceptions:
 */