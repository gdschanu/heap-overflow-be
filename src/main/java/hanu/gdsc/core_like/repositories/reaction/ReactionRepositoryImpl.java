package hanu.gdsc.core_like.repositories.reaction;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hanu.gdsc.core_like.domains.Reaction;
import hanu.gdsc.share.domains.Id;

@Repository
public class ReactionRepositoryImpl implements ReactionRepository {
    @Autowired
    private ReactionJPARepository reactionJpaRepository;

    @Override
    public Reaction getByCoderIdAndReactedObjectId(Id coderId, Id reactionObjectId) {
        try {
            ReactionEntity reactionEntiy = reactionJpaRepository.getByCoderIdAndReactedObjectId(coderId.toString(), reactionObjectId.toString());
            if(reactionEntiy != null) {
                return ReactionEntity.toDomain(reactionEntiy);
            }
            return null;
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public synchronized void save(Reaction reaction) {
        reactionJpaRepository.save(ReactionEntity.toEntity(reaction));
        reaction.increaseVersion();
    }
    
}
