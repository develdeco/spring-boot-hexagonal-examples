package org.develdeco.hexagonal.micro_simple.adapter.out.jpa.repository;

import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Long>  {

    void deleteByOrderId(Long orderId);
}
