package hanu.gdsc.coderAuth.domains;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;

public class Session {
    private Id id;
    private Id coderId;
    private DateTime expiredAt; 
    

    public Session(Id id, Id coderId, DateTime expiredAt) {
        this.id = id;
        this.coderId = coderId;
        this.expiredAt = expiredAt;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Id getCoderId() {
        return coderId;
    }

    public void setCoderId(Id coderId) {
        this.coderId = coderId;
    }

    public DateTime getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(DateTime expiredAt) {
        this.expiredAt = expiredAt;
    }

    
}
