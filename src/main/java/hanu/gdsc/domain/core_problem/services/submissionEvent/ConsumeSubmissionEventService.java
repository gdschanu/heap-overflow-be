package hanu.gdsc.domain.core_problem.services.submissionEvent;

import hanu.gdsc.domain.core_problem.config.SubmissionEventConfig;
import hanu.gdsc.domain.core_problem.models.AcceptedProblem;
import hanu.gdsc.domain.core_problem.models.Status;
import hanu.gdsc.domain.core_problem.models.SubmissionCount;
import hanu.gdsc.domain.core_problem.models.SubmissionEvent;
import hanu.gdsc.domain.core_problem.repositories.AcceptedProblemRepository;
import hanu.gdsc.domain.core_problem.repositories.SubmissionCountRepository;
import hanu.gdsc.domain.core_problem.repositories.SubmissionEventRepository;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.scheduling.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ConsumeSubmissionEventService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumeSubmissionEventService.class);

    private SubmissionEventRepository submissionEventRepository;

    private SubmissionCountRepository submissionCountRepository;
    private AcceptedProblemRepository acceptedProblemRepository;
    private hanu.gdsc.domain.practiceProblem_problem.services.submissionEvent.ConsumeSubmissionEventService practiceProblemSubmissionEventConsumer;

    public ConsumeSubmissionEventService(SubmissionEventRepository submissionEventRepository,
                                         SubmissionCountRepository submissionCountRepository,
                                         AcceptedProblemRepository acceptedProblemRepository,
                                         @Qualifier("PracticeProblem.ConsumeSubmissionEventService") hanu.gdsc.domain.practiceProblem_problem.services.submissionEvent.ConsumeSubmissionEventService practiceProblemSubmissionEventConsumer,
                                         SubmissionEventConfig submissionEventConfig) {
        this.submissionEventRepository = submissionEventRepository;
        this.submissionCountRepository = submissionCountRepository;
        new Scheduler(submissionEventConfig.getScanRateMillis(), new Scheduler.Runner() {
            @Override
            public void run() throws InvalidInputException {
                process();
            }
        }).start();
        this.acceptedProblemRepository = acceptedProblemRepository;
        this.practiceProblemSubmissionEventConsumer = practiceProblemSubmissionEventConsumer;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void process() throws InvalidInputException {
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
            if (submissionEvent.getStatus().equals(Status.AC)) {
                acceptedProblemRepository.save(new AcceptedProblem(submissionEvent.getCoderId(), submissionEvent.getProblemId(), submissionCount.getServiceToCreate()));
            }
            // TODO: remove this in the future, consume from message queue instead
            practiceProblemSubmissionEventConsumer.consume(submissionEvent);
            LOGGER.info("Increased submission count for problemId: " + submissionEvent.getProblemId());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Process submission event error: " + e);
            throw e;
        }
    }
}
