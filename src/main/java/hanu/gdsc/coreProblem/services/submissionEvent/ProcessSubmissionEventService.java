package hanu.gdsc.coreProblem.services.submissionEvent;

import hanu.gdsc.coreProblem.domains.Status;
import hanu.gdsc.coreProblem.domains.SubmissionCount;
import hanu.gdsc.coreProblem.domains.SubmissionEvent;
import hanu.gdsc.coreProblem.repositories.SubmissionCountRepository;
import hanu.gdsc.coreProblem.repositories.SubmissionEventRepository;
import hanu.gdsc.share.scheduling.ScheduledThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProcessSubmissionEventService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessSubmissionEventService.class);

    private SubmissionEventRepository submissionEventRepository;

    private SubmissionCountRepository submissionCountRepository;

    public ProcessSubmissionEventService(SubmissionEventRepository submissionEventRepository, SubmissionCountRepository submissionCountRepository) {
        this.submissionEventRepository = submissionEventRepository;
        this.submissionCountRepository = submissionCountRepository;
        new ScheduledThread(10000, new ScheduledThread.Runner() {
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
