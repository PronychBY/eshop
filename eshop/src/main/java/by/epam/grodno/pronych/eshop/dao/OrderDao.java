package by.epam.grodno.pronych.eshop.dao;

import java.util.List;

import by.epam.grodno.pronych.eshop.entity.Order;

public interface OrderDao {
    List<Order> getAll();
    void save(Order order);
    void delete(Order order);
    void delete(int id);
    void update(Order order);
    Order getById(int id);
}
