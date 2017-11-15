package com.github.binodnme.filters;

import com.github.binodnme.exception.InvalidFilterUnitException;
import com.github.binodnme.exception.UnsupportedTypeException;
import com.github.binodnme.pojo.FilterUnit;
import com.github.binodnme.util.FilterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Binod Shrestha <binodshrestha@lftechnology.com> on 11/10/17.
 */
public class QueryBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryBuilder.class);

    private EntityManager em;

    private Root root;

    private Class entity;

    public QueryBuilder(EntityManager em) {
        this.em = em;
    }

    public <T> TypedQuery<T> getQuery(List<FilterUnit> filters, Class<T> entity) {
        this.entity = entity;
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(this.entity);
        this.root = criteriaQuery.from(this.entity);

        Stream<FilterUnit> validFilterUnits = FilterUtils.getValidFilterUnits(filters, entity);

        Predicate[] predicates = buildPredicates(validFilterUnits);

        criteriaQuery.select(criteriaQuery.getSelection())
                .where(predicates);

        return em.createQuery(criteriaQuery);
    }


    public <T> List<T> getResultSet(List<FilterUnit> filters, Class<T> entity) {
        TypedQuery<T> query = this.getQuery(filters, entity);
        return query.getResultList();
    }

    private Predicate[] buildPredicates(Stream<FilterUnit> validFilterUnits) {
        List<Predicate> predicates = validFilterUnits.map(this::buildPredicate)
                .collect(Collectors.toList());

        return predicates.toArray(new Predicate[]{});
    }

    private Predicate buildPredicate(FilterUnit filterUnit) {
        //determine filter type
        TypeFilter filter = getFilterType(filterUnit);

        //filter and return predicate
        return filter.buildPredicate(this.em.getCriteriaBuilder(), this.root, filterUnit);
    }

    private TypeFilter getFilterType(FilterUnit filterUnit) {
        try {
            Field declaredField = this.entity.getDeclaredField(filterUnit.getKey());

            Class<?> type = declaredField.getType();

            if (type.equals(String.class)) {
                return new StringFilter();
            } else {
                throw new UnsupportedTypeException(type.getName() + " type is not supported yet");
            }

        } catch (NoSuchFieldException e) {
            LOGGER.warn("Exception {}", e);
            throw new InvalidFilterUnitException("No such field with name {}" + filterUnit.getKey());
        }
    }

}