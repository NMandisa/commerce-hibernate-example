/**
 * 
 */
package za.co.fynbos.model;

import java.io.Serializable;
import java.util.*;

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
@NamedEntityGraph(
		name = "category_products_entity_graph",
		attributeNodes = {
				@NamedAttributeNode("products")
		})
@NamedEntityGraph(
		name = "category_categories_entity_graph",
		attributeNodes = {
				@NamedAttributeNode("categories")
		})
@Table(name = "category",schema = "db_commerce")
public class Category implements Serializable{
	
	@Id
	@SequenceGenerator(name = "category_generator", sequenceName = "sequence_category_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
	@Column(name = "category_id")
	private Long categoryId;
	@Column(name = "category_name")
	private String categoryName;

	@OneToMany(
			mappedBy = "category",
			cascade={CascadeType.PERSIST, CascadeType.REMOVE},
			orphanRemoval = true
	)
	private List<Product> products = new ArrayList<>();
	@OneToMany(
			cascade={CascadeType.PERSIST, CascadeType.REMOVE},
			orphanRemoval = true
	)
	//@JoinColumn(name = "category_id" )
	@JoinTable(
			name = "category_has_categories",
			joinColumns = @JoinColumn(name = "parent_category_id", referencedColumnName = "category_id"),
			inverseJoinColumns = @JoinColumn(name = "child_category_id", referencedColumnName = "category_id",foreignKey=@ForeignKey(name = "categories_category_fk")
			))
	private Set<Category> categories = new HashSet<>();


	public Set<Category> getCategories(){
		return  categories;
	}

	public List<Product> getProducts(){
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
		categories.add(category);
		category.getCategory(category);
	}

	public void addCategories(Set<Category>childCategories){
		categories.addAll(childCategories);
		this.setCategories(categories);
	}

	public void removeCategory(Category category){
		categories.remove(category);
		category.getCategory(null);
	}
}
