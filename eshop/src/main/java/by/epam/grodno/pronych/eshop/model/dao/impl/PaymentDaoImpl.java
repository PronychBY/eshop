package by.epam.grodno.pronych.eshop.model.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import by.epam.grodno.pronych.eshop.model.dao.PaymentDao;
import by.epam.grodno.pronych.eshop.model.entity.Payment;

public class PaymentDaoImpl implements PaymentDao{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Payment> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Payment").list();
    }

    @Override
    public int save(Payment payment) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(payment);
        return payment.getId();
    }

    @Override
    public void delete(Payment payment) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(payment);
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Payment product = session.get(Payment.class, id);
        session.delete(product);
    }

    @Override
    public void update(Payment payment) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(payment);
        System.out.println("p:"+payment+" sucessfully updated");
    }

    @Override
    public Payment getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Payment.class, id);
    }	

}
