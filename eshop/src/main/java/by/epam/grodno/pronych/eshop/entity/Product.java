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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import by.epam.grodno.pronych.eshop.service.dts.ProductMsg;

/*
 * Система Интернет-магазин. 
 * Администратор осуществляет ведение каталога Товаров. 
 * Клиент делает и оплачивает Заказ на Товары. 
 * Администратор может занести неплательщиков в “черный список”.
 */
@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;

	@Column(name = "name")
	String name;

	public Product() {
	
	}

	public Product(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Product(ProductMsg productMsg) {
		this.name = productMsg.getName();
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

	public Set<Torder> getTorders() {
		return torders;
	}

	public void setTorders(Set<Torder> torders) {
		this.torders = torders;
	}

	@OneToMany(mappedBy = "product")
	private Set<Torder> torders = new HashSet<Torder>();
	
	/*@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "product_torder", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "torder_id"))
	private Set<Torder> torders = new HashSet<Torder>();
	*/
}
