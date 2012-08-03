package com.epam.cdp.annotation.apt;

import java.util.Collection;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.AnnotationMirror;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import com.sun.mirror.declaration.Declaration;
import com.sun.mirror.util.SourcePosition;

public class EventAnnotationProcessor implements AnnotationProcessor {

	private final AnnotationProcessorEnvironment processorEnv;

	private AnnotationTypeDeclaration typeDeclaration;

	public EventAnnotationProcessor(AnnotationProcessorEnvironment processorEnv) {
		super();
		this.processorEnv = processorEnv;
		this.typeDeclaration = (AnnotationTypeDeclaration) processorEnv.getTypeDeclaration("com.epam.cdp.annotation.event.EventHandler");
	}

	@Override
	public void process() {
		processorEnv.getMessager().printNotice("APT start processing...");
		
		Collection<Declaration> declarations = processorEnv.getDeclarationsAnnotatedWith(typeDeclaration);
		for (Declaration declaration : declarations) {
			processNoteAnnotations(declaration);
		}

		processorEnv.getMessager().printNotice("APT end processing.");
	}

	private void processNoteAnnotations(Declaration declaration) {
		Collection<AnnotationMirror> annotations = declaration.getAnnotationMirrors();
		for (AnnotationMirror mirror : annotations) {
			if (mirror.getAnnotationType().getDeclaration().equals(typeDeclaration)) {
				SourcePosition position = mirror.getPosition();
	
				processorEnv.getMessager().printNotice("Declaration: " + declaration.toString());
				processorEnv.getMessager().printNotice("Position: " + position);
			}
		}
	}

}
