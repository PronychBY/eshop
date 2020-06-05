package by.epam.grodno.pronych.eshop.model.dto;

import by.epam.grodno.pronych.eshop.model.entity.Order;
import by.epam.grodno.pronych.eshop.model.entity.Payment;
import by.epam.grodno.pronych.eshop.model.entity.Product;
import by.epam.grodno.pronych.eshop.model.entity.User;

public class PaymentDto {
	int id;
	String name;
	int sum;
	Order order;
	User user;
	
	public PaymentDto(){
		
	}
	public PaymentDto(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public PaymentDto(int id, String name,int sum) {
		this.id = id;
		this.name = name;
		this.sum = sum;
	}
	public PaymentDto(Payment payment) {
		this.id = payment.getId();
		this.name = payment.getName();
		this.sum = payment.getSum();
		this.user = payment.getUser();
		this.order = payment.getOrder();
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
	
	

}
