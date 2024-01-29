package za.co.fynbos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.fynbos.model.Brand;
import za.co.fynbos.model.Category;
import za.co.fynbos.model.image.ProductImage;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Noxolo.Mkhungo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long productId;
    private String productName;
    private double productPrice;

    private Category category;
    private Set<ProductImage> productImages;


    private Set<Brand> brands = new HashSet<>();

    private LocalDateTime dateCreated;

    private LocalDateTime lastUpdated;
}
