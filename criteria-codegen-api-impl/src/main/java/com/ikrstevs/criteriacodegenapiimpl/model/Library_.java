package com.ikrstevs.criteriacodegenapiimpl.model;

import java.time.Instant;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Library.class)
public class Library_ {

  public static volatile SingularAttribute<Library, Long> id;
  public static volatile SingularAttribute<Library, String> name;
  public static volatile SingularAttribute<Library, String> city;
  public static volatile SingularAttribute<Library, Instant> yearFounded;
}
