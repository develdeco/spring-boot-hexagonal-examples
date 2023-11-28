package org.develdeco.hexagonal.micro_simple.application.usecase;

import lombok.AllArgsConstructor;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.Order;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.QueryParameters;
import org.develdeco.hexagonal.micro_simple.domain.model.exception.ApiException;
import org.develdeco.hexagonal.micro_simple.domain.port.OrderRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GetOrdersUseCase {

    private static final Integer PAGE_SIZE = 25;

    private final OrderRepositoryPort orderRepositoryPort;

    public List<Order> getPagedOrders(QueryParameters queryParameters) {
        return orderRepositoryPort.getPagedOrders(getOffset(queryParameters.getPage(), PAGE_SIZE), PAGE_SIZE);
    }

    private static Integer getOffset(Integer page, Integer pageSize) {
        return page != null && page >= 0 ? page * pageSize : 0;
    }

    public Order getOrder(Long orderId) {
        Order order = orderRepositoryPort.getOrder(orderId);

        if (order == null) {
            throw new ApiException("Order not found");
        }

        return order;
    }
}
