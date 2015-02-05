package com.uticket;

public class Test {

	public static void main(String[] args) {
		String s="/list/*";
		System.out.println(s.endsWith(".js"));
		System.out.println(Test.class.getClassLoader().getResource("com").getFile());
		System.out.println("abc/def/goo\\d".replaceAll("/", ".").replaceAll("\\\\", "."));
		System.out.println("abc.def".split("\\.").length);
		System.out.println("\u9a8c\u8bc1\u7801");
	}

}
