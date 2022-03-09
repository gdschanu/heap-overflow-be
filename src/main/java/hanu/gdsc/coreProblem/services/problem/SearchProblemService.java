package hanu.gdsc.coreProblem.services.problem;

import java.util.List;

import org.springframework.data.domain.Pageable;

import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.share.domains.Id;

public interface SearchProblemService {
    public Problem getById(Id id);
    
    public List<Problem> search (Pageable pageable);

    public long count();
}
