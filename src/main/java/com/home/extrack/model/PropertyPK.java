package com.home.extrack.model;

import java.io.Serializable;
import java.util.Objects;

public class PropertyPK implements Serializable {
    private Long errorsEntityId;
    private Long otrsTicketId;

    public PropertyPK(Long errorsEntityId, Long otrsTicketId) {
        this.errorsEntityId = errorsEntityId;
        this.otrsTicketId = otrsTicketId;
    }

    public Long getId() {
        return errorsEntityId;
    }

    public void setId(Long errorsEntityId) {
        this.errorsEntityId = errorsEntityId;
    }

    public Long getTicketId() {
        return otrsTicketId;
    }

    public void setTicketId(Long otrsTicketId) {
        this.otrsTicketId = otrsTicketId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyPK that = (PropertyPK) o;
        return Objects.equals(errorsEntityId, that.errorsEntityId)
                && Objects.equals(otrsTicketId, that.otrsTicketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorsEntityId, otrsTicketId);
    }
}
