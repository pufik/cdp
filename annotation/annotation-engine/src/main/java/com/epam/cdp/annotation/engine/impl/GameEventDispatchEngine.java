package com.epam.cdp.annotation.engine.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.epam.cdp.annotation.engine.EventDispatchEngine;
import com.epam.cdp.annotation.event.Event;
import com.epam.cdp.annotation.event.EventHandler;
import com.epam.cdp.annotation.handler.BaseEventHandler;

public class GameEventDispatchEngine implements EventDispatchEngine {
	private final List<BaseEventHandler> eventHandlers;

	private final Map<Class<?>, Collection<Method>> handlersMapping;

	public GameEventDispatchEngine() {
		eventHandlers = new LinkedList<BaseEventHandler>();
		handlersMapping = new HashMap<Class<?>, Collection<Method>>();
	}

	@Override
	public void registerEventHandler(BaseEventHandler eventHandler) {
		eventHandlers.add(eventHandler);
		parseEventHandlerMethods(eventHandler);
	}

	private void parseEventHandlerMethods(BaseEventHandler eventHandler) {
		Method[] methods = eventHandler.getClass().getDeclaredMethods();
		for (Method method : methods) {
			if (Modifier.isPublic(method.getModifiers())) {
				registerHandlerEventMethod(method);
			}
		}
	}

	private void registerHandlerEventMethod(Method handlerMethod) {
		Annotation[] annotations = handlerMethod.getDeclaredAnnotations();

		if (annotations != null) {
			for (Annotation annotation : annotations) {
				if (annotation.annotationType().equals(EventHandler.class)) {
					addMethodToMapping(handlerMethod);
				}
			}
		}
	}

	private void addMethodToMapping(Method method) {
		Class<?>[] parameterTypes = method.getParameterTypes();
		Collection<Method> handlers = null;

		for (Class<?> type : parameterTypes) {
			handlers = handlersMapping.get(type);
			
			if (handlers == null) {
				handlers = new LinkedList<Method>();
			}

			handlers.add(method);
			handlersMapping.put(type, handlers);
		}
	}

	@Override
	public void dispatchEvent(Event event) {
		Collection<Method> methods = handlersMapping.get(event.getClass());
		if (methods == null) {
			return;
		}

		for (Method handler : methods) {
			try {
				handler.invoke(handler.getDeclaringClass().newInstance(), event);
			} catch (Exception e) {
				// TODO: Change to logger
				e.printStackTrace();
			}
		}
	}
}
