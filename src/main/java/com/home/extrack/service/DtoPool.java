package com.home.extrack.service;

import com.home.extrack.model.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class DtoPool {
    private static final ConcurrentHashMap<ErrorDTO, Long> map = new ConcurrentHashMap<>(9000);

    /**
     * Check system for system error with an interval
     * Sleep thread for business logic
     *
     * @param dto Object with error id
     * @return boolean
     */
    public boolean addToMapErrorDTO(ErrorDTO dto) {
        try {
            Thread.sleep((int) (Math.random() * 50) + 10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        boolean result = false;
        ErrorDTO copy = new ErrorDTO(dto.getErrorId(), dto.getName(), dto.getDate());
        copy.takeSec();
        if (!map.containsKey(dto) && !map.containsKey(copy)) {
            map.put(dto, 0L);
            result = true;
        }
        return result;
    }

    /**
     * Clean  mapError
     */
    @Scheduled(cron = "0 0 21 * * *", zone = "GMT+10")
    protected void clearMap() {
        log.info("mapError clear scheduled");
        map.clear();
    }
}