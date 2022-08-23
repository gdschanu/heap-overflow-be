package hanu.gdsc.contest_contest.services.contest;

import hanu.gdsc.contest_contest.repositories.contest.ContestRepository;
import hanu.gdsc.contest_contest.repositories.participant.ParticipantRepository;
import hanu.gdsc.contest_contest.services.participant.CreateParticipantService;
import hanu.gdsc.contest_contest.services.participant.CreateParticipantServiceImpl;
import hanu.gdsc.contest_contest.services.participant.SearchParticipantService;
import hanu.gdsc.share.domains.Id;
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

    public void joinContest(Id coderId, Id contestId) {
        CreateParticipantService createParticipantService =
                new CreateParticipantServiceImpl(contestRepository, participantRepository);
        createParticipantService.execute(coderId, contestId);
    }

    public boolean checkIfCoderJoinContest(Id coderId) {
        if (searchParticipantService.getById(coderId) != null) {
            return false;
        } else {
            return true;
        }
    }
}
