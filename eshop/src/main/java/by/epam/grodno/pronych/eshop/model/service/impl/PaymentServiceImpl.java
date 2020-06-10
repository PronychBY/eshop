package by.epam.grodno.pronych.eshop.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.grodno.pronych.eshop.model.dao.OrderDao;
import by.epam.grodno.pronych.eshop.model.dao.PaymentDao;
import by.epam.grodno.pronych.eshop.model.dao.UserDao;
import by.epam.grodno.pronych.eshop.model.dto.PaymentDto;
import by.epam.grodno.pronych.eshop.model.entity.Payment;
import by.epam.grodno.pronych.eshop.model.service.PaymentService;

@Service("paymentService")
@Transactional(readOnly=true)
public class PaymentServiceImpl implements PaymentService{
	private final PaymentDao dao;
	private final UserDao userDao;
	private final OrderDao orderDao;
	
	@Autowired
	public PaymentServiceImpl(PaymentDao dao, UserDao userDao, OrderDao orderDao) {
		this.dao = dao;
		this.userDao = userDao;
		this.orderDao = orderDao;
	}
	
	@Transactional
    public int save(PaymentDto paymentDto) {
		Payment payment = new Payment(paymentDto);
		payment.setUser(userDao.getById(paymentDto.getUserId()));
		payment.setOrder(orderDao.getById(paymentDto.getOrderId()));
		return dao.save(payment);
    }

	@Transactional
    public void update(PaymentDto paymentMsg) {
		Payment payment = getById(paymentMsg.getId());
		payment.setName(paymentMsg.getName());
    	payment.setSum(paymentMsg.getSum());
    	payment.setOrder(paymentMsg.getOrder());
    	payment.setUser(paymentMsg.getUser());
    	dao.update(payment);
    }

	@Transactional
    public void delete(Payment payment) {
        dao.delete(payment);
    }

	@Transactional
    public void delete(int id) {
        dao.delete(id);
    }

    public Payment getById(int id) {
    	Payment payment = dao.getById(id);
        return payment;
    }
    
    public List<Payment> getAll() {
    	List<Payment> payments = dao.getAll();
        return payments;
    }
    
    private List<PaymentDto> paymentToPaymentMsg(List<Payment> payments){
    	ArrayList<PaymentDto> paymentMsg = new ArrayList<PaymentDto>();
    	for (Payment paym : payments){
    		paymentMsg.add(new PaymentDto(paym));
    	}
        return paymentMsg;
    }
    
    public List<PaymentDto> getAllToJson() {
    	List<Payment> payments = dao.getAll();
        return paymentToPaymentMsg(payments);
    }
    
    public PaymentDto getByIdToJson(int id) {
    	return new PaymentDto(dao.getById(id));
    }

}
