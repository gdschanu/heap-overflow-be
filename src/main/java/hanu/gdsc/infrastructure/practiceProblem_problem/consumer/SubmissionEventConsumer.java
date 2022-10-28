package hanu.gdsc.infrastructure.practiceProblem_problem.consumer;

import com.rabbitmq.client.Channel;
import hanu.gdsc.domain.core_problem.models.SubmissionEvent;
import hanu.gdsc.domain.practiceProblem_problem.services.submissionEvent.ProcessSubmissionEventService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SubmissionEventConsumer {
    @Autowired
    private ProcessSubmissionEventService processSubmissionEventService;

    @RabbitListener(queues = "SUBMISSIONEVENTQUEUE", ackMode = "MANUAL")
    public void consume(SubmissionEvent event, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        channel.basicQos(1);
        processSubmissionEventService.consume(event, () -> {
            try {
                channel.basicAck(tag, false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
