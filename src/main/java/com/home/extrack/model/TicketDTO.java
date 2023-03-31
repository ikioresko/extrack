package com.home.extrack.model;

import java.util.Objects;

public class TicketDTO {
    private final Long errorID;
    private final String armData;
    private final String filialName;
    private final String errorItem;
    private final String state;
    private final String orderNumber;


    public TicketDTO(Long errorID, String armData, String filialName,
                     String errorItem, String state, String orderNumber) {
        this.errorID = errorID;
        this.armData = armData;
        this.filialName = filialName;
        this.errorItem = errorItem;
        this.state = state;
        this.orderNumber = orderNumber;
    }

    public Long getErrorID() {
        return errorID;
    }


    public String getArmData() {
        return armData;
    }

    public String getFilialName() {
        return filialName;
    }


    public String getErrorItem() {
        return errorItem;
    }

    public String getState() {
        return state;
    }

    public String getOrderNumber() {
        return orderNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketDTO ticketDTO = (TicketDTO) o;
        return Objects.equals(errorID, ticketDTO.errorID)
                && Objects.equals(armData, ticketDTO.armData)
                && Objects.equals(filialName, ticketDTO.filialName)
                && Objects.equals(errorItem, ticketDTO.errorItem)
                && Objects.equals(state, ticketDTO.state)
                && Objects.equals(orderNumber, ticketDTO.orderNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorID, armData, filialName,
                errorItem, state, orderNumber);
    }
}
