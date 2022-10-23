package hanu.gdsc.infrastructure.core_problem.config;

import hanu.gdsc.domain.core_problem.config.SubmissionEventConfig;
import org.springframework.core.env.Environment;

public class SubmissionEventConfigImpl implements SubmissionEventConfig {
    private static int SCAN_RATE_MILLIS = 10000;

    public SubmissionEventConfigImpl(Environment environment) {
        if (environment.getProperty("coreproblem.submissionevent.scanratemillis") != null) {
            SCAN_RATE_MILLIS = Integer.parseInt(environment.getProperty("coreproblem.submissionevent.scanratemillis"));
        }
    }

    @Override
    public int getScanRateMillis() {
        return SCAN_RATE_MILLIS;
    }
}
