package hanu.gdsc.core_problem.services.submission;

import hanu.gdsc.core_problem.domains.*;
import hanu.gdsc.practiceProblem_problem.exceptions.SubmissionIsBeingJudgedException;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.NotFoundException;

import java.util.List;

public interface SearchSubmissionService {

    public List<Submission> get(int page,
                                int perPage,
                                Id problemId,
                                Id coderId,
                                String serviceToCreate);

    public Submission getById(Id id, String serviceToCreate) throws SubmissionIsBeingJudgedException, NotFoundException;

    public List<String> getAllProblemIdACByCoderId(Id coderId, String serviceToCreate);
}

