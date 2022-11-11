package hanu.gdsc.domain.core_problem.repositories;

import java.util.List;

import hanu.gdsc.domain.core_problem.models.Submission;
import hanu.gdsc.domain.share.models.DateTime;
import hanu.gdsc.domain.share.models.Id;
import org.springframework.data.jpa.repository.Query;

public interface SubmissionRepository {
    public List<Submission> get(int page, int perPage, Id problemId, Id coderId, String serviceToCreate);
    public List<Submission> get(int page, int perPage, List<Id> problemIds, Id coderId, String serviceToCreate);
    public Submission getById(Id id, String serviceToCreate);
    public void deleteAllByProblemId(Id problemId);
    public Submission getACSubmissionBefore(Id coderId, Id problemId, DateTime beforeTime);

    public long countNotACSubmissionsBefore(Id coderId,
                                           Id problemId,
                                           String serviceToCreate,
                                           DateTime beforeTime);
}