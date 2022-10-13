package hanu.gdsc.core_order.repositories.item;

import hanu.gdsc.core_order.domains.Item;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface ItemRepository {
    public List<Item> getByIds(List<Id> ids);

    public void save(List<Item> items);
}
