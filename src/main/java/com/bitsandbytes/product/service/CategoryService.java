package com.bitsandbytes.product.service;

import com.bitsandbytes.product.dto.CategoryDTO;
import com.bitsandbytes.product.entity.Category;
import com.bitsandbytes.product.exception.CategoryAlreadyExistsException;
import com.bitsandbytes.product.mapper.CategoryMapper;
import com.bitsandbytes.product.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private  CategoryRepository categoryRepository;

    //    1>create categories----> takes input as DTO and convert to entity (store to entity)
//    kaise hoga?----> phela kaam dto se data lelo
//    mapper ko bolo ki ek function banaye jo dto ko entity(category ) me convert kar de
    public  CategoryDTO createCategory(CategoryDTO categoryDTO) {
//        avoiding duplicate category------>kaise?---->  (aasie , ksise, waise jaise ..mai padhon teri aakhon ko)
//        aaise ------>categoryDTO.getName(), name ka koi category exist karta hai to CategoryAlreadyExistsException de do
//        optional ka matlab yeh hai ki, either it is of type category or is null

        Optional<Category> optionalCategory= categoryRepository.findByName(categoryDTO.getName());

        // if not null
        if(optionalCategory.isPresent()) {
            throw new CategoryAlreadyExistsException("Category "+ categoryDTO.getName() +" Already Exists");
        }

//      otherwise normally
        Category category =CategoryMapper.toCategoryEntity(categoryDTO); //converts dto to entity,, and store the category to category variable
        category=categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);
    }
//    2>get  all categories
    public List<CategoryDTO> getAllCategories() {
//        sare categories repository se milegi
//        categoryRepository.findAll().stream().map(CategoryMapper::toCategoryDTO).toList();
        return categoryRepository.findAll().stream().map(CategoryMapper::toCategoryDTO).toList();
    }
    //    3>get  categories by id
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return CategoryMapper.toCategoryDTO(category);
    }

//    4>delete categories
    public String  deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
        return "category with id "+id+" Deleted Successfully";
    }
}
