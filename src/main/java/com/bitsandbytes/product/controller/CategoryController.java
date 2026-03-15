package com.bitsandbytes.product.controller;

import com.bitsandbytes.product.dto.CategoryDTO;
import com.bitsandbytes.product.dto.ProductDTO;
import com.bitsandbytes.product.exception.CategoryAlreadyExistsException;
import com.bitsandbytes.product.mapper.CategoryMapper;
import com.bitsandbytes.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Category REST API CRUD Operation",description = "we can handle All CRUD operation for Category  REST API")

//SERVICE  <--->  CONTROLLER <------> DTO
@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

//    create categories
//    howto create?  --> agar create karna paad raha hai entity to jarur data dto se daalna padega
//    ---- to output te entity and input dto hoga


    //    2>get  all categories

    @GetMapping
    @Operation(
            summary = "Fetch All Category ",
            description="REST API to Fetch All Category "
    )
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

//    1>create category

    @Operation(
            summary = "Create Category",
            description="REST API to Create Category"
    )
    @ApiResponse(
            responseCode="201",description="category CREATED"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO) {
//         try{
//            CategoryDTO savedCategory= categoryService.createCategory(categoryDTO);
//             return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
//        }
//         catch(CategoryAlreadyExistsException e){
//             return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//         }
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);

    }

    //    3>get  categories by id
    @GetMapping("/{id}")
    @Operation(
            summary = "Fetch Category By ID",
            description="REST API to Fetch Category By ID"
    )
    public CategoryDTO getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
    //   4> delete categories
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete Category By ID",
            description="REST API to Delete Category By ID"
    )
    public String  deleteCategoryById(@PathVariable Long id) {
       return categoryService.deleteCategoryById(id);
    }
}
