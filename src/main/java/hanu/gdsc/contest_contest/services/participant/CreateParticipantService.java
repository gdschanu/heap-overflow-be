package hanu.gdsc.contest_contest.services.participant;

import hanu.gdsc.share.domains.Id;

public interface CreateParticipantService {
    public void execute(Id coderId, Id contestId);
}
