package hanu.gdsc.domain.core_category.services.item;

import hanu.gdsc.domain.core_category.models.Category;
import hanu.gdsc.domain.core_like.exceptions.InvalidActionException;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import hanu.gdsc.domain.share.models.Id;

import java.util.List;

public interface CreateItemService {

    public void createItem(List<Id> categoryIds, String serviceToCreate) throws NotFoundException, InvalidInputException;

}
