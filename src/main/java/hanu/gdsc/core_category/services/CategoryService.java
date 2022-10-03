package hanu.gdsc.core_category.services;

import hanu.gdsc.core_category.domains.Category;
import hanu.gdsc.share.domains.Id;
import org.springframework.stereotype.Service;



@Service
public interface CategoryService {

    Category create(String name);

    boolean delete(Id id, String name);

}
