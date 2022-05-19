package hanu.gdsc.coreProblem.services.submit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.*;
import hanu.gdsc.coreProblem.config.SubmitQueueConfig;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeoutException;

@Service
public class StartJudgeTestCaseEventQueueImpl implements StartJudgeTestCaseEventQueue {
    private Queue<StartJudgeTestCaseEvent> queue;
    private Gson gson;
    private Channel channel;

    public StartJudgeTestCaseEventQueueImpl() throws IOException, TimeoutException {
        this.queue = new ConcurrentLinkedQueue();
        this.gson = new GsonBuilder().create();
        startRabbitMQ();
    }

    private void startRabbitMQ() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(SubmitQueueConfig.START_JUDGE_TEST_CASE_QUEUE_RABBIT_HOST_NAME);
        connectionFactory.setPort(SubmitQueueConfig.START_JUDGE_TEST_CASE_QUEUE_RABBIT_PORT);

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        this.channel = channel;

        channel.queueDeclare(SubmitQueueConfig.START_JUDGE_TEST_CASE_QUEUE_RABBIT_QUEUE_NAME, true, false, false, null);
        channel.exchangeDeclare(SubmitQueueConfig.START_JUDGE_TEST_CASE_QUEUE_RABBIT_EXCHANGE, "direct", true);
        channel.queueBind(SubmitQueueConfig.START_JUDGE_TEST_CASE_QUEUE_RABBIT_QUEUE_NAME,
                SubmitQueueConfig.START_JUDGE_TEST_CASE_QUEUE_RABBIT_EXCHANGE,
                SubmitQueueConfig.START_JUDGE_TEST_CASE_QUEUE_RABBIT_ROUTING_KEY);
        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                StartJudgeTestCaseEvent startJudgeTestCaseEvent = gson.fromJson(message, StartJudgeTestCaseEvent.class);
                startJudgeTestCaseEvent.setEventId(envelope.getDeliveryTag());
                queue.add(startJudgeTestCaseEvent);
            }
        };
        channel.basicConsume(SubmitQueueConfig.START_JUDGE_TEST_CASE_QUEUE_RABBIT_QUEUE_NAME, true, consumer);
    }


    @Override
    public StartJudgeTestCaseEvent get() {
        return queue.poll();
    }

    @Override
    public void ack(StartJudgeTestCaseEvent event) throws IOException {
        if (event.getEventId() == null) {
            throw new Error("Event must have eventId");
        }
        channel.basicAck(event.getEventId(), false);
    }

    @Override
    public void publish(StartJudgeTestCaseEvent event) throws IOException {
        event.nullOutEventId();
        channel.basicPublish(
                SubmitQueueConfig.START_JUDGE_TEST_CASE_QUEUE_RABBIT_EXCHANGE,
                SubmitQueueConfig.START_JUDGE_TEST_CASE_QUEUE_RABBIT_ROUTING_KEY,
                new AMQP.BasicProperties(),
                gson.toJson(event).getBytes()
        );
    }
}
