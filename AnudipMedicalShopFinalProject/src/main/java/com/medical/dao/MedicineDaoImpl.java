package com.medical.dao;

import com.medical.entities.Medicine;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.Query;
import java.util.List;


public class MedicineDaoImpl implements MedicineDao {
    private Session session;

    public MedicineDaoImpl(Session session) {
        this.session = session;
    }

    public void insert(Medicine medicine) {
        Transaction tx = session.beginTransaction();
        session.save(medicine);
        tx.commit();
    }

    public void update(int id, String name, double price) {
        Transaction tx = session.beginTransaction();
        Medicine medicine = session.find(Medicine.class, id);
        if (medicine != null) {
            medicine.setName(name);
            medicine.setPrice(price);
            session.merge(medicine);
            tx.commit();
        }
    }

    public void delete(int id) {
        Transaction tx = session.beginTransaction();
        Medicine medicine = session.find(Medicine.class, id);
        if (medicine != null) {
            session.remove(medicine);
            tx.commit();
        }
    }

    public Medicine search(int id) {
        return session.find(Medicine.class, id);
    }

    @SuppressWarnings("unchecked")
	public List<Medicine> select() {
        Query query = session.createQuery("FROM Medicine");
        return query.getResultList();
    }
}
