package hanu.gdsc.coreProblem.config;

public class SubmitQueueConfig {
    public static final String START_JUDGE_TEST_CASE_QUEUE_RABBIT_HOST_NAME = "localhost";
    public static final String START_JUDGE_TEST_CASE_QUEUE_RABBIT_QUEUE_NAME = "StartJudgeTestCaseQueue";

    public static final String START_JUDGE_TEST_CASE_QUEUE_RABBIT_EXCHANGE = "StartJudgeTestCaseQueueExchange";
    public static final String START_JUDGE_TEST_CASE_QUEUE_RABBIT_ROUTING_KEY = "StartJudgeTestCaseQueueRoutingKey";


    public static final String COMPLETE_JUDGE_TEST_CASE_QUEUE_RABBIT_HOST_NAME = "localhost";
    public static final String COMPLETE_JUDGE_TEST_CASE_QUEUE_RABBIT_QUEUE_NAME = "CompleteJudgeTestCaseQueue";

    public static final String COMPLETE_JUDGE_TEST_CASE_QUEUE_RABBIT_EXCHANGE = "CompleteJudgeTestCaseQueueExchange";
    public static final String COMPLETE_JUDGE_TEST_CASE_QUEUE_RABBIT_ROUTING_KEY = "CompleteJudgeTestCaseQueueRoutingKey";


}
