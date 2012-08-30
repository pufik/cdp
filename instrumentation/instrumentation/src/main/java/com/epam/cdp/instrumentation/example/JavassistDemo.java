package com.epam.cdp.instrumentation.example;

public class JavassistDemo {
	public void a() throws InterruptedException {
		Thread.sleep(1000);
		b();
	}

	void b() throws InterruptedException {
		Thread.sleep(1000);
		c(null, new Object(), new Integer(5));
	}

	protected void c(String z, Object... varargs) throws InterruptedException {
		Thread.sleep(1000);
		d();
	}

	private void d() throws InterruptedException {
		Thread.sleep(1000);
		//throw new RuntimeException("Time should be printed event after exception");
	}
}
