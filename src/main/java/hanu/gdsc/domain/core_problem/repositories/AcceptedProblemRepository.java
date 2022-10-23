package hanu.gdsc.domain.core_problem.repositories;

import hanu.gdsc.domain.core_problem.models.AcceptedProblem;
import hanu.gdsc.domain.share.models.Id;

import java.util.List;

public interface AcceptedProblemRepository {
    public void save(AcceptedProblem acceptedProblem);

    public List<AcceptedProblem> getByProblemIdsAndCoderId(Id coderId, List<Id> problemIds);

    public AcceptedProblem getByProblemIdAndCoderId(Id problemId, Id coderId, String serviceToCreate);
}
