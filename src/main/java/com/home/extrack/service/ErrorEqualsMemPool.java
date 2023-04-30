package com.home.extrack.service;

import com.home.extrack.database.FiltersDB;
import com.home.extrack.entity.FilterEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class ErrorEqualsMemPool {
    private final FiltersDB filtersDB;
    private static final ConcurrentHashMap<Long, String> map = new ConcurrentHashMap<>(400);

    public ErrorEqualsMemPool(FiltersDB filtersDB) {
        this.filtersDB = filtersDB;
    }

    public ConcurrentHashMap<Long, String> getMapFilterEquals() {
        return map;
    }

    /**
     * Reload list form DB
     */
    public void listInit() {
        map.clear();
        List<FilterEntity> equalsList = filtersDB.findAllByType("equals");
        for (FilterEntity filterEntity : equalsList) {
            map.put(filterEntity.getError(), filterEntity.getErrorRegexText());
        }
        log.info("Equals map size: {}", map.size());
    }

    /**
     * Check actual info into DB
     */
    @Scheduled(fixedDelay = 30000)
    protected void checkErrorsEntityDataBaseSize() {
        long dataBaseSize = filtersDB.getRegexpTableSizeByType("equals");
        if (map.size() != dataBaseSize) {
            listInit();
        }
    }
}
