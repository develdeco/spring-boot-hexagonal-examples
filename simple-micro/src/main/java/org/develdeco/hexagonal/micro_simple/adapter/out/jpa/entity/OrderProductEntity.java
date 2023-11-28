package org.develdeco.hexagonal.micro_simple.adapter.out.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.OrderProduct;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "ORDER_PRODUCT")
public class OrderProductEntity {

    @Id
    Long id;

    @Column(name = "ORDER_ID")
    Long orderId;

    @Column(name = "PRODUCT_ID")
    Long productId;

    @Column(name = "SKU")
    String sku;

    @Column(name = "NAME")
    String name;

    @Column(name = "PRICE")
    Double price;

    @Column(name = "QUANTITY")
    Integer quantity;

    @Column(name = "TOTAL_PRICE")
    Double totalPrice;

    @Column(name = "CREATION_DATE")
    LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "LAST_UPDATE_DATE")
    LocalDateTime lastUpdateDate;
}
