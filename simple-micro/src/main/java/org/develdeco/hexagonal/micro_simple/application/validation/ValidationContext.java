package org.develdeco.hexagonal.micro_simple.application.validation;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.CatalogProduct;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.Order;
import org.develdeco.hexagonal.micro_simple.domain.model.entity.OrderProduct;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ValidationContext {

    List<CatalogProduct> catalogProducts;

    public void mapIntoOrder(Order order) {
        Map<Long, CatalogProduct> catalogProductsMap = this.getCatalogProducts().stream()
                .collect(Collectors.toMap(CatalogProduct::getId, product -> product));

        for (OrderProduct orderProduct : order.getProducts()) {
            if (!catalogProductsMap.containsKey(orderProduct.getCatalogProduct().getId())) continue;
            CatalogProduct catalogProduct = catalogProductsMap.get(orderProduct.getCatalogProduct().getId());
            orderProduct.setCatalogProduct(catalogProduct);
        }
    }
}
