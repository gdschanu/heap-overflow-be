package hanu.gdsc.domain.core_order.services.budget;

import hanu.gdsc.domain.core_order.models.Budget;
import hanu.gdsc.domain.core_order.models.Transaction;

public interface CreateBudgetService {
    void save(Budget budget);
}
