package hanu.gdsc.contest_contest.services.contest;

import hanu.gdsc.contest_contest.domains.Participant;
import hanu.gdsc.contest_contest.repositories.contest.ContestRepository;
import hanu.gdsc.contest_contest.repositories.participant.ParticipantRepository;
import hanu.gdsc.contest_contest.services.participant.CreateParticipantService;
import hanu.gdsc.contest_contest.services.participant.SearchParticipantService;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinContestService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private ContestRepository contestRepository;

    @Autowired
    private SearchParticipantService searchParticipantService;

    @Autowired
    private CreateParticipantService createParticipantService;

    public void joinContest(Id coderId, Id contestId) {
        createParticipantService.execute(coderId, contestId);
    }

    public boolean checkIfCoderJoinContest(Id coderId, Id contestId) {
        Participant participant = searchParticipantService.getByCoderId(coderId);
        if (participant == null) {
            throw new BusinessLogicError("Coder không tồn tại", "NOT_FOUND");
        } else {
            if(!participant.getContestId().toString().equals(contestId.toString())) {
                return false;
            }
        }
        return true;
    }
}
