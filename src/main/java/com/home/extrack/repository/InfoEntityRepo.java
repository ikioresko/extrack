package com.home.extrack.repository;

import com.home.extrack.entity.InfoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoEntityRepo extends CrudRepository<InfoEntity, Long> {
}
