package com.medical.dao;

import com.medical.entities.Order;

import java.util.List;

public interface OrderDao {
    void insert(Order order);
    void update(int id, String status);
    void delete(int id);
    Order search(int id);
    List<Order> select();
}
