package com.dataJpa.Relationship.Service;

import com.dataJpa.Relationship.DTOs.Mapper;
import com.dataJpa.Relationship.DTOs.RequestDto.CategoryRequestDto;
import com.dataJpa.Relationship.DTOs.ResponseDto.CategoryResponseDto;
import com.dataJpa.Relationship.Models.Category;
import com.dataJpa.Relationship.Repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;


    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategory(Long categoryId) {
            return categoryRepository.findById(categoryId).orElseThrow( ()->
                    new IllegalArgumentException("Could not find category with id: "+categoryId)
            );
    }

    @Override
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        categoryRepository.save(category);

        return Mapper.categoryToCategoryResponseDto(category);
    }

    @Override
    public CategoryResponseDto getCategoryById(Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new IllegalArgumentException("Category with id: "+categoryId + " could not be found"));

        return Mapper.categoryToCategoryResponseDto(category);
    }

    @Override
    public List<CategoryResponseDto> getCategories() {

        List<Category> categories = StreamSupport
                .stream(categoryRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());

        return Mapper.categoriesToCategoryResponseDtos(categories)  ;
    }

    @Override
    public CategoryResponseDto deleteCategory(Long categoryId) {

        Category category = new Category();
        categoryRepository.delete(category);

        return Mapper.categoryToCategoryResponseDto(category);
    }

    @Transactional
    @Override
    public CategoryResponseDto editCategory(Long categoryId, CategoryRequestDto categoryRequestDto) {
        Category categoryToEdit = new Category();
        categoryToEdit.setName(categoryRequestDto.getName());

        return Mapper.categoryToCategoryResponseDto(categoryToEdit);
    }
}
