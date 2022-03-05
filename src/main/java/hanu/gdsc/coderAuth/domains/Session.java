package hanu.gdsc.coderAuth.domains;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;

public class Session {
    private Id id;
    private Id coderId;
    private DateTime expiredAt; 
    
    public Session() {
    }

    public Session(Id id, Id coderId, DateTime expiredAt) {
        this.id = id;
        this.coderId = coderId;
        this.expiredAt = expiredAt;
    }

    /**
     * @return Id return the id
     */
    public Id getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Id id) {
        this.id = id;
    }

    /**
     * @return Id return the coderId
     */
    public Id getCoderId() {
        return coderId;
    }

    /**
     * @param coderId the coderId to set
     */
    public void setCoderId(Id coderId) {
        this.coderId = coderId;
    }

    /**
     * @return DateTime return the expiredAt
     */
    public DateTime getExpiredAt() {
        return expiredAt;
    }

    /**
     * @param expiredAt the expiredAt to set
     */
    public void setExpiredAt(DateTime expiredAt) {
        this.expiredAt = expiredAt;
    }

}
