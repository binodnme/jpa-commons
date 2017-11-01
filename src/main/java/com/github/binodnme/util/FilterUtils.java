package com.github.binodnme.util;

import com.github.binodnme.annotations.Filterable;
import com.github.binodnme.pojo.FilterUnit;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author Binod Shrestha <binodshrestha@lftechnology.com> on 10/31/17.
 */
public class FilterUtils {

    private FilterUtils() {
    }

    public static Stream<Field> getFilterableFields(Class classType) {
        return Arrays.stream(classType.getDeclaredFields())
                .filter(field -> field.getAnnotation(Filterable.class) != null);
    }

    public static Stream<FilterUnit> getUsableFilterUnits(List<FilterUnit> filterUnits, Class classType) {
        List<Field> filterableFieldsList = getFilterableFields(classType).collect(toList());

        return filterUnits.stream()
                .filter(filterUnit -> isUsable(filterUnit, filterableFieldsList));
    }

    static boolean isUsable(FilterUnit filterUnit, List<Field> filterableFields) {
        return filterableFields.stream()
                .anyMatch(field -> field.getName().equalsIgnoreCase(filterUnit.getKey()));
    }

}
