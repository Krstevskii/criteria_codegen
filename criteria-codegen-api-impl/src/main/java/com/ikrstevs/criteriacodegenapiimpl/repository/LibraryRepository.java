package com.ikrstevs.criteriacodegenapiimpl.repository;

import com.ikrstevs.criteriacodegenapiimpl.model.Library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LibraryRepository extends JpaRepository<Library, Long>, JpaSpecificationExecutor<Library> {
}
