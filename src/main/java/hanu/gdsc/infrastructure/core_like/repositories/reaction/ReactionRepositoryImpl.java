package hanu.gdsc.infrastructure.core_like.repositories.reaction;

import hanu.gdsc.domain.core_like.models.Reaction;
import hanu.gdsc.domain.core_like.repositories.ReactionRepository;
import hanu.gdsc.domain.share.models.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@Repository
public class ReactionRepositoryImpl implements ReactionRepository {
    @Autowired
    private ReactionJPARepository reactionJpaRepository;

    @Override
    public Reaction getByCoderIdAndReactedObjectId(Id coderId, Id reactionObjectId, String serviceToCreate) {
        try {
            ReactionEntity reactionEntiy = reactionJpaRepository.getByCoderIdAndReactedObjectIdAndServiceToCreate(
                    coderId.toString(),
                    reactionObjectId.toString(),
                    serviceToCreate
            );
            if (reactionEntiy != null) {
                return ReactionEntity.toDomain(reactionEntiy);
            }
            return null;
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Reaction reaction) {
        reactionJpaRepository.save(ReactionEntity.toEntity(reaction));
    }
    
}
