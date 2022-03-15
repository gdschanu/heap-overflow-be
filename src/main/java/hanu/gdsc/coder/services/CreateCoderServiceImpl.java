package hanu.gdsc.coder.services;

import hanu.gdsc.coder.domains.Coder;
import hanu.gdsc.coder.repositories.CoderRepository;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateCoderServiceImpl implements CreateCoderService {
    private CoderRepository coderRepository;

    @Override
    public Id create() {
        Coder coder = Coder.create();
        coderRepository.create(coder);
        return coder.getId();
    }
}
