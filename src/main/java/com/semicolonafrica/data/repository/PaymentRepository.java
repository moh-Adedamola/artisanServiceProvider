package com.semicolonafrica.data.repository;

import com.semicolonafrica.data.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
