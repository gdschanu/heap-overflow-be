package hanu.gdsc.practiceProblem_problem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RunningSubmissionConfig {
    public static  int MAX_THREAD = 2;
    public static  int RATE_MILLIS = 5000;
    public static  int CLAIM_SUBMISSION_LOCK_SECOND = 60 * 5;

    public static  int SLEEP_WAITING_FOR_SUBMISSION_MILLIS = 100;


    public static  int PORT = 5000;
    public static  long GET_RUNNING_SUBMISSION_INFO_RATE_MILLIS = 5000;
    public static  long UPDATE_RUNNING_SUBMISSION_RATE_MILLIS = 3000;

    public static  long DELETE_JUDGER_SUBMISSION_RATE_MILLIS = 5000;

    public static  String IP = "localhost";

    public RunningSubmissionConfig(Environment environment) {
        if (environment.getProperty("IP") != null) {
            IP = environment.getProperty("IP");
        }
    }
}
