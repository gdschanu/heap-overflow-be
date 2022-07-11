package hanu.gdsc.core_like.services.reactedObject;

import hanu.gdsc.core_like.domains.ReactedObject;
import hanu.gdsc.core_like.repositories.reactedObject.ReactedObjectRepository;
import hanu.gdsc.share.domains.Id;
import org.springframework.stereotype.Service;

@Service
public class CreateReactedObjectServiceImpl implements CreateReactedObjectService{
    private final ReactedObjectRepository reactedObjectRepository;

    public CreateReactedObjectServiceImpl(final ReactedObjectRepository reactedObjectRepository) {
        this.reactedObjectRepository = reactedObjectRepository;
    }

    @Override
    public Id create(Input input) {
        ReactedObject reactedObject = ReactedObject.create(
                input.getServiceToCreate()
        );
        reactedObjectRepository.save(reactedObject);
        return reactedObject.getId();
    }
}
