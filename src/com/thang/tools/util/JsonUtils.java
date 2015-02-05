package com.thang.tools.util;

import com.google.gson.Gson;

public class JsonUtils {

	private static Gson gson=new Gson();
	
	public static String toJsonStr(Object obj){
		return gson.toJson(obj);
	}
	
	public static String toJsonStr(String name,Object obj){
		return "{"+name+":"+toJsonStr(obj)+"}";
	}
	
}
