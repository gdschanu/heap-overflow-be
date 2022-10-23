package hanu.gdsc.domain.core_order.repositories;

import hanu.gdsc.domain.core_order.models.Order;
import hanu.gdsc.domain.share.models.Id;

public interface OrderRepository {
    public void save(Order order);

    public Order getById(Id id);
}
