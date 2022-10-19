package hanu.gdsc.core_category.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.InvalidInputException;

import java.util.List;

public class Item {

    private Id id;
    private List<Id> categoryIds;

    private String serviceToCreate;

    private Item(Id id, List<Id> categoryIds, String serviceToCreate) {
        this.id = id;
        this.categoryIds = categoryIds;
        this.serviceToCreate = serviceToCreate;
    }

    public static Item create(List<Id> categoryIds, String serviceToCreate) throws InvalidInputException {
        if (categoryIds == null) {
            throw new InvalidInputException("categoryIds must be not null");
        }
        if (serviceToCreate == null) {
            throw new InvalidInputException("serviceToCreate must be not null");
        }
        return new Item(Id.generateRandom(), categoryIds, serviceToCreate);
    }

    public String getServiceToCreate() {
        return serviceToCreate;
    }


    public Id getId() {
        return id;
    }

    public List<Id> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Id> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
