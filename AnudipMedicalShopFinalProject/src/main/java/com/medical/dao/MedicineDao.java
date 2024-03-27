package com.medical.dao;

import com.medical.entities.Medicine;

import java.util.List;

public interface MedicineDao {
    void insert(Medicine medicine);
    void update(int id, String name, double price);
    void delete(int id);
    Medicine search(int id);
    List<Medicine> select();
}
