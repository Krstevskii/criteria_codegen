package com.ikrstevs.criteriacodegenapiimpl.model;

import com.ikrstevs.criteriacodegenprocessor.annotation.CriteriaBuilder;
import com.ikrstevs.criteriacodegenprocessor.annotation.CriteriaFilter;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@CriteriaBuilder
@Getter
public class Book {

  @Id
  @CriteriaFilter
  private Long id;

  private String name;

  @CriteriaFilter
  private String author;

  @CriteriaFilter
  private LocalDate date;
}
