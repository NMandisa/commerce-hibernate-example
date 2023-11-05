package za.co.fynbos.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class ProductImage implements Serializable {

    /*@Id
    @SequenceGenerator(name = "product_generator", sequenceName = "product_sequence", allocationSize = 1,initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @Column(name = "product_image_id")
    private Long productImageId;*/
    @Column(name = "product_image_url")
    private String productImageUrl;

    public  ProductImage(String productImageUrl){
        this.productImageUrl=productImageUrl;
    }
}
