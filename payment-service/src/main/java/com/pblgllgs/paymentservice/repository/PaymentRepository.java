package com.pblgllgs.paymentservice.repository;
/*
 *
 * @author pblgl
 * Created on 16-04-2024
 *
 */

import com.pblgllgs.paymentservice.entity.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentDetails, Long> {
    Optional<PaymentDetails> findByOrderId(long orderId);
}
