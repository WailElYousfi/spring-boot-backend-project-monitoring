package com.example.demo.services;

import java.text.DecimalFormat;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.UserRepository;
import com.example.demo.models.User;

public class test {
	@Autowired
	static UserRepository rep ;
	
	public static void main(String[] args) {
		/*long u = 5000;
		double d = ((double)u)/3600;
		DecimalFormat df = new DecimalFormat("0.0");
		System.out.printf(df.format(d));	*/
		String s = "1,2";
		float f = Float.parseFloat(s.replace(",", "."));
		System.out.println(f);
	}

}
