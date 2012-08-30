package com.epam.cdp.reflection.sources.part2;

public class ClassC extends ClassB {
	private String f1;
	
	protected String f2;
	
	public String f3;
	
	String f4;

	public String getF1C() {
		return f1;
	}

	public void setF1(String f1) {
		this.f1 = f1;
	}

	public String getF2C() {
		return f2;
	}

	public void setF2(String f2) {
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

	@Override
	public String toString() {
		return "ClassC [f1=" + f1 + ", f2=" + f2 + ", f3=" + f3 + ", f4=" + f4 + ", toString()=" + super.toString() + "]";
	}
}
