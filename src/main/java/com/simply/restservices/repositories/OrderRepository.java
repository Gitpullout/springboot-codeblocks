package com.simply.restservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simply.restservices.entities.Order;
import com.simply.restservices.entities.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
