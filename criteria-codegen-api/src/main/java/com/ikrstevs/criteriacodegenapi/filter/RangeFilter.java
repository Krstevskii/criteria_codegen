package com.ikrstevs.criteriacodegenapi.filter;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Criteria API Range filter API object.
 *
 * @param <T> the type of the Range filter object
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class RangeFilter<T extends Comparable<? super T>> extends Filter<T> {

  private static final long serialVersionUID = 1L;

  private T greaterThan;
  private T lesserThan;
  private T greaterThanOrEqual;
  private T lesserThanOrEqual;

  /**
   * Constructor.
   *
   * @param filter the range filter object.
   */
  public RangeFilter(final RangeFilter<T> filter) {
    super(filter);
    this.greaterThan = filter.greaterThan;
    this.lesserThan = filter.lesserThan;
    this.greaterThanOrEqual = filter.greaterThanOrEqual;
    this.lesserThanOrEqual = filter.lesserThanOrEqual;
  }

  /**
   * Copy of the range filter object.
   *
   * @return a copy of the range filter object
   */
  public RangeFilter<T> copy() {
    return new RangeFilter<>(this);
  }
}
