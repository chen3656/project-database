package com.schen.projectdevice.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestEncoder {
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String pass = "admin";
		System.out.println(encoder.encode(pass));
	}
}
