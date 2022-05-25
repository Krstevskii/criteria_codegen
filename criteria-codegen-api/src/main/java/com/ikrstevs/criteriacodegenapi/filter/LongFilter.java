package com.ikrstevs.criteriacodegenapi.filter;

import lombok.NoArgsConstructor;

/**
 * Criteria API Long filter API object.
 */
@NoArgsConstructor
public class LongFilter extends Filter<Long> {

  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   *
   * @param filter the long filter
   */
  public LongFilter(final LongFilter filter) {
    super(filter);
  }

  /**
   * Copy of the long filter object.
   *
   * @return a copy of the long filter object
   */
  public LongFilter copy() {
    return new LongFilter(this);
  }
}
