/**
 * 
 */
package za.co.fynbos.model;

import java.io.Serializable;
import java.util.*;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
		name = "category_products_entity_graph",
		attributeNodes = {
				@NamedAttributeNode("products"),
				@NamedAttributeNode("categories")
		})
@Table(name = "category",schema = "db_commerce")
public class Category implements Serializable{
	
	@Id
	@SequenceGenerator(name = "category_generator", sequenceName = "sequence_category_id", allocationSize = 1,initialValue =101)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
	@Column(name = "category_id")
	private Long categoryId;
	@Column(name = "category_name")
	private String categoryName;

	@OneToMany(
			mappedBy = "category",
			cascade={CascadeType.PERSIST, CascadeType.REMOVE},fetch = FetchType.LAZY,
			orphanRemoval = true
	)
	@Fetch(FetchMode.SUBSELECT)
	private Set<Product> products = new HashSet<>();
	@OneToMany(
			cascade={CascadeType.PERSIST, CascadeType.REMOVE,CascadeType.MERGE},fetch = FetchType.LAZY,
			orphanRemoval = true
	)
	//@JoinColumn(name = "category_id" )
	@JoinTable(
			name = "category_has_categories",
			joinColumns = @JoinColumn(name = "parent_category_id", referencedColumnName = "category_id"),
			inverseJoinColumns = @JoinColumn(name = "child_category_id", referencedColumnName = "category_id",foreignKey=@ForeignKey(name = "categories_category_fk")
			))
	private Set<Category> categories = new HashSet<>();

	public Category(String categoryName){
		this.categoryName=categoryName;
	}
	public Set<Category> getCategories(){
		return  categories;
	}

	public Set<Product> getProducts(){
		return products;
	}

	public Category getCategory(Category category){
		return category;
	}

	public void addProduct(Product product){
		products.add(product);
		product.setCategory(this);
	}

	public void removeProduct(Product product){
		products.remove(product);
		product.setCategory(null);
	}



	public void addCategory(Category category){
		this.categories.add(category);
		category.getCategory(category);
	}

	public void addCategories(Set<Category>childCategories){
		this.categories.addAll(childCategories);
		this.setCategories(categories);
	}

	public void removeCategory(Category category){
		categories.remove(category);
		category.getCategory(null);
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(categoryId).toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Category other)) return false;
		return new EqualsBuilder().append(categoryId, other.categoryId).isEquals();
	}
	public String toString(){
		return "Category id : "+categoryId+" category name : "+ categoryName +"\n products :"+products.toString()+"\n child categories :"+categories.toString();
		//return "Category id"+categoryId+" category name "+ categoryName +" products "+products.iterator().next().toString()+" child categories "+categories.iterator().next().toString();
	}
}
