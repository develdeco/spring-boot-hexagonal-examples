package org.develdeco.hexagonal.micro_simple.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.develdeco.hexagonal.micro_simple.domain.model.type.DeliveryType;
import org.develdeco.hexagonal.micro_simple.domain.model.type.OrderStatus;
import org.develdeco.hexagonal.micro_simple.domain.model.type.PaymentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Order {

    Long id;

    Client client;

    User user;

    OrderStatus status;

    PaymentStatus paymentStatus;

    DeliveryType deliveryType;

    LocalDate deliveryDate;

    List<OrderProduct> products;

    LocalDateTime creationDateTime;

    LocalDateTime lastUpdateDateTime;

    public Double getTotal() {
        double orderTotal = 0.0;
        for (OrderProduct orderProduct : products) {
            orderTotal += orderProduct.getTotalPrice();
        }
        return orderTotal;
    }
}
