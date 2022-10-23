package hanu.gdsc.domain.core_order.models;

import hanu.gdsc.domain.core_order.exceptions.InsufficientBudgetException;
import hanu.gdsc.domain.core_order.exceptions.InsufficientQuantityException;
import hanu.gdsc.domain.core_order.exceptions.InvalidStatusException;
import hanu.gdsc.domain.share.models.DateTime;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.models.IdentitifedVersioningDomainObject;
import hanu.gdsc.domain.share.models.Pair;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Order extends IdentitifedVersioningDomainObject {
    private Id coderId;
    private List<OrderLineItem> lineItems;
    private Status status;
    private DateTime createdAt;

    private Order(Id id,
                  long version,
                  Id coderId,
                  List<OrderLineItem> lineItems,
                  Status status,
                  DateTime createdAt) {
        super(id, version);
        this.coderId = coderId;
        this.lineItems = lineItems;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static Pair<Order, Transaction> create(Id coderId,
                                                  List<Item> items,
                                                  Map<Item, Integer> itemQuantityToOrder,
                                                  Budget budget) throws
            InvalidInputException, InsufficientQuantityException, InsufficientBudgetException {
        // validate input
        if (coderId == null) {
            throw new InvalidInputException("coderId must not be null.");
        }
        if (items == null) {
            throw new InvalidInputException("items must not be null.");
        }
        if (itemQuantityToOrder == null) {
            throw new InvalidInputException("itemQuantityToOrder must not be null.");
        }
        if (budget == null) {
            throw new InvalidInputException("budget must not be null.");
        }
        // logic
        final Order order = new Order(
                Id.generateRandom(),
                0,
                coderId,
                takeOrderLineItemsFromItems(items, itemQuantityToOrder),
                Status.PENDING,
                DateTime.now()
        );
        final Transaction transaction = budget.decreaseBy(order);
        return new Pair<>(order, transaction);
    }

    private static List<OrderLineItem> takeOrderLineItemsFromItems(List<Item> items,
                                                                   Map<Item, Integer> itemQuantityToOrder) throws
            InvalidInputException, InsufficientQuantityException {
        List<OrderLineItem> result = new ArrayList<>();
        for (Item item : items) {
            if (itemQuantityToOrder.get(item) == null) {
                throw new InvalidInputException("No quantity for item " + item.getId() + ".");
            }
            result.add(OrderLineItem.takeFromItem(
                    item,
                    itemQuantityToOrder.get(item)
            ));
        }
        return result;
    }

    public int totalPrice() {
        int result = 0;
        for (OrderLineItem lineItem : lineItems) {
            result += lineItem.getPrice();
        }
        return result;
    }

    public OrderLineItem getOrderLineItemByItemId(Id itemId) throws InvalidInputException {
        // validate input
        if (itemId == null) {
            throw new InvalidInputException("itemId must be not null.");
        }
        // logic
        for (OrderLineItem orderLineItem : lineItems) {
            if (orderLineItem.getItemId().equals(itemId)) {
                return orderLineItem;
            }
        }
        return null;
    }

    public Transaction cancel(List<Item> items, Budget budget) throws InvalidInputException,
            InvalidStatusException {
        // validate input
        if (items == null) {
            throw new InvalidInputException("items must not be null.");
        }
        if (budget == null) {
            throw new InvalidInputException("budget must not be null.");
        }
        // logic
        if (status != Status.PENDING) {
            throw new InvalidStatusException("");
        }
        status = Status.CANCELED;
        for (Item item : items) {
            item.increaseQuantity(getOrderLineItemByItemId(item.getId()).getQuantity());
        }
        return budget.increase(totalPrice(), "Increase budget for cancel order " + getId() + ".");
    }

    public Id getCoderId() {
        return coderId;
    }

    public List<OrderLineItem> getLineItems() {
        return Collections.unmodifiableList(lineItems);
    }

    public Status getStatus() {
        return status;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }
}
