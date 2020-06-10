package by.epam.grodno.pronych.eshop.model.service;

import java.util.List;

import by.epam.grodno.pronych.eshop.model.dto.PaymentDto;
import by.epam.grodno.pronych.eshop.model.entity.Payment;

public interface PaymentService {
    public int save(PaymentDto paymentDto);
    public void update(PaymentDto paymentMsg);
    public void delete(Payment payment);
    public void delete(int id);
    public Payment getById(int id);
    public List<Payment> getAll();
    public List<PaymentDto> getAllToJson();
    public PaymentDto getByIdToJson(int id);

}
