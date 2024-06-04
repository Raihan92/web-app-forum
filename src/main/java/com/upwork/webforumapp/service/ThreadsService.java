package com.upwork.webforumapp.service;

import com.upwork.webforumapp.dto.ApiResponse;
import com.upwork.webforumapp.dto.thread.*;
import com.upwork.webforumapp.exceptions.NotFondException;
import com.upwork.webforumapp.model.Category;
import com.upwork.webforumapp.model.Posts;
import com.upwork.webforumapp.model.Thread;
import com.upwork.webforumapp.repository.ThreadsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ThreadsService {

    Logger logger = LoggerFactory.getLogger(ThreadsService.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ThreadsRepository threadsRepository;

    public ApiResponse createThread(ThreadDto threadDto) throws NotFondException {
        Category category = categoryService.findCategory(threadDto.getCategory());
        if(category != null) {
            Thread thread = prepareThread(threadDto);
            threadsRepository.save(thread);
            return new ApiResponse("success", "Success!");
        } else {
            throw new NotFondException("Category Not Found");
        }
    }

    private Thread prepareThread(ThreadDto threadDto) {
        List<Posts> posts = preparePosts(threadDto.getOpeningPost());
        Thread thread = new Thread(threadDto.getTitle(), new Date(), threadDto.getAuthor(), threadDto.getCategory());
        for (Posts singlePost : posts) {
            thread.addPosts(singlePost);
        }
        return thread;
    }

    private List<Posts> preparePosts(ThreadPostDto openingPost) {
        List<Posts> posts = new ArrayList<>();
        Posts post = new Posts();
        post.setAuthor(openingPost.getAuthor());
        post.setText(openingPost.getText());
        post.setCreatedAt(new Date());
        posts.add(post);
        return posts;
    }

    public ReadAllThreadsResponseDto getThreadsByCriteria(List<String> categories, int page, int pageSize) throws NotFondException {
        ReadAllThreadsResponseDto responseDto = new ReadAllThreadsResponseDto();
        Pageable pageable = PageRequest.of(page, pageSize);
        List<Thread> threads = threadsRepository.findByCategories(categories, pageable).getContent();
        if(threads != null && threads.size() > 0) {
            for (Thread thread : threads) {
                responseDto.addThreads(new ThreadResponseDto(
                        thread.getId(),
                        thread.getTitle(),
                        thread.getAuthor(),
                        thread.getCategoryName(),
                        thread.getCreatedAt()
                ));
            }
            responseDto.setStatus("success");
            responseDto.setMessage("Success!");
        } else {
            throw new NotFondException("Category does not exist");
        }
        return responseDto;
    }

    public ReadThreadResponseDto getThreadsById(Integer threadId) throws NotFondException {
        ReadThreadResponseDto responseDto = new ReadThreadResponseDto();
        Optional<Thread> thread = threadsRepository.findById(threadId);
        if(thread != null) {
            responseDto.setThreadResponseDto(new ThreadResponseDto(
                    thread.get().getId(),
                    thread.get().getTitle(),
                    thread.get().getAuthor(),
                    thread.get().getCategoryName(),
                    thread.get().getCreatedAt()
            ));
            List<ThreadPostDto> threadPostDtos = new ArrayList<>();
            for(Posts post : thread.get().getPosts()) {
                ThreadPostDto postDto = new ThreadPostDto(post.getText(),post.getAuthor(),post.getCreatedAt());
                threadPostDtos.add(postDto);
            }
            responseDto.getThreadResponseDto().setPosts(threadPostDtos);
            responseDto.setStatus("success");
            responseDto.setMessage("Success!");
        } else {
            throw new NotFondException("Thread does not exist");
        }
        return responseDto;
    }

    public ApiResponse updateThreadsById(CreateThreadPostsDto threadDto) throws NotFondException {
        Optional<Thread> threadOptional = threadsRepository.findById(threadDto.getThreadId());
        if (!threadOptional.isPresent()) {
            throw new NotFondException("Thread not found");
        }

        Thread thread = threadOptional.get();
        for (ThreadPostDto postDto : threadDto.getPosts()) {
            Posts posts = new Posts();
            posts.setText(postDto.getText());
            posts.setCreatedAt(new Date());
            thread.addPosts(posts);
        }

        threadsRepository.save(thread);
        return new ApiResponse("success", "Success!");
    }

    public SearchThreadDto searchItems(String text) {
        List<Thread> threads = threadsRepository.searchThreads(text);
        SearchThreadDto response = new SearchThreadDto();

        for (Thread thread : threads) {
            Map<String, List<String>> hmap = new HashMap<>();
            List<String> pstText = new ArrayList<>();
            pstText.add(thread.getTitle());
            for(Posts post : thread.getPosts()) {
                pstText.add(post.getText());
                hmap.put(String.valueOf(thread.getId()), pstText);
            }
            response.setSearchResults(hmap);
        }
        response.setStatus("success");
        response.setMessage("Success!");
        return response;
    }
}
