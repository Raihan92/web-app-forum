package com.upwork.webforumapp.dto.category;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import java.util.Date;

public class CategoryDto {
    @JsonIgnore
    private Integer id;
    private String categoryName;
    private Date createdAt;

    public CategoryDto() {
    }

    public CategoryDto(Integer id, String categoryName, Date createdAt) {
        this.id = id;
        this.categoryName = categoryName;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
