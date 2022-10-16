package hanu.gdsc.core_problem.services.acceptedProblem;

import hanu.gdsc.core_problem.domains.AcceptedProblem;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface SearchAcceptedProblemService {
    public List<AcceptedProblem> getByProblemIdsAndCoderId(Id coderId, List<Id> problemIds, String serviceToCreate);

    public AcceptedProblem getByProblemIdAndCoderId(Id problemId, Id coderId, String serviceToCreate);
}
