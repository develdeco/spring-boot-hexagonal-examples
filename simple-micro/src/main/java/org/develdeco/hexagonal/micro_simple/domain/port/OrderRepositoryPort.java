package org.develdeco.hexagonal.micro_simple.domain.port;

import org.develdeco.hexagonal.micro_simple.domain.model.entity.Order;

import java.util.List;

public interface OrderRepositoryPort {

    Order saveOrder(Order order);

    Order getOrder(Long orderId);

    Order getOrderForUpdate(Long orderId);

    List<Order> getPagedOrders(Integer offset, Integer limit);
}
