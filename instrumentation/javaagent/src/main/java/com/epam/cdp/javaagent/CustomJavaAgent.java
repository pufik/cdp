package com.epam.cdp.javaagent;

import java.lang.instrument.Instrumentation;

import com.epam.cdp.javaagent.transformer.JavasistTransformer;

public class CustomJavaAgent {
	public static void premain(String agentArguments, Instrumentation instrumentation) {
		instrumentation.addTransformer(new JavasistTransformer());
	}
}
