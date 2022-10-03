package hanu.gdsc.core_category.domains;

import hanu.gdsc.share.domains.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.UniqueConstraint;

public class Category {

    @javax.persistence.Id
    private Id id;
    private String name;

    private String serviceToCreate;

    public Category() {

    }

    public Category(Id id, String name, String serviceToCreate) {
        this.id = id;
        this.name = name;
        this.serviceToCreate = serviceToCreate;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getServiceToCreate() {
        return serviceToCreate;
    }

    public void setServiceToCreate(String serviceToCreate) {
        this.serviceToCreate = serviceToCreate;
    }

    public void setName(String name) {
        this.name = name;
    }
}
