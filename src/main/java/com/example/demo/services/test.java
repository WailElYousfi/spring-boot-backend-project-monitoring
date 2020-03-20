package com.example.demo.services;

import org.apache.commons.lang3.StringUtils;

public class test {

	public static void main(String[] args) {
		String a = "4h";
		String b = "2.5";
		
		String te = StringUtils.chop(a);
		float af = Float.parseFloat(te);
		System.out.println(te);
	}

}
