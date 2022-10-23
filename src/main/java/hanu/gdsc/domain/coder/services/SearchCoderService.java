package hanu.gdsc.domain.coder.services;

import hanu.gdsc.domain.coder.models.Coder;
import hanu.gdsc.domain.coder.repositories.CoderRepository;
import hanu.gdsc.domain.share.models.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchCoderService {

    @Autowired
    private CoderRepository coderRepository;

    public Coder getById(Id id) {
        return coderRepository.getById(id);
    }

    public List<Coder> getTopCoders(int page, int perPage) {
        return coderRepository.get(page, perPage);
    }
}
