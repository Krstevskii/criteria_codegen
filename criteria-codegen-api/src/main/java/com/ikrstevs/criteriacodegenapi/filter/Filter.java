package com.ikrstevs.criteriacodegenapi.filter;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Common Criteria API filter API object.
 *
 * @param <T> the type of the object that's being queried.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Filter<T> {

  private static final long serialVersionUID = 1L;

  private T equals;
  private Boolean specified;
  private List<T> in;

  /**
   * Constructor.
   *
   * @param filter the filter object.
   */
  public Filter(final Filter<T> filter) {
    this.equals = filter.equals;
    this.specified = filter.specified;
    this.in = this.in == null ? null : new ArrayList<>();
  }

  /**
   * Copy filter object.
   *
   * @return a copy of the filter object
   */
  public Filter<T> copy() {
    return new Filter<>(this);
  }
}
