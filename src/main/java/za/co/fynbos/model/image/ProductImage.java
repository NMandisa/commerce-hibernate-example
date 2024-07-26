package za.co.fynbos.model.image;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import za.co.fynbos.enums.ProductImageType;
import za.co.fynbos.model.Product;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product_image",schema = "db_commerce")
public class ProductImage implements Serializable {
    @Id
    @SequenceGenerator(name = "product_image_generator", sequenceName = "sequence_product_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_image_generator")
    @Column(name = "product_image_id")
    private Long imageId;

    @Column(name = "product_image_url")
    private String imageUrl;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_has_product_images",
            joinColumns = @JoinColumn(name = "product_image_id", referencedColumnName = "product_image_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id",foreignKey=@ForeignKey(name = "product_id_fk")
            ))
    private Product product;

    @Column(name = "image_type")
    @Enumerated(EnumType.STRING)
    private ProductImageType imageType;

    public  ProductImage(String imageUrl){
        this.imageUrl=imageUrl;
    }
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(imageId).toHashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ProductImage other)) return false;
        return new EqualsBuilder().append(imageId, other.imageId).isEquals();
    }
    public String toString(){
        return "product image Id : "+imageId+" product image url: "+ imageUrl +"\n";
    }
}
