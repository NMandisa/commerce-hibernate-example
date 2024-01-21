/**
 * 
 */
package za.co.fynbos.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
@NamedNativeQuery(
		name = "Brand.findByDescription",
		query = "select * from db_commerce.brand b where b.brand_description = :description",
		resultClass = Product.class)
@NamedNativeQuery(
		name = "Brand.findAllOrderByNameDESC",
		query = "select * from db_commerce.brand order by brand_name DESC",
		resultClass = Product.class)
@Table(name = "brand", schema = "db_commerce")
public class Brand implements Serializable {
	
	@Id
	@SequenceGenerator(name = "brand_generator", sequenceName = "sequence_brand_id", allocationSize = 1, initialValue = 100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_generator")
	@Column(name = "brand_id")
	private Long brandId;

	@Column(name = "brand_name")
	private String brandName;

	@Column(name = "brand_description")
	private String brandDescription;

	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(
			name = "brands_has_products",
			joinColumns = @JoinColumn(name = "brand_id", referencedColumnName = "brand_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id",foreignKey=@ForeignKey(name = "product_id_fk"))
	)
	private Set<Product>products = new HashSet<>();

	public  Brand( String brandName){
		this.brandName=brandName;
	}

	public void addProduct(Product product){
		products.add(product);
		product.setBrands(List.of(this));
	}
	public void addProducts(Set<Product> addedProducts){
		products.addAll(addedProducts);
		for(Product product: addedProducts)
		{product.setBrands(List.of(this));}
	}
}
