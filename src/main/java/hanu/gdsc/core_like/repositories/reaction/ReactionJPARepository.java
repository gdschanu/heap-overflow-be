package hanu.gdsc.core_like.repositories.reaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionJPARepository extends JpaRepository<ReactionEntity, String> {
    public ReactionEntity getByCoderIdAndReactedObjectIdAndServiceToCreate(String coderid,
                                                                           String reactedObjectId,
                                                                           String serviceToCreate);

}
