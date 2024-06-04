package com.upwork.webforumapp.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "text")
    private String text;

    @Column(name = "author", nullable = true)
    private String author;

    @Column(name = "created_at")
    private Date createdAt;

    public Posts() {
    }

    public Posts(String text, String author, Date createdAt) {
        this.text = text;
        this.author = author;
        this.createdAt = createdAt;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
