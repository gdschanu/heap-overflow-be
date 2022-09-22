package hanu.gdsc.contest_contest.repositories.participant;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ParticipantJPARepository extends PagingAndSortingRepository<ParticipantEntity, String> {
    public List<ParticipantEntity> findByContestId(String contestId, Pageable pageable);

    List<ParticipantEntity> findByCoderId(String coderId);
}
