package hanu.gdsc.core_problem.config;

public class RunningSubmissionConfig {
    public static  long GET_RUNNING_SUBMISSION_INFO_RATE_MILLIS = 5000;
    public static  long UPDATE_RUNNING_SUBMISSION_RATE_MILLIS = 3000;

    public static  long DELETE_JUDGER_SUBMISSION_RATE_MILLIS = 5000;
    public static  int MAX_THREAD = 2;
    public static  int RATE_MILLIS = 5000;
    public static  int CLAIM_SUBMISSION_LOCK_SECOND = 60 * 5;

    public static  int SLEEP_WAITING_FOR_SUBMISSION_MILLIS = 100;
}
