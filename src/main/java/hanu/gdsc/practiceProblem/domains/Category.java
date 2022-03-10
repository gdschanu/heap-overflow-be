package hanu.gdsc.practiceProblem.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedDomainObject;

public class Category extends IdentitifedDomainObject {
    private String name;

    private Category(Id id, long version, String name) {
        super(id, version);
        this.name = name;
    }

    public static Category create(String name) {
        return new Category(
            Id.generateRandom(),
            0,
            name
        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
