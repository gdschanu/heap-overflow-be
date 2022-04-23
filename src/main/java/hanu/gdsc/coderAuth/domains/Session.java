package hanu.gdsc.coderAuth.domains;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;

public class Session {
    private Id id;
    private Id coderId;
    private DateTime expireAt; 
    

    public Session(Id id, Id coderId, DateTime expireAt) {
        this.id = id;
        this.coderId = coderId;
        this.expireAt = expireAt;
    }

    public Id getId() {
        return id;
    }

    public Id getCoderId() {
        return coderId;
    }

    public DateTime getExpireAt() {
        return expireAt;
    }
}
