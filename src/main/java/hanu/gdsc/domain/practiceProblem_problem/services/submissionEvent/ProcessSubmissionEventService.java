package hanu.gdsc.domain.practiceProblem_problem.services.submissionEvent;

import hanu.gdsc.domain.core_order.models.Budget;
import hanu.gdsc.domain.core_order.models.Transaction;
import hanu.gdsc.domain.core_order.repositories.BudgetRepository;
import hanu.gdsc.domain.core_order.repositories.TransactionRepository;
import hanu.gdsc.domain.core_problem.models.Status;
import hanu.gdsc.domain.core_problem.models.Submission;
import hanu.gdsc.domain.core_problem.models.SubmissionEvent;
import hanu.gdsc.domain.core_problem.repositories.SubmissionRepository;
import hanu.gdsc.domain.practiceProblem_problem.config.PracticeProblemProblemServiceName;
import hanu.gdsc.domain.practiceProblem_problem.models.Problem;
import hanu.gdsc.domain.practiceProblem_problem.models.Progress;
import hanu.gdsc.domain.practiceProblem_problem.repositories.ProblemRepository;
import hanu.gdsc.domain.practiceProblem_problem.repositories.ProgressRepository;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "PracticeProblem.ConsumeSubmissionEventService")
@AllArgsConstructor
public class ProcessSubmissionEventService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessSubmissionEventService.class);
    private final ProgressRepository progressRepository;
    private final ProblemRepository problemRepository;
    private final hanu.gdsc.domain.core_problem.services.submissionEvent.ConsumeSubmissionEventService coreConsumeSubmissionEventService;
    private final SubmissionRepository submissionRepository;
    private final BudgetRepository budgetRepository;
    private final TransactionRepository transactionRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void consume(SubmissionEvent event, Runnable ack) {
        try {
            coreConsumeSubmissionEventService.process(event);
            LOGGER.info("PRACTICE COMSUMING: " + event.getProblemId());
            Progress progress = progressRepository.getByCoderId(event.getCoderId());
            if (progress == null) {
                progress = Progress.create(event.getCoderId());
            }
            final Problem practiceProblem = problemRepository.getByCoreProblemProblemId(event.getProblemId());
            final Submission submission = submissionRepository.getByProblemIdAndCoderId(event.getProblemId(), event.getCoderId());
            Budget budget = budgetRepository.getByCoderId(event.getCoderId());
            if (practiceProblem == null || !event.getStatus().equals(Status.AC) || submission != null)
                return;
            progress.update(practiceProblem.getDifficulty());
            Transaction transaction = budget.increase(practiceProblem.getPoint(), PracticeProblemProblemServiceName.serviceName);
            transactionRepository.save(transaction);
            budgetRepository.save(budget);
            progressRepository.save(progress);
            LOGGER.info("UPDATED PROCESS: " + progress.getCoderId());
            ack.run();
        } catch (InvalidInputException e) {
            throw new RuntimeException(e);
        }
    }
}
