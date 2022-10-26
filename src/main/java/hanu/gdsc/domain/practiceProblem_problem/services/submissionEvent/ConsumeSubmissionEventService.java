package hanu.gdsc.domain.practiceProblem_problem.services.submissionEvent;

import com.rabbitmq.client.Channel;
import hanu.gdsc.domain.core_problem.models.Status;
import hanu.gdsc.domain.core_problem.models.SubmissionEvent;
import hanu.gdsc.domain.practiceProblem_problem.models.Problem;
import hanu.gdsc.domain.practiceProblem_problem.models.Progress;
import hanu.gdsc.domain.practiceProblem_problem.repositories.ProblemRepository;
import hanu.gdsc.domain.practiceProblem_problem.repositories.ProgressRepository;
import hanu.gdsc.domain.share.models.Queue;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "PracticeProblem.ConsumeSubmissionEventService")
@AllArgsConstructor
public class ConsumeSubmissionEventService {
    private static final Logger LOGGER = LoggerFactory.getLogger(hanu.gdsc.domain.practiceProblem_problem.services.submissionEvent.ConsumeSubmissionEventService.class);
    private final ProgressRepository progressRepository;
    private final ProblemRepository problemRepository;
    private final hanu.gdsc.domain.core_problem.services.submissionEvent.ConsumeSubmissionEventService coreConsumeSubmissionEventService;

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    @RabbitListener(queues = "SUBMISSIONEVENTQUEUE", ackMode = "MANUAL")
    public void consume(SubmissionEvent event, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
        channel.basicQos(1);
        coreConsumeSubmissionEventService.process(event);
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
        channel.basicAck(tag, false);
    }
}
