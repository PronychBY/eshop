package by.epam.grodno.pronych.eshop.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.epam.grodno.pronych.eshop.dao.ProductDao;
import by.epam.grodno.pronych.eshop.entity.Product;
import by.epam.grodno.pronych.eshop.service.dts.ProductMsg;



@Component("productService")
@Transactional
public class ProductService {
	private final ProductDao dao;
	
	@Autowired
	public ProductService(ProductDao dao) {
		this.dao = dao;
	}
	
    public void save(Product product) {
    	dao.save(product);
    }

    public void update(Product product) {
    	dao.update(product);
    }

    public void delete(Product product) {
        dao.delete(product);
    }

    public void delete(int id) {
        dao.delete(id);
    }

    public Product getById(int id) {
    	Product product = dao.getById(id);
        return product;
    }
    
    public List<Product> getAll() {
    	List<Product> products = dao.getAll();
        return products;
    }
    
    private List<ProductMsg> productToProductMsg(List<Product> products){
    	ArrayList<ProductMsg> productMsg = new ArrayList<ProductMsg>();
    	for (Product prod : products){
    		productMsg.add(new ProductMsg(prod));
    	}
        return productMsg;
    }
    
    public List<ProductMsg> getAllToJson() {
    	List<Product> products = dao.getAll();
        return productToProductMsg(products);
    }
    
    public ProductMsg getByIdToJson(int id) {
    	return new ProductMsg(dao.getById(id));
    }
    
}
