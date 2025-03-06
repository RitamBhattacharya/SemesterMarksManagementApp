package com.ritam.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.ritam.entity.SemesterDetailsEntity;

@Repository
public interface SemesterRepository extends ListCrudRepository<SemesterDetailsEntity, Integer> {

}
