package by.epam.grodno.pronych.eshop.model.service;

import java.util.List;

import by.epam.grodno.pronych.eshop.model.dto.ProductDto;
import by.epam.grodno.pronych.eshop.model.entity.Product;

public interface ProductService {
    public int save(Product product);
    public void update(ProductDto productMsg);
    public void delete(Product product);
    public void delete(int id);
    public Product getById(int id);
    public List<Product> getAll();
    public List<ProductDto> getAllToJson();
    public ProductDto getByIdToJson(int id);
}
