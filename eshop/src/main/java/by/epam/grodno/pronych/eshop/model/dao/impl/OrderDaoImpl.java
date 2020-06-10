package by.epam.grodno.pronych.eshop.model.dao.impl;

import java.util.List;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.epam.grodno.pronych.eshop.model.dao.OrderDao;
import by.epam.grodno.pronych.eshop.model.dto.OrderDto;
import by.epam.grodno.pronych.eshop.model.dto.UserDto;
import by.epam.grodno.pronych.eshop.model.entity.Order;
import by.epam.grodno.pronych.eshop.model.entity.User;

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
	@SuppressWarnings("unchecked")
	public List<OrderDto> getAllOrdersWithSum() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT SUM(t.sum) as sum, t.order.id, t.order.name, t.order.address, t.order.phone, t.order.user  "
				+ "FROM Torder as t GROUP BY t.order";
		Query query = session.createQuery(hql);
		return query.list();
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
	public List<OrderDto> getAllOrdersDebts() {
		Session session = sessionFactory.getCurrentSession();

		String sql = "SELECT subq.order_id, subq.sum, o.name, o.user_id, u.name as user_name, u.isInBlackList, SUM(COALESCE(p.sum,0)) as paysum"
				+ " FROM (SELECT t.order_id, SUM(t.sum) as sum FROM Torder as t GROUP BY t.order_id) as subq"
				+ " inner join orders as o ON subq.order_id = o.id" + " inner join user as u ON o.user_id = u.id"
				+ " left outer join payment as p ON subq.order_id = p.order_id"
				+ " GROUP BY subq.order_id, subq.sum, o.name, o.user_id, u.name, u.isInBlackList";
		SQLQuery query = session.createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List data = query.list();
		List<OrderDto> results = new LinkedList<OrderDto>();
		for (Object object : data) {
			Map row = (Map) object;

			OrderDto orderDto = new OrderDto();
			orderDto.setId((int) row.get("order_id"));
			orderDto.setName((String) row.get("name"));
			orderDto.setUserId(((BigInteger) row.get("user_id")).intValue());
			orderDto.setSumOfOrder(((BigDecimal) row.get("sum")).intValue());
			orderDto.setSumOfPayment(((BigDecimal) row.get("paysum")).intValue());

			results.add(orderDto);
		}
		System.out.println(results);
		return results;
	}

	@Override
	public List<UserDto> getAllUserDebts() {
		Session session = sessionFactory.getCurrentSession();

		String sql = "SELECT subq2.user_id, subq2.sum, u.name as user_name, u.isInBlackList, subq2.paysum"
				+ " FROM (SELECT subq.user_id, subq.sum, SUM(COALESCE(p.sum,0)) as paysum" + " FROM "
				+ "(SELECT o.user_id, SUM(t.sum) as sum FROM Torder as t"
				+ " inner join orders as o ON o.id = t.order_id" + " GROUP BY o.user_id) as subq"
				+ " left outer join payment as p ON p.user_id = subq.user_id"
				+ " GROUP BY subq.user_id, subq.sum) as subq2" + " inner join user as u ON u.id = subq2.user_id";

		SQLQuery query = session.createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List data = query.list();
		List<UserDto> results = new LinkedList<UserDto>();
		for (Object object : data) {
			Map row = (Map) object;

			UserDto userMsg = new UserDto();
			userMsg.setId(((BigInteger) row.get("user_id")).longValue());
			userMsg.setInBlackList((boolean) row.get("isInBlackList"));
			userMsg.setName((String) row.get("user_name"));
			userMsg.setSumOfOrders(((BigDecimal) row.get("sum")).intValue());
			userMsg.setPayments(((BigDecimal) row.get("paysum")).intValue());

			results.add(userMsg);
		}
		return results;
	}

	public List<UserDto> getAllDebts() {
		Session session = sessionFactory.getCurrentSession();

		String hql = "SELECT SUM(t.sum) as sum, t.order.user.id as user_id, t.order.user.name as user_name, t.order.user.isInBlackList as user_isInBlackList, SUM(coalesce(p.sum,0)) as paysum FROM Torder as t "
				+ "left join t.order.payments as p";
		Query query = session.createQuery(hql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List list = query.list();

		List<UserDto> results = new LinkedList<UserDto>();
		for (Object obj : list) {
			Map row = (Map) obj;

			UserDto userMsg = new UserDto();
			userMsg.setId((long) row.get("user_id"));
			userMsg.setInBlackList((boolean) row.get("user_isInBlackList"));
			userMsg.setName((String) row.get("user_name"));
			userMsg.setSumOfOrders((int) (long) row.get("sum"));

			results.add(userMsg);
		}
		System.out.println(results);
		return results;
	}
}
