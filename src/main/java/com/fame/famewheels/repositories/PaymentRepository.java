package com.fame.famewheels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fame.famewheels.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
