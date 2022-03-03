package hanu.gdsc.contest.services.participant;

import hanu.gdsc.share.domains.Id;

public interface CalculateParticipantRankService {
    public void execute(Id contestId);
}
