package org.develdeco.hexagonal.micro_simple.application.usecase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.develdeco.hexagonal.micro_simple.application.validation.ValidationContext;
import org.develdeco.hexagonal.micro_simple.application.validation.ValidationMethods;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.Order;
import org.develdeco.hexagonal.micro_simple.domain.model.exception.ApiException;
import org.develdeco.hexagonal.micro_simple.domain.model.type.OrderStatus;
import org.develdeco.hexagonal.micro_simple.domain.port.OrderRepositoryPort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@Slf4j
public class PlaceOrderUseCase {

    private final OrderRepositoryPort orderRepositoryPort;
    private final ValidationMethods validationMethods;

    public Order placeOrder(Order order) {
        log.info("Attempting to place the order");

        // Performing mandatory validations
        makeValidations(order);

        // Preparing the order data
        prepareModel(order);

        // Persisting the new order
        order = persistNewOrder(order);

        log.info("Order placed successfully");
        return order;
    }

    private void makeValidations(Order order) {
        log.info("Performing mandatory validations");
        ValidationContext validationContext = new ValidationContext();
        validationMethods.validateOrderProducts_NotEmpty(order.getProducts());
        validationMethods.validateOrderProducts_NoDuplicates(order.getProducts());
        validationMethods.validateClient_Exists(order.getClient().getId());
        validationMethods.validateUser_Exists(order.getUser().getId());
        validationMethods.validateUser_BelongsToClient(order.getUser().getId(), order.getClient().getId());
        validationMethods.validateCatalogProducts_AllFound(order.getProducts(), validationContext);
        validationContext.mapIntoOrder(order);
    }

    private void prepareModel(Order order) {
        log.info("Preparing the order data to place the order");
        order.setStatus(OrderStatus.NEW);
        order.setCreationDateTime(LocalDateTime.now());
        order.getProducts().forEach(orderProduct -> orderProduct.setCreationDateTime(LocalDateTime.now()));
    }

    private Order persistNewOrder(Order order) {
        log.info("Persisting the new order");
        try {
            return orderRepositoryPort.saveOrder(order);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new ApiException("Persistence failed during order creation");
        }
    }
}
