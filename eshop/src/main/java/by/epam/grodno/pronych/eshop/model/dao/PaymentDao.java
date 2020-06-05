package by.epam.grodno.pronych.eshop.model.dao;

import java.util.List;

import by.epam.grodno.pronych.eshop.model.entity.Payment;

public interface PaymentDao {
    List<Payment> getAll();
    int save(Payment payment);
    void delete(Payment payment);
    void delete(int id);
    void update(Payment payment);
    Payment getById(int id);

}
