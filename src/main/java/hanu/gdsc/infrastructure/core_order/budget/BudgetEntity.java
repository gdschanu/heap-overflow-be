package hanu.gdsc.infrastructure.core_order.budget;

import hanu.gdsc.domain.core_order.models.Budget;
import hanu.gdsc.domain.share.models.DateTime;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Constructor;

@Entity
@Table(name = "core_order_budget")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BudgetEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String coderId;
    private int balance;
    @Column(columnDefinition = "VARCHAR(100)")
    private String createdAt;
    @Column(columnDefinition = "VARCHAR(100)")
    private String updatedAt;
    @Version
    private long version;

    public static Budget toDomain(BudgetEntity budgetEntity) {
        try {
            Constructor<Budget> constructor = Budget.class.getDeclaredConstructor(
                    Long.TYPE,
                    hanu.gdsc.domain.share.models.Id.class,
                    Integer.TYPE,
                    DateTime.class,
                    DateTime.class
            );
            constructor.setAccessible(true);
            return constructor.newInstance(
                    budgetEntity.getVersion(),
                    new hanu.gdsc.domain.share.models.Id(budgetEntity.getCoderId()),
                    budgetEntity.getBalance(),
                    new DateTime(budgetEntity.getCreatedAt()),
                    new DateTime(budgetEntity.getUpdatedAt())
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }

    public static BudgetEntity toEntity(Budget budget) {
        return BudgetEntity.builder()
                .coderId(budget.getCoderId().toString())
                .balance(budget.getBalance())
                .createdAt(budget.getCreatedAt().toString())
                .updatedAt(budget.getUpdatedAt().toString())
                .version(budget.getVersion())
                .build();
    }
}
