package hanu.gdsc.contest.services.problem;

import hanu.gdsc.share.domains.Id;

public interface DeleteProblemService {
    public void execute(Id contestId, int ordinal);
}
