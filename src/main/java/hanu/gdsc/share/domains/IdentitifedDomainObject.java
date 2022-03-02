package hanu.gdsc.share.domains;

import java.util.UUID;

public class IdentitifedDomainObject {
    private UUID id;
    private long version;

    public IdentitifedDomainObject(UUID id, long version) {
        this.id = id;
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
