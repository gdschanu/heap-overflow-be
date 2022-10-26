package hanu.gdsc.domain.core_problem.services.submissionEvent;

import hanu.gdsc.domain.core_problem.models.SubmissionEvent;
import hanu.gdsc.domain.core_problem.services.runningSubmission.ProcessRunningSubmissionService;
import hanu.gdsc.domain.share.models.Exchange;
import hanu.gdsc.domain.share.models.RoutingKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublishSubmissionEventService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PublishSubmissionEventService.class);
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void publishMessage(SubmissionEvent submissionEvent) {
        rabbitTemplate.convertAndSend(Exchange.DIRECTEXCHANGE.name(), RoutingKey.SUBMISSIONEVENTKEY.name(), submissionEvent);
        LOGGER.info("PUBLISHED MESSAGE: " + submissionEvent.getProblemId());
    }
}
