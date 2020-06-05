package by.epam.grodno.pronych.eshop.model.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.epam.grodno.pronych.eshop.model.dao.UserDao;
import by.epam.grodno.pronych.eshop.model.entity.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User").list();
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Override
    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        session.delete(user);
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public User getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, (long)id);
    }
    
    @Override
    public void moveToBlackList(User user) {
    	user.setInBlackList(true);
    }
    
    @Override
    public void removeFromBlackList(User user) {
    	user.setInBlackList(false);
    }
    
	public User getByUserName(String name) {
		Query q = sessionFactory.getCurrentSession().createQuery("from User as u where u.username = :name");
        q.setParameter("name", name);
    	return (User)q.uniqueResult();
	}
 
	public User getByName(String name) {
		Query q = sessionFactory.getCurrentSession().createQuery("from User as u where u.name = :name");
        q.setParameter("name", name);
    	return (User)q.uniqueResult();
	}

    
}
