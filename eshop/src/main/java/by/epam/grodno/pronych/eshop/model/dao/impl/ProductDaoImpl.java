package by.epam.grodno.pronych.eshop.model.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.epam.grodno.pronych.eshop.model.dao.ProductDao;
import by.epam.grodno.pronych.eshop.model.entity.Product;

@Repository("productDao")
public class ProductDaoImpl implements ProductDao{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Product").list();
    }

    @Override
    public int save(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(product);
        return product.getId();
    }

    @Override
    public void delete(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(product);
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);
        session.delete(product);
    }

    @Override
    public void update(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
        System.out.println("p:"+product+" sucessfully updated");
    }

    @Override
    public Product getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Product.class, id);
    }	
}
