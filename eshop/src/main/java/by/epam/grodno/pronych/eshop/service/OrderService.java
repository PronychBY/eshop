package by.epam.grodno.pronych.eshop.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.epam.grodno.pronych.eshop.dao.OrderDao;
import by.epam.grodno.pronych.eshop.entity.Order;
import by.epam.grodno.pronych.eshop.service.dts.OrderMsg;

@Component("orderService")
@Transactional
public class OrderService {
	private final OrderDao dao;
	
	@Autowired
	public OrderService(OrderDao dao) {
		this.dao = dao;
	}
	
    public void save(Order order) {
    	dao.save(order);
    }

    public void update(Order order) {
    	dao.update(order);
    }

    public void delete(Order order) {
        dao.delete(order);
    }

    public void delete(int id) {
        dao.delete(id);
    }

    public Order getById(int id) {
    	Order order = dao.getById(id);
        return order;
    }
    
    public List<Order> getAll() {
    	List<Order> orders = dao.getAll();
        return orders;
    }
    
    private List<OrderMsg> orderToOrderMsg(List<Order> orders){
    	ArrayList<OrderMsg> orderMsg = new ArrayList<OrderMsg>();
    	for (Order ord : orders){
    		orderMsg.add(new OrderMsg(ord));
    	}
        return orderMsg;
    }
    
    public List<OrderMsg> getAllToJson() {
    	List<Order> orders = dao.getAll();
        return orderToOrderMsg(orders);
    }
    
    public OrderMsg getByIdToJson(int id) {
    	return new OrderMsg(dao.getById(id));
    }
    
    
}
