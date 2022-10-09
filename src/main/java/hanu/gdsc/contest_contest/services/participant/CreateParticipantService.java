package hanu.gdsc.contest_contest.services.participant;

import hanu.gdsc.contest_contest.domains.Contest;
import hanu.gdsc.contest_contest.domains.Participant;
import hanu.gdsc.contest_contest.repositories.contest.ContestRepository;
import hanu.gdsc.contest_contest.repositories.participant.ParticipantRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class CreateParticipantService {
    private final ContestRepository contestRepository;
    private final ParticipantRepository participantRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void execute(Id coderId, Id contestId) {
        Contest contest = contestRepository.getById(contestId);
        if (contest == null) {
            throw new BusinessLogicError("Contest không tồn tại.", "NOT_FOUND");
        }

        Participant existedParticipant = participantRepository.getById(
                coderId.toString() + "#" + contestId.toString());
        if(existedParticipant != null) {
            throw new BusinessLogicError("Coder đã tham gia contest này", "PARTICIPATED");
        }

        List<Participant> existedParticipantInAnother = participantRepository.getByCoderId(coderId);
        if(existedParticipantInAnother.size() != 0) {
            throw new BusinessLogicError("Coder đang tham gia contest khác", "PARTICIPATED_IN_ANOTHER");
        }

        Participant participant = Participant.create(coderId, contest);
        participantRepository.create(participant);

        contest = contestRepository.getById(contestId);
        if (contest == null) {
            throw new BusinessLogicError("Contest đã bị xóa", "NOT_FOUND");
        }
    }
}
