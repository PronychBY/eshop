package by.epam.grodno.pronych.eshop.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.epam.grodno.pronych.eshop.dao.OrderDao;
import by.epam.grodno.pronych.eshop.entity.Order;
import by.epam.grodno.pronych.eshop.entity.Torder;
import by.epam.grodno.pronych.eshop.entity.User;
import by.epam.grodno.pronych.eshop.service.dts.OrderMsg;
import by.epam.grodno.pronych.eshop.service.dts.ProductMsg;
import by.epam.grodno.pronych.eshop.service.dts.UserMsg;

@Service("orderService")
@Transactional(readOnly = true)
public class OrderService {
	private final OrderDao dao;
	private ProductService productService;
	
	@Autowired
	public OrderService(OrderDao dao, ProductService productService) {
		this.dao = dao;
		this.productService = productService;
	}
	
	@Transactional
	public void save(OrderMsg orderMsg) {
    	Order order = new Order();
		order.setName(orderMsg.getName());
		order.setAddress(orderMsg.getAddress());
		order.setPhone(orderMsg.getPhone());
		order.setUser(orderMsg.getUser());
		
		Set<Torder> torders = new HashSet<Torder>();
    	for (ProductMsg productMsg : orderMsg.getProducts()) {
    		Torder torder = new Torder();
    		torder.setProduct(productService.getById(productMsg.getId()));
			torder.setSum(productMsg.getPrice());
			torder.setOrder(order);
			torders.add(torder);
		}		
    	order.setTorders(torders);
    	
    	dao.save(order);
    }

	@Transactional
    public void update(Order order) {
    	dao.update(order);
    }

	@Transactional
    public void delete(Order order) {
        dao.delete(order);
    }

	@Transactional
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
    
    public int getSumOfOrder(User user) {
    	return dao.getSumOfOrdersByUser(user);
    }
    
    public List<UserMsg> getAllDebts() {
    	return dao.getAllDebts();
    }
    
    
}
