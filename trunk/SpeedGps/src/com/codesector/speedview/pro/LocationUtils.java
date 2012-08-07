package com.codesector.speedview.pro;

import com.codesector.speedview.pro.R;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class LocationUtils {
	public static final int LOCATION_FMT_DD = 2;
	public static final int LOCATION_FMT_DDM = 0;
	public static final int LOCATION_FMT_DDMS = 1;

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

	public static String getNiceLatitude(double paramDouble, int paramInt) {
		// TODO
		return "";
	}

	public static String getNiceLongitude(double paramDouble, int paramInt) {
		// TODO
		return "";
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
}