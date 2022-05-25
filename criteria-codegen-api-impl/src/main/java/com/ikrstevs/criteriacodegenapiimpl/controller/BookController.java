package com.ikrstevs.criteriacodegenapiimpl.controller;

import com.ikrstevs.criteriacodegenapiimpl.criteria.BookCriteria;
import com.ikrstevs.criteriacodegenapiimpl.model.Book;
import com.ikrstevs.criteriacodegenapiimpl.service.BookQueryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {

  private final BookQueryService bookQueryService;

  @GetMapping
  public String test() {
    return "Hello, World!";
  }

  @GetMapping("/books")
  public Collection<Book> getBooksByCriteria(final BookCriteria criteria) {
    final Collection<Book> res = bookQueryService.findByCriteria(criteria);
    return res;
  }
}
