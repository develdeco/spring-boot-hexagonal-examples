package org.develdeco.hexagonal.micro_simple.adapter.out.jpa.repository.transaction;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.entity.OrderProductEntity;
import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.repository.OrderProductRepository;
import org.develdeco.hexagonal.micro_simple.adapter.out.jpa.repository.OrderRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class OrderTransactionRepositoryImpl implements OrderTransactionRepository
{
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    @Override
    @Transactional
    public void saveOrder(OrderTransactionContext transactionContext) {
        saveOrderEntity(transactionContext);
        saveOrderProducts(transactionContext);
    }

    private void saveOrderEntity(OrderTransactionContext transactionContext) {
        try {
            log.info("Persisting the order summary");
            transactionContext.setOrder(orderRepository.save(transactionContext.getOrder()));
        } catch (Exception ex) {
            log.error("Persistence failed while saving the order summary");
            throw ex;
        }
    }

    private void saveOrderProducts(OrderTransactionContext transactionContext) {
        List<OrderProductEntity> orderProducts = transactionContext.getOrderProducts();
        if (orderProducts == null || orderProducts.size() == 0) return;
        try {
            log.info("Persisting the order products");
            orderProductRepository.deleteByOrderId(transactionContext.getOrder().getId());
            orderProducts.forEach(product -> product.setOrderId(transactionContext.getOrder().getId()));
            transactionContext.setOrderProducts(orderProductRepository.saveAll(orderProducts));
        } catch (Exception ex) {
            log.error("Persistence failed while saving the order products");
            throw ex;
        }
    }
}
