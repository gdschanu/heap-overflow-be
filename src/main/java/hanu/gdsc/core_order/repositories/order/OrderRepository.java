package hanu.gdsc.core_order.repositories.order;

import hanu.gdsc.core_order.domains.Order;
import hanu.gdsc.share.domains.Id;

public interface OrderRepository {
    public void save(Order order);

    public Order getById(Id id);
}
