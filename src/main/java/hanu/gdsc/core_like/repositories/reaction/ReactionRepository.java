package hanu.gdsc.core_like.repositories.reaction;

import hanu.gdsc.core_like.domains.Reaction;
import hanu.gdsc.share.domains.Id;

public interface ReactionRepository {
    public Reaction getByCoderIdAndReactedObjectId(Id coderId, Id reactedObjectId);

    public void save(Reaction reaction);
}
