package com.home.extrack.database;

import com.home.extrack.entity.TicketEntity;
import com.home.extrack.repository.TicketEntityRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketEntityDB {
    private final TicketEntityRepo ticketEntityRepo;

    public TicketEntityDB(TicketEntityRepo ticketEntityRepo) {
        this.ticketEntityRepo = ticketEntityRepo;
    }

    @Transactional(readOnly = true)
    public TicketEntity findTicketEntityByTicketID(Long OTRSTicketID) {
        return ticketEntityRepo.findTicketEntityByTicketID(OTRSTicketID);
    }

    @Transactional
    public TicketEntity save(TicketEntity entity) {
        return ticketEntityRepo.save(entity);
    }
}
