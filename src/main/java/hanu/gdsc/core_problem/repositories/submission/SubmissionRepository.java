package hanu.gdsc.core_problem.repositories.submission;

import java.util.List;
import java.util.Set;

import hanu.gdsc.core_problem.domains.Submission;
import hanu.gdsc.share.domains.Id;

public interface SubmissionRepository {
    public List<Submission> get(int page, int perPage, Id problemId, Id coderId, String serviceToCreate);
    public Submission getById(Id id, String serviceToCreate);
    public void save(Submission submission);

    public void deleteAllByProblemId(Id problemId);
    public List<Id> getAllProblemIdACByCoderId(Id coderId, String serviceToCreate);
}
