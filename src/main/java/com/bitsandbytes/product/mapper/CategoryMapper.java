package com.bitsandbytes.product.mapper;

import com.bitsandbytes.product.dto.CategoryDTO;
import com.bitsandbytes.product.entity.Category;


//DTO<----> MAPPER <----> ENTITY
//MAPPER ka kaam yahi hai ki data, dto ko entity me save kare ya vice versa
//to hamlog yaha ek category(entity ) ka object banate hai and usme data store karte hai (category.setName use kar ke)
//or ye data kaha se milega? -> ye milega dto se, islea (categoryDTO.getName() use kar rahe hai)
public class CategoryMapper {

//    dto to entity
    public static Category toCategoryEntity(CategoryDTO categoryDTO) {

        Category category = new Category();// ENTITY KA OBJECT BANAO
        category.setName(categoryDTO.getName());
         return category;

    }
//    entity to dto
    public static CategoryDTO toCategoryDTO(Category category) {
        if(category == null) {
            return null;
        }
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setProducts(category.getProducts().stream().map(ProductMapper::toProductDTO).toList());

        return categoryDTO;
    }
}
