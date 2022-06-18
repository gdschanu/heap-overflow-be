package hanu.gdsc.coderAuth_coderAuth.domains;

import hanu.gdsc.coderAuth_coderAuth.errors.ExpiredSession;
import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentifiedDomainObject;

public class Session extends IdentifiedDomainObject{
    private Id coderId;
    private DateTime expireAt; 

    private Session(Id id, Id coderId, DateTime expireAt) {
        super(id);
        this.coderId = coderId;
        this.expireAt = expireAt;
    }

    public static Session createSession(Id id, Id coderId) {
        return new Session(
            id, 
            coderId, 
            DateTime.now().plusMinutes(10080));
    }

    public Id getCoderId() {
        return coderId;
    }

    public DateTime getExpireAt() {
        return expireAt;
    }

    public boolean invalidated() {
        DateTime time = DateTime.now();
        if(!time.isBefore(expireAt)) {
            throw new ExpiredSession();
        }
        return false;
    }
}
