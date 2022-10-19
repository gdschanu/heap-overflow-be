package hanu.gdsc.core_category.services;

import hanu.gdsc.core_category.domains.Category;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.InvalidInputException;
import org.springframework.stereotype.Service;



@Service
public interface CategoryService {

    Id create(String name, String serviceToCreate) throws InvalidInputException;

    void delete(Id id, String serviceToCreate) throws NoSuchFieldException;

}
