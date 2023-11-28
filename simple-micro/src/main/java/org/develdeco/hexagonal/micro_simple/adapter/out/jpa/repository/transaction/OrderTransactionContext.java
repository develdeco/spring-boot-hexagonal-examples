package org.develdeco.hexagonal.micro_simple.adapter.out.jpa.repository.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.entity.OrderEntity;
import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.entity.OrderProductEntity;
import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.mapper.OrderEntityMapper;
import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.mapper.OrderProductEntityMapper;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.Order;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
@Slf4j
public class OrderTransactionContext
{
    private OrderEntity order;

    private List<OrderProductEntity> orderProducts;

    public static OrderTransactionContext fromOrder(Order orderModel) {
        OrderEntity order = OrderEntityMapper.buildEntity(orderModel);
        List<OrderProductEntity> orderProducts = OrderProductEntityMapper.buildEntityList(orderModel);

        return OrderTransactionContext.builder()
                .order(order)
                .orderProducts(orderProducts)
                .build();
    }

    public void mapChangesIntoOrder(Order orderModel) {
        orderModel.setId(order.getId());
    }
}
