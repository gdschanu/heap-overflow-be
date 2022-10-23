package hanu.gdsc.domain.core_like.services.reactedObject;

import hanu.gdsc.domain.core_like.models.ReactedObject;
import hanu.gdsc.domain.core_like.repositories.ReactedObjectRepository;
import hanu.gdsc.domain.share.models.Id;
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
                input.serviceToCreate
        );
        reactedObjectRepository.save(reactedObject);
        return reactedObject.getId();
    }
}
