package hanu.gdsc.core_like.repositories.reactedObject;

import hanu.gdsc.core_like.domains.ReactedObject;
import hanu.gdsc.share.domains.Id;

public interface ReactedObjectRepository {
    
    public void save(ReactedObject reactedObject);

    public ReactedObject getById(Id id, String serviceToCreate);
}
