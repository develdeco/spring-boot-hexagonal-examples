package org.develdeco.hexagonal.micro_simple.adapter.out.jpa.mapper;

import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.entity.CatalogProductEntity;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.CatalogProduct;

public class CatalogProductEntityMapper
{
    public static CatalogProduct buildCatalogProduct(CatalogProductEntity catalogProductEntity) {
        CatalogProduct catalogProduct = new CatalogProduct();
        catalogProduct.setId(catalogProductEntity.getId());
        catalogProduct.setName(catalogProductEntity.getName());
        catalogProduct.setSku(catalogProductEntity.getSku());
        catalogProduct.setPrice(catalogProductEntity.getPrice());
        return catalogProduct;
    }
}
