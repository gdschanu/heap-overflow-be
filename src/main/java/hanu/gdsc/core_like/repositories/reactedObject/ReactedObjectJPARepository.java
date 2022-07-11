package hanu.gdsc.core_like.repositories.reactedObject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactedObjectJPARepository extends JpaRepository<ReactedObjectEntity, String> {
    public ReactedObjectEntity findByIdAndServiceToCreate(String id, String serviceToCreate);
}
