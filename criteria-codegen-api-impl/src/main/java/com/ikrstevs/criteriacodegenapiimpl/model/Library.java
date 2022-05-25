package com.ikrstevs.criteriacodegenapiimpl.model;

import com.ikrstevs.criteriacodegenprocessor.annotation.CriteriaBuilder;
import com.ikrstevs.criteriacodegenprocessor.annotation.CriteriaFilter;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@CriteriaBuilder
@Entity
@Getter
@NoArgsConstructor
public class Library {

  @Id
  @CriteriaFilter
  private Long id;

  private String name;

  @CriteriaFilter
  private String city;

  @CriteriaFilter
  @Column(name = "year_founded")
  private Instant yearFounded;
}
