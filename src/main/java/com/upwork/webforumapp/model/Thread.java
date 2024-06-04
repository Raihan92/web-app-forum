package com.upwork.webforumapp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "thread")
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thread_id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "author")
    private String author;

    @Column(name="category_name")
    private String categoryName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Posts> posts;

    public Thread() {
    }

    public Thread(String title, Date createdAt, String author, String categoryName) {
        this.title = title;
        this.createdAt = createdAt;
        this.author = author;
        this.categoryName = categoryName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Posts> getPosts() {
        return posts;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }

    public void addPosts(Posts post) {
        if(posts == null) {
            posts = new ArrayList<>();
        }
        posts.add(post);
    }
}
