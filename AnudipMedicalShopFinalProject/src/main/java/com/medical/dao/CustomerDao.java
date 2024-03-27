package com.medical.dao;

import com.medical.entities.Customer;

import java.util.List;

public interface CustomerDao {
    void insert(Customer customer);
    void update(int id, String name, String email, String password, String phone);
    void delete(int id);
    Customer search(int id);
    List<Customer> select();
    Customer getCustomerByEmailAndPassword(String email, String password);
	void update(int id, String name, String phone);
}
