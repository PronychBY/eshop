package by.epam.grodno.pronych.eshop.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.epam.grodno.pronych.eshop.model.dao.ProductDao;
import by.epam.grodno.pronych.eshop.model.dto.ProductDto;
import by.epam.grodno.pronych.eshop.model.entity.Product;
import by.epam.grodno.pronych.eshop.model.service.ProductService;

@Service("productService")
@Transactional(readOnly=true)
public class ProductServiceImpl implements ProductService {
	private final ProductDao dao;
	
	@Autowired
	public ProductServiceImpl(ProductDao dao) {
		this.dao = dao;
	}
	
	@Transactional
    public int save(Product product) {
    	return dao.save(product);
    }

	@Transactional
    public void update(ProductDto productMsg) {
    	Product product = getById(productMsg.getId());
    	product.setName(productMsg.getName());
    	product.setPrice(productMsg.getPrice());
    	dao.update(product);
    }

	@Transactional
    public void delete(Product product) {
        dao.delete(product);
    }

	@Transactional
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
    
    private List<ProductDto> productToProductMsg(List<Product> products){
    	ArrayList<ProductDto> productMsg = new ArrayList<ProductDto>();
    	for (Product prod : products){
    		productMsg.add(new ProductDto(prod));
    	}
        return productMsg;
    }
    
    public List<ProductDto> getAllToJson() {
    	List<Product> products = dao.getAll();
        return productToProductMsg(products);
    }
    
    public ProductDto getByIdToJson(int id) {
    	return new ProductDto(dao.getById(id));
    }
    
}
