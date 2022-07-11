package hanu.gdsc.core_like.services.reactedObject;

import hanu.gdsc.core_like.domains.ReactedObject;
import hanu.gdsc.core_like.domains.Reaction;
import hanu.gdsc.core_like.errors.InvalidAction;
import hanu.gdsc.core_like.repositories.reactedObject.ReactedObjectRepository;
import hanu.gdsc.core_like.repositories.reaction.ReactionRepository;
import hanu.gdsc.share.error.NotFoundError;
import org.springframework.stereotype.Service;

@Service
public class ReactServiceImpl implements ReactService {
    private final ReactedObjectRepository reactedObjectRepository;
    private final ReactionRepository reactionRepository;

    public ReactServiceImpl(final ReactedObjectRepository reactedObjectRepository,
                            final ReactionRepository reactionRepository) {
        this.reactionRepository = reactionRepository;
        this.reactedObjectRepository = reactedObjectRepository;
    }

    @Override
    public void react(Input input) {
        ReactedObject reactedObject = reactedObjectRepository.getById(input.reactedObjectId, input.serviceToCreate);
        if (reactedObject == null) {
            throw new NotFoundError("Unknown reacted object");
        }
        Reaction reaction = reactionRepository.getByCoderIdAndReactedObjectId(
                input.coderId,
                input.reactedObjectId,
                input.serviceToCreate
        );
        if (reaction != null) {
            reactWhenExistingReaction(input, reaction, reactedObject);
        } else {
            reactWhenUnexistingReaction(input, reactedObject);
        }
    }

    private void reactWhenExistingReaction(Input input, Reaction reaction, ReactedObject reactedObject) {
        reaction.setAction(input.action, reactedObject);
        reactionRepository.save(reaction);
        reactedObjectRepository.save(reactedObject);
    }

    private void reactWhenUnexistingReaction(Input input, ReactedObject reactedObject) {
        switch (input.action) {
            case LIKE:
                Reaction like = Reaction.create(
                        input.coderId,
                        input.reactedObjectId,
                        input.action,
                        input.serviceToCreate
                );
                reactionRepository.save(like);
                reactedObject.increaseLikeCount();
                reactedObjectRepository.save(reactedObject);
                return;
            case DISLIKE:
                Reaction dislike = Reaction.create(
                        input.coderId,
                        input.reactedObjectId,
                        input.action,
                        input.serviceToCreate
                );
                reactionRepository.save(dislike);
                reactedObject.increaseDislikeCount();
                reactedObjectRepository.save(reactedObject);
                return;
            case UNLIKE:
                throw new InvalidAction("Could not unlike this object");
            case UNDISLIKE:
                throw new InvalidAction("Could not undislike this object");
        }
    }
    
}
