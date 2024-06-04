package com.upwork.webforumapp.dto.thread;

import java.util.ArrayList;
import java.util.List;

public class CreateThreadPostsDto {

    private Integer threadId;
    private List<ThreadPostDto> posts;

    public CreateThreadPostsDto() {
    }

    public CreateThreadPostsDto(Integer threadId, List<ThreadPostDto> posts) {
        this.threadId = threadId;
        this.posts = posts;
    }

    public Integer getThreadId() {
        return threadId;
    }

    public void setThreadId(Integer threadId) {
        this.threadId = threadId;
    }

    public List<ThreadPostDto> getPosts() {
        return posts;
    }

    public void setPosts(List<ThreadPostDto> posts) {
        this.posts = posts;
    }

    public void addPost(ThreadPostDto postDto) {
        if(posts == null) {
            posts = new ArrayList<>();
        }
        posts.add(postDto);
    }
}
