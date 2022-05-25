package com.ikrstevs.criteriacodegenapi.filter;

import java.time.Instant;

import lombok.NoArgsConstructor;

/**
 * Criteria API Instant filter API object.
 */
@NoArgsConstructor
public class InstantFilter extends Filter<Instant> {

  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   *
   * @param filter the instant filter
   */
  public InstantFilter(Filter<Instant> filter) {
    super(filter);
  }

  /**
   * Copy of the instant filter object.
   *
   * @return a copy of the instant filter object
   */
  public InstantFilter copy() {
    return new InstantFilter(this);
  }
}
