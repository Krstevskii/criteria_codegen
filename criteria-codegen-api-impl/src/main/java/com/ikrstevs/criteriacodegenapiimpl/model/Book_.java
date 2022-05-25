package com.ikrstevs.criteriacodegenapiimpl.model;

import java.time.LocalDate;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Book.class)
public class Book_ {

  public static volatile SingularAttribute<Book, Long> id;
  public static volatile SingularAttribute<Book, String> name;
  public static volatile SingularAttribute<Book, String> author;
  public static volatile SingularAttribute<Book, LocalDate> date;

}
