package hanu.gdsc.core_problem.services.submissionEvent;

import hanu.gdsc.core_problem.config.SubmissionEventConfig;
import hanu.gdsc.core_problem.domains.AcceptedProblem;
import hanu.gdsc.core_problem.domains.Status;
import hanu.gdsc.core_problem.domains.SubmissionCount;
import hanu.gdsc.core_problem.domains.SubmissionEvent;
import hanu.gdsc.core_problem.repositories.acceptedProblem.AcceptedProblemRepository;
import hanu.gdsc.core_problem.repositories.submissionCount.SubmissionCountRepository;
import hanu.gdsc.core_problem.repositories.submissionEvent.SubmissionEventRepository;
import hanu.gdsc.share.exceptions.InvalidInputException;
import hanu.gdsc.share.scheduling.Scheduler;
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
    private hanu.gdsc.practiceProblem_problem.services.submissionEvent.ConsumeSubmissionEventService practiceProblemSubmissionEventConsumer;

    public ConsumeSubmissionEventService(SubmissionEventRepository submissionEventRepository,
                                         SubmissionCountRepository submissionCountRepository,
                                         AcceptedProblemRepository acceptedProblemRepository,
                                         @Qualifier("PracticeProblem.ConsumeSubmissionEventService") hanu.gdsc.practiceProblem_problem.services.submissionEvent.ConsumeSubmissionEventService practiceProblemSubmissionEventConsumer) {
        this.submissionEventRepository = submissionEventRepository;
        this.submissionCountRepository = submissionCountRepository;
        new Scheduler(SubmissionEventConfig.RATE, new Scheduler.Runner() {
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
