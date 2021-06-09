package com.provectus.kafka.ui.base;

import java.lang.reflect.Method;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.platform.commons.util.ClassUtils;
import org.junit.platform.commons.util.Preconditions;


public class CamelCaseToSpacedDisplayNameGenerator implements DisplayNameGenerator {
  @Override
  public String generateDisplayNameForClass(Class<?> testClass) {
    String name = testClass.getName();
    int lastDot = name.lastIndexOf('.');
    return name.substring(lastDot + 1).replaceAll("([A-Z])", " $1").toLowerCase();
  }

  @Override
  public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
    return nestedClass.getSimpleName();
  }

  @Override
  public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
    return testMethod.getName().replaceAll("([A-Z])", " $1").toLowerCase()
        + parameterTypesAsString(testMethod);
  }

  static String parameterTypesAsString(Method method) {
    Preconditions.notNull(method, "Method must not be null");
    return method.getParameterTypes().length == 0
        ? ""
        : '(' + ClassUtils.nullSafeToString(Class::getSimpleName, method.getParameterTypes()) + ')';
  }
}
