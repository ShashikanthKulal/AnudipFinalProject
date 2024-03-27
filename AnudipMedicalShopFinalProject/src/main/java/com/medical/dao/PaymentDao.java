package com.medical.dao;

import com.medical.entities.Payment;

import java.util.List;

public interface PaymentDao {
    void insert(Payment payment);
    void update(int id, String status, double amount);
    void delete(int id);
    Payment search(int id);
    List<Payment> select();
	void update(Payment payment);
	List<Payment> selectAll();
}
