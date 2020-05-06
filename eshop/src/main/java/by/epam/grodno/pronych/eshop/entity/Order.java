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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import by.epam.grodno.pronych.eshop.service.dts.OrderMsg;

@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	int id;
	@Column(name="name")
	String name;
	@Column(name="sum")
	int sum;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")  
	private User user;
    
	@OneToMany(mappedBy = "order")
	private Set<Torder> torders = new HashSet<Torder>();
	
	@OneToMany(mappedBy = "order")
	private Set<Payment> payments = new HashSet<Payment>();

	public Order() {
	}
	
	public Order(int id, String name, int sum, User user, Set<Torder> torders, Set<Payment> payments) {
		this.id = id;
		this.name = name;
		this.sum = sum;
		this.user = user;
		this.torders = torders;
		this.payments = payments;
	}

	public Order(OrderMsg orderMsg) {
		this.name = orderMsg.getName();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Torder> getTorders() {
		return torders;
	}

	public void setTorders(Set<Torder> torders) {
		this.torders = torders;
	}

	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}
	
}
