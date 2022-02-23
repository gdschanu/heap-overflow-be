package hanu.gdsc.problem.services.submission;

import hanu.gdsc.contest.domains.contest.Contest;
import hanu.gdsc.contest.services.contest.GetContestService;
import hanu.gdsc.problem.domains.ID;
import hanu.gdsc.problem.domains.submission.Submission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetSubmissionServiceImpl implements GetSubmissionService {
    private final GetContestService getContestService;

    @Override
    public Submission getById(ID id) {
        Submission submission = null; // TODO: get from repository
        Contest contest = getContestService.getByProblemId(submission.getProblemId());
        if (contest != null && contest.isRunning()) {
            throw new Error("Bạn không được xem Submission của Contest đang diễn ra.");
        }
        return submission;
    }
}
