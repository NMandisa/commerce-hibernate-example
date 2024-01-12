/**
 * 
 */
package za.co.fynbos.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Noxolo.Mkhungo
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "brand", schema = "commerce")
public class Brand implements Serializable {
	
	@Id
	@SequenceGenerator(name = "brand_generator", sequenceName = "sequence_brand", allocationSize = 1, initialValue = 100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_generator")
	@Column(name = "brand_id")
	private Long brandId;

	@Column(name = "brand_name")
	private String brandName;

	@ManyToMany
	@JoinTable(
			name = "brands_has_products",
			joinColumns = @JoinColumn(name = "product_brand_id", referencedColumnName = "brand_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id",foreignKey=@ForeignKey(name = "brand_product_fk"))
	)
	private List<Product>products ;

	public  Brand( String brandName){
		this.brandName=brandName;
	}


}
