package by.epam.grodno.pronych.eshop.model.dao;

import java.util.List;

import by.epam.grodno.pronych.eshop.model.entity.Order;
import by.epam.grodno.pronych.eshop.model.entity.User;

public interface OrderDao {
    List<Order> getAll();
    void save(Order order);
    void delete(Order order);
    void delete(int id);
    void update(Order order);
    Order getById(int id);
    int getSumOfOrdersByUser(User user);
	List getAllDebts();
}
