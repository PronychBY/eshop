package by.epam.grodno.pronych.eshop.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import by.epam.grodno.pronych.eshop.model.dto.ProductDto;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;

	@Column(name = "name")
	String name;

	@Column(name = "price")
	int price;

	@OneToMany(mappedBy = "product")
	private Set<Torder> torders = new HashSet<Torder>();

	public Product() {

	}

	public Product(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Product(int id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Product(ProductDto productMsg) {

		this.id = productMsg.getId();
		this.name = productMsg.getName();
		this.price = productMsg.getPrice();
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

	public Set<Torder> getTorders() {
		return torders;
	}

	public void setTorders(Set<Torder> torders) {
		this.torders = torders;
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
		Product other = (Product) obj;
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

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", torders=" + torders + "]";
	}


}
