package com.medical.dao;

import com.medical.entities.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.Query;
import java.util.List;


public class OrderDaoImpl implements OrderDao {
    private Session session;

    public OrderDaoImpl(Session session) {
        this.session = session;
    }

    public void insert(Order order) {
        Transaction tx = session.beginTransaction();
        session.save(order);
        tx.commit();
    }

    public void update(int id, String status) {
        Transaction tx = session.beginTransaction();
        Order order = session.find(Order.class, id);
        if (order != null) {
            order.setStatus(status);
            session.merge(order);
            tx.commit();
        }
    }

    public void delete(int id) {
        Transaction tx = session.beginTransaction();
        Order order = session.find(Order.class, id);
        if (order != null) {
            session.remove(order);
            tx.commit();
        }
    }

    public Order search(int id) {
        return session.find(Order.class, id);
    }

    @SuppressWarnings("unchecked")
	public List<Order> select() {
        Query query = session.createQuery("FROM Order");
        return query.getResultList();
    }
}
