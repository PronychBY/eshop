package by.epam.grodno.pronych.eshop.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import by.epam.grodno.pronych.eshop.model.dto.PaymentDto;
import by.epam.grodno.pronych.eshop.model.dto.ProductDto;

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

	public Payment() {
		
	}

	public Payment(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Payment(int id, String name, int sum) {
		this.id = id;
		this.name = name;
		this.sum = sum;
	}

	public Payment(PaymentDto paymentDto) {
		this.id = paymentDto.getId();
		this.name = paymentDto.getName();
		this.sum = paymentDto.getSum();
		this.user = paymentDto.getUser();
		this.order = paymentDto.getOrder();
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + sum;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Payment other = (Payment) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (sum != other.sum)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
    
    
    
    
}
