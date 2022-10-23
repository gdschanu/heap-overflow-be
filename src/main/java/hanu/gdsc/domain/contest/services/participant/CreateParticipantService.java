package hanu.gdsc.domain.contest.services.participant;

import hanu.gdsc.domain.contest.models.Contest;
import hanu.gdsc.domain.contest.models.Participant;
import hanu.gdsc.domain.contest.models.ParticipantCount;
import hanu.gdsc.domain.contest.exception.AlreadyJoinedException;
import hanu.gdsc.domain.contest.exception.ContestEndedException;
import hanu.gdsc.domain.contest.repositories.ContestRepository;
import hanu.gdsc.domain.contest.repositories.ParticipantRepository;
import hanu.gdsc.domain.contest.repositories.ParticipantCountRepository;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CreateParticipantService {
    private final ContestRepository contestRepository;
    private final ParticipantRepository participantRepository;
    private final ParticipantCountRepository participantCountRepository;


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
        ParticipantCount participantCount = participantCountRepository.getByContestId(contestId);
        participantCount.increaseNum();
        participantCountRepository.save(participantCount);
    }
}
