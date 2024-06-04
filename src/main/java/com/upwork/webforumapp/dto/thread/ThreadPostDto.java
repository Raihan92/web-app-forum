package com.upwork.webforumapp.dto.thread;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class ThreadPostDto {

    @JsonIgnore
    private Integer id;

    private String text;

    private String author;

    @JsonIgnore
    private Date createdAt;

    public ThreadPostDto() {
    }

    public ThreadPostDto(String text) {
        this.text = text;
    }

    public ThreadPostDto(String text, String author, Date createdAt) {
        this.text = text;
        this.author = author;
        this.createdAt = createdAt;
    }

    public ThreadPostDto(String text, Date createdAt) {
        this.text = text;
        this.createdAt = createdAt;
    }

    public ThreadPostDto(Integer id, String text, Date createdAt) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
