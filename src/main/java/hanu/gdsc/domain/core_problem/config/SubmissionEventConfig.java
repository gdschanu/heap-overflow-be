package hanu.gdsc.domain.core_problem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

public interface SubmissionEventConfig {
    public int getScanRateMillis();
}
