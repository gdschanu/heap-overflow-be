package hanu.gdsc.domain.core_order.services.order;

import hanu.gdsc.domain.core_order.models.Budget;
import hanu.gdsc.domain.core_order.models.Item;
import hanu.gdsc.domain.core_order.models.Order;
import hanu.gdsc.domain.core_order.exceptions.InvalidStatusException;
import hanu.gdsc.domain.core_order.repositories.BudgetRepository;
import hanu.gdsc.domain.core_order.repositories.ItemRepository;
import hanu.gdsc.domain.core_order.repositories.OrderRepository;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
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
