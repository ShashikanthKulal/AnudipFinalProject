package com.medical.dao;

import com.medical.entities.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    private  Session session;

    public CustomerDaoImpl(Session session) {
        this.session = session;
    }

    public void insert(Customer customer) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(customer);
            tx.commit();
            System.out.println("Customer added successfully.");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public void update(int id, String name, String email, String password, String address, String phone) {
        Transaction tx = null;
        try {
            Customer customer = session.get(Customer.class, id);
            if (customer != null) {
                tx = session.beginTransaction();
                customer.setName(name);
                customer.setEmail(email);
                customer.setPassword(password);
                customer.setAddress(address);
                customer.setPhone(phone);
                session.update(customer);
                tx.commit();
                System.out.println("Customer updated successfully.");
            } else {
                System.out.println("Customer not found with ID: " + id);
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        Transaction tx = null;
        try {
            Customer customer = session.get(Customer.class, id);
            if (customer != null) {
                tx = session.beginTransaction();
                session.delete(customer);
                tx.commit();
                System.out.println("Customer deleted successfully.");
            } else {
                System.out.println("Customer not found with ID: " + id);
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public Customer search(int id) {
        try {
            return session.get(Customer.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
	public List<Customer> select() {
        try {
            Query query = session.createQuery("FROM Customer", Customer.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(int id, String name, String email, String password, String phone) {
        Transaction tx = null;
        try {
            Customer customer = session.get(Customer.class, id);
            if (customer != null) {
                tx = session.beginTransaction();
                customer.setName(name);
                customer.setEmail(email);
                customer.setPassword(password);
                customer.setPhone(phone);
                session.update(customer);
                tx.commit();
                System.out.println("Customer updated successfully.");
            } else {
                System.out.println("Customer not found with ID: " + id);
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public Customer getCustomerByEmailAndPassword(String email, String password) {
        try {
            Query query = session.createQuery("FROM Customer WHERE email = :email AND password = :password", Customer.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return (Customer) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	@Override
	public void update(int id, String name, String phone) {
		// TODO Auto-generated method stub
		
	}
}
