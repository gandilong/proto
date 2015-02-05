package com.uticket.tools.util;

import java.util.HashSet;
import java.util.Iterator;

public class StringBufferTest {

	public static void main(String[] args) {

		StringBuffer sber=new StringBuffer("have a good time :abc and you! yes :abc hoho!");
		String s=":abc";
		int i=sber.indexOf(s);
		HashSet<Integer> has=new HashSet<Integer>();
		while(-1!=i){
			//sber.delete(i, s.length());
			//sber.insert(i,"110");
			has.add(i);
			i=sber.indexOf(s, i+1);
		}
		Iterator<Integer> it=has.iterator();
		int k=0;
		while(it.hasNext()){
			k=it.next();
			sber.replace(k, k+s.length(), "222");	
		}
		//一次循环只能替换一个字符串
		System.out.println(sber.toString());
	}

}
