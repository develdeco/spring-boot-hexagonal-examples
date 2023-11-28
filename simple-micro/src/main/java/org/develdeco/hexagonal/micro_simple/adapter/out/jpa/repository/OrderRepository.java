package org.develdeco.hexagonal.micro_simple.adapter.out.jpa.repository;

import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
