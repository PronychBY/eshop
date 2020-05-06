package by.epam.grodno.pronych.eshop.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="torder")
public class Torder {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	int id;
	@Column(name="name")
	String name;
	@Column(name="count")
	int count;
	@Column(name="sum")
	int sum;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")  
	private Order order;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")  
	private Product product;
    
    /*@ManyToMany(mappedBy = "torders")    
    private Set<Product> products = new HashSet<Product>();
    */

}
