package org.develdeco.hexagonal.micro_simple.adapter.in.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.CatalogProduct;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.OrderProduct;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductDTO {

    @NotNull(message = "Product ID is required")
    Long productId;

    @NotNull(message = "Quantity is required")
    Integer quantity;

    Double price;

    Double totalPrice;

    public static OrderProductDTO fromModel(OrderProduct orderProduct) {
        return OrderProductDTO.builder()
                .productId(orderProduct.getCatalogProduct().getId())
                .quantity(orderProduct.getQuantity())
                .price(orderProduct.getPrice())
                .totalPrice(orderProduct.getTotalPrice())
                .build();
    }

    public OrderProduct toModel() {
        return OrderProduct.builder()
                .catalogProduct(CatalogProduct.builder().id(productId).build())
                .quantity(quantity)
                .price(price)
                .totalPrice(totalPrice)
                .build();
    }
}
