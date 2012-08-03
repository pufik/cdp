package com.epam.cdp.annotation.apt;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Set;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;

public class EventProcessorFactory implements AnnotationProcessorFactory {

	@Override
	public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds, AnnotationProcessorEnvironment env) {
		return new EventAnnotationProcessor(env);
	}

	@Override
	public Collection<String> supportedAnnotationTypes() {
		Collection<String> annotations = new LinkedList<String>();
		annotations.add("com.epam.cdp.annotation.event.EventHandler");

		return annotations;
	}

	@Override
	public Collection<String> supportedOptions() {
		return Collections.emptyList();
	}

}
