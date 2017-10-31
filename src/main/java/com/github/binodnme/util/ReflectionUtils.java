package com.github.binodnme.util;

import com.github.binodnme.annotations.Filterable;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Binod Shrestha <binodshrestha@lftechnology.com> on 10/31/17.
 */
public class ReflectionUtils {

    private ReflectionUtils() {
    }

    public static Stream<Field> getFilterableFields(Class classType) {
        return Arrays.stream(classType.getDeclaredFields())
                .filter(field -> field.getAnnotation(Filterable.class) != null);
    }

}
