package hanu.gdsc.core_order.services.order;

import hanu.gdsc.core_order.domains.Budget;
import hanu.gdsc.core_order.domains.Item;
import hanu.gdsc.core_order.domains.Order;
import hanu.gdsc.core_order.domains.Transaction;
import hanu.gdsc.core_order.exceptions.InsufficientBudgetException;
import hanu.gdsc.core_order.exceptions.InsufficientQuantityException;
import hanu.gdsc.core_order.repositories.budget.BudgetRepository;
import hanu.gdsc.core_order.repositories.item.ItemRepository;
import hanu.gdsc.core_order.repositories.order.OrderRepository;
import hanu.gdsc.core_order.repositories.transaction.TransactionRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.Pair;
import hanu.gdsc.share.exceptions.InvalidInputException;
import hanu.gdsc.share.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CreateOrderServiceImpl implements CreateOrderService {
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final BudgetRepository budgetRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Id create(Id coderId, List<OrderLineItemInput> lineItemsToOrder) throws
            InvalidInputException, InsufficientQuantityException, InsufficientBudgetException, NotFoundException {
        // validate input
        if (coderId == null) {
            throw new InvalidInputException("coderId must not be null.");
        }
        if (lineItemsToOrder == null) {
            throw new InvalidInputException("lineItemsToOrder must not be null.");
        }
        for (OrderLineItemInput orderLineItemInput : lineItemsToOrder) {
            if (orderLineItemInput == null) {
                throw new InvalidInputException("orderLineItemInputs must not contains null element.");
            }
            if (orderLineItemInput.itemId == null) {
                throw new InvalidInputException("orderLineItemInputs.itemId must not be null.");
            }
            if (orderLineItemInput.quantity <= 0) {
                throw new InvalidInputException("orderLineItemInputs.quantity must be greater than 0.");
            }
        }
        // logic
        final List<Item> items = itemRepository.getByIds(lineItemsToOrder.stream()
                .map(item -> item.itemId)
                .collect(Collectors.toList()));
        if (items.size() != lineItemsToOrder.size()) {
            throw new NotFoundException("Some items not found, please try again.");
        }
        final Budget budget = budgetRepository.getByCoderId(coderId);
        if (budget == null) {
            throw new InsufficientBudgetException("Coder doesn't have any budget.");
        }
        final Map<Item, Integer> itemQuantityToOrder = new HashMap<>();
        lineItemsToOrder.forEach(lineItemToOrder -> {
            for (Item item : items) {
                if (lineItemToOrder.itemId.equals(item.getId())) {
                    itemQuantityToOrder.put(item, lineItemToOrder.quantity);
                }
            }
        });
        final Pair<Order, Transaction> orderTransactionPair = Order.create(
                coderId,
                items,
                itemQuantityToOrder,
                budget
        );
        itemRepository.save(items);
        orderRepository.save(orderTransactionPair.getFirst());
        budgetRepository.save(budget);
        transactionRepository.save(orderTransactionPair.getSecond());
        return orderTransactionPair.getFirst().getId();
    }
}
