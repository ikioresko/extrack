package com.home.extrack.repository;

import com.home.extrack.entity.EntityTicket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepo extends CrudRepository<EntityTicket, Long> {
}
