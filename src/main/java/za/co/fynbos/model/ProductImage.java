package za.co.fynbos.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class ProductImage implements Serializable {

    @Column(name = "product_image_url")
    private String productImageUrl;

    public  ProductImage(String productImageUrl){
        this.productImageUrl=productImageUrl;
    }
}
