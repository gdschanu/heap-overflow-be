package hanu.gdsc.coder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coder.domains.Coder;
import hanu.gdsc.coder.repositories.CoderRepository;
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
