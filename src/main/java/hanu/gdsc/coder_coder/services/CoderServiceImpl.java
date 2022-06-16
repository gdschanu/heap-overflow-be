package hanu.gdsc.coder_coder.services;

import hanu.gdsc.coder_coder.domains.Coder;
import hanu.gdsc.coder_coder.repositories.CoderRepository;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoderServiceImpl {
    @Autowired
    private CoderRepository repository;

    public void createCoder() {

    }

    public void updateCoder() {
    }

    public void deleteCoder(Id id) {
//        Coder coder = repository.getById(id);
//        repository.delete(coder);
    }

    public void saveCoder(Id id) {
//        Coder coder = repository.getById(id);
//        repository.save(coder);
    }

    public List<Coder> searchById(Id id) {
//        return repository.searchById(id);
        return null;
    }

    public Coder getById(Id id) {
//        return repository.getById(id);
        return null;
    }
}
