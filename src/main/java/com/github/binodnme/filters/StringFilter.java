package com.github.binodnme.filters;

import com.github.binodnme.pojo.FilterUnit;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author Binod Shrestha <binodshrestha@lftechnology.com> on 11/7/17.
 */
public class StringFilter implements TypeFilter {

    @Override
    public Predicate buildPredicate(CriteriaBuilder criteriaBuilder, Root root, FilterUnit filterUnit) {

        switch (filterUnit.getOperator()) {
            case "eq":
                return criteriaBuilder.equal(root.get(filterUnit.getKey()), filterUnit.getValue());
            case "like":
                return criteriaBuilder.like(root.get(filterUnit.getKey()), filterUnit.getValue());
            default:
                return null;
        }

    }
}
