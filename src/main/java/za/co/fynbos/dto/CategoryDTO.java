package za.co.fynbos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.fynbos.model.Category;
import za.co.fynbos.model.Product;

import java.util.Set;

/**
 * @author Noxolo.Mkhungo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long categoryId;
    private String categoryName;
    private Set<Product> products;
    private Set<Category> categories;
}
