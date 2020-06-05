package by.epam.grodno.pronych.eshop.model.service;

import java.util.List;
import by.epam.grodno.pronych.eshop.model.dto.OrderDto;
import by.epam.grodno.pronych.eshop.model.dto.UserDto;
import by.epam.grodno.pronych.eshop.model.entity.Order;
import by.epam.grodno.pronych.eshop.model.entity.User;

public interface OrderService {
	public void save(OrderDto orderMsg);
    public void update(Order order);
    public void delete(Order order);
    public void delete(int id);
    public Order getById(int id);
    public List<Order> getAll();
    public List<OrderDto> getAllToJson();
    public OrderDto getByIdToJson(int id);
    public int getSumOfOrder(User user);
    public List<UserDto> getAllDebts();	
}
