package hanu.gdsc.practiceProblem_problem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration(value = "PracticeProblem.RunningSubmissionConfig")
public class RunningSubmissionConfig {
    public static  int PORT = 5000;

    public static  String IP = "localhost";

    public RunningSubmissionConfig(Environment environment) {
        if (environment.getProperty("practiceproblem.problem.runningsubmission.serverip") != null) {
            IP = environment.getProperty("practiceproblem.problem.runningsubmission.serverip");
        }
        if (environment.getProperty("practiceproblem.problem.runningsubmission.port") != null) {
            PORT = Integer.parseInt(environment.getProperty("practiceproblem.problem.runningsubmission.port"));
        }
    }
}
