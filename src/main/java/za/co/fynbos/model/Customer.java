package za.co.fynbos.model;


import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customer",schema = "db_commerce")
public class Customer {

    @Id
    @SequenceGenerator(name = "customer_generator", sequenceName = "sequence_customer_id", allocationSize = 1, initialValue = 11101)
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
    @AttributeOverride(name="street", column=@Column(name="home_street"))
    @AttributeOverride(name="city", column=@Column(name="home_city"))
    @AttributeOverride(name="zipcode", column=@Column(name="home_zipcode"))
    private Address homeAddress;

    @Embedded
    @AttributeOverride(name="street", column=@Column(name="billing_street"))
    @AttributeOverride(name="city", column=@Column(name="billing_city"))
    @AttributeOverride(name="zipcode", column=@Column(name="billing_zipcode"))
    private Address billingAddress;
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(customerId).toHashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Customer other)) return false;
        return new EqualsBuilder().append(customerId, other.customerId).isEquals();
    }
}
