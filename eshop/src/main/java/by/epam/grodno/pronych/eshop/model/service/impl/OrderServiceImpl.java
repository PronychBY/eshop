package by.epam.grodno.pronych.eshop.model.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.epam.grodno.pronych.eshop.model.dao.OrderDao;
import by.epam.grodno.pronych.eshop.model.dto.OrderDto;
import by.epam.grodno.pronych.eshop.model.dto.ProductDto;
import by.epam.grodno.pronych.eshop.model.dto.UserDto;
import by.epam.grodno.pronych.eshop.model.entity.Order;
import by.epam.grodno.pronych.eshop.model.entity.Torder;
import by.epam.grodno.pronych.eshop.model.entity.User;
import by.epam.grodno.pronych.eshop.model.service.OrderService;
import by.epam.grodno.pronych.eshop.model.service.ProductService;
import by.epam.grodno.pronych.eshop.model.service.UserService;

@Service("orderService")
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
	private final OrderDao dao;
	private ProductService productService;
	private UserService userService;

	@Autowired
	public OrderServiceImpl(OrderDao dao, ProductService productService, UserService userService) {
		this.dao = dao;
		this.productService = productService;
		this.userService = userService;
	}

	@PostConstruct
	public void init() {
		userService.setBeanOrderService(this);
	}

	@Transactional
	public void save(OrderDto orderMsg) {
		Order order = new Order();
		order.setName(orderMsg.getName());
		order.setAddress(orderMsg.getAddress());
		order.setPhone(orderMsg.getPhone());
		order.setUser(userService.getById((int) orderMsg.getUser().getId()));

		Set<Torder> torders = new HashSet<Torder>();
		for (ProductDto productMsg : orderMsg.getProducts()) {
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

	private List<OrderDto> orderToOrderMsg(List<Order> orders) {
		ArrayList<OrderDto> orderMsg = new ArrayList<OrderDto>();
		for (Order ord : orders) {
			orderMsg.add(new OrderDto(ord));
		}
		return orderMsg;
	}

	public List<OrderDto> getAllToJson() {
		List<Order> orders = dao.getAll();
		return orderToOrderMsg(orders);
	}

	public OrderDto getByIdToJson(int id) {
		return new OrderDto(dao.getById(id));
	}

	public int getSumOfOrder(User user) {
		return dao.getSumOfOrdersByUser(user);
	}

	public List<UserDto> getAllUserDebts() {
		return dao.getAllUserDebts();
	}

	public List<OrderDto> getAllOrdersDebts() {
		return dao.getAllOrdersDebts();
	}

}
