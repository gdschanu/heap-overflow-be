package hanu.gdsc.coreProblem.repositories;

import java.util.List;

import hanu.gdsc.coreProblem.domains.Submission;
import hanu.gdsc.share.domains.Id;

public interface SubmissionRepository {
    public void create(Submission submission);
}
