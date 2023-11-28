package org.develdeco.hexagonal.micro_simple.adapter.out.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.mapper.CatalogProductEntityMapper;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.CatalogProduct;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Data
@Entity
@Table(name = "CATALOG_PRODUCT")
public class CatalogProductEntity {

    @Id
    Long id;

    @Column(name = "SKU")
    String sku;

    @Column(name = "NAME")
    String name;

    @Column(name = "PRICE")
    Double price;

    public CatalogProduct toModel() {
        return CatalogProductEntityMapper.buildCatalogProduct(this);
    }
}
