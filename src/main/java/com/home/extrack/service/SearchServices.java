package com.home.extrack.service;


import com.home.extrack.database.ErrorEntityDB;
import com.home.extrack.entity.ErrorsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class SearchServices {
    private final ErrorEntityDB errorsRepo;
    private final ErrorPool errorPool;

    public SearchServices(ErrorEntityDB errorsRepo, ErrorPool errorPool) {
        this.errorsRepo = errorsRepo;
        this.errorPool = errorPool;
    }

    /**
     * Count words from error object
     *
     * @param hits       {errorId, count}
     * @param whatWeFind Error from service
     */
    private void search(HashMap<Long, Long> hits, String[] whatWeFind) {
        errorPool.getList().forEach(errors -> {
            for (String inputErrorWord : whatWeFind) {
                if (errors.errorItems.contains(inputErrorWord)) {
                    Long num = Optional.ofNullable(hits.get(errors.id)).orElse(0L);
                    hits.put(errors.id, num + 1L);
                }
            }
        });
    }

    /**
     * Do search by word consilience
     *
     * @param errorItem error object
     * @return ResponseEntity
     */
    public ErrorsEntity findError(String errorItem) {
        String[] whatWeFind = errorItem.split(" ");
        HashMap<Long, Long> hits = new HashMap<>();
        search(hits, whatWeFind);
        List<Long> answerIdList = new ArrayList();
        log.info("hits {}", hits);
        hits.forEach((k, v) -> {
        });
        int maxHits = hits.values().stream()
                .mapToInt(Math::toIntExact)
                .max()
                .orElse(0);
        if (maxHits > 0) {
            hits.forEach((k, v) -> {
                if (v == maxHits) {
                    answerIdList.add(k);
                }
            });
        }
        return answerIdList.size() == 1 ? errorsRepo.findById(answerIdList.get(0)).get() : null;
    }
}
