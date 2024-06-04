package com.upwork.webforumapp.dto.category;

import com.upwork.webforumapp.dto.ApiResponse;

import java.util.List;

public class ReadAllCategoryResponseDto extends ApiResponse {
    private List<CategoryDto> categoryDtoList;

    public ReadAllCategoryResponseDto() {
    }

    public ReadAllCategoryResponseDto(String status, String message) {
        super(status, message);
    }

    public ReadAllCategoryResponseDto(String status, String message, List<CategoryDto> categoryDtoList) {
        super(status, message);
        this.categoryDtoList = categoryDtoList;
    }

    public List<CategoryDto> getCategoryDtoList() {
        return categoryDtoList;
    }

    public void setCategoryDtoList(List<CategoryDto> categoryDtoList) {
        this.categoryDtoList = categoryDtoList;
    }
}
