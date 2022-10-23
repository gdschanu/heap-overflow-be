package hanu.gdsc.domain.core_order.services.order;

import hanu.gdsc.domain.core_order.exceptions.InsufficientBudgetException;
import hanu.gdsc.domain.core_order.exceptions.InsufficientQuantityException;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.NotFoundException;

import java.util.List;

public interface CreateOrderService {
    public static class OrderLineItemInput {
        public Id itemId;
        public int quantity;
    }

    public Id create(Id coderId, List<OrderLineItemInput> lineItemsToOrder) throws
            InvalidInputException, InsufficientQuantityException, InsufficientBudgetException, NotFoundException;
}
