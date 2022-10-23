package hanu.gdsc.domain.core_problem.services.problem;

import hanu.gdsc.domain.core_problem.models.Problem;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.NotFoundException;

import java.util.List;


public interface SearchProblemService {
    public Problem getById(Id id, String serviceToCreate) throws NotFoundException;

    public List<Problem> getByIds(List<Id> ids, String serviceToCreate);
}
