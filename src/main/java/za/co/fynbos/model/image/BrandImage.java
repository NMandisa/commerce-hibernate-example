package za.co.fynbos.model.image;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import za.co.fynbos.model.Brand;
import za.co.fynbos.model.Product;

/**
 * @author Noxolo.Mkhungo
 */
@Getter
@Setter
@Entity
@Table(name = "brand_image",schema = "db_commerce")
@DiscriminatorValue("brand_images")
public class BrandImage extends AbstractImage{

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "brand_has_brand_images",
            joinColumns = @JoinColumn(name = "brand_image_id", referencedColumnName = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "brand_id", referencedColumnName = "brand_id",foreignKey=@ForeignKey(name = "brand_image_id_fk")
            ))
    private Brand brand;
}
