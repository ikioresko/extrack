package com.home.extrack.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class FilterService {
    private final ErrorEqualsMemPool errorEqualsMemPool;
    private final RegexService regexService;


    public FilterService(ErrorEqualsMemPool errorEqualsMemPool,
                         RegexService regexService) {
        this.errorEqualsMemPool = errorEqualsMemPool;
        this.regexService = regexService;
    }

    /**
     * Looking hits by equals and regex
     *
     * @param errorItem error text
     * @return Object of error
     */
    protected Long filter(String errorItem) {
        Long errorId = null;
        ConcurrentHashMap<Long, String> mapErrorEquals = errorEqualsMemPool.getMapFilterEquals();
        for (Map.Entry<Long, String> entry : mapErrorEquals.entrySet()) {
            if (entry.getValue().equals(errorItem)) {
                errorId = entry.getKey();
                break;
            }
        }
        log.info("Equals find ID: {}", errorId);
        if (errorId == null) {
            errorId = regexService.regexSearch(errorItem);
            log.info("Regex find ID: {}", errorId);
        }
        return errorId;
    }
}
