package hanu.gdsc.core_order.repositories.transaction;

import hanu.gdsc.core_order.domains.Transaction;

public interface TransactionRepository {
    public void save(Transaction transaction);
}
