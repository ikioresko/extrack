package com.home.extrack.database;


import com.home.extrack.entity.TicketEntity;
import com.home.extrack.repository.TicketEntityRepo;
import org.springframework.stereotype.Service;

@Service
public class TicketRepoDB {
    private final TicketEntityRepo ticketEntityRepo;

    public TicketRepoDB(TicketEntityRepo ticketEntityRepo) {
        this.ticketEntityRepo = ticketEntityRepo;
    }

    public void save(TicketEntity entity) {
        ticketEntityRepo.save(entity);
    }
}
