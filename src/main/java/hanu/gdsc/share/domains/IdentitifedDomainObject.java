package hanu.gdsc.share.domains;

public class IdentitifedDomainObject {
    private Id id;
    private long version;

    public IdentitifedDomainObject(Id id, long version) {
        this.id = id;
        this.version = version;
    }

    public Id getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
