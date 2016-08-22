package com.mini.server.tools;

import java.text.SimpleDateFormat;

public class Variable {

	public static SimpleDateFormat formats = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
	public static String testId = "ffe05e21-bd04-4b51-8121-2858751a8724,test";
	public static String online_softindex = "";

	public static String getOnline_softindex() {
		return online_softindex;
	}
	public static void setOnline_softindex(String online_softindex) {
		Variable.online_softindex = online_softindex;
	}

}
