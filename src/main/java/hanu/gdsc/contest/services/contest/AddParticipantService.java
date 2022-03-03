package hanu.gdsc.contest.services.contest;

import hanu.gdsc.share.domains.Id;

public interface AddParticipantService {
    public void execute(Id coderId, Id contestId);
}
