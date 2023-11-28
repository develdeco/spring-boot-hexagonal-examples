package org.develdeco.hexagonal.micro_simple.adapter.out.jpa;

import lombok.AllArgsConstructor;
import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.entity.CatalogProductEntity;
import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.repository.CatalogProductRepository;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.CatalogProduct;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.OrderProduct;
import org.develdeco.hexagonal.micro_simple.domain.port.CatalogProductRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JpaCatalogProductRepositoryAdapter implements CatalogProductRepositoryPort
{
    private final CatalogProductRepository catalogProductRepository;

    public List<CatalogProduct> getCatalogProducts(List<OrderProduct> orderProducts) {
        List<Long> catalogProductsIds = orderProducts.stream()
                .map(orderProduct -> orderProduct.getCatalogProduct().getId())
                .collect(Collectors.toList());
        List<CatalogProductEntity> catalogProducts = catalogProductRepository.findAllByProductIdIn(catalogProductsIds);
        return catalogProducts.stream().map(CatalogProductEntity::toModel).collect(Collectors.toList());
    }
}
