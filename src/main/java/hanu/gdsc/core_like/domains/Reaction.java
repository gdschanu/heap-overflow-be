package hanu.gdsc.core_like.domains;

import hanu.gdsc.core_like.errors.InvalidAction;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.VersioningDomainObject;

public class Reaction extends VersioningDomainObject{
    private Id coderId;
    private Id reactedObjectId;
    private Action action;
    // private Action oldAction;

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

    public Action setAction(Action action) {
        if (action == this.action) {
            throw new InvalidAction("Could not " + action + " this object");
        }
        switch (action) {
            case LIKE : 
                if (this.action.equals(Action.DISLIKE) || this.action.equals(Action.UNLIKE) || this.action.equals(Action.UNDISLIKE)) {
                    this.action = action;
                    return this.action;
                } else {
                    throw new InvalidAction("Could not like this object");
                }
            case DISLIKE : 
                if (this.action.equals(Action.LIKE) || this.action.equals(Action.UNLIKE) || this.action.equals(Action.UNDISLIKE)) {
                    this.action = action;
                    return this.action;
                } else {
                    throw new InvalidAction("Could not dislike this object");
                }
            case UNLIKE : 
                if (this.action.equals(Action.LIKE)) {
                    this.action = action;
                    return this.action;
                } else {
                    throw new InvalidAction("Could not unlike this object");
                }
            case UNDISLIKE :
                if (this.action.equals(Action.DISLIKE)) {
                    this.action = action;
                    return this.action;
                } else {
                    throw new InvalidAction("Could not undislike this object");
                }
            default : 
                 throw new InvalidAction("Invalid action!");
        }

    }
}
