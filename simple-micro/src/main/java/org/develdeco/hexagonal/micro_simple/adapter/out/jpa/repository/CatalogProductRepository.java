package org.develdeco.hexagonal.micro_simple.adapter.out.jpa.repository;

import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.entity.CatalogProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogProductRepository extends JpaRepository<CatalogProductEntity, Long> {

    List<CatalogProductEntity> findAllByProductIdIn(List<Long> productIdList);
}
