package org.develdeco.hexagonal.micro_simple.domain.port;

import org.develdeco.hexagonal.micro_simple.domain.model.entity.CatalogProduct;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.OrderProduct;

import java.util.List;

public interface CatalogProductRepositoryPort
{
    List<CatalogProduct> getCatalogProducts(List<OrderProduct> orderProducts);
}
