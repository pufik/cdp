package com.epam.cdp.reflection.sources.part2;

public class ClassB extends ClassA {
	public static String f10;
	private Integer f1;
	protected Long f2;
	public String f3;
	String f4;

	public static String getF10() {
		return f10;
	}

	public static void setF10(String f10) {
		ClassB.f10 = f10;
	}

	public Integer getF1() {
		return f1;
	}

	public void setF1(Integer f1) {
		this.f1 = f1;
	}

	public Long getF2() {
		return f2;
	}

	public void setF2(Long f2) {
		this.f2 = f2;
	}

	public String getF3() {
		return f3;
	}

	public void setF3(String f3) {
		this.f3 = f3;
	}

	public String getF4() {
		return f4;
	}

	public void setF4(String f4) {
		this.f4 = f4;
	}
}
