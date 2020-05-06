package by.epam.grodno.pronych.eshop.service.dts;

import by.epam.grodno.pronych.eshop.entity.Product;

public class ProductMsg {

	private int id;
	private String name;
    private int price;
	
	public ProductMsg(){
		
	}
	public ProductMsg(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public ProductMsg(int id, String name,int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public ProductMsg(Product product) {
		this.id = product.getId();
		this.name = product.getName();
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}
