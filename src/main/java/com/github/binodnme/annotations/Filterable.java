package com.github.binodnme.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Binod Shrestha <binodshrestha@lftechnology.com> on 10/31/17.
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Filterable {
    String mappedBy() default "";

    String filterKey() default "";
}
