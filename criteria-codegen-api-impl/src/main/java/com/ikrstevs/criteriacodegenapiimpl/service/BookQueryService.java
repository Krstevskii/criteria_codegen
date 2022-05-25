package com.ikrstevs.criteriacodegenapiimpl.service;

import com.ikrstevs.criteriacodegenapi.service.QueryService;
import com.ikrstevs.criteriacodegenapiimpl.criteria.BookCriteria;
import com.ikrstevs.criteriacodegenapiimpl.model.Book;
import com.ikrstevs.criteriacodegenapiimpl.model.Book_;
import com.ikrstevs.criteriacodegenapiimpl.repository.BookRepository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookQueryService extends QueryService<Book> {

  private final BookRepository bookRepository;

  public BookQueryService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public List<Book> findByCriteria(final BookCriteria bookCriteriaTemp) {

    final Specification<Book> specification = buildSpecification(bookCriteriaTemp);

    return bookRepository.findAll(specification);
  }

  private Specification<Book> buildSpecification(final BookCriteria bookCriteriaTemp) {

    Specification<Book> bookSpecification = Specification.where(null);

    if (bookCriteriaTemp.getId() != null) {
      bookSpecification = bookSpecification.and(buildSpecification(bookCriteriaTemp.getId(), Book_.id));
    }

    if (bookCriteriaTemp.getDate() != null) {
      bookSpecification = bookSpecification.and(buildRangeSpecification(bookCriteriaTemp.getDate(), Book_.date));
    }

    if (bookCriteriaTemp.getAuthor() != null) {
      bookSpecification = bookSpecification.and(buildStringSpecification(bookCriteriaTemp.getAuthor(), Book_.author));
    }

    return bookSpecification;
  }
}
