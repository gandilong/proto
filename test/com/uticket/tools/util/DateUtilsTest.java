package com.uticket.tools.util;

import java.util.Date;

import org.junit.Test;

import com.thang.tools.util.DateUtils;

public class DateUtilsTest {

	@Test
	public void testGetSysmonth() {
		System.out.println(DateUtils.getSysmonth());
	}

	@Test
	public void testGetSysdate() {
		System.out.println(DateUtils.getSysdate());
	}

	@Test
	public void testGetSystime() {
		System.out.println(DateUtils.getSystime());
	}

	@Test
	public void testDateToStr() {
		System.out.println(DateUtils.dateToStr(new Date(), DateUtils.YYYY_MM_DD_HH_mm_ss_SS));
	}
	
	@Test
	public void testGetLastDay(){
		System.out.println("testGetLastDay:"+DateUtils.dateToStr(DateUtils.lastDay(), DateUtils.YYYY_MM_DD_HH_mm_ss_SS));
	}
	
	@Test
	public void testAgoDay(){
		System.out.println("testGetAgoDay:"+DateUtils.dateToStr(DateUtils.agoDay(1), DateUtils.YYYY_MM_DD_HH_mm_ss_SS));
	}

}
