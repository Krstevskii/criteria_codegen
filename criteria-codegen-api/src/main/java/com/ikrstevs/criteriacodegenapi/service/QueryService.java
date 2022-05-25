package com.ikrstevs.criteriacodegenapi.service;

import com.ikrstevs.criteriacodegenapi.filter.Filter;
import com.ikrstevs.criteriacodegenapi.filter.RangeFilter;
import com.ikrstevs.criteriacodegenapi.filter.StringFilter;

import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import javax.transaction.Transactional;

@Transactional
public abstract class QueryService<E> {

  protected <X> Specification<E> buildSpecification(final Filter<X> filter, final SingularAttribute<E, X> field) {
    return buildBaseSpecification(filter, root -> root.get(field));
  }

  protected <X> Specification<E> buildBaseSpecification(final Filter<X> filter,
    final Function<Root<E>, Expression<X>> metaClassFunction) {

    if (filter.getEquals() != null) {
      return equalsSpecification(metaClassFunction, filter.getEquals());
    } else if (filter.getIn() != null) {
      return inSpecification(metaClassFunction, filter.getIn());
    } else if (filter.getSpecified() != null) {
      return byFieldSpecification(metaClassFunction, filter.getSpecified());
    }

    return null;
  }

  protected Specification<E> buildStringSpecification(final StringFilter filter,
    final SingularAttribute<E, String> field) {
    final Function<Root<E>, Expression<String>> metaClassFunction = root -> root.get(field);

    final Specification<E> specification = buildBaseSpecification(filter, metaClassFunction);

    if (specification != null) {
      return specification;
    }

    if (filter.getContains() != null) {
      return containsSpecification(metaClassFunction, filter.getContains());
    }

    return null;
  }

  protected <X extends Comparable<? super X>> Specification<E> buildRangeSpecification(final RangeFilter<X> filter,
    final SingularAttribute<E, X> field) {

    final Function<Root<E>, Expression<X>> metaClassFunction = root -> root.get(field);

    Specification<E> specification = Specification.where(buildSpecification(filter, field));

    if (filter.getGreaterThan() != null) {
      specification = specification.and(greaterThan(metaClassFunction, filter.getGreaterThan()));
    }

    if (filter.getLesserThan() != null) {
      specification = specification.and(lesserThan(metaClassFunction, filter.getLesserThan()));
    }

    if (filter.getGreaterThanOrEqual() != null) {
      specification = specification.and(greaterThanOrEqual(metaClassFunction, filter.getGreaterThanOrEqual()));
    }

    if (filter.getLesserThanOrEqual() != null) {
      specification = specification.and(lesserThanOrEqual(metaClassFunction, filter.getLesserThanOrEqual()));
    }

    return specification;
  }

  protected <X> Specification<E> equalsSpecification(final Function<Root<E>, Expression<X>> metaClassFunction,
    final X value) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(metaClassFunction.apply(root), value);
  }

  protected <X> Specification<E> inSpecification(final Function<Root<E>, Expression<X>> metaClassFunction,
    final Collection<X> values) {
    return (root, query, criteriaBuilder) -> {
      In<X> in = criteriaBuilder.in(metaClassFunction.apply(root));

      for (final X value : values) {
        in = in.value(value);
      }

      return in;
    };
  }

  protected <X> Specification<E> byFieldSpecification(final Function<Root<E>, Expression<X>> metaClassFunction,
    final boolean specified) {
    return specified
      ? (root, query, criteriaBuilder) -> criteriaBuilder.isNotNull(metaClassFunction.apply(root))
      : (root, query, criteriaBuilder) -> criteriaBuilder.isNull(metaClassFunction.apply(root));
  }

  protected Specification<E> containsSpecification(final Function<Root<E>, Expression<String>> metaClassFunction,
    final String contains) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.like(
      criteriaBuilder.upper(metaClassFunction.apply(root)), wrapLikeQuery(contains));
  }

  protected <X extends Comparable<? super X>> Specification<E> greaterThan(
    final Function<Root<E>, Expression<X>> metaClassFunction, final X value) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(metaClassFunction.apply(root), value);
  }

  protected <X extends Comparable<? super X>> Specification<E> lesserThan(
    final Function<Root<E>, Expression<X>> metaClassFunction, final X value) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(metaClassFunction.apply(root), value);
  }

  protected <X extends Comparable<? super X>> Specification<E> greaterThanOrEqual(
    final Function<Root<E>, Expression<X>> metaClassFunction, final X value) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(metaClassFunction.apply(root), value);
  }

  protected <X extends Comparable<? super X>> Specification<E> lesserThanOrEqual(
    final Function<Root<E>, Expression<X>> metaClassFunction, final X value) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(metaClassFunction.apply(root), value);
  }

  private String wrapLikeQuery(final String value) {
    return "%" + value.toUpperCase() + "%";
  }
}
