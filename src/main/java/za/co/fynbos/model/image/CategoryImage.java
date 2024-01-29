package za.co.fynbos.model.image;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import za.co.fynbos.model.Brand;
import za.co.fynbos.model.Category;

/**
 * @author Noxolo.Mkhungo
 */
@Getter
@Setter
@Entity
@Table(name = "category_image",schema = "db_commerce")
@DiscriminatorValue("category_images")
public class CategoryImage extends AbstractImage{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "category_has_category_images",
            joinColumns = @JoinColumn(name = "category_image_id", referencedColumnName = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id",foreignKey=@ForeignKey(name = "category_id_fk")
            ))
    private Category category;
}
