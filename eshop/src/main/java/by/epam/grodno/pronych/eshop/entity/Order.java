package by.epam.grodno.pronych.eshop.entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import by.epam.grodno.pronych.eshop.service.dts.ProductMsg;

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
	
	String address;
	String phone;

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")  
	private User user;
    
	@OneToMany(mappedBy = "order", cascade=CascadeType.ALL)
	private Set<Torder> torders = new HashSet<Torder>();
	
	@OneToMany(mappedBy = "order", cascade=CascadeType.ALL)
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
		this.user = orderMsg.getUser();
		//this.address = orderMsg.getAddress();
		//this.phone = orderMsg.getPhone();
		
		//for (ProductMsg productMsg : orderMsg.getProducts()) {
		//	Torder torder = new Torder();
		//	torder.setProduct(new Product(productMsg));
		//	this.torders.add(torder);
		//}		
		
		//
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", name=" + name + ", sum=" + sum + ", address=" + address + ", phone=" + phone
				+ ", user=" + user + ", torders=" + torders + ", payments=" + payments + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + sum;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (sum != other.sum)
			return false;
		return true;
	}
	
}
