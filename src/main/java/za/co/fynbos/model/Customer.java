package za.co.fynbos.model;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @SequenceGenerator(name = "customer_generator", sequenceName = "customer_sequence", allocationSize = 1, initialValue = 11101)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_surname")
    private String customerSurname;

    @Embedded
    private Address address;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="street", column=@Column(name="home_street")),
            @AttributeOverride(name="city", column=@Column(name="home_city")),
            @AttributeOverride(name="zipcode", column=@Column(name="home_zipcode"))
    } )
    private Address homeAddress;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="street", column=@Column(name="billing_street")),
            @AttributeOverride(name="city", column=@Column(name="billing_city")),
            @AttributeOverride(name="zipcode", column=@Column(name="billing_zipcode"))
    } )
    private Address billingAddress;
}
