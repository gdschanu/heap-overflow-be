package hanu.gdsc.contestSubdomain.contestContext.services.contest;

import hanu.gdsc.share.domains.Id;

public interface DeleteProblemService {
    public void execute(Id contestId, int ordinal);
}
