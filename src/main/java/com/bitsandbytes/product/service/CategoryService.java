package com.bitsandbytes.product.service;

import com.bitsandbytes.product.dto.CategoryDTO;
import com.bitsandbytes.product.entity.Category;
import com.bitsandbytes.product.mapper.CategoryMapper;
import com.bitsandbytes.product.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

    private  CategoryRepository categoryRepository;

    //    create categories----> takes input as DTO and convert to entity (store to entity)
//    kaise hoga?----> phela kaam dto se data lelo
//    mapper ko bolo ki ek function banaye jo dto ko entity(category ) me convert kar de
    public  CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category =CategoryMapper.toCategoryEntity(categoryDTO); //converts dto to entity,, and store the category to category variable
        category=categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);
    }
//    get  all categories
//    get  categories by id
//    delete categories

}
