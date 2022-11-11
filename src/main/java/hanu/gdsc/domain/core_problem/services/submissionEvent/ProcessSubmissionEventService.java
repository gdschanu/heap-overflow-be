package hanu.gdsc.domain.core_problem.services.submissionEvent;

import hanu.gdsc.domain.core_problem.models.AcceptedProblem;
import hanu.gdsc.domain.core_problem.models.Status;
import hanu.gdsc.domain.core_problem.models.SubmissionCount;
import hanu.gdsc.domain.core_problem.models.SubmissionEvent;
import hanu.gdsc.domain.core_problem.repositories.AcceptedProblemRepository;
import hanu.gdsc.domain.core_problem.repositories.SubmissionCountRepository;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service("hanu.gdsc.domain.core_problem.services.submissionEvent.ProcessSubmissionEventService")
public class ProcessSubmissionEventService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessSubmissionEventService.class);
    private SubmissionCountRepository submissionCountRepository;
    private AcceptedProblemRepository acceptedProblemRepository;

    public ProcessSubmissionEventService(SubmissionCountRepository submissionCountRepository,
                                         AcceptedProblemRepository acceptedProblemRepository) {
        this.submissionCountRepository = submissionCountRepository;
        this.acceptedProblemRepository = acceptedProblemRepository;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void process(SubmissionEvent submissionEvent, Runnable ack) {
        try {
            if (submissionEvent == null) {
                return;
            }
            LOGGER.info("CORE CONSUMING: " + submissionEvent.getProblemId());
            SubmissionCount submissionCount = submissionCountRepository.getByProblemId(submissionEvent.getProblemId());
            if (submissionEvent.getStatus().equals(Status.AC)) {
                submissionCount.increaseACsCount();
            }
            submissionCount.increaseSubmissionsCount();
            submissionCountRepository.update(submissionCount);
            if (submissionEvent.getStatus().equals(Status.AC)) {
                acceptedProblemRepository.save(new AcceptedProblem(submissionEvent.getCoderId(), submissionEvent.getProblemId(), submissionCount.getServiceToCreate()));
            }
            LOGGER.info("Increased submission count for problemId: " + submissionEvent.getProblemId());
            ack.run();
        } catch (InvalidInputException e) {
            // Cannot reach
        }
    }
}
