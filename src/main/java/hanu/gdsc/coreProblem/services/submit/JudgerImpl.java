package hanu.gdsc.coreProblem.services.submit;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.coreProblem.config.Judge0Config;
import hanu.gdsc.coreProblem.domains.KB;
import hanu.gdsc.coreProblem.domains.Millisecond;
import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

@Service
public class JudgerImpl implements Judger {
    // > Utils
    @Autowired
    private ObjectMapper objectMapper;

    private String base64Encode(String s) {
        if (s == null) return s;
        return new String(Base64.getEncoder().encode(s.getBytes()));
    }

    private String base64Decode(String s) {
        if (s == null) return s;
        return new String(Base64.getDecoder().decode(s.replace("\n", "")));
    }

    // < Utils

    // ============================================================================================

    // > Create Submission

    private static class CreateSubmissionRequest {
        public int language_id;
        public String source_code;
        public String stdin;
    }

    private static class CreateSubmissionResponse {
        public String token;
    }


    public String createSubmission(String code, String input, ProgrammingLanguage programmingLanguage) throws IOException, InterruptedException {
        CreateSubmissionRequest request = new CreateSubmissionRequest();
        request.language_id = getJudge0ProgrammingLanguageId(programmingLanguage);
        request.source_code = new String(Base64.getEncoder().encode(code.getBytes()));
        request.stdin = new String(Base64.getEncoder().encode(input.getBytes()));
        String requestString = objectMapper.writeValueAsString(request);
        HttpRequest httpReq = HttpRequest.newBuilder()
                .uri(URI.create(Judge0Config.SERVER_URL + "/submissions" + "?base64_encoded=true&fields=*"))
                .header("content-type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(requestString))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(httpReq, HttpResponse.BodyHandlers.ofString());
        CreateSubmissionResponse resp = objectMapper.readValue(response.body(), CreateSubmissionResponse.class);
        return resp.token;
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
        // cannot reach
        return 0;
    }

    // < Create Submission

    // ============================================================================================

    // > Get Submission

    public static class SubmissionImpl implements Submission {
        private String stdout;
        private String time;
        private String memory;
        private String stderr;
        private String compileOutput;
        private int status;

        public SubmissionImpl(String stdout, String time, String memory, String stderr, String compileOutput, int status) {
            this.stdout = stdout;
            this.time = time;
            this.memory = memory;
            this.stderr = stderr;
            this.compileOutput = compileOutput;
            this.status = status;
        }

        public boolean processing() {
            return status == 2;
        }

        public boolean inQueue() {
            return status == 1;
        }

        public boolean compilationError() {
            return compileOutput != null;
        }

        public String compilationMessage() {
            return compileOutput == null ?
                    "" : compileOutput;
        }

        public boolean stdError() {
            return compileOutput != null;
        }

        public String stdMessage() {
            return compileOutput == null ?
                    "" : compileOutput;
        }

        public KB memory() {
            return new KB(Double.parseDouble(memory));
        }

        public Millisecond runTime() {
            return new Millisecond(Math.round(Double.parseDouble(time)));
        }

        public Output output() {
            return new Output(stdout);
        }

        @Override
        public String toString() {
            return "Submission{" +
                    "stdout='" + stdout + '\'' +
                    ", time='" + time + '\'' +
                    ", memory='" + memory + '\'' +
                    ", stderr='" + stderr + '\'' +
                    ", compileOutput='" + compileOutput + '\'' +
                    ", status=" + status +
                    '}';
        }
    }

    private static class GetSubmissionByIdResponseStatus {
        public int id;
    }

    private static class GetSubmissionByIdResponse {
        public String stdout;
        public String time;
        public String memory;
        public String stderr;
        public String compile_output;
        public GetSubmissionByIdResponseStatus status;
    }

    public Submission getSubmissionById(String submissionId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Judge0Config.SERVER_URL + "/submissions" + "/" + submissionId + "?base64_encoded=true&fields=*"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        GetSubmissionByIdResponse submission = objectMapper.readValue(response.body(), GetSubmissionByIdResponse.class);
        return new SubmissionImpl(
                base64Decode(submission.stdout),
                submission.time,
                submission.memory,
                base64Decode(submission.stderr),
                base64Decode(submission.compile_output),
                submission.status.id
        );
    }

    // < Get Submission

    // ============================================================================================
}
