package hanu.gdsc.core_like.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.VersioningDomainObject;

public class Reaction extends VersioningDomainObject{
    private Id coderId;
    private Id reactedObjectId;
    private Action action;

    private Reaction(long version, Id coderId, Id reactedObjectId, Action action) {
        super(version);
        this.coderId = coderId;
        this.reactedObjectId = reactedObjectId;
        this.action = action;
    }

    public static Reaction create(Id coderId, Id reactedObjectId, Action action) {
        return new Reaction(
            0,
            coderId,
            reactedObjectId,
            action
        );
    }

    public Id getCoderId() {
        return coderId;
    }

    public Id getReactedObjectId() {
        return reactedObjectId;
    }
     
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
