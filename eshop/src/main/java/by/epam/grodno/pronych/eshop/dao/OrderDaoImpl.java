package by.epam.grodno.pronych.eshop.dao;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.epam.grodno.pronych.eshop.entity.Order;
import by.epam.grodno.pronych.eshop.entity.User;
import by.epam.grodno.pronych.eshop.service.dts.UserMsg;

@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Order> getAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Order").list();
	}

	@Override
	public void save(Order order) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(order);
	}

	@Override
	public void delete(Order order) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(order);
	}

	@Override
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Order order = session.get(Order.class, id);
		session.delete(order);
	}

	@Override
	public void update(Order order) {
		Session session = sessionFactory.getCurrentSession();
		session.update(order);
	}

	@Override
	public Order getById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Order.class, id);
	}

	@Override
	public int getSumOfOrdersByUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT SUM(e.sum) as res " + "FROM Order as e Where e.user=:user " + "GROUP BY e.user";
		Query query = session.createQuery(hql);
		query.setParameter("user", user);
		int result = (int) (long) query.uniqueResult();
		return result;
	}

	
	@Override
	public List<UserMsg> getAllDebts() {
		Session session = sessionFactory.getCurrentSession();

		String hql = "SELECT SUM(t.sum) as sum, t.order.user.id as user_id, t.order.user.name as user_name, t.order.user.isInBlackList as user_isInBlackList, SUM(coalesce(p.sum,0)) as paysum FROM Torder as t "
				+ "left join t.order.payments as p";
		Query query = session.createQuery(hql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List list = query.list();

		List<UserMsg> results = new LinkedList<UserMsg>();
		for (Object obj : list) {
			Map row = (Map) obj;
			UserMsg userMsg = new UserMsg();
			userMsg.setId((long)row.get("user_id"));
			userMsg.setInBlackList((boolean)row.get("user_isInBlackList"));
			userMsg.setName((String)row.get("user_name"));
			userMsg.setSumOfOrders((int)(long)row.get("sum"));
			userMsg.setPayments((int)(long)row.get("paysum"));
			
			results.add(userMsg);
		}		
		//System.out.println(results);
		return results;
	}
}
