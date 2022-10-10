package hanu.gdsc.core_order.repositories.budget;

import hanu.gdsc.core_order.domains.Budget;
import hanu.gdsc.share.domains.Id;

public interface BudgetRepository {
    public Budget getByCoderId(Id coderId);

    public void save(Budget budget);
}
