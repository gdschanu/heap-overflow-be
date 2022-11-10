package hanu.gdsc.domain.practiceProblem_problem.services.submissionEvent;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.domain.core_problem.models.Status;
import hanu.gdsc.domain.core_problem.models.SubmissionEvent;
import hanu.gdsc.domain.practiceProblem_problem.config.PracticeProblemProblemServiceName;
import hanu.gdsc.domain.practiceProblem_problem.models.Problem;
import hanu.gdsc.domain.practiceProblem_problem.models.Progress;
import hanu.gdsc.domain.practiceProblem_problem.repositories.ProblemRepository;
import hanu.gdsc.domain.practiceProblem_problem.repositories.ProgressRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ProcessPracticeProblemSubmissionEventService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessPracticeProblemSubmissionEventService.class);
    private final ProgressRepository progressRepository;
    private final ProblemRepository problemRepository;
    private final ObjectMapper objectMapper;

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void process(SubmissionEvent event, Runnable ack) {
        try {
            System.out.println(objectMapper.writeValueAsString(event));
        } catch (Throwable e) {}
        if (!event.getServiceToCreate().equals(PracticeProblemProblemServiceName.serviceName)) {
            ack.run();
            return;
        }
        LOGGER.info("PRACTICE COMSUMING: " + event.getProblemId());
        Progress progress = progressRepository.getByCoderId(event.getCoderId());
        if (progress == null) {
            progress = Progress.create(event.getCoderId());
        }
        final Problem practiceProblem = problemRepository.getByCoreProblemProblemId(event.getProblemId());
        if (practiceProblem == null || !event.getStatus().equals(Status.AC))
            return;
        progress.update(practiceProblem.getDifficulty());
        progressRepository.save(progress);
        LOGGER.info("UPDATED PROCESS: " + progress.getCoderId());
        ack.run();
    }
}
