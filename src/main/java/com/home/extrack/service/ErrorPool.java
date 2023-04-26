package com.home.extrack.service;


import com.home.extrack.database.ErrorEntityDB;
import com.home.extrack.entity.ErrorsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@EnableScheduling
@Slf4j
public class ErrorPool {
    private final ErrorEntityDB errorsRepo;
    private static final List<Errors> list = new ArrayList<>();

    public ErrorPool(ErrorEntityDB errorsRepo) {
        this.errorsRepo = errorsRepo;
    }

    public List<Errors> getList() {
        return list;
    }

    protected class Errors {
        public List<String> errorItems;
        public Long id;
    }

    /**
     * Init pool with string formatter
     */
    private void listInit() {
        list.clear();
        List<ErrorsEntity> listErrorsEntityFromBD = (List<ErrorsEntity>) errorsRepo.findErrorsEntityWithoutInnerObjects();
        listErrorsEntityFromBD.forEach(errorsEntity -> {
            List<String> listOfValues = List.of(errorsEntity
                    .getErrorItem()
                    .replaceAll("<", " ")
                    .replaceAll(">", " ")
                    .split(" "));
            Errors errors = new Errors();
            errors.errorItems = listOfValues;
            errors.id = errorsEntity.getId();
            list.add(errors);
        });
        log.info("Error List size: {}", list.size());
    }

    /**
     * Check actual info into DB
     */
    @Scheduled(fixedDelay = 30000)
    protected void checkErrorsEntityDataBaseSize() {
        Long dataBaseSize = errorsRepo.getTableSize();
        if (list.size() != dataBaseSize) {
            listInit();
        }
    }
}
