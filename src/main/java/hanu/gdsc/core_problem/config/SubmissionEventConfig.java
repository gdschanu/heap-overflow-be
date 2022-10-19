package hanu.gdsc.core_problem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SubmissionEventConfig {
    public static int SCAN_RATE_MILLIS = 10000;

    public SubmissionEventConfig(Environment environment) {
        if (environment.getProperty("coreproblem.submissionevent.scanratemillis") != null) {
            SCAN_RATE_MILLIS = Integer.parseInt(environment.getProperty("coreproblem.submissionevent.scanratemillis"));
        }
    }
}
