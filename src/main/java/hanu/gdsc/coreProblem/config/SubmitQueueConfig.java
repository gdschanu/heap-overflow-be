package hanu.gdsc.coreProblem.config;

public class SubmitQueueConfig {
    public static final String START_JUDGE_TEST_CASE_QUEUE_RABBIT_HOST_NAME = "103.183.113.130";
    public static final int START_JUDGE_TEST_CASE_QUEUE_RABBIT_PORT = 5673;
    public static final String START_JUDGE_TEST_CASE_QUEUE_RABBIT_QUEUE_NAME = "StartJudgeTestCaseQueue";

    public static final String START_JUDGE_TEST_CASE_QUEUE_RABBIT_EXCHANGE = "StartJudgeTestCaseQueueExchange";
    public static final String START_JUDGE_TEST_CASE_QUEUE_RABBIT_ROUTING_KEY = "StartJudgeTestCaseQueueRoutingKey";


    public static final String COMPLETE_JUDGE_TEST_CASE_QUEUE_RABBIT_HOST_NAME = START_JUDGE_TEST_CASE_QUEUE_RABBIT_HOST_NAME;
    public static final int COMPLETE_JUDGE_TEST_CASE_QUEUE_RABBIT_PORT = START_JUDGE_TEST_CASE_QUEUE_RABBIT_PORT;
    public static final String COMPLETE_JUDGE_TEST_CASE_QUEUE_RABBIT_QUEUE_NAME = "CompleteJudgeTestCaseQueue";

    public static final String COMPLETE_JUDGE_TEST_CASE_QUEUE_RABBIT_EXCHANGE = "CompleteJudgeTestCaseQueueExchange";
    public static final String COMPLETE_JUDGE_TEST_CASE_QUEUE_RABBIT_ROUTING_KEY = "CompleteJudgeTestCaseQueueRoutingKey";


}
