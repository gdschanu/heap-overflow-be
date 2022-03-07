package hanu.gdsc.coreProblem.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;

import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.share.domains.Id;

public interface ProblemRepository {
    public Problem getById(Id id);

    public void create(Problem problem);

    public void deleteById(Id id);

    public List<Problem> search(Pageable pageable);
}
