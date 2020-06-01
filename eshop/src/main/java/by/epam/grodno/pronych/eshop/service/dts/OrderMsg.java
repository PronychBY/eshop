package by.epam.grodno.pronych.eshop.service.dts;

import java.util.List;

import by.epam.grodno.pronych.eshop.entity.Order;
import by.epam.grodno.pronych.eshop.entity.User;

public class OrderMsg {

	private int id;
	private String name;
	private String phone;
	private String address;
	List<ProductMsg> products;
	User user;
	
	public OrderMsg(){
		
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
	public List<ProductMsg> getProducts() {
		return products;
	}
	public void setProducts(List<ProductMsg> products) {
		this.products = products;
	}
	public OrderMsg(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public OrderMsg(Order order) {
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
