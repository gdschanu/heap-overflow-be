package hanu.gdsc.contest.services.contest;

import hanu.gdsc.contest.domains.contest.Contest;
import hanu.gdsc.problem.domains.ID;

public interface GetContestService {
    public Contest getByProblemId(ID problemId);
}
