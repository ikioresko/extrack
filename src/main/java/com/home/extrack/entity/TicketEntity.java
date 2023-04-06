package com.home.extrack.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long ticketID;
    private Long ticketNumber;
    private Long errorId;

    @Transient
    private final AtomicInteger count = new AtomicInteger(1);

    public TicketEntity(Long ticketID,
                        Long ticketNumber,
                        Long errorId) {
        this.ticketID = ticketID;
        this.ticketNumber = ticketNumber;
        this.errorId = errorId;
    }

    public TicketEntity() {
    }

    public Long getTicketID() {
        return ticketID;
    }

    public void setTicketID(Long ticketID) {
        this.ticketID = ticketID;
    }

    public Long getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Long ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getErrorId() {
        return errorId;
    }

    public void setErrorId(Long errorId) {
        this.errorId = errorId;
    }

    public AtomicInteger getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TicketEntity that = (TicketEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
