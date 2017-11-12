package com.github.binodnme.filter;

import com.github.binodnme.pojo.FilterUnit;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author Binod Shrestha <binodshrestha@lftechnology.com> on 11/2/17.
 */
public interface TypeFilter {

    Predicate buildPredicate(CriteriaBuilder cb, Root root, FilterUnit filterUnit);

}
