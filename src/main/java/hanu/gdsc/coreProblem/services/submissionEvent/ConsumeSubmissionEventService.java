package hanu.gdsc.coreProblem.services.submissionEvent;

import hanu.gdsc.coreProblem.config.SubmissionEventConfig;
import hanu.gdsc.coreProblem.domains.Status;
import hanu.gdsc.coreProblem.domains.SubmissionCount;
import hanu.gdsc.coreProblem.domains.SubmissionEvent;
import hanu.gdsc.coreProblem.repositories.SubmissionCountRepository;
import hanu.gdsc.coreProblem.repositories.SubmissionEventRepository;
import hanu.gdsc.share.scheduling.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ConsumeSubmissionEventService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumeSubmissionEventService.class);

    private SubmissionEventRepository submissionEventRepository;

    private SubmissionCountRepository submissionCountRepository;

    public ConsumeSubmissionEventService(SubmissionEventRepository submissionEventRepository, SubmissionCountRepository submissionCountRepository) {
        this.submissionEventRepository = submissionEventRepository;
        this.submissionCountRepository = submissionCountRepository;
        new Scheduler(SubmissionEventConfig.RATE, new Scheduler.Runner() {
            @Override
            public void run() {
                process();
            }
        }).start();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void process() {
        try {
            SubmissionEvent submissionEvent = submissionEventRepository.getSubmissionEvent();
            if (submissionEvent == null) {
                return;
            }
            SubmissionCount submissionCount = submissionCountRepository.getByProblemId(submissionEvent.getProblemId());
            if (submissionEvent.getStatus().equals(Status.AC)) {
                submissionCount.increaseACsCount();
            }
            submissionCount.increaseSubmissionsCount();
            submissionCountRepository.update(submissionCount);
            submissionEventRepository.delete(submissionEvent.getId());
            LOGGER.info("Increased submission count for problemId: " + submissionEvent.getProblemId());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Process submission event error: " + e);

        }
    }
}
