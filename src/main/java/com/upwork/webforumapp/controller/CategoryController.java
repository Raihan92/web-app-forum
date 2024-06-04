package com.upwork.webforumapp.controller;

import com.upwork.webforumapp.dto.ApiResponse;
import com.upwork.webforumapp.dto.category.CategoryCreateResponseDto;
import com.upwork.webforumapp.dto.category.CategoryDto;
import com.upwork.webforumapp.dto.category.CategoryList;
import com.upwork.webforumapp.dto.category.ReadAllCategoryResponseDto;
import com.upwork.webforumapp.exceptions.AuthFailException;
import com.upwork.webforumapp.service.AuthService;
import com.upwork.webforumapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<?> createCategories(@RequestHeader("session") String token,
                                              @RequestBody CategoryList categoryList) throws Exception {
        authService.authenticate(token);
        CategoryCreateResponseDto dto = categoryService.createCategories(categoryList);
        HttpHeaders headers = authService.getHeader(token);
        return new ResponseEntity<>(dto, headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories(@RequestHeader("session") String token) throws Exception {
        authService.authenticate(token);
        ReadAllCategoryResponseDto categoryDtos = categoryService.readCategories();
        HttpHeaders headers = authService.getHeader(token);
        return new ResponseEntity<>(categoryDtos, headers, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCategory(@RequestHeader("session") String token,
                                            @RequestParam("category") String category) throws Exception {
        authService.validateAdministrator(token);
        ApiResponse dto = categoryService.deleteCategory(category);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
