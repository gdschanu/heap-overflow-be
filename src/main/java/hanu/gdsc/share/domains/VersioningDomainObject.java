package hanu.gdsc.share.domains;

public class VersioningDomainObject {
    private long version;

    public VersioningDomainObject(long version) {
        this.version = version;
    }

    public long getVersion() {
        return version;
    }
}
