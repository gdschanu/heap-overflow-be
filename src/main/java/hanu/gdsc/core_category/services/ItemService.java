package hanu.gdsc.core_category.services;

import hanu.gdsc.core_category.domains.Category;
import hanu.gdsc.core_category.domains.Item;
import hanu.gdsc.share.domains.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {

    Item create(Category[] categoryIds);

    Item delete(Id id);

}

