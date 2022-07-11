package hanu.gdsc.core_like.repositories.reactedObject;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hanu.gdsc.core_like.domains.ReactedObject;
import hanu.gdsc.share.domains.Id;

@Repository
public class ReactedObjectRepositoryImpl implements ReactedObjectRepository{
    @Autowired
    private ReactedObjectJPARepository reactedObjectJpaRepository;

    @Override
    @Transactional
    public synchronized void execute(ReactedObject reactedObject) {
        reactedObjectJpaRepository.save(ReactedObjectEntity.toEntity(reactedObject)); 
        reactedObject.increaseVersion();
    }

    @Override
    public ReactedObject getById(Id id) {
        try {
            ReactedObjectEntity reactedObjectEntity = reactedObjectJpaRepository.getById(id.toString());
            return ReactedObjectEntity.toDomain(reactedObjectEntity);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
