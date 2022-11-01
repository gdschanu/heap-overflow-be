package hanu.gdsc.domain.core_category.services.item;

import hanu.gdsc.domain.core_order.repositories.ItemRepository;
import hanu.gdsc.domain.share.models.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteItemServiceImpl implements DeleteItemService {

    private ItemRepository itemRepository;

    @Override
    public void deleteById(Id id, String serviceToCreate) {
        itemRepository.deleteById(id, serviceToCreate);
    }

    @Override
    public void deleteMany(List<Id> categoryIds) {
        itemRepository.deleteMany(categoryIds);
    }
}
