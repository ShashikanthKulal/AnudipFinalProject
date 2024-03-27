package com.medical.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @Column(name = "status")
    private String status;

    // Constructors, getters, and setters
    // Constructors
    public Order(LocalDateTime localDateTime, Customer customer) {
    }

    public Order(String status) {
        this.status = status;
    }

    // Getters and setters for all fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString() method
    @Override
    public String toString() {
        return "Order [id=" + id + ", status=" + status + "]";
    }

	public Customer getCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getOrderDateTime() {
		// TODO Auto-generated method stub
		return 0;
	}
}
