package by.epam.grodno.pronych.eshop.model.dto;

import by.epam.grodno.pronych.eshop.model.entity.Product;

public class ProductDto {

	private int id;
	private String name;
    private int price;
	
	public ProductDto(){
		
	}
	public ProductDto(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public ProductDto(int id, String name,int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public ProductDto(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
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
	@Override
	public String toString() {
		return "ProductMsg [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + price;
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
		ProductDto other = (ProductDto) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price != other.price)
			return false;
		return true;
	}

	
	
}
