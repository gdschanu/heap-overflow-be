package hanu.gdsc.domain.core_like.repositories;

import hanu.gdsc.domain.core_like.models.ReactedObject;
import hanu.gdsc.domain.share.models.Id;

public interface ReactedObjectRepository {
    
    public void save(ReactedObject reactedObject);

    public ReactedObject getById(Id id, String serviceToCreate);
}
