package za.co.fynbos.model.image;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Noxolo.Mkhungo
 */
@Getter
@Setter
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,name = "image_type")
//@DiscriminatorOptions(force = true,insert = false)
public abstract class AbstractImage implements Serializable {
    @Id
    @SequenceGenerator(name = "image_generator", sequenceName = "sequence_image_id", allocationSize = 10, initialValue = 10101)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_generator")
    @Column(name = "image_id")
    private long imageId;

    @Column(name = "image_name")
    private String imageName;
    @Column(name = "image_url")
    private String imageUrl;
}
