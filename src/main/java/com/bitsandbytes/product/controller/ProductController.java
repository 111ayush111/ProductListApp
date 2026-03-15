package com.bitsandbytes.product.controller;


import com.bitsandbytes.product.dto.ProductDTO;
import com.bitsandbytes.product.entity.Category;
import com.bitsandbytes.product.entity.Product;
import com.bitsandbytes.product.mapper.ProductMapper;
import com.bitsandbytes.product.repository.CategoryRepository;
import com.bitsandbytes.product.repository.ProductRepository;
import com.bitsandbytes.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Product REST API CRUD Operation",description = "we can handle All CRUD operation for Product REST API")
//we need controller to control
//for that we need REST API
//rest api ke liea @RestContoller use akrna hota hai
@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {


private ProductService productService;

        @Operation(
                summary = "Fetch all Products",
                description="REST API to fetch all Products"
        )
//    get ALL Product
    @GetMapping
    public List<ProductDTO> getALlProducts() {
        return productService.getAllProducts();
    }


//    get product by id
        @Operation(
        summary = "Fetch Products by ID",
        description="REST API to Fetch Products by ID"
        )
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }


//    create product

        @Operation(
        summary = "Create Product",
        description="REST API to Create Product"
            )
        @ApiResponse(
                responseCode="201",description="Product CREATED"
        )

        @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @PostMapping
     public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
            productDTO = productService.createProduct(productDTO);
            return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }


//    update product
@PreAuthorize("hasAuthority('ROLE_SELLER')")
@PutMapping("/{id}")

        @Operation(
        summary = "Update Product",
        description="REST API to Update Product"
        )
public ProductDTO updateProductById(@RequestParam long id, @RequestBody ProductDTO productDTO) {
productDTO = productService.updateProductById(id, productDTO);

    return productDTO;
}



//    delete product
    @Operation(
            summary = "Delete Product",
            description="REST API to Delete P roduct"
    )
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @DeleteMapping("/{id}")
    public String deleteProductById(@RequestParam long id) {
            return productService.deleteProductById(id);
    }
}
