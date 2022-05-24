package hanu.gdsc.coreProblem.services.submit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;
import hanu.gdsc.coreProblem.config.SubmitQueueConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeoutException;

@Service
public class CompleteJudgeTestCaseEventQueueImpl implements CompleteJudgeTestCaseEventQueue {
    private Queue<CompleteJudgeTestCaseEvent> queue;
    private Channel channel;
    @Autowired
    private ObjectMapper objectMapper;


    public CompleteJudgeTestCaseEventQueueImpl() throws IOException, TimeoutException {
        this.queue = new ConcurrentLinkedQueue();
        startRabbitMQ();
    }

    private void startRabbitMQ() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(SubmitQueueConfig.COMPLETE_JUDGE_TEST_CASE_QUEUE_RABBIT_HOST_NAME);
        connectionFactory.setPort(SubmitQueueConfig.COMPLETE_JUDGE_TEST_CASE_QUEUE_RABBIT_PORT);

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        this.channel = channel;

        channel.queueDeclare(SubmitQueueConfig.COMPLETE_JUDGE_TEST_CASE_QUEUE_RABBIT_QUEUE_NAME, true, false, false, null);
        channel.exchangeDeclare(SubmitQueueConfig.COMPLETE_JUDGE_TEST_CASE_QUEUE_RABBIT_EXCHANGE, "direct", true);
        channel.queueBind(SubmitQueueConfig.COMPLETE_JUDGE_TEST_CASE_QUEUE_RABBIT_QUEUE_NAME,
                SubmitQueueConfig.COMPLETE_JUDGE_TEST_CASE_QUEUE_RABBIT_EXCHANGE,
                SubmitQueueConfig.COMPLETE_JUDGE_TEST_CASE_QUEUE_RABBIT_ROUTING_KEY);
        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                CompleteJudgeTestCaseEvent completeJudgeTestCaseEvent = objectMapper.readValue(message, CompleteJudgeTestCaseEvent.class);
                completeJudgeTestCaseEvent.setEventId(envelope.getDeliveryTag());
                queue.add(completeJudgeTestCaseEvent);
            }
        };
        channel.basicConsume(SubmitQueueConfig.COMPLETE_JUDGE_TEST_CASE_QUEUE_RABBIT_QUEUE_NAME, true, consumer);
    }

    @Override
    public CompleteJudgeTestCaseEvent get() {
        return queue.poll();
    }

    @Override
    public void ack(CompleteJudgeTestCaseEvent event) throws IOException {
        if (event.getEventId() == null) {
            throw new Error("Event must have eventId");
        }
        channel.basicAck(event.getEventId(), false);
    }

    @Override
    public void publish(CompleteJudgeTestCaseEvent event) throws IOException {
        event.nullOutEventId();
        channel.basicPublish(
                SubmitQueueConfig.COMPLETE_JUDGE_TEST_CASE_QUEUE_RABBIT_EXCHANGE,
                SubmitQueueConfig.COMPLETE_JUDGE_TEST_CASE_QUEUE_RABBIT_ROUTING_KEY,
                new AMQP.BasicProperties(),
                objectMapper.writeValueAsString(event).getBytes()
        );
    }
}
