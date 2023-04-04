package com.home.extrack.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class FilterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, columnDefinition="TEXT")
    private String errorRegexText;

    private String type;

    private Long count;

    private Long errorId;

    public FilterEntity(Long id,
                        String type,
                        String errorRegexText,
                        Long errorId) {
        this.errorRegexText = errorRegexText;
        this.type = type;
        this.id = id;
        this.errorId = errorId;
    }

    public FilterEntity() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getError() {
        return errorId;
    }

    public void setError(Long errorId) {
        this.errorId = errorId;
    }

    public String getErrorRegexText() {
        return errorRegexText;
    }

    public void setErrorRegexText(String errorRegexText) {
        this.errorRegexText = errorRegexText;
    }

    public Long getCount() {return count;}

    public void setCount(Long count) {this.count = count;}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FilterEntity that = (FilterEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
