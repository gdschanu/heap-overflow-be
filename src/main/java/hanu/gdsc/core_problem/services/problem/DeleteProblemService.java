package hanu.gdsc.core_problem.services.problem;

import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface DeleteProblemService {
    public void deleteById(Id id);

    public void deleteMany(List<Id> ids);
}
