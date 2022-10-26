package hanu.gdsc.domain.core_problem.services.submissionEvent;

import hanu.gdsc.domain.core_problem.models.AcceptedProblem;
import hanu.gdsc.domain.core_problem.models.Status;
import hanu.gdsc.domain.core_problem.models.SubmissionCount;
import hanu.gdsc.domain.core_problem.models.SubmissionEvent;
import hanu.gdsc.domain.core_problem.repositories.AcceptedProblemRepository;
import hanu.gdsc.domain.core_problem.repositories.SubmissionCountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ConsumeSubmissionEventService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumeSubmissionEventService.class);
    private SubmissionCountRepository submissionCountRepository;
    private AcceptedProblemRepository acceptedProblemRepository;

    public ConsumeSubmissionEventService(SubmissionCountRepository submissionCountRepository,
                                         AcceptedProblemRepository acceptedProblemRepository) {
        this.submissionCountRepository = submissionCountRepository;
        this.acceptedProblemRepository = acceptedProblemRepository;
    }

    public void process(SubmissionEvent submissionEvent) throws Exception {
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
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Process submission event error: " + e);
            throw e;
        }
    }
}
