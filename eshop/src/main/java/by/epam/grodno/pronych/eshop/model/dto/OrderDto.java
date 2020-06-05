package by.epam.grodno.pronych.eshop.model.dto;

import java.util.List;

import by.epam.grodno.pronych.eshop.model.entity.Order;
import by.epam.grodno.pronych.eshop.model.entity.User;

public class OrderDto {

	private int id;
	private String name;
	private String phone;
	private String address;
	List<ProductDto> products;
	User user;
	
	public OrderDto(){
		
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<ProductDto> getProducts() {
		return products;
	}
	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}
	public OrderDto(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public OrderDto(Order order) {
		this.id = order.getId();
		this.name = order.getName();
		
		this.address = order.getAddress();
		this.phone = order.getPhone();
		this.user = order.getUser();
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "OrderMsg [id=" + id + ", name=" + name + ", phone=" + phone + ", address=" + address + ", products="
				+ products + ", user=" + user + "]";
	}

}
