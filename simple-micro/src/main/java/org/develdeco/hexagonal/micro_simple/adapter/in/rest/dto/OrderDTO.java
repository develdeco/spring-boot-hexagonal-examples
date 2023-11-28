package org.develdeco.hexagonal.micro_simple.adapter.in.rest.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.develdeco.hexagonal.micro_simple.domain.model.converter.LocalDateConverter;
import org.develdeco.hexagonal.micro_simple.domain.model.converter.LocalDateTimeConverter;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.Order;
import org.develdeco.hexagonal.micro_simple.domain.model.type.DeliveryType;
import org.develdeco.hexagonal.micro_simple.domain.model.type.OrderStatus;
import org.develdeco.hexagonal.micro_simple.domain.model.type.PaymentStatus;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Slf4j
public class OrderDTO {

    Long orderId;

    ClientDTO client;

    UserDTO user;

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

    Double totalPrice;

    @JsonSerialize(using = LocalDateTimeConverter.JsonSerializer.class)
    LocalDateTime creationDate;

    @JsonSerialize(using = LocalDateTimeConverter.JsonSerializer.class)
    LocalDateTime lastUpdateDate;

    public static OrderDTO fromModel(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.mapFromModel(order);
        return orderDTO;
    }

    public void mapFromModel(Order order) {
        orderId = order.getId();
        client = ClientDTO.fromModel(order.getClient());
        user = UserDTO.fromModel(order.getUser());
        status = order.getStatus();
        paymentStatus = order.getPaymentStatus();
        deliveryType = order.getDeliveryType();
        deliveryDate = order.getDeliveryDate();
        products = order.getProducts().stream().map(OrderProductDTO::fromModel).collect(Collectors.toList());
        totalPrice = order.getTotal();
        creationDate = order.getCreationDateTime();
        lastUpdateDate = order.getLastUpdateDateTime();
    }
}
