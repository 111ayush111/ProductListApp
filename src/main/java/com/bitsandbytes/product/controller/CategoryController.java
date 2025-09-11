package com.bitsandbytes.product.controller;

import com.bitsandbytes.product.dto.CategoryDTO;
import com.bitsandbytes.product.mapper.CategoryMapper;
import com.bitsandbytes.product.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//SERVICE  <--->  CONTROLLER <------> DTO
@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

//    create categories
//    howto create?  --> agar create karna paad raha hai entity to jarur data dto se daalna padega
//    ---- to output te entity and input dto hoga
@PostMapping
public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
    return categoryService.createCategory(categoryDTO);
}


//    get  all categories
//    get  categories by id
//    delete categories

}
