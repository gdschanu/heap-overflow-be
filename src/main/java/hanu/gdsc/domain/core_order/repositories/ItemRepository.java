package hanu.gdsc.domain.core_order.repositories;

import hanu.gdsc.domain.core_order.models.Item;
import hanu.gdsc.domain.share.models.Id;

import java.util.List;

public interface ItemRepository {
    public List<Item> getByIds(List<Id> ids);

    public void save(List<Item> items);

    public void deleteById(Id id, String serviceToCreate);

    public void deleteMany(List<Id> categoryIds);
}
