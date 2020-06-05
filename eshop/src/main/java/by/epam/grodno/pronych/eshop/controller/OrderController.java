package by.epam.grodno.pronych.eshop.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.epam.grodno.pronych.eshop.model.dto.OrderDto;
import by.epam.grodno.pronych.eshop.model.entity.Order;
import by.epam.grodno.pronych.eshop.model.service.impl.OrderServiceImpl;

/*
 * Система Интернет-магазин. 
 * Администратор осуществляет ведение каталога Товаров. add update delete getall getbyid
 * Клиент делает и оплачивает Заказ на Товары. addorder updateorder deleteorder getbyid getbyuser 
 * Администратор может занести неплательщиков в “черный список”. moveToBlackList(user) removeFromBlackList(User)
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderServiceImpl orderService;

	//@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/list")
    public ResponseEntity<List<OrderDto>> list() {
    	List < OrderDto > listData = orderService.getAllToJson();
        //return ResponseEntity.ok().header("Access-Control-Allow-Origin", "*").body(listData);
        return ResponseEntity.ok().body(listData);
    }
	
	//@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/get/{id}")
	public ResponseEntity<?> get(@PathVariable("id") int id) {
		OrderDto orderMsg = orderService.getByIdToJson(id);		
    	return ResponseEntity.ok().body(orderMsg);
	}	
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		orderService.delete(id);
		return ResponseEntity.ok().body(id);
	}	
    
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/add")
    public ResponseEntity<OrderDto> add(@RequestBody OrderDto orderMsg) {
		//System.out.println(orderMsg);
		//Order order = new Order(orderMsg);
        orderService.save(orderMsg);
        
        //return ResponseEntity.ok().body("Product with id="+newId+" added successfully!");
        //return new ResponseEntity<>("Hello World!", HttpStatus.OK);
        return ResponseEntity.ok().body(orderMsg);
    }	
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody OrderDto orderMsg) {
		Order order = orderService.getById(orderMsg.getId());
        orderService.update(order);
 		
        return ResponseEntity.ok().body("Product with id="+orderMsg.getId()+" updated successfully!");
    }	
	
}
