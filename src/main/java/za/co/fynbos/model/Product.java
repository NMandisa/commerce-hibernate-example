/**
 * 
 */
package za.co.fynbos.model;

import java.io.Serializable;
import java.time.LocalDateTime;


import jakarta.persistence.*;
import lombok.*;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GeneratorType;
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
@Table(name = "product", schema = "commerce")
public class Product implements Serializable {

	
	@Id
	@SequenceGenerator(name = "product_generator", sequenceName = "product_sequence", allocationSize = 1,initialValue = 10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
	@Column(name = "product_id")
	private Long productId;
	@Column(name = "product_name")
	private String productName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id" )
	@JoinTable(
			name = "category_has_products",
			joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id",foreignKey=@ForeignKey(name = "product_category_fk")
	))
	private Category category;


	@Column(name = "date_created")
	@CreationTimestamp
	private LocalDateTime dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private LocalDateTime lastUpdated;

}
