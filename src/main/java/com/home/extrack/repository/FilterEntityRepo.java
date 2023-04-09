package com.home.extrack.repository;

import com.home.extrack.entity.FilterEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterEntityRepo extends CrudRepository<FilterEntity, Long> {
}
