package com.pblgllgs.orderservice.repository;
/*
 *
 * @author pblgl
 * Created on 15-04-2024
 *
 */

import com.pblgllgs.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
