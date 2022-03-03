package hanu.gdsc.contest.services.participant;

import hanu.gdsc.contest.domains.Contest;
import hanu.gdsc.contest.domains.Participant;
import hanu.gdsc.contest.repositories.ContestRepository;
import hanu.gdsc.contest.repositories.ParticipantRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateParticipantServiceImpl implements CreateParticipantService {
    private final ContestRepository contestRepository;
    private final ParticipantRepository participantRepository;

    @Override
    public void execute(Id coderId, Id contestId) {
        Contest contest = contestRepository.getById(contestId);
        if (contest == null) {
            throw new BusinessLogicError("Contest không tồn tại.");
        }
        Participant participant = Participant.create(coderId, contest);
        participantRepository.create(participant);
    }
}
