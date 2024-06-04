package com.upwork.webforumapp.service;

import com.upwork.webforumapp.dto.ApiResponse;
import com.upwork.webforumapp.dto.category.CategoryCreateResponseDto;
import com.upwork.webforumapp.dto.category.CategoryDto;
import com.upwork.webforumapp.dto.category.CategoryList;
import com.upwork.webforumapp.dto.category.ReadAllCategoryResponseDto;
import com.upwork.webforumapp.exceptions.NotFondException;
import com.upwork.webforumapp.model.Category;
import com.upwork.webforumapp.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CategoryService {

    Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryCreateResponseDto createCategories(CategoryList categoryList) {
        for (String categoryName : categoryList.getCategories()) {
            Category category = prepareCategory(categoryName);
            categoryRepository.save(category);
        }
        return new CategoryCreateResponseDto("success", "Success!");
    }

    private Category prepareCategory(String categoryName) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setCreatedAt(new Date());
        return category;
    }

    public Category findCategory(String categoryName) throws NotFondException {
        Category category = categoryRepository.findByCategoryName(categoryName);
        if(category == null)
            throw new NotFondException("Not Found");
        return category;
    }

    public ReadAllCategoryResponseDto readCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories != null && categories.size() > 0) {
            List<CategoryDto> categoryDtos = new ArrayList<>();
            for (Category category : categories) {
                CategoryDto categoryDto = new CategoryDto();
                BeanUtils.copyProperties(category, categoryDto);
                categoryDtos.add(categoryDto);
            }
            return new ReadAllCategoryResponseDto("success", "Success!", categoryDtos);
        } else {
            return new ReadAllCategoryResponseDto("success", "No Category Found!");
        }
    }

    @Transactional
    public ApiResponse deleteCategory(String category) {
        categoryRepository.deleteByCategoryName(category);
        return new ApiResponse("success", "Success!");
    }
}
