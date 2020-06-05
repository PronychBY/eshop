package by.epam.grodno.pronych.eshop.model.dao;

import java.util.List;

import by.epam.grodno.pronych.eshop.model.entity.Product;

public interface ProductDao {
    List<Product> getAll();
    int save(Product product);
    void delete(Product product);
    void delete(int id);
    void update(Product product);
    Product getById(int id);
}
