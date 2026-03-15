package com.bitsandbytes.product.dto;

import com.bitsandbytes.product.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Schema(
        name = "Product",
        description = "It holds Product Information"
)

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long CategoryId;
}
