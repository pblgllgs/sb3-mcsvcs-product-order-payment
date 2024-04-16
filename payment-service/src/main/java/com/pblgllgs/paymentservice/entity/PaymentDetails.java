package com.pblgllgs.paymentservice.entity;
/*
 *
 * @author pblgl
 * Created on 16-04-2024
 *
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "t_payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "payment_date")
    private Instant paymentDate;
    @Column(name = "payment_mode")
    private String paymentMode;
    @Column(name = "payment_status")
    private String paymentStatus;
    private Double amount;
    @Column(name = "order_id")
    private Long orderId;
}
