package hanu.gdsc.coreProblem.services.problem;

import java.util.List;

import org.springframework.data.domain.Pageable;

import hanu.gdsc.coreProblem.domains.Problem;

public interface SearchProblemService {
    public List<Problem> search (Pageable pageable);
}
