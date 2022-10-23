package hanu.gdsc.domain.core_order.repositories;

import hanu.gdsc.domain.core_order.models.Budget;
import hanu.gdsc.domain.share.models.Id;

public interface BudgetRepository {
    public Budget getByCoderId(Id coderId);

    public void save(Budget budget);
}
