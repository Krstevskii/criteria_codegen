package com.ikrstevs.criteriacodegenapiimpl.controller;

import com.ikrstevs.criteriacodegenapiimpl.criteria.LibraryCriteria;
import com.ikrstevs.criteriacodegenapiimpl.model.Library;
import com.ikrstevs.criteriacodegenapiimpl.service.LibraryQueryService;

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
public class LibraryController {
  private final LibraryQueryService libraryQueryService;

  @GetMapping("/libraries")
  public Collection<Library> getLibraries(final LibraryCriteria libraryCriteria) {
    return libraryQueryService.findByCriteria(libraryCriteria);
  }

}
