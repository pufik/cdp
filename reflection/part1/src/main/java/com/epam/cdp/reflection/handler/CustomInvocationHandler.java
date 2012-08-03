package com.epam.cdp.reflection.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

import com.epam.cdp.reflection.sources.part1.LevelDispatcher;

public class CustomInvocationHandler implements InvocationHandler {
	private LevelDispatcher dispatcher;

	public CustomInvocationHandler(LevelDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		showMethodInfo(method);
		return invokeMethod(method, args);
	}

	private Object invokeMethod(Method method, Object[] args) throws Exception {
		Date startDate = new Date();
		Object result = method.invoke(dispatcher, args);
		Date endDate = new Date();
		
		printMessage("Invoke time (ms):" + (endDate.getTime() - startDate.getTime()));
		printMessage("-----------------------------------");
		
		return result;
	}

	private void showMethodInfo(Method method) {
		printMessage("----- Method: " + method.getName() + " -------");
		showDeclaredClassInfo(method.getDeclaringClass());
		showParametersInfo(method.getParameterTypes());
		showAnnotationsInfo(method.getDeclaredAnnotations());
	}

	private void showDeclaredClassInfo(Class<?> clazz) {
		printMessage("Declared class: " + clazz.getName());
	}

	private void showParametersInfo(Class<?>[] parameters) {
		printMessage("Parameters number: " + parameters.length);
		for (Class<?> parameter : parameters) {
			printMessage("Parameter: " + parameter.getName());
		}
	}
	
	private void showAnnotationsInfo(Annotation[] annotations) {
		printMessage("Annotations number: " + annotations.length);
		for (Annotation annotation : annotations) {
			printMessage("Annotation: " + annotation.annotationType().getName());
		}
	}

	private void printMessage(String message) {
		System.out.println(message);
	}
}