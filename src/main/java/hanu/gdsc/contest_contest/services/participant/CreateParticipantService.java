package hanu.gdsc.contest_contest.services.participant;

import hanu.gdsc.contest_contest.domains.Contest;
import hanu.gdsc.contest_contest.domains.Participant;
import hanu.gdsc.contest_contest.errors.ContestEndedError;
import hanu.gdsc.contest_contest.repositories.contest.ContestRepository;
import hanu.gdsc.contest_contest.repositories.participant.ParticipantRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.NotFoundError;
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
    public void execute(Id coderId, Id contestId) {
        final Contest contest = contestRepository.getById(contestId);
        if (contest == null) {
            throw new NotFoundError("Contest không tồn tại.");
        }
        if (contest.ended()) {
            throw new ContestEndedError();
        }
        final Participant participant = Participant.create(coderId, contest);
        participantRepository.save(participant);
        if (contestRepository.getById(contestId) == null) {
            throw new NotFoundError("Contest đã bị xóa");
        }
    }
}
