/**
 * 
 */
package za.co.fynbos.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import za.co.fynbos.model.image.BrandImage;
import za.co.fynbos.model.image.CategoryImage;

/**
 * @author Noxolo.Mkhungo
 *
 */

@Getter
@Setter
@Entity
@NamedEntityGraph(
		name = "brand_products_entity_graph",
		attributeNodes = {@NamedAttributeNode("products"),@NamedAttributeNode("images")})
@NamedNativeQuery(
		name = "Brand.findByDescription",
		query = "select * from db_commerce.brand b where b.brand_description = :description",
		resultClass = Brand.class
)
@NamedNativeQuery(
		name = "Brand.findAllOrderByNameDESC",
		query = "select * from db_commerce.brand order by brand_name DESC",
		resultClass = Brand.class)
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

	@OneToMany(mappedBy = "brand",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	private List<BrandImage> images= new ArrayList<>();

	@OneToMany(mappedBy = "brand",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
	@Fetch(FetchMode.SUBSELECT)
	//@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
	/*@JoinTable(
			name = "brands_has_products",
			joinColumns = @JoinColumn(name = "brand_id", referencedColumnName = "brand_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id",foreignKey=@ForeignKey(name = "product_id_fk"))
	)*/
	private List<Product> products = new ArrayList<>();

	public  Brand( String brandName){
		this.brandName=brandName;
	}

	public void addProduct(Product product){
		products.add(product);
		product.setBrand(this);
	}
	public void removeProduct(Product product){
		products.remove(product);
		product.setBrand(null);
	}

	/*public void addProduct(Product product){
		products.add(product);
		for(Product addedproduct: products)
		{addedproduct.setBrands(Set.of(this));}
	}
	public void removeProduct(Product product){
		products.remove(product);
		product.addBrand(null);
	}
	public void addProducts(Set<Product> addProducts){
		products.addAll(addProducts);
		for(Product product: addProducts)
		{product.setBrands(Set.of(this));}
	}
	public void removeProducts(Set<Product> removeLProducts){
		products.removeAll(removeLProducts);
		for (Product product:removeLProducts){
			product.setBrands(Set.of());
		}
	}*/
	@Override
	public int hashCode() {return new HashCodeBuilder().append(brandId).toHashCode();}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Brand other)) return false;
		return new EqualsBuilder().append(brandId, other.brandId).isEquals();
	}
	public String toString(){
		return "brand id : "+brandId+" brand name : "+ brandName +" brand description : "+brandDescription+"\n";
	}
}
