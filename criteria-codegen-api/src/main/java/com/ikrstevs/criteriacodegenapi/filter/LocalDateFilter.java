package com.ikrstevs.criteriacodegenapi.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LocalDateFilter extends RangeFilter<LocalDate> {

  private static final long serialVersionUID = 1L;

  public LocalDateFilter(final LocalDateFilter filter) {
    super(filter);
  }

  @Override
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  public void setEquals(LocalDate equals) {
    super.setEquals(equals);
  }

  @Override
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  public void setSpecified(Boolean specified) {
    super.setSpecified(specified);
  }

  @Override
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  public void setIn(List<LocalDate> in) {
    super.setIn(in);
  }

  @Override
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  public void setGreaterThan(LocalDate greaterThan) {
    super.setGreaterThan(greaterThan);
  }

  @Override
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  public void setLesserThan(LocalDate lesserThan) {
    super.setLesserThan(lesserThan);
  }

  @Override
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  public void setGreaterThanOrEqual(LocalDate greaterThanOrEqual) {
    super.setGreaterThanOrEqual(greaterThanOrEqual);
  }

  @Override
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  public void setLesserThanOrEqual(LocalDate lesserThanOrEqual) {
    super.setLesserThanOrEqual(lesserThanOrEqual);
  }

  public LocalDateFilter copy() {
    return new LocalDateFilter(this);
  }
}
