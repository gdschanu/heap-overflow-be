package hanu.gdsc.domain.core_order.repositories;

import hanu.gdsc.domain.core_order.models.Transaction;

public interface TransactionRepository {
    public void save(Transaction transaction);
}
