package hanu.gdsc.core_like.domains;

import hanu.gdsc.core_like.errors.InvalidAction;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.VersioningDomainObject;

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

    public void setAction(Action action, ReactedObject reactedObject) {
        if (action.equals(this.action)) {
            throw new InvalidAction("Could not " + action + " this object");
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
                    throw new InvalidAction("Could not like this object");
                }
            case DISLIKE : 
                if (this.action.equals(Action.LIKE) || this.action.equals(Action.UNLIKE) || this.action.equals(Action.UNDISLIKE)) {
                    this.action = action;
                    reactedObject.increaseDislikeCount();
                    if (oldAction.equals(Action.LIKE))
                        reactedObject.decreaseLikeCount();
                    return;
                } else {
                    throw new InvalidAction("Could not dislike this object");
                }
            case UNLIKE : 
                if (this.action.equals(Action.LIKE)) {
                    this.action = action;
                    reactedObject.decreaseLikeCount();
                    return;
                } else {
                    throw new InvalidAction("Could not unlike this object");
                }
            case UNDISLIKE :
                if (this.action.equals(Action.DISLIKE)) {
                    this.action = action;
                    reactedObject.decreaseDislikeCount();
                    return;
                } else {
                    throw new InvalidAction("Could not undislike this object");
                }
            default : 
                 throw new InvalidAction("Invalid action!");
        }
    }

    public String getServiceToCreate() {
        return serviceToCreate;
    }
}
