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


    /**
     * Returns stream of fields that are annotated with {@link Filterable}
     *
     * @param classType Entity
     * @return Stream of Field
     */
    public static Stream<Field> getFilterableFields(Class classType) {
        return Arrays.stream(classType.getDeclaredFields())
                .filter(field -> field.getAnnotation(Filterable.class) != null);
    }


    /**
     * Returns the valid filterable units
     * @param filterUnits list of filter unit
     * @param classType Entity
     * @return valid filterable units
     */
    public static Stream<FilterUnit> getValidFilterUnits(List<FilterUnit> filterUnits, Class classType) {
        List<Field> filterableFieldsList = getFilterableFields(classType).collect(toList());

        return filterUnits.stream()
                .filter(filterUnit -> isValid(filterUnit, filterableFieldsList));
    }

    static boolean isValid(FilterUnit filterUnit, List<Field> filterableFields) {
        return filterableFields.stream()
                .anyMatch(field -> field.getName().equalsIgnoreCase(filterUnit.getKey()));
    }

}
