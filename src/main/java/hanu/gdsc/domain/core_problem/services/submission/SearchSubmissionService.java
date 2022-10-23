package hanu.gdsc.domain.core_problem.services.submission;

import hanu.gdsc.domain.core_problem.models.Submission;
import hanu.gdsc.domain.practiceProblem_problem.exceptions.SubmissionIsBeingJudgedException;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.NotFoundException;

import java.util.List;

public interface SearchSubmissionService {

    public List<Submission> get(int page,
                                int perPage,
                                Id problemId,
                                Id coderId,
                                String serviceToCreate);

    public Submission getById(Id id, String serviceToCreate) throws SubmissionIsBeingJudgedException, NotFoundException;

    public List<Id> getAllProblemIdACByCoderId(Id coderId, String serviceToCreate);
}

