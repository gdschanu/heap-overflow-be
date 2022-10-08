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
        final Participant participant = Participant.create(coderId, contest);
        participantRepository.save(participant);
        if (contestRepository.getById(contestId) == null) {
            throw new NotFoundException("Contest đã bị xóa");
        }
    }
}
