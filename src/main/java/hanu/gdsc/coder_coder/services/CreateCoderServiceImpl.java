package hanu.gdsc.coder_coder.services;

import hanu.gdsc.coder_coder.repositories.CoderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coder_coder.domains.Coder;
import hanu.gdsc.share.domains.Id;

@Service
public class CreateCoderServiceImpl implements CreateCoderService {
    @Autowired
    private CoderRepository coderRepository;

    @Override
    public Id create() {
        Coder coder = Coder.create();
        coderRepository.create(coder);
        return coder.getId();
    }
}
