package hanu.gdsc.contest_contest.services.participant;

import hanu.gdsc.contest_contest.domains.Contest;
import hanu.gdsc.contest_contest.domains.Participant;
import hanu.gdsc.contest_contest.exception.ContestEndedException;
import hanu.gdsc.contest_contest.repositories.contest.ContestRepository;
import hanu.gdsc.contest_contest.repositories.participant.ParticipantRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CreateParticipantService {
    private final ContestRepository contestRepository;
    private final ParticipantRepository participantRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void execute(Id coderId, Id contestId) throws NotFoundException, ContestEndedException {
        final Contest contest = contestRepository.getById(contestId);
        if (contest == null) {
            throw new NotFoundException("Contest không tồn tại.");
        }
<<<<<<< HEAD

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
=======
        final Participant participant = Participant.create(coderId, contest);
        participantRepository.save(participant);
        if (contestRepository.getById(contestId) == null) {
            throw new NotFoundException("Contest đã bị xóa");
>>>>>>> ac6a0db65f7a56ddb504018446d5ecd3f1d34891
        }
    }
}
