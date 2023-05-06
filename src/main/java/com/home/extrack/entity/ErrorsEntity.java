package com.home.extrack.entity;

import javax.persistence.*;
import java.util.*;

@Entity
public class ErrorsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String errorItem;
    private String type;
    private Long count;
    private Boolean checkTicketCreate;
    private Boolean ignore;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final Set<CommentsEntity> comments = new LinkedHashSet<>();
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "replace_id")
    private ReplaceEntity replaceEntities;

    public ErrorsEntity() {
    }

    public ErrorsEntity(String errorItem, String type) {
        this.errorItem = errorItem;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getErrorItem() {
        return errorItem;
    }

    public void setErrorItem(String errorItem) {
        this.errorItem = errorItem;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Boolean getCheckTicketCreate() {
        return checkTicketCreate;
    }

    public void setCheckTicketCreate(Boolean checkTicketCreate) {
        this.checkTicketCreate = checkTicketCreate;
    }

    public Boolean getIgnore() {
        return ignore;
    }

    public void setIgnore(Boolean ignore) {
        this.ignore = ignore;
    }

    public Set<CommentsEntity> getComments() {
        return comments;
    }

    public ReplaceEntity getReplaceEntities() {
        return replaceEntities;
    }

    public void setReplaceEntities(ReplaceEntity replaceEntities) {
        this.replaceEntities = replaceEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ErrorsEntity that = (ErrorsEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
