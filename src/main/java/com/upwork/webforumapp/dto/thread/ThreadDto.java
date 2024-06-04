package com.upwork.webforumapp.dto.thread;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ThreadDto {

    private String title;
    private String author;
    private String category;
    private ThreadPostDto openingPost;

    public ThreadDto() {
    }

    public ThreadDto(String title, String category, ThreadPostDto openingPost) {
        this.title = title;
        this.category = category;
        this.openingPost = openingPost;
    }

    public ThreadDto(String title, String author, String category, ThreadPostDto openingPost) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.openingPost = openingPost;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ThreadPostDto getOpeningPost() {
        return openingPost;
    }

    public void setOpeningPost(ThreadPostDto openingPost) {
        this.openingPost = openingPost;
    }
}
