package com.ikrstevs.criteriacodegenapiimpl.service;

import com.ikrstevs.criteriacodegenapi.service.QueryService;
import com.ikrstevs.criteriacodegenapiimpl.criteria.LibraryCriteria;
import com.ikrstevs.criteriacodegenapiimpl.model.Library;
import com.ikrstevs.criteriacodegenapiimpl.model.Library_;
import com.ikrstevs.criteriacodegenapiimpl.repository.LibraryRepository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibraryQueryService extends QueryService<Library> {

  private final LibraryRepository libraryRepository;

  public List<Library> findByCriteria(final LibraryCriteria libraryCriteria) {
    final Specification<Library> specification = buildSpecification(libraryCriteria);

    return libraryRepository.findAll(specification);
  }

  private Specification<Library> buildSpecification(final LibraryCriteria libraryCriteria) {

    Specification<Library> librarySpecification = Specification.where(null);

    if (libraryCriteria.getId() != null) {
      librarySpecification = librarySpecification.and(buildSpecification(libraryCriteria.getId(), Library_.id));
    }

    if (libraryCriteria.getCity() != null) {
      librarySpecification = librarySpecification.and(buildSpecification(libraryCriteria.getCity(), Library_.city));
    }

    if (libraryCriteria.getYearFounded() != null) {
      librarySpecification = librarySpecification.and(
        buildSpecification(libraryCriteria.getYearFounded(), Library_.yearFounded));
    }

    return librarySpecification;
  }

}

