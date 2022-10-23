package hanu.gdsc.domain.coder.services;

import hanu.gdsc.domain.coder.models.Coder;
import hanu.gdsc.domain.coder.repositories.CoderRepository;
import hanu.gdsc.domain.share.models.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCoderServiceImpl implements CreateCoderService {
    @Autowired
    private CoderRepository coderRepository;

    @Override
    public Id create(Input input) {
        Coder coder = Coder.create(
                input.name,
                input.age,
                input.avatar,
                input.phone,
                input.university,
                input.slogan,
                input.gender,
                input.address
        );
        coderRepository.save(coder);
        return coder.getId();
    }
}
