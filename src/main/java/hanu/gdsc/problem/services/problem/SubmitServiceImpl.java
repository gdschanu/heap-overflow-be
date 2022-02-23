package hanu.gdsc.problem.services.problem;

import hanu.gdsc.contest.services.contest.GetContestService;
import hanu.gdsc.contest.services.contestProblem.GetContestProblemService;
import hanu.gdsc.problem.domains.ID;
import hanu.gdsc.problem.domains.submission.Submission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SubmitServiceImpl implements SubmitService {
    private final GetContestService getContestService;
    private final GetContestProblemService getContestProblemService;

    private boolean problemIsInAnyRunningContest(ID problemId) {
        List<GetContestService.ContestDTO> contests = getContestService.getAllRunningContest();
        List<GetContestProblemService.ContestProblemDTO> contestProblems = getContestProblemService
                .getContestProblemsOfContests(contests
                        .stream().map(c -> c.id)
                        .collect(Collectors.toList()));
        for (GetContestProblemService.ContestProblemDTO contestProblem : contestProblems) {
            if (contestProblem.problemId.equals(problemId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public InContestSubmitOutput inContestSubmit(SubmitInput input) {
        if (!problemIsInAnyRunningContest(input.problemId)) {
            throw new Error("Problem không thuộc về Contest nào.");
        }
        Submission submission = submit(input);
        return InContestSubmitOutput.builder()
                .runTime(submission.getRunTime())
                .memory(submission.getMemory())
                .status(submission.getStatus())
                .build();
    }

    @Override
    public OutContestSubmitOutput outContestSubmit(SubmitInput input) {
        if (problemIsInAnyRunningContest(input.problemId)) {
            throw new Error("Problem đang được sử dụng trong Contest.");
        }
        Submission submission = submit(input);
        return OutContestSubmitOutput.builder()
                .runTime(submission.getRunTime())
                .memory(submission.getMemory())
                .status(submission.getStatus())
                .failedTestCase(submission.getFailedTestCase())
                .build();
    }

    private Submission submit(SubmitInput input) {
        // TODO: implement this
        return null;
    }
}
