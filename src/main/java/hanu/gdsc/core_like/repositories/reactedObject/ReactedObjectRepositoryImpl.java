package hanu.gdsc.core_like.repositories.reactedObject;

import hanu.gdsc.core_like.domains.ReactedObject;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@Repository
public class ReactedObjectRepositoryImpl implements ReactedObjectRepository{
    @Autowired
    private ReactedObjectJPARepository reactedObjectJpaRepository;

    @Override
    public void save(ReactedObject reactedObject) {
        reactedObjectJpaRepository.save(ReactedObjectEntity.toEntity(reactedObject));
    }

    @Override
    public ReactedObject getById(Id id, String serviceToCreate) {
        try {
            ReactedObjectEntity reactedObjectEntity = reactedObjectJpaRepository.findByIdAndServiceToCreate(
                    id.toString(),
                    serviceToCreate
            );
            return ReactedObjectEntity.toDomain(reactedObjectEntity);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
