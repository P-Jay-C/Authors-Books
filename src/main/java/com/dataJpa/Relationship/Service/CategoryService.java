package com.dataJpa.Relationship.Service;

import com.dataJpa.Relationship.DTOs.RequestDto.CategoryRequestDto;
import com.dataJpa.Relationship.DTOs.ResponseDto.CategoryResponseDto;
import com.dataJpa.Relationship.Models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    public Category getCategory(Long categoryId);
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto);
    public CategoryResponseDto getCategoryById(Long categoryId);

    public List<CategoryResponseDto> getCategories();
    public CategoryResponseDto deleteCategory(Long categoryId);

    public CategoryResponseDto editCategory(Long categoryId, CategoryRequestDto categoryRequestDto);
}
