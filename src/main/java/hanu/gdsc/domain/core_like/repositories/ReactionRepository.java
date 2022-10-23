package hanu.gdsc.domain.core_like.repositories;

import hanu.gdsc.domain.core_like.models.Reaction;
import hanu.gdsc.domain.share.models.Id;

public interface ReactionRepository {
    public Reaction getByCoderIdAndReactedObjectId(Id coderId, Id reactedObjectId, String serviceToCreate);

    public void save(Reaction reaction);
}
