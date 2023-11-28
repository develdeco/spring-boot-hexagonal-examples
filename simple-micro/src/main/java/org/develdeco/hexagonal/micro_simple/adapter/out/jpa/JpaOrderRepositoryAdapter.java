package org.develdeco.hexagonal.micro_simple.adapter.out.jpa;

import lombok.AllArgsConstructor;
import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.entity.OrderEntity;
import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.mapper.OrderProductEntityMapper;
import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.repository.OrderRepository;
import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.repository.transaction.OrderTransactionContext;
import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.repository.transaction.OrderTransactionRepository;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.Order;
import org.develdeco.hexagonal.micro_simple.domain.port.OrderRepositoryPort;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class JpaOrderRepositoryAdapter implements OrderRepositoryPort {

    private final OrderRepository orderRepository;
    private final OrderTransactionRepository orderTransactionRepository;

    @Override
    public Order saveOrder(Order order) {
        OrderTransactionContext transactionContext = OrderTransactionContext.fromOrder(order);
        orderTransactionRepository.saveOrder(transactionContext);
        transactionContext.mapChangesIntoOrder(order);
        return order;
    }

    @Override
    public Order getOrder(Long orderId) {
        Optional<OrderEntity> orderResult = orderRepository.findById(orderId);
        return orderResult.map(this::buildOrder).orElse(null);
    }

    @Override
    public Order getOrderForUpdate(Long orderId) {
        Optional<OrderEntity> orderResult = orderRepository.findById(orderId);
        if (orderResult.isEmpty()) return null;

        OrderEntity orderEntity = orderResult.get();
        Order order = orderEntity.toModel();
        order.setProducts(OrderProductEntityMapper.buildOrderProductList(orderEntity.getProducts()));
        return order;
    }

    @Override
    public List<Order> getPagedOrders(Integer page, Integer limit) {
        return orderRepository.findAll(PageRequest.of(page, limit)).get()
                .map(this::buildOrder)
                .collect(Collectors.toList());
    }

    private Order buildOrder(OrderEntity orderEntity) {
        Order order = orderEntity.toModel();
        order.setClient(orderEntity.getClient().toModel());
        order.setUser(orderEntity.getUser().toModel());
        order.setProducts(OrderProductEntityMapper.buildOrderProductList(orderEntity.getProducts()));
        return order;
    }
}
