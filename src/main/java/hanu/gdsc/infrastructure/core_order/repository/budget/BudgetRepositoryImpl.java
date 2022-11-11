package hanu.gdsc.infrastructure.core_order.repository.budget;

import hanu.gdsc.domain.core_order.models.Budget;
import hanu.gdsc.domain.core_order.repositories.BudgetRepository;
import hanu.gdsc.domain.share.models.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@Repository
public class BudgetRepositoryImpl implements BudgetRepository {
    @Autowired
    private BudgetJPARepository budgetJPARepository;

    @Override
    public Budget getByCoderId(Id coderId) {
        try {
            return BudgetEntity.toDomain(budgetJPARepository.findByCoderId(coderId.toString()));
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }

    @Override
    public void save(Budget budget) {
        budgetJPARepository.save(BudgetEntity.toEntity(budget));
    }
}
