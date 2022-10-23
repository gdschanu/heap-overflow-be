package hanu.gdsc.infrastructure.core_problem.virtualMachine;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.domain.core_problem.config.RunningSubmissionConfig;
import hanu.gdsc.domain.core_problem.models.KB;
import hanu.gdsc.domain.core_problem.models.Millisecond;
import hanu.gdsc.domain.core_problem.models.ProgrammingLanguage;
import hanu.gdsc.domain.core_problem.models.TimeLimit;
import hanu.gdsc.domain.core_problem.virtualMachine.VirtualMachine;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

@Service
public class VirtualMachineImpl implements VirtualMachine {
    private ObjectMapper objectMapper;
    private RunningSubmissionConfig runningSubmissionConfig;

    public VirtualMachineImpl(ObjectMapper objectMapper, RunningSubmissionConfig runningSubmissionConfig) {
        this.objectMapper = objectMapper;
        this.runningSubmissionConfig = runningSubmissionConfig;
    }

    private String base64Encode(String s) {
        if (s == null) return s;
        return new String(Base64.getEncoder().encode(s.getBytes()));
    }

    private String base64Decode(String s) {
        if (s == null) return s;
        return new String(Base64.getDecoder().decode(s.replace("\n", "")));
    }

    private static class CreateSubmissionRequest {
        public int language_id;
        public String source_code;
        public String stdin;
        public long cpu_time_limit;
    }

    private static class CreateSubmissionResponseStatus {
        public int id;
    }

    private static class CreateSubmissionResponse {
        public String stdout;
        public String time;
        public String memory;
        public String stderr;
        public String compile_output;
        public CreateSubmissionResponseStatus status;
        public String token;
    }


    public Submission createSubmission(String code, String input, ProgrammingLanguage programmingLanguage) throws IOException, InterruptedException {
        CreateSubmissionRequest request = new CreateSubmissionRequest();
        request.language_id = getJudge0ProgrammingLanguageId(programmingLanguage);
        request.source_code = new String(Base64.getEncoder().encode(code.getBytes()));
        request.stdin = new String(Base64.getEncoder().encode(input.getBytes()));
        request.cpu_time_limit = TimeLimit.MAX.toSecond();
        String requestString = objectMapper.writeValueAsString(request);
        HttpRequest httpReq = HttpRequest.newBuilder()
                .uri(URI.create(runningSubmissionConfig.getVirtualMachineUrl() + "/submissions" + "?base64_encoded=true&fields=*&wait=true"))
                .header("content-type", "application/json")
                .header("X-Auth-Token", runningSubmissionConfig.getVirtualMachineToken())
                .method("POST", HttpRequest.BodyPublishers.ofString(requestString))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(httpReq, HttpResponse.BodyHandlers.ofString());
        CreateSubmissionResponse submission = objectMapper.readValue(response.body(), CreateSubmissionResponse.class);
        if (response.statusCode() == 201)
            deleteSubmission(submission.token);
        else {
            return new SubmissionImpl(
                    "",
                    null,
                    null,
                    null,
                    null,
                    -1,
                    response.body()
            );
        }
        return new SubmissionImpl(
                base64Decode(submission.stdout),
                submission.time,
                submission.memory,
                base64Decode(submission.stderr),
                base64Decode(submission.compile_output),
                submission.status.id,
                ""
        );
    }

    private int getJudge0ProgrammingLanguageId(ProgrammingLanguage programmingLanguage) {
        switch (programmingLanguage) {
            case JAVA:
                return 62;
            case PYTHON:
                return 71;
            case CPLUSPLUS:
                return 54;
            case JAVASCRIPT:
                return 63;
        }
        return 0;
    }

    public static class SubmissionImpl implements Submission {
        private String stdout;
        private String time;
        private String memory;
        private String stderr;
        private String compileOutput;
        private int status;

        private String stdMessage;

        public SubmissionImpl(String stdout,
                              String time,
                              String memory,
                              String stderr,
                              String compileOutput,
                              int status,
                              String stdMessage) {
            this.stdout = stdout;
            this.time = time;
            this.memory = memory;
            this.stderr = stderr;
            this.compileOutput = compileOutput;
            this.status = status;
            this.stdMessage = stdMessage;
        }

        public boolean compilationError() {
           return status == 6;
        }

        public String compilationMessage() {
            return compileOutput == null ?
                    "" : compileOutput;
        }

        public boolean stdError() {
            return status == -1;
        }

        public String stdMessage() {
            return stdMessage;
        }

        public KB memory() {
            if (memory == null)
                return new KB(0);
            return new KB(Double.parseDouble(memory));
        }

        public Millisecond runTime() {
            if (time == null) {
                return new Millisecond(0);
            }
            return Millisecond.fromSecond(Float.parseFloat(time));
        }

        public String output() {
            if (stdout == null) {
                return "";
            }
            return stdout;
        }

        @Override
        public String toString() {
            return "SubmissionImpl{" +
                    "stdout='" + stdout + '\'' +
                    ", time='" + time + '\'' +
                    ", memory='" + memory + '\'' +
                    ", stderr='" + stderr + '\'' +
                    ", compileOutput='" + compileOutput + '\'' +
                    ", status=" + status +
                    ", stdMessage='" + stdMessage + '\'' +
                    '}';
        }
    }

    private void deleteSubmission(String submissionToDelete) {
        HttpRequest httpReq = HttpRequest.newBuilder()
                .uri(URI.create(runningSubmissionConfig.getVirtualMachineUrl() + "/submissions/" + submissionToDelete))
                .header("content-type", "application/json")
                .header("X-Auth-Token", runningSubmissionConfig.getVirtualMachineToken())
                .header("X-Auth-User", runningSubmissionConfig.getVirtualMachineUser())
                .method("DELETE", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(httpReq, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200)
                throw new Error("Error deleting judger submission, http code: " + response.statusCode());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
