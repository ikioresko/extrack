package com.home.extrack.repository;

import com.home.extrack.entity.TicketEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketEntityRepo extends CrudRepository<TicketEntity, Long> {
    TicketEntity findTicketEntityByTicketID(Long ticketID);

}
