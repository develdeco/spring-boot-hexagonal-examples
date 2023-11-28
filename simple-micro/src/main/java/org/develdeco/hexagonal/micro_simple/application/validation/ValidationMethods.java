package org.develdeco.hexagonal.micro_simple.application.validation;

import lombok.AllArgsConstructor;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.CatalogProduct;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.Order;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.OrderProduct;
import org.develdeco.hexagonal.micro_simple.domain.model.exception.ApiException;
import org.develdeco.hexagonal.micro_simple.domain.port.CatalogProductRepositoryPort;
import org.develdeco.hexagonal.micro_simple.domain.port.ClientRepositoryPort;
import org.develdeco.hexagonal.micro_simple.domain.port.OrderRepositoryPort;
import org.develdeco.hexagonal.micro_simple.domain.port.UserRepositoryPort;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ValidationMethods {

    private final OrderRepositoryPort orderRepositoryPort;
    private final ClientRepositoryPort clientRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final CatalogProductRepositoryPort catalogProductRepositoryPort;

    public void validateOrder_Exists(Long orderId) {
        Order order = orderRepositoryPort.getOrder(orderId);

        if (order == null) {
            throw new ApiException("Order not found", HttpStatus.NOT_FOUND);
        }
    }

    public void validateOrderProducts_NotEmpty(List<OrderProduct> orderProducts) {
        if (orderProducts.size() > 0) return;
        throw new ApiException("There are no products in the order", HttpStatus.BAD_REQUEST);
    }

    public void validateOrderProducts_NoDuplicates(List<OrderProduct> orderProducts) {
        Set<Long> catalogProductsIds = orderProducts.stream()
                .map(orderProduct -> orderProduct.getCatalogProduct().getId())
                .collect(Collectors.toSet());

        if (orderProducts.size() > catalogProductsIds.size()) {
            throw new ApiException("There are repeated product IDs in the order", HttpStatus.BAD_REQUEST);
        }
    }

    public void validateClient_Exists(Long clientId) {
        boolean doesClientExists = clientRepositoryPort.doesClientExists(clientId);

        if (!doesClientExists) {
            throw new ApiException("Client not found", HttpStatus.PRECONDITION_FAILED);
        }
    }

    public void validateUser_Exists(Long userId) {
        boolean doesUserExists = userRepositoryPort.doesUserExists(userId);

        if (!doesUserExists) {
            throw new ApiException("User not found", HttpStatus.PRECONDITION_FAILED);
        }
    }

    public void validateUser_BelongsToClient(Long userId, Long clientId) {
        boolean doesUserBelongsToClient = userRepositoryPort.doesUserBelongsToClient(userId, clientId);

        if (!doesUserBelongsToClient) {
            throw new ApiException("The user does not belong to the client", HttpStatus.PRECONDITION_FAILED);
        }
    }

    public void validateCatalogProducts_AllFound(List<OrderProduct> orderProducts, ValidationContext validationContext) {
        List<CatalogProduct> catalogProducts = catalogProductRepositoryPort.getCatalogProducts(orderProducts);

        Set<Long> catalogProductIds = catalogProducts.stream().map(CatalogProduct::getId).collect(Collectors.toSet());

        if (catalogProducts.size() < orderProducts.size()) {
            List<String> notFoundCatalogProductIds = orderProducts.stream()
                    .filter(orderProduct -> !catalogProductIds.contains(orderProduct.getCatalogProduct().getId()))
                    .map(orderProduct -> orderProduct.getCatalogProduct().getId().toString())
                    .collect(Collectors.toList());
            String formattedIds = String.join(",", notFoundCatalogProductIds);
            String errorMessageTemplate = "%s. (IDs: %s)";
            String errorMessage = String.format(errorMessageTemplate, "There are products in the order not present in the catalog", formattedIds);
            throw new ApiException(errorMessage, HttpStatus.PRECONDITION_FAILED);
        }

        validationContext.setCatalogProducts(catalogProducts);
    }
}
