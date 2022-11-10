package hanu.gdsc.domain.core_order.services.budget;

import hanu.gdsc.domain.core_order.models.Budget;
import hanu.gdsc.domain.share.models.DateTime;
import hanu.gdsc.domain.share.models.Id;
import lombok.Builder;
import lombok.Data;
public interface SearchBudgetService {

    @Builder
    @Data
    public class Output {
        public Id coderId;
        public int balance;
        public DateTime updatedAt;
    }
    Output findByCoderId(Id coderId);
}
