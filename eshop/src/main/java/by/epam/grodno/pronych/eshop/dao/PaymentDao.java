package by.epam.grodno.pronych.eshop.dao;

import java.util.List;

import by.epam.grodno.pronych.eshop.entity.Payment;

public interface PaymentDao {
    List<Payment> getAll();
    void save(Payment payment);
    void delete(Payment payment);
    void delete(int id);
    void update(Payment payment);
    Payment getById(int id);

}
