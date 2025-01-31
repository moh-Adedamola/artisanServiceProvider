package com.semicolonafrica.data.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment {

//    @ManyToOne
//    @JoinColumn(name = "job_id", referencedColumnName = "id")
//    private Job job;
//
//    @NotNull(message = "Amount is required")
//    private BigDecimal amount;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Job job;

    private double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private LocalDateTime paymentDate;

    private LocalDateTime updatedAt;
}
