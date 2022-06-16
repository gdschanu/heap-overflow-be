package hanu.gdsc.coreSubdomain.problemContext.config;

public class RunningSubmissionConfig {
    public static final int MAX_THREAD = 2;
    public static final int RATE_MILLIS = 5000;
    public static final int CLAIM_SUBMISSION_LOCK_SECOND = 60 * 5;

    public static final int SLEEP_WAITING_FOR_SUBMISSION_MILLIS = 100;


    public static final int PORT = 5000;
    public static final long GET_RUNNING_SUBMISSION_INFO_RATE_MILLIS = 5000;
    public static final long GET_NEW_SOCKET_RATE_MILLIS = 5000;

    public static final long UPDATE_RUNNING_SUBMISSION_RATE_MILLIS = 3000;
}
