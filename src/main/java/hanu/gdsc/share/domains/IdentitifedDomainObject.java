package hanu.gdsc.share.domains;

import javax.persistence.Id;

public class IdentitifedDomainObject {
    private ID id;
    private long version;

    public IdentitifedDomainObject(ID id, long version) {
        this.id = id;
        this.version = version;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
