package by.epam.grodno.pronych.eshop.service.dts;

import by.epam.grodno.pronych.eshop.entity.Order;

public class OrderMsg {

	private int id;
	private String name;
	
	public OrderMsg(){
		
	}
	public OrderMsg(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public OrderMsg(Order order) {
		this.id = order.getId();
		this.name = order.getName();
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

}
