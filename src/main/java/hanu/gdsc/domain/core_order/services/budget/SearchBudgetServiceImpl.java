package hanu.gdsc.domain.core_order.services.budget;


import hanu.gdsc.domain.core_order.models.Budget;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.core_order.repositories.BudgetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchBudgetServiceImpl implements SearchBudgetService{
    private final BudgetRepository budgetRepository;

    @Override
    public Output findByCoderId(Id coderId) {
        Budget budget = budgetRepository.getByCoderId(coderId);
        return Output.builder()
                .coderId(budget.getCoderId())
                .balance(budget.getBalance())
                .updatedAt(budget.getUpdatedAt())
                .build();
    }
}
