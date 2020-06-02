package by.epam.grodno.pronych.eshop.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.epam.grodno.pronych.eshop.entity.Product;
import by.epam.grodno.pronych.eshop.service.ProductService;
import by.epam.grodno.pronych.eshop.service.dts.ProductMsg;

/*
 * Система Интернет-магазин. 
 * Администратор осуществляет ведение каталога Товаров. add update delete getall getbyid
 * Клиент делает и оплачивает Заказ на Товары. addorder updateorder deleteorder getbyid getbyuser 
 * Администратор может занести неплательщиков в “черный список”. moveToBlackList(user) removeFromBlackList(User)
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/list")
    public ResponseEntity<List<ProductMsg>> list() {
    	List < ProductMsg > listData = productService.getAllToJson();
        //return ResponseEntity.ok().header("Access-Control-Allow-Origin", "*").body(listData);
        return ResponseEntity.ok().body(listData);
    }
	
	//@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/get/{id}")
	public ResponseEntity<?> get(@PathVariable("id") int id) {
		ProductMsg productMsg = productService.getByIdToJson(id);		
    	return ResponseEntity.ok().body(productMsg);
	}	
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		System.out.println("delete id:" +id);
		productService.delete(id);
		return ResponseEntity.ok().body(id);
	}	
    
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/add")
    public ResponseEntity<Integer> add(@RequestBody ProductMsg productMsg) {
        Product product = new Product(productMsg);
        int id = productService.save(product);
        
        //return ResponseEntity.ok().body("Product with id="+newId+" added successfully!");
        //return new ResponseEntity<>("Hello World!", HttpStatus.OK);
        return ResponseEntity.ok().body(id);
    }	
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/update")
    public ResponseEntity<ProductMsg> update(@RequestBody ProductMsg productMsg) {
        //System.out.println(productMsg);
		//Product product = productService.getById(productMsg.getId());
        productService.update(productMsg);
 		
        //return ResponseEntity.ok().body("Product with id="+productMsg.getId()+" updated successfully!");
        return ResponseEntity.ok().body(productMsg);
    }	
	
}
