package hanu.gdsc.domain.core_problem.services.problem;

import hanu.gdsc.domain.share.models.Id;

import java.util.List;

public interface DeleteProblemService {
    public void deleteById(Id id);

    public void deleteMany(List<Id> ids);
}
