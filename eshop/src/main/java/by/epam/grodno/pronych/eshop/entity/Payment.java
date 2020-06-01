package by.epam.grodno.pronych.eshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="payment")
public class Payment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	int id;
	@Column(name="name")
	String name;
	@Column(name="sum")
	int sum;

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id")  
	private Order order;
	
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")  
	private User user;
	
}
