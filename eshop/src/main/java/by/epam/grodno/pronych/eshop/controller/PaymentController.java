package by.epam.grodno.pronych.eshop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import by.epam.grodno.pronych.eshop.model.dto.PaymentDto;
import by.epam.grodno.pronych.eshop.model.service.PaymentService;
import by.epam.grodno.pronych.eshop.security.jwt.JwtProvider;

@CrossOrigin("*")
@RestController
@RequestMapping("/payment")
public class PaymentController {
	private PaymentService paymentService;
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);

	@Autowired
	PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/list")
	public ResponseEntity<List<PaymentDto>> list() {
		List<PaymentDto> listData = paymentService.getAllToJson();
		return ResponseEntity.ok().body(listData);
	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/get/{id}")
	public ResponseEntity<?> get(@PathVariable("id") int id) {
		PaymentDto paymentDto = paymentService.getByIdToJson(id);
		return ResponseEntity.ok().body(paymentDto);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (LOGGER.isInfoEnabled()){
			LOGGER.info("delete id:" + id);
		}
		paymentService.delete(id);
		return ResponseEntity.ok().body(id);
	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@PostMapping("/add")
	public ResponseEntity<Integer> add(@RequestBody PaymentDto paymentDto) {
		if (LOGGER.isInfoEnabled()){
			LOGGER.info(paymentDto.toString());
		}
		int id = paymentService.save(paymentDto);
		return ResponseEntity.ok().body(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/update")
	public ResponseEntity<PaymentDto> update(@RequestBody PaymentDto paymentDto) {
		paymentService.update(paymentDto);
		return ResponseEntity.ok().body(paymentDto);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/orders")
	public ResponseEntity<PaymentDto> getOrders(@RequestBody PaymentDto paymentDto) {
		return ResponseEntity.ok().body(paymentDto);
	}

}
