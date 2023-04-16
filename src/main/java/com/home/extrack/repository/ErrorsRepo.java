package com.home.extrack.repository;

import com.home.extrack.entity.ErrorsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorsRepo extends CrudRepository<ErrorsEntity, Long> {
    Iterable<ErrorsEntity> findErrorsEntityWithoutInnerObjects();
}
