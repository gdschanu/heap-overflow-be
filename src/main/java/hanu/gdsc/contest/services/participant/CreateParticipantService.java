package hanu.gdsc.contest.services.participant;

import hanu.gdsc.contest.domains.Contest;
import hanu.gdsc.contest.domains.Participant;
import hanu.gdsc.contest.exception.AlreadyJoinedException;
import hanu.gdsc.contest.exception.ContestEndedException;
import hanu.gdsc.contest.repositories.contest.ContestRepository;
import hanu.gdsc.contest.repositories.participant.ParticipantRepository;
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

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void execute(Id coderId, Id contestId) throws NotFoundException, ContestEndedException, AlreadyJoinedException {
        Contest contest = contestRepository.getById(contestId);
        if (contest == null) {
            throw new NotFoundException("Contest not found.");
        }
        if (participantRepository.getByCoderIdAndContestId(coderId, contestId) != null) {
            throw new AlreadyJoinedException("You have already joined this contest.");
        }
        final Participant participant = Participant.create(coderId, contest);
        participantRepository.save(participant);
        contest = contestRepository.getById(contestId);
        if (contestRepository.getById(contestId) == null) {
            throw new NotFoundException("Contest removed");
        }
    }
}
