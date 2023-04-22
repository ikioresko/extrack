package com.home.extrack.service;

import com.home.extrack.entity.TicketEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class TicketPool {
    /**
     * mapTicket {Error,Ticket}
     */
    private static final ConcurrentHashMap<Long, TicketEntity> mapTicket = new ConcurrentHashMap<>(250);

    public ConcurrentHashMap<Long, TicketEntity> getMap() {
        return mapTicket;
    }

    /**
     * Clean mapTicket
     */
    @Scheduled(cron = "0 0 21 * * *", zone = "GMT+10")
    protected void clearMap() {
        log.info("mapTicket clear scheduled");
        mapTicket.clear();
    }
}
