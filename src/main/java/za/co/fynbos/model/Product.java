/**
 * 
 */
package za.co.fynbos.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Noxolo.Mkhungo
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedEntityGraph(
		name = "product_brands_entity_graph",
		attributeNodes = {@NamedAttributeNode("brands")})
@NamedEntityGraph(
		name = "product_images_entity_graph",
		attributeNodes = {@NamedAttributeNode("productImages")})
@NamedQuery(
		name = "Product.findAllOrderByProductNameDesc",
		query = "SELECT p from Product p ORDER By p.productName DESC")
@NamedQuery(
		name = "Product.findAllOrderByProductNameASC",
		query = "SELECT p from Product p ORDER By p.productName ASC")
@NamedQuery(
		name = "Product.findById",
		query = "SELECT p from Product p where p.id = :id")
@Table(name = "product", schema = "db_commerce")
public class Product implements Serializable {
	
	@Id
	@SequenceGenerator(name = "product_generator", sequenceName = "sequence_product_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
	@Column(name = "product_id")
	private Long productId;
	@Column(name = "product_name")
	private String productName;
	@Column(name = "product_price")
	private double productPrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(
			name = "category_has_products",
			joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id",foreignKey=@ForeignKey(name = "product_category_fk")
	))
	private Category category;

	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private Set<ProductImage> productImages;

	@ManyToMany(mappedBy = "products",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
	/*@JoinTable(
			name = "products_has_brands",
			joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "brand_id", referencedColumnName = "brand_id",foreignKey=@ForeignKey(name = "product_brand_fk"))
	)*/
	private Set<Brand> brands = new HashSet<>();

	@Column(name = "date_created")
	@CreationTimestamp
	private LocalDateTime dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private LocalDateTime lastUpdated;
	public Product(String productName){this.productName=productName;}
	public void addBrand(Brand brand){
		brands.add(brand);
		brand.setProducts(Set.of(this));
	}
	public void removeBrand(Brand brand){
		brands.remove(brand);
		brand.setProducts(Set.of(null));
	}

}
