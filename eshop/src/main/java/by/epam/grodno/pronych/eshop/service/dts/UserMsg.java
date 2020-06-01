package by.epam.grodno.pronych.eshop.service.dts;

import by.epam.grodno.pronych.eshop.entity.User;

public class UserMsg {
	private long id;
	private String name;
	private int sumOfOrders;
	private int payments;
	private boolean isInBlackList;
	
	public UserMsg() {
	}
	
	public UserMsg(int id, String name, boolean isInBlackList) {
		this.id = id;
		this.name = name;
		this.isInBlackList = isInBlackList;
	}
	public UserMsg(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.isInBlackList = user.isInBlackList();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isInBlackList() {
		return isInBlackList;
	}

	public void setInBlackList(boolean isInBlackList) {
		this.isInBlackList = isInBlackList;
	}

	public int getSumOfOrders() {
		return sumOfOrders;
	}

	public void setSumOfOrders(int sumOfOrders) {
		this.sumOfOrders = sumOfOrders;
	}

	public int getPayments() {
		return payments;
	}

	public void setPayments(int payments) {
		this.payments = payments;
	}

	@Override
	public String toString() {
		return "UserMsg [id=" + id + ", name=" + name + ", sumOfOrders=" + sumOfOrders + ", payments=" + payments
				+ ", isInBlackList=" + isInBlackList + "]";
	}
	
	
	
	
}
