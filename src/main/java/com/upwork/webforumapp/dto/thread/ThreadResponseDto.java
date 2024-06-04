package com.upwork.webforumapp.dto.thread;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThreadResponseDto {
    @JsonProperty("id")
    private Integer threadId;
    private String title;
    private String author;
    @JsonProperty("category")
    private String category_name;
    private Date createdAt;
    private List<ThreadPostDto> posts;

    public ThreadResponseDto() {
    }

    public ThreadResponseDto(Integer threadId, String title, String author, String category_name, Date createdAt) {
        this.threadId = threadId;
        this.title = title;
        this.author = author;
        this.category_name = category_name;
        this.createdAt = createdAt;
    }

    public Integer getThreadId() {
        return threadId;
    }

    public void setThreadId(Integer threadId) {
        this.threadId = threadId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<ThreadPostDto> getPosts() {
        return posts;
    }

    public void setPosts(List<ThreadPostDto> posts) {
        this.posts = posts;
    }

    public void addPosts(ThreadPostDto postDto) {
        if(posts == null) {
            posts = new ArrayList<>();
        }
        posts.add(postDto);
    }
}
