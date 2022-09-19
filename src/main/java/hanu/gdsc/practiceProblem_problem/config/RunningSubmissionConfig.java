package hanu.gdsc.practiceProblem_problem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RunningSubmissionConfig {
    public static  int PORT = 5000;

    public static  String IP = "localhost";

    public RunningSubmissionConfig(Environment environment) {
        if (environment.getProperty("IP") != null) {
            IP = environment.getProperty("IP");
        }
    }
}
