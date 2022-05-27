package hanu.gdsc.share.domains;

public class IdentitifedVersioningDomainObject extends IdentifiedDomainObject {
    private long version;

    public IdentitifedVersioningDomainObject(Id id, long version) {
        super(id);
        this.version = version;
    }

    public long getVersion() {
        return version;
    }

    public void increaseVersion() {
        version++;
    }
}
