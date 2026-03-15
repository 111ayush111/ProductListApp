package com.bitsandbytes.product.service;

import com.bitsandbytes.product.dto.ProductDTO;
import com.bitsandbytes.product.entity.Category;
import com.bitsandbytes.product.entity.Product;
import com.bitsandbytes.product.exception.CategoryNotFoundException;
import com.bitsandbytes.product.mapper.ProductMapper;
import com.bitsandbytes.product.repository.CategoryRepository;
import com.bitsandbytes.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

//    1>create Product
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
//        1>product ka category v hai ... to phele wo find karo...
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category Id "+productDTO.getCategoryId()+" not found"));
//        "Category Id "+productDTO.getCategoryId()+" not found"------>this is String and also body of message
//        constructor(String) need a string, which is taken as body message in GlobalExceptionHandler

//        DTO ---->Entity
        Product product = ProductMapper.toProductEntity(productDTO, category);
        product = productRepository.save(product);

//        Entity-->DTO
        return ProductMapper.toProductDTO(product);
        }

//        2>get all Products
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(ProductMapper::toProductDTO).toList();
        }

//       3> get product by id
    public ProductDTO getProductById(@RequestParam long id) {
        Product product=(productRepository.findById(id).orElseThrow(()->new RuntimeException("product not found")));
        return ProductMapper.toProductDTO(product);
    }
//   4> update product
    public ProductDTO updateProductById(@RequestParam long id, @RequestBody ProductDTO productDTO) {
                    Product product = productRepository.findById(id).orElseThrow(()->new RuntimeException("product not found"));
                    Category category= categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(()->new RuntimeException("category not found"));
                    product.setCategory(category);
                    product.setName(productDTO.getName());
                    product.setDescription(productDTO.getDescription());
                    product.setPrice(productDTO.getPrice());
                    productRepository.save(product);
                    return ProductMapper.toProductDTO(product);
    }
//    5> delete Product
    public String deleteProductById(long id) {
        productRepository.deleteById(id);
        return "Product with "+id+" is deleted";
    }
}

