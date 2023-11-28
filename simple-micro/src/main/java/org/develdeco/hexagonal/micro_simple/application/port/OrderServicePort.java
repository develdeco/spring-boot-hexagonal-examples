package org.develdeco.hexagonal.micro_simple.application.port;

import lombok.AllArgsConstructor;
import org.develdeco.hexagonal.micro_simple.application.usecase.GetOrdersUseCase;
import org.develdeco.hexagonal.micro_simple.application.usecase.ModifyOrderUseCase;
import org.develdeco.hexagonal.micro_simple.application.usecase.PlaceOrderUseCase;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.Order;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.QueryParameters;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderServicePort {

    private final GetOrdersUseCase getOrdersUseCase;
    private final PlaceOrderUseCase placeOrderUseCase;
    private final ModifyOrderUseCase modifyOrderUseCase;

    public List<Order> filterOrders(QueryParameters queryParameters) {
        return getOrdersUseCase.getPagedOrders(queryParameters);
    }

    public Order getOrder(Long orderId) {
        return getOrdersUseCase.getOrder(orderId);
    }

    public Order placeOrder(Order order) {
        return placeOrderUseCase.placeOrder(order);
    }

    public Order modifyOrder(Order inputOrder) {
        return modifyOrderUseCase.modifyOrder(inputOrder);
    }
}
