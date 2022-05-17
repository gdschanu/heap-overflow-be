package hanu.gdsc.coreProblem.services.submit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hanu.gdsc.coreProblem.config.Judge0Config;
import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
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

    Gson gson = new GsonBuilder().create();

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
        String requestString = gson.toJson(request);
        HttpRequest httpReq = HttpRequest.newBuilder()
                .uri(URI.create(Judge0Config.SERVER_URL + "/submissions" + "?base64_encoded=true&fields=*"))
                .header("content-type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(requestString))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(httpReq, HttpResponse.BodyHandlers.ofString());
        CreateSubmissionResponse resp = gson.fromJson(response.body(), CreateSubmissionResponse.class);
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
        GetSubmissionByIdResponse submission = gson.fromJson(response.body(), GetSubmissionByIdResponse.class);
        return new Submission(
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
