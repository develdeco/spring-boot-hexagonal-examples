package org.develdeco.hexagonal.micro_simple.adapter.in.rest.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.develdeco.hexagonal.micro_simple.domain.model.converter.LocalDateConverter;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.Client;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.Order;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.User;
import org.develdeco.hexagonal.micro_simple.domain.model.type.DeliveryType;
import org.develdeco.hexagonal.micro_simple.domain.model.type.OrderStatus;
import org.develdeco.hexagonal.micro_simple.domain.model.type.PaymentStatus;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class InputOrderDTO {

    Long orderId;

    @NotNull(message = "Client ID is required")
    Long clientId;

    @NotNull(message = "User ID is required")
    Long userId;

    @JsonDeserialize(using = OrderStatus.JsonDeserializer.class)
    OrderStatus status;

    @JsonDeserialize(using = PaymentStatus.JsonDeserializer.class)
    PaymentStatus paymentStatus;

    @JsonDeserialize(using = DeliveryType.JsonDeserializer.class)
    DeliveryType deliveryType;

    @JsonDeserialize(using = LocalDateConverter.JsonDeserializer.class)
    LocalDate deliveryDate;

    @NotNull(message = "Products are required")
    List<OrderProductDTO> products;

    public Order toModel() {
        Order order = new Order();
        order.setClient(Client.builder().id(clientId).build());
        order.setUser(User.builder().id(userId).build());
        order.setStatus(status);
        order.setPaymentStatus(paymentStatus);
        order.setDeliveryType(deliveryType);
        order.setDeliveryDate(deliveryDate);
        order.setProducts(products.stream().map(OrderProductDTO::toModel).collect(Collectors.toList()));
        return order;
    }

    public Order toModel(Long orderId) {
        Order order = toModel();
        order.setId(orderId);
        return order;
    }
}
