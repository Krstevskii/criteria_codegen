package com.ikrstevs.criteriacodegenprocessor.processor;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes({
  "com.ikrstevs.criteriacodegenprocessor.annotation.CriteriaFilter"
})
@SupportedSourceVersion(SourceVersion.RELEASE_11)
@AutoService(Processor.class)
public class CriteriaBuilderProcessor extends AbstractProcessor {

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    Set<? extends Element> annotatedElements = null;
    final Map<String, Set<Element>> map = new HashMap<>();

    for (final TypeElement annotation : annotations) {
      annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
    }

    if (annotatedElements == null) {
      return true;
    }
    annotatedElements.forEach(element -> {
      final Element enclosingElement = element.getEnclosingElement();
      final String enclosingElementName = enclosingElement.getSimpleName().toString();

      final Set<Element> elements = map.get(enclosingElementName);

      if (elements != null) {
        elements.add(element);
      } else {
        final Set<Element> emptyElements = new HashSet<>();
        emptyElements.add(element);
        map.put(enclosingElementName, emptyElements);
      }
    });

    for (final Map.Entry<String, Set<Element>> entry : map.entrySet()) {
      try {
        writeBuilderFile("com.ikrstevs.criteriacodegenapiimpl.criteria." + entry.getKey() + "Criteria",
          entry.getValue());
      } catch (RuntimeException | IOException ignored) {
        System.out.println("Exception");
      }

    }

    return true;
  }

  private void writeBuilderFile(final String className, final Set<? extends Element> annotatedElements)
    throws IOException {
    String packageName = null;
    int lastDot = className.lastIndexOf('.');
    if (lastDot > 0) {
      packageName = className.substring(0, lastDot);
    }
    String simpleClassName = className.substring(lastDot + 1);
    String builderClassName = className;
    String builderSimpleClassName = builderClassName
      .substring(lastDot + 1);

    JavaFileObject builderFile = processingEnv.getFiler()
      .createSourceFile(builderClassName);

    try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {

      if (packageName != null) {
        out.print("package ");
        out.print(packageName);
        out.println(";");
        out.println();
      }

      out.println("import com.ikrstevs.criteriacodegenapi.filter.*;");
      out.println("import lombok.Data;");

      out.println("@Data");
      out.print("public class ");
      out.print(builderSimpleClassName);
      out.println("{");
      out.println();
      annotatedElements.forEach(element -> {
        final TypeMirror clazz = element.asType();

        if (Objects.equals(clazz.toString(), "java.lang.String")) {
          out.println("private StringFilter " + element.getSimpleName().toString() + ";");
        } else if (Objects.equals(clazz.toString(), "java.lang.Long")) {
          out.println("private LongFilter " + element.getSimpleName().toString() + ";");
        } else if (Objects.equals(clazz.toString(), "java.time.LocalDate")) {
          out.println("private LocalDateFilter " + element.getSimpleName().toString() + ";");
        } else if (Objects.equals(clazz.toString(), "java.time.Instant")) {
          out.println("private InstantFilter " + element.getSimpleName().toString() + ";");
        }
      });
      out.println("}");

    } catch (RuntimeException exception) {
      System.out.println("Exception");
    }
  }
}
