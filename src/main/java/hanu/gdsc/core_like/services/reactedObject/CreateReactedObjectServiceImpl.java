package hanu.gdsc.core_like.services.reactedObject;

import org.springframework.stereotype.Service;

import hanu.gdsc.core_like.domains.ReactedObject;
import hanu.gdsc.core_like.repositories.reactedObject.ReactedObjectRepository;

@Service
public class CreateReactedObjectServiceImpl implements CreateReactedObjectService{
    private final ReactedObjectRepository reactedObjectRepository;

    public CreateReactedObjectServiceImpl(final ReactedObjectRepository reactedObjectRepository) {
        this.reactedObjectRepository = reactedObjectRepository;
    }

    @Override
    public void create(Input input) {
        ReactedObject reactedObject = ReactedObject.create(
            input.objectId,
            input.serviceToCreate
        );
        reactedObjectRepository.execute(reactedObject);
    }
}
