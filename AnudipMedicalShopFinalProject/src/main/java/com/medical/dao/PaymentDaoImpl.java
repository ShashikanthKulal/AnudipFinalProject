package com.medical.dao;

import com.medical.entities.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.Query;
import java.util.List;

public  class PaymentDaoImpl implements PaymentDao {
    private Session session;

    public PaymentDaoImpl(Session session) {
        this.session = session;
    }

    public void insert(Payment payment) {
        Transaction tx = session.beginTransaction();
        session.save(payment);
        tx.commit();
    }

    public void update(int id, String status, double amount) {
        Transaction tx = session.beginTransaction();
        Payment payment = session.find(Payment.class, id);
        if (payment != null) {
            payment.setStatus(status);
            payment.setAmount(amount);
            session.merge(payment);
            tx.commit();
        }
    }

    public void delete(int id) {
        Transaction tx = session.beginTransaction();
        Payment payment = session.find(Payment.class, id);
        if (payment != null) {
            session.remove(payment);
            tx.commit();
        }
    }

    public Payment search(int id) {
        return session.find(Payment.class, id);
    }

    @SuppressWarnings("unchecked")
	public List<Payment> select() {
        Query query = session.createQuery("FROM Payment");
        return query.getResultList();
    }

	@Override
	public void update(Payment payment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Payment> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
