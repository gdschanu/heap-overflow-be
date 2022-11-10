package hanu.gdsc.infrastructure.core_order.budget;

import hanu.gdsc.domain.core_order.models.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetJPARepository extends JpaRepository<BudgetEntity, String> {
    BudgetEntity findByCoderId(String coderId);
}
