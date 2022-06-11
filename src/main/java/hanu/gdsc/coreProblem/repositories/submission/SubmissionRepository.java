package hanu.gdsc.coreProblem.repositories.submission;

import java.util.List;

import hanu.gdsc.coreProblem.domains.Submission;
import hanu.gdsc.share.domains.Id;

public interface SubmissionRepository {
    public List<Submission> get(int page, int perPage, Id problemId, Id coderId, String serviceToCreate);
    public Submission getById(Id id, String serviceToCreate);
    public void create(Submission submission);
}
