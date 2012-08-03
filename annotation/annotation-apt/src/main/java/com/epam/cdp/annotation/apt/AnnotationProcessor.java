package com.epam.cdp.annotation.apt;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes({ "com.epam.cdp.annotation.event.EventHandler" })
public class AnnotationProcessor extends AbstractProcessor {

	private final String eventClassName = "com.epam.cdp.annotation.event.Event";

	private Set<Modifier> allowedModifiers;

	public AnnotationProcessor() {
		allowedModifiers = new HashSet<Modifier>();
		allowedModifiers.add(Modifier.PUBLIC);
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		for (TypeElement typeElement : annotations) {
			roundEnv.getElementsAnnotatedWith(typeElement).toString();

			Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(typeElement);

			for (Element element : elements) {
				checkElementType(element);
				checkMethodSignature((ExecutableElement) element);
			}
		}

		return false;
	}

	private void checkElementType(Element element) {
		if (!ElementKind.METHOD.equals(element.getKind())) {
			printError("Bad annotation place: " + element.toString());
		}
	}

	private void checkMethodSignature(ExecutableElement method) {
		List<? extends VariableElement> parameters = method.getParameters();

		checkMethodModifiers(method);

		if ((parameters.isEmpty()) || (parameters.size() != 1)) {
			printError("Method must contains only 1 parameter" + method.toString());
		} else {
			checkMethodParameter(method, parameters.get(0));
		}
	}

	private void checkMethodModifiers(ExecutableElement method) {
		Set<Modifier> modifiers = method.getModifiers();

		for (Modifier modifier : modifiers) {
			if (!allowedModifiers.contains(modifier)) {
				printError("Method can't be " + modifier.name() + ": " + method.toString());
				continue;
			}
		}
	}

	private void checkMethodParameter(ExecutableElement element, VariableElement parameter) {
		TypeMirror parameterTypeMirror = parameter.asType();
		TypeElement parameterTypeElement = (TypeElement) processingEnv.getTypeUtils().asElement(parameterTypeMirror);

		TypeElement baseAbstractionType = processingEnv.getElementUtils().getTypeElement(eventClassName);

		if (!isExtendsOrImplements(parameterTypeElement, baseAbstractionType)) {
			printError("Parameter is not valid: " + parameterTypeElement.toString() + " Method: " + element.toString());
		}
	}

	private void printError(String message) {
		processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, message);
	}

	public boolean isExtendsOrImplements(TypeElement element, TypeElement interfaceOrClass) {
		Types types = processingEnv.getTypeUtils();
		for (TypeMirror interfaceType : element.getInterfaces()) {
			if (types.asElement(interfaceType).equals(interfaceOrClass)) {
				return true;
			}
		}

		if (element.equals(interfaceOrClass)) {
			return true;
		}

		TypeElement superClass = (TypeElement) types.asElement(element.getSuperclass());
		return superClass != null && isExtendsOrImplements(superClass, interfaceOrClass);
	}
}
