package hanu.gdsc.domain.share.models;

public class IdentifiedDomainObject {
    private Id id;

    public IdentifiedDomainObject(Id id) {
        this.id = id;
    }

    public Id getId() {
        return id;
    }
}
