package hanu.gdsc.coreProblem.services.problem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hanu.gdsc.coreProblem.config.Judge0Config;
import hanu.gdsc.coreProblem.domains.KB;
import hanu.gdsc.coreProblem.domains.Millisecond;
import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import hanu.gdsc.coreProblem.domains.RunCodeOutput;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

@Service
public class RunCodeServiceImpl implements RunCodeService {
    @Override
    public Output execute(String code, String input, ProgrammingLanguage programmingLanguage) {
        try {
            String submissionToken = createSubmission(code, input, programmingLanguage);
            Judge0Submission submission = getSubmission(submissionToken);
            if (submission.compilationError()) {
                return Output.builder()
                        .compilationError(true)
                        .compilationMessage(
                                new String(
                                        Base64.getDecoder().decode(
                                                submission.compile_output.replace("\n", "")
                                        )
                                )
                        )
                        .build();
            }
            if (submission.stderr()) {
                return Output.builder()
                        .stdError(true)
                        .stdMessage(
                                new String(
                                        Base64.getDecoder().decode(
                                                submission.stderr.replace("\n", "")
                                        )
                                )
                        )
                        .build();
            }
            return Output.builder()
                    .runTime(Millisecond.fromSecond(Float.parseFloat(submission.time)))
                    .memory(new KB(Float.parseFloat(submission.memory)))
                    .output(
                            new RunCodeOutput(
                                    new String(
                                            Base64.getDecoder().decode(
                                                    submission.stdout.replace("\n", "")
                                            )
                                    )
                            )
                    )
                    .compilationError(false)
                    .stdError(false)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error("Judge server error.");
        }
    }

    private static class CreateSubmissionRequest {
        public int language_id;
        public String source_code;
        public String stdin;
    }

    private static class CreateSubmissionResponse {
        public String token;
    }

    private String createSubmission(String code, String input, ProgrammingLanguage programmingLanguage) throws Exception {
        CreateSubmissionRequest request = new CreateSubmissionRequest();
        request.language_id = getJudge0ProgrammingLanguageId(programmingLanguage);
        request.source_code = new String(Base64.getEncoder().encode(code.getBytes()));
        request.stdin = new String(Base64.getEncoder().encode(input.getBytes()));
        Gson gson = new GsonBuilder().create();
        String requestString = gson.toJson(request);
        HttpRequest httpReq = HttpRequest.newBuilder()
                .uri(URI.create(Judge0Config.SERVER_URL + "?base64_encoded=true&fields=*"))
                .header("content-type", "application/json")
                .header("x-rapidapi-host", "judge0-ce.p.rapidapi.com")
                .header("x-rapidapi-key", "f803e60e33msh9bba8830c9044abp1f5592jsn0abe369e6307")
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

    private static class Judge0Submission {
        public String stdout;
        public String time;
        public String memory;
        public String stderr;
        public String compile_output;

        boolean compilationError() {
            return compile_output != null;
        }

        boolean stderr() {
            return stderr != null;
        }
    }

    private Judge0Submission getSubmission(String submissonToken) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Judge0Config.SERVER_URL + "/" + submissonToken + "?base64_encoded=true&fields=*"))
                .header("x-rapidapi-host", "judge0-ce.p.rapidapi.com")
                .header("x-rapidapi-key", "f803e60e33msh9bba8830c9044abp1f5592jsn0abe369e6307")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new GsonBuilder().create();
        Judge0Submission submission = gson.fromJson(response.body(), Judge0Submission.class);
        return submission;
    }
}
