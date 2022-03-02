package hanu.gdsc.coreProblem.services.submission;

import java.util.UUID;

import hanu.gdsc.coreProblem.domains.Submission;

public interface GetSubmissionService {
    public Submission getById(UUID id);
}
