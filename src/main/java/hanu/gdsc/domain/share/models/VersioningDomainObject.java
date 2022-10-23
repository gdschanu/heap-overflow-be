package hanu.gdsc.domain.share.models;

public class VersioningDomainObject {
    private long version;

    public VersioningDomainObject(long version) {
        this.version = version;
    }

    public long getVersion() {
        return version;
    }

    public void increaseVersion() {
        version++;
    }
}
