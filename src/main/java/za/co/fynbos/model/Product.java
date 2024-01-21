/**
 * 
 */
package za.co.fynbos.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(
			name = "category_has_products",
			joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id",foreignKey=@ForeignKey(name = "product_category_fk")
	))
	private Category category;

	@OneToMany
	@JoinTable(
			name = "product_has_product_images",
			joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "product_image_id", referencedColumnName = "product_image_id",foreignKey=@ForeignKey(name = "product_image_id_fk")
			))
	private Set<ProductImage> productImages;

	@ManyToMany(mappedBy = "products",cascade = CascadeType.ALL)
	/*@JoinTable(
			name = "products_has_brands",
			joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "brand_id", referencedColumnName = "brand_id",foreignKey=@ForeignKey(name = "product_brand_fk"))
	)*/
	private List<Brand> brands ;

	@Column(name = "date_created")
	@CreationTimestamp
	private LocalDateTime dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private LocalDateTime lastUpdated;
	public Product(String productName){this.productName=productName;}

}
