package hanu.gdsc.contest.services.contest;

import hanu.gdsc.contest.domains.Contest;
import hanu.gdsc.contest.domains.Participant;
import hanu.gdsc.contest.repositories.ContestRepository;
import hanu.gdsc.contest.repositories.ParticipantRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddParticipantServiceImpl implements AddParticipantService {
    private final ContestRepository contestRepository;
    private final ParticipantRepository participantRepository;

    @Override
    public void execute(Id coderId, Id contestId) {
        Contest contest = contestRepository.getById(contestId);
        if (!contest.canAddParticipant()) {
            throw new BusinessLogicError("Không thể thêm thí sinh, kì thi đang diễn ra hoặc đã kết thúc.");
        }
        Participant participant = Participant.create(coderId, contestId);
        participantRepository.save(participant);
    }
}
