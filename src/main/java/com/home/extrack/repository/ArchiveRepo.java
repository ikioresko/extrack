package com.home.extrack.repository;

import com.home.extrack.entity.EntityArchive;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveRepo extends CrudRepository<EntityArchive, Long> {
}
