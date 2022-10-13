package hanu.gdsc.core_order.services.order;

import hanu.gdsc.core_order.domains.Budget;
import hanu.gdsc.core_order.domains.Item;
import hanu.gdsc.core_order.domains.Order;
import hanu.gdsc.core_order.exceptions.InvalidStatusException;
import hanu.gdsc.core_order.repositories.budget.BudgetRepository;
import hanu.gdsc.core_order.repositories.item.ItemRepository;
import hanu.gdsc.core_order.repositories.order.OrderRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.InvalidInputException;
import hanu.gdsc.share.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UpdateOrderServiceImpl implements UpdateOrderService {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final BudgetRepository budgetRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void cancel(Id orderId, Id coderId) throws NotFoundException,
            InvalidInputException, InvalidStatusException {
        // validate input
        if (orderId == null) {
            throw new InvalidInputException("orderId must be not null.");
        }
        if (coderId == null) {
            throw new InvalidInputException("coderId must be not null.");
        }
        // logic
        final Order order = orderRepository.getById(orderId);
        if (order == null) {
            throw new NotFoundException("Unknown order.");
        }
        final List<Item> items = itemRepository.getByIds(order.getLineItems().stream()
                .map(line -> line.getItemId())
                .collect(Collectors.toList()));
        final Budget budget = budgetRepository.getByCoderId(coderId);
        order.cancel(items, budget);
        orderRepository.save(order);
        itemRepository.save(items);
        budgetRepository.save(budget);
    }
}
