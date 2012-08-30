package com.epam.cdp.reflection;

import com.epam.cdp.reflection.copy.DeepCopyProcessor;
import com.epam.cdp.reflection.sources.part2.ClassC;

public class AppPart2 {

	public static void main(String[] args) {
		DeepCopyProcessor copier = new DeepCopyProcessor();
		ClassC original = new ClassC();
		original.setF0("S1");
		original.setF1(2);
		original.setF1("SC2");
		original.setF2("S2");
		original.setF1(2);
		original.setF2(3L);
		original.setF3("S4");
		original.setF4("S5");
		original.setF10("S10");

		try {
			System.out.println(original.toString());
			System.out.println(copier.copy(original).toString());
		} catch (Exception e) {
			// TODO ADD LOGGER
			e.printStackTrace();
		}
	}

}
