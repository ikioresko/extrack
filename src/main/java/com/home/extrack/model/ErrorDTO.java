package com.home.extrack.model;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class ErrorDTO {
    private final Long errorId;
    private final String name;
    private Date date;

    public ErrorDTO(Long errorId, String name) {
        this.errorId = errorId;
        this.name = name;
        this.date = initDate();
    }

    public ErrorDTO(Long errorId, String name, Date date) {
        this.errorId = errorId;
        this.name = name;
        this.date = date;
    }

    private Date initDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear(Calendar.MILLISECOND);
        return calendar.getTime();
    }

    public void takeSec() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) - 1);
        date = calendar.getTime();
    }

    public Long getErrorId() {
        return errorId;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ErrorDTO errorDTO = (ErrorDTO) o;
        return Objects.equals(errorId, errorDTO.errorId)
                && Objects.equals(name, errorDTO.name)
                && Objects.equals(date, errorDTO.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorId, name, date);
    }
}
