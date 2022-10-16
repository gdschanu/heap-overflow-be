package hanu.gdsc.practiceProblem_problem.services.submissionEvent;

import hanu.gdsc.core_problem.domains.SubmissionEvent;
import hanu.gdsc.practiceProblem_problem.domains.Problem;
import hanu.gdsc.practiceProblem_problem.domains.Progress;
import hanu.gdsc.practiceProblem_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.practiceProblem_problem.repositories.progress.ProgressRepository;
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
