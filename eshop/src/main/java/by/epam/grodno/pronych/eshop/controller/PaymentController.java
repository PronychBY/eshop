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

import by.epam.grodno.pronych.eshop.model.dto.PaymentDto;
import by.epam.grodno.pronych.eshop.model.entity.Payment;
import by.epam.grodno.pronych.eshop.model.service.PaymentService;


@CrossOrigin("*")
@RestController
@RequestMapping("/payment")
public class PaymentController {
	PaymentService paymentService;
	
	@Autowired
	PaymentController(PaymentService paymentService){
		this.paymentService = paymentService;
	}

	//@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/list")
    public ResponseEntity<List<PaymentDto>> list() {
    	List < PaymentDto > listData = paymentService.getAllToJson();
        //return ResponseEntity.ok().header("Access-Control-Allow-Origin", "*").body(listData);
        return ResponseEntity.ok().body(listData);
    }
	
	
	//@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/get/{id}")
	public ResponseEntity<?> get(@PathVariable("id") int id) {
		PaymentDto paymentDto = paymentService.getByIdToJson(id);		
    	return ResponseEntity.ok().body(paymentDto);
	}	
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		System.out.println("delete id:" +id);
		paymentService.delete(id);
		return ResponseEntity.ok().body(id);
	}	
    
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/add")
    public ResponseEntity<Integer> add(@RequestBody PaymentDto paymentDto) {
		Payment payment = new Payment(paymentDto);
        int id = paymentService.save(payment);
        
        //return ResponseEntity.ok().body("Product with id="+newId+" added successfully!");
        //return new ResponseEntity<>("Hello World!", HttpStatus.OK);
        return ResponseEntity.ok().body(id);
    }	
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/update")
    public ResponseEntity<PaymentDto> update(@RequestBody PaymentDto paymentDto) {
        //System.out.println(paymentDto);
		//Product product = productService.getById(productMsg.getId());
        paymentService.update(paymentDto);
        //return ResponseEntity.ok().body("Product with id="+productMsg.getId()+" updated successfully!");
        return ResponseEntity.ok().body(paymentDto);
    }	
	
}
