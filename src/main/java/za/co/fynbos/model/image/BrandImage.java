package za.co.fynbos.model.image;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import za.co.fynbos.model.Brand;

/**
 * @author Noxolo.Mkhungo
 */
@Getter
@Setter
@Entity
@AttributeOverride(name="imageId", column=@Column(name="brand_image_id"))
@AttributeOverride(name="imageName", column=@Column(name="brand_image_name"))
@AttributeOverride(name="imageUrl", column=@Column(name="brand_image_url"))
@Table(name = "brand_image",schema = "db_commerce")
@DiscriminatorValue("brand_images")
public class BrandImage extends AbstractImage{

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "brand_has_brand_images",
            joinColumns = @JoinColumn(name = "brand_image_id", referencedColumnName = "brand_image_id"),
            inverseJoinColumns = @JoinColumn(name = "brand_id", referencedColumnName = "brand_id",foreignKey=@ForeignKey(name = "brand_image_id_fk")
            ))
    private Brand brand;
}
