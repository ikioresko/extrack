package com.home.extrack.database;

import com.home.extrack.entity.ErrorsEntity;
import com.home.extrack.repository.ErrorsRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class ErrorEntityDB {
    private final ErrorsRepo errorsRepo;

    public ErrorEntityDB(ErrorsRepo errorsRepo) {
        this.errorsRepo = errorsRepo;
    }

    @Transactional
    public ErrorsEntity save(ErrorsEntity entity) {
        return errorsRepo.save(entity);
    }

    @Transactional(readOnly = true)
    public Optional<ErrorsEntity> findById(Long id) {
        return errorsRepo.findById(id);
    }

    public Iterable<ErrorsEntity> findAllErrors() {
        return errorsRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Iterable<ErrorsEntity> findErrorsEntityWithoutInnerObjects() {
        return errorsRepo.findErrorsEntityWithoutInnerObjects();
    }

    @Transactional(readOnly = true)
    public Long getTableSize() {
        return errorsRepo.count();
    }
}