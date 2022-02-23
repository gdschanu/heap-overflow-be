package hanu.gdsc.contest.services.contest;

import hanu.gdsc.contest.domains.Contest;
import hanu.gdsc.problem.domains.ID;

import java.time.ZonedDateTime;
import java.util.List;

public interface GetContestService {
    public Contest getByProblemId(ID problemId);
}
