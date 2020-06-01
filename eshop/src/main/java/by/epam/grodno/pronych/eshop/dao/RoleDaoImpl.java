package by.epam.grodno.pronych.eshop.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.epam.grodno.pronych.eshop.entity.Role;
import by.epam.grodno.pronych.eshop.entity.RoleName;


@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Role").list();
    }

    @Override
    public void save(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(role);
    }

    @Override
    public void delete(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(role);
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Role role = session.get(Role.class, id);
        session.delete(role);
    }

    @Override
    public void update(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.update(role);
    }

    @Override
    public Role getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Role.class, id);
    }
	
    @Override
	public Role getByName(RoleName name) {
        Query q = sessionFactory.getCurrentSession().createQuery("from Role as u where u.name = :name");
        q.setParameter("name", name);
    	return (Role)q.uniqueResult();
    }
    
    @Override
	public Role getByName(String name) {
    	Query q = sessionFactory.getCurrentSession().createQuery("from Role as u where u.name = :name");
        q.setParameter("name", RoleName.valueOf(name));
    	return (Role)q.uniqueResult();
    }
	
}
