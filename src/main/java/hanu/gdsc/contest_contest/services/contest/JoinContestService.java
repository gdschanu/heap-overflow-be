package hanu.gdsc.contest_contest.services.contest;

import hanu.gdsc.contest_contest.domains.Participant;
import hanu.gdsc.contest_contest.exception.ContestEndedException;
import hanu.gdsc.contest_contest.repositories.contest.ContestRepository;
import hanu.gdsc.contest_contest.repositories.participant.ParticipantRepository;
import hanu.gdsc.contest_contest.services.participant.CreateParticipantService;
import hanu.gdsc.contest_contest.services.participant.SearchParticipantService;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void joinContest(Id coderId, Id contestId) throws ContestEndedException, NotFoundException {
        createParticipantService.execute(coderId, contestId);
    }

    public boolean checkIfCoderJoinContest(Id coderId, Id contestId) {
        List<Participant> participants = searchParticipantService.getByCoderId(coderId);
        if (participants == null) {
            return false;
        } else {
            for(Participant participant: participants) {
                if(participant.getContestId().toString().equals(contestId.toString())) {
                    return true;
                }
            }
            return false;
        }
    }
}
