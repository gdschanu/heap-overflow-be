package hanu.gdsc.core_category.domains;

import hanu.gdsc.share.domains.Id;

import java.util.List;

public class Item {

    private Id id;
    private Category[] categoriesId;

    private String serviceToCreate;

    public Item(Id id, Category[] categoriesId, String serviceToCreate) {
        this.id = id;
        this.categoriesId = categoriesId;
        this.serviceToCreate = serviceToCreate;
    }

    public String getServiceToCreate() {
        return serviceToCreate;
    }

    public void setServiceToCreate(String serviceToCreate) {
        this.serviceToCreate = serviceToCreate;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Category[] getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(Category[] categoriesId) {
        this.categoriesId = categoriesId;
    }
}
