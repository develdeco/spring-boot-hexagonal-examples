package org.develdeco.hexagonal.micro_simple.adapter.out.jpa.mapper;

import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.entity.OrderEntity;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.Client;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.Order;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.User;
import org.develdeco.hexagonal.micro_simple.domain.model.type.DeliveryType;
import org.develdeco.hexagonal.micro_simple.domain.model.type.OrderStatus;
import org.develdeco.hexagonal.micro_simple.domain.model.type.PaymentStatus;

import java.time.LocalDateTime;

public class OrderEntityMapper {

    public static OrderEntity buildEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        setOrderId(orderEntity, order.getId());
        orderEntity.setClientId(order.getClient().getId());
        orderEntity.setUserId(order.getUser().getId());
        orderEntity.setStatus(order.getStatus().getValue());
        orderEntity.setPaymentStatus(order.getPaymentStatus().getValue());
        orderEntity.setDeliveryType(order.getDeliveryType().getValue());
        orderEntity.setDeliveryDate(order.getDeliveryDate());
        orderEntity.setTotal(order.getTotal());
        setCreationDate(orderEntity, order.getCreationDateTime());
        setLastUpdateDate(orderEntity, order.getLastUpdateDateTime());
        return orderEntity;
    }

    public static Order buildOrder(OrderEntity orderEntity) {
        Order order = new Order();
        order.setId(orderEntity.getId());
        order.setClient(buildClient(orderEntity));
        order.setUser(buildUser(orderEntity));
        order.setStatus(OrderStatus.ofValue(orderEntity.getStatus()));
        order.setPaymentStatus(PaymentStatus.ofValue(orderEntity.getPaymentStatus()));
        order.setDeliveryType(DeliveryType.ofValue(orderEntity.getDeliveryType()));
        order.setDeliveryDate(orderEntity.getDeliveryDate());
        order.setCreationDateTime(orderEntity.getCreationDate());
        order.setLastUpdateDateTime(orderEntity.getLastUpdateDate());
        return order;
    }

    private static void setOrderId(OrderEntity orderEntity, Long orderId) {
        if (orderId == null) return;
        orderEntity.setId(orderId);
    }

    private static void setCreationDate(OrderEntity orderEntity, LocalDateTime creationDateTime) {
        if (creationDateTime == null) return;
        orderEntity.setCreationDate(creationDateTime);
    }

    private static void setLastUpdateDate(OrderEntity orderEntity, LocalDateTime lastUpdateDateTime) {
        if (lastUpdateDateTime == null) return;
        orderEntity.setLastUpdateDate(lastUpdateDateTime);
    }

    private static Client buildClient(OrderEntity orderEntity) {
        Client client = new Client();
        client.setId(orderEntity.getClientId());
        return client;
    }

    private static User buildUser(OrderEntity orderEntity) {
        User user = new User();
        user.setId(orderEntity.getUserId());
        return user;
    }
}
