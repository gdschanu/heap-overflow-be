package hanu.gdsc.core_like.domains;

import hanu.gdsc.core_like.exceptions.InvalidActionException;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.VersioningDomainObject;
import hanu.gdsc.share.exceptions.InvalidStateException;

public class Reaction extends VersioningDomainObject {
    private Id coderId;
    private Id reactedObjectId;
    private Action action;

    private String serviceToCreate;

    public Reaction(long version, Id coderId, Id reactedObjectId, Action action, String serviceToCreate) {
        super(version);
        this.coderId = coderId;
        this.reactedObjectId = reactedObjectId;
        this.action = action;
        this.serviceToCreate = serviceToCreate;
    }

    public static Reaction create(Id coderId, Id reactedObjectId, Action action, String serviceToCreate) {
        return new Reaction(
                0,
                coderId,
                reactedObjectId,
                action,
                serviceToCreate
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

    public void setAction(Action action, ReactedObject reactedObject) throws InvalidActionException {
        if (action.equals(this.action)) {
            throw new InvalidActionException("Could not " + action + " this object");
        }
        Action oldAction = this.action;
        switch (action) {
            case LIKE:
                if (this.action.equals(Action.DISLIKE) || this.action.equals(Action.UNLIKE) || this.action.equals(Action.UNDISLIKE)) {
                    this.action = action;
                    reactedObject.increaseLikeCount();
                    if (oldAction.equals(Action.DISLIKE))
                        reactedObject.decreaseDislikeCount();
                    return;
                } else {
                    throw new InvalidActionException("Could not like this object");
                }
            case DISLIKE : 
                if (this.action.equals(Action.LIKE) || this.action.equals(Action.UNLIKE) || this.action.equals(Action.UNDISLIKE)) {
                    this.action = action;
                    reactedObject.increaseDislikeCount();
                    if (oldAction.equals(Action.LIKE))
                        reactedObject.decreaseLikeCount();
                    return;
                } else {
                    throw new InvalidActionException("Could not dislike this object");
                }
            case UNLIKE : 
                if (this.action.equals(Action.LIKE)) {
                    this.action = action;
                    reactedObject.decreaseLikeCount();
                    return;
                } else {
                    throw new InvalidActionException("Could not unlike this object");
                }
            case UNDISLIKE :
                if (this.action.equals(Action.DISLIKE)) {
                    this.action = action;
                    reactedObject.decreaseDislikeCount();
                    return;
                } else {
                    throw new InvalidActionException("Could not undislike this object");
                }
            default : 
                 throw new InvalidActionException("Invalid action!");
        }
    }

    public String getServiceToCreate() {
        return serviceToCreate;
    }
}
