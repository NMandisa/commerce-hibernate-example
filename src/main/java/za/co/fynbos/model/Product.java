/**
 * 
 */
package za.co.fynbos.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;
import za.co.fynbos.model.image.ProductImage;

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
		name = "product_images_brand_entity_graph",
		attributeNodes = {@NamedAttributeNode("images"), @NamedAttributeNode("brand")})
@NamedQuery(
		name = "Product.findAllOrderByProductNameDesc",
		query = "SELECT p from Product p ORDER By p.productName DESC")
@NamedQuery(
		name = "Product.findAllOrderByProductNameASC",
		query = "SELECT p from Product p ORDER By p.productName ASC")
@NamedQuery(
		name = "Product.findById",
		query = "SELECT p from Product p where p.id = :id")
@Table(name = "product", schema = "db_commerce" )
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
	@Fetch(FetchMode.SELECT)
	@JoinTable(
			name = "category_has_products",
			joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"),foreignKey=@ForeignKey(name = "product_fk"),
			inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id",foreignKey=@ForeignKey(name = "category_fk")

	))
	private Category category;

	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	private List<ProductImage> images= new ArrayList<>();

	//@ManyToMany(mappedBy = "products",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
	//@Fetch(FetchMode.SUBSELECT)
	/*@JoinTable(
			name = "products_has_brands",
			joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "brand_id", referencedColumnName = "brand_id",foreignKey=@ForeignKey(name = "product_brand_fk"))
	)*/
	//private Set<Brand> brands = new HashSet<>();
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "brands_has_products",
			joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "brand_id", referencedColumnName = "brand_id",foreignKey=@ForeignKey(name = "product_brand_fk"))
	)
	private Brand brand;

	@Column(name = "product_date_created")
	@CreationTimestamp
	private LocalDateTime productDateCreated;

	@Column(name = "product_last_updated")
	@UpdateTimestamp
	private LocalDateTime productLastUpdated;
	public Product(String productName){this.productName=productName;}
	public void addBrand(Brand brand){
		brand.addProduct(this);
	}
	public void removeBrand(Brand brand){
		brand.removeProduct(null);
	}
	/*public void addBrand(Brand brand){
		brands.add(brand);
		brand.setProducts(Set.of(this));
	}
	public void removeBrand(Brand brand){
		brands.remove(brand);
		brand.setProducts(Set.of());
	}*/

	public void addImage(ProductImage image){
		images.add(image);
		image.setProduct(this);
	}
	public void removeImage(ProductImage image){
		images.remove(image);
		image.setProduct(null);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(productId).toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Product other)) return false;
		return new EqualsBuilder().append(productId, other.productId).isEquals();
	}

	/*@Override
	public boolean equals(Object o) {
		if (this == o) {return true;}
		if (!(o instanceof Product)) {return false;}
		return productId != null && productId.equals(((Product) o).getProductId());}
	@Override
	public int hashCode() {return getClass().hashCode();}*/

	public String toString(){
		return "product id : "+productId+" product name : "+ productName +"\n product images : "+images.toString()+"\n  product price :"+productPrice+" last updated : "+productLastUpdated+ " date created : " +productDateCreated+ "\n brands :"+brand+"\n";
	}
}
