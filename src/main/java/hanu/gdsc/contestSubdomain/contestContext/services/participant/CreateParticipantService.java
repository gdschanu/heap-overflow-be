package hanu.gdsc.contestSubdomain.contestContext.services.participant;

import hanu.gdsc.share.domains.Id;

public interface CreateParticipantService {
    public void execute(Id coderId, Id contestId);
}
