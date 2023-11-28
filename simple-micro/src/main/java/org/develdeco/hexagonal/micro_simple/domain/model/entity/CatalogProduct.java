package org.develdeco.hexagonal.micro_simple.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CatalogProduct {

    Long id;

    String sku;

    String name;

    Double price;
}
