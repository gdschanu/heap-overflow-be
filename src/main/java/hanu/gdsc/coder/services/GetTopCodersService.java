package hanu.gdsc.coder.services;

import hanu.gdsc.coder.domains.Coder;
import hanu.gdsc.coder.repositories.CoderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTopCodersService {

    @Autowired
    private CoderRepository coderRepository;

    public List<Coder> getTopCoders(int page, int perPage) {
        return coderRepository.get(page, perPage);
    }
}
