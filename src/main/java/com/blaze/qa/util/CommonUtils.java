package com.blaze.qa.util;

import java.util.HashMap;

public class CommonUtils {
	public static HashMap<String, String> ArrayToMap(String Data) {
		HashMap<String, String> str= new HashMap<String, String>();
		if(Data.contains("Wind")|| Data.contains("wind")) {
			String replacedata= Data.replace("KPH", "");
			String[] split = replacedata.split("Gusting");
			String[] split2 = split[0].split(":");
			str.put(split2[0].trim(), split2[1].trim());
		}else {
			if(Data.contains("Temp")) {
				String TempRemove = Data.replace("Temp in ", "");
				String[] split = TempRemove.split(":");
				str.put(split[0].trim(), split[1].trim());
			} else if(Data.contains("Humidity")) {
				String TempRemove = Data.replace("%", "");
				String[] split = TempRemove.split(":");
				str.put(split[0].trim(), split[1].trim());
			}
			
			else {
				String[] split = Data.split(":");
				str.put(split[0].trim(), split[1].trim());
			}
		}
		return str;
	}
	
	public static double findvariancefromGivenData(double api, double web) {
		double variance = 0;
		double totaladd = api + web;
		double squaretotal = totaladd * totaladd;
		squaretotal = squaretotal / 2;
		double indivisqaure = (api * api) + (web * web);
		double subtractamopunt = indivisqaure - squaretotal;
		double onesubitem = 2 - 1;
		double Beforvar = subtractamopunt / onesubitem;
		variance = Math.sqrt(Beforvar);
		return variance;
	}
}
