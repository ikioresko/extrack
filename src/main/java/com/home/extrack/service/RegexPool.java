package com.home.extrack.service;

import com.home.extrack.database.FiltersDB;
import com.home.extrack.entity.FilterEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

@Service
@Slf4j
public class RegexPool {
    private static final ConcurrentHashMap<Long, Pattern> mapRegex = new ConcurrentHashMap<>(500);
    private final FiltersDB filtersDB;

    public RegexPool(FiltersDB filtersDB) {
        this.filtersDB = filtersDB;
    }

    /**
     * Init regex list
     */
    public void listInit() {
        mapRegex.clear();
        List<FilterEntity> errorsFilterDataEntities = filtersDB.findAllByType("regexp");
        for (FilterEntity filterEntity : errorsFilterDataEntities) {
            mapRegex.put(filterEntity.getError(),
                    Pattern.compile(filterEntity.getErrorRegexText(), Pattern.CASE_INSENSITIVE));
        }
        log.info("Regex size: {}", mapRegex.size());
    }

    public ConcurrentHashMap<Long, Pattern> getMap() {
        return mapRegex;
    }

    /**
     * Check actual regex list
     */
    @Scheduled(fixedDelay = 60000)
    protected void checkErrorsEntityDataBaseSize() {
        long dataBaseSize = filtersDB.getRegexpTableSizeByType("regexp");
        if (mapRegex.size() != dataBaseSize) {
            listInit();
        }
    }
}
