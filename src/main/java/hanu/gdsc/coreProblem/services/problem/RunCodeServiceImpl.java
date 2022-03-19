package hanu.gdsc.coreProblem.services.problem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hanu.gdsc.coreProblem.domains.KB;
import hanu.gdsc.coreProblem.domains.Millisecond;
import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import hanu.gdsc.coreProblem.domains.RunCodeOutput;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
                        .compilationMessage(submission.compileOutput)
                        .build();
            }
            if (submission.stderr()) {
                return Output.builder()
                        .stdError(true)
                        .stdMessage(submission.stderr)
                        .build();
            }
            return Output.builder()
                    .runTime(Millisecond.fromSecond(Float.parseFloat(submission.time)))
                    .memory(new KB(Float.parseFloat(submission.memory)))
                    .output(new RunCodeOutput(submission.stdout))
                    .compilationError(false)
                    .stdError(false)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error("Judge server error.");
        }
    }

    private String createSubmission(String code, String input, ProgrammingLanguage programmingLanguage) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://judge0-ce.p.rapidapi.com/submissions?base64_encoded=true&fields=*"))
                .header("content-type", "application/json")
                .header("x-rapidapi-host", "judge0-ce.p.rapidapi.com")
                .header("x-rapidapi-key", "f803e60e33msh9bba8830c9044abp1f5592jsn0abe369e6307")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\n    \"language_id\": 52,\n    \"source_code\": \"I2luY2x1ZGUgPHN0ZGlvLmg+CgppbnQgbWFpbih2b2lkKSB7CiAgY2hhciBuYW1lWzEwXTsKICBzY2FuZigiJXMiLCBuYW1lKTsKICBwcmludGYoImhlbGxvLCAlc1xuIiwgbmFtZSk7CiAgcmV0dXJuIDA7Cn0=\",\n    \"stdin\": \"SnVkZ2Uw\"\n}"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private static class Judge0Submission {
        public String stdout;
        private String time;
        private String memory;
        public String stderr;
        public String token;
        public String compileOutput;
        public String message;

        boolean compilationError() {
            return compileOutput != null;
        }

        boolean stderr() {
            return stderr != null;
        }
    }

    private Judge0Submission getSubmission(String submissonToken) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://judge0-ce.p.rapidapi.com/submissions/9cec3d91-8596-43c4-8747-ed61f0a7011b?base64_encoded=true&fields=*"))
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
