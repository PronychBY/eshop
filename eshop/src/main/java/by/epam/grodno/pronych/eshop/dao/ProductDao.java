package by.epam.grodno.pronych.eshop.dao;

import java.util.List;

import by.epam.grodno.pronych.eshop.entity.Product;

public interface ProductDao {
    List<Product> getAll();
    void save(Product product);
    void delete(Product product);
    void delete(int id);
    void update(Product product);
    Product getById(int id);
}
