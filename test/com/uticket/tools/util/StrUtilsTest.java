package com.uticket.tools.util;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thang.tools.util.StrUtils;

public class StrUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testJoin() {
		String[] strs={"a"};
		String s=StrUtils.join(strs, ",");
		System.out.println(s);
	}
	
	@Test
	public void testTrim(){
		String s="abcgood time asdf abc";
		String str=StrUtils.trim(s, "abc");
		System.out.println(str);
	}

}
