package com.epam.cdp;

import com.epam.cdp.instrumentation.TMPClass;
import com.epam.cdp.instrumentation.example.JavassistDemo;


public class AppLoader {

	public static void main(String[] args) {
		System.out.println("Start program...");
		
		TMPClass tmp = new TMPClass();
		tmp.say();

		JavassistDemo demo = new JavassistDemo();
		try {
			demo.a();
		} catch (InterruptedException e) {
			// TODO Change to Logger
			e.printStackTrace();
		}
	}
}
