package hanu.gdsc.core_category.domains;

import hanu.gdsc.share.domains.Id;

public class Item {

    private Id id;
    private Category[] categoryIds;

    private String serviceToCreate;

    public Item(Id id, Category[] categoriesId, String serviceToCreate) {
        this.id = id;
        this.categoryIds = categoriesId;
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

    public Category[] getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(Category[] categoryIds) {
        this.categoryIds = categoryIds;
    }
}
