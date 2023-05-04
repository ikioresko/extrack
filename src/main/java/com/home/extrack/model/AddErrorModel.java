package com.home.extrack.model;

import java.sql.Timestamp;
import java.util.Objects;

public class AddErrorModel {
    private final String errorItem;
    private final String code;
    private final String type;
    private final String ticket;
    private final String userName;
    private final Timestamp time;
    private final String orderNumber;
    private final String integrationId;
    private final String uid;


    public AddErrorModel(String errorItem, String code, String type, String ticket, String userName,
                         Timestamp time, String orderNumber, String integrationId, String uid) {
        this.errorItem = errorItem;
        this.code = code;
        this.type = type;
        this.ticket = ticket;
        this.userName = userName;
        this.time = time;
        this.orderNumber = orderNumber;
        this.integrationId = integrationId;
        this.uid = uid;
    }


    public String getErrorItem() {
        return errorItem;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getTicket() {
        return ticket;
    }

    public String getUserName() {
        return userName;
    }

    public Timestamp getTime() {
        return time;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getIntegrationId() {
        return integrationId;
    }

    public String getUid() {
        return uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddErrorModel that = (AddErrorModel) o;
        return Objects.equals(errorItem, that.errorItem)
                && Objects.equals(code, that.code) && Objects.equals(type, that.type)
                && Objects.equals(ticket, that.ticket) && Objects.equals(userName, that.userName)
                && Objects.equals(time, that.time) && Objects.equals(orderNumber, that.orderNumber)
                && Objects.equals(integrationId, that.integrationId) && Objects.equals(uid, that.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorItem, code, type, ticket, userName, time, orderNumber, integrationId, uid);
    }
}
