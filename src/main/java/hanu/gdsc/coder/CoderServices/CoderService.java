package hanu.gdsc.coder.CoderServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coder.CoderRepositories.CoderRepository;
import hanu.gdsc.coder.Domains.Coder;
import hanu.gdsc.share.domains.Id;

@Service
public class CoderService {
    @Autowired
    private CoderRepository repository;

    public void createCoder() {
        
    }

    public void updateCoder() {
    }

    public void deleteCoder(Id id) {
        Coder coder = repository.getById(id);
        repository.delete(coder);
    }

    public void saveCoder(Id id) {
        Coder coder = repository.getById(id);
        repository.save(coder);
    }

    public List<Coder> searchById(Id id){
        return repository.searchById(id);
    }
    public Coder getById(Id id) {
        return repository.getById(id);
    }
}
