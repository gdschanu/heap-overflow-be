package hanu.gdsc.infrastructure.contest.consumer;

import com.rabbitmq.client.Channel;
import hanu.gdsc.domain.contest.services.submissionEvent.ProcessSubmissionEventService;
import hanu.gdsc.domain.core_problem.models.SubmissionEvent;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("hanu.gdsc.infrastructure.contest.consumer.SubmissionEventConsumer")
public class SubmissionEventConsumer {
    @Autowired
    private ProcessSubmissionEventService processSubmissionEventService;
    private static final String SUBMISSIONEVENTQUEUE = "Q_CONTEST_SUBMISSIONEVENT";
    private static final String EXCHANGE = "E_COREPROBLEM_SUBMISSIONEVENT";

    @RabbitListener(queues = SUBMISSIONEVENTQUEUE, ackMode = "MANUAL")
    public void consume(SubmissionEvent event, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        channel.basicQos(1);
        processSubmissionEventService.process(event, () -> {
            try {
                channel.basicAck(tag, false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Bean(name = "hanu.gdsc.infrastructure.contest.consumer.queue")
    public Queue submissionEventQueue() {
        return new Queue(SUBMISSIONEVENTQUEUE, true);
    }

    @Bean(name = "hanu.gdsc.infrastructure.contest.consumer.exchange")
    public FanoutExchange fanout() {
        return new FanoutExchange(EXCHANGE);
    }

    @Bean(name = "hanu.gdsc.infrastructure.contest.consumer.binding")
    public Binding binding(@Qualifier("hanu.gdsc.infrastructure.contest.consumer.exchange")
                           FanoutExchange fanoutExchange,
                           @Qualifier("hanu.gdsc.infrastructure.contest.consumer.queue")
                           Queue queue) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
}
