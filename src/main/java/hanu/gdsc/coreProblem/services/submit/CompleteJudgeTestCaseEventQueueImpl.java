package hanu.gdsc.coreProblem.services.submit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.*;
import hanu.gdsc.coreProblem.config.SubmitQueueConfig;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class CompleteJudgeTestCaseEventQueueImpl implements CompleteJudgeTestCaseEventQueue {
    private CompleteJudgeTestCaseEventHandler completeJudgeTestCaseEventHandler;
    private Gson gson;
    private Channel channel;

    public CompleteJudgeTestCaseEventQueueImpl(CompleteJudgeTestCaseEventHandler completeJudgeTestCaseEventHandler) throws IOException, TimeoutException {
        this.completeJudgeTestCaseEventHandler = completeJudgeTestCaseEventHandler;
        this.gson = new GsonBuilder().create();
        startRabbitMQ();
    }

    private void startRabbitMQ() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(SubmitQueueConfig.COMPLETE_JUDGE_TEST_CASE_QUEUE_RABBIT_HOST_NAME);

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        this.channel = channel;

        channel.queueDeclare(SubmitQueueConfig.COMPLETE_JUDGE_TEST_CASE_QUEUE_RABBIT_QUEUE_NAME, true, false, false, null);
        channel.exchangeDeclare(SubmitQueueConfig.START_JUDGE_TEST_CASE_QUEUE_RABBIT_EXCHANGE, "Direct", true);

        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                CompleteJudgeTestCaseEvent completeJudgeTestCaseEventQueue = gson.fromJson(message, CompleteJudgeTestCaseEvent.class);
                try {
                    completeJudgeTestCaseEventHandler.handle(completeJudgeTestCaseEventQueue);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        channel.basicConsume(SubmitQueueConfig.COMPLETE_JUDGE_TEST_CASE_QUEUE_RABBIT_QUEUE_NAME, true, consumer);
    }

    @Override
    public void publish(CompleteJudgeTestCaseEvent event) throws IOException {
        channel.basicPublish(
                SubmitQueueConfig.COMPLETE_JUDGE_TEST_CASE_QUEUE_RABBIT_EXCHANGE,
                "",
                new AMQP.BasicProperties(),
                gson.toJson(event).getBytes()
        );
    }

    @Override
    public void ack(CompleteJudgeTestCaseEvent event) throws IOException {
        if (event.getEventId() == null) {
            throw new Error("Event must have eventId");
        }
        channel.basicAck(event.getEventId(), false);
    }
}
