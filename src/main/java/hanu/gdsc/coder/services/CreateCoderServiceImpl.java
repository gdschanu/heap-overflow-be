package hanu.gdsc.coder.services;

import hanu.gdsc.coder.domains.Coder;
import hanu.gdsc.coder.repositories.CoderRepository;
import hanu.gdsc.share.domains.Id;
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
