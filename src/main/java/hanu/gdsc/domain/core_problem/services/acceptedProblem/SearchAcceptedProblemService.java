package hanu.gdsc.domain.core_problem.services.acceptedProblem;

import hanu.gdsc.domain.core_problem.models.AcceptedProblem;
import hanu.gdsc.domain.share.models.Id;

import java.util.List;

public interface SearchAcceptedProblemService {
    public List<AcceptedProblem> getByProblemIdsAndCoderId(Id coderId, List<Id> problemIds, String serviceToCreate);

    public AcceptedProblem getByProblemIdAndCoderId(Id problemId, Id coderId, String serviceToCreate);
}
