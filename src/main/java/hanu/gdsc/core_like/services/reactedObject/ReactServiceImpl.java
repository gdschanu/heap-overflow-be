package hanu.gdsc.core_like.services.reactedObject;

import org.springframework.stereotype.Service;

import hanu.gdsc.core_like.domains.ReactedObject;
import hanu.gdsc.core_like.domains.Reaction;
import hanu.gdsc.core_like.errors.InvalidAction;
import hanu.gdsc.core_like.repositories.reactedObject.ReactedObjectRepository;
import hanu.gdsc.core_like.repositories.reaction.ReactionRepository;

@Service
public class ReactServiceImpl implements ReactService{
    private final ReactedObjectRepository reactedObjectRepository;
    private final ReactionRepository reactionRepository;

    public ReactServiceImpl(final ReactedObjectRepository reactedObjectRepository,
                            final ReactionRepository reactionRepository) {
        this.reactionRepository = reactionRepository;
        this.reactedObjectRepository = reactedObjectRepository;
    }

    @Override
    public boolean react(Input input) {
        ReactedObject reactedObject = reactedObjectRepository.getById(input.reactedObjectId);
        if (reactedObject != null) {
            Reaction reaction = reactionRepository.getByCoderIdAndReactedObjectId(input.coderId, input.reactedObjectId);
            if (reaction != null) {
                return reactWhenExistingReaction(input, reaction, reactedObject);
            } else {
                return reactWhenUnexistingReaction(input, reactedObject);
            }
        }
        return false;
    }

    private boolean reactWhenExistingReaction(Input input, Reaction reaction, ReactedObject reactedObject) {
        if (input.action == reaction.getAction()) {
            throw new InvalidAction("Could not " + input.action.toString() + " this object");
        }
        switch (input.action) {
            case LIKE : 
                if (reaction.getAction().toString().equals("DISLIKE") || reaction.getAction().toString().equals("UNLIKE") || reaction.getAction().toString().equals("UNDISLIKE")) {
                    reaction.setAction(input.action);
                    reactedObject.increaseLikeCount();
                    if(reaction.getAction().toString().equals("DISLIKE")) {
                        reactedObject.decreaseDislikeCount();
                    }
                    reactionRepository.save(reaction);
                    reactedObjectRepository.execute(reactedObject);
                    return true;
                } else {
                    throw new InvalidAction("Could not like this object");
                }

            case DISLIKE : 
                if (reaction.getAction().toString().equals("LIKE") || reaction.getAction().toString().equals("UNDISLIKE") || reaction.getAction().toString().equals("UNLIKE")) {
                    reaction.setAction(input.action);
                    reactedObject.increaseDislikeCount();
                    if (reaction.getAction().toString().equals("LIKE")) {
                        reactedObject.decreaseLikeCount();
                    }
                    reactionRepository.save(reaction);
                    reactedObjectRepository.execute(reactedObject);
                    return true;
                } else {
                    throw new InvalidAction("Could not dislike this object");
                }

            case UNLIKE : 
            if (reaction.getAction().toString().equals("LIKE")) {
                reactionRepository.save(reaction);
                reactedObject.decreaseLikeCount();
                reactionRepository.save(reaction);
                reactedObjectRepository.execute(reactedObject);
                return true;
            } else {
                throw new InvalidAction("Could not unlike this object");
            }

            case UNDISLIKE :
            if (reaction.getAction().toString().equals("DISLIKE")) {
                reaction.setAction(input.action);
                reactedObject.decreaseDislikeCount();
                reactionRepository.save(reaction);
                reactedObjectRepository.execute(reactedObject);
                return true;
            } else {
                throw new InvalidAction("Could not undislike this object");
            }
        }
        return false;
    }

    private boolean reactWhenUnexistingReaction(Input input, ReactedObject reactedObject) {
        switch (input.action) {
            case LIKE : 
                Reaction like = Reaction.create(
                    input.coderId,
                    input.reactedObjectId,
                    input.action
                );
                reactionRepository.save(like);
                reactedObject.increaseLikeCount();
                reactedObjectRepository.execute(reactedObject);
                return true;
            case DISLIKE : 
                Reaction dislike = Reaction.create(
                    input.coderId,
                    input.reactedObjectId,
                    input.action
                );
                reactionRepository.save(dislike);
                reactedObject.increaseDislikeCount();
                reactedObjectRepository.execute(reactedObject);
                return true;
            case UNLIKE : 
                throw new InvalidAction("Could not unlike this object");

            case UNDISLIKE :
                throw new InvalidAction("Could not undislike this object");
        }
        return false;
    }
    
}
