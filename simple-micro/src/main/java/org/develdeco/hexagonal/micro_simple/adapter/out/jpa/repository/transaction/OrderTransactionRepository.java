package org.develdeco.hexagonal.micro_simple.adapter.out.jpa.repository.transaction;

public interface OrderTransactionRepository
{
    void saveOrder(OrderTransactionContext transactionContext);
}
