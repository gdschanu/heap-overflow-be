package hanu.gdsc.domain.practiceProblem_problem.services.submissionEvent;

import hanu.gdsc.domain.core_problem.models.SubmissionEvent;
import hanu.gdsc.domain.practiceProblem_problem.models.Problem;
import hanu.gdsc.domain.practiceProblem_problem.models.Progress;
import hanu.gdsc.domain.practiceProblem_problem.repositories.ProblemRepository;
import hanu.gdsc.domain.practiceProblem_problem.repositories.ProgressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service(value = "PracticeProblem.ConsumeSubmissionEventService")
@AllArgsConstructor
public class ConsumeSubmissionEventService {
    private final ProgressRepository progressRepository;
    private final ProblemRepository problemRepository;

    public void consume(SubmissionEvent event) {
        Progress progress = progressRepository.getByCoderId(event.getCoderId());
        if (progress == null) {
            progress = Progress.create(event.getCoderId());
        }
        final Problem practiceProblem = problemRepository.getByCoreProblemProblemId(event.getProblemId());
        if (practiceProblem == null)
            return;
        progress.update(practiceProblem.getDifficulty());
        progressRepository.save(progress);
    }
}
