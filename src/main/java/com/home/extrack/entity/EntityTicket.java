package com.home.extrack.entity;

import com.home.extrack.model.PropertyPK;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(PropertyPK.class)
public class EntityTicket {
    @Id
    private Long Id;
    @Id
    private Long ticketId;

    public EntityTicket(Long Id, Long ticketId) {
        this.Id = Id;
        this.ticketId = ticketId;
    }

    public EntityTicket() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long errorsEntityId) {
        this.Id = errorsEntityId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long otrsTicketId) {
        this.ticketId = otrsTicketId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityTicket that = (EntityTicket) o;
        return Objects.equals(Id, that.Id)
                && Objects.equals(ticketId, that.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, ticketId);
    }
}
