package hanu.gdsc.domain.core_order.services.budget;

import hanu.gdsc.domain.core_order.models.Budget;
import hanu.gdsc.domain.core_order.repositories.BudgetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateBudgetServiceImpl implements CreateBudgetService{
    private final BudgetRepository budgetRepository;

    @Override
    public void save(Budget budget) {
        budgetRepository.save(budget);
    }
}
