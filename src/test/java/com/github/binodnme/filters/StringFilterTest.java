package com.github.binodnme.filters;

import com.github.binodnme.pojo.FilterUnit;
import org.junit.jupiter.api.Test;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.mockito.Mockito.mock;

/**
 * @author Binod Shrestha <binodshrestha@lftechnology.com> on 11/7/17.
 */

class StringFilterTest {


    /**
     * HAWA TEST
     * @throws Exception
     */
    @Test
    void test() throws Exception {
        //given
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Root root = mock(Root.class);

        FilterUnit filterUnit = new FilterUnit("firstName", "eq", "haku");

        TypeFilter typeFilter = new StringFilter();

        //when
        Predicate predicate = typeFilter.buildPredicate(criteriaBuilder, root, filterUnit);

        //then
        System.out.println(predicate);

    }

}