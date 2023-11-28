package org.develdeco.hexagonal.micro_simple.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderProduct {

    Long id;

    CatalogProduct catalogProduct;

    String sku;

    String name;

    Double price;

    Integer quantity;

    Double totalPrice;

    LocalDateTime creationDateTime;

    LocalDateTime lastUpdateDateTime;
}
