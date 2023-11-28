package org.develdeco.hexagonal.micro_simple.application.usecase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.develdeco.hexagonal.micro_simple.application.validation.ValidationContext;
import org.develdeco.hexagonal.micro_simple.application.validation.ValidationMethods;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.Order;
import org.develdeco.hexagonal.micro_simple.domain.model.exception.ApiException;
import org.develdeco.hexagonal.micro_simple.domain.port.OrderRepositoryPort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class ModifyOrderUseCase {

    private final OrderRepositoryPort orderRepositoryPort;
    private final ValidationMethods validationMethods;

    public Order modifyOrder(Order inputOrder) {
        log.info("Attempting to modify the order");

        // Performing mandatory validations
        makeValidations(inputOrder);

        // Getting current order data
        Order currentOrder = getCurrentOrder(inputOrder.getId());

        // Preparing the order data
        prepareModel(currentOrder, inputOrder);

        // Persisting the order changes
        Order resultOrder = persistOrderChanges(currentOrder);

        log.info("Order modified successfully");
        return resultOrder;
    }

    private void makeValidations(Order order) {
        log.info("Performing mandatory validations");
        ValidationContext validationContext = new ValidationContext();
        validationMethods.validateOrder_Exists(order.getId());
        validationMethods.validateOrderProducts_NotEmpty(order.getProducts());
        validationMethods.validateOrderProducts_NoDuplicates(order.getProducts());
        validationMethods.validateClient_Exists(order.getClient().getId());
        validationMethods.validateUser_Exists(order.getUser().getId());
        validationMethods.validateUser_BelongsToClient(order.getUser().getId(), order.getClient().getId());
        validationMethods.validateCatalogProducts_AllFound(order.getProducts(), validationContext);
        validationContext.mapIntoOrder(order);
    }

    private void prepareModel(Order currentOrder, Order inputOrder) {
        log.info("Preparing the order data to modify the order");
        currentOrder.setStatus(inputOrder.getStatus());
        currentOrder.setPaymentStatus(inputOrder.getPaymentStatus());
        currentOrder.setDeliveryType(inputOrder.getDeliveryType());
        currentOrder.setDeliveryDate(inputOrder.getDeliveryDate());
        currentOrder.setLastUpdateDateTime(LocalDateTime.now());
        currentOrder.setProducts(inputOrder.getProducts());
    }

    public Order persistOrderChanges(Order order) {
        log.info("Persisting the order changes");
        try {
            return orderRepositoryPort.saveOrder(order);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new ApiException("Persistence failed during order update");
        }
    }

    public Order getCurrentOrder(Long orderId) {
        log.info("Getting current order data");
        return Optional.ofNullable(orderRepositoryPort.getOrderForUpdate(orderId))
                .orElseThrow(() -> new ApiException("Order not found", HttpStatus.NOT_FOUND));
    }
}
