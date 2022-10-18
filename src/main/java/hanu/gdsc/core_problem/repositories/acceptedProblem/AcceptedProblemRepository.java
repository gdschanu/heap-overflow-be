package hanu.gdsc.core_problem.repositories.acceptedProblem;

import hanu.gdsc.core_problem.domains.AcceptedProblem;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface AcceptedProblemRepository {
    public void save(AcceptedProblem acceptedProblem);

    public List<AcceptedProblem> getByProblemIdsAndCoderId(Id coderId, List<Id> problemIds);

    public AcceptedProblem getByProblemIdAndCoderId(Id problemId, Id coderId, String serviceToCreate);
}
