package org.develdeco.hexagonal.micro_simple.adapter.out.jpa.mapper;

import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.entity.OrderProductEntity;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.CatalogProduct;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.Order;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.OrderProduct;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OrderProductEntityMapper {

    public static List<OrderProductEntity> buildEntityList(Order order) {
        if (order.getProducts() == null) return Collections.emptyList();
        return order.getProducts().stream()
                .map(OrderProductEntityMapper::buildEntity)
                .collect(Collectors.toList());
    }

    public static List<OrderProduct> buildOrderProductList(List<OrderProductEntity> orderProductEntityList) {
        if (orderProductEntityList == null) return Collections.emptyList();
        return orderProductEntityList.stream()
                .map(OrderProductEntityMapper::buildOrderProduct)
                .collect(Collectors.toList());
    }

    public static OrderProductEntity buildEntity(OrderProduct orderProduct) {
        OrderProductEntity orderProductEntity = new OrderProductEntity();
        setId(orderProductEntity, orderProduct.getId());
        orderProductEntity.setProductId(orderProduct.getCatalogProduct().getId());
        orderProductEntity.setSku(orderProduct.getSku());
        orderProductEntity.setName(orderProduct.getName());
        orderProductEntity.setPrice(orderProduct.getPrice());
        orderProductEntity.setQuantity(orderProduct.getQuantity());
        orderProductEntity.setTotalPrice(orderProduct.getTotalPrice());
        setCreationDate(orderProductEntity, orderProduct.getCreationDateTime());
        setLastUpdateDate(orderProductEntity, orderProduct.getLastUpdateDateTime());
        return orderProductEntity;
    }

    public static OrderProduct buildOrderProduct(OrderProductEntity orderProductEntity) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setId(orderProductEntity.getId());
        orderProduct.setCatalogProduct(buildCatalogProduct(orderProductEntity));
        orderProduct.setSku(orderProductEntity.getSku());
        orderProduct.setName(orderProductEntity.getName());
        orderProduct.setPrice(orderProductEntity.getPrice());
        orderProduct.setQuantity(orderProductEntity.getQuantity());
        orderProduct.setTotalPrice(orderProductEntity.getTotalPrice());
        orderProduct.setCreationDateTime(orderProductEntity.getCreationDate());
        orderProduct.setLastUpdateDateTime(orderProductEntity.getLastUpdateDate());
        return orderProduct;
    }

    private static void setId(OrderProductEntity orderProductEntity, Long orderProductId) {
        if (orderProductId == null) return;
        orderProductEntity.setId(orderProductId);
    }

    private static void setCreationDate(OrderProductEntity orderProductEntity, LocalDateTime creationDateTime) {
        if (creationDateTime == null) return;
        orderProductEntity.setCreationDate(creationDateTime);
    }

    private static void setLastUpdateDate(OrderProductEntity orderProductEntity, LocalDateTime lastUpdateDateTime) {
        if (lastUpdateDateTime == null) return;
        orderProductEntity.setLastUpdateDate(lastUpdateDateTime);
    }

    public static CatalogProduct buildCatalogProduct(OrderProductEntity orderProductEntity) {
        CatalogProduct catalogProduct = new CatalogProduct();
        catalogProduct.setId(orderProductEntity.getProductId());
        return catalogProduct;
    }
}
