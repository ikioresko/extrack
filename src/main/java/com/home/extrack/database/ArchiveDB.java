package com.home.extrack.database;


import com.home.extrack.entity.EntityArchive;
import com.home.extrack.repository.ArchiveRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ArchiveDB {
    private final ArchiveRepo archiveRepo;

    public ArchiveDB(ArchiveRepo archiveRepo) {
        this.archiveRepo = archiveRepo;
    }

    @Transactional
    public EntityArchive save(EntityArchive entityArchive) {
        return archiveRepo.save(entityArchive);
    }
}
