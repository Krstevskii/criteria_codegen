package com.ikrstevs.criteriacodegenapi.filter;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Criteria API String filter API object.
 */
@Data
@NoArgsConstructor
public class StringFilter extends Filter<String> {

  private static final long serialVersionUID = 1L;

  private String contains;

  /**
   * Constructor.
   *
   * @param filter the string filter
   */
  public StringFilter(final StringFilter filter) {
    super(filter);
    this.contains = filter.contains;
  }

  /**
   * Copy of the string filter object.
   *
   * @return a copy of the string filter object
   */
  public StringFilter copy() {
    return new StringFilter(this);
  }
}
