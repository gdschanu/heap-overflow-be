package hanu.gdsc.infrastructure.contest.controllers.testCase;

import hanu.gdsc.domain.contest.services.testCase.CreateContestTestCaseService;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.infrastructure.share.controller.ControllerHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Contest - TestCase", description = "Rest-API endpoint for Contest")
public class CreateContestTestCaseController {
    @Autowired
    private CreateContestTestCaseService service;

    @Schema(title = "Create Test Case", description = "Data transfer object for TestCase to create")
    public static class InputCreate {
        @Schema(description = "specify the input of this testcase in problem", example = "String", required = true)
        public String input;
        @Schema(description = "specify the expectedOutput of this testcase in problem", example = "String", required = true)
        public String expectedOutput;
        @Schema(description = "specify the oridinal of this testcase in problem", example = "1", required = true)
        public int ordinal;
        @Schema(description = "specify this testcase is sample or not", example = "true", required = true)
        public boolean isSample;
        @Schema(description = "specify the description of this testcase", example = "blablablabla", required = true)
        public String description;
    }


    @PostMapping("/contest/{contestId}/problem/{contestProblemOrdinal}/testCase")
    public ResponseEntity<?> create(@RequestBody InputCreate input,
                                    @PathVariable("contestId") String contestId,
                                    @PathVariable("contestProblemOrdinal") int contestProblemOrdinal) {
        return ControllerHandler.handle(() -> {
            service.create(new CreateContestTestCaseService.Input(
                    new Id(contestId),
                    contestProblemOrdinal,
                    input.input,
                    input.expectedOutput,
                    input.ordinal,
                    input.isSample,
                    input.description
            ));
            return new ControllerHandler.Result(
                    "Success",
                    null
            );
        });
    }
}
