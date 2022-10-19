package hanu.gdsc.core_problem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RunningSubmissionConfig {
    public static int MAX_THREAD = 2;
    public static int SCAN_RATE_MILLIS = 5000;
    public static int SCAN_LOCK_SECOND = 60 * 5;

    public static String VM_URL = "http://103.183.113.65:2358";

    public RunningSubmissionConfig(Environment environment) {
        if (environment.getProperty("coreproblem.runningsubmission.maxthread") != null) {
            MAX_THREAD = Integer.parseInt(environment.getProperty("coreproblem.runningsubmission.maxthread"));
        }
        if (environment.getProperty("coreproblem.runningsubmission.scanratemillis") != null) {
            SCAN_RATE_MILLIS = Integer.parseInt(environment.getProperty("coreproblem.runningsubmission.scanratemillis"));
        }
        if (environment.getProperty("coreproblem.runningsubmission.scanlocksecond") != null) {
            SCAN_LOCK_SECOND = Integer.parseInt(environment.getProperty("coreproblem.runningsubmission.scanlocksecond"));
        }
        if (environment.getProperty("coreproblem.runningsubmission.vmurl") != null) {
            VM_URL = environment.getProperty("coreproblem.runningsubmission.vmurl");
        }
    }
}
