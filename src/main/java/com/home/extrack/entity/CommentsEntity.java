package com.home.extrack.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class CommentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String commentText;
    private String technicLogin;
    private Timestamp commentData;

    public CommentsEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getTechnicLogin() {
        return technicLogin;
    }

    public void setTechnicLogin(String technicLogin) {
        this.technicLogin = technicLogin;
    }

    public Timestamp getCommentData() {
        return commentData;
    }

    public void setCommentData(Timestamp commentData) {
        this.commentData = commentData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommentsEntity that = (CommentsEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}