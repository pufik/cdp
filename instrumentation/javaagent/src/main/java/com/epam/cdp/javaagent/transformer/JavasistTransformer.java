package com.epam.cdp.javaagent.transformer;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class JavasistTransformer implements ClassFileTransformer {

	private static final String LOCAL_DATE_CLASS = "java.util.Date";
	private static final String CATCH_EXCEPTION_CLASS = "java.lang.Exception";
	private static final String START_INVOKE_TIME = "startTime";
	private static final String CLASS_FILTER = "com/epam/cdp/instrumentation";

	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer)
			throws IllegalClassFormatException {
		// System.out.println("Transform class: " + className);

		if (className.contains(CLASS_FILTER)) {
			ClassPool classPool = ClassPool.getDefault();
			try {
				CtClass clazz = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
				if (!clazz.isInterface()) {
					insertCode(clazz, classPool);
				}

				return clazz.toBytecode();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	private void insertCode(CtClass clazz, ClassPool classPool) throws Exception {
		CtClass dateClass = classPool.get(LOCAL_DATE_CLASS);
		CtClass exceptionClass = classPool.get(CATCH_EXCEPTION_CLASS);
		
		CtMethod[] methods = clazz.getDeclaredMethods();
		for (CtMethod method : methods) {
			method.addLocalVariable(START_INVOKE_TIME, dateClass);
			method.addCatch("{ System.out.println($e); return; }", exceptionClass);
			method.insertBefore(insertBefore(method));
			method.insertAfter(insertAfter());
		}
	}

	private String insertBefore(CtMethod method) {
		return "{startTime = new java.util.Date();}";
	}

	private String insertAfter() {
		return "{java.util.Date endTime = new java.util.Date(); "
				+ "System.out.print(\"Current method: \" + Thread.currentThread().getStackTrace()[1].getMethodName());"
				+ "System.out.print(\" Invovked from method: \" + Thread.currentThread().getStackTrace()[2].getMethodName());"
				+ "System.out.println(\"\\nExecute time:\\n\" + startTime + \"\\n\" + endTime);}";
	}
}
