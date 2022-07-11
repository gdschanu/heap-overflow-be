package hanu.gdsc.core_like.services.reactedObject;

import org.springframework.stereotype.Service;

import hanu.gdsc.core_like.domains.Action;
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
        Action oldAction = reaction.getAction();
        switch (input.action) {
            case LIKE : 
                if(oldAction.equals(Action.DISLIKE)) {
                    reactedObject.decreaseLikeCount();
                }
                reaction.setAction(input.action);
                reactedObject.increaseLikeCount();
                reactionRepository.save(reaction);
                reactedObjectRepository.execute(reactedObject);
                return true;
            case DISLIKE : 
                if(oldAction.equals(Action.LIKE)) {
                    reactedObject.decreaseLikeCount();
                }
                reaction.setAction(input.action);
                reactedObject.increaseDislikeCount();
                reactionRepository.save(reaction);
                reactedObjectRepository.execute(reactedObject);
                return true;
            case UNLIKE : 
                reaction.setAction(input.action);
                reactedObject.decreaseLikeCount();
                reactionRepository.save(reaction);
                reactedObjectRepository.execute(reactedObject);
                return true;
            case UNDISLIKE :
                reaction.setAction(input.action); 
                reactedObject.decreaseDislikeCount();
                reactionRepository.save(reaction);
                reactedObjectRepository.execute(reactedObject);
                return true;
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
