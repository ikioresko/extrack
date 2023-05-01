package com.home.extrack.database;

import com.home.extrack.entity.InfoEntity;
import com.home.extrack.repository.InfoEntityRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserInfoDB {
    private final InfoEntityRepo infoEntityRepo;

    public UserInfoDB(InfoEntityRepo infoEntityRepo) {
        this.infoEntityRepo = infoEntityRepo;
    }

    @Transactional
    public InfoEntity save(InfoEntity entity) {
        return infoEntityRepo.save(entity);
    }
}
