package com.uticket.tools.util;


import org.junit.Test;

import com.thang.tools.util.DirUtils;

public class DirUtilsTest {

	
	
	
	@Test
	public void testRoot() {
		System.out.println(DirUtils.class.getResource("/").getFile());
		System.out.println(DirUtils.Root());
	}

	@Test
	public void testWebINF() {
		System.out.println(DirUtils.WebINF());
	}

}
